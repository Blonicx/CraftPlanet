package com.blonicx.craftplanet.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Window.class)
public class WindowMixin {
    @Unique int FPS = MinecraftClient.getInstance().options.getMaxFps().getValue();

    @Inject(method = "onWindowFocusChanged", at = @At("HEAD"))
    void onWindowFocusChanged(long window, boolean focused, CallbackInfo ci) {
        if (!focused) FPS = MinecraftClient.getInstance().options.getMaxFps().getValue();

        MinecraftClient.getInstance().options.getMaxFps().setValue(focused ? FPS : 15);
    }
}
