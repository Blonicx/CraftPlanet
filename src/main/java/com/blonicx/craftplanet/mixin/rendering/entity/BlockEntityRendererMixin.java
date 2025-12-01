package com.blonicx.craftplanet.mixin.rendering.entity;

import com.blonicx.craftplanet.integration.config.ConfigManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntityRenderer.class)
public class BlockEntityRendererMixin {
    @Inject(method = "getRenderDistance", at = @At("HEAD"), cancellable = true)
    void getRenderDistance(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue(ConfigManager.config.maxBlockEntityRenderDistance);
    }
}
