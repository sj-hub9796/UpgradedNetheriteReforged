package net.sjhub.upgradednetheritereforged.handlers;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.sjhub.upgradednetheritereforged.UpgradedNetheriteMod;
import net.sjhub.upgradednetheritereforged.config.UpgradedNetheriteConfig;
import net.sjhub.upgradednetheritereforged.init.UpgradedNetheriteEffects;
import net.sjhub.upgradednetheritereforged.utils.DurabilityUtil;
import net.sjhub.upgradednetheritereforged.utils.EntityDataUtil;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGuiOverlayEvent.Pre;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.event.entity.living.LivingFallEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingJumpEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.event.entity.living.MobEffectEvent.Added;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
   modid = UpgradedNetheriteMod.MOD_ID,
   bus = Bus.FORGE
)
public class ArmorEventHandler {
   @SubscribeEvent(
      receiveCanceled = true,
      priority = EventPriority.LOWEST
   )
   public static void onLivingEquipmentChange(LivingEquipmentChangeEvent event) {
      if (event.getEntity() instanceof Player) {
         Player player = (Player)event.getEntity();
         if (WitherUtil.isWearingWitherArmor(player) && UpgradedNetheriteConfig.EnableWitherImmune && player.hasEffect(MobEffects.WITHER)) {
            player.removeEffect(MobEffects.WITHER);
            DurabilityUtil.WitherDurabilityLoss(player);
         }

         if (PoisonUtil.isWearingPoisonArmor(player) && UpgradedNetheriteConfig.EnablePoisonImmune && player.hasEffect(MobEffects.POISON)) {
            player.removeEffect(MobEffects.POISON);
            DurabilityUtil.PoisonDurabilityLoss(player);
         }

         if (FeatherUtil.isWearingFeatherArmor(player) && UpgradedNetheriteConfig.EnableLevitationImmune && player.hasEffect(MobEffects.LEVITATION)) {
            player.removeEffect(MobEffects.LEVITATION);
            DurabilityUtil.FeatherDurabilityLoss(player);
         }

         if (UpgradedNetheriteConfig.EnableHealthMalus) {
            if (CorruptUtil.intWearingCorrupt(player, false) > 0 && EntityDataUtil.getCorrupterite(player) < CorruptUtil.intWearingCorrupt(player, false)) {
               EntityDataUtil.setCorrupterite(player, CorruptUtil.intWearingCorrupt(player, false));
            }

            if (CorruptUtil.intWearingCorrupt(player, false) >= 0 && EntityDataUtil.getCorrupterite(player) > CorruptUtil.intWearingCorrupt(player, false) + EntityDataUtil.getMalusCorruption(player)) {
               EntityDataUtil.setMalusCorruption(player, EntityDataUtil.getCorrupterite(player) - CorruptUtil.intWearingCorrupt(player, false));
               player.addEffect(new MobEffectInstance((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get(), 12000, EntityDataUtil.getMalusCorruption(player) - 1, false, true, true));
            } else if (CorruptUtil.intWearingCorrupt(player, false) + EntityDataUtil.getMalusCorruption(player) > EntityDataUtil.getCorrupterite(player) && player.hasEffect((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get())) {
               Integer time = 0;
               time = player.getEffect((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get()).getDuration();
               player.removeEffect((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get());
               EntityDataUtil.setMalusCorruption(player, EntityDataUtil.getCorrupterite(player) - CorruptUtil.intWearingCorrupt(player, false));
               if (CorruptUtil.intWearingCorrupt(player, false) < EntityDataUtil.getCorrupterite(player)) {
                  player.addEffect(new MobEffectInstance((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get(), time, EntityDataUtil.getMalusCorruption(player) - 1, false, true, true));
               }
            }
         } else {
            EntityDataUtil.setCorrupterite(player, 0);
            EntityDataUtil.setMalusCorruption(player, 0);
            if (player.hasEffect((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get())) {
               player.removeEffect((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get());
            }
         }
      }

   }

   public static boolean enderSaveVoid(Player player) {
      Level level = player.level();
      if (!EntityDataUtil.hasEnderTeleportCooldown(player)) {
         if (EntityDataUtil.getAbilityEnderPos(player) == null) {
            if (!level.getBlockState(new BlockPos(100, 48, 0)).blocksMotion()) { //blocksMotion()) {
               level.setBlockAndUpdate(new BlockPos(100, 48, 0), Blocks.OBSIDIAN.defaultBlockState());
            }

            if (level.getBlockState(new BlockPos(100, 49, 0)) != Blocks.AIR.defaultBlockState()) {
               level.setBlockAndUpdate(new BlockPos(100, 49, 0), Blocks.AIR.defaultBlockState());
            }

            if (level.getBlockState(new BlockPos(100, 50, 0)) != Blocks.AIR.defaultBlockState()) {
               level.setBlockAndUpdate(new BlockPos(100, 50, 0), Blocks.AIR.defaultBlockState());
            }

            player.stopRiding();
            player.fallDistance = 0.0F;
            player.teleportTo(100.5D, 49.0D, 0.5D);
            player.getPersistentData().putInt("upgraded_netherite_ender_teleport_cd", 20);
            SoundEvent soundevent = SoundEvents.ENDERMAN_TELEPORT;
            player.level().playSound((Player)null, 100.5D, 49.0D, 0.5D, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
            player.playSound(soundevent, 1.0F, 1.0F);

            for(int i = 0; i < 32; ++i) {
               player.level().addParticle(ParticleTypes.PORTAL, 100.5D, 49.0D + player.getRandom().nextDouble() * 2.0D, 0.5D, player.getRandom().nextGaussian(), 0.0D, player.getRandom().nextGaussian());
            }

            return true;
         }

         BlockPos blockpos = EntityDataUtil.getAbilityEnderPos(player);
         List<BlockPos> validTpList = new ArrayList();
         if (level.getBlockState(blockpos.below()).blocksMotion() && (player.level().getFluidState(blockpos).isEmpty() || player.level().getBlockState(blockpos).is(Blocks.BUBBLE_COLUMN)) && player.level().getBlockState(blockpos).isPathfindable(player.level(), blockpos, PathComputationType.LAND) && (player.level().getFluidState(blockpos.above()).isEmpty() || player.level().getBlockState(blockpos.above()).is(Blocks.BUBBLE_COLUMN)) && player.level().getBlockState(blockpos.above()).isPathfindable(player.level(), blockpos.above(), PathComputationType.LAND)) {
            validTpList.add(blockpos.immutable());
         }

         if (validTpList.size() <= 0) {
            Iterator var4 = BlockPos.betweenClosed(blockpos.offset(-5, -5, -5), blockpos.offset(5, 5, 5)).iterator();

            label90:
            while(true) {
               BlockPos blockpos1;
               do {
                  do {
                     do {
                        do {
                           if (!var4.hasNext()) {
                              break label90;
                           }

                           blockpos1 = (BlockPos)var4.next();
                        } while(!level.getBlockState(blockpos1.below()).blocksMotion());
                     } while(!player.level().getFluidState(blockpos1).isEmpty() && !player.level().getBlockState(blockpos1).is(Blocks.BUBBLE_COLUMN));
                  } while(!player.level().getBlockState(blockpos1).isPathfindable(player.level(), blockpos1, PathComputationType.LAND));
               } while(!player.level().getFluidState(blockpos1.above()).isEmpty() && !player.level().getBlockState(blockpos1.above()).is(Blocks.BUBBLE_COLUMN));

               if (player.level().getBlockState(blockpos1.above()).isPathfindable(player.level(), blockpos1.above(), PathComputationType.LAND)) {
                  validTpList.add(blockpos1.immutable());
               }
            }
         }

         if (validTpList.size() > 0) {
            player.stopRiding();
            player.fallDistance = 0.0F;
            Integer IRNG = player.getRandom().nextInt(validTpList.size());
            player.teleportTo((double)((BlockPos)validTpList.get(IRNG)).getX() + 0.5D, (double)((BlockPos)validTpList.get(IRNG)).getY(), (double)((BlockPos)validTpList.get(IRNG)).getZ() + 0.5D);
            player.getPersistentData().putInt("upgraded_netherite_ender_teleport_cd", 20);
            SoundEvent soundevent = SoundEvents.ENDERMAN_TELEPORT;
            player.level().playSound((Player)null, (double)((BlockPos)validTpList.get(IRNG)).getX() + 0.5D, (double)((BlockPos)validTpList.get(IRNG)).getY(), (double)((BlockPos)validTpList.get(IRNG)).getZ() + 0.5D, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
            player.playSound(soundevent, 1.0F, 1.0F);

            for(int i = 0; i < 32; ++i) {
               player.level().addParticle(ParticleTypes.PORTAL, (double)((BlockPos)validTpList.get(IRNG)).getX() + 0.5D, (double)((BlockPos)validTpList.get(IRNG)).getY() + player.getRandom().nextDouble() * 2.0D, (double)((BlockPos)validTpList.get(IRNG)).getZ() + 0.5D, player.getRandom().nextGaussian(), 0.0D, player.getRandom().nextGaussian());
            }

            return true;
         }
      }

      return false;
   }

   public static void enderBreakArmor(Player player) {
      Iterable<ItemStack> armorList = player.getArmorSlots();
      if (UpgradedNetheriteConfig.EnableBreakEnderArmor) {
         Iterator var2 = armorList.iterator();

         while(var2.hasNext()) {
            ItemStack stack = (ItemStack)var2.next();
            if (!stack.isEmpty() && EnderUtil.isEnderArmor(stack)) {
               stack.hurtAndBreak(stack.getMaxDamage() - stack.getDamageValue(), player, (e) -> {
                  e.broadcastBreakEvent(((ArmorItem)stack.getItem()).getEquipmentSlot());
               });
            }
         }
      }

   }

   @SubscribeEvent(
      receiveCanceled = true,
      priority = EventPriority.LOWEST
   )
   public static void onDamageEntity(LivingHurtEvent event) {
      if (!event.getEntity().level().isClientSide) {
         if (event.getEntity() instanceof Player && "sonic_boom".equals(event.getSource().getMsgId()) && EchoUtil.isWearingEchoArmor((Player)event.getEntity()) && UpgradedNetheriteConfig.EnableReduceDamageEchoArmor) {
            float reduceDamage = (float)UpgradedNetheriteConfig.ReduceDamageEchoArmor;
            event.setAmount(event.getAmount() - event.getAmount() * Math.min(1.0F, reduceDamage / 100.0F));
         }

      }
   }

   private Multimap<Attribute, AttributeModifier> SwimAttributeMap() {
      Multimap<Attribute, AttributeModifier> attributesDefault = HashMultimap.create();
      attributesDefault.put((Attribute)ForgeMod.SWIM_SPEED.get(), new AttributeModifier(UUID.fromString("fbfd69fe-3369-11eb-adc1-0242ac120002"), "upgradednetherite_reforged:swim_bonus", 1.1D, Operation.MULTIPLY_BASE));
      return attributesDefault;
   }

   private Multimap<Attribute, AttributeModifier> HealthAttributeMap(Integer mult) {
      Multimap<Attribute, AttributeModifier> attributesDefault = HashMultimap.create();
      attributesDefault.put(Attributes.MAX_HEALTH, new AttributeModifier(UUID.fromString("7d87fb9e-d0ca-4bdd-8d00-384044b3417b"), "upgradednetherite_reforged:health_malus", (double)UpgradedNetheriteConfig.HealthMalus * -0.01D * (double)mult, Operation.MULTIPLY_TOTAL));
      return attributesDefault;
   }

   private Multimap<Attribute, AttributeModifier> LuckAttributeMap() {
      Multimap<Attribute, AttributeModifier> attributesDefault = HashMultimap.create();
      attributesDefault.put(Attributes.LUCK, new AttributeModifier(UUID.fromString("33ca3756-9ea8-41d0-898c-d93352065fbb"), "upgradednetherite_reforged:luck_bonus", (double)UpgradedNetheriteConfig.LuckBonus, Operation.ADDITION));
      return attributesDefault;
   }

   @SubscribeEvent
   public void onLivingUpdate(LivingTickEvent event) {
      if (event.getEntity() instanceof Player) {
         Player player = (Player)event.getEntity();
         BlockPos blockpos = player.blockPosition().below();
         BlockState blockstate = player.level().getBlockState(blockpos);
         if (blockstate.blocksMotion()) {
            EntityDataUtil.setAbilityEnderPos(player, true);
         }

         if (GoldUtil.isWearingGoldArmor(player) && UpgradedNetheriteConfig.EnableLuckBonus) {
            player.getAttributes().addTransientAttributeModifiers(this.LuckAttributeMap());
         } else if (!GoldUtil.isWearingGoldArmor(player)) {
            player.getAttributes().removeAttributeModifiers(this.LuckAttributeMap());
         }

         if (FeatherUtil.isWearingFeatherArmor(player) && (player.level().getFluidState(player.blockPosition()).is(FluidTags.LAVA) || player.level().getFluidState(player.blockPosition()).is(FluidTags.WATER)) && !player.isCrouching() && !player.isSwimming() && !player.isFallFlying() && UpgradedNetheriteConfig.EnableWaterLavaWalking && !player.getAbilities().flying) {
            if (!player.isInWater() && !player.isInLava()) {
               player.fallDistance = 0.0F;
               player.setOnGround(true);
               player.setDeltaMovement(player.getDeltaMovement().add(0.0D, -player.getDeltaMovement().y(), 0.0D));
               if (player.level().getFluidState(player.blockPosition()).is(FluidTags.LAVA) && UpgradedNetheriteConfig.EnableLavaDamageDurabilityFeatherArmor) {
                  DurabilityUtil.FeatherDurabilityLoss(player);
               }

               if (player.level().getFluidState(player.blockPosition()).is(FluidTags.WATER) && UpgradedNetheriteConfig.EnableWaterDamageDurabilityFeatherArmor) {
                  DurabilityUtil.FeatherDurabilityLoss(player);
               }
            } else if (!WaterUtil.isWearingWaterArmor(player) && player.getDeltaMovement().y < 0.226D && player.getDeltaMovement().y > 0.11D && (player.isEyeInFluid(FluidTags.LAVA) || player.isEyeInFluid(FluidTags.WATER)) && player.level().isClientSide && player instanceof LocalPlayer && ((LocalPlayer)player).input.jumping) {
               player.setDeltaMovement(player.getDeltaMovement().add(0.0D, (player.getDeltaMovement().y + 1.0D) * 0.015D, 0.0D));
            } else if (player.getDeltaMovement().y < 0.15D && !player.isEyeInFluid(FluidTags.LAVA) && !player.isEyeInFluid(FluidTags.WATER)) {
               player.setDeltaMovement(player.getDeltaMovement().add(0.0D, 0.15D - player.getDeltaMovement().y, 0.0D));
            }
         }

         if (player.level().isClientSide && FeatherUtil.isWearingFeatherArmor(player) && (player.onGround() || EntityDataUtil.getAbilityClimbwall(player)) && !EntityDataUtil.getAbilityMultiJump(player) && UpgradedNetheriteConfig.EnableMultiJump) {
            EntityDataUtil.setAbilityMultiJump(player, true);
         } else if (player.level().isClientSide && player instanceof LocalPlayer && ((LocalPlayer)player).input.jumping && FeatherUtil.isWearingFeatherArmor(player) && !player.getAbilities().mayfly && !player.isInWater() && !player.isInLava() && !player.onClimbable() && player.getDeltaMovement().y < 0.0D && EntityDataUtil.getAbilityMultiJump(player) && UpgradedNetheriteConfig.EnableMultiJump) {
            player.fallDistance = 0.0F;
            PlayerFallDistanceUpdateHandler.PlayerFallDistanceUpdate(player.getUUID(), player.fallDistance);
            player.jumpFromGround();
            EntityDataUtil.decreaseAbilityMultiJump(player);
         } else if ((!FeatherUtil.isWearingFeatherArmor(player) || !UpgradedNetheriteConfig.EnableMultiJump) && EntityDataUtil.getAbilityMultiJump(player)) {
            EntityDataUtil.setAbilityMultiJump(player, false);
         }

         if ((CorruptUtil.intWearingCorrupt(player, true) >= 1 || player.hasEffect((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get())) && UpgradedNetheriteConfig.EnableHealthMalus) {
            if (CorruptUtil.intWearingCorrupt(player, true) >= 1 || player.hasEffect((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get())) {
               if (player.hasEffect((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get())) {
                  player.getAttributes().addTransientAttributeModifiers(this.HealthAttributeMap(CorruptUtil.intWearingCorrupt(player, true) + player.getEffect((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get()).getAmplifier() + 1));
                  if (player.getHealth() > player.getMaxHealth()) {
                     player.setHealth(player.getMaxHealth());
                  }
               } else {
                  player.getAttributes().addTransientAttributeModifiers(this.HealthAttributeMap(CorruptUtil.intWearingCorrupt(player, true)));
                  if (player.getHealth() > player.getMaxHealth()) {
                     player.setHealth(player.getMaxHealth());
                  }
               }
            }
         } else {
            player.getAttributes().removeAttributeModifiers(this.HealthAttributeMap(0));
         }

         if (EntityDataUtil.getMalusCorruption(player) > 0 && !player.hasEffect((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get())) {
            EntityDataUtil.setMalusCorruption(player, 0);
            EntityDataUtil.setCorrupterite(player, CorruptUtil.intWearingCorrupt(player, false));
         }

         if (WaterUtil.isWearingWaterArmor(player) && UpgradedNetheriteConfig.EnableWaterBreath && player.getAirSupply() < player.getMaxAirSupply()) {
            player.setAirSupply(player.getAirSupply() + 4);
            DurabilityUtil.WaterDurabilityLoss(player);
         }

         if (PhantomUtil.isWearingPhantomArmor(player) && UpgradedNetheriteConfig.EnableStepHeight) {
            if (player.maxUpStep() < 1.0F) {
               EntityDataUtil.setAbilityStepHeight(player, true);
               player.setMaxUpStep(1.0F);
            }
         } else if (EntityDataUtil.getAbilityStepHeight(player) && player.maxUpStep() > 0.6F) {
            player.setMaxUpStep(0.6F);
            EntityDataUtil.setAbilityStepHeight(player, false);
         }

         if (CorruptUtil.intWearingCorrupt(player, false) > 0) {
            DurabilityUtil.CorruptDurabilityGain(player);
         }

         if (FireUtil.isWearingFireArmor(player) && UpgradedNetheriteConfig.EnableLavaSpeed && player.isInLava() && !player.getAbilities().flying) {
            player.setDeltaMovement(player.getDeltaMovement().multiply(1.659999966621399D, 1.0D, 1.659999966621399D));
         }

         if (WaterUtil.isWearingWaterArmor(player) && UpgradedNetheriteConfig.EnableWaterSpeed) {
            player.getAttributes().addTransientAttributeModifiers(this.SwimAttributeMap());
         } else if (!WaterUtil.isWearingWaterArmor(player)) {
            player.getAttributes().removeAttributeModifiers(this.SwimAttributeMap());
         }

         if (UpgradedNetheriteConfig.EnableClimbWall && PoisonUtil.isWearingPoisonArmor(player)) {
            if (!player.isCrouching() && EntityDataUtil.getAbilityClimbwall(player)) {
               if (player.getDeltaMovement().y() < 0.0D) {
                  player.setOnGround(true);
                  player.setDeltaMovement(player.getDeltaMovement().add(-player.getDeltaMovement().x() / 5.0D, -player.getDeltaMovement().y(), -player.getDeltaMovement().z() / 5.0D));
                  player.fallDistance = 0.0F;
               }

               if (player.getDeltaMovement().x() != 0.0D && player.getDeltaMovement().z() != 0.0D) {
                  EntityDataUtil.setAbilityClimbwall(player, false);
               }
            }

            if ((player.isCrouching() || player.onClimbable()) && EntityDataUtil.getAbilityClimbwall(player)) {
               EntityDataUtil.setAbilityClimbwall(player, false);
            }

            if (!player.isCrouching() && player.horizontalCollision && !player.isInLava() && !player.isInWater() && !player.getAbilities().flying && !player.onClimbable() && player.getDeltaMovement().y() < 0.1D) {
               Double LookAt = player.getLookAngle().y;
               if (LookAt > 0.1D) {
                  LookAt = 0.1D;
               }

               if (LookAt < -0.1D) {
                  LookAt = -0.1D;
               }

               if (player.level().isClientSide && player instanceof LocalPlayer && ((LocalPlayer)player).input.forwardImpulse < 0.0F) {
                  LookAt = LookAt * -1.0D;
               }

               player.setDeltaMovement(player.getDeltaMovement().add(-player.getDeltaMovement().x() / 5.0D, LookAt - player.getDeltaMovement().y, -player.getDeltaMovement().z() / 5.0D));
               EntityDataUtil.setAbilityClimbwall(player, true);
               player.fallDistance = 0.0F;
               if (player.level().isClientSide) {
                  PlayerFallDistanceUpdateHandler.PlayerFallDistanceUpdate(player.getUUID(), player.fallDistance);
               }
            }
         } else if (!PoisonUtil.isWearingPoisonArmor(player) && EntityDataUtil.getAbilityClimbwall(player)) {
            EntityDataUtil.setAbilityClimbwall(player, false);
         }

         if (EchoUtil.isWearingEchoArmor(player) && !EntityDataUtil.hasEchoHealCooldown(player) && player.level().getBiome(player.blockPosition()).is(Biomes.DEEP_DARK) && UpgradedNetheriteConfig.EnableHealEchoArmor && player.getHealth() < player.getMaxHealth()) {
            player.getPersistentData().putInt("upgraded_netherite_echo_heal_cd", UpgradedNetheriteConfig.HealEchoArmorDelay * 20);
            player.heal(1.0F);
         }

         EntityDataUtil.tickCooldown(player);
      }

   }

   @SubscribeEvent(
      receiveCanceled = true
   )
   public void onLivingAttackEvent(LivingAttackEvent event) {
      if (event.getEntity() instanceof Player) {
         Player player = (Player)event.getEntity();
         if (event.getSource().is(DamageTypeTags.IS_FIRE) && FireUtil.isWearingFireArmor(player)) {
            if (UpgradedNetheriteConfig.EnableFireImmune) {
               if (event.isCancelable()) {
                  event.setCanceled(true);
               }

               player.clearFire();
               DurabilityUtil.FireDurabilityLoss(player);
            }
         } else if (event.getSource().is(DamageTypes.FELL_OUT_OF_WORLD) && !player.level().isClientSide() && EnderUtil.isWearingEnderArmor(player) && EnderUtil.isVoidYLevel(player) && !player.hasEffect((MobEffect)UpgradedNetheriteEffects.ENDER_ANCHOR.get()) && UpgradedNetheriteConfig.EnableVoidSave && enderSaveVoid(player)) {
            enderBreakArmor(player);
            if (event.isCancelable()) {
               event.setCanceled(true);
            }
         }
      }

   }

   @SubscribeEvent(
      receiveCanceled = true,
      priority = EventPriority.HIGHEST
   )
   public void onEntityFall(LivingFallEvent event) {
      if (event.getEntity() instanceof Player) {
         Player player = (Player)event.getEntity();
         if (PhantomUtil.isWearingPhantomArmor(player) && UpgradedNetheriteConfig.EnableFallImmune) {
            event.setResult(Result.DENY);
            if (UpgradedNetheriteConfig.EnableDamageDurabilityPhantomArmor) {
               Iterable<ItemStack> armorList = player.getArmorSlots();
               Iterator var4 = armorList.iterator();

               while(var4.hasNext()) {
                  ItemStack stack = (ItemStack)var4.next();
                  if (!stack.isEmpty() && PhantomUtil.isPhantomArmor(stack) && (double)player.fallDistance >= 3.5D) {
                     stack.hurtAndBreak(UpgradedNetheriteConfig.MultiplierDamageDurabilityPhantomArmor * (Math.round(player.fallDistance) - 3), player, (livingEntity) -> {
                        livingEntity.broadcastBreakEvent(((ArmorItem)stack.getItem()).getEquipmentSlot());
                     });
                  }
               }
            }

            if (event.isCancelable()) {
               event.setCanceled(true);
            }
         }

         if (FeatherUtil.isWearingFeatherArmor(player) && UpgradedNetheriteConfig.EnableReduceFallDamage) {
            event.setDamageMultiplier(0.5F);
            if (UpgradedNetheriteConfig.EnableFallDamageDurabilityFeatherArmor && (double)event.getDistance() > 3.0D) {
               DurabilityUtil.FeatherDurabilityLoss(player);
            }
         }
      }

   }

   @SubscribeEvent(
      receiveCanceled = true,
      priority = EventPriority.HIGHEST
   )
   public void onApplyEffect(Added event) {
      if (event.getEntity() instanceof ServerPlayer) {
         ServerPlayer player = (ServerPlayer)event.getEntity();
         if (event.getEffectInstance().getEffect() == MobEffects.WITHER && WitherUtil.isWearingWitherArmor(player) && UpgradedNetheriteConfig.EnableWitherImmune) {
            event.setResult(Result.DENY);
            if (event.isCancelable()) {
               event.setCanceled(true);
            }

            DurabilityUtil.WitherDurabilityLoss(player);
         } else if (event.getEffectInstance().getEffect() == MobEffects.POISON && PoisonUtil.isWearingPoisonArmor(player) && UpgradedNetheriteConfig.EnablePoisonImmune) {
            event.setResult(Result.DENY);
            if (event.isCancelable()) {
               event.setCanceled(true);
            }

            DurabilityUtil.PoisonDurabilityLoss(player);
         } else if (event.getEffectInstance().getEffect() == MobEffects.LEVITATION && FeatherUtil.isWearingFeatherArmor(player) && UpgradedNetheriteConfig.EnableLevitationImmune) {
            event.setResult(Result.DENY);
            if (event.isCancelable()) {
               event.setCanceled(true);
            }

            DurabilityUtil.FeatherDurabilityLoss(player);
         }
      }

   }

   @SubscribeEvent
   public void onJump(LivingJumpEvent event) {
      if (event.getEntity() instanceof Player) {
         Player player = (Player)event.getEntity();
         EntityDataUtil.setAbilityClimbwall(player, false);
      }

   }

   @SubscribeEvent(
      receiveCanceled = true
   )
   @OnlyIn(Dist.CLIENT)
   public void onOverlayRender(Pre event) {
      Minecraft mc = Minecraft.getInstance();
      if (event.getOverlay() == VanillaGuiOverlay.AIR_LEVEL.type() && WaterUtil.isWearingWaterArmor(mc.player) && UpgradedNetheriteConfig.EnableWaterBreath && (double)mc.player.getAirSupply() >= (double)mc.player.getMaxAirSupply() * 0.966D) {
         event.setCanceled(true);
      }

   }
}
