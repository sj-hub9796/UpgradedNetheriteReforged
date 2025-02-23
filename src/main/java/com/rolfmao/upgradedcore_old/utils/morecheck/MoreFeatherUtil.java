package com.rolfmao.upgradedcore_old.utils.morecheck;

import com.rolfmao.upgradedcore_old.compat.ExternalMods;
//import com.rolfmao.upgradednetherite_aetherite.utils.check.AetheriteFeatherUtil;
//import com.rolfmao.upgradednetherite_ultimate.config.UpgradedNetheriteUltimateConfig;
//import com.rolfmao.upgradednetherite_ultimate.utils.UltimateItemDataUtil;
//import com.rolfmao.upgradednetherite_ultimate.utils.UltimateUtil;
//import com.rolfmao.upgradedtools.utils.check.ToolFeatherUtil;
//import com.rolfmao.upgradedtools.utils.check.ToolUltimateUtil;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;

// TODO
public class MoreFeatherUtil {
   public static int setFeatherArmor() {
      Integer count = 0;
//      if (ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded()) {
//         count = AetheriteFeatherUtil.setFeatherArmor();
//      }

      return count;
   }

   public static int intWearingFeatherArmor(Player player) {
      Integer count = 0;
//      if (ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded()) {
//         count = count + UltimateUtil.isWearingUltimateFeatherArmor(player);
//      }
//
//      if (ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded()) {
//         count = count + AetheriteFeatherUtil.intWearingFeatherArmor(player);
//      }

      return count;
   }

   public static boolean isHorseWearingFeatherArmor(Horse horse) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isHorseUltimateArmor(horse.m_30722_()) && !UltimateItemDataUtil.getUltimeriteDisableFeather(horse.m_30722_()) && UpgradedNetheriteUltimateConfig.EnableUltimateFeatherArmorEffect;
      return false;
   }

   public static boolean isFeatherArmor(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isFeatherArmor(itemStack) || ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded() && AetheriteFeatherUtil.isFeatherArmor(itemStack);
      return false;
   }

   public static boolean isFeatherToolOrWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateToolOrWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateToolOrWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableFeather(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateFeatherToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolFeatherUtil.isFeatherToolOrWeapon(itemStack);
      return false;
   }

   public static boolean isFeatherWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateMeleeWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableFeather(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateFeatherToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolFeatherUtil.isFeatherMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isFeatherMeleeWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateMeleeWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateMeleeWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableFeather(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateFeatherToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolFeatherUtil.isFeatherMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isFeatherRangedWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateRangedWeapon(itemStack) && !UltimateItemDataUtil.getUltimeriteDisableFeather(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateFeatherToolEffect;
      return false;
   }

   public static boolean isFeatherTool(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateTool(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateTool(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableFeather(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateFeatherToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolFeatherUtil.isFeatherTool(itemStack);
      return false;
   }

   public static boolean isFeatherProjectile(Projectile projectile) {
      return false;
   }

   public static boolean isFeatherShield(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateShield(itemStack) && !UltimateItemDataUtil.getUltimeriteDisableFeather(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateFeatherToolEffect;
      return false;
   }

   public static boolean isFeatherHorseArmor(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateHorseArmor(itemStack) && !UltimateItemDataUtil.getUltimeriteDisableFeather(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateFeatherArmorEffect;
      return false;
   }
}
