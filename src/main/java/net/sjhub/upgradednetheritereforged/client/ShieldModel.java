package net.sjhub.upgradednetheritereforged.client;

import net.sjhub.upgradednetheritereforged.init.ModItems;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@EventBusSubscriber(
   value = {Dist.CLIENT},
   modid = "upgradednetherite",
   bus = Bus.MOD
)
public class ShieldModel {
   @SubscribeEvent
   public static void init(FMLClientSetupEvent event) {
      event.enqueueWork(() -> {
         addShieldPropertyOverrides(new ResourceLocation("upgradednetherite", "blocking"), (stack, world, entity, seed) -> {
            return entity != null && entity.m_6117_() && entity.m_21211_() == stack ? 1.0F : 0.0F;
         }, (ItemLike)ModItems.NETHERITE_SHIELD.get(), (ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_SHIELD.get(), (ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_SHIELD.get(), (ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_SHIELD.get(), (ItemLike)ModItems.WATER_UPGRADED_NETHERITE_SHIELD.get(), (ItemLike)ModItems.WITHER_UPGRADED_NETHERITE_SHIELD.get(), (ItemLike)ModItems.POISON_UPGRADED_NETHERITE_SHIELD.get(), (ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_SHIELD.get(), (ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_SHIELD.get(), (ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_SHIELD.get(), (ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_SHIELD.get());
      });
   }

   private static void addShieldPropertyOverrides(ResourceLocation override, ClampedItemPropertyFunction propertyGetter, ItemLike... shields) {
      ItemLike[] var3 = shields;
      int var4 = shields.length;

      for(int var5 = 0; var5 < var4; ++var5) {
         ItemLike shield = var3[var5];
         ItemProperties.register(shield.m_5456_(), override, propertyGetter);
      }

   }
}
