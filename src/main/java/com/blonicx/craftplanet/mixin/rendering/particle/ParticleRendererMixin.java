package com.blonicx.craftplanet.mixin.rendering.particle;

import com.blonicx.craftplanet.integration.CPlanetConfig;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ParticleGroup.class)
public abstract class ParticleRendererMixin {
    @Shadow
    public abstract int size();

    @Inject(method = "add", at = @At("HEAD"), cancellable = true)
    void add(Particle particle, CallbackInfo ci){
        if (!(this.size() < CPlanetConfig.INSTANCE.instance().maxParticles)) {
            ci.cancel();
        }
    }
}
