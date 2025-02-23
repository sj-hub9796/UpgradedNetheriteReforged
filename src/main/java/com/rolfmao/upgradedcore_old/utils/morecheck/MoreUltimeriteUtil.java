package com.rolfmao.upgradedcore_old.utils.morecheck;

//import com.rolfmao.upgradednetherite_items.init.ModItems;
import com.rolfmao.upgradedcore_old.compat.ExternalMods;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;

// TODO
public class MoreUltimeriteUtil {
   public static boolean isUltimateToggle(ItemStack itemStack) {
//      return ExternalMods.UPGRADEDNETHERITE_ITEMS.isLoaded() && ItemStack.m_41746_(itemStack, new ItemStack((ItemLike)ModItems.ULTIMATE_UPGRADED_NETHERITE_TOTEM.get())) || ExternalMods.UPGRADEDTOOLS.isLoaded() && ItemStack.m_41746_(itemStack, new ItemStack((ItemLike)com.rolfmao.upgradedtools.init.ModItems.ULTIMATE_UPGRADED_NETHERITE_HAMMER.get()));
      return false;
   }
}
