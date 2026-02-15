package com.blonicx.craftplanet.integration;


import com.blonicx.craftplanet.CraftPlanet;
import com.blonicx.craftplanet.resources.Cache;
import com.google.gson.GsonBuilder;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.IntegerFieldControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class CPlanetConfig {
    public static final ConfigClassHandler<CPlanetConfig> INSTANCE = ConfigClassHandler.createBuilder(CPlanetConfig.class)
            .id(Identifier.fromNamespaceAndPath(CraftPlanet.MOD_ID, "config"))
            .serializer(config -> GsonConfigSerializerBuilder.create(config)
                    .setPath(FabricLoader.getInstance().getConfigDir().resolve("craftplanet.json"))
                    .appendGsonBuilder(GsonBuilder::setPrettyPrinting)
                    .setJson5(false)
                    .build())
            .build();

    // General
    @SerialEntry public boolean disableArmorRendering = false;
    @SerialEntry public int maxChatMessages = 100;

    // Performance
    @SerialEntry public boolean disableWeatherRendering = false;

    @SerialEntry public int maxParticles = 5000;

    @SerialEntry public int maxSignRenderDistance = 16;
    @SerialEntry public int maxItemFrameRenderDistance = 16;
    @SerialEntry public int maxEntityRenderDistance = 64;
    @SerialEntry public int maxBlockEntityRenderDistance = 64;

    // Cape
    @SerialEntry public String cape_name = "";

    public static ConfigCategory getGeneralCategory() {
        CPlanetConfig config = INSTANCE.instance();
        CPlanetConfig defaults = INSTANCE.defaults();

        return ConfigCategory.createBuilder()
                .name(Component.translatable("category.craftplanet.general"))
                .option(Option.<Boolean>createBuilder()
                        .name(Component.translatable("settings.craftplanet.disableArmorRendering"))
                        .description(OptionDescription.of(Component.translatable("settings.craftplanet.disableArmorRendering_tooltip")))
                        .binding(
                                defaults.disableArmorRendering,
                                () -> config.disableArmorRendering,
                                val -> config.disableArmorRendering = val
                        ).controller(TickBoxControllerBuilder::create)
                        .build())
                .option(Option.<Integer>createBuilder()
                        .name(Component.translatable("settings.craftplanet.maxChatMessages"))
                        .description(OptionDescription.of(Component.translatable("settings.craftplanet.maxChatMessages_tooltip")))
                        .binding(
                                defaults.maxChatMessages,
                                () -> config.maxChatMessages,
                                val -> config.maxChatMessages = val
                        ).controller(opt -> IntegerFieldControllerBuilder.create(opt)
                                .min(0)
                                .max(10000))
                        .build())
                .build();
    }

    public static ConfigCategory getPerformanceCategory() {
        CPlanetConfig config = INSTANCE.instance();
        CPlanetConfig defaults = INSTANCE.defaults();

        return ConfigCategory.createBuilder()
                .name(Component.translatable("category.craftplanet.performance"))
                .group(OptionGroup.createBuilder()
                        .name(Component.translatable("settings.craftplanet.generalDescription"))
                        .option(Option.<Boolean>createBuilder()
                                .name(Component.translatable("settings.craftplanet.disableWeatherRendering"))
                                .description(OptionDescription.of(Component.translatable("settings.craftplanet.disableWeatherRendering_tooltip")))
                                .binding(
                                        defaults.disableWeatherRendering,
                                        () -> config.disableWeatherRendering,
                                        val -> config.disableWeatherRendering = val
                                ).controller(TickBoxControllerBuilder::create)
                                .build())
                        .build())

                .group(OptionGroup.createBuilder()
                        .name(Component.translatable("settings.craftplanet.particlesDescription"))
                        .option(Option.<Integer>createBuilder()
                                .name(Component.translatable("settings.craftplanet.maxParticles"))
                                .description(OptionDescription.of(Component.translatable("settings.craftplanet.maxParticles_tooltip")))
                                .binding(
                                        defaults.maxParticles,
                                        () -> config.maxParticles,
                                        val -> {
                                            config.maxParticles = val;
                                            Minecraft.getInstance().particleEngine.clearParticles();
                                        }
                                ).controller(opt -> IntegerFieldControllerBuilder.create(opt)
                                        .min(0)
                                        .max(10000))
                                .build())
                        .build())

                .group(OptionGroup.createBuilder()
                        .name(Component.translatable("settings.craftplanet.renderDistancesDescription"))
                        .option(Option.<Integer>createBuilder()
                                .name(Component.translatable("settings.craftplanet.maxSignTextRendering"))
                                .description(OptionDescription.of(Component.translatable("settings.craftplanet.maxSignTextRendering")))
                                .binding(
                                        defaults.maxSignRenderDistance,
                                        () -> config.maxSignRenderDistance,
                                        val -> config.maxSignRenderDistance = val
                                ).controller(opt -> IntegerFieldControllerBuilder.create(opt)
                                        .min(0)
                                        .max(64))
                                .build())
                        .option(Option.<Integer>createBuilder()
                                .name(Component.translatable("settings.craftplanet.maxItemFrameRendering"))
                                .description(OptionDescription.of(Component.translatable("settings.craftplanet.maxItemFrameRendering_tooltip")))
                                .binding(
                                        defaults.maxItemFrameRenderDistance,
                                        () -> config.maxItemFrameRenderDistance,
                                        val -> config.maxItemFrameRenderDistance = val
                                ).controller(opt -> IntegerFieldControllerBuilder.create(opt)
                                        .min(0)
                                        .max(64))
                                .build())
                        .option(Option.<Integer>createBuilder()
                                .name(Component.translatable("settings.craftplanet.maxBlockEntityRenderDistance"))
                                .description(OptionDescription.of(Component.translatable("settings.craftplanet.maxBlockEntityRenderDistance")))
                                .binding(
                                        defaults.maxBlockEntityRenderDistance,
                                        () -> config.maxBlockEntityRenderDistance,
                                        val -> config.maxBlockEntityRenderDistance = val
                                ).controller(opt -> IntegerFieldControllerBuilder.create(opt)
                                        .min(0)
                                        .max(256))
                                .build())
                        .option(Option.<Integer>createBuilder()
                                .name(Component.translatable("settings.craftplanet.maxEntityRenderDistance"))
                                .description(OptionDescription.of(Component.translatable("settings.craftplanet.maxEntityRenderDistance_tooltip")))
                                .binding(
                                        defaults.maxEntityRenderDistance,
                                        () -> config.maxEntityRenderDistance,
                                        val -> config.maxEntityRenderDistance = val
                                ).controller(opt -> IntegerFieldControllerBuilder.create(opt)
                                        .min(0)
                                        .max(256))
                                .build())
                        .build())
                .build();
    }

    public static ConfigCategory getOtherCategory() {
        return ConfigCategory.createBuilder()
                .name(Component.translatable("category.craftplanet.other"))
                .option(ButtonOption.createBuilder()
                        .name(Component.translatable("button.craftplanet.clear_cache"))
                        .description(OptionDescription.of(Component.translatable("settings.craftplanet.clear_cache_tooltip")))
                        .action((yaclScreen, buttonOption) -> {
                            Cache.clearCache();
                        })
                        .build())
                .build();
    }
}
