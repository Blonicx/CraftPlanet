package com.blonicx.craftplanet.rendering;

import com.blonicx.craftplanet.CraftPlanet;
import com.blonicx.craftplanet.integration.CPlanetConfig;
import com.blonicx.craftplanet.resources.Cache;
import com.mojang.blaze3d.platform.NativeImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.Identifier;

public class TextureLoader {
    public static Identifier CAPE_TEXTURE;

    public static Identifier loadTextureFromFile(File file, String textureName) {
        if (!file.exists()) {
            CPlanetConfig.INSTANCE.instance().cape_name = "";
            return null;
        }

        try (FileInputStream stream = new FileInputStream(file)) {
            NativeImage image = NativeImage.read(stream);
            DynamicTexture texture = new DynamicTexture(() -> CraftPlanet.MOD_ID + "/cache/" + textureName, image);

            Identifier id = Identifier.fromNamespaceAndPath(CraftPlanet.MOD_ID, "cache/" + textureName);

            TextureManager textureManager = Minecraft.getInstance().getTextureManager();
            textureManager.register(id, texture);

            storeFile(file);

            return id;
        } catch (IOException e) {
            throw new RuntimeException("Failed to load image: " + file, e);
        }
    }

    static void storeFile(File file) {
        try {
            Path original = file.toPath();
            File destFile = new File(Cache.CACHE_DIR + "/" + original.getFileName());
            Path copied = destFile.toPath();

            CraftPlanet.LOGGER.info("Saved to: {}", copied);

            Files.createDirectories(copied.getParent());

            Files.copy(original, copied, StandardCopyOption.REPLACE_EXISTING);

            CPlanetConfig.INSTANCE.instance().cape_name = destFile.getName();
            CPlanetConfig.INSTANCE.save();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
