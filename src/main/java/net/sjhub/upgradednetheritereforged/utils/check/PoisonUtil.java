package net.sjhub.upgradednetheritereforged.utils.check;

import com.rolfmao.upgradedcore_old.utils.SetArmorUtil;
import com.rolfmao.upgradedcore_old.utils.morecheck.MorePoisonUtil;
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

public class PoisonUtil {
   public static int setPoisonArmor() {
      return UpgradedNetheriteConfig.UpgradedPlayerPoisonArmorRequireSet + MorePoisonUtil.setPoisonArmor();
   }

   public static int intWearingPoisonArmor(Player player) {
      Integer intSet = 0;
      intSet = intSet + SetArmorUtil.isWearingArmor(player, ModArmorMaterial.POISON_UPGRADED_NETHERITE);
      intSet = intSet + MorePoisonUtil.intWearingPoisonArmor(player);
      return intSet;
   }

   public static boolean isWearingPoisonArmor(Player player) {
      Integer intSet = 0;
      intSet = intSet + SetArmorUtil.isWearingArmor(player, ModArmorMaterial.POISON_UPGRADED_NETHERITE);
      intSet = intSet + MorePoisonUtil.intWearingPoisonArmor(player);
      return intSet >= setPoisonArmor();
   }

   public static boolean isHorseWearingPoisonArmor(Horse horse) {
      return horse.getArmor().getItem() == ((HorseArmorItem)ModItems.POISON_UPGRADED_NETHERITE_HORSE_ARMOR.get()).asItem() || MorePoisonUtil.isHorseWearingPoisonArmor(horse);
   }

   public static boolean isPoisonArmor(ItemStack itemStack) {
      return itemStack.getItem() instanceof ArmorItem && ((ArmorItem)itemStack.getItem()).getMaterial() == ModArmorMaterial.POISON_UPGRADED_NETHERITE || MorePoisonUtil.isPoisonArmor(itemStack);
   }

   public static boolean isPoisonToolOrWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.POISON_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.POISON_UPGRADED_NETHERITE_AXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.POISON_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.POISON_UPGRADED_NETHERITE_CROSSBOW.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.POISON_UPGRADED_NETHERITE_HOE.get())) || MorePoisonUtil.isPoisonToolOrWeapon(itemStack);
   }

   public static boolean isPoisonWeapon(ItemStack itemStack) {
      return isPoisonMeleeWeapon(itemStack) || isPoisonRangedWeapon(itemStack);
   }

   public static boolean isPoisonMeleeWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.POISON_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.POISON_UPGRADED_NETHERITE_AXE.get())) || MorePoisonUtil.isPoisonWeapon(itemStack);
   }

   public static boolean isPoisonRangedWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.POISON_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.POISON_UPGRADED_NETHERITE_CROSSBOW.get())) || MorePoisonUtil.isPoisonRangedWeapon(itemStack);
   }

   public static boolean isPoisonTool(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.POISON_UPGRADED_NETHERITE_AXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.POISON_UPGRADED_NETHERITE_HOE.get())) || MorePoisonUtil.isPoisonTool(itemStack);
   }

   public static boolean isPoisonHoe(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.POISON_UPGRADED_NETHERITE_HOE.get())) || MorePoisonUtil.isPoisonHoe(itemStack);
   }

   public static boolean isPoisonProjectile(Projectile projectile) {
      return (projectile instanceof Arrow || projectile instanceof SpectralArrow || projectile instanceof FireworkRocketEntity || MorePoisonUtil.isPoisonProjectile(projectile)) && projectile.getTags().contains("PoisonUpgradedNetheriteBow");
   }

   public static boolean isPoisonShield(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.POISON_UPGRADED_NETHERITE_SHIELD.get())) || MorePoisonUtil.isPoisonShield(itemStack);
   }

   public static boolean isPoisonHorseArmor(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.POISON_UPGRADED_NETHERITE_HORSE_ARMOR.get())) || MorePoisonUtil.isPoisonHorseArmor(itemStack);
   }
}
