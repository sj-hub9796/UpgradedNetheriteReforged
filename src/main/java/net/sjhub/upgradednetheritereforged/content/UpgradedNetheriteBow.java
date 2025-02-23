package net.sjhub.upgradednetheritereforged.content;

import com.rolfmao.upgradedcore_old.helpers.TooltipHelper;
import net.sjhub.upgradednetheritereforged.config.UpgradedNetheriteConfig;
import net.sjhub.upgradednetheritereforged.init.ModItems;
import net.sjhub.upgradednetheritereforged.utils.ToolUtil;
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
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.AbstractArrow.Pickup;
import net.minecraft.world.item.ArrowItem;
import net.minecraft.world.item.BowItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;

public class UpgradedNetheriteBow extends BowItem {
   public UpgradedNetheriteBow(Properties properties) {
      super(properties);
   }

   public boolean m_6832_(ItemStack toRepair, ItemStack repair) {
      return Items.NETHERITE_INGOT.equals(repair.getItem());
   }

   public boolean canApplyAtEnchantingTable(ItemStack itemStack, Enchantment enchantment) {
      if (itemStack.getItem() == ModItems.CORRUPT_UPGRADED_NETHERITE_BOW.get() && enchantment == Enchantments.MENDING) {
         return false;
      } else {
         return (itemStack.getItem() == ModItems.GOLD_UPGRADED_NETHERITE_BOW.get() || itemStack.getItem() == ModItems.CORRUPT_UPGRADED_NETHERITE_BOW.get()) && enchantment == Enchantments.MOB_LOOTING ? true : enchantment.category.canEnchant(itemStack.getItem());
      }
   }

