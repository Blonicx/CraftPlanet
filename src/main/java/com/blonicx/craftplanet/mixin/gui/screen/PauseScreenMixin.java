package com.blonicx.craftplanet.mixin.gui.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Mixin(PauseScreen.class)
public class PauseScreenMixin extends Screen {
    protected PauseScreenMixin(Component component) {
        super(component);
    }

    @Unique
    StringWidget timeWidget;

    @Inject(method = "init", at = @At("HEAD"))
    void init(CallbackInfo ci) {
        int w = this.font.width(this.title);

        //? if >= 1.21.9 {
        timeWidget = new StringWidget(this.width / 2 - w / 4, 50, w, 9, Component.literal(""), this.font);
         //?} else {
        /*timeWidget = new StringWidget(this.width / 2 - w / 2, 50, w, 9, Component.literal(""), this.font);
        *///?}
        this.addRenderableWidget(timeWidget);
    }

    @Inject(method = "render", at = @At("HEAD"))
    void render(GuiGraphics guiGraphics, int i, int j, float f, CallbackInfo ci) {
        LocalTime now = LocalTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("HH:mm"));
        timeWidget.setMessage(Component.literal(time));
    }

}
