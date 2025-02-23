package net.sjhub.upgradednetheritereforged.utils;

import net.sjhub.upgradednetheritereforged.config.UpgradedNetheriteConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;

public class EntityDataUtil {
   public static void setAbilityEnderPos(Entity entity, Boolean bool) {
      if (bool) {
         if (getAbilityEnderPos(entity) == null || !(new BlockPos(getAbilityEnderPos(entity))).equals(entity.blockPosition())) {
            entity.getPersistentData().putIntArray("upgraded_netherite_ender_pos", new int[]{entity.blockPosition().getX(), entity.blockPosition().getY(), entity.blockPosition().getZ()});
         }
      } else {
         entity.getPersistentData().remove("upgraded_netherite_ender_pos");
      }

   }

   public static BlockPos getAbilityEnderPos(Entity entity) {
      return entity.getPersistentData().contains("upgraded_netherite_ender_pos") ? new BlockPos(entity.getPersistentData().getIntArray("upgraded_netherite_ender_pos")[0], entity.getPersistentData().getIntArray("upgraded_netherite_ender_pos")[1], entity.getPersistentData().getIntArray("upgraded_netherite_ender_pos")[2]) : null;
   }

   public static void setAbilityClimbwall(Entity entity, Boolean bool) {
      entity.getPersistentData().putBoolean("upgraded_netherite_climbstick", bool);
   }

   public static boolean getAbilityClimbwall(Entity entity) {
      return entity.getPersistentData().contains("upgraded_netherite_climbstick") && entity.getPersistentData().getBoolean("upgraded_netherite_climbstick");
   }

   public static void setAbilityStepHeight(Entity entity, Boolean bool) {
      entity.getPersistentData().putBoolean("upgraded_netherite_stepheight", bool);
   }

   public static boolean getAbilityStepHeight(Entity entity) {
      return entity.getPersistentData().contains("upgraded_netherite_stepheight") && entity.getPersistentData().getBoolean("upgraded_netherite_stepheight");
   }

   public static void setAbilityMultiJump(Entity entity, Boolean bool) {
      if (bool) {
         entity.getPersistentData().putInt("upgraded_netherite_multi_jump", UpgradedNetheriteConfig.MultiJump);
      } else {
         entity.getPersistentData().putInt("upgraded_netherite_multi_jump", 0);
      }

   }

   public static void decreaseAbilityMultiJump(Entity entity) {
      entity.getPersistentData().putInt("upgraded_netherite_multi_jump", entity.getPersistentData().getInt("upgraded_netherite_multi_jump") - 1);
   }

   public static boolean getAbilityMultiJump(Entity entity) {
      return entity.getPersistentData().contains("upgraded_netherite_multi_jump") && entity.getPersistentData().getInt("upgraded_netherite_multi_jump") > 0;
   }

   public static void setCorrupterite(Entity entity, Integer integer) {
      if (integer > 0) {
         entity.getPersistentData().putInt("upgraded_netherite_corrupterite", integer);
      } else {
         entity.getPersistentData().remove("upgraded_netherite_corrupterite");
      }

   }

   public static int getCorrupterite(Entity entity) {
      return entity.getPersistentData().contains("upgraded_netherite_corrupterite") ? entity.getPersistentData().getInt("upgraded_netherite_corrupterite") : 0;
   }

   public static void setMalusCorruption(Entity entity, Integer integer) {
      if (integer > 0) {
         entity.getPersistentData().putInt("upgraded_netherite_corruption", integer);
      } else {
         entity.getPersistentData().remove("upgraded_netherite_corruption");
      }

   }

   public static int getMalusCorruption(Entity entity) {
      return entity.getPersistentData().contains("upgraded_netherite_corruption") ? entity.getPersistentData().getInt("upgraded_netherite_corruption") : 0;
   }