   public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int slot, boolean p_77663_5_) {
      if (itemStack.getItem() == ModItems.CORRUPT_UPGRADED_NETHERITE_BOW.get() && itemStack.isEnchanted() && EnchantmentHelper.getItemEnchantmentLevel(Enchantments.MENDING, itemStack) > 0) {
         Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(itemStack);
         if (enchantments.containsKey(Enchantments.MENDING)) {
            enchantments.remove(Enchantments.MENDING);
            EnchantmentHelper.setEnchantments(enchantments, itemStack);
         }
      }

   }

   public InteractionResult useOn(UseOnContext context) {
      InteractionResult iResult = InteractionResult.PASS;
      if (context.getPlayer() != null && context.getPlayer().isCrouching() && EnderUtil.isEnderRangedWeapon(context.getItemInHand()) && !context.getPlayer().getCooldowns().isOnCooldown(context.getItemInHand().getItem())) {
         iResult = ToolUtil.EnderSetTag(context);
      }

      return iResult == InteractionResult.PASS ? super.useOn(context) : iResult;
   }

   public void releaseUsing(ItemStack itemStack, Level level, LivingEntity livingEntity, int timeLeft) {
      if (livingEntity instanceof Player) {
         Player playerentity = (Player)livingEntity;
         boolean flag = playerentity.getAbilities().instabuild || EnchantmentHelper.getTagEnchantmentLevel(Enchantments.INFINITY_ARROWS, itemStack) > 0;
         ItemStack itemstack = playerentity.getProjectile(itemStack);
         int i = this.getUseDuration(itemStack) - timeLeft;
         i = ForgeEventFactory.onArrowLoose(itemStack, level, playerentity, i, !itemstack.isEmpty() || flag);
         if (i < 0) {
            return;
         }

         if (!itemstack.isEmpty() || flag) {
            if (itemstack.isEmpty()) {
               itemstack = new ItemStack(Items.ARROW);
            }

            float f = getPowerForTime(i);
            if (!((double)f < 0.1D)) {
               boolean flag1 = playerentity.getAbilities().instabuild || itemstack.getItem() instanceof ArrowItem && ((ArrowItem)itemstack.getItem()).isInfinite(itemstack, itemStack, playerentity);
               if (!level.isClientSide) {
                  ArrowItem arrowitem = (ArrowItem)(itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                  AbstractArrow abstractarrowentity = arrowitem.createArrow(level, itemstack, playerentity);
                  abstractarrowentity = this.customArrow(abstractarrowentity);
                  abstractarrowentity.shootFromRotation(playerentity, playerentity.getXRot(), playerentity.getYRot(), 0.0F, f * 3.0F, 1.0F);
                  if (f == 1.0F) {
                     abstractarrowentity.setCritArrow(true);
                  }

                  int j = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.POWER_ARROWS, itemStack);
                  if (j > 0) {
                     abstractarrowentity.setBaseDamage(3.0D + (double)j * 0.5D + 0.5D);
                  } else {
                     abstractarrowentity.setBaseDamage(3.0D);
                  }

                  int k = EnchantmentHelper.getTagEnchantmentLevel(Enchantments.PUNCH_ARROWS, itemStack);
                  if (k > 0) {
                     abstractarrowentity.setKnockback(k);
                  }

                  if (EnchantmentHelper.getTagEnchantmentLevel(Enchantments.FLAMING_ARROWS, itemStack) > 0) {
                     abstractarrowentity.setSecondsOnFire(100);
                  }

                  itemStack.hurtAndBreak(1, (ServerPlayer)playerentity, (p_220009_1_) -> {
                     p_220009_1_.broadcastBreakEvent(playerentity.getUsedItemHand());
                  });
                  if (flag1 || playerentity.getAbilities().instabuild && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)) {
                     abstractarrowentity.pickup = Pickup.CREATIVE_ONLY;
                  }

                  if (this.m_5456_() != ModItems.NETHERITE_BOW.get()) {
                     abstractarrowentity.getPersistentData().putFloat("getPowerForTime", f);
                  }

                  if (this.m_5456_() == ModItems.GOLD_UPGRADED_NETHERITE_BOW.get()) {
                     abstractarrowentity.m_20049_("GoldUpgradedNetheriteBow");
                     if (EnchantmentHelper.getTagEnchantmentLevel(Enchantments.MOB_LOOTING, itemStack) > 0) {
                        abstractarrowentity.getPersistentData().putInt("LootingGoldUpgradedNetheriteBow", EnchantmentHelper.getTagEnchantmentLevel(Enchantments.MOB_LOOTING, itemStack));
                     }
                  }

                  if (this.m_5456_() == ModItems.FIRE_UPGRADED_NETHERITE_BOW.get()) {
                     abstractarrowentity.m_20049_("FireUpgradedNetheriteBow");
                     if (EnchantmentHelper.getTagEnchantmentLevel(Enchantments.FLAMING_ARROWS, itemStack) > 0) {
                        abstractarrowentity.m_20049_("FlameFireUpgradedNetheriteBow");
                     }
                  }

                  if (this.m_5456_() == ModItems.ENDER_UPGRADED_NETHERITE_BOW.get()) {
                     abstractarrowentity.m_20049_("EnderUpgradedNetheriteBow");
                     if (itemStack.getOrCreateTag().getBoolean("UpgradedNetherite_Tagged")) {
                        abstractarrowentity.getPersistentData().putIntArray("UpgradedNetherite_Position", itemStack.getOrCreateTag().getIntArray("UpgradedNetherite_Position"));
                        abstractarrowentity.getPersistentData().putString("UpgradedNetherite_Dimension", itemStack.getOrCreateTag().getString("UpgradedNetherite_Dimension"));
                        abstractarrowentity.getPersistentData().putBoolean("UpgradedNetherite_Tagged", itemStack.getOrCreateTag().getBoolean("UpgradedNetherite_Tagged"));
                     }
                  }

                  if (this.m_5456_() == ModItems.WATER_UPGRADED_NETHERITE_BOW.get()) {
                     abstractarrowentity.m_20049_("WaterUpgradedNetheriteBow");
                  }

                  if (this.m_5456_() == ModItems.WITHER_UPGRADED_NETHERITE_BOW.get()) {
                     abstractarrowentity.m_20049_("WitherUpgradedNetheriteBow");
                  }

                  if (this.m_5456_() == ModItems.POISON_UPGRADED_NETHERITE_BOW.get()) {
                     abstractarrowentity.m_20049_("PoisonUpgradedNetheriteBow");
                  }

                  if (this.m_5456_() == ModItems.PHANTOM_UPGRADED_NETHERITE_BOW.get()) {
                     abstractarrowentity.m_20049_("PhantomUpgradedNetheriteBow");
                  }

                  if (this.m_5456_() == ModItems.FEATHER_UPGRADED_NETHERITE_BOW.get()) {
                     abstractarrowentity.m_20049_("FeatherUpgradedNetheriteBow");
                  }

                  if (this.m_5456_() == ModItems.CORRUPT_UPGRADED_NETHERITE_BOW.get()) {
                     abstractarrowentity.m_20049_("CorruptUpgradedNetheriteBow");
                     abstractarrowentity.getPersistentData().putInt("LootingCorruptUpgradedNetheriteBow", CorruptUtil.intWearingCorrupt(playerentity, true));
                  }

                  if (this.m_5456_() == ModItems.ECHO_UPGRADED_NETHERITE_BOW.get()) {
                     abstractarrowentity.m_20049_("EchoUpgradedNetheriteBow");
                  }

                  level.m_7967_(abstractarrowentity);
               }

               level.playSound((Player)null, playerentity.m_20185_(), playerentity.m_20186_(), playerentity.m_20189_(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (level.random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
               if (!flag1 && !playerentity.getAbilities().instabuild) {
                  itemstack.shrink(1);
                  if (itemstack.isEmpty()) {
                     playerentity.getInventory().removeItem(itemstack);
                  }
               }

               playerentity.awardStat(Stats.ITEM_USED.get(this));
            }
         }
      }

   }

   @OnlyIn(Dist.CLIENT)
   public void appendHoverText(ItemStack itemStack, Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
      super.appendHoverText(itemStack, level, tooltip, tooltipFlag);
      if (!UpgradedNetheriteConfig.DisableTooltips) {
         Item bow = itemStack.getItem();
         if (bow != ModItems.NETHERITE_BOW.get()) {
            if (Screen.hasShiftDown()) {
               float EnchantBonus;
               Map enchantments;
               int EnchantLevel;
               if (!GoldUtil.isGoldRangedWeapon(itemStack) || !UpgradedNetheriteConfig.EnableDamageBonusGoldWeapon && !UpgradedNetheriteConfig.EnableLootingBonus) {
                  if (!FireUtil.isFireRangedWeapon(itemStack) || !UpgradedNetheriteConfig.EnableDamageBonusFireWeapon && !UpgradedNetheriteConfig.EnableAutoSmelt) {
                     if (EnderUtil.isEnderRangedWeapon(itemStack)) {
                        if (UpgradedNetheriteConfig.EnablePreventTeleport) {
                           tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.OnHit.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Ender_Weapon.TT"));
                        }

                        if (UpgradedNetheriteConfig.EnableTeleportChest) {
                           tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Ender_Tool.TT"));
                           if (itemStack.getTag() != null && itemStack.getTag().contains("UpgradedNetherite_Tagged") && itemStack.getTag().getBoolean("UpgradedNetherite_Tagged")) {
                              String world = level.dimension().location().getPath();
                              String var10001;
                              if (!world.equals(itemStack.getTag().getString("UpgradedNetherite_Dimension"))) {
                                 tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                                 tooltip.add(Component.translatable("upgradednetherite.Target.TT"));
                                 tooltip.add(Component.translatable("upgradednetherite.Ender_Dim.TT"));
                                 var10001 = itemStack.getTag().getString("UpgradedNetherite_Dimension");
                                 tooltip.add(Component.literal("§7• §c" + var10001 + "§7 : §3" + itemStack.getTag().getIntArray("UpgradedNetherite_Position")[0] + "§7, §3" + itemStack.getTag().getIntArray("UpgradedNetherite_Position")[1] + "§7, §3" + itemStack.getTag().getIntArray("UpgradedNetherite_Position")[2] + "§7."));
                              } else {
                                 tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                                 tooltip.add(Component.translatable("upgradednetherite.Target.TT"));
                                 var10001 = itemStack.getTag().getString("UpgradedNetherite_Dimension");
                                 tooltip.add(Component.literal("§7• §9" + var10001 + "§7 : §3" + itemStack.getTag().getIntArray("UpgradedNetherite_Position")[0] + "§7, §3" + itemStack.getTag().getIntArray("UpgradedNetherite_Position")[1] + "§7, §3" + itemStack.getTag().getIntArray("UpgradedNetherite_Position")[2] + "§7."));
                              }
                           }
                        }
                     } else if (WaterUtil.isWaterRangedWeapon(itemStack) && (UpgradedNetheriteConfig.EnableDamageBonusWaterWeapon || UpgradedNetheriteConfig.EnableMiningSpeedUnderwater)) {
                        tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                        tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                        if (UpgradedNetheriteConfig.EnableDamageBonusWaterWeapon) {
                           TooltipHelper.addTWO(tooltip, "upgradednetherite.Water_Weapon.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusWaterWeapon + "%"});
                        }

                        if (UpgradedNetheriteConfig.EnableMiningSpeedUnderwater) {
                           tooltip.add(Component.translatable("upgradednetherite.Water_Tool.TT"));
                        }
                     } else if (WitherUtil.isWitherRangedWeapon(itemStack)) {
                        if (UpgradedNetheriteConfig.EnableWitherEffect) {
                           tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.OnHit.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Wither_Weapon.TT"));
                        }

                        if (UpgradedNetheriteConfig.EnableDamageBonusWitherWeapon) {
                           tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                           TooltipHelper.addTWO(tooltip, "upgradednetherite.Wither_Weapon2.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusWitherWeapon + "%"});
                        }
                     } else if (PoisonUtil.isPoisonRangedWeapon(itemStack)) {
                        if (UpgradedNetheriteConfig.EnablePoisonEffect) {
                           tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.OnHit.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Poison_Weapon.TT"));
                        }

                        if (UpgradedNetheriteConfig.EnableDamageBonusPoisonWeapon) {
                           tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                           TooltipHelper.addTWO(tooltip, "upgradednetherite.Poison_Weapon2.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusPoisonWeapon + "%"});
                        }
                     } else if (!PhantomUtil.isPhantomRangedWeapon(itemStack) || !UpgradedNetheriteConfig.EnableDamageBonusPhantomWeapon && !UpgradedNetheriteConfig.EnableGlowingEffect && !UpgradedNetheriteConfig.EnableReachEffect) {
                        if (FeatherUtil.isFeatherRangedWeapon(itemStack)) {
                           tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.OnHit.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Feather_Bow.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                           TooltipHelper.addTWO(tooltip, "upgradednetherite.Feather_Tool.TT", new Object[]{"§7• "});
                        } else if (CorruptUtil.isCorruptRangedWeapon(itemStack)) {
                           tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Malus.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Corrupt_Bonus2.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Corrupt_Enchant.TT"));
                           if (UpgradedNetheriteConfig.EnableDamageBonusCorruptWeapon || UpgradedNetheriteConfig.EnableLootingBonusCorruptWeapon) {
                              tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                              tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                           }

                           if (Minecraft.getInstance().player != null && CorruptUtil.intWearingCorrupt(Minecraft.getInstance().player, true) > 0) {
                              Object[] var10002;
                              int var10005;
                              if (UpgradedNetheriteConfig.EnableDamageBonusCorruptWeapon) {
                                 var10002 = new Object[1];
                                 var10005 = CorruptUtil.intWearingCorrupt(Minecraft.getInstance().player, true);
                                 var10002[0] = "§6" + var10005 * UpgradedNetheriteConfig.DamageBonusCorruptWeapon + "%";
                                 TooltipHelper.addTWO(tooltip, "upgradednetherite.Corrupt_Weapon3.TT", var10002);
                              }

                              if (UpgradedNetheriteConfig.EnableLootingBonusCorruptWeapon) {
                                 var10002 = new Object[1];
                                 var10005 = CorruptUtil.intWearingCorrupt(Minecraft.getInstance().player, true);
                                 var10002[0] = "§6" + var10005 * UpgradedNetheriteConfig.LootingBonusCorruptWeapon;
                                 TooltipHelper.addTWO(tooltip, "upgradednetherite.Corrupt_Weapon4.TT", var10002);
                              }
                           }

                           EnchantBonus = 0.0F;
                           enchantments = EnchantmentHelper.getEnchantments(itemStack);
                           if (!enchantments.isEmpty() && enchantments.containsKey(Enchantments.MOB_LOOTING)) {
                              EnchantLevel = (Integer)enchantments.get(Enchantments.MOB_LOOTING);
                              EnchantBonus = (float)EnchantLevel;
                           }

                           if (!(EnchantBonus > 0.0F)) {
                              tooltip.add(Component.translatable("upgradednetherite.Gold_Bow.TT"));
                           }

                           if (UpgradedNetheriteConfig.EnableDamageBonusCorruptWeapon) {
                              TooltipHelper.addTWO(tooltip, "upgradednetherite.Corrupt_Weapon2.TT", new Object[]{"§d" + UpgradedNetheriteConfig.DamageBonusCorruptWeapon + "%"});
                           }

                           if (UpgradedNetheriteConfig.EnableLootingBonusCorruptWeapon) {
                              TooltipHelper.addTWO(tooltip, "upgradednetherite.Corrupt_Weapon.TT", new Object[]{"§d" + UpgradedNetheriteConfig.LootingBonusCorruptWeapon});
                           }
                        } else if (EchoUtil.isEchoRangedWeapon(itemStack) && (UpgradedNetheriteConfig.EnableDamageBonusEchoWeapon || UpgradedNetheriteConfig.EnableBonusExpEcho)) {
                           tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                           if (UpgradedNetheriteConfig.EnableDamageBonusEchoWeapon) {
                              TooltipHelper.addTWO(tooltip, "upgradednetherite.Echo_Weapon.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusEchoWeapon + "%"});
                           }

                           if (UpgradedNetheriteConfig.EnableBonusExpEcho) {
                              TooltipHelper.addTWO(tooltip, "upgradednetherite.Echo_Tool2.TT", new Object[0]);
                           }
                        } else {
                           tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                           tooltip.add(Component.translatable("upgradednetherite.Disabled.TT"));
                        }
                     } else {
                        tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                        tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                        if (UpgradedNetheriteConfig.EnableDamageBonusPhantomWeapon) {
                           TooltipHelper.addTWO(tooltip, "upgradednetherite.Phantom_Weapon.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusPhantomWeapon + "%"});
                        }

                        if (UpgradedNetheriteConfig.EnableGlowingEffect) {
                           TooltipHelper.addTWO(tooltip, "upgradednetherite.Phantom_Tool.TT", new Object[]{"§7• "});
                        }

                        if (UpgradedNetheriteConfig.EnableReachEffect) {
                           tooltip.add(Component.translatable("upgradednetherite.Phantom_Tool2.TT"));
                        }
                     }
                  } else {
                     tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                     if (UpgradedNetheriteConfig.EnableDamageBonusFireWeapon) {
                        EnchantBonus = 0.0F;
                        enchantments = EnchantmentHelper.getEnchantments(itemStack);
                        if (!enchantments.isEmpty() && enchantments.containsKey(Enchantments.FLAMING_ARROWS)) {
                           EnchantLevel = (Integer)enchantments.get(Enchantments.FLAMING_ARROWS);
                           EnchantBonus = (float)EnchantLevel;
                        }

                        tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                        if (EnchantBonus >= 1.0F) {
                           TooltipHelper.addTWO(tooltip, "upgradednetherite.Fire_Weapon.TT", new Object[]{"§6" + (UpgradedNetheriteConfig.DamageBonusFireWeapon + UpgradedNetheriteConfig.DamageBonusFireEnchantWeapon) + "%"});
                        } else {
                           TooltipHelper.addTWO(tooltip, "upgradednetherite.Fire_Weapon.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusFireWeapon + "%"});
                           TooltipHelper.addTWO(tooltip, "upgradednetherite.Fire_Bow.TT", new Object[]{"§d" + UpgradedNetheriteConfig.DamageBonusFireEnchantWeapon + "%"});
                        }
                     }

                     if (UpgradedNetheriteConfig.EnableAutoSmelt) {
                        TooltipHelper.addTWO(tooltip, "upgradednetherite.Fire_Tool.TT", new Object[]{"§7• "});
                     }
                  }
               } else {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                  if (UpgradedNetheriteConfig.EnableDamageBonusGoldWeapon) {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Weapon.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusGoldWeapon + "%"});
                  }

                  if (UpgradedNetheriteConfig.EnableLootingBonus) {
                     EnchantBonus = 0.0F;
                     enchantments = EnchantmentHelper.getEnchantments(itemStack);
                     if (!enchantments.isEmpty() && enchantments.containsKey(Enchantments.MOB_LOOTING)) {
                        EnchantLevel = (Integer)enchantments.get(Enchantments.MOB_LOOTING);
                        EnchantBonus = (float)EnchantLevel;
                     }

                     if (EnchantBonus >= 3.0F) {
                        TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Weapon2.TT", new Object[]{"§6" + (UpgradedNetheriteConfig.LootingBonus + UpgradedNetheriteConfig.LootingEnchantBonus)});
                     } else {
                        TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Weapon2.TT", new Object[]{"§6" + UpgradedNetheriteConfig.LootingBonus});
                        if (!(EnchantBonus > 0.0F)) {
                           tooltip.add(Component.translatable("upgradednetherite.Gold_Bow.TT"));
                        }

                        TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Weapon3.TT", new Object[]{"§d" + UpgradedNetheriteConfig.LootingEnchantBonus});
                     }
                  }
               }
            } else {
               tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
               tooltip.add(Component.translatable("upgradednetherite.HoldShift.TT"));
               if (EnderUtil.isEnderRangedWeapon(itemStack)) {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  if (itemStack.getTag() != null && itemStack.getTag().contains("UpgradedNetherite_Tagged") && itemStack.getTag().getBoolean("UpgradedNetherite_Tagged")) {
                     tooltip.add(Component.translatable("upgradednetherite.Ender_ToolTar.TT"));
                  } else {
                     tooltip.add(Component.translatable("upgradednetherite.Ender_ToolReq.TT"));
                  }
               }
            }
         }
      }

   }
}
