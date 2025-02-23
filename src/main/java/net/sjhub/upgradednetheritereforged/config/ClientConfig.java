package net.sjhub.upgradednetheritereforged.config;

import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.Builder;

final class ClientConfig {
   final BooleanValue DisableTooltips;
   final BooleanValue AltTextures;
   final BooleanValue OldTextures;

   ClientConfig(Builder builder) {
      builder.push("general");
      this.DisableTooltips = builder.comment("Disable Tooltips ?").define("DisableTooltips", false);
      this.AltTextures = builder.comment("Show Ressource Pack : Upgraded Netherite Alt ?").define("AltTextures", false);
      this.OldTextures = builder.comment("Show Ressource Pack : Upgraded Netherite Old ?").define("OldTextures", false);
      builder.pop();
   }
}
