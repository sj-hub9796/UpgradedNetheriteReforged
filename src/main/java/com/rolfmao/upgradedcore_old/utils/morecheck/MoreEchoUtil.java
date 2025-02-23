package com.rolfmao.upgradedcore_old.utils.morecheck;

import com.rolfmao.upgradedcore_old.compat.ExternalMods;
//import com.rolfmao.upgradednetherite_aetherite.utils.check.AetheriteEchoUtil;
//import com.rolfmao.upgradednetherite_ultimate.config.UpgradedNetheriteUltimateConfig;
//import com.rolfmao.upgradednetherite_ultimate.init.ModItems;
//import com.rolfmao.upgradednetherite_ultimate.utils.UltimateItemDataUtil;
//import com.rolfmao.upgradednetherite_ultimate.utils.UltimateUtil;
//import com.rolfmao.upgradedtools.utils.check.ToolEchoUtil;
//import com.rolfmao.upgradedtools.utils.check.ToolUltimateUtil;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

// TODO
public class MoreEchoUtil {
   public static int setEchoArmor() {
      Integer count = 0;
//      if (ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded()) {
//         count = AetheriteEchoUtil.setEchoArmor();
//      }

      return count;
   }

   public static int intWearingEchoArmor(Player player) {
      Integer count = 0;
//      if (ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded()) {
//         count = count + UltimateUtil.isWearingUltimateEchoArmor(player);
//      }
//
//      if (ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded()) {
//         count = count + AetheriteEchoUtil.intWearingEchoArmor(player);
//      }

      return count;
   }

   public static boolean isEchoArmor(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isEchoArmor(itemStack) || ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded() && AetheriteEchoUtil.isEchoArmor(itemStack);
      return false;
   }

   public static boolean isHorseWearingEchoArmor(Horse horse) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isHorseUltimateArmor(horse.m_30722_()) && !UltimateItemDataUtil.getUltimeriteDisableEcho(horse.m_30722_()) && UpgradedNetheriteUltimateConfig.EnableUltimateEchoArmorEffect;
      return false;
   }

   public static boolean isEchoToolOrWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateToolOrWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateToolOrWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableEcho(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateEchoToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolEchoUtil.isEchoToolOrWeapon(itemStack);
      return false;
   }

   public static boolean isEchoWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateMeleeWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableEcho(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateEchoToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolEchoUtil.isEchoMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isEchoMeleeWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateMeleeWeapon(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateMeleeWeapon(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableEcho(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateEchoToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolEchoUtil.isEchoMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isEchoRangedWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateRangedWeapon(itemStack) && !UltimateItemDataUtil.getUltimeriteDisableEcho(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateEchoToolEffect;
      return false;
   }

   public static boolean isEchoTool(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && (UltimateUtil.isUltimateTool(itemStack) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolUltimateUtil.isUltimateTool(itemStack)) && !UltimateItemDataUtil.getUltimeriteDisableEcho(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateEchoToolEffect || ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolEchoUtil.isEchoTool(itemStack);
      return false;
   }

   public static boolean isEchoProjectile(Projectile projectile) {
      return false;
   }

   public static boolean isEchoShield(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateShield(itemStack) && !UltimateItemDataUtil.getUltimeriteDisableEcho(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateEchoToolEffect;
      return false;
   }

   public static boolean isEchoHorseArmor(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() && UltimateUtil.isUltimateHorseArmor(itemStack) && !UltimateItemDataUtil.getUltimeriteDisableEcho(itemStack) && UpgradedNetheriteUltimateConfig.EnableUltimateEchoArmorEffect;
      return false;
   }

   public static boolean isEchoSoulbound(ItemStack itemStack) {
      boolean var10000 = false;
//      if ((!ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded() || (!UltimateUtil.isUltimateToolOrWeapon(itemStack) && !UltimateUtil.isUltimateShield(itemStack) && (!ExternalMods.UPGRADEDTOOLS.isLoaded() || !ToolUltimateUtil.isUltimateToolOrWeapon(itemStack)) || UltimateItemDataUtil.getUltimeriteDisableEcho(itemStack) || !UpgradedNetheriteUltimateConfig.EnableUltimateEchoToolEffect) && (!UltimateUtil.isUltimateArmor(itemStack) && !UltimateUtil.isUltimateHorseArmor(itemStack) || UltimateItemDataUtil.getUltimeriteDisableEcho(itemStack) || !UpgradedNetheriteUltimateConfig.EnableUltimateEchoArmorEffect) && (!ItemStack.m_41746_(itemStack, new ItemStack((ItemLike)ModItems.ULTIMATE_UPGRADED_NETHERITE_INGOT.get())) || !UpgradedNetheriteUltimateConfig.EnableUltimateEchoToolEffect && !UpgradedNetheriteUltimateConfig.EnableUltimateEchoArmorEffect) && (!ExternalMods.UPGRADEDNETHERITE_ITEMS.isLoaded() || !ItemStack.m_41746_(itemStack, new ItemStack((ItemLike)com.rolfmao.upgradednetherite_items.init.ModItems.ULTIMATE_UPGRADED_NETHERITE_TOTEM.get())) || UltimateItemDataUtil.getUltimeriteDisableEcho(itemStack) || !UpgradedNetheriteUltimateConfig.EnableUltimateEchoToolEffect)) && (!ExternalMods.UPGRADEDNETHERITE_ITEMS.isLoaded() || !ItemStack.m_41746_(itemStack, new ItemStack((ItemLike)com.rolfmao.upgradednetherite_items.init.ModItems.ECHO_UPGRADED_NETHERITE_TOTEM.get()))) && (!ExternalMods.UPGRADEDTOOLS.isLoaded() || !ToolEchoUtil.isEchoToolOrWeapon(itemStack))) {
//         var10000 = false;
//      } else {
//         var10000 = true;
//      }

      return var10000;
   }
}
