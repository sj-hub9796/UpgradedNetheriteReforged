package net.sjhub.upgradednetheritereforged.utils.check;

import com.rolfmao.upgradedcore.utils.SetArmorUtil;
import com.rolfmao.upgradedcore.utils.morecheck.MoreEchoUtil;
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

public class EchoUtil {
   public static int setEchoArmor() {
      return UpgradedNetheriteConfig.UpgradedPlayerEchoArmorRequireSet + MoreEchoUtil.setEchoArmor();
   }

   public static int intWearingEchoArmor(Player player) {
      Integer intSet = 0;
      intSet = intSet + SetArmorUtil.isWearingArmor(player, ModArmorMaterial.ECHO_UPGRADED_NETHERITE);
      intSet = intSet + MoreEchoUtil.intWearingEchoArmor(player);
      return intSet;
   }

   public static boolean isWearingEchoArmor(Player player) {
      Integer intSet = 0;
      intSet = intSet + SetArmorUtil.isWearingArmor(player, ModArmorMaterial.ECHO_UPGRADED_NETHERITE);
      intSet = intSet + MoreEchoUtil.intWearingEchoArmor(player);
      return intSet >= setEchoArmor();
   }

   public static boolean isHorseWearingEchoArmor(Horse horse) {
      return horse.getArmor().getItem() == ((HorseArmorItem)ModItems.ECHO_UPGRADED_NETHERITE_ARMOR_HORSE.get()).m_5456_() || MoreEchoUtil.isHorseWearingEchoArmor(horse);
   }

   public static boolean isEchoArmor(ItemStack itemStack) {
      return itemStack.getItem() instanceof ArmorItem && ((ArmorItem)itemStack.getItem()).getMaterial() == ModArmorMaterial.ECHO_UPGRADED_NETHERITE || MoreEchoUtil.isEchoArmor(itemStack);
   }

   public static boolean isEchoToolOrWeapon(ItemStack itemStack) {
      return ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_PICKAXE.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_SHOVEL.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_AXE.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_HOE.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_CROSSBOW.get())) || MoreEchoUtil.isEchoToolOrWeapon(itemStack);
   }

   public static boolean isEchoWeapon(ItemStack itemStack) {
      return isEchoMeleeWeapon(itemStack) || isEchoRangedWeapon(itemStack);
   }

   public static boolean isEchoMeleeWeapon(ItemStack itemStack) {
      return ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_AXE.get())) || MoreEchoUtil.isEchoWeapon(itemStack);
   }

   public static boolean isEchoRangedWeapon(ItemStack itemStack) {
      return ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_CROSSBOW.get())) || MoreEchoUtil.isEchoRangedWeapon(itemStack);
   }

   public static boolean isEchoTool(ItemStack itemStack) {
      return ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_PICKAXE.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_SHOVEL.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_AXE.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_HOE.get())) || MoreEchoUtil.isEchoTool(itemStack);
   }

   public static boolean isEchoProjectile(Projectile projectile) {
      return (projectile instanceof Arrow || projectile instanceof SpectralArrow || projectile instanceof FireworkRocketEntity || MoreEchoUtil.isEchoProjectile(projectile)) && projectile.m_19880_().contains("EchoUpgradedNetheriteBow");
   }

   public static boolean isEchoShield(ItemStack itemStack) {
      return ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_SHIELD.get())) || MoreEchoUtil.isEchoShield(itemStack);
   }

   public static boolean isEchoHorseArmor(ItemStack itemStack) {
      return ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_ARMOR_HORSE.get())) || MoreEchoUtil.isEchoHorseArmor(itemStack);
   }

   public static boolean isEchoSoulbound(ItemStack itemStack) {
      return ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_INGOT.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_PICKAXE.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_SHOVEL.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_AXE.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_CROSSBOW.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_SHIELD.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_HELMET.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_CHESTPLATE.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_LEGGINGS.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_BOOTS.get())) || ItemStack.isSame(itemStack, new ItemStack((ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_ARMOR_HORSE.get())) || MoreEchoUtil.isEchoSoulbound(itemStack);
   }
}
