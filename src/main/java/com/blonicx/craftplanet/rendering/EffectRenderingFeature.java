package com.blonicx.craftplanet.rendering;

import net.minecraft.client.render.entity.feature.EnergySwirlOverlayFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.LoadedEntityModels;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.util.Identifier;

public class EffectRenderingFeature <S extends BipedEntityRenderState, M extends BipedEntityModel<S>, A extends BipedEntityModel<S>> extends EnergySwirlOverlayFeatureRenderer<S, M> {
    private final BipedEntityModel model;
    private static final Identifier SKIN = Identifier.ofVanilla("textures/entity/creeper/creeper_armor.png");

    public EffectRenderingFeature(FeatureRendererContext<S, M> context, LoadedEntityModels loader, BipedEntityModel model) {
        super(context);
        this.model = new BipedEntityModel(loader.getModelPart(EntityModelLayers.CREEPER_ARMOR));
    }

    @Override
    protected boolean shouldRender(S state) {
        return true;
    }

    protected float getEnergySwirlX(float partialAge) {
        return partialAge * 0.01F;
    }

    protected Identifier getEnergySwirlTexture() {
        return SKIN;
    }

    protected M getEnergySwirlModel() {
        return (M) this.model;
    }
}
