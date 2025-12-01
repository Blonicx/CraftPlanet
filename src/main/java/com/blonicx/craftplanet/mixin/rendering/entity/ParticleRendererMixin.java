package com.blonicx.craftplanet.mixin.rendering.entity;

import com.blonicx.craftplanet.integration.config.ConfigManager;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleRenderer.class)
public abstract class ParticleRendererMixin {
    @Shadow
    public abstract int size();

    @Inject(method = "add", at = @At("HEAD"), cancellable = true)
    void add(Particle particle, CallbackInfo ci){
        if (!(this.size() < ConfigManager.config.maxParticles)) {
            ci.cancel();
        }
    }
}
