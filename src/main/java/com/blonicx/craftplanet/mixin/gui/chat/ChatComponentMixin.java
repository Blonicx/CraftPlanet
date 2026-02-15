package com.blonicx.craftplanet.mixin.gui.chat;

import com.blonicx.craftplanet.CraftPlanet;
import com.blonicx.craftplanet.integration.CPlanetConfig;
import net.minecraft.client.GuiMessage;
import net.minecraft.client.gui.components.ChatComponent;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ChatComponent.class)
public class ChatComponentMixin {

    @Shadow
    @Final
    private List<GuiMessage> allMessages;

    @Inject(method = "addMessageToQueue", at = @At("HEAD"), cancellable = true)
    private void addMessageToQueue(GuiMessage guiMessage, CallbackInfo ci) {
        CraftPlanet.LOGGER.info("Amount of messages: {}", this.allMessages.size());

        this.allMessages.add(guiMessage);

        while (this.allMessages.size() > CPlanetConfig.INSTANCE.instance().maxChatMessages) {
            this.allMessages.removeLast();
        }

        ci.cancel();
    }
}
