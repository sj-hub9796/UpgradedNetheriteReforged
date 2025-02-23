package net.sjhub.upgradednetheritereforged.handlers;

import net.sjhub.upgradednetheritereforged.config.UpgradedNetheriteConfig;
import net.sjhub.upgradednetheritereforged.init.UpgradedNetheriteEffects;
import net.sjhub.upgradednetheritereforged.utils.check.CorruptUtil;
import net.sjhub.upgradednetheritereforged.utils.check.EchoUtil;
import net.sjhub.upgradednetheritereforged.utils.check.EnderUtil;
import net.sjhub.upgradednetheritereforged.utils.check.FeatherUtil;
import net.sjhub.upgradednetheritereforged.utils.check.FireUtil;
import net.sjhub.upgradednetheritereforged.utils.check.GoldUtil;
import net.sjhub.upgradednetheritereforged.utils.check.PhantomUtil;
import net.sjhub.upgradednetheritereforged.utils.check.PoisonUtil;
import net.sjhub.upgradednetheritereforged.utils.check.WaterUtil;
import net.sjhub.upgradednetheritereforged.utils.check.WitherUtil;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.monster.Phantom;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.entity.monster.warden.Warden;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingExperienceDropEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
   modid = UpgradedNetheriteMod.MOD_ID,
   bus = Bus.FORGE
)
public class WeaponEventHandler {
   @SubscribeEvent
   public static void onLivingExperienceDrop(LivingExperienceDropEvent event) {
      if (UpgradedNetheriteConfig.EnableBonusExpEcho && event.getDroppedExperience() > 0 && event.getAttackingPlayer() != null) {
         Player player = event.getAttackingPlayer();
         if (EchoUtil.isEchoToolOrWeapon(player.getMainHandItem())) {
            int nextInt;
            int exp;
            if (UpgradedNetheriteConfig.MaxExpEcho < UpgradedNetheriteConfig.MinExpEcho) {
               exp = 0;
            } else if (UpgradedNetheriteConfig.MaxExpEcho == UpgradedNetheriteConfig.MinExpEcho) {
               exp = UpgradedNetheriteConfig.MinExpEcho;
            } else {
               nextInt = player.getRandom().nextInt(UpgradedNetheriteConfig.MaxExpEcho - UpgradedNetheriteConfig.MinExpEcho + 1);
               exp = UpgradedNetheriteConfig.MinExpEcho + nextInt;
            }

            event.setDroppedExperience(event.getOriginalExperience() + exp);
         }
      }

   }

   @SubscribeEvent(
      receiveCanceled = true,
      priority = EventPriority.LOWEST
   )
   public static void onLooting(LootingLevelEvent event) {
      if (event.getDamageSource() != null && event.getDamageSource().getEntity() instanceof ServerPlayer) {
         ServerPlayer player = (ServerPlayer)event.getDamageSource().getEntity();
         Entity target = event.getEntity();
         ItemStack heldItem = player.getMainHandItem();
         if (event.getDamageSource().getDirectEntity() instanceof Projectile) {
            Projectile projectile = (Projectile)event.getDamageSource().getDirectEntity();
            if (GoldUtil.isGoldProjectile(projectile) && UpgradedNetheriteConfig.EnableLootingBonus) {
               int EnchantBonus = event.getDamageSource().getDirectEntity().getPersistentData().getInt("LootingGoldUpgradedNetheriteBow");
               if ((float)EnchantBonus >= 3.0F) {
                  event.setLootingLevel(event.getLootingLevel() + UpgradedNetheriteConfig.LootingBonus + UpgradedNetheriteConfig.LootingEnchantBonus);
               } else {
                  event.setLootingLevel(event.getLootingLevel() + UpgradedNetheriteConfig.LootingBonus);
               }
            }

            if (CorruptUtil.isCorruptProjectile(projectile) && UpgradedNetheriteConfig.EnableLootingBonusCorruptWeapon) {
               event.setLootingLevel(event.getLootingLevel() + event.getDamageSource().getDirectEntity().getPersistentData().getInt("LootingCorruptUpgradedNetheriteBow") * UpgradedNetheriteConfig.LootingBonusCorruptWeapon);
            }

            if (EnderUtil.isEnderProjectile(projectile) && target instanceof EnderMan && UpgradedNetheriteConfig.EnableDoubleLootingBonusEnderWeapon) {
               event.setLootingLevel(event.getLootingLevel() * 2);
            }
         } else if (GoldUtil.isGoldWeapon(heldItem) && UpgradedNetheriteConfig.EnableLootingBonus) {
            float EnchantBonus = 0.0F;
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(heldItem);
            if (!enchantments.isEmpty() && enchantments.containsKey(Enchantments.MOB_LOOTING)) {
               int EnchantLevel = (Integer)enchantments.get(Enchantments.MOB_LOOTING);
               EnchantBonus = (float)EnchantLevel;
            }

            if (EnchantBonus >= 3.0F) {
               event.setLootingLevel(event.getLootingLevel() + UpgradedNetheriteConfig.LootingBonus + UpgradedNetheriteConfig.LootingEnchantBonus);
            } else {
               event.setLootingLevel(event.getLootingLevel() + UpgradedNetheriteConfig.LootingBonus);
            }
         } else if (CorruptUtil.isCorruptWeapon(heldItem) && UpgradedNetheriteConfig.EnableLootingBonusCorruptWeapon) {
            event.setLootingLevel(event.getLootingLevel() + CorruptUtil.intWearingCorrupt(player, true) * UpgradedNetheriteConfig.LootingBonusCorruptWeapon);
         }

         if (EnderUtil.isEnderWeapon(heldItem) && target instanceof EnderMan && UpgradedNetheriteConfig.EnableDoubleLootingBonusEnderWeapon) {
            event.setLootingLevel(event.getLootingLevel() * 2);
         }
      }

   }

