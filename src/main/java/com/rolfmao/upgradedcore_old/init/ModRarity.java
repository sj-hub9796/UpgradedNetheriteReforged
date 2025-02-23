package com.rolfmao.upgradedcore_old.init;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;

public class ModRarity {
   public static final Rarity UPGRADED_SET;
   public static final Rarity UPGRADED_ULTIMATE;
   public static final Rarity UPGRADED_CREATIVE;

   static {
      UPGRADED_SET = Rarity.create("SET", ChatFormatting.GREEN);
      UPGRADED_ULTIMATE = Rarity.create("ULTIMATE", ChatFormatting.RED);
      UPGRADED_CREATIVE = Rarity.create("CREATIVE", ChatFormatting.LIGHT_PURPLE);
   }
}