   public static void tickCooldown(Player player) {
      if (player.getPersistentData().contains("upgraded_netherite_fire_durability_loss") && player.getPersistentData().getInt("upgraded_netherite_fire_durability_loss") > 0) {
         player.getPersistentData().putFloat("upgraded_netherite_fire_durability_loss", (float)(player.getPersistentData().getInt("upgraded_netherite_fire_durability_loss") - 1));
      }

      if (player.getPersistentData().contains("upgraded_netherite_fire_durability_loss") && player.getPersistentData().getInt("upgraded_netherite_fire_durability_loss") <= 0) {
         player.getPersistentData().remove("upgraded_netherite_fire_durability_loss");
      }

      if (player.getPersistentData().contains("upgraded_netherite_water_durability_loss") && player.getPersistentData().getInt("upgraded_netherite_water_durability_loss") > 0) {
         player.getPersistentData().putFloat("upgraded_netherite_water_durability_loss", (float)(player.getPersistentData().getInt("upgraded_netherite_water_durability_loss") - 1));
      }

      if (player.getPersistentData().contains("upgraded_netherite_water_durability_loss") && player.getPersistentData().getInt("upgraded_netherite_water_durability_loss") <= 0) {
         player.getPersistentData().remove("upgraded_netherite_water_durability_loss");
      }

      if (player.getPersistentData().contains("upgraded_netherite_wither_durability_loss") && player.getPersistentData().getInt("upgraded_netherite_wither_durability_loss") > 0) {
         player.getPersistentData().putFloat("upgraded_netherite_wither_durability_loss", (float)(player.getPersistentData().getInt("upgraded_netherite_wither_durability_loss") - 1));
      }

      if (player.getPersistentData().contains("upgraded_netherite_wither_durability_loss") && player.getPersistentData().getInt("upgraded_netherite_wither_durability_loss") <= 0) {
         player.getPersistentData().remove("upgraded_netherite_wither_durability_loss");
      }

      if (player.getPersistentData().contains("upgraded_netherite_poison_durability_loss") && player.getPersistentData().getInt("upgraded_netherite_poison_durability_loss") > 0) {
         player.getPersistentData().putFloat("upgraded_netherite_poison_durability_loss", (float)(player.getPersistentData().getInt("upgraded_netherite_poison_durability_loss") - 1));
      }

      if (player.getPersistentData().contains("upgraded_netherite_poison_durability_loss") && player.getPersistentData().getInt("upgraded_netherite_poison_durability_loss") <= 0) {
         player.getPersistentData().remove("upgraded_netherite_poison_durability_loss");
      }

      if (player.getPersistentData().contains("upgraded_netherite_feather_durability_loss") && player.getPersistentData().getInt("upgraded_netherite_feather_durability_loss") > 0) {
         player.getPersistentData().putFloat("upgraded_netherite_feather_durability_loss", (float)(player.getPersistentData().getInt("upgraded_netherite_feather_durability_loss") - 1));
      }

      if (player.getPersistentData().contains("upgraded_netherite_feather_durability_loss") && player.getPersistentData().getInt("upgraded_netherite_feather_durability_loss") <= 0) {
         player.getPersistentData().remove("upgraded_netherite_feather_durability_loss");
      }

      if (player.getPersistentData().contains("upgraded_netherite_corrupt_durability_gain") && player.getPersistentData().getInt("upgraded_netherite_corrupt_durability_gain") > 0) {
         player.getPersistentData().putFloat("upgraded_netherite_corrupt_durability_gain", (float)(player.getPersistentData().getInt("upgraded_netherite_corrupt_durability_gain") - 1));
      }

      if (player.getPersistentData().contains("upgraded_netherite_corrupt_durability_gain") && player.getPersistentData().getInt("upgraded_netherite_corrupt_durability_gain") <= 0) {
         player.getPersistentData().remove("upgraded_netherite_corrupt_durability_gain");
      }

      if (player.getPersistentData().contains("upgraded_netherite_ender_teleport_cd") && player.getPersistentData().getInt("upgraded_netherite_ender_teleport_cd") > 0) {
         player.getPersistentData().putFloat("upgraded_netherite_ender_teleport_cd", (float)(player.getPersistentData().getInt("upgraded_netherite_ender_teleport_cd") - 1));
      }

      if (player.getPersistentData().contains("upgraded_netherite_ender_teleport_cd") && player.getPersistentData().getInt("upgraded_netherite_ender_teleport_cd") <= 0) {
         player.getPersistentData().remove("upgraded_netherite_ender_teleport_cd");
      }

      if (player.getPersistentData().contains("upgraded_netherite_echo_heal_cd") && player.getPersistentData().getInt("upgraded_netherite_echo_heal_cd") > 0) {
         player.getPersistentData().putFloat("upgraded_netherite_echo_heal_cd", (float)(player.getPersistentData().getInt("upgraded_netherite_echo_heal_cd") - 1));
      }

      if (player.getPersistentData().contains("upgraded_netherite_echo_heal_cd") && player.getPersistentData().getInt("upgraded_netherite_echo_heal_cd") <= 0) {
         player.getPersistentData().remove("upgraded_netherite_echo_heal_cd");
      }

   }

