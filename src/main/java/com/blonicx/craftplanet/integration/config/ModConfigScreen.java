package com.blonicx.craftplanet.integration.config;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

public class ModConfigScreen {

    public static Screen create(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(Text.translatable("title.craftplanet.config"));

        ConfigEntryBuilder entry = builder.entryBuilder();

        // Category
        var general = builder.getOrCreateCategory(Text.translatable("category.craftplanet.general"));
        var performance = builder.getOrCreateCategory(Text.translatable("category.craftplanet.performance"));

        // General
        general.addEntry(entry.startBooleanToggle(
                                Text.translatable("settings.craftplanet.filterChat"),
                                ConfigManager.config.filterChat
                        ).setDefaultValue(true)
                        .setTooltip(Text.translatable("settings.craftplanet.filterChat_tooltip"))
                        .setSaveConsumer(newValue -> ConfigManager.config.filterChat = newValue)
                        .build()
        );

        // Performance
        performance.addEntry(entry.startBooleanToggle(
                                Text.translatable("settings.craftplanet.maxSignTextRendering"),
                                ConfigManager.config.maxSignTextRendering
                        ).setDefaultValue(true)
                        .setTooltip(Text.translatable("settings.craftplanet.maxSignTextRendering_tooltip"))
                        .setSaveConsumer(newValue -> ConfigManager.config.maxSignTextRendering = newValue)
                        .build()
        );

        performance.addEntry(entry.startBooleanToggle(
                                Text.translatable("settings.craftplanet.disableWeatherRendering"),
                                ConfigManager.config.disableWeatherRendering
                        ).setDefaultValue(true)
                        .setTooltip(Text.translatable("settings.craftplanet.disableWeatherRendering_tooltip"))
                        .setSaveConsumer(newValue -> ConfigManager.config.disableWeatherRendering = newValue)
                        .build()
        );

        performance.addEntry(entry.startIntField(
                                Text.translatable("settings.craftplanet.maxParticles"),
                                ConfigManager.config.maxParticles
                        ).setDefaultValue(5000)
                        .setTooltip(Text.translatable("settings.craftplanet.maxParticles_tooltip"))
                        .setMin(0)
                        .setMax(10000)
                        .setSaveConsumer(newValue -> ConfigManager.config.maxParticles = newValue)
                        .build()
        );

        performance.addEntry(entry.startIntField(
                                Text.translatable("settings.craftplanet.maxBlockEntityRenderDistance"),
                                ConfigManager.config.maxBlockEntityRenderDistance
                        ).setDefaultValue(5000)
                        .setTooltip(Text.translatable("settings.craftplanet.maxBlockEntityRenderDistance_tooltip"))
                        .setMin(0)
                        .setMax(256)
                        .setSaveConsumer(newValue -> ConfigManager.config.maxBlockEntityRenderDistance = newValue)
                        .build()
        );

        builder.setSavingRunnable(ConfigManager::save);

        return builder.build();
    }
}
