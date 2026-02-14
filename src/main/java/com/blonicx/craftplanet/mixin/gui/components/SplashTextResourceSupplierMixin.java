package com.blonicx.craftplanet.mixin.gui.components;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import net.minecraft.client.resources.SplashManager;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.profiling.ProfilerFiller;

@Mixin(SplashManager.class)
public class SplashTextResourceSupplierMixin {
    @Shadow @Mutable
    private List<String> splashes;

    @Inject(method = "apply(Ljava/util/List;Lnet/minecraft/server/packs/resources/ResourceManager;Lnet/minecraft/util/profiling/ProfilerFiller;)V", at = @At("TAIL"))
    void apply(List<String> list, ResourceManager resourceManager, ProfilerFiller profiler, CallbackInfo ci) {
        List<String> mutable = new java.util.ArrayList<>(this.splashes);
        mutable.add("CraftPlanet!");
        this.splashes = mutable;
    }
}
