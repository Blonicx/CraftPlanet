package com.blonicx.craftplanet.resources;

import net.fabricmc.loader.api.FabricLoader;

import java.io.File;

public class Cache {
    public static final String CACHE_DIR = new File(FabricLoader.getInstance().getConfigDir().toFile(), "craftplanet/cache/").toString();
}
