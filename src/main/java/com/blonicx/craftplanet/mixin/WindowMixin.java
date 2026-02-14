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
    @Unique int FPS = Minecraft.getInstance().options.framerateLimit().get();

    @Inject(method = "onFocus", at = @At("HEAD"))
    void onWindowFocusChanged(long window, boolean focused, CallbackInfo ci) {
        if (!focused) FPS = Minecraft.getInstance().options.framerateLimit().get();

        Minecraft.getInstance().options.framerateLimit().set(focused ? FPS : 15);
    }
}
