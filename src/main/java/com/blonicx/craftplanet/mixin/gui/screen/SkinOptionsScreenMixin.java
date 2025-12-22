package com.blonicx.craftplanet.mixin.gui.screen;

import com.blonicx.craftplanet.screens.SkinEditor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.SkinOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(SkinOptionsScreen.class)
public abstract class SkinOptionsScreenMixin extends GameOptionsScreen {
    public SkinOptionsScreenMixin(Screen parent, GameOptions gameOptions, Text screenTitle) {
        super(parent, gameOptions, screenTitle);
    }

    @Inject(method = "addOptions", at = @At("TAIL"))
    private void addCustomButton(CallbackInfo ci) {
        List<ClickableWidget> list = new ArrayList<>();

        list.add(
                ButtonWidget.builder(Text.translatable("button.craftplanet.skin_editor"),button -> {
                            MinecraftClient.getInstance().setScreen(new SkinEditor());
                        })
                        .width(310)
                        .build()
        );

        this.body.addAll(list);
    }
}