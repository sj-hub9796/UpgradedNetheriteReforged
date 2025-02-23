package net.sjhub.upgradednetheritereforged.handlers;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import net.sjhub.upgradednetheritereforged.UpgradedNetheriteMod;
import net.sjhub.upgradednetheritereforged.config.UpgradedNetheriteConfig;
import net.sjhub.upgradednetheritereforged.utils.EntityDataUtil;
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
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.EntityMountEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LootingLevelEvent;
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
public class HorseArmorEventHandler {
   @SubscribeEvent(
      receiveCanceled = true,
      priority = EventPriority.LOWEST
   )
   public static void onDamageEntity(LivingHurtEvent event) {
      if (!event.getEntity().level().isClientSide) {
         if (event.getEntity() instanceof Player && event.getEntity().getVehicle() instanceof Horse && FeatherUtil.isHorseWearingFeatherArmor((Horse)event.getEntity().getVehicle()) && event.getSource().is(DamageTypes.FALL) && UpgradedNetheriteConfig.EnableReduceFallDamage) {
            event.setAmount(event.getAmount() / 2.0F);
         } else if (event.getEntity() instanceof Horse && FeatherUtil.isHorseWearingFeatherArmor((Horse)event.getEntity()) && event.getSource().is(DamageTypes.FALL) && UpgradedNetheriteConfig.EnableReduceFallDamage) {
            event.setAmount(event.getAmount() / 2.0F);
         }

         if (event.getEntity() instanceof Horse && "sonic_boom".equals(event.getSource().getMsgId()) && EchoUtil.isHorseWearingEchoArmor((Horse)event.getEntity()) && UpgradedNetheriteConfig.EnableReduceDamageEchoArmor) {
            float reduceDamage = (float)UpgradedNetheriteConfig.ReduceDamageEchoArmor;
            event.setAmount(event.getAmount() - event.getAmount() * Math.min(1.0F, reduceDamage / 100.0F));
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
         if (player.getVehicle() instanceof Horse) {
            Horse horse = (Horse)player.getVehicle();
            if (GoldUtil.isHorseWearingGoldArmor(horse) && UpgradedNetheriteConfig.EnableLootingBonus) {
               event.setLootingLevel(event.getLootingLevel() + 1);
            }
         }
      }

   }

   private Multimap<Attribute, AttributeModifier> SwimHorseAttributeMap() {
      Multimap<Attribute, AttributeModifier> attributesDefault = HashMultimap.create();
      attributesDefault.put((Attribute)ForgeMod.SWIM_SPEED.get(), new AttributeModifier(UUID.fromString("fbfd69fe-3369-11eb-adc1-0242ac120002"), "upgradednetherite_reforged:swim_bonus", 1.5D, Operation.MULTIPLY_BASE));
      return attributesDefault;
   }

   @SubscribeEvent
   public void onLivingUpdate(LivingTickEvent event) {
      if (event.getEntity() instanceof Horse && ((Horse)event.getEntity()).isTamed()) {
         Horse horse = (Horse)event.getEntity();
         Player rider = null;
         if (!horse.getPassengers().isEmpty()) {
            Iterator var4 = horse.getPassengers().iterator();

            while(var4.hasNext()) {
               Entity entity = (Entity)var4.next();
               if (entity instanceof Player) {
                  rider = (Player)entity;
               }
            }
         }

         if (horse.isInLava() && FireUtil.isHorseWearingFireArmor(horse) && UpgradedNetheriteConfig.EnableLavaSpeed) {
            horse.setDeltaMovement(horse.getDeltaMovement().multiply(1.66D, 1.0D, 1.66D));
         }

         if (EnderUtil.isHorseWearingEnderArmor(horse) && UpgradedNetheriteConfig.EnableVoidSave) {
            BlockPos blockpos1 = horse.blockPosition().below();
            BlockState blockstate = horse.level().getBlockState(blockpos1);
            if (blockstate.blocksMotion()) {
               EntityDataUtil.setAbilityEnderPos(horse, true);
            }
         } else if (!EnderUtil.isHorseWearingEnderArmor(horse) && EntityDataUtil.getAbilityEnderPos(horse) != null) {
            EntityDataUtil.setAbilityEnderPos(horse, false);
         }

         if (WaterUtil.isHorseWearingWaterArmor(horse) && UpgradedNetheriteConfig.EnableWaterSpeed) {
            horse.getAttributes().addTransientAttributeModifiers(this.SwimHorseAttributeMap());
         } else if (!WaterUtil.isHorseWearingWaterArmor(horse)) {
            horse.getAttributes().removeAttributeModifiers(this.SwimHorseAttributeMap());
         }

         if (PoisonUtil.isHorseWearingPoisonArmor(horse) && UpgradedNetheriteConfig.EnableClimbWall) {
            if (rider != null && EntityDataUtil.getAbilityClimbwall(horse)) {
               if (horse.getDeltaMovement().y() < 0.0D) {
                  horse.setOnGround(true);
                  horse.fallDistance = 0.0F;
                  horse.setDeltaMovement(horse.getDeltaMovement().add(-horse.getDeltaMovement().x() / 10.0D, -horse.getDeltaMovement().y(), -horse.getDeltaMovement().z() / 10.0D));
               }

               if (horse.getDeltaMovement().x() != 0.0D && horse.getDeltaMovement().z() != 0.0D) {
                  EntityDataUtil.setAbilityClimbwall(horse, false);
               }
            }

            if (rider != null && horse.horizontalCollision && !horse.isInLava() && !horse.isInWater()) {
               Double LookAt = horse.getLookAngle().y;
               if (LookAt > 0.1D) {
                  LookAt = 0.1D;
               }

               if (LookAt < -0.1D) {
                  LookAt = -0.1D;
               }

               if (rider.level().isClientSide && ((LocalPlayer)rider).input.forwardImpulse < 0.0F) {
                  LookAt = LookAt * -1.0D;
               }

               horse.fallDistance = 0.0F;
               horse.setDeltaMovement(horse.getDeltaMovement().add(-horse.getDeltaMovement().x() / 10.0D, LookAt - horse.getDeltaMovement().y, -horse.getDeltaMovement().z() / 10.0D));
               if (horse.level().isClientSide) {
                  EntityFallDistanceUpdateHandler.EntityFallDistanceUpdate(horse.getId(), horse.fallDistance);
               }

               EntityDataUtil.setAbilityClimbwall(horse, true);
            }
         } else if (!PoisonUtil.isHorseWearingPoisonArmor(horse) && EntityDataUtil.getAbilityClimbwall(horse)) {
            EntityDataUtil.setAbilityClimbwall(horse, false);
         }

         if (horse.getAirSupply() < horse.getMaxAirSupply() && WaterUtil.isHorseWearingWaterArmor(horse) && UpgradedNetheriteConfig.EnableWaterBreath) {
            horse.setAirSupply(horse.getAirSupply() + 1);
         }

         if (horse.hasEffect(MobEffects.WITHER) && WitherUtil.isHorseWearingWitherArmor(horse) && UpgradedNetheriteConfig.EnableWitherImmune) {
            horse.removeEffect(MobEffects.WITHER);
         }

         if (horse.hasEffect(MobEffects.POISON) && PoisonUtil.isHorseWearingPoisonArmor(horse) && UpgradedNetheriteConfig.EnablePoisonImmune) {
            horse.removeEffect(MobEffects.POISON);
         }

         if ((horse.level().getFluidState(horse.blockPosition()).is(FluidTags.LAVA) || horse.level().getFluidState(horse.blockPosition()).is(FluidTags.WATER)) && FeatherUtil.isHorseWearingFeatherArmor(horse) && UpgradedNetheriteConfig.EnableWaterLavaWalking) {
            if (!horse.isInWater() && !horse.isInLava()) {
               horse.setOnGround(true);
               horse.setJumping(false);
               horse.setDeltaMovement(horse.getDeltaMovement().add(0.0D, -horse.getDeltaMovement().y(), 0.0D));
            } else if (horse.getDeltaMovement().y < 0.3D && (horse.isEyeInFluid(FluidTags.LAVA) || horse.isEyeInFluid(FluidTags.WATER))) {
               horse.setDeltaMovement(horse.getDeltaMovement().add(0.0D, 0.3D - horse.getDeltaMovement().y, 0.0D));
            } else if (horse.getDeltaMovement().y < 0.15D && !horse.isEyeInFluid(FluidTags.LAVA) && !horse.isEyeInFluid(FluidTags.WATER)) {
               horse.setDeltaMovement(horse.getDeltaMovement().add(0.0D, 0.15D - horse.getDeltaMovement().y, 0.0D));
            }
         }

         if (EchoUtil.isHorseWearingEchoArmor(horse) && UpgradedNetheriteConfig.EnableHealEchoArmor && !EntityDataUtil.hasEchoHealCooldownHorse(horse) && horse.getHealth() < horse.getMaxHealth()) {
            horse.getPersistentData().putInt("upgraded_netherite_echo_heal_cd", UpgradedNetheriteConfig.HealEchoArmorDelay * 20);
            horse.heal(1.0F);
         }

         EntityDataUtil.tickCooldownHorse(horse);
      }

      if (event.getEntity() instanceof Player) {
         Player player = (Player)event.getEntity();
         if (player.getVehicle() instanceof Horse && player.level().isClientSide) {
            Horse horse = (Horse)player.getVehicle();
            if ((WaterUtil.isHorseWearingWaterArmor(horse) && horse.level().getFluidState(player.blockPosition()).is(FluidTags.WATER) || FireUtil.isHorseWearingFireArmor(horse) && horse.level().getFluidState(player.blockPosition()).is(FluidTags.LAVA)) && player.level().isClientSide && player instanceof LocalPlayer && ((LocalPlayer)player).input.forwardImpulse != 0.0F) {
               double d3 = player.getLookAngle().y;
               double d4 = d3 < -0.2D ? 0.085D : 0.06D;
               double d5;
               if (player.level().isClientSide && player instanceof LocalPlayer && ((LocalPlayer)player).input.forwardImpulse > 0.0F) {
                  d5 = (d3 - horse.getDeltaMovement().y) * d4;
               } else {
                  d5 = -((d3 - horse.getDeltaMovement().y) * d4) / 4.0D;
               }

               horse.setDeltaMovement(horse.getDeltaMovement().add(0.0D, d5, 0.0D));
            }
         }
      }

   }

   @SubscribeEvent(
      receiveCanceled = true,
      priority = EventPriority.HIGHEST
   )
   public void onLivingAttackEvent(LivingAttackEvent event) {
      if (event.getEntity() instanceof Player && event.getEntity().getVehicle() instanceof Horse && ((Horse)event.getEntity().getVehicle()).isTamed() && PhantomUtil.isHorseWearingPhantomArmor((Horse)event.getEntity().getVehicle()) && event.isCancelable()) {
         event.setCanceled(true);
      }

      if (event.getEntity() instanceof Horse && ((Horse)event.getEntity()).isTamed()) {
         Horse horse = (Horse)event.getEntity();
         if (event.getSource().is(DamageTypeTags.IS_FIRE) && FireUtil.isHorseWearingFireArmor(horse) && UpgradedNetheriteConfig.EnableFireImmune) {
            if (event.isCancelable()) {
               event.setCanceled(true);
            }

            horse.clearFire();
         } else if (event.getSource().is(DamageTypes.FALL) && PhantomUtil.isHorseWearingPhantomArmor(horse) && UpgradedNetheriteConfig.EnableFallImmune) {
            if (event.isCancelable()) {
               event.setCanceled(true);
            }
         } else if (event.getSource().is(DamageTypes.DROWN) && WaterUtil.isHorseWearingWaterArmor(horse) && UpgradedNetheriteConfig.EnableWaterBreath) {
            if (event.isCancelable()) {
               event.setCanceled(true);
            }

            horse.setAirSupply(horse.getMaxAirSupply());
         } else if (event.getSource().is(DamageTypes.FELL_OUT_OF_WORLD) && EnderUtil.isHorseWearingEnderArmor(horse) && UpgradedNetheriteConfig.EnableVoidSave && horse.getY() < -63.0D) {
            if (event.isCancelable()) {
               event.setCanceled(true);
            }

            Level world = horse.level();
            if (!horse.getPassengers().isEmpty()) {
               horse.ejectPassengers();
            }

            horse.fallDistance = 0.0F;
            Boolean validtp = false;
            BlockPos blockpos;
            if (EntityDataUtil.getAbilityEnderPos(horse) != null) {
               if (horse.randomTeleport((double)horse.getPersistentData().getIntArray("upgraded_netherite_ender_pos")[0] + 0.5D, (double)horse.getPersistentData().getIntArray("upgraded_netherite_ender_pos")[1], (double)horse.getPersistentData().getIntArray("upgraded_netherite_ender_pos")[2] + 0.5D, true)) {
                  SoundEvent soundevent = SoundEvents.ENDERMAN_TELEPORT;
                  horse.level().playSound((Player)null, (double)horse.getPersistentData().getIntArray("upgraded_netherite_ender_pos")[0] + 0.5D, (double)horse.getPersistentData().getIntArray("upgraded_netherite_ender_pos")[1], (double)horse.getPersistentData().getIntArray("upgraded_netherite_ender_pos")[2] + 0.5D, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
                  horse.playSound(soundevent, 1.0F, 1.0F);
                  validtp = true;
               } else {
                  blockpos = EntityDataUtil.getAbilityEnderPos(horse);
                  List<BlockPos> validTpList = new ArrayList();
                  Iterator var7 = BlockPos.betweenClosed(blockpos.offset(-10, -10, -10), blockpos.offset(10, 10, 10)).iterator();

                  label219:
                  while(true) {
                     BlockPos blockpos1;
                     do {
                        do {
                           do {
                              do {
                                 do {
                                    do {
                                       do {
                                          do {
                                             do {
                                                do {
                                                   do {
                                                      do {
                                                         do {
                                                            do {
                                                               do {
                                                                  do {
                                                                     do {
                                                                        do {
                                                                           do {
                                                                              do {
                                                                                 if (!var7.hasNext()) {
                                                                                    if (validTpList.size() > 0) {
                                                                                       Integer IRNG = horse.getRandom().nextInt(validTpList.size());
                                                                                       horse.teleportTo((double)((BlockPos)validTpList.get(IRNG)).getX() + 0.5D, (double)((BlockPos)validTpList.get(IRNG)).getY(), (double)((BlockPos)validTpList.get(IRNG)).getZ() + 0.5D);
                                                                                       SoundEvent soundevent = SoundEvents.ENDERMAN_TELEPORT;
                                                                                       horse.level().playSound((Player)null, (double)((BlockPos)validTpList.get(IRNG)).getX() + 0.5D, (double)((BlockPos)validTpList.get(IRNG)).getY(), (double)((BlockPos)validTpList.get(IRNG)).getZ() + 0.5D, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
                                                                                       horse.playSound(soundevent, 1.0F, 1.0F);
                                                                                       validtp = true;
                                                                                    }
                                                                                    break label219;
                                                                                 }

                                                                                 blockpos1 = (BlockPos)var7.next();
                                                                              } while(!world.getBlockState(blockpos1.below()).blocksMotion());
                                                                           } while(!horse.level().getFluidState(blockpos1).isEmpty() && !horse.level().getBlockState(blockpos1).is(Blocks.BUBBLE_COLUMN));
                                                                        } while(!horse.level().getBlockState(blockpos1).isPathfindable(horse.level(), blockpos1, PathComputationType.LAND));
                                                                     } while(!horse.level().getFluidState(blockpos1.offset(-1, 1, 0)).isEmpty() && !horse.level().getBlockState(blockpos1.offset(-1, 1, 0)).is(Blocks.BUBBLE_COLUMN));
                                                                  } while(!horse.level().getBlockState(blockpos1.offset(-1, 1, 0)).isPathfindable(horse.level(), blockpos1.offset(-1, 1, 0), PathComputationType.LAND));
                                                               } while(!horse.level().getFluidState(blockpos1.offset(-1, 1, -1)).isEmpty() && !horse.level().getBlockState(blockpos1.offset(-1, 1, -1)).is(Blocks.BUBBLE_COLUMN));
                                                            } while(!horse.level().getBlockState(blockpos1.offset(-1, 1, -1)).isPathfindable(horse.level(), blockpos1.offset(-1, 1, -1), PathComputationType.LAND));
                                                         } while(!horse.level().getFluidState(blockpos1.offset(-1, 1, 1)).isEmpty() && !horse.level().getBlockState(blockpos1.offset(-1, 1, 1)).is(Blocks.BUBBLE_COLUMN));
                                                      } while(!horse.level().getBlockState(blockpos1.offset(-1, 1, 1)).isPathfindable(horse.level(), blockpos1.offset(-1, 1, 1), PathComputationType.LAND));
                                                   } while(!horse.level().getFluidState(blockpos1.offset(0, 1, 0)).isEmpty() && !horse.level().getBlockState(blockpos1.offset(0, 1, 0)).is(Blocks.BUBBLE_COLUMN));
                                                } while(!horse.level().getBlockState(blockpos1.offset(0, 1, 0)).isPathfindable(horse.level(), blockpos1.offset(0, 1, 0), PathComputationType.LAND));
                                             } while(!horse.level().getFluidState(blockpos1.offset(0, 1, -1)).isEmpty() && !horse.level().getBlockState(blockpos1.offset(0, 1, -1)).is(Blocks.BUBBLE_COLUMN));
                                          } while(!horse.level().getBlockState(blockpos1.offset(0, 1, -1)).isPathfindable(horse.level(), blockpos1.offset(0, 1, -1), PathComputationType.LAND));
                                       } while(!horse.level().getFluidState(blockpos1.offset(0, 1, 1)).isEmpty() && !horse.level().getBlockState(blockpos1.offset(0, 1, 1)).is(Blocks.BUBBLE_COLUMN));
                                    } while(!horse.level().getBlockState(blockpos1.offset(0, 1, 1)).isPathfindable(horse.level(), blockpos1.offset(0, 1, 1), PathComputationType.LAND));
                                 } while(!horse.level().getFluidState(blockpos1.offset(1, 1, 0)).isEmpty() && !horse.level().getBlockState(blockpos1.offset(1, 1, 0)).is(Blocks.BUBBLE_COLUMN));
                              } while(!horse.level().getBlockState(blockpos1.offset(1, 1, 0)).isPathfindable(horse.level(), blockpos1.offset(1, 1, 0), PathComputationType.LAND));
                           } while(!horse.level().getFluidState(blockpos1.offset(1, 1, -1)).isEmpty() && !horse.level().getBlockState(blockpos1.offset(1, 1, -1)).is(Blocks.BUBBLE_COLUMN));
                        } while(!horse.level().getBlockState(blockpos1.offset(1, 1, -1)).isPathfindable(horse.level(), blockpos1.offset(1, 1, -1), PathComputationType.LAND));
                     } while(!horse.level().getFluidState(blockpos1.offset(1, 1, 1)).isEmpty() && !horse.level().getBlockState(blockpos1.offset(1, 1, 1)).is(Blocks.BUBBLE_COLUMN));

                     if (horse.level().getBlockState(blockpos1.offset(1, 1, 1)).isPathfindable(horse.level(), blockpos1.offset(1, 1, 1), PathComputationType.LAND)) {
                        validTpList.add(blockpos1.immutable());
                     }
                  }
               }
            }

            if (!validtp) {
               blockpos = new BlockPos(100, 49, 0);
               Iterator var10 = BlockPos.betweenClosed(blockpos.offset(-1, -1, -1), blockpos.offset(1, -1, 1)).iterator();

               BlockPos blockpos1;
               while(var10.hasNext()) {
                  blockpos1 = (BlockPos)var10.next();
                  world.setBlockAndUpdate(blockpos1, Blocks.OBSIDIAN.defaultBlockState());
               }

               var10 = BlockPos.betweenClosed(blockpos.offset(-1, 0, -1), blockpos.offset(1, 1, 1)).iterator();

               while(var10.hasNext()) {
                  blockpos1 = (BlockPos)var10.next();
                  world.setBlockAndUpdate(blockpos1, Blocks.AIR.defaultBlockState());
               }

               horse.teleportTo(100.5D, 49.0D, 0.5D);
               SoundEvent soundevent = SoundEvents.ENDERMAN_TELEPORT;
               horse.level().playSound((Player)null, 100.5D, 49.0D, 0.5D, soundevent, SoundSource.PLAYERS, 1.0F, 1.0F);
               horse.playSound(soundevent, 1.0F, 1.0F);
            }
         }
      }

   }

   @SubscribeEvent
   public void onJump(LivingJumpEvent event) {
      if (event.getEntity() instanceof Horse && ((Horse)event.getEntity()).isTamed()) {
         Horse horse = (Horse)event.getEntity();
         EntityDataUtil.setAbilityClimbwall(horse, false);
      }

   }

   @SubscribeEvent(
      receiveCanceled = true,
      priority = EventPriority.HIGHEST
   )
   public void onApplyEffect(Added event) {
      if (event.getEntity() instanceof Horse && ((Horse)event.getEntity()).isTamed()) {
         Horse horse = (Horse)event.getEntity();
         if (event.getEffectInstance().getEffect() == MobEffects.WITHER && WitherUtil.isHorseWearingWitherArmor(horse) && UpgradedNetheriteConfig.EnableWitherImmune) {
            event.setResult(Result.DENY);
            if (event.isCancelable()) {
               event.setCanceled(true);
            }
         } else if (event.getEffectInstance().getEffect() == MobEffects.POISON && PoisonUtil.isHorseWearingPoisonArmor(horse) && UpgradedNetheriteConfig.EnablePoisonImmune) {
            event.setResult(Result.DENY);
            if (event.isCancelable()) {
               event.setCanceled(true);
            }
         }
      }

   }

   @SubscribeEvent
   public void onUnmount(EntityMountEvent event) {
      if (event.isDismounting() && event.getEntity() instanceof Player && !event.getEntity().level().isClientSide() && !event.getEntity().isDescending() && event.getEntity().getVehicle() instanceof Horse) {
         Horse horse = (Horse)event.getEntity().getVehicle();
         if (horse.isTamed() && horse.isEyeInFluid(FluidTags.WATER) && WaterUtil.isHorseWearingWaterArmor(horse) && (UpgradedNetheriteConfig.EnableWaterBreath || UpgradedNetheriteConfig.EnableWaterSpeed)) {
            event.setCanceled(true);
         }
      }

   }
}
