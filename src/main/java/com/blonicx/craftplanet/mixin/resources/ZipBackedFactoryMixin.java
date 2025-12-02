package com.blonicx.craftplanet.mixin.resources;

import com.blonicx.craftplanet.CraftPlanet;
import com.blonicx.craftplanet.resources.FastZipResourcePack;
import net.minecraft.resource.*;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipFile;

// Credits: https://github.com/DrexHD/quick-pack/tree/main

@Mixin(ZipResourcePack.ZipBackedFactory.class)
public class ZipBackedFactoryMixin {
    @Shadow
    @Final
    private File file;

    /**
     * @author Blonicx / drex
     * @reason Use optimized FastZipResourcePack
     */
    @Overwrite
    public ResourcePack openWithOverlays(ResourcePackInfo info, ResourcePackProfile.Metadata metadata) {
        ZipFile zipFile = null;

        try {
            zipFile = new ZipFile(this.file);
        } catch (IOException e) {
            CraftPlanet.LOGGER.error("Failed to open pack {}", this.file, e);
        }

        List<String> overlays = metadata.overlays();
        return new FastZipResourcePack(info, zipFile, overlays);
    }
}
