package net.sjhub.upgradednetheritereforged.utils.check;

import com.rolfmao.upgradedcore_old.utils.SetArmorUtil;
import com.rolfmao.upgradedcore_old.utils.morecheck.MoreCorruptUtil;
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

public class CorruptUtil {
   public static Integer intWearingCorrupt(Player player, Boolean enableHorse) {
      Integer count = 0;
      count = count + SetArmorUtil.isWearingArmor(player, ModArmorMaterial.CORRUPT_UPGRADED_NETHERITE);
      count = count + MoreCorruptUtil.intWearingCorruptArmor(player);
      if (enableHorse && player.getVehicle() != null && player.getVehicle() instanceof Horse && isHorseWearingCorruptArmor((Horse)player.getVehicle())) {
         count = count + 1;
      }

      return count;
   }

   public static boolean isHorseWearingCorruptArmor(Horse horse) {
      return horse.getArmor().getItem() == ((HorseArmorItem)ModItems.CORRUPT_UPGRADED_NETHERITE_HORSE_ARMOR.get()).asItem();
   }

   public static boolean isCorruptArmor(ItemStack itemStack) {
      return itemStack.getItem() instanceof ArmorItem && ((ArmorItem)itemStack.getItem()).getMaterial() == ModArmorMaterial.CORRUPT_UPGRADED_NETHERITE || MoreCorruptUtil.isCorruptArmor(itemStack);
   }

   public static boolean isCorruptToolOrWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_PICKAXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_SHOVEL.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_AXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_HOE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_CROSSBOW.get())) || MoreCorruptUtil.isCorruptToolOrWeapon(itemStack);
   }

   public static boolean isCorruptWeapon(ItemStack itemStack) {
      return isCorruptMeleeWeapon(itemStack) || isCorruptRangedWeapon(itemStack);
   }

   public static boolean isCorruptMeleeWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_SWORD.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_AXE.get())) || MoreCorruptUtil.isCorruptWeapon(itemStack);
   }

   public static boolean isCorruptRangedWeapon(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_BOW.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_CROSSBOW.get())) || MoreCorruptUtil.isCorruptRangedWeapon(itemStack);
   }

   public static boolean isCorruptTool(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_PICKAXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_SHOVEL.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_AXE.get())) || ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_HOE.get())) || MoreCorruptUtil.isCorruptTool(itemStack);
   }

   public static boolean isCorruptProjectile(Projectile projectile) {
      return (projectile instanceof Arrow || projectile instanceof SpectralArrow || projectile instanceof FireworkRocketEntity || MoreCorruptUtil.isCorruptProjectile(projectile)) && projectile.getTags().contains("CorruptUpgradedNetheriteBow");
   }

   public static boolean isCorruptShield(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_SHIELD.get())) || MoreCorruptUtil.isCorruptShield(itemStack);
   }

   public static boolean isCorruptHorseArmor(ItemStack itemStack) {
      return ItemStack.isSameItem(itemStack, new ItemStack((ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_HORSE_ARMOR.get())) || MoreCorruptUtil.isCorruptHorseArmor(itemStack);
   }
}
