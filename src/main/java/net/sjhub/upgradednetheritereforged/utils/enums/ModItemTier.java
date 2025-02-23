package net.sjhub.upgradednetheritereforged.utils.enums;

import java.util.function.Supplier;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public enum ModItemTier implements Tier {
   GOLD_UPGRADED_NETHERITE(4, 2031, 9.0F, 3.0F, 15, () -> {
      return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
   }),
   FIRE_UPGRADED_NETHERITE(4, 2031, 9.0F, 3.0F, 15, () -> {
      return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
   }),
   ENDER_UPGRADED_NETHERITE(4, 2031, 9.0F, 3.0F, 15, () -> {
      return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
   }),
   WATER_UPGRADED_NETHERITE(4, 2031, 9.0F, 3.0F, 15, () -> {
      return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
   }),
   WITHER_UPGRADED_NETHERITE(4, 2031, 9.0F, 3.0F, 15, () -> {
      return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
   }),
   POISON_UPGRADED_NETHERITE(4, 2031, 9.0F, 3.0F, 15, () -> {
      return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
   }),
   PHANTOM_UPGRADED_NETHERITE(4, 2031, 9.0F, 3.0F, 15, () -> {
      return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
   }),
   FEATHER_UPGRADED_NETHERITE(4, 2031, 9.0F, 3.0F, 15, () -> {
      return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
   }),
   CORRUPT_UPGRADED_NETHERITE(4, 2031, 9.0F, 3.0F, 15, () -> {
      return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
   }),
   ECHO_UPGRADED_NETHERITE(4, 2031, 9.0F, 3.0F, 15, () -> {
      return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
   });

   private final int harvestLevel;
   private final int maxUses;
   private final float efficiency;
   private final float attackDamage;
   private final int enchantability;
   private final Supplier<Ingredient> repairMaterial;

   private ModItemTier(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
      this.harvestLevel = harvestLevel;
      this.maxUses = maxUses;
      this.efficiency = efficiency;
      this.attackDamage = attackDamage;
      this.enchantability = enchantability;
      this.repairMaterial = repairMaterial;
   }

   public int getUses() {
      return this.maxUses;
   }

   public float getSpeed() {
      return this.efficiency;
   }

   public float getAttackDamageBonus() {
      return this.attackDamage;
   }

   public int getLevel() {
      return this.harvestLevel;
   }

   public int getEnchantmentValue() {
      return this.enchantability;
   }

   public Ingredient getRepairIngredient() {
      return (Ingredient)this.repairMaterial.get();
   }

   // $FF: synthetic method
   private static ModItemTier[] $values() {
      return new ModItemTier[]{GOLD_UPGRADED_NETHERITE, FIRE_UPGRADED_NETHERITE, ENDER_UPGRADED_NETHERITE, WATER_UPGRADED_NETHERITE, WITHER_UPGRADED_NETHERITE, POISON_UPGRADED_NETHERITE, PHANTOM_UPGRADED_NETHERITE, FEATHER_UPGRADED_NETHERITE, CORRUPT_UPGRADED_NETHERITE, ECHO_UPGRADED_NETHERITE};
   }
}
