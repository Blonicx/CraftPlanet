package com.blonicx.craftplanet.mixin.rendering.entity.block;

import com.blonicx.craftplanet.integration.config.ConfigManager;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.AbstractSignRenderer;
import net.minecraft.client.renderer.blockentity.state.SignRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractSignRenderer.class)
public class AbstractSignBlockEntityRendererMixin {
    @Inject(method = "submitSignText", at = @At("HEAD"), cancellable = true)
    void hasText(SignRenderState renderState, PoseStack matrices, SubmitNodeCollector queue, boolean front, CallbackInfo ci) {
        LocalPlayer player = Minecraft.getInstance().player;

        assert player!=null;
        if (!player.blockPosition().closerThan(renderState.blockPos, ConfigManager.config.maxSignRenderDistance)) ci.cancel();
    }
}