   public static void tickCooldownHorse(Horse horse) {
      if (horse.getPersistentData().contains("upgraded_netherite_echo_heal_cd") && horse.getPersistentData().getInt("upgraded_netherite_echo_heal_cd") > 0) {
         horse.getPersistentData().putFloat("upgraded_netherite_echo_heal_cd", (float)(horse.getPersistentData().getInt("upgraded_netherite_echo_heal_cd") - 1));
      }

      if (horse.getPersistentData().contains("upgraded_netherite_echo_heal_cd") && horse.getPersistentData().getInt("upgraded_netherite_echo_heal_cd") <= 0) {
         horse.getPersistentData().remove("upgraded_netherite_echo_heal_cd");
      }

   }

   public static boolean hasFireDurabilityCooldown(Player player) {
      return player.getPersistentData().contains("upgraded_netherite_fire_durability_loss") && player.getPersistentData().getInt("upgraded_netherite_fire_durability_loss") > 0;
   }

   public static boolean hasWaterDurabilityCooldown(Player player) {
      return player.getPersistentData().contains("upgraded_netherite_water_durability_loss") && player.getPersistentData().getInt("upgraded_netherite_water_durability_loss") > 0;
   }

   public static boolean hasWitherDurabilityCooldown(Player player) {
      return player.getPersistentData().contains("upgraded_netherite_wither_durability_loss") && player.getPersistentData().getInt("upgraded_netherite_wither_durability_loss") > 0;
   }

   public static boolean hasPoisonDurabilityCooldown(Player player) {
      return player.getPersistentData().contains("upgraded_netherite_poison_durability_loss") && player.getPersistentData().getInt("upgraded_netherite_poison_durability_loss") > 0;
   }

   public static boolean hasFeatherDurabilityCooldown(Player player) {
      return player.getPersistentData().contains("upgraded_netherite_feather_durability_loss") && player.getPersistentData().getInt("upgraded_netherite_feather_durability_loss") > 0;
   }

   public static boolean hasCorruptDurabilityCooldown(Player player) {
      return player.getPersistentData().contains("upgraded_netherite_corrupt_durability_gain") && player.getPersistentData().getInt("upgraded_netherite_corrupt_durability_gain") > 0;
   }

   public static boolean hasEnderTeleportCooldown(Player player) {
      return player.getPersistentData().contains("upgraded_netherite_ender_teleport_cd") && player.getPersistentData().getInt("upgraded_netherite_ender_teleport_cd") > 0;
   }

   public static boolean hasEchoHealCooldown(Player player) {
      return player.getPersistentData().contains("upgraded_netherite_echo_heal_cd") && player.getPersistentData().getInt("upgraded_netherite_echo_heal_cd") > 0;
   }

   public static boolean hasEchoHealCooldownHorse(Horse horse) {
      return horse.getPersistentData().contains("upgraded_netherite_echo_heal_cd") && horse.getPersistentData().getInt("upgraded_netherite_echo_heal_cd") > 0;
   }
}
