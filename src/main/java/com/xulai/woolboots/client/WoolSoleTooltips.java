package com.xulai.woolboots.client;

import com.xulai.woolboots.WoolBootsComponents;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

public final class WoolSoleTooltips {

    private WoolSoleTooltips() {
    }

    @SubscribeEvent
    public static void onItemTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        if (!WoolBootsComponents.hasWoolSole(stack)) {
            return;
        }

        List<Component> lines = event.getToolTip();
        lines.add(Component.empty());
        lines.add(Component.translatable("woolboots.tooltip.sole").withStyle(ChatFormatting.GOLD, ChatFormatting.BOLD));
        lines.add(Component.translatable("woolboots.tooltip.silent").withStyle(ChatFormatting.GRAY));
        lines.add(Component.translatable("woolboots.tooltip.detect").withStyle(ChatFormatting.DARK_GRAY));
    }
}
