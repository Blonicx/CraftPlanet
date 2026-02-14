package com.blonicx.craftplanet.mixin.gui.screen;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphics;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Gui.class)
public class InGameHudMixin {

    @Inject(
            method = "render",
            at = @At("TAIL")
    )
    private void renderCustomText(GuiGraphics context, DeltaTracker tickCounter, CallbackInfo ci) {
        Minecraft client = Minecraft.getInstance();

        if (client.player == null || client.options.hideGui) return;

        String text = "FPS: " + Minecraft.getInstance().getFps();

        int screenWidth = client.getWindow().getGuiScaledWidth();

        int textWidth = client.font.width(text);

        int x = screenWidth - textWidth - 4; // 4px padding from right
        int y = 4; // 4px from top

        context.drawString(
                client.font,
                text,
                x,
                y,
                0xFFFFFF, // color
                true       // shadow
        );
    }
}
