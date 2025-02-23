package net.sjhub.upgradednetheritereforged.init;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import static net.sjhub.upgradednetheritereforged.UpgradedNetheriteMod.MOD_ID;

@EventBusSubscriber(
   modid = "upgradednetherite_reforged",
   bus = Bus.MOD
)
public class ModCreativeModeTabs {

   public static final DeferredRegister<CreativeModeTab> SIMPLE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);

   public static RegistryObject<CreativeModeTab> UPGRADED_NETHERITE_TAB = SIMPLE_TAB.register("upgradednetherite_tab", () ->
           CreativeModeTab.builder()
                   .title(Component.translatable("itemGroup.upgradednetheriteTab"))
                   .icon(() -> new ItemStack(ModItems.FIRE_UPGRADED_NETHERITE_CHESTPLATE.get()))
                   .build());

   public static void register(IEventBus bus) {
      SIMPLE_TAB.register(bus);
   }
}
