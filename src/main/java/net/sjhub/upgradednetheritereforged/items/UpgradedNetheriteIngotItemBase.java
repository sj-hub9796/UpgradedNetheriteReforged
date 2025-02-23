package net.sjhub.upgradednetheritereforged.items;

import net.sjhub.upgradednetheritereforged.init.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.ItemLike;

public class UpgradedNetheriteIngotItemBase extends Item {
   public UpgradedNetheriteIngotItemBase() {
      super((new Properties()).rarity(Rarity.RARE).fireResistant());
   }

   public boolean isPiglinCurrency(ItemStack stack) {
      return ItemStack.isSameItem(stack, new ItemStack((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_INGOT.get()));
   }
}
