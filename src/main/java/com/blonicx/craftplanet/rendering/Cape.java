package com.blonicx.craftplanet.rendering;

import net.minecraft.core.ClientAsset;
import net.minecraft.resources.Identifier;

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
