package net.sjhub.upgradednetheritereforged.utils.check;

import com.rolfmao.upgradedcore_old.utils.SetArmorUtil;
import com.rolfmao.upgradedcore_old.utils.morecheck.MorePhantomUtil;
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

public class PhantomUtil {
   public static int setPhantomArmor() {
      return UpgradedNetheriteConfig.UpgradedPlayerPhantomArmorRequireSet + MorePhantomUtil.setPhantomArmor();
   }

   public static int intWearingPhantomArmor(Player player) {
      Integer intSet = 0;
      intSet = intSet + SetArmorUtil.isWearingArmor(player, ModArmorMaterial.PHANTOM_UPGRADED_NETHERITE);
      intSet = intSet + MorePhantomUtil.intWearingPhantomArmor(player);
      return intSet;
   }

   public static boolean isWearingPhantomArmor(Player player) {
      Integer intSet = 0;
      intSet = intSet + SetArmorUtil.isWearingArmor(player, ModArmorMaterial.PHANTOM_UPGRADED_NETHERITE);
      intSet = intSet + MorePhantomUtil.intWearingPhantomArmor(player);
      return intSet >= setPhantomArmor();
   }

   public static boolean isHorseWearingPhantomArmor(Horse horse) {
      return horse.getArmor().getItem() == ((HorseArmorItem)ModItems.PHANTOM_UPGRADED_NETHERITE_HORSE_ARMOR.get()).asItem() || MorePhantomUtil.isHorseWearingPhantomArmor(horse);
   }

   public static boolean isPhantomArmor(ItemStack itemStack) {
      return itemStack.getItem() instanceof ArmorItem && ((ArmorItem)itemStack.getItem()).getMaterial() == ModArmorMaterial.PHANTOM_UPGRADED_NETHERITE || MorePhantomUtil.isPhantomArmor(itemStack);
   }

   public static boolean isPhantomToolOrWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_PICKAXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_SHOVEL.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_AXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_HOE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_CROSSBOW.get())) || MorePhantomUtil.isPhantomToolOrWeapon(itemStack);
   }

   public static boolean isPhantomWeapon(ItemStack itemStack) {
      return isPhantomMeleeWeapon(itemStack) || isPhantomRangedWeapon(itemStack);
   }

   public static boolean isPhantomMeleeWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_AXE.get())) || MorePhantomUtil.isPhantomWeapon(itemStack);
   }

   public static boolean isPhantomRangedWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_CROSSBOW.get())) || MorePhantomUtil.isPhantomRangedWeapon(itemStack);
   }

   public static boolean isPhantomTool(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_PICKAXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_SHOVEL.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_AXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_HOE.get())) || MorePhantomUtil.isPhantomTool(itemStack);
   }

   public static boolean isPhantomProjectile(Projectile projectile) {
      return (projectile instanceof Arrow || projectile instanceof SpectralArrow || projectile instanceof FireworkRocketEntity || MorePhantomUtil.isPhantomProjectile(projectile)) && projectile.getTags().contains("PhantomUpgradedNetheriteBow");
   }

   public static boolean isPhantomShield(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_SHIELD.get())) || MorePhantomUtil.isPhantomShield(itemStack);
   }

   public static boolean isPhantomHorseArmor(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_HORSE_ARMOR.get())) || MorePhantomUtil.isPhantomHorseArmor(itemStack);
   }
}
