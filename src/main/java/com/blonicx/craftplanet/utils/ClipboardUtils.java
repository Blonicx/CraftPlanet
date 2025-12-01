package com.blonicx.craftplanet.utils;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Clipboard;

public class ClipboardUtils {
    public static void copyToClipboard(String string){
        MinecraftClient client = MinecraftClient.getInstance();

        Clipboard clipboard = new Clipboard();
        clipboard.set(client.getWindow(), string);
    }
}
