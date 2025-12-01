package com.blonicx.craftplanet.mixin.rendering.entity;

import com.blonicx.craftplanet.integration.config.ConfigManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BlockEntityRenderer.class)
public interface BlockEntityRendererMixin {
    /**
     * @author Blonicx
     * @reason Return set max Distance
     */
    @Overwrite default int getRenderDistance() {
        return ConfigManager.config.maxBlockEntityRenderDistance;
    }
}
