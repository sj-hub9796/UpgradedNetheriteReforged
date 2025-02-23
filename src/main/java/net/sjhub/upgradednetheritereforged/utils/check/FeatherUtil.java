package net.sjhub.upgradednetheritereforged.utils.check;

import com.rolfmao.upgradedcore_old.utils.SetArmorUtil;
import com.rolfmao.upgradedcore_old.utils.morecheck.MoreFeatherUtil;
import net.sjhub.upgradednetheritereforged.config.UpgradedNetheriteConfig;
import net.sjhub.upgradednetheritereforged.init.ModItems;
import net.sjhub.upgradednetheritereforged.utils.enums.ModArmorMaterial;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.FireworkRocketEntity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.SpectralArrow;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class FeatherUtil {
   public static int setFeatherArmor() {
      return UpgradedNetheriteConfig.UpgradedPlayerFeatherArmorRequireSet + MoreFeatherUtil.setFeatherArmor();
   }

   public static int intWearingFeatherArmor(Player player) {
      Integer intSet = 0;
      intSet = intSet + SetArmorUtil.isWearingArmor(player, ModArmorMaterial.FEATHER_UPGRADED_NETHERITE);
      intSet = intSet + MoreFeatherUtil.intWearingFeatherArmor(player);
      return intSet;
   }

   public static boolean isWearingFeatherArmor(Player player) {
      Integer intSet = 0;
      intSet = intSet + SetArmorUtil.isWearingArmor(player, ModArmorMaterial.FEATHER_UPGRADED_NETHERITE);
      intSet = intSet + MoreFeatherUtil.intWearingFeatherArmor(player);
      return intSet >= setFeatherArmor();
   }

   public static boolean isHorseWearingFeatherArmor(Horse horse) {
      return horse.getArmor().getItem() == ((HorseArmorItem)ModItems.FEATHER_UPGRADED_NETHERITE_HORSE_ARMOR.get()).asItem() || MoreFeatherUtil.isHorseWearingFeatherArmor(horse);
   }

   public static boolean isFeatherArmor(ItemStack itemStack) {
      return itemStack.getItem() instanceof ArmorItem && ((ArmorItem)itemStack.getItem()).getMaterial() == ModArmorMaterial.FEATHER_UPGRADED_NETHERITE || MoreFeatherUtil.isFeatherArmor(itemStack);
   }

   public static boolean isFeatherToolOrWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_PICKAXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_SHOVEL.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_AXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_HOE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_CROSSBOW.get())) || MoreFeatherUtil.isFeatherToolOrWeapon(itemStack);
   }

   public static boolean isFeatherWeapon(ItemStack itemStack) {
      return isFeatherMeleeWeapon(itemStack) || isFeatherRangedWeapon(itemStack);
   }

   public static boolean isFeatherMeleeWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_AXE.get())) || MoreFeatherUtil.isFeatherWeapon(itemStack);
   }

   public static boolean isFeatherRangedWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_CROSSBOW.get())) || MoreFeatherUtil.isFeatherRangedWeapon(itemStack);
   }

   public static boolean isFeatherTool(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_PICKAXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_SHOVEL.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_AXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_HOE.get())) || MoreFeatherUtil.isFeatherTool(itemStack);
   }

   public static boolean isFeatherProjectile(Projectile projectile) {
      return (projectile instanceof Arrow || projectile instanceof SpectralArrow || projectile instanceof FireworkRocketEntity || MoreFeatherUtil.isFeatherProjectile(projectile)) && projectile.getTags().contains("FeatherUpgradedNetheriteBow");
   }

   public static boolean isFeatherShield(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_SHIELD.get())) || MoreFeatherUtil.isFeatherShield(itemStack);
   }

   public static boolean isFeatherHorseArmor(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_HORSE_ARMOR.get())) || MoreFeatherUtil.isFeatherHorseArmor(itemStack);
   }
}
