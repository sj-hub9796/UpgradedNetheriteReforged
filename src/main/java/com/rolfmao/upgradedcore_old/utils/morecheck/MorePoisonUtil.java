package com.rolfmao.upgradedcore_old.utils.morecheck;

import com.rolfmao.upgradedcore_old.compat.ExternalMods;
//import com.rolfmao.upgradednetherite_aetherite.utils.check.AetheritePoisonUtil;
//import com.rolfmao.upgradednetherite_ultimate.config.UpgradedNetheriteUltimateConfig;
//import com.rolfmao.upgradednetherite_ultimate.utils.UltimateItemDataUtil;
//import com.rolfmao.upgradednetherite_ultimate.utils.UltimateUtil;
//import com.rolfmao.upgradedtools.utils.check.ToolPoisonUtil;
//import com.rolfmao.upgradedtools.utils.check.ToolUltimateUtil;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;

// TODO
public class MorePoisonUtil {
   public static int setPoisonArmor() {
      Integer count = 0;
//      if (ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded()) {
//         count = AetheritePoisonUtil.setPoisonArmor();
//      }
//
      return count;
   }

   public static int intWearingPoisonArmor(Player player) {
      Integer count = 0;
//      if (ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded()) {
//         count = count + UltimateUtil.isWearingUltimatePoisonArmor(player);
//      }
//
//      if (ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded()) {
//         count = count + AetheritePoisonUtil.intWearingPoisonArmor(player);
//      }

      return count;
   }

   public static boolean isHorseWearingPoisonArmor(Horse horse) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isHorseUltimateArmor(horse.m_30722_()) && !UltimateItemDataUtil.getUltimeriteDisablePoison(horse.m_30722_()) && UpgradedNetheriteUltimateConfig.EnableUltimatePoisonArmorEffect;
      return false;
   }

   public static boolean isPoisonArmor(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isPoisonArmor(itemStack) || ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded() && AetheritePoisonUtil.isPoisonArmor(itemStack);
      return false;
   }

   public static boolean isPoisonToolOrWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateToolOrWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateToolOrWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisablePoison(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimatePoisonToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolPoisonUtil.isPoisonToolOrWeapon(itemStack);
      return false;
   }

   public static boolean isPoisonWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateMeleeWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisablePoison(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimatePoisonToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolPoisonUtil.isPoisonMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isPoisonMeleeWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateMeleeWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateMeleeWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisablePoison(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimatePoisonToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolPoisonUtil.isPoisonMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isPoisonRangedWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateRangedWeapon(itemStack) && !UltimateItemDataUtil.getUltimeriteDisablePoison(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimatePoisonToolEffect;
      return false;
   }

   public static boolean isPoisonTool(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateTool(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateTool(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisablePoison(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimatePoisonToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolPoisonUtil.isPoisonTool(itemStack);
      return false;
   }

   public static boolean isPoisonHoe(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolPoisonUtil.isPoisonTool(itemStack) || ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateHoe(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateTool(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisablePoison(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimatePoisonToolEffect;
      return false;
   }

   public static boolean isPoisonProjectile(Projectile projectile) {
      return false;
   }

   public static boolean isPoisonShield(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateShield(itemStack) && !UltimateItemDataUtil.getUltimeriteDisablePoison(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimatePoisonToolEffect;
      return false;
   }

   public static boolean isPoisonHorseArmor(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateHorseArmor(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimatePoisonArmorEffect;
      return false;
   }
}
