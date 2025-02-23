package net.sjhub.upgradednetheritereforged.utils.check;

import com.rolfmao.upgradedcore_old.utils.SetArmorUtil;
import com.rolfmao.upgradedcore_old.utils.morecheck.MoreGoldUtil;
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

public class GoldUtil {
   public static int setGoldArmor() {
      return UpgradedNetheriteConfig.UpgradedPlayerGoldArmorRequireSet + MoreGoldUtil.setGoldArmor();
   }

   public static int intWearingGoldArmor(Player player) {
      Integer intSet = 0;
      intSet = intSet + SetArmorUtil.isWearingArmor(player, ModArmorMaterial.GOLD_UPGRADED_NETHERITE);
      intSet = intSet + MoreGoldUtil.intWearingGoldArmor(player);
      return intSet;
   }

   public static boolean isWearingGoldArmor(Player player) {
      Integer intSet = 0;
      intSet = intSet + SetArmorUtil.isWearingArmor(player, ModArmorMaterial.GOLD_UPGRADED_NETHERITE);
      intSet = intSet + MoreGoldUtil.intWearingGoldArmor(player);
      return intSet >= setGoldArmor();
   }

   public static boolean isHorseWearingGoldArmor(Horse horse) {
      return horse.getArmor().getItem() == ((HorseArmorItem)ModItems.GOLD_UPGRADED_NETHERITE_ARMOR_HORSE.get()).asItem() || MoreGoldUtil.isHorseWearingGoldArmor(horse);
   }

   public static boolean isGoldArmor(ItemStack itemStack) {
      return itemStack.getItem() instanceof ArmorItem && ((ArmorItem)itemStack.getItem()).getMaterial() == ModArmorMaterial.GOLD_UPGRADED_NETHERITE || MoreGoldUtil.isGoldArmor(itemStack);
   }

   public static boolean isGoldToolOrWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_PICKAXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_SHOVEL.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_AXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_HOE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_CROSSBOW.get())) || MoreGoldUtil.isGoldToolOrWeapon(itemStack);
   }

   public static boolean isGoldWeapon(ItemStack itemStack) {
      return isGoldMeleeWeapon(itemStack) || isGoldRangedWeapon(itemStack);
   }

   public static boolean isGoldMeleeWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_AXE.get())) || MoreGoldUtil.isGoldWeapon(itemStack);
   }

   public static boolean isGoldRangedWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_CROSSBOW.get())) || MoreGoldUtil.isGoldRangedWeapon(itemStack);
   }

   public static boolean isGoldTool(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_PICKAXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_SHOVEL.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_AXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_HOE.get())) || MoreGoldUtil.isGoldTool(itemStack);
   }

   public static boolean isGoldHoe(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_HOE.get())) || MoreGoldUtil.isGoldHoe(itemStack);
   }

   public static boolean isGoldProjectile(Projectile projectile) {
      return (projectile instanceof Arrow || projectile instanceof SpectralArrow || projectile instanceof FireworkRocketEntity || MoreGoldUtil.isGoldProjectile(projectile)) && projectile.getTags().contains("GoldUpgradedNetheriteBow");
   }

   public static boolean isGoldShield(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_SHIELD.get())) || MoreGoldUtil.isGoldShield(itemStack);
   }

   public static boolean isGoldHorseArmor(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_ARMOR_HORSE.get())) || MoreGoldUtil.isGoldHorseArmor(itemStack);
   }
}
