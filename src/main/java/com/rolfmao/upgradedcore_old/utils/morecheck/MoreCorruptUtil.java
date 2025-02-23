package com.rolfmao.upgradedcore_old.utils.morecheck;

//import com.rolfmao.upgradednetherite_aetherite.utils.check.AetheriteCorruptUtil;
//import com.rolfmao.upgradedtools.utils.check.ToolCorruptUtil;
import com.rolfmao.upgradedcore_old.compat.ExternalMods;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;

// TODO
public class MoreCorruptUtil {
   public static int intWearingCorruptArmor(Player player) {
      Integer intSet = 0;
//      if (ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded()) {
//         intSet = intSet + AetheriteCorruptUtil.intWearingCorruptArmor(player);
//      }

      return intSet;
   }

   public static boolean isHorseWearingCorruptArmor(Horse horse) {
      return false;
   }

   public static boolean isCorruptArmor(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_AETHERITE.isLoaded() && AetheriteCorruptUtil.isCorruptArmor(itemStack);
      return false;
   }

   public static boolean isCorruptToolOrWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolCorruptUtil.isCorruptToolOrWeapon(itemStack);
      return false;
   }

   public static boolean isCorruptWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolCorruptUtil.isCorruptMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isCorruptMeleeWeapon(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolCorruptUtil.isCorruptMeleeWeapon(itemStack);
      return false;
   }

   public static boolean isCorruptRangedWeapon(ItemStack itemStack) {
      return false;
   }

   public static boolean isCorruptTool(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDTOOLS.isLoaded() && ToolCorruptUtil.isCorruptTool(itemStack);
      return false;
   }

   public static boolean isCorruptProjectile(Projectile projectile) {
      return false;
   }

   public static boolean isCorruptShield(ItemStack itemStack) {
      return false;
   }

   public static boolean isCorruptHorseArmor(ItemStack itemStack) {
      return false;
   }
}
