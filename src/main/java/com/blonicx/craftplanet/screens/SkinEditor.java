package com.blonicx.craftplanet.screens;

import com.blonicx.craftplanet.resources.TextureLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.util.tinyfd.TinyFileDialogs;

import java.io.File;

public class SkinEditor extends Screen {
    public SkinEditor() {
        super(Text.translatable("screen.craftplanet.skin_editor"));
    }

    @Override
    protected void init() {
        int w = MinecraftClient.getInstance().getWindow().getScaledWidth();
        int h = MinecraftClient.getInstance().getWindow().getScaledHeight();

        ButtonWidget btn = ButtonWidget.builder(Text.translatable("button.craftplanet.choose_cape"),button -> {
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
        }).dimensions(40, 40, 120, 20).build();

        this.addDrawableChild(btn);
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
    }
}
