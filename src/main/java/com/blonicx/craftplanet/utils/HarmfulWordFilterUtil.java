package com.blonicx.craftplanet.utils;

import com.blonicx.craftplanet.CraftPlanet;
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
                        "Add Custom Words:"
                ));
            }

            harmfulWords = Files.readAllLines(FILE).stream()
                    .map(String::trim)
                    .filter(s -> !s.isEmpty())
                    .map(String::toLowerCase)
                    .collect(Collectors.toSet());

            int length = harmfulWords.size() - 1;

            CraftPlanet.LOGGER.info(CraftPlanet.MOD_ID + " Loaded {} harmful words.", length);

        } catch (Exception e) {
            System.err.println(CraftPlanet.MOD_ID + " Failed to load harmful_words.txt");
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
