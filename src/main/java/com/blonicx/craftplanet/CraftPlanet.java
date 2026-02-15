package com.blonicx.craftplanet;

import com.blonicx.craftplanet.event.KeyInputEvents;
import com.blonicx.craftplanet.event.TooltipEvents;
import com.blonicx.craftplanet.integration.config.ConfigManager;
import net.fabricmc.api.ClientModInitializer;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CraftPlanet implements ClientModInitializer {
    public static final String MOD_ID = "craftplanet";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitializeClient() {
        // Register
        KeyInputEvents.register();
        TooltipEvents.register();

        // Load Data
        ConfigManager.load(FabricLoader.getInstance().getConfigDir().toFile());

        LOGGER.info("CraftPlanet initialized!");
    }
}