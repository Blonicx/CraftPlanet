package com.blonicx.craftplanet.integration.config;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class ConfigManager {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static File file;
    public static Config config = new Config();

    public static void load(File configDir) {
        try {
            file = new File(configDir, "craftplanet.json");

            if (file.exists()) {
                config = GSON.fromJson(new FileReader(file), Config.class);
            } else {
                save(); // first time
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void save() {
        try (FileWriter writer = new FileWriter(file)) {
            GSON.toJson(config, writer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
