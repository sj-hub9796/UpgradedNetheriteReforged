package net.sjhub.upgradednetheritereforged.items;

import net.sjhub.upgradednetheritereforged.init.ModItems;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.ItemLike;

public class UpgradedNetheriteIngotItemBase extends Item {
   public UpgradedNetheriteIngotItemBase() {
      super((new Properties()).m_41497_(Rarity.RARE).m_41486_());
   }

   public boolean isPiglinCurrency(ItemStack stack) {
      return ItemStack.m_41746_(stack, new ItemStack((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_INGOT.get()));
   }
}
