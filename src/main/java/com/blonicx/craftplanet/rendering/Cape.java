package com.blonicx.craftplanet.rendering;

import dev.blonicx.craftlib.api.identifier.GlobalIdentifier;
import net.minecraft.core.ClientAsset;

//? if >=1.21.11 {
import net.minecraft.resources.Identifier;
//?} else {
/*import net.minecraft.resources.ResourceLocation;
*///?}

public class Cape implements ClientAsset.Texture {
    GlobalIdentifier id;

    public Cape(GlobalIdentifier identifier) {
        id = identifier;
    }

    @Override
    //? if >=1.21.11 {
    public Identifier texturePath() {
    //?} else {
    /*public ResourceLocation texturePath() {
     *///?}
        return id.unwrap();
    }


    //? if >=1.21.11 {
    public Identifier id() {
    //?} else {
    /*public ResourceLocation id() {
    *///?}
        return id.unwrap();
    }
}