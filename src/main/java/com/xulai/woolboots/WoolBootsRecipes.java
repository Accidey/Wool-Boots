package com.xulai.woolboots;

import com.xulai.woolboots.recipe.UnsoledBootsIngredient;
import com.xulai.woolboots.recipe.WoolSoleRecipe;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.neoforged.neoforge.common.crafting.IngredientType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public final class WoolBootsRecipes {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, WoolBoots.MODID);

    public static final DeferredRegister<IngredientType<?>> INGREDIENT_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.INGREDIENT_TYPES, WoolBoots.MODID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<WoolSoleRecipe>> WOOL_SOLE = RECIPE_SERIALIZERS.register("wool_sole", WoolSoleRecipe.Serializer::new);

    public static final DeferredHolder<IngredientType<?>, IngredientType<UnsoledBootsIngredient>> UNSOLED_BOOTS = INGREDIENT_TYPES.register(
            "unsoled_boots",
            () -> new IngredientType<>(UnsoledBootsIngredient.CODEC, StreamCodec.unit(UnsoledBootsIngredient.INSTANCE)));

    private WoolBootsRecipes() {
    }
}
