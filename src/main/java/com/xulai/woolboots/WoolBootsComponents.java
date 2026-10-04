package com.xulai.woolboots;

import java.util.function.Supplier;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.Unit;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class WoolBootsComponents {

    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.create(Registries.DATA_COMPONENT_TYPE, WoolBoots.MODID);

    public static final Supplier<DataComponentType<Unit>> WOOL_SOLE = DATA_COMPONENT_TYPES.register(
            "wool_sole",
            () -> DataComponentType.<Unit>builder()
                    .persistent(Unit.CODEC)
                    .networkSynchronized(StreamCodec.unit(Unit.INSTANCE))
                    .build());

    private WoolBootsComponents() {
    }

    public static boolean hasWoolSole(ItemStack stack) {
        return stack.has(WOOL_SOLE.get());
    }

    public static void setWoolSole(ItemStack stack) {
        stack.set(WOOL_SOLE.get(), Unit.INSTANCE);
    }
}
