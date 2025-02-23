package net.sjhub.upgradednetheritereforged.init;

import net.sjhub.upgradednetheritereforged.effects.Attraction;
import net.sjhub.upgradednetheritereforged.effects.EnderAnchor;
import net.sjhub.upgradednetheritereforged.effects.NetheriteCorruption;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@EventBusSubscriber(
   modid = "upgradednetherite_reforged",
   bus = Bus.MOD
)
public class UpgradedNetheriteEffects {
   public static final DeferredRegister<MobEffect> EFFECTS;
   public static final RegistryObject<MobEffect> ENDER_ANCHOR;
   public static final RegistryObject<MobEffect> ATTRACTION;
   public static final RegistryObject<MobEffect> NETHERITE_CORRUPTION;

   static {
      EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, "upgradednetherite_reforged");
      ENDER_ANCHOR = EFFECTS.register("ender_anchor", EnderAnchor::new);
      ATTRACTION = EFFECTS.register("attraction", Attraction::new);
      NETHERITE_CORRUPTION = EFFECTS.register("netherite_corruption", NetheriteCorruption::new);
   }
}
