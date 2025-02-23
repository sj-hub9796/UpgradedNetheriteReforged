package net.sjhub.upgradednetheritereforged.utils.enums;

import java.util.EnumMap;
import java.util.function.Supplier;
import net.minecraft.Util;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ArmorItem.Type;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

public enum ModArmorMaterial implements ArmorMaterial {
   GOLD_UPGRADED_NETHERITE("upgradednetherite_reforged:gold_upgraded_netherite", 37, (EnumMap)Util.make(new EnumMap(Type.class), (goldUpgradedNetherite) -> {
      goldUpgradedNetherite.put(Type.BOOTS, 3);
      goldUpgradedNetherite.put(Type.LEGGINGS, 6);
      goldUpgradedNetherite.put(Type.CHESTPLATE, 8);
      goldUpgradedNetherite.put(Type.HELMET, 3);
   }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, () -> {
      return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
   }, 0.1F),
   FIRE_UPGRADED_NETHERITE("upgradednetherite_reforged:fire_upgraded_netherite", 37, (EnumMap)Util.make(new EnumMap(Type.class), (fireUpgradedNetherite) -> {
      fireUpgradedNetherite.put(Type.BOOTS, 3);
      fireUpgradedNetherite.put(Type.LEGGINGS, 6);
      fireUpgradedNetherite.put(Type.CHESTPLATE, 8);
      fireUpgradedNetherite.put(Type.HELMET, 3);
   }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, () -> {
      return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
   }, 0.1F),
   ENDER_UPGRADED_NETHERITE("upgradednetherite_reforged:ender_upgraded_netherite", 37, (EnumMap)Util.make(new EnumMap(Type.class), (enderUpgradedNetherite) -> {
      enderUpgradedNetherite.put(Type.BOOTS, 3);
      enderUpgradedNetherite.put(Type.LEGGINGS, 6);
      enderUpgradedNetherite.put(Type.CHESTPLATE, 8);
      enderUpgradedNetherite.put(Type.HELMET, 3);
   }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, () -> {
      return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
   }, 0.1F),
   WATER_UPGRADED_NETHERITE("upgradednetherite_reforged:water_upgraded_netherite", 37, (EnumMap)Util.make(new EnumMap(Type.class), (waterUpgradedNetherite) -> {
      waterUpgradedNetherite.put(Type.BOOTS, 3);
      waterUpgradedNetherite.put(Type.LEGGINGS, 6);
      waterUpgradedNetherite.put(Type.CHESTPLATE, 8);
      waterUpgradedNetherite.put(Type.HELMET, 3);
   }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, () -> {
      return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
   }, 0.1F),
   WITHER_UPGRADED_NETHERITE("upgradednetherite_reforged:wither_upgraded_netherite", 37, (EnumMap)Util.make(new EnumMap(Type.class), (witherUpgradedNetherite) -> {
      witherUpgradedNetherite.put(Type.BOOTS, 3);
      witherUpgradedNetherite.put(Type.LEGGINGS, 6);
      witherUpgradedNetherite.put(Type.CHESTPLATE, 8);
      witherUpgradedNetherite.put(Type.HELMET, 3);
   }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, () -> {
      return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
   }, 0.1F),
   POISON_UPGRADED_NETHERITE("upgradednetherite_reforged:poison_upgraded_netherite", 37, (EnumMap)Util.make(new EnumMap(Type.class), (poisonUpgradedNetherite) -> {
      poisonUpgradedNetherite.put(Type.BOOTS, 3);
      poisonUpgradedNetherite.put(Type.LEGGINGS, 6);
      poisonUpgradedNetherite.put(Type.CHESTPLATE, 8);
      poisonUpgradedNetherite.put(Type.HELMET, 3);
   }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, () -> {
      return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
   }, 0.1F),
   PHANTOM_UPGRADED_NETHERITE("upgradednetherite_reforged:phantom_upgraded_netherite", 37, (EnumMap)Util.make(new EnumMap(Type.class), (phantomUpgradedNetherite) -> {
      phantomUpgradedNetherite.put(Type.BOOTS, 3);
      phantomUpgradedNetherite.put(Type.LEGGINGS, 6);
      phantomUpgradedNetherite.put(Type.CHESTPLATE, 8);
      phantomUpgradedNetherite.put(Type.HELMET, 3);
   }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, () -> {
      return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
   }, 0.1F),
   FEATHER_UPGRADED_NETHERITE("upgradednetherite_reforged:feather_upgraded_netherite", 37, (EnumMap)Util.make(new EnumMap(Type.class), (featherUpgradedNetherite) -> {
      featherUpgradedNetherite.put(Type.BOOTS, 3);
      featherUpgradedNetherite.put(Type.LEGGINGS, 6);
      featherUpgradedNetherite.put(Type.CHESTPLATE, 8);
      featherUpgradedNetherite.put(Type.HELMET, 3);
   }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, () -> {
      return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
   }, 0.1F),
   CORRUPT_UPGRADED_NETHERITE("upgradednetherite_reforged:corrupt_upgraded_netherite", 37, (EnumMap)Util.make(new EnumMap(Type.class), (corruptUpgradedNetherite) -> {
      corruptUpgradedNetherite.put(Type.BOOTS, 3);
      corruptUpgradedNetherite.put(Type.LEGGINGS, 6);
      corruptUpgradedNetherite.put(Type.CHESTPLATE, 8);
      corruptUpgradedNetherite.put(Type.HELMET, 3);
   }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, () -> {
      return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
   }, 0.1F),
   ECHO_UPGRADED_NETHERITE("upgradednetherite_reforged:echo_upgraded_netherite", 37, (EnumMap)Util.make(new EnumMap(Type.class), (echoUpgradedNetherite) -> {
      echoUpgradedNetherite.put(Type.BOOTS, 3);
      echoUpgradedNetherite.put(Type.LEGGINGS, 6);
      echoUpgradedNetherite.put(Type.CHESTPLATE, 8);
      echoUpgradedNetherite.put(Type.HELMET, 3);
   }), 15, SoundEvents.ARMOR_EQUIP_NETHERITE, 3.0F, () -> {
      return Ingredient.of(new ItemLike[]{Items.NETHERITE_INGOT});
   }, 0.1F);

   private static final EnumMap<Type, Integer> MAX_DAMAGE_ARRAY = (EnumMap)Util.make(new EnumMap(Type.class), (p_266653_) -> {
      p_266653_.put(Type.BOOTS, 13);
      p_266653_.put(Type.LEGGINGS, 15);
      p_266653_.put(Type.CHESTPLATE, 16);
      p_266653_.put(Type.HELMET, 11);
   });
   private final String name;
   private final int maxDamageFactor;
   private final EnumMap<Type, Integer> damageReductionAmountArray;
   private final int enchantability;
   private final SoundEvent soundEvent;
   private final float thoughness;
   private final Supplier<Ingredient> repairMaterial;
   private final float knockbackResistance;

   private ModArmorMaterial(String name, int maxDamageFactor, EnumMap<Type, Integer> damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float thoughness, Supplier<Ingredient> repairMaterial, float knockbackResistance) {
      this.name = name;
      this.maxDamageFactor = maxDamageFactor;
      this.damageReductionAmountArray = damageReductionAmountArray;
      this.enchantability = enchantability;
      this.soundEvent = soundEvent;
      this.thoughness = thoughness;
      this.repairMaterial = repairMaterial;
      this.knockbackResistance = knockbackResistance;
   }

   public int getDurabilityForType(Type slotIn) {
      return (Integer)MAX_DAMAGE_ARRAY.get(slotIn) * this.maxDamageFactor;
   }

   public int getDefenseForType(Type slotIn) {
      return (Integer)this.damageReductionAmountArray.get(slotIn);
   }

   public int getEnchantmentValue() {
      return this.enchantability;
   }

   public SoundEvent getEquipSound() {
      return this.soundEvent;
   }

   public Ingredient getRepairIngredient() {
      return (Ingredient)this.repairMaterial.get();
   }

   public String getName() {
      return this.name;
   }

   public float getToughness() {
      return this.thoughness;
   }

   public float getKnockbackResistance() {
      return this.knockbackResistance;
   }

   // $FF: synthetic method
   private static ModArmorMaterial[] $values() {
      return new ModArmorMaterial[]{GOLD_UPGRADED_NETHERITE, FIRE_UPGRADED_NETHERITE, ENDER_UPGRADED_NETHERITE, WATER_UPGRADED_NETHERITE, WITHER_UPGRADED_NETHERITE, POISON_UPGRADED_NETHERITE, PHANTOM_UPGRADED_NETHERITE, FEATHER_UPGRADED_NETHERITE, CORRUPT_UPGRADED_NETHERITE, ECHO_UPGRADED_NETHERITE};
   }
}
