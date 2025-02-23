package net.sjhub.upgradednetheritereforged.utils.check;

import com.rolfmao.upgradedcore_old.utils.SetArmorUtil;
import com.rolfmao.upgradedcore_old.utils.morecheck.MoreFireUtil;
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

public class FireUtil {
   public static int setFireArmor() {
      return UpgradedNetheriteConfig.UpgradedPlayerFireArmorRequireSet + MoreFireUtil.setFireArmor();
   }

   public static int intWearingFireArmor(Player player) {
      Integer intSet = 0;
      intSet = intSet + SetArmorUtil.isWearingArmor(player, ModArmorMaterial.FIRE_UPGRADED_NETHERITE);
      intSet = intSet + MoreFireUtil.intWearingFireArmor(player);
      return intSet;
   }

   public static boolean isWearingFireArmor(Player player) {
      Integer intSet = 0;
      intSet = intSet + SetArmorUtil.isWearingArmor(player, ModArmorMaterial.FIRE_UPGRADED_NETHERITE);
      intSet = intSet + MoreFireUtil.intWearingFireArmor(player);
      return intSet >= setFireArmor();
   }

   public static boolean isHorseWearingFireArmor(Horse horse) {
      return horse.getArmor().getItem() == ((HorseArmorItem)ModItems.FIRE_UPGRADED_NETHERITE_ARMOR_HORSE.get()).asItem() || MoreFireUtil.isHorseWearingFireArmor(horse);
   }

   public static boolean isFireArmor(ItemStack itemStack) {
      return itemStack.getItem() instanceof ArmorItem && ((ArmorItem)itemStack.getItem()).getMaterial() == ModArmorMaterial.FIRE_UPGRADED_NETHERITE || MoreFireUtil.isFireArmor(itemStack);
   }

   public static boolean isFireToolOrWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_PICKAXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_SHOVEL.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_AXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_HOE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_CROSSBOW.get())) || MoreFireUtil.isFireToolOrWeapon(itemStack);
   }

   public static boolean isFireWeapon(ItemStack itemStack) {
      return isFireMeleeWeapon(itemStack) || isFireRangedWeapon(itemStack);
   }

   public static boolean isFireMeleeWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_AXE.get())) || MoreFireUtil.isFireWeapon(itemStack);
   }

   public static boolean isFireRangedWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_CROSSBOW.get())) || MoreFireUtil.isFireRangedWeapon(itemStack);
   }

   public static boolean isFireTool(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_PICKAXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_SHOVEL.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_AXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_HOE.get())) || MoreFireUtil.isFireTool(itemStack);
   }

   public static boolean isFireProjectile(Projectile projectile) {
      return (projectile instanceof Arrow || projectile instanceof SpectralArrow || projectile instanceof FireworkRocketEntity || MoreFireUtil.isFireProjectile(projectile)) && projectile.getTags().contains("FireUpgradedNetheriteBow");
   }

   public static boolean isFireShield(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_SHIELD.get())) || MoreFireUtil.isFireShield(itemStack);
   }

   public static boolean isFireHorseArmor(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_ARMOR_HORSE.get())) || MoreFireUtil.isFireHorseArmor(itemStack);
   }
}
