package com.blonicx.craftplanet.resources;

import com.blonicx.craftplanet.CraftPlanet;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class TextureLoader {
    public static Identifier loadTextureFromFile(File file, String textureName) {
        try (FileInputStream stream = new FileInputStream(file)) {
            NativeImage image = NativeImage.read(stream);
            NativeImageBackedTexture texture = new NativeImageBackedTexture(() -> CraftPlanet.MOD_ID + "/cache/" + textureName, image);

            Identifier id = Identifier.of(CraftPlanet.MOD_ID, "cache/" + textureName);

            TextureManager textureManager = MinecraftClient.getInstance().getTextureManager();
            textureManager.registerTexture(id, texture);

            return id;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image: " + file, e);
        }
    }
}
