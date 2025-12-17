package com.blonicx.craftplanet.mixin.gui.screen;

import com.blonicx.craftplanet.resources.TextureLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.screen.option.SkinOptionsScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import org.lwjgl.PointerBuffer;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.util.tinyfd.TinyFileDialogs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Mixin(SkinOptionsScreen.class)
public abstract class SkinOptionsScreenMixin extends GameOptionsScreen {
    public SkinOptionsScreenMixin(Screen parent, GameOptions gameOptions, Text screenTitle) {
        super(parent, gameOptions, screenTitle);
    }

    @Inject(method = "addOptions", at = @At("TAIL"))
    private void addCustomButton(CallbackInfo ci) {
        List<ClickableWidget> list = new ArrayList<>();

        list.add(
                ButtonWidget.builder(Text.translatable("button.craftplanet.choose_cape"),button -> {
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
                        })
                        .width(310)
                        .build()
        );

        this.body.addAll(list);
    }
}