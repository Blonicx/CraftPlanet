package com.blonicx.craftplanet.mixin.rendering.entity.block;

import com.blonicx.craftplanet.integration.CPlanetConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import net.minecraft.client.renderer.entity.state.ItemFrameRenderState;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if >= 1.21.9 {
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.state.CameraRenderState;
//?} else {

//?}

@Mixin(ItemFrameRenderer.class)
public class ItemFrameEntityRendererMixin {
    //? if >= 1.21.9 {
    @Inject(method = "submit(Lnet/minecraft/client/renderer/entity/state/ItemFrameRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/SubmitNodeCollector;Lnet/minecraft/client/renderer/state/CameraRenderState;)V", at = @At("HEAD"), cancellable = true)
    void render(ItemFrameRenderState itemFrameEntityRenderState, PoseStack matrixStack, SubmitNodeCollector orderedRenderCommandQueue, CameraRenderState cameraRenderState, CallbackInfo ci) {
        LocalPlayer player = Minecraft.getInstance().player;

        assert player!=null;
        if (!player.blockPosition().closerToCenterThan(cameraRenderState.pos, CPlanetConfig.INSTANCE.instance().maxItemFrameRenderDistance)) ci.cancel();
    }
     //?} else {
    /*@Inject(method = "render(Lnet/minecraft/client/renderer/entity/state/ItemFrameRenderState;Lcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V", at = @At("HEAD"), cancellable = true)
    void render(ItemFrameRenderState itemFrameRenderState, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, CallbackInfo ci) {
        LocalPlayer player = Minecraft.getInstance().player;

        BlockPos pos = new BlockPos((int)itemFrameRenderState.x, (int)itemFrameRenderState.y, (int)itemFrameRenderState.z);

        assert player!=null;
        if (!player.blockPosition().closerThan(pos, CPlanetConfig.INSTANCE.instance().maxItemFrameRenderDistance)) ci.cancel();
    }
    *///?}
}
