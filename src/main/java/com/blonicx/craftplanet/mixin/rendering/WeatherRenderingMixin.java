package com.blonicx.craftplanet.mixin.rendering;

import com.blonicx.craftplanet.integration.CPlanetConfig;
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
    //? if >= 1.21.9 {
    void renderPieces(VertexConsumer vertexConsumer, List<WeatherEffectRenderer.ColumnInstance> pieces, Vec3 pos, float intensity, int range, float gradient, CallbackInfo ci) {
    //?} else {
    /*void renderPieces(VertexConsumer vertexConsumer, List<WeatherEffectRenderer> pieces, Vec3 pos, float intensity, int range, float gradient, CallbackInfo ci) {
    *///?}
        if (CPlanetConfig.INSTANCE.instance().disableWeatherRendering) {
            ci.cancel();
        }
    }

    @Inject(method = "tickRainParticles", at = @At("HEAD"), cancellable = true)
    //? if >= 1.21.11 {
    void addParticlesAndSound(ClientLevel world, Camera camera, int ticks, ParticleStatus particlesMode, int weatherRadius, CallbackInfo ci) {
    //?} else {
    /*void addParticlesAndSound(ClientLevel clientLevel, Camera camera, int i, ParticleStatus particleStatus, CallbackInfo ci) {
    *///?}
        if (CPlanetConfig.INSTANCE.instance().disableWeatherRendering) {
            ci.cancel();
        }
    }
}
