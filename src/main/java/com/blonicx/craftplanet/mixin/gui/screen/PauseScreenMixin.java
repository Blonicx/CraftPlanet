package com.blonicx.craftplanet.mixin.gui.screen;

import net.minecraft.client.gui.components.StringWidget;
import net.minecraft.client.gui.screens.PauseScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
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

    @Inject(method = "init", at = @At("HEAD"))
    void init(CallbackInfo ci) {
        LocalTime now = LocalTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("HH:mm"));
        int w = this.font.width(this.title);

        //? if >= 1.21.9 {
        this.addRenderableWidget(new StringWidget(this.width / 2 - w / 4, 50, w, 9, Component.literal(time), this.font));
         //?} else {
        /*this.addRenderableWidget(new StringWidget(this.width / 2 - w / 2, 50, w, 9, Component.literal(time), this.font));
        *///?}
    }
}