   @SubscribeEvent(
      receiveCanceled = true,
      priority = EventPriority.HIGHEST
   )
   public static void onAttackEntityEvent(AttackEntityEvent event) {
      if (!event.getEntity().level().isClientSide) {
         Player player = event.getEntity();
         Entity target = event.getTarget();
         ItemStack heldItem = player.getMainHandItem();
         if (target instanceof EnderMan && EnderUtil.isEnderWeapon(heldItem) || !target.fireImmune() && FireUtil.isFireWeapon(heldItem) || (target.fireImmune() && !(target instanceof WitherBoss) && !(target instanceof EnderDragon) || target instanceof EnderMan) && WaterUtil.isWaterWeapon(heldItem) || target instanceof Phantom && PhantomUtil.isPhantomWeapon(heldItem) || target instanceof PiglinBrute && GoldUtil.isGoldWeapon(heldItem) || CorruptUtil.intWearingCorrupt(player, true) > 0 && CorruptUtil.isCorruptWeapon(heldItem) || target instanceof Warden && EchoUtil.isEchoWeapon(heldItem)) {
            player.getPersistentData().putFloat("upgraded_netherite_bonus_damage", player.getAttackStrengthScale(0.0F));
         }

         if ((double)player.getAttackStrengthScale(0.0F) >= 1.0D && (EnderUtil.isEnderWeapon(heldItem) || WitherUtil.isWitherWeapon(heldItem) || PoisonUtil.isPoisonWeapon(heldItem))) {
            player.getPersistentData().putBoolean("upgraded_netherite_fullcharged_attack", true);
         }

      }
   }

