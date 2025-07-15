package net.sjhub.upgradednetheritereforged.data;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.sjhub.upgradednetheritereforged.UpgradedNetheriteMod;
import net.sjhub.upgradednetheritereforged.init.ModItems;
import net.sjhub.upgradednetheritereforged.modifiers.GlobalLootModifiers;
import net.sjhub.upgradednetheritereforged.modifiers.LootTableModifier;

public class LootTableData extends GlobalLootModifierProvider {

    public LootTableData(PackOutput output) {
        this(output, UpgradedNetheriteMod.MOD_ID);
    }

    public LootTableData(PackOutput output, String modid) {
        super(output, modid);
    }

    @Override
    protected void start() {
        add("netherite_transcend_from_bastion_bridge", new LootTableModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_bridge")).build(),
                LootItemRandomChanceCondition.randomChance(0.05F).build()
        }, ModItems.NETHERITE_TRANSCEND_SMITHING_TEMPLATE.get()));

        add("netherite_transcend_from_bastion_hoglin_stable", new LootTableModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_hoglin_stable")).build(),
                LootItemRandomChanceCondition.randomChance(0.05F).build()
        }, ModItems.NETHERITE_TRANSCEND_SMITHING_TEMPLATE.get()));

        add("netherite_transcend_from_bastion_other", new LootTableModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_other")).build(),
                LootItemRandomChanceCondition.randomChance(0.05F).build()
        }, ModItems.NETHERITE_TRANSCEND_SMITHING_TEMPLATE.get()));

        add("netherite_transcend_from_bastion_treasure", new LootTableModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_treasure")).build(),
                LootItemRandomChanceCondition.randomChance(0.05F).build()
        }, ModItems.NETHERITE_TRANSCEND_SMITHING_TEMPLATE.get()));

        add("upgraded_hoe_tool", new GlobalLootModifiers.UpgradedHoeModifier(new LootItemCondition[]{
        }));

        add("auto_smelt_tool", new GlobalLootModifiers.AutoSmeltModifier(new LootItemCondition[]{
        }));

        add("ender_teleport_tool", new GlobalLootModifiers.EnderTeleportModifier(new LootItemCondition[]{
        }));
    }
}
