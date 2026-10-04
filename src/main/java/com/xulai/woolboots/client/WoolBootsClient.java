package com.xulai.woolboots.client;

import com.xulai.woolboots.WoolBoots;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(value = WoolBoots.MODID, dist = Dist.CLIENT)
public final class WoolBootsClient {

    public WoolBootsClient(ModContainer container, IEventBus modBus) {
        NeoForge.EVENT_BUS.register(WoolSoleTooltips.class);
    }
}
