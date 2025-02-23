package com.rolfmao.upgradedcore_old.utils.morecheck;

import com.rolfmao.upgradedcore_old.compat.ExternalMods;
//import com.rolfmao.upgradednetherite_aetherite.utils.check.AetheriteGoldUtil;
//import com.rolfmao.upgradednetherite_ultimate.config.UpgradedNetheriteUltimateConfig;
//import com.rolfmao.upgradednetherite_ultimate.utils.UltimateItemDataUtil;
//import com.rolfmao.upgradednetherite_ultimate.utils.UltimateUtil;
//import com.rolfmao.upgradedtools.utils.check.ToolGoldUtil;
//import com.rolfmao.upgradedtools.utils.check.ToolUltimateUtil;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;

// TODO
public class MoreGoldUtil {
   public static int setGoldArmor() {
      Integer count = 0;
//      if (ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded()) {
//         count = AetheriteGoldUtil.setGoldArmor();
//      }

      return count;
   }

   public static int intWearingGoldArmor(Player player) {
      Integer count = 0;
//      if (ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded()) {
//         count = count + UltimateUtil.isWearingUltimateGoldArmor(player);
//      }
//
//      if (ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded()) {
//         count = count + AetheriteGoldUtil.intWearingGoldArmor(player);
//      }

      return count;
   }

   public static boolean isHorseWearingGoldArmor(Horse horse) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isHorseUltimateArmor(horse.m_30722_()) && !UltimateItemDataUtil.getUltimeriteDisableGold(horse.m_30722_()) && UpgradedNetheriteUltimateConfig.EnableUltimateGoldArmorEffect;
      return false;
   }

   public static boolean isGoldArmor(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isGoldArmor(itemStack) || ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded() && AetheriteGoldUtil.isGoldArmor(itemStack);
      return false;
   }

   public static boolean isGoldToolOrWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateToolOrWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateToolOrWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableGold(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateGoldToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolGoldUtil.isGoldToolOrWeapon(itemStack);
      return false;
   }

   public static boolean isGoldWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateMeleeWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableGold(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateGoldToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolGoldUtil.isGoldMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isGoldMeleeWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateMeleeWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateMeleeWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableGold(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateGoldToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolGoldUtil.isGoldMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isGoldRangedWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateRangedWeapon(itemStack) && !UltimateItemDataUtil.getUltimeriteDisableGold(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateGoldToolEffect;
      return false;
   }

   public static boolean isGoldTool(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateTool(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateTool(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableGold(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateGoldToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolGoldUtil.isGoldTool(itemStack);
      return false;
   }

   public static boolean isGoldHoe(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolGoldUtil.isGoldTool(itemStack) || ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateHoe(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateTool(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableGold(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateGoldToolEffect;
      return false;
   }

   public static boolean isGoldProjectile(Projectile projectile) {
      return false;
   }

   public static boolean isGoldShield(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateShield(itemStack) && !UltimateItemDataUtil.getUltimeriteDisableGold(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateGoldToolEffect;
      return false;
   }

   public static boolean isGoldHorseArmor(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateHorseArmor(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateGoldArmorEffect;
      return false;
   }
}
