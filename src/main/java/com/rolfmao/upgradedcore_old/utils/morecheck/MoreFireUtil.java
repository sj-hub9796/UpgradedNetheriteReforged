package com.rolfmao.upgradedcore_old.utils.morecheck;

import com.rolfmao.upgradedcore_old.compat.ExternalMods;
//import com.rolfmao.upgradednetherite_aetherite.utils.check.AetheriteFireUtil;
//import com.rolfmao.upgradednetherite_ultimate.config.UpgradedNetheriteUltimateConfig;
//import com.rolfmao.upgradednetherite_ultimate.utils.UltimateItemDataUtil;
//import com.rolfmao.upgradednetherite_ultimate.utils.UltimateUtil;
//import com.rolfmao.upgradedtools.utils.check.ToolFireUtil;
//import com.rolfmao.upgradedtools.utils.check.ToolUltimateUtil;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;

// TODO
public class MoreFireUtil {
   public static int setFireArmor() {
      Integer count = 0;
//      if (ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded()) {
//         count = AetheriteFireUtil.setFireArmor();
//      }

      return count;
   }

   public static int intWearingFireArmor(Player player) {
      Integer count = 0;
//      if (ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded()) {
//         count = count + UltimateUtil.isWearingUltimateFireArmor(player);
//      }
//
//      if (ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded()) {
//         count = count + AetheriteFireUtil.intWearingFireArmor(player);
//      }

      return count;
   }

   public static boolean isHorseWearingFireArmor(Horse horse) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isHorseUltimateArmor(horse.m_30722_()) && !UltimateItemDataUtil.getUltimeriteDisableFire(horse.m_30722_()) && UpgradedNetheriteUltimateConfig.EnableUltimateFireArmorEffect;
      return false;
   }

   public static boolean isFireArmor(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isFireArmor(itemStack) || ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded() && AetheriteFireUtil.isFireArmor(itemStack);
      return false;
   }

   public static boolean isFireToolOrWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateToolOrWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateToolOrWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableFire(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateFireToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolFireUtil.isFireToolOrWeapon(itemStack);
      return false;
   }

   public static boolean isFireWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateMeleeWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableFire(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateFireToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolFireUtil.isFireMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isFireMeleeWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateMeleeWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateMeleeWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableFire(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateFireToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolFireUtil.isFireMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isFireRangedWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateRangedWeapon(itemStack) && !UltimateItemDataUtil.getUltimeriteDisableFire(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateFireToolEffect;
      return false;
   }

   public static boolean isFireTool(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateTool(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateTool(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableFire(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateFireToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolFireUtil.isFireTool(itemStack);
      return false;
   }

   public static boolean isFireProjectile(Projectile projectile) {
      return false;
   }

   public static boolean isFireShield(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateShield(itemStack) && !UltimateItemDataUtil.getUltimeriteDisableFire(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateFireToolEffect;
      return false;
   }

   public static boolean isFireHorseArmor(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateHorseArmor(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateFireArmorEffect;
      return false;
   }
}
