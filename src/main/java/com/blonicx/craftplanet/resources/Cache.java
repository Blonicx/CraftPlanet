package com.blonicx.craftplanet.resources;

import com.blonicx.craftplanet.CraftPlanet;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.util.Objects;

public class Cache {
    public static final String CACHE_DIR = new File(FabricLoader.getInstance().getConfigDir().toFile(), "craftplanet/cache/").toString();

    public static void clearCache() {
        File[] cache = new File(CACHE_DIR).listFiles();

        for (File file : Objects.requireNonNull(cache)) {
            file.delete();
        }

        CraftPlanet.LOGGER.info("Cleared {} files from the {} Cache!", cache.length, CraftPlanet.MOD_ID);
    }
}
