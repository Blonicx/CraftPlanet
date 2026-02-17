package com.blonicx.craftplanet.mixin.rendering.entity.block;

import com.blonicx.craftplanet.integration.CPlanetConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.blockentity.AbstractSignRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if >= 1.21.9 {
import net.minecraft.client.renderer.blockentity.state.SignRenderState;
import net.minecraft.client.renderer.SubmitNodeCollector;
 //?} else {
/*import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.SignText;
import net.minecraft.client.renderer.MultiBufferSource;
*///?}

@Mixin(AbstractSignRenderer.class)
public class AbstractSignBlockEntityRendererMixin {
    //? if >= 1.21.9 {
    @Inject(method = "submitSignText", at = @At("HEAD"), cancellable = true)
    void hasText(SignRenderState renderState, PoseStack matrices, SubmitNodeCollector queue, boolean front, CallbackInfo ci) {
        LocalPlayer player = Minecraft.getInstance().player;

        assert player!=null;
        if (!player.blockPosition().closerThan(renderState.blockPos, CPlanetConfig.INSTANCE.instance().maxSignRenderDistance)) ci.cancel();
    }
     //?} else {
    /*@Inject(method = "renderSignText", at = @At("HEAD"), cancellable = true)
    void renderSignText(BlockPos blockPos, SignText signText, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j, int k, boolean bl, CallbackInfo ci) {
        LocalPlayer player = Minecraft.getInstance().player;

        assert player!=null;
        if (!player.blockPosition().closerThan(blockPos, CPlanetConfig.INSTANCE.instance().maxSignRenderDistance)) ci.cancel();
    }
    *///?}
}
