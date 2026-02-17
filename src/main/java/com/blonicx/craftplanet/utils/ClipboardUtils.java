package com.blonicx.craftplanet.utils;

import com.mojang.blaze3d.platform.ClipboardManager;
import net.minecraft.client.Minecraft;

public class ClipboardUtils {
    public static void copyToClipboard(String string){
        Minecraft client = Minecraft.getInstance();

        ClipboardManager clipboard = new ClipboardManager();
        //? if >= 1.21.9 {
        clipboard.setClipboard(client.getWindow(), string);
        //?} else {
        /*clipboard.setClipboard(client.getWindow().getWindow(), string);
        *///?}
    }
}
