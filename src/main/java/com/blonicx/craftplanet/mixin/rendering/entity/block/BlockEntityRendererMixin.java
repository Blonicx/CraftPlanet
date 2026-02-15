package com.blonicx.craftplanet.mixin.rendering.entity.block;

import com.blonicx.craftplanet.integration.CPlanetConfig;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(BlockEntityRenderer.class)
public interface BlockEntityRendererMixin {
    /**
     * @author Blonicx
     * @reason Return set max Distance
     */
    @Overwrite default int getViewDistance() {
        return CPlanetConfig.INSTANCE.instance().maxBlockEntityRenderDistance;
    }
}
