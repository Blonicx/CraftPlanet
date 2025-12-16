package com.blonicx.craftplanet.utils;

import com.blonicx.craftplanet.CraftPlanet;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import oshi.SystemInfo;
import oshi.hardware.GraphicsCard;
import oshi.hardware.HardwareAbstractionLayer;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

public class SystemInfoUtil {
    private static final String URL_TEMPLATE =
            "https://gfwsl.geforce.com/services_toolkit/services/com/nvidia/services/AjaxDriverService.php"
                    + "?func=DriverManualLookup"
                    + "&pfid=%d"
                    + "&osID=%d"
                    + "&dch=%d";

    public static boolean isNvidiaDriverUpToDate() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();

        for (GraphicsCard gpu : hal.getGraphicsCards()) {

            if (!gpu.getName().toLowerCase().contains("nvidia"))
                continue;

            String installed = gpu.getVersionInfo();

            try {
                Optional<String> latestOpt = get_latest(gpu);
                if (latestOpt.isEmpty()) return true;

                String latest = latestOpt.get();

                return compareVersions(installed, latest) >= 0;

            } catch (Exception e) {
                CraftPlanet.LOGGER.error("Driver check failed", e);
                return true; // fail-safe
            }
        }

        return true; // no NVIDIA GPU found
    }

    private static int compareVersions(String a, String b) {
        String[] as = a.split("\\.");
        String[] bs = b.split("\\.");

        int len = Math.max(as.length, bs.length);
        for (int i = 0; i < len; i++) {
            int ai = i < as.length ? Integer.parseInt(as[i]) : 0;
            int bi = i < bs.length ? Integer.parseInt(bs[i]) : 0;
            if (ai != bi) return Integer.compare(ai, bi);
        }
        return 0;
    }

    static Optional<String> get_latest(GraphicsCard gpu) throws Exception {
        int pfid = 933; // RTX (basic)
        int osID = 57;  // Win10/11 64-bit
        int dch = 1;

        String url = String.format(URL_TEMPLATE, pfid, osID, dch);

        HttpURLConnection con = (HttpURLConnection) new URL(url).openConnection();
        con.setRequestMethod("GET");

        JsonObject json = JsonParser.parseReader(
                new InputStreamReader(con.getInputStream())
        ).getAsJsonObject();

        JsonObject info = json
                .getAsJsonArray("IDS")
                .get(0)
                .getAsJsonObject();

        return Optional.of(
                info.getAsJsonObject("downloadInfo")
                        .get("Version")
                        .getAsString()
        );
    }

}
