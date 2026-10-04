package com.xulai.woolboots.events;

import com.xulai.woolboots.WoolBootsComponents;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.VanillaGameEvent;

public final class WoolBootsEvents {

    private WoolBootsEvents() {
    }

    @SubscribeEvent
    public static void onVanillaGameEvent(VanillaGameEvent event) {
        Holder<GameEvent> gameEvent = event.getVanillaEvent();
        if (!gameEvent.is(GameEvent.STEP.key()) && !gameEvent.is(GameEvent.HIT_GROUND.key())) {
            return;
        }
        if (!(event.getCause() instanceof LivingEntity entity)) {
            return;
        }
        if (WoolBootsComponents.hasWoolSole(entity.getItemBySlot(EquipmentSlot.FEET))) {
            event.setCanceled(true);
        }
    }
}
