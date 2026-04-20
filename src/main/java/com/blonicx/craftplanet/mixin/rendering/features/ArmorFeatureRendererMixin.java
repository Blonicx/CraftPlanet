package com.blonicx.craftplanet.mixin.rendering.features;

import com.blonicx.craftplanet.integration.CPlanetConfig;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//? if >= 1.21.6 {
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
//?}

//? if >= 1.21.9 {
import net.minecraft.client.renderer.SubmitNodeCollector;
 //?} else {
/*import net.minecraft.client.renderer.MultiBufferSource;
*///?}

@Mixin(HumanoidArmorLayer.class)
//? if >= 1.21.6 {
public class ArmorFeatureRendererMixin<S extends HumanoidRenderState, M extends HumanoidModel<S>, A extends HumanoidModel<S>> {
//?} else {
/*public class ArmorFeatureRendererMixin<T extends LivingEntity, M extends HumanoidModel<T>, A extends HumanoidModel<T>> {
*///?}
    @Inject(method = "renderArmorPiece", at = @At("HEAD"), cancellable = true)
    //? if >= 1.21.9 {
    void render(PoseStack matrices, SubmitNodeCollector queue, ItemStack stack, EquipmentSlot slot, int light, S state, CallbackInfo ci) {
     //?} else if >= 1.21.6 {
    /*void render(PoseStack poseStack, MultiBufferSource multiBufferSource, ItemStack itemStack, EquipmentSlot equipmentSlot, int i, HumanoidModel humanoidModel, CallbackInfo ci) {
    *///?} else {
    /*void render(PoseStack poseStack, MultiBufferSource multiBufferSource, LivingEntity livingEntity, EquipmentSlot equipmentSlot, int i, HumanoidModel humanoidModel, CallbackInfo ci) {
    *///?}
        if (CPlanetConfig.INSTANCE.instance().disableArmorRendering) ci.cancel();
    }
}
