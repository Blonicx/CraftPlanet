package com.blonicx.craftplanet.mixin.resources;

import com.blonicx.craftplanet.CraftPlanet;
import com.blonicx.craftplanet.resources.FastZipResourcePack;
import net.minecraft.server.packs.FilePackResources;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.repository.Pack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipFile;

// Credits: https://github.com/DrexHD/quick-pack/tree/main

@Mixin(FilePackResources.FileResourcesSupplier.class)
public class ZipBackedFactoryMixin {
    @Shadow @Final
    private File content;

    /**
     * @author Blonicx / drex
     * @reason Use optimized FastZipResourcePack
     */
    @Overwrite
    public PackResources openFull(PackLocationInfo info, Pack.Metadata metadata) {
        ZipFile zipFile = null;

        try {
            zipFile = new ZipFile(this.content);
        } catch (IOException e) {
            CraftPlanet.LOGGER.error("Failed to open pack {}", this.content, e);
        }

        List<String> overlays = metadata.overlays();
        return new FastZipResourcePack(info, zipFile, overlays);
    }
}
