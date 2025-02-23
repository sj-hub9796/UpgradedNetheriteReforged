package com.rolfmao.upgradedcore_old.utils.morecheck;

import com.rolfmao.upgradedcore_old.compat.ExternalMods;
//import com.rolfmao.upgradednetherite_aetherite.utils.check.AetheriteEnderUtil;
//import com.rolfmao.upgradednetherite_ultimate.config.UpgradedNetheriteUltimateConfig;
//import com.rolfmao.upgradednetherite_ultimate.utils.UltimateItemDataUtil;
//import com.rolfmao.upgradednetherite_ultimate.utils.UltimateUtil;
//import com.rolfmao.upgradedtools.utils.check.ToolEnderUtil;
//import com.rolfmao.upgradedtools.utils.check.ToolUltimateUtil;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;

// TODO
public class MoreEnderUtil {
   public static int setEnderArmor() {
      Integer count = 0;
//      if (ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded()) {
//         count = AetheriteEnderUtil.setEnderArmor();
//      }

      return count;
   }

   public static int intWearingEnderArmor(Player player) {
      Integer count = 0;
//      if (ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded()) {
//         count = count + UltimateUtil.isWearingUltimateEnderArmor(player);
//      }
//
//      if (ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded()) {
//         count = count + AetheriteEnderUtil.intWearingEnderArmor(player);
//      }

      return count;
   }

   public static boolean isHorseWearingEnderArmor(Horse horse) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isHorseUltimateArmor(horse.m_30722_()) && !UltimateItemDataUtil.getUltimeriteDisableEnder(horse.m_30722_()) && UpgradedNetheriteUltimateConfig.EnableUltimateEnderArmorEffect;
      return false;
   }

   public static boolean isEnderArmor(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isEnderArmor(itemStack) || ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded() && AetheriteEnderUtil.isEnderArmor(itemStack);
      return false;
   }

   public static boolean isEnderToolOrWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateToolOrWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateToolOrWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableEnder(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateEnderToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolEnderUtil.isEnderToolOrWeapon(itemStack);
      return false;
   }

   public static boolean isEnderWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateMeleeWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableEnder(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateEnderToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolEnderUtil.isEnderMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isEnderMeleeWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateMeleeWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateMeleeWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableEnder(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateEnderToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolEnderUtil.isEnderMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isEnderRangedWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateRangedWeapon(itemStack) && !UltimateItemDataUtil.getUltimeriteDisableEnder(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateEnderToolEffect;
      return false;
   }

   public static boolean isEnderTool(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateTool(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateTool(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableEnder(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateEnderToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolEnderUtil.isEnderTool(itemStack);
      return false;
   }

   public static boolean isEnderProjectile(Projectile projectile) {
      return false;
   }

   public static boolean isEnderShield(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateShield(itemStack) && !UltimateItemDataUtil.getUltimeriteDisableEnder(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateEnderToolEffect;
      return false;
   }

   public static boolean isEnderHorseArmor(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateHorseArmor(itemStack) && !UltimateItemDataUtil.getUltimeriteDisableEnder(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateEnderArmorEffect;
      return false;
   }
}
