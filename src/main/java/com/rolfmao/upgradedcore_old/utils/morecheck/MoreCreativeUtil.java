package com.rolfmao.upgradedcore_old.utils.morecheck;

import com.rolfmao.upgradedcore_old.compat.ExternalMods;
//import com.rolfmao.upgradedtools.utils.check.ToolCreativeUtil;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;

// TODO
public class MoreCreativeUtil {
   public static int intWearingCreativeArmor(Player player) {
      Integer intSet = 0;
      return intSet;
   }

   public static boolean isHorseWearingCreativeArmor(Horse horse) {
      return false;
   }

   public static boolean isCreativeToolOrWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolCreativeUtil.isCreativeToolOrWeapon(itemStack);
      return false;
   }

   public static boolean isCreativeWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolCreativeUtil.isCreativeMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isCreativeMeleeWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolCreativeUtil.isCreativeMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isCreativeRangedWeapon(ItemStack itemStack) {
      return false;
   }

   public static boolean isCreativeTool(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolCreativeUtil.isCreativeTool(itemStack);
      return false;
   }

   public static boolean isCreativeProjectile(Projectile projectile) {
      return false;
   }

   public static boolean isCreativeShield(ItemStack itemStack) {
      return false;
   }

   public static boolean isCreativeHorseArmor(ItemStack itemStack) {
      return false;
   }
}
