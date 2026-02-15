package com.blonicx.craftplanet.integration;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import net.minecraft.network.chat.Component;

public class ModMenuIntegration implements ModMenuApi {
    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parent -> YetAnotherConfigLib.createBuilder()
                .title(Component.translatable("title.craftplanet.config"))
                .category(CPlanetConfig.getGeneralCategory())
                .category(CPlanetConfig.getPerformanceCategory())
                .category(CPlanetConfig.getOtherCategory())
                .save(CPlanetConfig.INSTANCE::save)
                .build().generateScreen(parent);
    }
}
