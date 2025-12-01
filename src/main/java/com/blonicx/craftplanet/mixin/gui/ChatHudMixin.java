package com.blonicx.craftplanet.mixin.gui;

import com.blonicx.craftplanet.integration.config.ConfigManager;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatHud.class)
public abstract class ChatHudMixin {
    @Shadow
    public abstract void addMessage(Text message);

    @Inject(method = "addMessage(Lnet/minecraft/text/Text;Lnet/minecraft/network/message/MessageSignatureData;Lnet/minecraft/client/gui/hud/MessageIndicator;)V", at = @At("HEAD"), cancellable = true)
    void addMessage(Text message, MessageSignatureData signatureData, MessageIndicator indicator, CallbackInfo ci) {
        //TODO: Add file parsing for multiple harmful words.
        if (message.contains(Text.literal("")) && ConfigManager.config.filterChat) {
            this.addMessage(Text.translatable("info.craftplanet.harmful_chat_msg"));
            ci.cancel();
        }
    }
}
