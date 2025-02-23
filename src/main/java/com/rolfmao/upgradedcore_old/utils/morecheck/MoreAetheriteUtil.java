package com.rolfmao.upgradedcore_old.utils.morecheck;

import com.rolfmao.upgradedcore_old.compat.ExternalMods;
//import com.rolfmao.upgradednetherite_aetherite.utils.check.AetheriteUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

// TODO
public class MoreAetheriteUtil {
   public static int intWearingAetheriteArmor(Player player) {
      Integer count = 0;
//      if (ExternalMods.UPGRADEDNETHERITE_ULTIMATE.isLoaded()) {
//         count = count + AetheriteUtil.intWearingAetheriteArmor(player);
//      }

      return count;
   }

   public static boolean isAetheriteArmor(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded() && AetheriteUtil.isAetheriteArmor(itemStack);
      return false;
   }

   public static boolean isAetheriteWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded() && AetheriteUtil.isAetheriteWeapon(itemStack);
      return false;
   }

   public static boolean isAetheriteTool(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded() && AetheriteUtil.isAetheriteTool(itemStack);
      return false;
   }
}
