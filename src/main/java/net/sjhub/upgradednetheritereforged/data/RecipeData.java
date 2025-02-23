package net.sjhub.upgradednetheritereforged.data;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.sjhub.upgradednetheritereforged.init.ModItems;
import net.sjhub.upgradednetheritereforged.utils.ItemUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import static net.sjhub.upgradednetheritereforged.UpgradedNetheriteMod.MOD_ID;

public class RecipeData extends RecipeProvider {

    public RecipeData(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        // default netherite upgrade
        netheriteCustomSmithing(consumer, Items.BOW, ModItems.NETHERITE_BOW.get(), Items.NETHERITE_INGOT, Items.DRAGON_BREATH, RecipeCategory.COMBAT);
        netheriteCustomSmithing(consumer, Items.CROSSBOW, ModItems.NETHERITE_CROSSBOW.get(), Items.NETHERITE_INGOT, Items.DRAGON_BREATH, RecipeCategory.COMBAT);
        netheriteCustomSmithing(consumer, Items.SHIELD, ModItems.NETHERITE_SHIELD.get(), Items.NETHERITE_INGOT, Items.DRAGON_BREATH, RecipeCategory.COMBAT);
        netheriteCustomSmithing(consumer, Items.DIAMOND_HORSE_ARMOR, ModItems.NETHERITE_HORSE_ARMOR.get(), Items.NETHERITE_INGOT, Items.DRAGON_BREATH, RecipeCategory.COMBAT);

        // netherite transcend(dynamic)
        String[] types = {"GOLD", "FIRE", "ENDER", "WATER", "WITHER", "POISON", "PHANTOM", "FEATHER", "CORRUPT", "ECHO"};
        Map<String, RecipeCategory> equipments = new HashMap<>();
        equipments.put("SWORD", RecipeCategory.COMBAT);
        equipments.put("SHIELD", RecipeCategory.COMBAT);
        equipments.put("BOW", RecipeCategory.COMBAT);
        equipments.put("CROSSBOW", RecipeCategory.COMBAT);
        equipments.put("SHOVEL", RecipeCategory.TOOLS);
        equipments.put("PICKAXE", RecipeCategory.TOOLS);
        equipments.put("AXE", RecipeCategory.TOOLS);
        equipments.put("HOE", RecipeCategory.TOOLS);
        equipments.put("HELMET", RecipeCategory.COMBAT);
        equipments.put("CHESTPLATE", RecipeCategory.COMBAT);
        equipments.put("LEGGINGS", RecipeCategory.COMBAT);
        equipments.put("BOOTS", RecipeCategory.COMBAT);
        equipments.put("HORSE_ARMOR", RecipeCategory.COMBAT);

        for (String type : types) {
            for (String equipment : equipments.keySet()) {
                String inputString = "NETHERITE_" + equipment;
                String outputString = type + "_UPGRADED_NETHERITE_" + equipment;
                String ingredientString = type + "_UPGRADED_NETHERITE_INGOT";
                RecipeCategory recipeCategory = equipments.get(equipment);

                Item input = ItemUtil.valueOf(MOD_ID, inputString);
                Item output = ItemUtil.valueOf(MOD_ID, outputString);
                Item ingredient = ItemUtil.valueOf(MOD_ID, ingredientString);

                if (input != null && output != null && ingredient != null) {
                    if (input != Items.AIR && output != Items.AIR && ingredient != Items.AIR) {
                        netheriteTranscend(consumer, input, output, ingredient, recipeCategory);
                    }
                }
            }
        }
    }

    public static void netheriteTranscend(Consumer<FinishedRecipe> consumer, Item input, Item output, Item ingredient, RecipeCategory recipeCategory) {
        netheriteCustomSmithing(consumer, input, output, ingredient, ModItems.NETHERITE_TRANSCEND_SMITHING_TEMPLATE.get(), recipeCategory);
    }

    public static void netheriteCustomSmithing(Consumer<FinishedRecipe> consumer, Item input, Item output, Item ingredient, Item template, RecipeCategory recipeCategory) {
        SmithingTransformRecipeBuilder.smithing(
                Ingredient.of(template),
                Ingredient.of(input),
                Ingredient.of(ingredient),
                recipeCategory,
                output
        ).unlocks(
                "has_netherite_ingot",
                has((ItemLike) ingredient)
        ).save(
                consumer,
                getItemName(output) + "_" + "smithing"
        );
    }
}
