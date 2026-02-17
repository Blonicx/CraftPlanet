package com.blonicx.craftplanet.mixin.rendering.particle;

import com.blonicx.craftplanet.integration.CPlanetConfig;
import dev.blonicx.craftlib.api.particle.Particles;
import net.minecraft.client.particle.Particle;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if >= 1.21.9 {
import net.minecraft.client.particle.ParticleGroup;
//?} else {
/*import net.minecraft.client.particle.ParticleEngine;
*///?}

//? if >= 1.21.9 {
@Mixin(ParticleGroup.class)
 //?} else {
/*@Mixin(ParticleEngine.class)
*///?}
public abstract class ParticleRendererMixin {

    @Inject(method = "add", at = @At("HEAD"), cancellable = true)
    void add(Particle particle, CallbackInfo ci){
        if (!(Particles.ENGINE.particleAmount() < CPlanetConfig.INSTANCE.instance().maxParticles)) {
            ci.cancel();
        }
    }
}
