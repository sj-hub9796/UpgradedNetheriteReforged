package net.sjhub.upgradednetheritereforged.client;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShieldTextures {
   public static final Material LOCATION_NETHERITE_SHIELD_BASE;
   public static final Material LOCATION_GOLD_UPGRADED_NETHERITE_SHIELD_BASE;
   public static final Material LOCATION_FIRE_UPGRADED_NETHERITE_SHIELD_BASE;
   public static final Material LOCATION_ENDER_UPGRADED_NETHERITE_SHIELD_BASE;
   public static final Material LOCATION_WATER_UPGRADED_NETHERITE_SHIELD_BASE;
   public static final Material LOCATION_WITHER_UPGRADED_NETHERITE_SHIELD_BASE;
   public static final Material LOCATION_POISON_UPGRADED_NETHERITE_SHIELD_BASE;
   public static final Material LOCATION_PHANTOM_UPGRADED_NETHERITE_SHIELD_BASE;
   public static final Material LOCATION_FEATHER_UPGRADED_NETHERITE_SHIELD_BASE;
   public static final Material LOCATION_CORRUPT_UPGRADED_NETHERITE_SHIELD_BASE;
   public static final Material LOCATION_ECHO_UPGRADED_NETHERITE_SHIELD_BASE;

   static {
      LOCATION_NETHERITE_SHIELD_BASE = new Material(Sheets.f_110738_, new ResourceLocation("upgradednetherite", "entity/shield/netherite_shield_base"));
      LOCATION_GOLD_UPGRADED_NETHERITE_SHIELD_BASE = new Material(Sheets.f_110738_, new ResourceLocation("upgradednetherite", "entity/shield/gold_upgraded_netherite_shield_base"));
      LOCATION_FIRE_UPGRADED_NETHERITE_SHIELD_BASE = new Material(Sheets.f_110738_, new ResourceLocation("upgradednetherite", "entity/shield/fire_upgraded_netherite_shield_base"));
      LOCATION_ENDER_UPGRADED_NETHERITE_SHIELD_BASE = new Material(Sheets.f_110738_, new ResourceLocation("upgradednetherite", "entity/shield/ender_upgraded_netherite_shield_base"));
      LOCATION_WATER_UPGRADED_NETHERITE_SHIELD_BASE = new Material(Sheets.f_110738_, new ResourceLocation("upgradednetherite", "entity/shield/water_upgraded_netherite_shield_base"));
      LOCATION_WITHER_UPGRADED_NETHERITE_SHIELD_BASE = new Material(Sheets.f_110738_, new ResourceLocation("upgradednetherite", "entity/shield/wither_upgraded_netherite_shield_base"));
      LOCATION_POISON_UPGRADED_NETHERITE_SHIELD_BASE = new Material(Sheets.f_110738_, new ResourceLocation("upgradednetherite", "entity/shield/poison_upgraded_netherite_shield_base"));
      LOCATION_PHANTOM_UPGRADED_NETHERITE_SHIELD_BASE = new Material(Sheets.f_110738_, new ResourceLocation("upgradednetherite", "entity/shield/phantom_upgraded_netherite_shield_base"));
      LOCATION_FEATHER_UPGRADED_NETHERITE_SHIELD_BASE = new Material(Sheets.f_110738_, new ResourceLocation("upgradednetherite", "entity/shield/feather_upgraded_netherite_shield_base"));
      LOCATION_CORRUPT_UPGRADED_NETHERITE_SHIELD_BASE = new Material(Sheets.f_110738_, new ResourceLocation("upgradednetherite", "entity/shield/corrupt_upgraded_netherite_shield_base"));
      LOCATION_ECHO_UPGRADED_NETHERITE_SHIELD_BASE = new Material(Sheets.f_110738_, new ResourceLocation("upgradednetherite", "entity/shield/echo_upgraded_netherite_shield_base"));
   }
}
