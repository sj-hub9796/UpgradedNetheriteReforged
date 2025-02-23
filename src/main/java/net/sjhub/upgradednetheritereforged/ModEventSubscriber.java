package net.sjhub.upgradednetheritereforged;

import net.sjhub.upgradednetheritereforged.config.ConfigHelper;
import net.sjhub.upgradednetheritereforged.config.ConfigHolder;
import net.sjhub.upgradednetheritereforged.utils.ShieldRecipes;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(
   modid = UpgradedNetheriteMod.MOD_ID,
   bus = Bus.MOD
)
public class ModEventSubscriber {
   private static final DeferredRegister<RecipeSerializer<?>> RECIPE;
   public static final RegistryObject<SimpleCraftingRecipeSerializer<ShieldRecipes>> SHIELD_RECIPE;

   @SubscribeEvent
   public static void onModConfigEvent(ModConfigEvent event) {
      ModConfig config = event.getConfig();
      if (config.getSpec() == ConfigHolder.CLIENT_SPEC) {
         ConfigHelper.bakeClient(config);
      } else if (config.getSpec() == ConfigHolder.SERVER_SPEC) {
         ConfigHelper.bakeServer(config);
      }

   }

   public static void create(IEventBus bus) {
      RECIPE.register(bus);
   }

   static {
      RECIPE = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, "upgradednetherite_reforged");
      SHIELD_RECIPE = RECIPE.register("shield_decoration", () -> {
         return ShieldRecipes.SERIALIZER;
      });
   }
}
