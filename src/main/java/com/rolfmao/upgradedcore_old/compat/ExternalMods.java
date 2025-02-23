package com.rolfmao.upgradedcore_old.compat;

import net.minecraftforge.fml.ModList;

public enum ExternalMods {
   UPGRADEDNETHERITE("upgradednetherite"),
   UPGRADEDNETHERITE_ITEMS("upgradednetherite_items"),
   UPGRADEDNETHERITE_ULTIMATE("upgradednetherite_ultimate"),
   UPGRADEDNETHERITE_CREATIVE("upgradednetherite_creative"),
   UPGRADEDTOOLS("upgradedtools"),
   UPGRADEDNETHERITE_AETHERITE("upgradednetherite_aetherite"),
   CURIOS("curios");

   private final boolean loaded;

   private ExternalMods(String modid) {
      this.loaded = ModList.get() != null && ModList.get().getModContainerById(modid).isPresent();
   }

   public boolean isLoaded() {
      return this.loaded;
   }

   // $FF: synthetic method
   private static ExternalMods[] $values() {
      return new ExternalMods[]{UPGRADEDNETHERITE, UPGRADEDNETHERITE_ITEMS, UPGRADEDNETHERITE_ULTIMATE, UPGRADEDNETHERITE_CREATIVE, UPGRADEDTOOLS, UPGRADEDNETHERITE_AETHERITE, CURIOS};
   }
}
