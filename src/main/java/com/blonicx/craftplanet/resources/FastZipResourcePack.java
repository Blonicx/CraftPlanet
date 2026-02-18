package com.blonicx.craftplanet.resources;

import com.blonicx.craftplanet.CraftPlanet;
import dev.blonicx.craftlib.api.identifier.GlobalIdentifier;
import dev.blonicx.craftlib.api.identifier.GlobalIdentifiers;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import net.minecraft.server.packs.AbstractPackResources;
import net.minecraft.server.packs.PackLocationInfo;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.resources.IoSupplier;

//? if >=1.21.11 {
import net.minecraft.resources.Identifier;
 //?} else {
/*import net.minecraft.resources.ResourceLocation;
*///?}

// Credits: https://github.com/DrexHD/quick-pack/tree/main

/**
 * @author Blonicx
 */
public class FastZipResourcePack extends AbstractPackResources {
    private final ZipFile zipFile;

    private final Map<String, byte[]> cache = new ConcurrentHashMap<>();

    private final Map<String, ZipEntry> entries = new HashMap<>();

    private final Map<String, Set<String>> namespaces = new HashMap<>();

    private final List<String> prefixStack;
    private final Set<String> overlays;

    /**
     * Constructor.
     */
    public FastZipResourcePack(PackLocationInfo info, ZipFile zipFile, List<String> overlays) {
        super(info);
        this.zipFile = zipFile;

        this.overlays = new HashSet<>(overlays);

        this.prefixStack = new ArrayList<>(overlays.size() + 1);
        for (int i = overlays.size() - 1; i >= 0; i--) {
            prefixStack.add(overlays.get(i) + "/");
        }
        prefixStack.add("");

        indexZip(); // <= does NOT extract any file data, only builds a directory index
    }

    /**
     * Build a directory index: collect ALL entry names + namespaces.
     */
    private void indexZip() {
        Enumeration<? extends ZipEntry> it = zipFile.entries();

        while (it.hasMoreElements()) {
            ZipEntry e = it.nextElement();
            if (e.isDirectory()) continue;

            String path = e.getName();
            entries.put(path, e);

            extractNamespace(path);
        }
    }

    /**
     * Extract namespaces exactly once, at load time.
     */
    private void extractNamespace(String path) {
        String[] parts = path.split("/");
        if (parts.length == 0) return;

        boolean isOverlay = overlays.contains(parts[0]);
        String type;
        String namespace;

        if (isOverlay && parts.length >= 3) {
            type = parts[1];
            namespace = parts[2];
        } else if (!isOverlay && parts.length >= 2) {
            type = parts[0];
            namespace = parts[1];
        } else return;

        //? if >= 1.21.11 {
        if (Identifier.isValidNamespace(namespace)) {
        //?} else {
        /*if (ResourceLocation.isValidNamespace(namespace)) {
         *///?}
            namespaces.computeIfAbsent(type, s -> new HashSet<>()).add(namespace);
        } else {
            CraftPlanet.LOGGER.warn("Invalid namespace {} in pack {}", namespace, zipFile);
        }
    }

    /**
     * Lazy-load file: first lookup loads, later lookups hit RAM cache.
     */
    private byte @Nullable [] loadFile(String path) {
        byte[] cached = cache.get(path);
        if (cached != null) return cached;

        ZipEntry entry = entries.get(path);
        if (entry == null) return null;

        try (InputStream in = zipFile.getInputStream(entry)) {
            byte[] data = in.readAllBytes();
            cache.put(path, data);
            return data;
        } catch (IOException e) {
            CraftPlanet.LOGGER.error("Failed to load {}", path, e);
            return null;
        }
    }

    @Override
    public @Nullable IoSupplier<InputStream> getRootResource(String... segments) {
        String path = String.join("/", segments);
        byte[] data = loadFile(path);
        return data == null ? null : () -> new ByteArrayInputStream(data);
    }

    //? if >= 1.21.11 {
    @Override
    public @Nullable IoSupplier<InputStream> getResource(PackType packType, Identifier id) {
        for (String prefix : prefixStack) {

            String full = prefix + packType.getDirectory() + "/" + id.getNamespace() + "/" + id.getPath();
            byte[] data = loadFile(full);
            if (data != null) {
                return () -> new ByteArrayInputStream(data);
            }
        }
        return null;
    }
     //?} else {
    /*@Override
    public @Nullable IoSupplier<InputStream> getResource(PackType packType, ResourceLocation id) {
        for (String prefix : prefixStack) {

            String full = prefix + packType.getDirectory() + "/" + id.getNamespace() + "/" + id.getPath();
            byte[] data = loadFile(full);
            if (data != null) {
                return () -> new ByteArrayInputStream(data);
            }
        }
        return null;
    }
    *///?}

    @Override
    public void listResources(PackType type, String namespace, String path, ResourceOutput out) {
        String base = type.getDirectory() + "/" + namespace + "/" + path + "/";

        for (String prefix : prefixStack) {
            String p = prefix + base;

            for (Map.Entry<String, ZipEntry> e : entries.entrySet()) {
                String filePath = e.getKey();

                if (filePath.startsWith(p)) {
                    String rlPath = filePath.substring((prefix + type.getDirectory() + "/" + namespace + "/").length());

                    GlobalIdentifier id = GlobalIdentifiers.of(namespace, rlPath);

                    if (id.tryBuild() == null) continue;

                    out.accept(id.tryBuild(), () -> new ByteArrayInputStream(Objects.requireNonNull(loadFile(filePath))));
                }
            }
        }
    }

    @Override
    public Set<String> getNamespaces(PackType type) {
        return namespaces.getOrDefault(type.getDirectory(), Collections.emptySet());
    }

    @Override
    public void close() {
        try {
            zipFile.close();
        } catch (IOException ignored) {}
    }
}