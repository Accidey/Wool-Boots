package com.xulai.woolboots.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.xulai.woolboots.WoolBootsComponents;
import com.xulai.woolboots.WoolBootsRecipes;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import net.minecraft.world.item.crafting.ShapedRecipePattern;

public class WoolSoleRecipe extends ShapedRecipe {

    private final ItemStack displayResult;

    public WoolSoleRecipe(String group, CraftingBookCategory category, ShapedRecipePattern pattern, ItemStack displayResult, boolean showNotification) {
        super(group, category, pattern, displayResult, showNotification);
        this.displayResult = displayResult;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return WoolBootsRecipes.WOOL_SOLE.get();
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return this.displayResult;
    }

    @Override
    public ItemStack assemble(CraftingInput input, HolderLookup.Provider registries) {
        for (int i = 0; i < input.size(); i++) {
            ItemStack stack = input.getItem(i);
            if (UnsoledBootsIngredient.isUnsoledBoots(stack)) {
                ItemStack result = stack.copyWithCount(1);
                WoolBootsComponents.setWoolSole(result);
                return result;
            }
        }
        return ItemStack.EMPTY;
    }

    public static class Serializer implements RecipeSerializer<WoolSoleRecipe> {

        public static final MapCodec<WoolSoleRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                        Codec.STRING.optionalFieldOf("group", "").forGetter(WoolSoleRecipe::getGroup),
                        CraftingBookCategory.CODEC.fieldOf("category").orElse(CraftingBookCategory.MISC).forGetter(WoolSoleRecipe::category),
                        ShapedRecipePattern.MAP_CODEC.forGetter(recipe -> recipe.pattern),
                        ItemStack.STRICT_CODEC.fieldOf("result").forGetter(recipe -> recipe.displayResult),
                        Codec.BOOL.optionalFieldOf("show_notification", true).forGetter(WoolSoleRecipe::showNotification))
                .apply(instance, WoolSoleRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, WoolSoleRecipe> STREAM_CODEC = StreamCodec.of(Serializer::toNetwork, Serializer::fromNetwork);

        @Override
        public MapCodec<WoolSoleRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, WoolSoleRecipe> streamCodec() {
            return STREAM_CODEC;
        }

        private static WoolSoleRecipe fromNetwork(RegistryFriendlyByteBuf buffer) {
            return new WoolSoleRecipe(
                    buffer.readUtf(),
                    buffer.readEnum(CraftingBookCategory.class),
                    ShapedRecipePattern.STREAM_CODEC.decode(buffer),
                    ItemStack.STREAM_CODEC.decode(buffer),
                    buffer.readBoolean());
        }

        private static void toNetwork(RegistryFriendlyByteBuf buffer, WoolSoleRecipe recipe) {
            buffer.writeUtf(recipe.getGroup());
            buffer.writeEnum(recipe.category());
            ShapedRecipePattern.STREAM_CODEC.encode(buffer, recipe.pattern);
            ItemStack.STREAM_CODEC.encode(buffer, recipe.displayResult);
            buffer.writeBoolean(recipe.showNotification());
        }
    }
}
