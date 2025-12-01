package com.blonicx.craftplanet.utils;

import net.fabricmc.loader.api.FabricLoader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class HarmfulWordFilterUtil {
    private static final Path FILE = FabricLoader.getInstance()
            .getConfigDir()
            .resolve("craftplanet")
            .resolve("harmful_words.txt");

    private static Set<String> harmfulWords = new HashSet<>();

    public static void load() {
        try {
            Files.createDirectories(FILE.getParent());

            if (!Files.exists(FILE)) {
                Files.write(FILE, List.of(
                        "Delete this Line and add Custom Words:"
                ));
            }

            harmfulWords = Files.readAllLines(FILE).stream()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());

            System.out.println("[CraftPlanet] Loaded " + harmfulWords.size() + " harmful words.");

        } catch (Exception e) {
            System.err.println("[CraftPlanet] Failed to load harmful_words.txt");
            e.printStackTrace();
        }
    }

    public static boolean containsHarmfulWord(String text) {
        String normalized = text.toLowerCase();
        for (String word : harmfulWords) {
            if (normalized.contains(word))
                return true;
        }
        return false;
    }
}
