package net.sjhub.upgradednetheritereforged.init;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.event.CreativeModeTabEvent.Register;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
   modid = "upgradednetherite_reforged",
   bus = Bus.MOD
)
public class ModCreativeModeTabs {
   public static CreativeModeTab UPGRADED_NETHERITE_TAB;

   @SubscribeEvent
   public static void registerCreativeModeTabs(Register event) {
      UPGRADED_NETHERITE_TAB = event.registerCreativeModeTab(new ResourceLocation("upgradednetherite_reforged", "upgradednetherite_tab"), (builder) -> {
         builder.m_257737_(() -> {
            return new ItemStack((ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_CHESTPLATE.get());
         }).m_257941_(Component.m_237115_("itemGroup.upgradednetheriteTab"));
      });
   }
}
