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
                                Text.translatable("settings.craftplanet.disableArmorRendering"),
                                ConfigManager.config.disableArmorRendering
                        ).setDefaultValue(true)
                        .setTooltip(Text.translatable("settings.craftplanet.disableArmorRendering_tooltip"))
                        .setSaveConsumer(newValue -> ConfigManager.config.disableWeatherRendering = newValue)
                        .build()
        );

        general.addEntry(entry.startBooleanToggle(
                                Text.translatable("settings.craftplanet.filterChat"),
                                ConfigManager.config.filterChat
                        ).setDefaultValue(true)
                        .setTooltip(Text.translatable("settings.craftplanet.filterChat_tooltip"))
                        .setSaveConsumer(newValue -> ConfigManager.config.filterChat = newValue)
                        .build()
        );

        general.addEntry(
                entry.startTextDescription(Text.translatable("info.craftplanet.add_words")
                ).build()
        );

        // Performance
        var generalSub = entry.startSubCategory(
                Text.translatable("settings.craftplanet.generalDescription")
        );

        generalSub.add(entry.startBooleanToggle(
                                Text.translatable("settings.craftplanet.disableWeatherRendering"),
                                ConfigManager.config.disableWeatherRendering
                        ).setDefaultValue(true)
                        .setTooltip(Text.translatable("settings.craftplanet.disableWeatherRendering_tooltip"))
                        .setSaveConsumer(newValue -> ConfigManager.config.disableWeatherRendering = newValue)
                        .build()
        );

        // Particle
        var particlesSub = entry.startSubCategory(
                Text.translatable("settings.craftplanet.particlesDescription")
        );

        particlesSub.add(
                entry.startIntField(
                                Text.translatable("settings.craftplanet.maxParticles"),
                                ConfigManager.config.maxParticles
                        ).setDefaultValue(5000)
                        .setTooltip(Text.translatable("settings.craftplanet.maxParticles_tooltip"))
                        .setMin(0)
                        .setMax(10000)
                        .setSaveConsumer(newValue -> ConfigManager.config.maxParticles = newValue)
                        .build()
        );

        // Render Distances
        var renderDistancesSub = entry.startSubCategory(
                Text.translatable("settings.craftplanet.renderDistancesDescription")
        );

        renderDistancesSub.add(entry.startIntField(
                                Text.translatable("settings.craftplanet.maxSignTextRendering"),
                                ConfigManager.config.maxSignRenderDistance
                        ).setDefaultValue(16)
                        .setTooltip(Text.translatable("settings.craftplanet.maxSignTextRendering_tooltip"))
                        .setMin(0)
                        .setMax(64)
                        .setSaveConsumer(newValue -> ConfigManager.config.maxSignRenderDistance = newValue)
                        .build()
        );

        renderDistancesSub.add(entry.startIntField(
                                Text.translatable("settings.craftplanet.maxItemFrameRendering"),
                                ConfigManager.config.maxItemFrameRenderDistance
                        ).setDefaultValue(16)
                        .setTooltip(Text.translatable("settings.craftplanet.maxItemFrameRendering_tooltip"))
                        .setMin(0)
                        .setMax(64)
                        .setSaveConsumer(newValue -> ConfigManager.config.maxItemFrameRenderDistance = newValue)
                        .build()
        );

        renderDistancesSub.add(entry.startIntField(
                                Text.translatable("settings.craftplanet.maxBlockEntityRenderDistance"),
                                ConfigManager.config.maxBlockEntityRenderDistance
                        ).setDefaultValue(64)
                        .setTooltip(Text.translatable("settings.craftplanet.maxBlockEntityRenderDistance_tooltip"))
                        .setMin(0)
                        .setMax(256)
                        .setSaveConsumer(newValue -> ConfigManager.config.maxBlockEntityRenderDistance = newValue)
                        .build()
        );

        renderDistancesSub.add(entry.startIntField(
                                Text.translatable("settings.craftplanet.maxEntityRenderDistance"),
                                ConfigManager.config.maxEntityRenderDistance
                        ).setDefaultValue(64)
                        .setTooltip(Text.translatable("settings.craftplanet.maxEntityRenderDistance_tooltip"))
                        .setMin(0)
                        .setMax(256)
                        .setSaveConsumer(newValue -> ConfigManager.config.maxEntityRenderDistance = newValue)
                        .build()
        );

        // Build
        performance.addEntry(generalSub.build());
        performance.addEntry(particlesSub.build());
        performance.addEntry(renderDistancesSub.build());

        builder.setSavingRunnable(ConfigManager::save);

        return builder.build();
    }
}
