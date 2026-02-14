package com.blonicx.craftplanet.mixin.entity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    @Inject(method = "isInWall", at = @At("HEAD"), cancellable = true)
    private void isInsideWall(CallbackInfoReturnable<Boolean> cir) {
        if(level().isClientSide()) {
            cir.setReturnValue(false);
        }
    }

    @Inject(method = "doPush", at = @At("HEAD"), cancellable = true)
    private void pushAway(CallbackInfo ci) {
        if (level().isClientSide()) {
            ci.cancel();
        }
    }
}
