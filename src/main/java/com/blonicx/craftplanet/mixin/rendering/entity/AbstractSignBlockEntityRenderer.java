package com.blonicx.craftplanet.mixin.rendering.entity;

import com.blonicx.craftplanet.integration.config.ConfigManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.client.render.block.entity.state.SignBlockEntityRenderState;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(net.minecraft.client.render.block.entity.AbstractSignBlockEntityRenderer.class)
public class AbstractSignBlockEntityRenderer {
    @Inject(method = "renderText", at = @At("HEAD"), cancellable = true)
    void hasText(SignBlockEntityRenderState renderState, MatrixStack matrices, OrderedRenderCommandQueue queue, boolean front, CallbackInfo ci) {
        ClientPlayerEntity player = MinecraftClient.getInstance().player;

        assert player!=null;
        if (!player.getBlockPos().isWithinDistance(renderState.pos, ConfigManager.config.maxSignRenderDistance)) ci.cancel();
    }
}
