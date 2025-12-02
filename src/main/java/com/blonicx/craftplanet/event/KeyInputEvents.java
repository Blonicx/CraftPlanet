package com.blonicx.craftplanet.event;

import com.blonicx.craftplanet.CraftPlanet;
import com.blonicx.craftplanet.utils.ClipboardUtils;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class KeyInputEvents {
    private static final KeyBinding.Category CATEGORY = KeyBinding.Category.create(Identifier.of(CraftPlanet.MOD_ID, "keybinds_category"));
    private static final String KEY_COPY_COORDS = "key.craftplanet.copy_coords";

    // Keybinds
    private static KeyBinding copyCoordsKey;

    static void registerKeyInputs(){
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (copyCoordsKey.wasPressed()) {
                assert client.player != null;
                String playerPos = String.format("%d-x %d-y %d-z", (int)client.player.getX(), (int)client.player.getY(), (int)client.player.getZ());

                ClipboardUtils.copyToClipboard(playerPos);
                client.player.sendMessage(Text.translatable("message.craftplanet.copied_coords"), true);
            }
        });
    }

    public static void register(){
        copyCoordsKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_COPY_COORDS,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_B,
                CATEGORY
        ));

        registerKeyInputs();

        CraftPlanet.LOGGER.info("Registered KeyEvents for {}", CraftPlanet.MOD_ID);
    }
}
