package com.blonicx.craftplanet.rendering.features;

import net.minecraft.client.render.entity.feature.EnergySwirlOverlayFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.util.Identifier;

public class EffectRenderingFeature<S extends BipedEntityRenderState, M extends BipedEntityModel<S>> extends EnergySwirlOverlayFeatureRenderer<S, M> {

    private static final Identifier SKIN = Identifier.ofVanilla("textures/entity/creeper/creeper_armor.png");
    private final M model;

    public EffectRenderingFeature(FeatureRendererContext<S, M> context, M model) {
        super(context);
        this.model = model;
    }

    @Override
    protected boolean shouldRender(S state) {
        return true;
    }

    @Override
    protected float getEnergySwirlX(float partialAge) {
        return partialAge * 0.01F;
    }

    @Override
    protected Identifier getEnergySwirlTexture() {
        return SKIN;
    }

    @Override
    protected M getEnergySwirlModel() {
        return this.model;
    }
}
