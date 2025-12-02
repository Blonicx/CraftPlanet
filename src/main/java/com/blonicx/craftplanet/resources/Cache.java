package com.blonicx.craftplanet.resources;

import com.blonicx.craftplanet.CraftPlanet;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Cache {
    private static final Path CACHE_DIR_PATH = FabricLoader.getInstance()
            .getConfigDir()
            .resolve("craftplanet")
            .resolve(".cache");

    public static void load() {
        try {
            Files.createDirectories(CACHE_DIR_PATH.getParent());
        } catch (IOException e) {
            CraftPlanet.LOGGER.error(e.toString());
        }
    }
}
