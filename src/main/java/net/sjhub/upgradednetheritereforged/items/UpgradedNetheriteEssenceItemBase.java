package net.sjhub.upgradednetheritereforged.items;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;

public class UpgradedNetheriteEssenceItemBase extends Item {
   public UpgradedNetheriteEssenceItemBase() {
      super((new Properties()).stacksTo(16).rarity(Rarity.RARE));
   }
}
