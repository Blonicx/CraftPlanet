package com.blonicx.craftplanet.mixin;

import com.mojang.blaze3d.platform.Window;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Window.class)
public class WindowMixin {
    @Unique int fps = -1;

    @Inject(method = "onFocus", at = @At("HEAD"))
    void onFocus(long window, boolean focused, CallbackInfo ci) {
        var mc = Minecraft.getInstance();

        if (fps == -1) fps = mc.options.framerateLimit().get();

        mc.options.framerateLimit().set(focused ? fps : 15);
    }
}
