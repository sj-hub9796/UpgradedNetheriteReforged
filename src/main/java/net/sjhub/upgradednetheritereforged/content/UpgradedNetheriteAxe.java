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
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class UpgradedNetheriteAxe extends AxeItem {
   public UpgradedNetheriteAxe(Tier tier, int attackDamageIn, float attackSpeedIn, Properties properties) {
      super(tier, (float)attackDamageIn, attackSpeedIn, properties);
   }

   public boolean canApplyAtEnchantingTable(ItemStack itemStack, Enchantment enchantment) {
      if (itemStack.getItem() == ModItems.CORRUPT_UPGRADED_NETHERITE_AXE.get() && enchantment == Enchantments.MENDING) {
         return false;
      } else if ((itemStack.getItem() == ModItems.GOLD_UPGRADED_NETHERITE_AXE.get() || itemStack.getItem() == ModItems.CORRUPT_UPGRADED_NETHERITE_AXE.get()) && enchantment == Enchantments.MOB_LOOTING) {
         return true;
      } else {
         return itemStack.getItem() == ModItems.FIRE_UPGRADED_NETHERITE_AXE.get() && enchantment == Enchantments.FIRE_ASPECT ? true : enchantment.category.canEnchant(itemStack.getItem());
      }
   }

   public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int slot, boolean p_77663_5_) {
      if (itemStack.getItem() == ModItems.CORRUPT_UPGRADED_NETHERITE_AXE.get() && itemStack.isEnchanted() && EnchantmentHelper.getTagEnchantmentLevel(Enchantments.MENDING, itemStack) > 0) {
         Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(itemStack);
         if (enchantments.containsKey(Enchantments.MENDING)) {
            enchantments.remove(Enchantments.MENDING);
            EnchantmentHelper.setEnchantments(enchantments, itemStack);
         }
      }

   }

   public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
      ItemStack stack = player.getMainHandItem();
      if (player.isCrouching() && !player.getCooldowns().isOnCooldown(stack.getItem()) && ToolUtil.isUsingEffectTool(player)) {
         ToolUtil.toggleDisableEffect(player);
         return new InteractionResultHolder(InteractionResult.SUCCESS, stack);
      } else {
         return super.use(level, player, interactionHand);
      }
   }

   public InteractionResult useOn(UseOnContext context) {
      InteractionResult iResult = super.useOn(context);
      if (iResult == InteractionResult.PASS && context.getPlayer() != null && context.getPlayer().isCrouching() && EnderUtil.isEnderToolOrWeapon(context.getItemInHand()) && !context.getPlayer().getCooldowns().isOnCooldown(context.getItemInHand().getItem())) {
         iResult = ToolUtil.EnderSetTag(context);
      }

      if (iResult == InteractionResult.PASS) {
         iResult = super.use(context.getLevel(), context.getPlayer(), context.getHand()).getResult();
      }

      return iResult;
   }

   @OnlyIn(Dist.CLIENT)
   public void appendHoverText(ItemStack itemStack, Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
      super.appendHoverText(itemStack, level, tooltip, tooltipFlag);
      if (!UpgradedNetheriteConfig.DisableTooltips) {
         if (Screen.hasShiftDown()) {
            float EnchantBonus;
            Map enchantments;
            int EnchantLevel;
            if (GoldUtil.isGoldTool(itemStack) && (UpgradedNetheriteConfig.EnableDamageBonusGoldWeapon || UpgradedNetheriteConfig.EnableFortuneBonus || UpgradedNetheriteConfig.EnableLootingBonus)) {
               tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
               tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
               if (UpgradedNetheriteConfig.EnableDamageBonusGoldWeapon) {
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Weapon.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusGoldWeapon + "%"});
               }

               EnchantBonus = 0.0F;
               enchantments = EnchantmentHelper.getEnchantments(itemStack);
               if (!enchantments.isEmpty() && enchantments.containsKey(Enchantments.BLOCK_FORTUNE)) {
                  EnchantLevel = (Integer)enchantments.get(Enchantments.BLOCK_FORTUNE);
                  EnchantBonus = (float)EnchantLevel;
               }

               float EnchantBonusLooting = 0.0F;
               if (!enchantments.isEmpty() && enchantments.containsKey(Enchantments.MOB_LOOTING)) {
                  EnchantLevel = (Integer)enchantments.get(Enchantments.MOB_LOOTING);
                  EnchantBonusLooting = (float)EnchantLevel;
               }

               if (UpgradedNetheriteConfig.EnableFortuneBonus) {
                  if (EnchantBonus >= 3.0F) {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Tool.TT", new Object[]{"§6" + (UpgradedNetheriteConfig.FortuneBonus + UpgradedNetheriteConfig.FortuneEnchantBonus)});
                  } else {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Tool.TT", new Object[]{"§6" + UpgradedNetheriteConfig.FortuneBonus});
                  }
               }

               if (UpgradedNetheriteConfig.EnableLootingBonus) {
                  if (EnchantBonusLooting >= 3.0F) {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Weapon2.TT", new Object[]{"§6" + (UpgradedNetheriteConfig.LootingBonus + UpgradedNetheriteConfig.LootingEnchantBonus)});
                  } else {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Weapon2.TT", new Object[]{"§6" + UpgradedNetheriteConfig.LootingBonus});
                  }
               }

               if (EnchantBonus < 3.0F && UpgradedNetheriteConfig.EnableFortuneBonus) {
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Tool2.TT", new Object[]{"§d" + UpgradedNetheriteConfig.FortuneEnchantBonus});
               }

               if (EnchantBonusLooting < 3.0F && UpgradedNetheriteConfig.EnableLootingBonus) {
                  if (!(EnchantBonusLooting > 0.0F)) {
                     tooltip.add(Component.translatable("upgradednetherite.Gold_Bow.TT"));
                  }

                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Weapon3.TT", new Object[]{"§d" + UpgradedNetheriteConfig.LootingEnchantBonus});
               }
            } else if (!FireUtil.isFireTool(itemStack) || !UpgradedNetheriteConfig.EnableDamageBonusFireWeapon && !UpgradedNetheriteConfig.EnableAutoSmelt) {
               if (EnderUtil.isEnderTool(itemStack)) {
                  if (UpgradedNetheriteConfig.EnablePreventTeleport) {
                     tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                     tooltip.add(Component.translatable("upgradednetherite.OnHit.TT"));
                     tooltip.add(Component.translatable("upgradednetherite.Ender_Weapon.TT"));
                  }

                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                  if (UpgradedNetheriteConfig.EnableDamageBonusEnderWeapon) {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Ender_Weapon2.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusEnderWeapon + "%"});
                  }

                  if (UpgradedNetheriteConfig.EnableDoubleLootingBonusEnderWeapon) {
                     tooltip.add(Component.translatable("upgradednetherite.Ender_Weapon3.TT"));
                  }

                  if (UpgradedNetheriteConfig.EnableTeleportChest) {
                     if (ToolUtil.getDisableEffect(itemStack)) {
                        TooltipHelper.addTWO(tooltip, "upgradednetherite.Ender_Tool.TT", new Object[]{"§c• "});
                     } else {
                        TooltipHelper.addTWO(tooltip, "upgradednetherite.Ender_Tool.TT", new Object[]{"§7• "});
                     }

                     if (itemStack.getTag().getBoolean("UpgradedNetherite_Tagged")) {
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
               } else if (WaterUtil.isWaterTool(itemStack) && (UpgradedNetheriteConfig.EnableDamageBonusWaterWeapon || UpgradedNetheriteConfig.EnableDamageBonusWaterEndermanWeapon || UpgradedNetheriteConfig.EnableMiningSpeedUnderwater)) {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                  if (UpgradedNetheriteConfig.EnableDamageBonusWaterWeapon) {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Water_Weapon.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusWaterWeapon + "%"});
                  }

                  if (UpgradedNetheriteConfig.EnableDamageBonusWaterEndermanWeapon) {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Water_Weapon2.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusWaterEndermanWeapon + "%"});
                  }

                  if (UpgradedNetheriteConfig.EnableMiningSpeedUnderwater) {
                     tooltip.add(Component.translatable("upgradednetherite.Water_Tool.TT"));
                  }
               } else if (WitherUtil.isWitherTool(itemStack)) {
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
               } else if (PoisonUtil.isPoisonTool(itemStack)) {
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
               } else if (PhantomUtil.isPhantomTool(itemStack) && (UpgradedNetheriteConfig.EnableDamageBonusPhantomWeapon || UpgradedNetheriteConfig.EnableGlowingEffect || UpgradedNetheriteConfig.EnableReachEffect)) {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                  if (UpgradedNetheriteConfig.EnableDamageBonusPhantomWeapon) {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Phantom_Weapon.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusPhantomWeapon + "%"});
                  }

                  if (UpgradedNetheriteConfig.EnableGlowingEffect) {
                     if (ToolUtil.getDisableEffect(itemStack)) {
                        TooltipHelper.addTWO(tooltip, "upgradednetherite.Phantom_Tool.TT", new Object[]{"§c• "});
                     } else {
                        TooltipHelper.addTWO(tooltip, "upgradednetherite.Phantom_Tool.TT", new Object[]{"§7• "});
                     }
                  }

                  if (UpgradedNetheriteConfig.EnableReachEffect) {
                     tooltip.add(Component.translatable("upgradednetherite.Phantom_Tool2.TT"));
                  }
               } else if (FeatherUtil.isFeatherTool(itemStack) && UpgradedNetheriteConfig.EnableAttractItem) {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                  if (ToolUtil.getDisableEffect(itemStack)) {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Feather_Tool.TT", new Object[]{"§c• "});
                  } else {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Feather_Tool.TT", new Object[]{"§7• "});
                  }
               } else if (CorruptUtil.isCorruptTool(itemStack)) {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Malus.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Corrupt_Bonus2.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Corrupt_Enchant.TT"));
                  if (UpgradedNetheriteConfig.EnableDamageBonusCorruptWeapon || UpgradedNetheriteConfig.EnableLootingBonusCorruptWeapon || UpgradedNetheriteConfig.EnableFortuneBonusCorruptTool) {
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

                     if (UpgradedNetheriteConfig.EnableFortuneBonusCorruptTool) {
                        var10002 = new Object[1];
                        var10005 = CorruptUtil.intWearingCorrupt(Minecraft.getInstance().player, true);
                        var10002[0] = "§6" + var10005 * UpgradedNetheriteConfig.FortuneBonusCorruptTool;
                        TooltipHelper.addTWO(tooltip, "upgradednetherite.Corrupt_Tool2.TT", var10002);
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

                  if (UpgradedNetheriteConfig.EnableFortuneBonusCorruptTool) {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Corrupt_Tool.TT", new Object[]{"§d" + UpgradedNetheriteConfig.FortuneBonusCorruptTool});
                  }
               } else if (!EchoUtil.isEchoTool(itemStack) || !UpgradedNetheriteConfig.EnableDamageBonusEchoWeapon && !UpgradedNetheriteConfig.EnableBonusExpEcho) {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Disabled.TT"));
               } else {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                  if (UpgradedNetheriteConfig.EnableDamageBonusEchoWeapon) {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Echo_Weapon.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusEchoWeapon + "%"});
                  }

                  if (UpgradedNetheriteConfig.EnableBonusExpEcho) {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Echo_Tool2.TT", new Object[0]);
                  }
               }
            } else {
               tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
               tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
               if (UpgradedNetheriteConfig.EnableDamageBonusFireWeapon) {
                  EnchantBonus = 0.0F;
                  enchantments = EnchantmentHelper.getEnchantments(itemStack);
                  if (!enchantments.isEmpty() && enchantments.containsKey(Enchantments.FIRE_ASPECT)) {
                     EnchantLevel = (Integer)enchantments.get(Enchantments.FIRE_ASPECT);
                     EnchantBonus = (float)EnchantLevel;
                  }

                  if (EnchantBonus >= 2.0F) {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Fire_Weapon.TT", new Object[]{"§6" + (UpgradedNetheriteConfig.DamageBonusFireWeapon + UpgradedNetheriteConfig.DamageBonusFireEnchantWeapon) + "%"});
                  } else {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Fire_Weapon.TT", new Object[]{"§6" + UpgradedNetheriteConfig.DamageBonusFireWeapon + "%"});
                     tooltip.add(Component.translatable("upgradednetherite.Fire_Weapon3.TT"));
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Fire_Weapon2.TT", new Object[]{"§d" + UpgradedNetheriteConfig.DamageBonusFireEnchantWeapon + "%"});
                  }
               }

               if (UpgradedNetheriteConfig.EnableAutoSmelt) {
                  if (ToolUtil.getDisableEffect(itemStack)) {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Fire_Tool.TT", new Object[]{"§c• "});
                  } else {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Fire_Tool.TT", new Object[]{"§7• "});
                  }
               }
            }
         } else {
            tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
            tooltip.add(Component.translatable("upgradednetherite.HoldShift.TT"));
            if (EnderUtil.isEnderTool(itemStack)) {
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
