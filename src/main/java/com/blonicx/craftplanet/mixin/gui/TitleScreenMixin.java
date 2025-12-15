package com.blonicx.craftplanet.mixin.gui;

import com.blonicx.craftplanet.utils.SystemInfoUtil;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {
    @Inject(method = "render", at = @At("HEAD"))
    void render(DrawContext context, int mouseX, int mouseY, float deltaTicks, CallbackInfo ci) {
         if (!SystemInfoUtil.isNvidiaDriverUpToDate()) context.drawText(MinecraftClient.getInstance().textRenderer, Text.translatable("info.craftplanet.outdated_driver"), context.getScaledWindowWidth() / 2, context.getScaledWindowHeight() / 2, 0, false);
    }
}
