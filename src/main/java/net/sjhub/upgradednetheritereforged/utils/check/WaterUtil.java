package net.sjhub.upgradednetheritereforged.utils.check;

import com.rolfmao.upgradedcore_old.utils.SetArmorUtil;
import com.rolfmao.upgradedcore_old.utils.morecheck.MoreWaterUtil;
import net.sjhub.upgradednetheritereforged.config.UpgradedNetheriteConfig;
import net.sjhub.upgradednetheritereforged.init.ModItems;
import net.sjhub.upgradednetheritereforged.utils.enums.ModArmorMaterial;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class WaterUtil {
   public static int setWaterArmor() {
      return UpgradedNetheriteConfig.UpgradedPlayerWaterArmorRequireSet + MoreWaterUtil.setWaterArmor();
   }

   public static int intWearingWaterArmor(Player player) {
      Integer intSet = 0;
      intSet = intSet + SetArmorUtil.isWearingArmor(player, ModArmorMaterial.WATER_UPGRADED_NETHERITE);
      intSet = intSet + MoreWaterUtil.intWearingWaterArmor(player);
      return intSet;
   }

   public static boolean isWearingWaterArmor(Player player) {
      Integer intSet = 0;
      intSet = intSet + SetArmorUtil.isWearingArmor(player, ModArmorMaterial.WATER_UPGRADED_NETHERITE);
      intSet = intSet + MoreWaterUtil.intWearingWaterArmor(player);
      return intSet >= setWaterArmor();
   }

   public static boolean isHorseWearingWaterArmor(Horse horse) {
      return horse.getArmor().getItem() == ((HorseArmorItem)ModItems.WATER_UPGRADED_NETHERITE_HORSE_ARMOR.get()).asItem() || MoreWaterUtil.isHorseWearingWaterArmor(horse);
   }

   public static boolean isWaterArmor(ItemStack itemStack) {
      return itemStack.getItem() instanceof ArmorItem && ((ArmorItem)itemStack.getItem()).getMaterial() == ModArmorMaterial.WATER_UPGRADED_NETHERITE || MoreWaterUtil.isWaterArmor(itemStack);
   }

   public static boolean isWaterToolOrWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WATER_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WATER_UPGRADED_NETHERITE_PICKAXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WATER_UPGRADED_NETHERITE_SHOVEL.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WATER_UPGRADED_NETHERITE_AXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WATER_UPGRADED_NETHERITE_HOE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WATER_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WATER_UPGRADED_NETHERITE_CROSSBOW.get())) || MoreWaterUtil.isWaterToolOrWeapon(itemStack);
   }

   public static boolean isWaterWeapon(ItemStack itemStack) {
      return isWaterMeleeWeapon(itemStack) || isWaterRangedWeapon(itemStack);
   }

   public static boolean isWaterMeleeWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WATER_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WATER_UPGRADED_NETHERITE_AXE.get())) || MoreWaterUtil.isWaterWeapon(itemStack);
   }

   public static boolean isWaterRangedWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WATER_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WATER_UPGRADED_NETHERITE_CROSSBOW.get())) || MoreWaterUtil.isWaterRangedWeapon(itemStack);
   }

   public static boolean isWaterTool(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WATER_UPGRADED_NETHERITE_PICKAXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WATER_UPGRADED_NETHERITE_SHOVEL.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WATER_UPGRADED_NETHERITE_AXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WATER_UPGRADED_NETHERITE_HOE.get())) || MoreWaterUtil.isWaterTool(itemStack);
   }

   public static boolean isWaterProjectile(Projectile projectile) {
      return (projectile instanceof Arrow || projectile instanceof SpectralArrow || projectile instanceof FireworkRocketEntity || MoreWaterUtil.isWaterProjectile(projectile)) && projectile.getTags().contains("WaterUpgradedNetheriteBow");
   }

   public static boolean isWaterShield(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WATER_UPGRADED_NETHERITE_SHIELD.get())) || MoreWaterUtil.isWaterShield(itemStack);
   }

   public static boolean isWaterHorseArmor(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WATER_UPGRADED_NETHERITE_HORSE_ARMOR.get())) || MoreWaterUtil.isWaterHorseArmor(itemStack);
   }
}
