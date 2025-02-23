package com.rolfmao.upgradedcore_old.client;

import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public final class BowModel {
   public static void setupBowModelProperties(Item bow) {
      ItemProperties.register(bow, new ResourceLocation("pull"), (p0, p1, p2, p3) -> {
         if (p2 == null) {
            return 0.0F;
         } else {
            return p2.getUseItem() != p0 ? 0.0F : (float)(p0.getUseDuration() - p2.getUseItemRemainingTicks()) / 20.0F;
         }
      });
      ItemProperties.register(bow, new ResourceLocation("pulling"), (p0, p1, p2, p3) -> {
         return p2 != null && p2.isUsingItem() && p2.getUseItem() == p0 ? 1.0F : 0.0F;
      });
   }
}
