package com.rolfmao.upgradedcore_old.utils.morecheck;

import com.rolfmao.upgradedcore_old.compat.ExternalMods;
//import com.rolfmao.upgradednetherite_aetherite.utils.check.AetheriteWaterUtil;
//import com.rolfmao.upgradednetherite_ultimate.config.UpgradedNetheriteUltimateConfig;
//import com.rolfmao.upgradednetherite_ultimate.utils.UltimateItemDataUtil;
//import com.rolfmao.upgradednetherite_ultimate.utils.UltimateUtil;
//import com.rolfmao.upgradedtools.utils.check.ToolUltimateUtil;
//import com.rolfmao.upgradedtools.utils.check.ToolWaterUtil;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;

// TODO
public class MoreWaterUtil {
   public static int setWaterArmor() {
      Integer count = 0;
//      if (ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded()) {
//         count = AetheriteWaterUtil.setWaterArmor();
//      }

      return count;
   }

   public static int intWearingWaterArmor(Player player) {
      Integer count = 0;
//      if (ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded()) {
//         count = count + UltimateUtil.isWearingUltimateWaterArmor(player);
//      }
//
//      if (ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded()) {
//         count = count + AetheriteWaterUtil.intWearingWaterArmor(player);
//      }

      return count;
   }

   public static boolean isHorseWearingWaterArmor(Horse horse) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isHorseUltimateArmor(horse.m_30722_()) && !UltimateItemDataUtil.getUltimeriteDisableWater(horse.m_30722_()) && UpgradedNetheriteUltimateConfig.EnableUltimateWaterArmorEffect;
      return false;
   }

   public static boolean isWaterArmor(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isWaterArmor(itemStack) || ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded() && AetheriteWaterUtil.isWaterArmor(itemStack);
      return false;
   }

   public static boolean isWaterToolOrWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateToolOrWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateToolOrWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableWater(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateWaterToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolWaterUtil.isWaterToolOrWeapon(itemStack);
      return false;
   }

   public static boolean isWaterWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateMeleeWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableWater(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateWaterToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolWaterUtil.isWaterMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isWaterMeleeWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateMeleeWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateMeleeWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableWater(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateWaterToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolWaterUtil.isWaterMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isWaterRangedWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateRangedWeapon(itemStack) && !UltimateItemDataUtil.getUltimeriteDisableWater(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateWaterToolEffect;
      return false;
   }

   public static boolean isWaterTool(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateTool(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateTool(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableWater(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateWaterToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolWaterUtil.isWaterTool(itemStack);
      return false;
   }

   public static boolean isWaterProjectile(Projectile projectile) {
      return false;
   }

   public static boolean isWaterShield(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateShield(itemStack) && !UltimateItemDataUtil.getUltimeriteDisableWater(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateWaterToolEffect;
      return false;
   }

   public static boolean isWaterHorseArmor(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateHorseArmor(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateWaterArmorEffect;
      return false;
   }
}
