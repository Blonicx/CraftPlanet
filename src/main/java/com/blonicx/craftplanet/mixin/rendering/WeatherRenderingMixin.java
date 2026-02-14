package com.blonicx.craftplanet.mixin.rendering;

import com.blonicx.craftplanet.integration.config.ConfigManager;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.WeatherEffectRenderer;
import net.minecraft.server.level.ParticleStatus;
import net.minecraft.world.phys.Vec3;

@Mixin(WeatherEffectRenderer.class)
public class WeatherRenderingMixin {
    @Inject(method = "renderInstances", at = @At("HEAD"), cancellable = true)
    void renderPieces(VertexConsumer vertexConsumer, List<WeatherEffectRenderer.ColumnInstance> pieces, Vec3 pos, float intensity, int range, float gradient, CallbackInfo ci) {
        if (ConfigManager.config.disableWeatherRendering) {
            ci.cancel();
        }
    }

    @Inject(method = "tickRainParticles", at = @At("HEAD"), cancellable = true)
    void addParticlesAndSound(ClientLevel world, Camera camera, int ticks, ParticleStatus particlesMode, int weatherRadius, CallbackInfo ci) {
        if (ConfigManager.config.disableWeatherRendering) {
            ci.cancel();
        }
    }
}
