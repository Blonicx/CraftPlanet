package com.blonicx.craftplanet.rendering;

import net.minecraft.core.ClientAsset;

//? if >=1.21.11 {
/*import net.minecraft.resources.Identifier;
 *///?} else {
import net.minecraft.resources.ResourceLocation;
//?}
/*
public class Cape implements ClientAsset.Texture {
    Identifier id;

    public Cape(Identifier identifier) {
        id = identifier;
    }

    @Override
    public Identifier texturePath() {
        return id;
    }

    @Override
    public Identifier id() {
        return id;
    }
}
*///?} else {
public class Cape implements ClientAsset.Texture {
    ResourceLocation id;

    public Cape(ResourceLocation identifier) {
        id = identifier;
    }

    @Override
    public ResourceLocation texturePath() {
        return id;
    }

    @Override
    public ResourceLocation id() {
        return id;
    }
}
//?}
