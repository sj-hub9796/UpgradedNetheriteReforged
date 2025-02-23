package com.rolfmao.upgradedcore_old.client;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CrossbowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public final class CrossBowModel {
   public static void setupCrossBowModelProperties(Item crossbow) {
      ItemProperties.register(crossbow, new ResourceLocation("pull"), (p0, p1, p2, p3) -> {
         if (p2 == null) {
            return 0.0F;
         } else {
            return CrossbowItem.isCharged(p0) ? 0.0F : (float)(p0.getUseDuration() - p2.getUseItemRemainingTicks()) / (float)CrossbowItem.getChargeDuration(p0);
         }
      });
      ItemProperties.register(crossbow, new ResourceLocation("pulling"), (p0, p1, p2, p3) -> {
         return p2 != null && p2.isUsingItem() && p2.getUseItem() == p0 && !CrossbowItem.isCharged(p0) ? 1.0F : 0.0F;
      });
      ItemProperties.register(crossbow, new ResourceLocation("charged"), (p0, p1, p2, p3) -> {
         return p2 != null && CrossbowItem.isCharged(p0) ? 1.0F : 0.0F;
      });
      ItemProperties.register(crossbow, new ResourceLocation("firework"), (p0, p1, p2, p3) -> {
         return p2 != null && CrossbowItem.isCharged(p0) && CrossbowItem.containsChargedProjectile(p0, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
      });
   }
}
