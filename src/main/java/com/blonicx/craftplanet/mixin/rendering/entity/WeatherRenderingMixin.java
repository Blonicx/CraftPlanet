package com.blonicx.craftplanet.mixin.rendering.entity;

import com.blonicx.craftplanet.integration.config.ConfigManager;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WeatherRendering;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.ParticlesMode;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(WeatherRendering.class)
public class WeatherRenderingMixin {
    @Inject(method = "renderPieces", at = @At("HEAD"), cancellable = true)
    void renderPieces(VertexConsumer vertexConsumer, List<WeatherRendering.Piece> pieces, Vec3d pos, float intensity, int range, float gradient, CallbackInfo ci) {
        if (ConfigManager.config.disableWeatherRendering) {
            ci.cancel();
        }
    }

    @Inject(method = "addParticlesAndSound", at = @At("HEAD"), cancellable = true)
    void addParticlesAndSound(ClientWorld world, Camera camera, int ticks, ParticlesMode particlesMode, CallbackInfo ci) {
        if (ConfigManager.config.disableWeatherRendering) {
            ci.cancel();
        }
    }
}
