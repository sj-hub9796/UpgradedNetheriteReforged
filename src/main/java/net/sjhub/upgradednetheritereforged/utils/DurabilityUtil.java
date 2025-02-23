package net.sjhub.upgradednetheritereforged.utils;

import net.sjhub.upgradednetheritereforged.config.UpgradedNetheriteConfig;
import net.sjhub.upgradednetheritereforged.init.ModItems;
import net.sjhub.upgradednetheritereforged.utils.check.CorruptUtil;
import net.sjhub.upgradednetheritereforged.utils.check.FeatherUtil;
import net.sjhub.upgradednetheritereforged.utils.check.FireUtil;
import net.sjhub.upgradednetheritereforged.utils.check.PoisonUtil;
import net.sjhub.upgradednetheritereforged.utils.check.WaterUtil;
import net.sjhub.upgradednetheritereforged.utils.check.WitherUtil;
import net.sjhub.upgradednetheritereforged.utils.enums.ModArmorMaterial;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

public class DurabilityUtil {
   public static void FireDurabilityLoss(Player player) {
      if (UpgradedNetheriteConfig.EnableDamageDurabilityFireArmor && !EntityDataUtil.hasFireDurabilityCooldown(player)) {
         player.getPersistentData().putInt("upgraded_netherite_fire_durability_loss", UpgradedNetheriteConfig.DelayDamageDurabilityFireArmor);
         Iterable<ItemStack> armorList = player.getArmorSlots();
         Iterator var2 = armorList.iterator();

         while(var2.hasNext()) {
            ItemStack itemStack = (ItemStack)var2.next();
            if (!itemStack.isEmpty() && itemStack.getItem() instanceof ArmorItem && FireUtil.isFireArmor(itemStack)) {
               itemStack.hurtAndBreak(UpgradedNetheriteConfig.DamageDurabilityFireArmor, player, (e) -> {
                  e.m_21166_(((ArmorItem)itemStack.getItem()).getEquipmentSlot());
               });
            }
         }
      }

   }

   public static void WaterDurabilityLoss(Player player) {
      if (UpgradedNetheriteConfig.EnableDamageDurabilityWaterArmor && !EntityDataUtil.hasWaterDurabilityCooldown(player)) {
         player.getPersistentData().putInt("upgraded_netherite_water_durability_loss", UpgradedNetheriteConfig.DelayDamageDurabilityWaterArmor);
         Iterable<ItemStack> armorList = player.getArmorSlots();
         Iterator var2 = armorList.iterator();

         while(var2.hasNext()) {
            ItemStack itemStack = (ItemStack)var2.next();
            if (!itemStack.isEmpty() && itemStack.getItem() instanceof ArmorItem && WaterUtil.isWaterArmor(itemStack)) {
               itemStack.hurtAndBreak(UpgradedNetheriteConfig.DamageDurabilityWaterArmor, player, (e) -> {
                  e.m_21166_(((ArmorItem)itemStack.getItem()).getEquipmentSlot());
               });
            }
         }
      }

   }

   public static void WitherDurabilityLoss(Player player) {
      if (UpgradedNetheriteConfig.EnableDamageDurabilityWitherArmor && !EntityDataUtil.hasWitherDurabilityCooldown(player)) {
         player.getPersistentData().putInt("upgraded_netherite_wither_durability_loss", UpgradedNetheriteConfig.DelayDamageDurabilityWitherArmor);
         Iterable<ItemStack> armorList = player.getArmorSlots();
         Iterator var2 = armorList.iterator();

         while(var2.hasNext()) {
            ItemStack itemStack = (ItemStack)var2.next();
            if (!itemStack.isEmpty() && itemStack.getItem() instanceof ArmorItem && WitherUtil.isWitherArmor(itemStack)) {
               itemStack.hurtAndBreak(UpgradedNetheriteConfig.DamageDurabilityWitherArmor, player, (e) -> {
                  e.m_21166_(((ArmorItem)itemStack.getItem()).getEquipmentSlot());
               });
            }
         }
      }

   }

