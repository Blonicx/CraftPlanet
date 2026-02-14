package com.blonicx.craftplanet.event;

import com.blonicx.craftplanet.CraftPlanet;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public class TooltipEvents {
    static void registerTooltips(){
        ItemTooltipCallback.EVENT.register(((itemStack, tooltipContext, tooltipType, list) -> {
            if (itemStack.isDamaged()) {
                int maxDurability = itemStack.getMaxDamage();
                int durability = maxDurability - itemStack.getDamageValue();

                float percent = Math.round(((float) durability / maxDurability) * 100);

                String end = " " + durability + " [" + percent + "%] / " + maxDurability + " ";
                list.add(Component.literal(end).append(Component.translatable("info.craftplanet.durability")).withStyle(ChatFormatting.DARK_GREEN));
            }
        }));
    }

    public static void register(){
        registerTooltips();

        CraftPlanet.LOGGER.info("Registered TooltipEvents for {}", CraftPlanet.MOD_ID);
    }
}
