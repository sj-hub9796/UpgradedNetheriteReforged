package net.sjhub.upgradednetheritereforged.config;

import net.minecraftforge.fml.config.ModConfig;

public final class ConfigHelper {
   public static void bakeClient(ModConfig config) {
      UpgradedNetheriteConfig.DisableTooltips = (Boolean)ConfigHolder.CLIENT.DisableTooltips.get();
      UpgradedNetheriteConfig.AltTextures = (Boolean)ConfigHolder.CLIENT.AltTextures.get();
      UpgradedNetheriteConfig.OldTextures = (Boolean)ConfigHolder.CLIENT.OldTextures.get();
   }

   public static void bakeServer(ModConfig config) {
      UpgradedNetheriteConfig.UpgradedPlayerGoldArmorRequireSet = (Integer)ConfigHolder.SERVER.UpgradedPlayerGoldArmorRequireSet.get();
      UpgradedNetheriteConfig.UpgradedPlayerFireArmorRequireSet = (Integer)ConfigHolder.SERVER.UpgradedPlayerFireArmorRequireSet.get();
      UpgradedNetheriteConfig.UpgradedPlayerEnderArmorRequireSet = (Integer)ConfigHolder.SERVER.UpgradedPlayerEnderArmorRequireSet.get();
      UpgradedNetheriteConfig.UpgradedPlayerWaterArmorRequireSet = (Integer)ConfigHolder.SERVER.UpgradedPlayerWaterArmorRequireSet.get();
      UpgradedNetheriteConfig.UpgradedPlayerWitherArmorRequireSet = (Integer)ConfigHolder.SERVER.UpgradedPlayerWitherArmorRequireSet.get();
      UpgradedNetheriteConfig.UpgradedPlayerPoisonArmorRequireSet = (Integer)ConfigHolder.SERVER.UpgradedPlayerPoisonArmorRequireSet.get();
      UpgradedNetheriteConfig.UpgradedPlayerPhantomArmorRequireSet = (Integer)ConfigHolder.SERVER.UpgradedPlayerPhantomArmorRequireSet.get();
      UpgradedNetheriteConfig.UpgradedPlayerFeatherArmorRequireSet = (Integer)ConfigHolder.SERVER.UpgradedPlayerFeatherArmorRequireSet.get();
      UpgradedNetheriteConfig.UpgradedPlayerEchoArmorRequireSet = (Integer)ConfigHolder.SERVER.UpgradedPlayerEchoArmorRequireSet.get();
      UpgradedNetheriteConfig.EnablePiglinNeutral = (Boolean)ConfigHolder.SERVER.EnablePiglinNeutral.get();
      UpgradedNetheriteConfig.EnableLuckBonus = (Boolean)ConfigHolder.SERVER.EnableLuckBonus.get();
      UpgradedNetheriteConfig.LuckBonus = (Integer)ConfigHolder.SERVER.LuckBonus.get();
      UpgradedNetheriteConfig.EnableDamageBonusGoldWeapon = (Boolean)ConfigHolder.SERVER.EnableDamageBonusGoldWeapon.get();
      UpgradedNetheriteConfig.DamageBonusGoldWeapon = (Integer)ConfigHolder.SERVER.DamageBonusGoldWeapon.get();
      UpgradedNetheriteConfig.EnableLootingBonus = (Boolean)ConfigHolder.SERVER.EnableLootingBonus.get();
      UpgradedNetheriteConfig.LootingBonus = (Integer)ConfigHolder.SERVER.LootingBonus.get();
      UpgradedNetheriteConfig.LootingEnchantBonus = (Integer)ConfigHolder.SERVER.LootingEnchantBonus.get();
      UpgradedNetheriteConfig.EnableFortuneBonus = (Boolean)ConfigHolder.SERVER.EnableFortuneBonus.get();
      UpgradedNetheriteConfig.FortuneBonus = (Integer)ConfigHolder.SERVER.FortuneBonus.get();
      UpgradedNetheriteConfig.FortuneEnchantBonus = (Integer)ConfigHolder.SERVER.FortuneEnchantBonus.get();
      UpgradedNetheriteConfig.EnableFireImmune = (Boolean)ConfigHolder.SERVER.EnableFireImmune.get();
      UpgradedNetheriteConfig.EnableLavaSpeed = (Boolean)ConfigHolder.SERVER.EnableLavaSpeed.get();
      UpgradedNetheriteConfig.EnableDamageDurabilityFireArmor = (Boolean)ConfigHolder.SERVER.EnableDamageDurabilityFireArmor.get();
      UpgradedNetheriteConfig.DelayDamageDurabilityFireArmor = (Integer)ConfigHolder.SERVER.DelayDamageDurabilityFireArmor.get();
      UpgradedNetheriteConfig.DamageDurabilityFireArmor = (Integer)ConfigHolder.SERVER.DamageDurabilityFireArmor.get();
      UpgradedNetheriteConfig.EnableAutoSmelt = (Boolean)ConfigHolder.SERVER.EnableAutoSmelt.get();
      UpgradedNetheriteConfig.EnableAutoSmeltFortune = (Boolean)ConfigHolder.SERVER.EnableAutoSmeltFortune.get();
      UpgradedNetheriteConfig.EnableDamageBonusFireWeapon = (Boolean)ConfigHolder.SERVER.EnableDamageBonusFireWeapon.get();
      UpgradedNetheriteConfig.DamageBonusFireWeapon = (Integer)ConfigHolder.SERVER.DamageBonusFireWeapon.get();
      UpgradedNetheriteConfig.DamageBonusFireEnchantWeapon = (Integer)ConfigHolder.SERVER.DamageBonusFireEnchantWeapon.get();
      UpgradedNetheriteConfig.EnableVoidSave = (Boolean)ConfigHolder.SERVER.EnableVoidSave.get();
      UpgradedNetheriteConfig.EnablePreventAnger = (Boolean)ConfigHolder.SERVER.EnablePreventAnger.get();
      UpgradedNetheriteConfig.EnableBreakEnderArmor = (Boolean)ConfigHolder.SERVER.EnableBreakEnderArmor.get();
      UpgradedNetheriteConfig.EnableDamageBonusEnderWeapon = (Boolean)ConfigHolder.SERVER.EnableDamageBonusEnderWeapon.get();
      UpgradedNetheriteConfig.DamageBonusEnderWeapon = (Integer)ConfigHolder.SERVER.DamageBonusEnderWeapon.get();
      UpgradedNetheriteConfig.EnablePreventTeleport = (Boolean)ConfigHolder.SERVER.EnablePreventTeleport.get();
      UpgradedNetheriteConfig.EnableDoubleLootingBonusEnderWeapon = (Boolean)ConfigHolder.SERVER.EnableDoubleLootingBonusEnderWeapon.get();
      UpgradedNetheriteConfig.EnableTeleportChest = (Boolean)ConfigHolder.SERVER.EnableTeleportChest.get();
      UpgradedNetheriteConfig.EnableWaterBreath = (Boolean)ConfigHolder.SERVER.EnableWaterBreath.get();
      UpgradedNetheriteConfig.EnableWaterSpeed = (Boolean)ConfigHolder.SERVER.EnableWaterSpeed.get();
      UpgradedNetheriteConfig.EnableDamageDurabilityWaterArmor = (Boolean)ConfigHolder.SERVER.EnableDamageDurabilityWaterArmor.get();
      UpgradedNetheriteConfig.DelayDamageDurabilityWaterArmor = (Integer)ConfigHolder.SERVER.DelayDamageDurabilityWaterArmor.get();
      UpgradedNetheriteConfig.DamageDurabilityWaterArmor = (Integer)ConfigHolder.SERVER.DamageDurabilityWaterArmor.get();
      UpgradedNetheriteConfig.EnableDamageBonusWaterWeapon = (Boolean)ConfigHolder.SERVER.EnableDamageBonusWaterWeapon.get();
      UpgradedNetheriteConfig.DamageBonusWaterWeapon = (Integer)ConfigHolder.SERVER.DamageBonusWaterWeapon.get();
      UpgradedNetheriteConfig.EnableDamageBonusWaterEndermanWeapon = (Boolean)ConfigHolder.SERVER.EnableDamageBonusWaterEndermanWeapon.get();
      UpgradedNetheriteConfig.DamageBonusWaterEndermanWeapon = (Integer)ConfigHolder.SERVER.DamageBonusWaterEndermanWeapon.get();
      UpgradedNetheriteConfig.EnableMiningSpeedUnderwater = (Boolean)ConfigHolder.SERVER.EnableMiningSpeedUnderwater.get();
      UpgradedNetheriteConfig.EnableElderGuardianDebuffImmune = (Boolean)ConfigHolder.SERVER.EnableElderGuardianDebuffImmune.get();
      UpgradedNetheriteConfig.EnableWitherImmune = (Boolean)ConfigHolder.SERVER.EnableWitherImmune.get();
      UpgradedNetheriteConfig.EnableDamageDurabilityWitherArmor = (Boolean)ConfigHolder.SERVER.EnableDamageDurabilityWitherArmor.get();
      UpgradedNetheriteConfig.DelayDamageDurabilityWitherArmor = (Integer)ConfigHolder.SERVER.DelayDamageDurabilityWitherArmor.get();
      UpgradedNetheriteConfig.DamageDurabilityWitherArmor = (Integer)ConfigHolder.SERVER.DamageDurabilityWitherArmor.get();
      UpgradedNetheriteConfig.EnableDamageBonusWitherWeapon = (Boolean)ConfigHolder.SERVER.EnableDamageBonusWitherWeapon.get();
      UpgradedNetheriteConfig.DamageBonusWitherWeapon = (Integer)ConfigHolder.SERVER.DamageBonusWitherWeapon.get();
      UpgradedNetheriteConfig.EnableWitherEffect = (Boolean)ConfigHolder.SERVER.EnableWitherEffect.get();
      UpgradedNetheriteConfig.EnablePoisonImmune = (Boolean)ConfigHolder.SERVER.EnablePoisonImmune.get();
      UpgradedNetheriteConfig.EnableClimbWall = (Boolean)ConfigHolder.SERVER.EnableClimbWall.get();
      UpgradedNetheriteConfig.EnableDamageDurabilityPoisonArmor = (Boolean)ConfigHolder.SERVER.EnableDamageDurabilityPoisonArmor.get();
      UpgradedNetheriteConfig.DelayDamageDurabilityPoisonArmor = (Integer)ConfigHolder.SERVER.DelayDamageDurabilityPoisonArmor.get();
      UpgradedNetheriteConfig.DamageDurabilityPoisonArmor = (Integer)ConfigHolder.SERVER.DamageDurabilityPoisonArmor.get();
      UpgradedNetheriteConfig.EnableDamageBonusPoisonWeapon = (Boolean)ConfigHolder.SERVER.EnableDamageBonusPoisonWeapon.get();
      UpgradedNetheriteConfig.DamageBonusPoisonWeapon = (Integer)ConfigHolder.SERVER.DamageBonusPoisonWeapon.get();
      UpgradedNetheriteConfig.EnablePoisonEffect = (Boolean)ConfigHolder.SERVER.EnablePoisonEffect.get();
      UpgradedNetheriteConfig.EnableFallImmune = (Boolean)ConfigHolder.SERVER.EnableFallImmune.get();
      UpgradedNetheriteConfig.EnableStepHeight = (Boolean)ConfigHolder.SERVER.EnableStepHeight.get();
      UpgradedNetheriteConfig.EnableDamageDurabilityPhantomArmor = (Boolean)ConfigHolder.SERVER.EnableDamageDurabilityPhantomArmor.get();
      UpgradedNetheriteConfig.MultiplierDamageDurabilityPhantomArmor = (Integer)ConfigHolder.SERVER.MultiplierDamageDurabilityPhantomArmor.get();
      UpgradedNetheriteConfig.EnableDamageBonusPhantomWeapon = (Boolean)ConfigHolder.SERVER.EnableDamageBonusPhantomWeapon.get();
      UpgradedNetheriteConfig.DamageBonusPhantomWeapon = (Integer)ConfigHolder.SERVER.DamageBonusPhantomWeapon.get();
      UpgradedNetheriteConfig.EnableGlowingEffect = (Boolean)ConfigHolder.SERVER.EnableGlowingEffect.get();
      UpgradedNetheriteConfig.EnableReachEffect = (Boolean)ConfigHolder.SERVER.EnableReachEffect.get();
      UpgradedNetheriteConfig.BonusReachTool = (Integer)ConfigHolder.SERVER.BonusReachTool.get();
      UpgradedNetheriteConfig.EnableWaterLavaWalking = (Boolean)ConfigHolder.SERVER.EnableWaterLavaWalking.get();
      UpgradedNetheriteConfig.EnableMultiJump = (Boolean)ConfigHolder.SERVER.EnableMultiJump.get();
      UpgradedNetheriteConfig.MultiJump = (Integer)ConfigHolder.SERVER.MultiJump.get();
      UpgradedNetheriteConfig.EnableReduceFallDamage = (Boolean)ConfigHolder.SERVER.EnableReduceFallDamage.get();
      UpgradedNetheriteConfig.EnableWaterDamageDurabilityFeatherArmor = (Boolean)ConfigHolder.SERVER.EnableWaterDamageDurabilityFeatherArmor.get();
      UpgradedNetheriteConfig.EnableLavaDamageDurabilityFeatherArmor = (Boolean)ConfigHolder.SERVER.EnableLavaDamageDurabilityFeatherArmor.get();
      UpgradedNetheriteConfig.EnableFallDamageDurabilityFeatherArmor = (Boolean)ConfigHolder.SERVER.EnableFallDamageDurabilityFeatherArmor.get();
      UpgradedNetheriteConfig.DelayDamageDurabilityFeatherArmor = (Integer)ConfigHolder.SERVER.DelayDamageDurabilityFeatherArmor.get();
      UpgradedNetheriteConfig.DamageDurabilityFeatherArmor = (Integer)ConfigHolder.SERVER.DamageDurabilityFeatherArmor.get();
      UpgradedNetheriteConfig.EnableAttractItem = (Boolean)ConfigHolder.SERVER.EnableAttractItem.get();
      UpgradedNetheriteConfig.EnableLevitationImmune = (Boolean)ConfigHolder.SERVER.EnableLevitationImmune.get();
      UpgradedNetheriteConfig.EnableHealthMalus = (Boolean)ConfigHolder.SERVER.EnableHealthMalus.get();
      UpgradedNetheriteConfig.HealthMalus = (Integer)ConfigHolder.SERVER.HealthMalus.get();
      UpgradedNetheriteConfig.EnableDamageBonusCorruptWeapon = (Boolean)ConfigHolder.SERVER.EnableDamageBonusCorruptWeapon.get();
      UpgradedNetheriteConfig.DamageBonusCorruptWeapon = (Integer)ConfigHolder.SERVER.DamageBonusCorruptWeapon.get();
      UpgradedNetheriteConfig.EnableLootingBonusCorruptWeapon = (Boolean)ConfigHolder.SERVER.EnableLootingBonusCorruptWeapon.get();
      UpgradedNetheriteConfig.LootingBonusCorruptWeapon = (Integer)ConfigHolder.SERVER.LootingBonusCorruptWeapon.get();
      UpgradedNetheriteConfig.EnableFortuneBonusCorruptTool = (Boolean)ConfigHolder.SERVER.EnableFortuneBonusCorruptTool.get();
      UpgradedNetheriteConfig.FortuneBonusCorruptTool = (Integer)ConfigHolder.SERVER.FortuneBonusCorruptTool.get();
      UpgradedNetheriteConfig.EnableSoulbound = (Boolean)ConfigHolder.SERVER.EnableSoulbound.get();
      UpgradedNetheriteConfig.EnableKeepItemsChance = (Boolean)ConfigHolder.SERVER.EnableKeepItemsChance.get();
      UpgradedNetheriteConfig.KeepItemsChance = (Integer)ConfigHolder.SERVER.KeepItemsChance.get();
      UpgradedNetheriteConfig.EnableReduceDamageEchoArmor = (Boolean)ConfigHolder.SERVER.EnableReduceDamageEchoArmor.get();
      UpgradedNetheriteConfig.ReduceDamageEchoArmor = (Integer)ConfigHolder.SERVER.ReduceDamageEchoArmor.get();
      UpgradedNetheriteConfig.EnableHealEchoArmor = (Boolean)ConfigHolder.SERVER.EnableHealEchoArmor.get();
      UpgradedNetheriteConfig.HealEchoArmorDelay = (Integer)ConfigHolder.SERVER.HealEchoArmorDelay.get();
      UpgradedNetheriteConfig.EnableDamageBonusEchoWeapon = (Boolean)ConfigHolder.SERVER.EnableDamageBonusEchoWeapon.get();
      UpgradedNetheriteConfig.DamageBonusEchoWeapon = (Integer)ConfigHolder.SERVER.DamageBonusEchoWeapon.get();
      UpgradedNetheriteConfig.EnableBonusExpEcho = (Boolean)ConfigHolder.SERVER.EnableBonusExpEcho.get();
      UpgradedNetheriteConfig.MinExpEcho = (Integer)ConfigHolder.SERVER.MinExpEcho.get();
      UpgradedNetheriteConfig.MaxExpEcho = (Integer)ConfigHolder.SERVER.MaxExpEcho.get();
   }
}