   public static void PoisonDurabilityLoss(Player player) {
      if (UpgradedNetheriteConfig.EnableDamageDurabilityPoisonArmor && !EntityDataUtil.hasPoisonDurabilityCooldown(player)) {
         player.getPersistentData().putInt("upgraded_netherite_poison_durability_loss", UpgradedNetheriteConfig.DelayDamageDurabilityPoisonArmor);
         Iterable<ItemStack> armorList = player.getArmorSlots();
         Iterator var2 = armorList.iterator();

         while(var2.hasNext()) {
            ItemStack itemStack = (ItemStack)var2.next();
            if (!itemStack.isEmpty() && itemStack.getItem() instanceof ArmorItem && PoisonUtil.isPoisonArmor(itemStack)) {
               itemStack.hurtAndBreak(UpgradedNetheriteConfig.DamageDurabilityPoisonArmor, player, (e) -> {
                  e.m_21166_(((ArmorItem)itemStack.getItem()).getEquipmentSlot());
               });
            }
         }
      }

   }

   public static void FeatherDurabilityLoss(Player player) {
      if (!EntityDataUtil.hasFeatherDurabilityCooldown(player)) {
         player.getPersistentData().putInt("upgraded_netherite_feather_durability_loss", UpgradedNetheriteConfig.DelayDamageDurabilityFeatherArmor);
         Iterable<ItemStack> armorList = player.getArmorSlots();
         Iterator var2 = armorList.iterator();

         while(var2.hasNext()) {
            ItemStack itemStack = (ItemStack)var2.next();
            if (!itemStack.isEmpty() && itemStack.getItem() instanceof ArmorItem && FeatherUtil.isFeatherArmor(itemStack)) {
               itemStack.hurtAndBreak(UpgradedNetheriteConfig.DamageDurabilityFeatherArmor, player, (e) -> {
                  e.m_21166_(((ArmorItem)itemStack.getItem()).getEquipmentSlot());
               });
            }
         }
      }

   }

   public static void CorruptDurabilityGain(Player player) {
      if (!EntityDataUtil.hasCorruptDurabilityCooldown(player) && !player.f_19853_.isClientSide && player.getFoodData().getFoodLevel() >= 1) {
         List<ItemStack> CorruptList = new ArrayList();
         Iterable<ItemStack> wearedList = player.getArmorSlots();
         Iterator var3 = wearedList.iterator();

         while(var3.hasNext()) {
            ItemStack itemStack = (ItemStack)var3.next();
            if (!itemStack.isEmpty() && itemStack.getItem() instanceof ArmorItem) {
               ArmorItem stuff = (ArmorItem)itemStack.getItem();
               ArmorMaterial compareMaterial = stuff.getMaterial();
               if (compareMaterial == ModArmorMaterial.CORRUPT_UPGRADED_NETHERITE && itemStack.getDamageValue() > 0) {
                  CorruptList.add(itemStack);
               }
            }
         }

         if (CorruptUtil.isCorruptToolOrWeapon(player.getMainHandItem()) && player.getMainHandItem().getDamageValue() > 0) {
            CorruptList.add(player.getMainHandItem());
         }

         if ((CorruptUtil.isCorruptToolOrWeapon(player.m_21206_()) || ItemStack.isSame(player.m_21206_(), new ItemStack((ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_SHIELD.get()))) && player.m_21206_().getDamageValue() > 0) {
            CorruptList.add(player.m_21206_());
         }

         if (CorruptList.size() <= 0) {
            return;
         }

         int durability = 40;

         do {
            int nextInt = player.m_217043_().nextInt(CorruptList.size());
            ItemStack itemStack = (ItemStack)CorruptList.get(nextInt);
            if (itemStack.getDamageValue() >= 1) {
               player.causeFoodExhaustion(0.1F);
               itemStack.setDamageValue(itemStack.getDamageValue() - 1);
               if (itemStack.getDamageValue() == 0) {
                  CorruptList.remove(itemStack);
               }

               --durability;
            }
         } while(durability > 0 && !CorruptList.isEmpty());

         player.getPersistentData().putInt("upgraded_netherite_corrupt_durability_gain", 100);
      }

   }
}
