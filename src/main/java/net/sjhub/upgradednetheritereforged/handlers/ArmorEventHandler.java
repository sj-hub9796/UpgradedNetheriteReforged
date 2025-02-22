package net.sjhub.upgradednetheritereforged.handlers;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
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
   modid = "upgradednetherite",
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
         if (WitherUtil.isWearingWitherArmor(player) && UpgradedNetheriteConfig.EnableWitherImmune && player.m_21023_(MobEffects.f_19615_)) {
            player.m_21195_(MobEffects.f_19615_);
            DurabilityUtil.WitherDurabilityLoss(player);
         }

         if (PoisonUtil.isWearingPoisonArmor(player) && UpgradedNetheriteConfig.EnablePoisonImmune && player.m_21023_(MobEffects.f_19614_)) {
            player.m_21195_(MobEffects.f_19614_);
            DurabilityUtil.PoisonDurabilityLoss(player);
         }

         if (FeatherUtil.isWearingFeatherArmor(player) && UpgradedNetheriteConfig.EnableLevitationImmune && player.m_21023_(MobEffects.f_19620_)) {
            player.m_21195_(MobEffects.f_19620_);
            DurabilityUtil.FeatherDurabilityLoss(player);
         }

         if (UpgradedNetheriteConfig.EnableHealthMalus) {
            if (CorruptUtil.intWearingCorrupt(player, false) > 0 && EntityDataUtil.getCorrupterite(player) < CorruptUtil.intWearingCorrupt(player, false)) {
               EntityDataUtil.setCorrupterite(player, CorruptUtil.intWearingCorrupt(player, false));
            }

            if (CorruptUtil.intWearingCorrupt(player, false) >= 0 && EntityDataUtil.getCorrupterite(player) > CorruptUtil.intWearingCorrupt(player, false) + EntityDataUtil.getMalusCorruption(player)) {
               EntityDataUtil.setMalusCorruption(player, EntityDataUtil.getCorrupterite(player) - CorruptUtil.intWearingCorrupt(player, false));
               player.m_7292_(new MobEffectInstance((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get(), 12000, EntityDataUtil.getMalusCorruption(player) - 1, false, true, true));
            } else if (CorruptUtil.intWearingCorrupt(player, false) + EntityDataUtil.getMalusCorruption(player) > EntityDataUtil.getCorrupterite(player) && player.m_21023_((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get())) {
               Integer time = 0;
               time = player.m_21124_((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get()).m_19557_();
               player.m_21195_((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get());
               EntityDataUtil.setMalusCorruption(player, EntityDataUtil.getCorrupterite(player) - CorruptUtil.intWearingCorrupt(player, false));
               if (CorruptUtil.intWearingCorrupt(player, false) < EntityDataUtil.getCorrupterite(player)) {
                  player.m_7292_(new MobEffectInstance((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get(), time, EntityDataUtil.getMalusCorruption(player) - 1, false, true, true));
               }
            }
         } else {
            EntityDataUtil.setCorrupterite(player, 0);
            EntityDataUtil.setMalusCorruption(player, 0);
            if (player.m_21023_((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get())) {
               player.m_21195_((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get());
            }
         }
      }

   }

   public static boolean enderSaveVoid(Player player) {
      Level level = player.f_19853_;
      if (!EntityDataUtil.hasEnderTeleportCooldown(player)) {
         if (EntityDataUtil.getAbilityEnderPos(player) == null) {
            if (!level.m_8055_(new BlockPos(100, 48, 0)).m_60767_().m_76334_()) {
               level.m_46597_(new BlockPos(100, 48, 0), Blocks.f_50080_.m_49966_());
            }

            if (level.m_8055_(new BlockPos(100, 49, 0)) != Blocks.f_50016_.m_49966_()) {
               level.m_46597_(new BlockPos(100, 49, 0), Blocks.f_50016_.m_49966_());
            }

            if (level.m_8055_(new BlockPos(100, 50, 0)) != Blocks.f_50016_.m_49966_()) {
               level.m_46597_(new BlockPos(100, 50, 0), Blocks.f_50016_.m_49966_());
            }

            player.m_8127_();
            player.f_19789_ = 0.0F;
            player.m_6021_(100.5D, 49.0D, 0.5D);
            player.getPersistentData().m_128405_("upgraded_netherite_ender_teleport_cd", 20);
            SoundEvent soundevent = SoundEvents.f_11852_;
            player.f_19853_.m_6263_((Player)null, 100.5D, 49.0D, 0.5D, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
            player.m_5496_(soundevent, 1.0F, 1.0F);

            for(int i = 0; i < 32; ++i) {
               player.f_19853_.m_7106_(ParticleTypes.f_123760_, 100.5D, 49.0D + player.m_217043_().m_188500_() * 2.0D, 0.5D, player.m_217043_().m_188583_(), 0.0D, player.m_217043_().m_188583_());
            }

            return true;
         }

         BlockPos blockpos = EntityDataUtil.getAbilityEnderPos(player);
         List<BlockPos> validTpList = new ArrayList();
         if (level.m_8055_(blockpos.m_7495_()).m_60767_().m_76334_() && (player.f_19853_.m_6425_(blockpos).m_76178_() || player.f_19853_.m_8055_(blockpos).m_60713_(Blocks.f_50628_)) && player.f_19853_.m_8055_(blockpos).m_60647_(player.f_19853_, blockpos, PathComputationType.LAND) && (player.f_19853_.m_6425_(blockpos.m_7494_()).m_76178_() || player.f_19853_.m_8055_(blockpos.m_7494_()).m_60713_(Blocks.f_50628_)) && player.f_19853_.m_8055_(blockpos.m_7494_()).m_60647_(player.f_19853_, blockpos.m_7494_(), PathComputationType.LAND)) {
            validTpList.add(blockpos.m_7949_());
         }

         if (validTpList.size() <= 0) {
            Iterator var4 = BlockPos.m_121940_(blockpos.m_7918_(-5, -5, -5), blockpos.m_7918_(5, 5, 5)).iterator();

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
                        } while(!level.m_8055_(blockpos1.m_7495_()).m_60767_().m_76334_());
                     } while(!player.f_19853_.m_6425_(blockpos1).m_76178_() && !player.f_19853_.m_8055_(blockpos1).m_60713_(Blocks.f_50628_));
                  } while(!player.f_19853_.m_8055_(blockpos1).m_60647_(player.f_19853_, blockpos1, PathComputationType.LAND));
               } while(!player.f_19853_.m_6425_(blockpos1.m_7494_()).m_76178_() && !player.f_19853_.m_8055_(blockpos1.m_7494_()).m_60713_(Blocks.f_50628_));

               if (player.f_19853_.m_8055_(blockpos1.m_7494_()).m_60647_(player.f_19853_, blockpos1.m_7494_(), PathComputationType.LAND)) {
                  validTpList.add(blockpos1.m_7949_());
               }
            }
         }

         if (validTpList.size() > 0) {
            player.m_8127_();
            player.f_19789_ = 0.0F;
            Integer IRNG = player.m_217043_().m_188503_(validTpList.size());
            player.m_6021_((double)((BlockPos)validTpList.get(IRNG)).m_123341_() + 0.5D, (double)((BlockPos)validTpList.get(IRNG)).m_123342_(), (double)((BlockPos)validTpList.get(IRNG)).m_123343_() + 0.5D);
            player.getPersistentData().m_128405_("upgraded_netherite_ender_teleport_cd", 20);
            SoundEvent soundevent = SoundEvents.f_11852_;
            player.f_19853_.m_6263_((Player)null, (double)((BlockPos)validTpList.get(IRNG)).m_123341_() + 0.5D, (double)((BlockPos)validTpList.get(IRNG)).m_123342_(), (double)((BlockPos)validTpList.get(IRNG)).m_123343_() + 0.5D, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
            player.m_5496_(soundevent, 1.0F, 1.0F);

            for(int i = 0; i < 32; ++i) {
               player.f_19853_.m_7106_(ParticleTypes.f_123760_, (double)((BlockPos)validTpList.get(IRNG)).m_123341_() + 0.5D, (double)((BlockPos)validTpList.get(IRNG)).m_123342_() + player.m_217043_().m_188500_() * 2.0D, (double)((BlockPos)validTpList.get(IRNG)).m_123343_() + 0.5D, player.m_217043_().m_188583_(), 0.0D, player.m_217043_().m_188583_());
            }

            return true;
         }
      }

      return false;
   }

   public static void enderBreakArmor(Player player) {
      Iterable<ItemStack> armorList = player.m_6168_();
      if (UpgradedNetheriteConfig.EnableBreakEnderArmor) {
         Iterator var2 = armorList.iterator();

         while(var2.hasNext()) {
            ItemStack stack = (ItemStack)var2.next();
            if (!stack.m_41619_() && EnderUtil.isEnderArmor(stack)) {
               stack.m_41622_(stack.m_41776_() - stack.m_41773_(), player, (e) -> {
                  e.m_21166_(((ArmorItem)stack.m_41720_()).m_40402_());
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
      if (!event.getEntity().f_19853_.f_46443_) {
         if (event.getEntity() instanceof Player && "sonic_boom".equals(event.getSource().m_19385_()) && EchoUtil.isWearingEchoArmor((Player)event.getEntity()) && UpgradedNetheriteConfig.EnableReduceDamageEchoArmor) {
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
      attributesDefault.put(Attributes.f_22276_, new AttributeModifier(UUID.fromString("7d87fb9e-d0ca-4bdd-8d00-384044b3417b"), "upgradednetherite_reforged:health_malus", (double)UpgradedNetheriteConfig.HealthMalus * -0.01D * (double)mult, Operation.MULTIPLY_TOTAL));
      return attributesDefault;
   }

   private Multimap<Attribute, AttributeModifier> LuckAttributeMap() {
      Multimap<Attribute, AttributeModifier> attributesDefault = HashMultimap.create();
      attributesDefault.put(Attributes.f_22286_, new AttributeModifier(UUID.fromString("33ca3756-9ea8-41d0-898c-d93352065fbb"), "upgradednetherite_reforged:luck_bonus", (double)UpgradedNetheriteConfig.LuckBonus, Operation.ADDITION));
      return attributesDefault;
   }

   @SubscribeEvent
   public void onLivingUpdate(LivingTickEvent event) {
      if (event.getEntity() instanceof Player) {
         Player player = (Player)event.getEntity();
         BlockPos blockpos = player.m_20183_().m_7495_();
         BlockState blockstate = player.f_19853_.m_8055_(blockpos);
         if (blockstate.m_60767_().m_76334_()) {
            EntityDataUtil.setAbilityEnderPos(player, true);
         }

         if (GoldUtil.isWearingGoldArmor(player) && UpgradedNetheriteConfig.EnableLuckBonus) {
            player.m_21204_().m_22178_(this.LuckAttributeMap());
         } else if (!GoldUtil.isWearingGoldArmor(player)) {
            player.m_21204_().m_22161_(this.LuckAttributeMap());
         }

         if (FeatherUtil.isWearingFeatherArmor(player) && (player.f_19853_.m_6425_(player.m_20183_()).m_205070_(FluidTags.f_13132_) || player.f_19853_.m_6425_(player.m_20183_()).m_205070_(FluidTags.f_13131_)) && !player.m_6047_() && !player.m_6069_() && !player.m_21255_() && UpgradedNetheriteConfig.EnableWaterLavaWalking && !player.m_150110_().f_35935_) {
            if (!player.m_20069_() && !player.m_20077_()) {
               player.f_19789_ = 0.0F;
               player.m_6853_(true);
               player.m_20256_(player.m_20184_().m_82520_(0.0D, -player.m_20184_().m_7098_(), 0.0D));
               if (player.f_19853_.m_6425_(player.m_20183_()).m_205070_(FluidTags.f_13132_) && UpgradedNetheriteConfig.EnableLavaDamageDurabilityFeatherArmor) {
                  DurabilityUtil.FeatherDurabilityLoss(player);
               }

               if (player.f_19853_.m_6425_(player.m_20183_()).m_205070_(FluidTags.f_13131_) && UpgradedNetheriteConfig.EnableWaterDamageDurabilityFeatherArmor) {
                  DurabilityUtil.FeatherDurabilityLoss(player);
               }
            } else if (!WaterUtil.isWearingWaterArmor(player) && player.m_20184_().f_82480_ < 0.226D && player.m_20184_().f_82480_ > 0.11D && (player.m_204029_(FluidTags.f_13132_) || player.m_204029_(FluidTags.f_13131_)) && player.f_19853_.f_46443_ && player instanceof LocalPlayer && ((LocalPlayer)player).f_108618_.f_108572_) {
               player.m_20256_(player.m_20184_().m_82520_(0.0D, (player.m_20184_().f_82480_ + 1.0D) * 0.015D, 0.0D));
            } else if (player.m_20184_().f_82480_ < 0.15D && !player.m_204029_(FluidTags.f_13132_) && !player.m_204029_(FluidTags.f_13131_)) {
               player.m_20256_(player.m_20184_().m_82520_(0.0D, 0.15D - player.m_20184_().f_82480_, 0.0D));
            }
         }

         if (player.f_19853_.f_46443_ && FeatherUtil.isWearingFeatherArmor(player) && (player.m_20096_() || EntityDataUtil.getAbilityClimbwall(player)) && !EntityDataUtil.getAbilityMultiJump(player) && UpgradedNetheriteConfig.EnableMultiJump) {
            EntityDataUtil.setAbilityMultiJump(player, true);
         } else if (player.f_19853_.f_46443_ && player instanceof LocalPlayer && ((LocalPlayer)player).f_108618_.f_108572_ && FeatherUtil.isWearingFeatherArmor(player) && !player.m_150110_().f_35936_ && !player.m_20069_() && !player.m_20077_() && !player.m_6147_() && player.m_20184_().f_82480_ < 0.0D && EntityDataUtil.getAbilityMultiJump(player) && UpgradedNetheriteConfig.EnableMultiJump) {
            player.f_19789_ = 0.0F;
            PlayerFallDistanceUpdateHandler.PlayerFallDistanceUpdate(player.m_20148_(), player.f_19789_);
            player.m_6135_();
            EntityDataUtil.decreaseAbilityMultiJump(player);
         } else if ((!FeatherUtil.isWearingFeatherArmor(player) || !UpgradedNetheriteConfig.EnableMultiJump) && EntityDataUtil.getAbilityMultiJump(player)) {
            EntityDataUtil.setAbilityMultiJump(player, false);
         }

         if ((CorruptUtil.intWearingCorrupt(player, true) >= 1 || player.m_21023_((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get())) && UpgradedNetheriteConfig.EnableHealthMalus) {
            if (CorruptUtil.intWearingCorrupt(player, true) >= 1 || player.m_21023_((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get())) {
               if (player.m_21023_((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get())) {
                  player.m_21204_().m_22178_(this.HealthAttributeMap(CorruptUtil.intWearingCorrupt(player, true) + player.m_21124_((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get()).m_19564_() + 1));
                  if (player.m_21223_() > player.m_21233_()) {
                     player.m_21153_(player.m_21233_());
                  }
               } else {
                  player.m_21204_().m_22178_(this.HealthAttributeMap(CorruptUtil.intWearingCorrupt(player, true)));
                  if (player.m_21223_() > player.m_21233_()) {
                     player.m_21153_(player.m_21233_());
                  }
               }
            }
         } else {
            player.m_21204_().m_22161_(this.HealthAttributeMap(0));
         }

         if (EntityDataUtil.getMalusCorruption(player) > 0 && !player.m_21023_((MobEffect)UpgradedNetheriteEffects.NETHERITE_CORRUPTION.get())) {
            EntityDataUtil.setMalusCorruption(player, 0);
            EntityDataUtil.setCorrupterite(player, CorruptUtil.intWearingCorrupt(player, false));
         }

         if (WaterUtil.isWearingWaterArmor(player) && UpgradedNetheriteConfig.EnableWaterBreath && player.m_20146_() < player.m_6062_()) {
            player.m_20301_(player.m_20146_() + 4);
            DurabilityUtil.WaterDurabilityLoss(player);
         }

         if (PhantomUtil.isWearingPhantomArmor(player) && UpgradedNetheriteConfig.EnableStepHeight) {
            if (player.f_19793_ < 1.0F) {
               EntityDataUtil.setAbilityStepHeight(player, true);
               player.f_19793_ = 1.0F;
            }
         } else if (EntityDataUtil.getAbilityStepHeight(player) && player.f_19793_ > 0.6F) {
            player.f_19793_ = 0.6F;
            EntityDataUtil.setAbilityStepHeight(player, false);
         }

         if (CorruptUtil.intWearingCorrupt(player, false) > 0) {
            DurabilityUtil.CorruptDurabilityGain(player);
         }

         if (FireUtil.isWearingFireArmor(player) && UpgradedNetheriteConfig.EnableLavaSpeed && player.m_20077_() && !player.m_150110_().f_35935_) {
            player.m_20256_(player.m_20184_().m_82542_(1.659999966621399D, 1.0D, 1.659999966621399D));
         }

         if (WaterUtil.isWearingWaterArmor(player) && UpgradedNetheriteConfig.EnableWaterSpeed) {
            player.m_21204_().m_22178_(this.SwimAttributeMap());
         } else if (!WaterUtil.isWearingWaterArmor(player)) {
            player.m_21204_().m_22161_(this.SwimAttributeMap());
         }

         if (UpgradedNetheriteConfig.EnableClimbWall && PoisonUtil.isWearingPoisonArmor(player)) {
            if (!player.m_6047_() && EntityDataUtil.getAbilityClimbwall(player)) {
               if (player.m_20184_().m_7098_() < 0.0D) {
                  player.m_6853_(true);
                  player.m_20256_(player.m_20184_().m_82520_(-player.m_20184_().m_7096_() / 5.0D, -player.m_20184_().m_7098_(), -player.m_20184_().m_7094_() / 5.0D));
                  player.f_19789_ = 0.0F;
               }

               if (player.m_20184_().m_7096_() != 0.0D && player.m_20184_().m_7094_() != 0.0D) {
                  EntityDataUtil.setAbilityClimbwall(player, false);
               }
            }

            if ((player.m_6047_() || player.m_6147_()) && EntityDataUtil.getAbilityClimbwall(player)) {
               EntityDataUtil.setAbilityClimbwall(player, false);
            }

            if (!player.m_6047_() && player.f_19862_ && !player.m_20077_() && !player.m_20069_() && !player.m_150110_().f_35935_ && !player.m_6147_() && player.m_20184_().m_7098_() < 0.1D) {
               Double LookAt = player.m_20154_().f_82480_;
               if (LookAt > 0.1D) {
                  LookAt = 0.1D;
               }

               if (LookAt < -0.1D) {
                  LookAt = -0.1D;
               }

               if (player.f_19853_.f_46443_ && player instanceof LocalPlayer && ((LocalPlayer)player).f_108618_.f_108567_ < 0.0F) {
                  LookAt = LookAt * -1.0D;
               }

               player.m_20256_(player.m_20184_().m_82520_(-player.m_20184_().m_7096_() / 5.0D, LookAt - player.m_20184_().f_82480_, -player.m_20184_().m_7094_() / 5.0D));
               EntityDataUtil.setAbilityClimbwall(player, true);
               player.f_19789_ = 0.0F;
               if (player.f_19853_.f_46443_) {
                  PlayerFallDistanceUpdateHandler.PlayerFallDistanceUpdate(player.m_20148_(), player.f_19789_);
               }
            }
         } else if (!PoisonUtil.isWearingPoisonArmor(player) && EntityDataUtil.getAbilityClimbwall(player)) {
            EntityDataUtil.setAbilityClimbwall(player, false);
         }

         if (EchoUtil.isWearingEchoArmor(player) && !EntityDataUtil.hasEchoHealCooldown(player) && player.f_19853_.m_204166_(player.m_20183_()).m_203565_(Biomes.f_220594_) && UpgradedNetheriteConfig.EnableHealEchoArmor && player.m_21223_() < player.m_21233_()) {
            player.getPersistentData().m_128405_("upgraded_netherite_echo_heal_cd", UpgradedNetheriteConfig.HealEchoArmorDelay * 20);
            player.m_5634_(1.0F);
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
         if (event.getSource().m_269533_(DamageTypeTags.f_268745_) && FireUtil.isWearingFireArmor(player)) {
            if (UpgradedNetheriteConfig.EnableFireImmune) {
               if (event.isCancelable()) {
                  event.setCanceled(true);
               }

               player.m_20095_();
               DurabilityUtil.FireDurabilityLoss(player);
            }
         } else if (event.getSource().m_276093_(DamageTypes.f_268724_) && !player.f_19853_.m_5776_() && EnderUtil.isWearingEnderArmor(player) && EnderUtil.isVoidYLevel(player) && !player.m_21023_((MobEffect)UpgradedNetheriteEffects.ENDER_ANCHOR.get()) && UpgradedNetheriteConfig.EnableVoidSave && enderSaveVoid(player)) {
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
               Iterable<ItemStack> armorList = player.m_6168_();
               Iterator var4 = armorList.iterator();

               while(var4.hasNext()) {
                  ItemStack stack = (ItemStack)var4.next();
                  if (!stack.m_41619_() && PhantomUtil.isPhantomArmor(stack) && (double)player.f_19789_ >= 3.5D) {
                     stack.m_41622_(UpgradedNetheriteConfig.MultiplierDamageDurabilityPhantomArmor * (Math.round(player.f_19789_) - 3), player, (livingEntity) -> {
                        livingEntity.m_21166_(((ArmorItem)stack.m_41720_()).m_40402_());
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
         if (event.getEffectInstance().m_19544_() == MobEffects.f_19615_ && WitherUtil.isWearingWitherArmor(player) && UpgradedNetheriteConfig.EnableWitherImmune) {
            event.setResult(Result.DENY);
            if (event.isCancelable()) {
               event.setCanceled(true);
            }

            DurabilityUtil.WitherDurabilityLoss(player);
         } else if (event.getEffectInstance().m_19544_() == MobEffects.f_19614_ && PoisonUtil.isWearingPoisonArmor(player) && UpgradedNetheriteConfig.EnablePoisonImmune) {
            event.setResult(Result.DENY);
            if (event.isCancelable()) {
               event.setCanceled(true);
            }

            DurabilityUtil.PoisonDurabilityLoss(player);
         } else if (event.getEffectInstance().m_19544_() == MobEffects.f_19620_ && FeatherUtil.isWearingFeatherArmor(player) && UpgradedNetheriteConfig.EnableLevitationImmune) {
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
      Minecraft mc = Minecraft.m_91087_();
      if (event.getOverlay() == VanillaGuiOverlay.AIR_LEVEL.type() && WaterUtil.isWearingWaterArmor(mc.f_91074_) && UpgradedNetheriteConfig.EnableWaterBreath && (double)mc.f_91074_.m_20146_() >= (double)mc.f_91074_.m_6062_() * 0.966D) {
         event.setCanceled(true);
      }

   }
}