   @SubscribeEvent(
      receiveCanceled = true
   )
   public static void onDamageEntity(LivingHurtEvent event) {
      if (event.getSource().getEntity() instanceof Player) {
         if (event.getEntity().level().isClientSide) {
            return;
         }

         ServerPlayer player = (ServerPlayer)event.getSource().getEntity();
         ItemStack heldItem = player.getMainHandItem();
         LivingEntity target = event.getEntity();
         float bonusDamage = 0.0F;
         if (event.getSource().getDirectEntity() instanceof Projectile) {
            Projectile projectile = (Projectile)event.getSource().getDirectEntity();
            if (target instanceof PiglinBrute && GoldUtil.isGoldProjectile(projectile) && UpgradedNetheriteConfig.EnableDamageBonusGoldWeapon) {
               bonusDamage += (float)UpgradedNetheriteConfig.DamageBonusGoldWeapon;
            }

            if (target.isOnFire() && FireUtil.isFireProjectile(projectile) && UpgradedNetheriteConfig.EnableDamageBonusFireWeapon) {
               if (projectile.getTags().contains("FlameFireUpgradedNetheriteBow")) {
                  bonusDamage += (float)(UpgradedNetheriteConfig.DamageBonusFireWeapon + UpgradedNetheriteConfig.DamageBonusFireEnchantWeapon);
               } else {
                  bonusDamage += (float)UpgradedNetheriteConfig.DamageBonusFireWeapon;
               }
            }

            if (target.fireImmune() && WaterUtil.isWaterProjectile(projectile)) {
               bonusDamage += (float)UpgradedNetheriteConfig.DamageBonusWaterWeapon;
            }

            if (WitherUtil.isWitherProjectile(projectile)) {
               if (target.hasEffect(MobEffects.WITHER) && UpgradedNetheriteConfig.EnableDamageBonusWitherWeapon) {
                  bonusDamage += (float)UpgradedNetheriteConfig.DamageBonusWitherWeapon;
               }

               if ((double)event.getSource().getDirectEntity().getPersistentData().getFloat("getPowerForTime") == 1.0D && UpgradedNetheriteConfig.EnableWitherEffect) {
                  event.getEntity().addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 0, false, true, true));
               }
            }

            if (PoisonUtil.isPoisonProjectile(projectile)) {
               if (target.hasEffect(MobEffects.POISON) && UpgradedNetheriteConfig.EnableDamageBonusPoisonWeapon) {
                  bonusDamage += (float)UpgradedNetheriteConfig.DamageBonusPoisonWeapon;
               }

               if ((double)event.getSource().getDirectEntity().getPersistentData().getFloat("getPowerForTime") == 1.0D && UpgradedNetheriteConfig.EnablePoisonEffect) {
                  event.getEntity().addEffect(new MobEffectInstance(MobEffects.POISON, 140, 0, false, true, true));
               }
            }

            if (target instanceof Phantom && PhantomUtil.isPhantomProjectile(projectile) && UpgradedNetheriteConfig.EnableDamageBonusPhantomWeapon) {
               bonusDamage += (float)UpgradedNetheriteConfig.DamageBonusPhantomWeapon;
            }

            if (FeatherUtil.isFeatherProjectile(projectile)) {
               event.getEntity().addEffect(new MobEffectInstance((MobEffect)UpgradedNetheriteEffects.ATTRACTION.get(), 200, 0, false, true, true));
            }

            if (UpgradedNetheriteConfig.EnableDamageBonusCorruptWeapon && CorruptUtil.isCorruptProjectile(projectile) && projectile.getPersistentData().contains("LootingCorruptUpgradedNetheriteBow") && projectile.getPersistentData().getInt("LootingCorruptUpgradedNetheriteBow") > 0) {
               bonusDamage += (float)(UpgradedNetheriteConfig.DamageBonusCorruptWeapon * event.getSource().getDirectEntity().getPersistentData().getInt("LootingCorruptUpgradedNetheriteBow"));
            }

            if (target instanceof Warden && EchoUtil.isEchoProjectile(projectile) && UpgradedNetheriteConfig.EnableDamageBonusEchoWeapon) {
               bonusDamage += (float)UpgradedNetheriteConfig.DamageBonusEchoWeapon;
            }

            if (bonusDamage > 0.0F) {
               if (event.getSource().getDirectEntity().getPersistentData().contains("getPowerForTime") && event.getSource().getDirectEntity().getPersistentData().getFloat("getPowerForTime") < 1.0F) {
                  bonusDamage = bonusDamage * event.getSource().getDirectEntity().getPersistentData().getFloat("getPowerForTime") / 2.0F;
               }

               event.setAmount(event.getAmount() + event.getAmount() * (bonusDamage / 100.0F));
            }
         } else {
            if (target instanceof PiglinBrute && GoldUtil.isGoldMeleeWeapon(heldItem) && UpgradedNetheriteConfig.EnableDamageBonusGoldWeapon) {
               bonusDamage += (float)UpgradedNetheriteConfig.DamageBonusGoldWeapon;
               if (heldItem.getOrCreateTag().contains("shield_bonusdamage")) {
                  bonusDamage += heldItem.getOrCreateTag().getFloat("shield_bonusdamage");
                  heldItem.getOrCreateTag().remove("shield_bonusdamage");
               }
            }

            if (target.isOnFire() && FireUtil.isFireMeleeWeapon(heldItem) && UpgradedNetheriteConfig.EnableDamageBonusFireWeapon) {
               float EnchantBonus = 0.0F;
               Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(heldItem);
               if (!enchantments.isEmpty() && enchantments.containsKey(Enchantments.FIRE_ASPECT)) {
                  int EnchantLevel = (Integer)enchantments.get(Enchantments.FIRE_ASPECT);
                  EnchantBonus = (float)EnchantLevel;
               }

               if (EnchantBonus >= 2.0F) {
                  bonusDamage += (float)(UpgradedNetheriteConfig.DamageBonusFireWeapon + UpgradedNetheriteConfig.DamageBonusFireEnchantWeapon);
               } else {
                  bonusDamage += (float)UpgradedNetheriteConfig.DamageBonusFireWeapon;
               }

               if (heldItem.getOrCreateTag().contains("shield_bonusdamage")) {
                  bonusDamage += heldItem.getOrCreateTag().getFloat("shield_bonusdamage");
                  heldItem.getOrCreateTag().remove("shield_bonusdamage");
               }
            }

            if (EnderUtil.isEnderMeleeWeapon(heldItem) && UpgradedNetheriteConfig.EnableDamageBonusEnderWeapon) {
               if (player.getPersistentData().contains("upgraded_netherite_fullcharged_attack") && player.getPersistentData().getBoolean("upgraded_netherite_fullcharged_attack") && UpgradedNetheriteConfig.EnablePreventTeleport) {
                  event.getEntity().addEffect(new MobEffectInstance((MobEffect)UpgradedNetheriteEffects.ENDER_ANCHOR.get(), 200, 0, false, true, true));
               }

               if (target instanceof EnderMan) {
                  bonusDamage += (float)UpgradedNetheriteConfig.DamageBonusEnderWeapon;
                  if (heldItem.getOrCreateTag().contains("shield_bonusdamage")) {
                     bonusDamage += heldItem.getOrCreateTag().getFloat("shield_bonusdamage");
                     heldItem.getOrCreateTag().remove("shield_bonusdamage");
                  }
               }
            }

            if (WaterUtil.isWaterMeleeWeapon(heldItem)) {
               if (target.fireImmune()) {
                  bonusDamage += (float)UpgradedNetheriteConfig.DamageBonusWaterWeapon;
                  if (heldItem.getOrCreateTag().contains("shield_bonusdamage")) {
                     bonusDamage += heldItem.getOrCreateTag().getFloat("shield_bonusdamage");
                     heldItem.getOrCreateTag().remove("shield_bonusdamage");
                  }
               }

               if (target instanceof EnderMan) {
                  bonusDamage += (float)UpgradedNetheriteConfig.DamageBonusWaterEndermanWeapon;
                  if (heldItem.getOrCreateTag().contains("shield_bonusdamage")) {
                     bonusDamage += heldItem.getOrCreateTag().getFloat("shield_bonusdamage");
                     heldItem.getOrCreateTag().remove("shield_bonusdamage");
                  }
               }
            }

            if (WitherUtil.isWitherMeleeWeapon(heldItem)) {
               if (target.hasEffect(MobEffects.WITHER) && UpgradedNetheriteConfig.EnableDamageBonusWitherWeapon) {
                  bonusDamage += (float)UpgradedNetheriteConfig.DamageBonusWitherWeapon;
               }

               if (player.getPersistentData().contains("upgraded_netherite_fullcharged_attack") && player.getPersistentData().getBoolean("upgraded_netherite_fullcharged_attack") && UpgradedNetheriteConfig.EnableWitherEffect) {
                  event.getEntity().addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 0, false, true, true));
               }
            }

            if (PoisonUtil.isPoisonMeleeWeapon(heldItem)) {
               if (target.hasEffect(MobEffects.POISON) && UpgradedNetheriteConfig.EnableDamageBonusPoisonWeapon) {
                  bonusDamage += (float)UpgradedNetheriteConfig.DamageBonusPoisonWeapon;
               }

               if (player.getPersistentData().contains("upgraded_netherite_fullcharged_attack") && player.getPersistentData().getBoolean("upgraded_netherite_fullcharged_attack") && UpgradedNetheriteConfig.EnablePoisonEffect) {
                  event.getEntity().addEffect(new MobEffectInstance(MobEffects.POISON, 140, 0, false, true, true));
               }
            }

            if (target instanceof Phantom && UpgradedNetheriteConfig.EnableDamageBonusPhantomWeapon && PhantomUtil.isPhantomMeleeWeapon(heldItem)) {
               bonusDamage += (float)UpgradedNetheriteConfig.DamageBonusPhantomWeapon;
               if (heldItem.getOrCreateTag().contains("shield_bonusdamage")) {
                  bonusDamage += heldItem.getOrCreateTag().getFloat("shield_bonusdamage");
                  heldItem.getOrCreateTag().remove("shield_bonusdamage");
               }
            }

            if (CorruptUtil.intWearingCorrupt(player, true) > 0 && CorruptUtil.isCorruptMeleeWeapon(heldItem) && UpgradedNetheriteConfig.EnableDamageBonusCorruptWeapon) {
               bonusDamage += (float)(UpgradedNetheriteConfig.DamageBonusCorruptWeapon * CorruptUtil.intWearingCorrupt(player, true));
               if (heldItem.getOrCreateTag().contains("shield_bonusdamage")) {
                  bonusDamage += heldItem.getOrCreateTag().getFloat("shield_bonusdamage");
                  heldItem.getOrCreateTag().remove("shield_bonusdamage");
               }
            }

            if (target instanceof Warden && UpgradedNetheriteConfig.EnableDamageBonusEchoWeapon && EchoUtil.isEchoMeleeWeapon(heldItem)) {
               bonusDamage += (float)UpgradedNetheriteConfig.DamageBonusEchoWeapon;
               if (heldItem.getOrCreateTag().contains("shield_bonusdamage")) {
                  bonusDamage += heldItem.getOrCreateTag().getFloat("shield_bonusdamage");
                  heldItem.getOrCreateTag().remove("shield_bonusdamage");
               }
            }

            if (bonusDamage > 0.0F) {
               if (player.getPersistentData().contains("upgraded_netherite_bonus_damage") && player.getPersistentData().getFloat("upgraded_netherite_bonus_damage") < 1.0F) {
                  bonusDamage = bonusDamage * player.getPersistentData().getFloat("upgraded_netherite_bonus_damage") / 2.0F;
               }

               event.setAmount(event.getAmount() + event.getAmount() * (bonusDamage / 100.0F));
            }

            if (player.getPersistentData().contains("upgraded_netherite_fullcharged_attack")) {
               player.getPersistentData().remove("upgraded_netherite_fullcharged_attack");
            }

            if (player.getPersistentData().contains("upgraded_netherite_bonus_damage")) {
               player.getPersistentData().remove("upgraded_netherite_bonus_damage");
            }
         }
      }

   }

   @SubscribeEvent(
      receiveCanceled = true,
      priority = EventPriority.HIGHEST
   )
   public void onLivingAttackEvent(LivingAttackEvent event) {
      if (event.getEntity() instanceof Player) {
         Player player = (Player)event.getEntity();
         if (event.getSource().getEntity() != null && event.getSource().getEntity() instanceof LivingEntity) {
            Entity attacker = event.getSource().getEntity();
            ItemStack stackShield = player.getUseItem();
            Item shield = stackShield.getItem();
            ItemStack heldItem = player.getMainHandItem();
            if (GoldUtil.isGoldShield(stackShield) && attacker instanceof PiglinBrute && GoldUtil.isGoldMeleeWeapon(heldItem) && player.isBlocking()) {
               if (heldItem.getOrCreateTag().contains("shield_bonusdamage")) {
                  heldItem.getOrCreateTag().putFloat("shield_bonusdamage", Math.min(heldItem.getOrCreateTag().getFloat("shield_bonusdamage") + (float)UpgradedNetheriteConfig.DamageBonusGoldWeapon / 20.0F, (float)UpgradedNetheriteConfig.DamageBonusGoldWeapon / 2.0F));
               } else {
                  heldItem.getOrCreateTag().putFloat("shield_bonusdamage", (float)UpgradedNetheriteConfig.DamageBonusGoldWeapon / 20.0F);
               }
            }

            if (FireUtil.isFireShield(stackShield) && attacker.isOnFire() && FireUtil.isFireMeleeWeapon(heldItem) && player.isBlocking()) {
               if (heldItem.getOrCreateTag().contains("shield_bonusdamage")) {
                  heldItem.getOrCreateTag().putFloat("shield_bonusdamage", Math.min(heldItem.getOrCreateTag().getFloat("shield_bonusdamage") + (float)UpgradedNetheriteConfig.DamageBonusFireWeapon / 20.0F, (float)UpgradedNetheriteConfig.DamageBonusFireWeapon / 2.0F));
               } else {
                  heldItem.getOrCreateTag().putFloat("shield_bonusdamage", (float)UpgradedNetheriteConfig.DamageBonusFireWeapon / 20.0F);
               }
            }

            if (EnderUtil.isEnderShield(stackShield) && attacker instanceof EnderMan && EnderUtil.isEnderMeleeWeapon(heldItem) && player.isBlocking()) {
               if (heldItem.getOrCreateTag().contains("shield_bonusdamage")) {
                  heldItem.getOrCreateTag().putFloat("shield_bonusdamage", Math.min(heldItem.getOrCreateTag().getFloat("shield_bonusdamage") + (float)UpgradedNetheriteConfig.DamageBonusEnderWeapon / 20.0F, (float)UpgradedNetheriteConfig.DamageBonusEnderWeapon / 2.0F));
               } else {
                  heldItem.getOrCreateTag().putFloat("shield_bonusdamage", (float)UpgradedNetheriteConfig.DamageBonusEnderWeapon / 20.0F);
               }
            }

            if (WaterUtil.isWaterShield(stackShield) && (attacker.fireImmune() || attacker instanceof EnderMan) && WaterUtil.isWaterMeleeWeapon(heldItem) && player.isBlocking()) {
               if (heldItem.getOrCreateTag().contains("shield_bonusdamage")) {
                  heldItem.getOrCreateTag().putFloat("shield_bonusdamage", Math.min(heldItem.getOrCreateTag().getFloat("shield_bonusdamage") + (float)UpgradedNetheriteConfig.DamageBonusWaterWeapon / 20.0F, (float)UpgradedNetheriteConfig.DamageBonusWaterWeapon / 2.0F));
               } else {
                  heldItem.getOrCreateTag().putFloat("shield_bonusdamage", (float)UpgradedNetheriteConfig.DamageBonusWaterWeapon / 20.0F);
               }
            }

            if (WitherUtil.isWitherShield(stackShield) && WitherUtil.isWitherMeleeWeapon(heldItem) && ((LivingEntity)attacker).hasEffect(MobEffects.WITHER) && player.isBlocking()) {
               if (heldItem.getOrCreateTag().contains("shield_bonusdamage")) {
                  heldItem.getOrCreateTag().putFloat("shield_bonusdamage", Math.min(heldItem.getOrCreateTag().getFloat("shield_bonusdamage") + (float)UpgradedNetheriteConfig.DamageBonusWitherWeapon / 20.0F, (float)UpgradedNetheriteConfig.DamageBonusWitherWeapon / 2.0F));
               } else {
                  heldItem.getOrCreateTag().putFloat("shield_bonusdamage", (float)UpgradedNetheriteConfig.DamageBonusWitherWeapon / 20.0F);
               }
            }

            if (PoisonUtil.isPoisonShield(stackShield) && PoisonUtil.isPoisonMeleeWeapon(heldItem) && ((LivingEntity)attacker).hasEffect(MobEffects.POISON) && player.isBlocking()) {
               if (heldItem.getOrCreateTag().contains("shield_bonusdamage")) {
                  heldItem.getOrCreateTag().putFloat("shield_bonusdamage", Math.min(heldItem.getOrCreateTag().getFloat("shield_bonusdamage") + (float)UpgradedNetheriteConfig.DamageBonusPoisonWeapon / 20.0F, (float)UpgradedNetheriteConfig.DamageBonusPoisonWeapon / 2.0F));
               } else {
                  heldItem.getOrCreateTag().putFloat("shield_bonusdamage", (float)UpgradedNetheriteConfig.DamageBonusPoisonWeapon / 20.0F);
               }
            }

            if (PhantomUtil.isPhantomShield(stackShield) && attacker instanceof Phantom && PhantomUtil.isPhantomMeleeWeapon(heldItem) && player.isBlocking()) {
               if (heldItem.getOrCreateTag().contains("shield_bonusdamage")) {
                  heldItem.getOrCreateTag().putFloat("shield_bonusdamage", Math.min(heldItem.getOrCreateTag().getFloat("shield_bonusdamage") + (float)UpgradedNetheriteConfig.DamageBonusPhantomWeapon / 20.0F, (float)UpgradedNetheriteConfig.DamageBonusPhantomWeapon / 2.0F));
               } else {
                  heldItem.getOrCreateTag().putFloat("shield_bonusdamage", (float)UpgradedNetheriteConfig.DamageBonusPhantomWeapon / 20.0F);
               }
            }

            if (FeatherUtil.isFeatherShield(stackShield) && player.isBlocking()) {
               Double rand = Math.random();
               if (rand <= 0.5D) {
                  double px = player.getX();
                  double py = player.getY();
                  double pz = player.getZ();
                  List<Entity> entitys = player.level().getEntitiesOfClass(Entity.class, new AABB(px - 1.0D, py - 1.0D, pz - 1.0D, px + 1.0D, py + 1.0D, pz + 1.0D));
                  int bumped = 0;
                  Iterator var16 = entitys.iterator();

                  label159:
                  while(true) {
                     Entity entity;
                     do {
                        do {
                           do {
                              do {
                                 if (!var16.hasNext()) {
                                    break label159;
                                 }

                                 entity = (Entity)var16.next();
                              } while(!entity.isAlive());
                           } while(entity instanceof WitherBoss);
                        } while(entity instanceof EnderDragon);
                     } while(entity instanceof Player && !(attacker instanceof Player));

                     if (bumped > 32) {
                        break;
                     }

                     Vec3 entityVector = new Vec3(entity.getX(), entity.getY(), entity.getZ());
                     Vec3 finalVector = (new Vec3(px, py + 0.5D, pz)).subtract(entityVector);
                     Double distance = Math.sqrt(finalVector.x * finalVector.x + finalVector.y * finalVector.y + finalVector.z * finalVector.z);
                     if (distance > 1.0D) {
                        finalVector = finalVector.normalize();
                     }

                     entity.setDeltaMovement(-finalVector.x * 1.25D, 0.25D, -finalVector.z * 1.25D);
                     ++bumped;
                  }
               }
            }

            if (CorruptUtil.isCorruptShield(stackShield) && CorruptUtil.isCorruptMeleeWeapon(heldItem) && player.isBlocking()) {
               if (heldItem.getOrCreateTag().contains("shield_bonusdamage")) {
                  heldItem.getOrCreateTag().putFloat("shield_bonusdamage", Math.min(heldItem.getOrCreateTag().getFloat("shield_bonusdamage") + (float)UpgradedNetheriteConfig.DamageBonusCorruptWeapon / 20.0F, (float)UpgradedNetheriteConfig.DamageBonusCorruptWeapon / 2.0F));
               } else {
                  heldItem.getOrCreateTag().putFloat("shield_bonusdamage", (float)UpgradedNetheriteConfig.DamageBonusCorruptWeapon / 20.0F);
               }
            }

            if (EchoUtil.isEchoShield(stackShield) && attacker instanceof Warden && EchoUtil.isEchoMeleeWeapon(heldItem) && player.isBlocking()) {
               if (heldItem.getOrCreateTag().contains("shield_bonusdamage")) {
                  heldItem.getOrCreateTag().putFloat("shield_bonusdamage", Math.min(heldItem.getOrCreateTag().getFloat("shield_bonusdamage") + (float)UpgradedNetheriteConfig.DamageBonusEchoWeapon / 20.0F, (float)UpgradedNetheriteConfig.DamageBonusEchoWeapon / 2.0F));
               } else {
                  heldItem.getOrCreateTag().putFloat("shield_bonusdamage", (float)UpgradedNetheriteConfig.DamageBonusEchoWeapon / 20.0F);
               }
            }
         }
      }

   }
}
