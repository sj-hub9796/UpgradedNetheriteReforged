package com.rolfmao.upgradedcore_old.utils.morecheck;

import com.rolfmao.upgradedcore_old.compat.ExternalMods;
//import com.rolfmao.upgradednetherite_aetherite.utils.check.AetheriteWitherUtil;
//import com.rolfmao.upgradednetherite_ultimate.config.UpgradedNetheriteUltimateConfig;
//import com.rolfmao.upgradednetherite_ultimate.utils.UltimateItemDataUtil;
//import com.rolfmao.upgradednetherite_ultimate.utils.UltimateUtil;
//import com.rolfmao.upgradedtools.utils.check.ToolUltimateUtil;
//import com.rolfmao.upgradedtools.utils.check.ToolWitherUtil;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;

// TODO
public class MoreWitherUtil {
   public static int setWitherArmor() {
      Integer count = 0;
//      if (ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded()) {
//         count = AetheriteWitherUtil.setWitherArmor();
//      }

      return count;
   }

   public static int intWearingWitherArmor(Player player) {
      Integer count = 0;
//      if (ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded()) {
//         count = count + UltimateUtil.isWearingUltimateWitherArmor(player);
//      }
//
//      if (ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded()) {
//         count = count + AetheriteWitherUtil.intWearingWitherArmor(player);
//      }

      return count;
   }

   public static boolean isHorseWearingWitherArmor(Horse horse) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isHorseUltimateArmor(horse.m_30722_()) && !UltimateItemDataUtil.getUltimeriteDisableWither(horse.m_30722_()) && UpgradedNetheriteUltimateConfig.EnableUltimateWitherArmorEffect;
      return false;
   }

   public static boolean isWitherArmor(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isWitherArmor(itemStack) || ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded() && AetheriteWitherUtil.isWitherArmor(itemStack);
      return false;
   }

   public static boolean isWitherToolOrWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateToolOrWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateToolOrWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableWither(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateWitherToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolWitherUtil.isWitherToolOrWeapon(itemStack);
      return false;
   }

   public static boolean isWitherWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateMeleeWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableWither(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateWitherToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolWitherUtil.isWitherMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isWitherMeleeWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateMeleeWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateMeleeWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableWither(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateWitherToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolWitherUtil.isWitherMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isWitherRangedWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateRangedWeapon(itemStack) && !UltimateItemDataUtil.getUltimeriteDisableWither(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateWitherToolEffect;
      return false;
   }

   public static boolean isWitherTool(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateTool(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateTool(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableWither(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateWitherToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolWitherUtil.isWitherTool(itemStack);
      return false;
   }

   public static boolean isWitherProjectile(Projectile projectile) {
      return false;
   }

   public static boolean isWitherShield(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateShield(itemStack) && !UltimateItemDataUtil.getUltimeriteDisableWither(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateWitherToolEffect;
      return false;
   }

   public static boolean isWitherHorseArmor(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateHorseArmor(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateWitherArmorEffect;
      return false;
   }
}
