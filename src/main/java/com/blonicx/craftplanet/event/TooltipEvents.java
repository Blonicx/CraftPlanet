package com.blonicx.craftplanet.event;

import com.blonicx.craftplanet.CraftPlanet;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class TooltipEvents {
    static void registerTooltips(){
        ItemTooltipCallback.EVENT.register(((itemStack, tooltipContext, tooltipType, list) -> {
            if (itemStack.isDamaged()) {
                int maxDurability = itemStack.getMaxDamage();
                int durability = maxDurability - itemStack.getDamage();

                float percent = Math.round(((float) durability / maxDurability) * 100);

                String end = " " + durability + " [" + percent + "%] / " + maxDurability + " ";
                list.add(Text.literal(end).append(Text.translatable("info.craftplanet.durability")).formatted(Formatting.DARK_GREEN));
            }
        }));
    }

    public static void register(){
        registerTooltips();

        CraftPlanet.LOGGER.info("Registered KeyEvents for {}", CraftPlanet.MOD_ID);
    }
}
