package com.blonicx.craftplanet.mixin.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    public abstract Level level();

    @Inject(method = "isInWall", at = @At("HEAD"), cancellable = true)
    private void isInsideWall(CallbackInfoReturnable<Boolean> cir) {
        if(level().isClientSide()) {
            cir.setReturnValue(false);
            cir.cancel();
        }
    }
}
