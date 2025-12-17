package com.blonicx.craftplanet.resources;

import com.blonicx.craftplanet.CraftPlanet;
import com.blonicx.craftplanet.integration.config.ConfigManager;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.NativeImage;
import net.minecraft.client.texture.NativeImageBackedTexture;
import net.minecraft.client.texture.TextureManager;
import net.minecraft.util.Identifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class TextureLoader {
    public static Identifier CAPE_TEXTURE;

    public static Identifier loadTextureFromFile(File file, String textureName) {
        if (!file.exists()) {
            ConfigManager.config.cape_name = "";
            return null;
        }

        try (FileInputStream stream = new FileInputStream(file)) {
            NativeImage image = NativeImage.read(stream);
            NativeImageBackedTexture texture = new NativeImageBackedTexture(() -> CraftPlanet.MOD_ID + "/cache/" + textureName, image);

            Identifier id = Identifier.of(CraftPlanet.MOD_ID, "cache/" + textureName);

            TextureManager textureManager = MinecraftClient.getInstance().getTextureManager();
            textureManager.registerTexture(id, texture);

            storeFile(file);

            return id;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image: " + file, e);
        }
    }

    static void storeFile(File file) {
        try {
            Path original = file.toPath();
            File destFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), "craftplanet/cache/" + original.getFileName());
            Path copied = destFile.toPath();

            Files.createDirectories(copied.getParent());

            Files.copy(original, copied, StandardCopyOption.REPLACE_EXISTING);

            ConfigManager.config.cape_name = destFile.getName();
            ConfigManager.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
