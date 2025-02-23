package com.rolfmao.upgradedcore_old.utils;

import java.util.Iterator;
import javax.annotation.Nonnull;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;

public class SetArmorUtil {
   public static Integer isWearingArmor(@Nonnull Player player, @Nonnull ArmorMaterial material) {
      Iterable<ItemStack> wearedList = player.getArmorSlots();
      Integer count = 0;
      Iterator var4 = wearedList.iterator();

      while(var4.hasNext()) {
         ItemStack stack = (ItemStack)var4.next();
         if (!stack.isEmpty() && stack.getItem() instanceof ArmorItem) {
            ArmorItem stuff = (ArmorItem)stack.getItem();
            ArmorMaterial compareMaterial = stuff.getMaterial();
            if (compareMaterial == material) {
               count = count + 1;
            }
         }
      }

      return count;
   }

   public static Integer isWearingArmor(@Nonnull Player player, @Nonnull ArmorMaterial material, @Nonnull String strDisable) {
      Iterable<ItemStack> wearedList = player.getArmorSlots();
      Integer count = 0;
      Iterator var5 = wearedList.iterator();

      while(var5.hasNext()) {
         ItemStack stack = (ItemStack)var5.next();
         if (!stack.isEmpty() && stack.getItem() instanceof ArmorItem) {
            ArmorItem stuff = (ArmorItem)stack.getItem();
            ArmorMaterial compareMaterial = stuff.getMaterial();
            if (compareMaterial == material && !stack.getOrCreateTag().contains(strDisable)) {
               count = count + 1;
            }
         }
      }

      return count;
   }
}
