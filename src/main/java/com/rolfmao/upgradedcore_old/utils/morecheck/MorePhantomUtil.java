package com.rolfmao.upgradedcore_old.utils.morecheck;

import com.rolfmao.upgradedcore_old.compat.ExternalMods;
//import com.rolfmao.upgradednetherite_aetherite.utils.check.AetheritePhantomUtil;
//import com.rolfmao.upgradednetherite_ultimate.config.UpgradedNetheriteUltimateConfig;
//import com.rolfmao.upgradednetherite_ultimate.utils.UltimateItemDataUtil;
//import com.rolfmao.upgradednetherite_ultimate.utils.UltimateUtil;
//import com.rolfmao.upgradedtools.utils.check.ToolPhantomUtil;
//import com.rolfmao.upgradedtools.utils.check.ToolUltimateUtil;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;

// TODO
public class MorePhantomUtil {
   public static int setPhantomArmor() {
      Integer count = 0;
//      if (ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded()) {
//         count = AetheritePhantomUtil.setPhantomArmor();
//      }

      return count;
   }

   public static int intWearingPhantomArmor(Player player) {
      Integer count = 0;
//      if (ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded()) {
//         count = count + UltimateUtil.isWearingUltimatePhantomArmor(player);
//      }
//
//      if (ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded()) {
//         count = count + AetheritePhantomUtil.intWearingPhantomArmor(player);
//      }

      return count;
   }

   public static boolean isHorseWearingPhantomArmor(Horse horse) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isHorseUltimateArmor(horse.m_30722_()) && !UltimateItemDataUtil.getUltimeriteDisablePhantom(horse.m_30722_()) && UpgradedNetheriteUltimateConfig.EnableUltimatePhantomArmorEffect;
      return false;
   }

   public static boolean isPhantomArmor(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isPhantomArmor(itemStack) || ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded() && AetheritePhantomUtil.isPhantomArmor(itemStack);
      return false;
   }

   public static boolean isPhantomToolOrWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateToolOrWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateToolOrWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisablePhantom(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimatePhantomToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolPhantomUtil.isPhantomToolOrWeapon(itemStack);
      return false;
   }

   public static boolean isPhantomWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateMeleeWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisablePhantom(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimatePhantomToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolPhantomUtil.isPhantomMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isPhantomMeleeWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateMeleeWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateMeleeWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisablePhantom(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimatePhantomToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolPhantomUtil.isPhantomMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isPhantomRangedWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateRangedWeapon(itemStack) && !UltimateItemDataUtil.getUltimeriteDisablePhantom(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimatePhantomToolEffect;
      return false;
   }

   public static boolean isPhantomTool(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateTool(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateTool(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisablePhantom(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimatePhantomToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolPhantomUtil.isPhantomTool(itemStack);
      return false;
   }

   public static boolean isPhantomProjectile(Projectile projectile) {
      return false;
   }

   public static boolean isPhantomShield(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateShield(itemStack) && !UltimateItemDataUtil.getUltimeriteDisablePhantom(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimatePhantomToolEffect;
      return false;
   }

   public static boolean isPhantomHorseArmor(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateHorseArmor(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimatePhantomArmorEffect;
      return false;
   }
}
