package com.blonicx.craftplanet.event;

import com.blonicx.craftplanet.CraftPlanet;
import com.blonicx.craftplanet.utils.ClipboardUtils;
import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.lwjgl.glfw.GLFW;

public class KeyInputEvents {
    private static final KeyMapping.Category CATEGORY = KeyMapping.Category.register(Identifier.fromNamespaceAndPath(CraftPlanet.MOD_ID, "keybinds_category"));
    private static final String KEY_COPY_COORDS = "key.craftplanet.copy_coords";

    // Keybinds
    private static KeyMapping copyCoordsKey;

    static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (copyCoordsKey.consumeClick()) {
                assert client.player != null;
                String playerPos = String.format("%d-x %d-y %d-z", (int)client.player.getX(), (int)client.player.getY(), (int)client.player.getZ());

                ClipboardUtils.copyToClipboard(playerPos);
                client.player.displayClientMessage(Component.translatable("message.craftplanet.copied_coords"), true);
            }
        });
    }

    public static void register(){
        copyCoordsKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                KEY_COPY_COORDS,
                InputConstants.Type.KEYSYM,
                GLFW.GLFW_KEY_B,
                CATEGORY
        ));

        registerKeyInputs();

        CraftPlanet.LOGGER.info("Registered KeyEvents for {}", CraftPlanet.MOD_ID);
    }
}
