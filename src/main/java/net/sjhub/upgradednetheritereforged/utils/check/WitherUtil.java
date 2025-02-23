package net.sjhub.upgradednetheritereforged.utils.check;

import com.rolfmao.upgradedcore_old.utils.SetArmorUtil;
import com.rolfmao.upgradedcore_old.utils.morecheck.MoreWitherUtil;
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

public class WitherUtil {
   public static int setWitherArmor() {
      return UpgradedNetheriteConfig.UpgradedPlayerWitherArmorRequireSet + MoreWitherUtil.setWitherArmor();
   }

   public static int intWearingWitherArmor(Player player) {
      Integer intSet = 0;
      intSet = intSet + SetArmorUtil.isWearingArmor(player, ModArmorMaterial.WITHER_UPGRADED_NETHERITE);
      intSet = intSet + MoreWitherUtil.intWearingWitherArmor(player);
      return intSet;
   }

   public static boolean isWearingWitherArmor(Player player) {
      Integer intSet = 0;
      intSet = intSet + SetArmorUtil.isWearingArmor(player, ModArmorMaterial.WITHER_UPGRADED_NETHERITE);
      intSet = intSet + MoreWitherUtil.intWearingWitherArmor(player);
      return intSet >= setWitherArmor();
   }

   public static boolean isHorseWearingWitherArmor(Horse horse) {
      return horse.getArmor().getItem() == ((HorseArmorItem)ModItems.WITHER_UPGRADED_NETHERITE_ARMOR_HORSE.get()).asItem() || MoreWitherUtil.isHorseWearingWitherArmor(horse);
   }

   public static boolean isWitherArmor(ItemStack itemStack) {
      return itemStack.getItem() instanceof ArmorItem && ((ArmorItem)itemStack.getItem()).getMaterial() == ModArmorMaterial.WITHER_UPGRADED_NETHERITE || MoreWitherUtil.isWitherArmor(itemStack);
   }

   public static boolean isWitherToolOrWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WITHER_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WITHER_UPGRADED_NETHERITE_AXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WITHER_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WITHER_UPGRADED_NETHERITE_CROSSBOW.get())) || MoreWitherUtil.isWitherToolOrWeapon(itemStack);
   }

   public static boolean isWitherWeapon(ItemStack itemStack) {
      return isWitherMeleeWeapon(itemStack) || isWitherRangedWeapon(itemStack);
   }

   public static boolean isWitherMeleeWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WITHER_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WITHER_UPGRADED_NETHERITE_AXE.get())) || MoreWitherUtil.isWitherWeapon(itemStack);
   }

   public static boolean isWitherRangedWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WITHER_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WITHER_UPGRADED_NETHERITE_CROSSBOW.get())) || MoreWitherUtil.isWitherRangedWeapon(itemStack);
   }

   public static boolean isWitherTool(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WITHER_UPGRADED_NETHERITE_AXE.get())) || MoreWitherUtil.isWitherTool(itemStack);
   }

   public static boolean isWitherProjectile(Projectile projectile) {
      return (projectile instanceof Arrow || projectile instanceof SpectralArrow || projectile instanceof FireworkRocketEntity || MoreWitherUtil.isWitherProjectile(projectile)) && projectile.getTags().contains("WitherUpgradedNetheriteBow");
   }

   public static boolean isWitherShield(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WITHER_UPGRADED_NETHERITE_SHIELD.get())) || MoreWitherUtil.isWitherShield(itemStack);
   }

   public static boolean isWitherHorseArmor(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.WITHER_UPGRADED_NETHERITE_ARMOR_HORSE.get())) || MoreWitherUtil.isWitherHorseArmor(itemStack);
   }
}
