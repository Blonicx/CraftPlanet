package com.blonicx.craftplanet.mixin.gui.screen;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.render.RenderTickCounter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class InGameHudMixin {

    @Inject(
            method = "render",
            at = @At("TAIL")
    )
    private void renderCustomText(DrawContext context, RenderTickCounter tickCounter, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player == null || client.options.hudHidden) return;

        String text = "FPS: " + MinecraftClient.getInstance().getCurrentFps();

        int screenWidth = client.getWindow().getScaledWidth();

        int textWidth = client.textRenderer.getWidth(text);

        int x = screenWidth - textWidth - 4; // 4px padding from right
        int y = 4; // 4px from top

        context.drawText(
                client.textRenderer,
                text,
                x,
                y,
                0xFFFFFF, // color
                true       // shadow
        );
    }
}
