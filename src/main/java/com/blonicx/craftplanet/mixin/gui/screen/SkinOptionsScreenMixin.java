package com.blonicx.craftplanet.mixin.gui.screen;

import com.blonicx.craftplanet.screens.SkinEditor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.client.gui.screens.options.SkinCustomizationScreen;
import net.minecraft.network.chat.Component;

@Mixin(SkinCustomizationScreen.class)
public abstract class SkinOptionsScreenMixin extends OptionsSubScreen {
    public SkinOptionsScreenMixin(Screen parent, Options gameOptions, Component screenTitle) {
        super(parent, gameOptions, screenTitle);
    }

    @Inject(method = "addOptions", at = @At("TAIL"))
    private void addCustomButton(CallbackInfo ci) {
        List<AbstractWidget> list = new ArrayList<>();

        list.add(
                Button.builder(Component.translatable("button.craftplanet.skin_editor"),button -> {
                            Minecraft.getInstance().setScreen(new SkinEditor());
                        })
                        .width(310)
                        .build()
        );

        this.list.addSmall(list);
    }
}