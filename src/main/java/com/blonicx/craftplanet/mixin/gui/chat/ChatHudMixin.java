package com.blonicx.craftplanet.mixin.gui.chat;

import com.blonicx.craftplanet.integration.config.ConfigManager;
import com.blonicx.craftplanet.utils.HarmfulWordFilterUtil;
import net.minecraft.client.gui.hud.ChatHud;
import net.minecraft.client.gui.hud.MessageIndicator;
import net.minecraft.network.message.MessageSignatureData;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
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
        if (!ConfigManager.config.filterChat) return;

        String plain = message.getString();

        if (HarmfulWordFilterUtil.containsHarmfulWord(plain)) {
            this.addMessage(Text.translatable("info.craftplanet.harmful_chat_msg").formatted(Formatting.RED));
            ci.cancel();
        }
    }
}
