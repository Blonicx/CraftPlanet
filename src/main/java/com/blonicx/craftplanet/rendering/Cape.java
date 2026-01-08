package com.blonicx.craftplanet.rendering;

import net.minecraft.util.AssetInfo;
import net.minecraft.util.Identifier;

public class Cape implements AssetInfo.TextureAsset {
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
