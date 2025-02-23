package net.sjhub.upgradednetheritereforged.utils.check;

import com.rolfmao.upgradedcore_old.utils.SetArmorUtil;
import com.rolfmao.upgradedcore_old.utils.morecheck.MoreEnderUtil;
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
import net.minecraft.world.level.Level;

public class EnderUtil {
   public static int setEnderArmor() {
      return UpgradedNetheriteConfig.UpgradedPlayerEnderArmorRequireSet + MoreEnderUtil.setEnderArmor();
   }

   public static int intWearingEnderArmor(Player player) {
      Integer intSet = 0;
      intSet = intSet + SetArmorUtil.isWearingArmor(player, ModArmorMaterial.ENDER_UPGRADED_NETHERITE);
      intSet = intSet + MoreEnderUtil.intWearingEnderArmor(player);
      return intSet;
   }

   public static boolean isWearingEnderArmor(Player player) {
      Integer intSet = 0;
      intSet = intSet + SetArmorUtil.isWearingArmor(player, ModArmorMaterial.ENDER_UPGRADED_NETHERITE);
      intSet = intSet + MoreEnderUtil.intWearingEnderArmor(player);
      return intSet >= setEnderArmor();
   }

   public static boolean isHorseWearingEnderArmor(Horse horse) {
      return horse.getArmor().getItem() == ((HorseArmorItem)ModItems.ENDER_UPGRADED_NETHERITE_ARMOR_HORSE.get()).asItem() || MoreEnderUtil.isHorseWearingEnderArmor(horse);
   }

   public static boolean isEnderArmor(ItemStack itemStack) {
      return itemStack.getItem() instanceof ArmorItem && ((ArmorItem)itemStack.getItem()).getMaterial() == ModArmorMaterial.ENDER_UPGRADED_NETHERITE || MoreEnderUtil.isEnderArmor(itemStack);
   }

   public static boolean isEnderToolOrWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_PICKAXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_SHOVEL.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_AXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_HOE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_CROSSBOW.get())) || MoreEnderUtil.isEnderToolOrWeapon(itemStack);
   }

   public static boolean isEnderWeapon(ItemStack itemStack) {
      return isEnderMeleeWeapon(itemStack) || isEnderRangedWeapon(itemStack);
   }

   public static boolean isEnderMeleeWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_AXE.get())) || MoreEnderUtil.isEnderWeapon(itemStack);
   }

   public static boolean isEnderRangedWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_CROSSBOW.get())) || MoreEnderUtil.isEnderRangedWeapon(itemStack);
   }

   public static boolean isEnderTool(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_PICKAXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_SHOVEL.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_AXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_HOE.get())) || MoreEnderUtil.isEnderTool(itemStack);
   }

   public static boolean isEnderProjectile(Projectile projectile) {
      return (projectile instanceof Arrow || projectile instanceof SpectralArrow || projectile instanceof FireworkRocketEntity || MoreEnderUtil.isEnderProjectile(projectile)) && projectile.getTags().contains("EnderUpgradedNetheriteBow");
   }

   public static boolean isEnderShield(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_SHIELD.get())) || MoreEnderUtil.isEnderShield(itemStack);
   }

   public static boolean isEnderHorseArmor(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_ARMOR_HORSE.get())) || MoreEnderUtil.isEnderHorseArmor(itemStack);
   }

   public static boolean isVoidYLevel(Player player) {
      return player.level().dimension() == Level.OVERWORLD && player.getY() < -128.0D || player.getY() < -64.0D;
   }
}
