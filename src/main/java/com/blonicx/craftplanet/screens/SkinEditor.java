package com.blonicx.craftplanet.screens;

import com.blonicx.craftplanet.rendering.TextureLoader;
import com.blonicx.craftplanet.resources.Cache;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.util.tinyfd.TinyFileDialogs;

import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class SkinEditor extends Screen {
    public SkinEditor() {
        super(Component.translatable("screen.craftplanet.skin_editor"));
    }

    @Override
    protected void init() {
        int w = Minecraft.getInstance().getWindow().getScreenWidth();
        int h = Minecraft.getInstance().getWindow().getScreenHeight();

        Button capeBtn = Button.builder(Component.translatable("button.craftplanet.choose_cape"), button -> {
            try (MemoryStack stack = MemoryStack.stackPush()) {

                PointerBuffer filters = stack.mallocPointer(1);
                filters.put(stack.UTF8("*.png"));
                filters.flip();

                String path = TinyFileDialogs.tinyfd_openFileDialog(
                        "Select Cape PNG",
                        "",
                        filters,
                        "PNG Images",
                        false
                );

                if (path != null) {
                    TextureLoader.CAPE_TEXTURE = TextureLoader.loadTextureFromFile(new File(path), "current_cape");
                }
            }
        }).bounds(40, 40, 120, 20).build();

        Button clearCache = Button.builder(Component.translatable("button.craftplanet.clear_cache"), button -> {
            Cache.clearCache();
        }).bounds(40, 0, 120, 20).build();

        this.addRenderableWidget(capeBtn);
        this.addRenderableWidget(clearCache);
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
    }
}
