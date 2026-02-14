package com.blonicx.craftplanet.mixin.gui.chat;

import com.blonicx.craftplanet.integration.config.ConfigManager;
import com.blonicx.craftplanet.utils.HarmfulWordFilterUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.client.GuiMessageTag;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MessageSignature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChatComponent.class)
public abstract class ChatHudMixin {
    @Shadow
    public abstract void addMessage(Component message);

    @Inject(method = "addMessage(Lnet/minecraft/network/chat/Component;Lnet/minecraft/network/chat/MessageSignature;Lnet/minecraft/client/GuiMessageTag;)V", at = @At("HEAD"), cancellable = true)
    void addMessage(Component message, MessageSignature signatureData, GuiMessageTag indicator, CallbackInfo ci) {
        if (!ConfigManager.config.filterChat) return;

        String plain = message.getString();

        if (HarmfulWordFilterUtil.containsHarmfulWord(plain)) {
            this.addMessage(Component.translatable("info.craftplanet.harmful_chat_msg").withStyle(ChatFormatting.RED));
            ci.cancel();
        }
    }
}
