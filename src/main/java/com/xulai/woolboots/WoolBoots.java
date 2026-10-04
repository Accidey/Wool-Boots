package com.xulai.woolboots;

import com.mojang.logging.LogUtils;
import com.xulai.woolboots.events.WoolBootsEvents;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(WoolBoots.MODID)
public class WoolBoots {

    public static final String MODID = "woolboots";

    public static final Logger LOGGER = LogUtils.getLogger();

    public WoolBoots(IEventBus modEventBus, ModContainer modContainer) {
        WoolBootsComponents.DATA_COMPONENT_TYPES.register(modEventBus);
        WoolBootsRecipes.RECIPE_SERIALIZERS.register(modEventBus);
        WoolBootsRecipes.INGREDIENT_TYPES.register(modEventBus);

        NeoForge.EVENT_BUS.register(WoolBootsEvents.class);
    }
}
