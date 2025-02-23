package net.sjhub.upgradednetheritereforged.content;

import com.rolfmao.upgradedcore_old.helpers.TooltipHelper;
import net.sjhub.upgradednetheritereforged.config.UpgradedNetheriteConfig;
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
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.EnderMan;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class UpgradedNetheriteArmor extends ArmorItem {
   public UpgradedNetheriteArmor(ArmorMaterial materialIn, Type slot, Properties builderIn) {
      super(materialIn, slot, builderIn);
   }

   public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
      return CorruptUtil.isCorruptArmor(stack) && enchantment == Enchantments.MENDING ? false : enchantment.category.canEnchant(stack.getItem());
   }

   public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int slot, boolean p_77663_5_) {
      if (CorruptUtil.isCorruptArmor(itemStack) && itemStack.isEnchanted() && EnchantmentHelper.getTagEnchantmentLevel(Enchantments.MENDING, itemStack) > 0) {
         Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(itemStack);
         if (enchantments.containsKey(Enchantments.MENDING)) {
            enchantments.remove(Enchantments.MENDING);
            EnchantmentHelper.setEnchantments(enchantments, itemStack);
         }
      }

   }

   public boolean makesPiglinsNeutral(ItemStack itemStack, LivingEntity wearer) {
      return GoldUtil.isWearingGoldArmor((Player)wearer) && UpgradedNetheriteConfig.EnablePiglinNeutral;
   }

   public boolean isEnderMask(ItemStack itemStack, Player player, EnderMan endermanEntity) {
      return EnderUtil.isWearingEnderArmor(player) && UpgradedNetheriteConfig.EnablePreventAnger;
   }

   public boolean canWalkOnPowderedSnow(ItemStack itemStack, LivingEntity wearer) {
      return FeatherUtil.isWearingFeatherArmor((Player)wearer);
   }

   @OnlyIn(Dist.CLIENT)
   public void appendHoverText(ItemStack itemStack, Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
      super.appendHoverText(itemStack, level, tooltip, tooltipFlag);
      if (!UpgradedNetheriteConfig.DisableTooltips) {
         if (Screen.hasShiftDown()) {
            Object[] var10002;
            int var10005;
            if (!GoldUtil.isGoldArmor(itemStack) || !UpgradedNetheriteConfig.EnablePiglinNeutral && !UpgradedNetheriteConfig.EnableLuckBonus) {
               if (FireUtil.isFireArmor(itemStack) && (UpgradedNetheriteConfig.EnableFireImmune || UpgradedNetheriteConfig.EnableLavaSpeed)) {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  var10002 = new Object[1];
                  var10005 = FireUtil.intWearingFireArmor(Minecraft.getInstance().player);
                  var10002[0] = "§a§l" + var10005 + "/" + FireUtil.setFireArmor();
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.SetBonus.TT", var10002);
                  if (UpgradedNetheriteConfig.EnableFireImmune) {
                     tooltip.add(Component.translatable("upgradednetherite.Fire_Bonus.TT"));
                  }

                  if (UpgradedNetheriteConfig.EnableLavaSpeed) {
                     tooltip.add(Component.translatable("upgradednetherite.Fire_Bonus2.TT"));
                  }
               } else if (!EnderUtil.isEnderArmor(itemStack) || !UpgradedNetheriteConfig.EnableVoidSave && !UpgradedNetheriteConfig.EnablePreventAnger) {
                  if (WaterUtil.isWaterArmor(itemStack) && (UpgradedNetheriteConfig.EnableWaterBreath || UpgradedNetheriteConfig.EnableWaterSpeed || UpgradedNetheriteConfig.EnableElderGuardianDebuffImmune)) {
                     tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                     var10002 = new Object[1];
                     var10005 = WaterUtil.intWearingWaterArmor(Minecraft.getInstance().player);
                     var10002[0] = "§a§l" + var10005 + "/" + WaterUtil.setWaterArmor();
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.SetBonus.TT", var10002);
                     if (UpgradedNetheriteConfig.EnableWaterBreath) {
                        tooltip.add(Component.translatable("upgradednetherite.Water_Bonus.TT"));
                     }

                     if (UpgradedNetheriteConfig.EnableWaterSpeed) {
                        tooltip.add(Component.translatable("upgradednetherite.Water_Bonus2.TT"));
                     }

                     if (UpgradedNetheriteConfig.EnableElderGuardianDebuffImmune) {
                        tooltip.add(Component.translatable("upgradednetherite.Water_Bonus3.TT"));
                     }
                  } else if (WitherUtil.isWitherArmor(itemStack) && UpgradedNetheriteConfig.EnableWitherImmune) {
                     tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                     var10002 = new Object[1];
                     var10005 = WitherUtil.intWearingWitherArmor(Minecraft.getInstance().player);
                     var10002[0] = "§a§l" + var10005 + "/" + WitherUtil.setWitherArmor();
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.SetBonus.TT", var10002);
                     tooltip.add(Component.translatable("upgradednetherite.Wither_Bonus.TT"));
                  } else if (PoisonUtil.isPoisonArmor(itemStack) && (UpgradedNetheriteConfig.EnablePoisonImmune || UpgradedNetheriteConfig.EnableClimbWall)) {
                     tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                     var10002 = new Object[1];
                     var10005 = PoisonUtil.intWearingPoisonArmor(Minecraft.getInstance().player);
                     var10002[0] = "§a§l" + var10005 + "/" + PoisonUtil.setPoisonArmor();
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.SetBonus.TT", var10002);
                     if (UpgradedNetheriteConfig.EnablePoisonImmune) {
                        tooltip.add(Component.translatable("upgradednetherite.Poison_Bonus.TT"));
                     }

                     if (UpgradedNetheriteConfig.EnableClimbWall) {
                        tooltip.add(Component.translatable("upgradednetherite.Poison_Bonus2.TT"));
                     }
                  } else if (PhantomUtil.isPhantomArmor(itemStack) && (UpgradedNetheriteConfig.EnableFallImmune || UpgradedNetheriteConfig.EnableStepHeight)) {
                     tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                     var10002 = new Object[1];
                     var10005 = PhantomUtil.intWearingPhantomArmor(Minecraft.getInstance().player);
                     var10002[0] = "§a§l" + var10005 + "/" + PhantomUtil.setPhantomArmor();
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.SetBonus.TT", var10002);
                     if (UpgradedNetheriteConfig.EnableFallImmune) {
                        tooltip.add(Component.translatable("upgradednetherite.Phantom_Bonus.TT"));
                     }

                     if (UpgradedNetheriteConfig.EnableStepHeight) {
                        tooltip.add(Component.translatable("upgradednetherite.Phantom_Bonus2.TT"));
                     }
                  } else if (!FeatherUtil.isFeatherArmor(itemStack) || !UpgradedNetheriteConfig.EnableWaterLavaWalking && !UpgradedNetheriteConfig.EnableMultiJump && !UpgradedNetheriteConfig.EnableReduceFallDamage && !UpgradedNetheriteConfig.EnableLevitationImmune) {
                     if (CorruptUtil.isCorruptArmor(itemStack)) {
                        tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                        tooltip.add(Component.translatable("upgradednetherite.Malus.TT"));
                        if (UpgradedNetheriteConfig.EnableHealthMalus) {
                           TooltipHelper.addTWO(tooltip, "upgradednetherite.Corrupt_Bonus.TT", new Object[]{"§6" + UpgradedNetheriteConfig.HealthMalus + "%"});
                        }

                        tooltip.add(Component.translatable("upgradednetherite.Corrupt_Bonus2.TT"));
                        tooltip.add(Component.translatable("upgradednetherite.Corrupt_Enchant.TT"));
                     } else if (EchoUtil.isEchoArmor(itemStack) && (UpgradedNetheriteConfig.EnableKeepItemsChance || UpgradedNetheriteConfig.EnableReduceDamageEchoArmor || UpgradedNetheriteConfig.EnableHealEchoArmor)) {
                        tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                        var10002 = new Object[1];
                        var10005 = EchoUtil.intWearingEchoArmor(Minecraft.getInstance().player);
                        var10002[0] = "§a§l" + var10005 + "/" + EchoUtil.setEchoArmor();
                        TooltipHelper.addTWO(tooltip, "upgradednetherite.SetBonus.TT", var10002);
                        if (UpgradedNetheriteConfig.EnableKeepItemsChance) {
                           TooltipHelper.addTWO(tooltip, "upgradednetherite.Echo_Bonus.TT", new Object[]{"§6" + UpgradedNetheriteConfig.KeepItemsChance + "%"});
                        }

                        if (UpgradedNetheriteConfig.EnableReduceDamageEchoArmor) {
                           TooltipHelper.addTWO(tooltip, "upgradednetherite.Echo_Bonus2.TT", new Object[]{"§6" + UpgradedNetheriteConfig.ReduceDamageEchoArmor + "%"});
                        }

                        if (UpgradedNetheriteConfig.EnableHealEchoArmor) {
                           tooltip.add(Component.translatable("upgradednetherite.Echo_Bonus3.TT"));
                        }
                     } else {
                        tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                        tooltip.add(Component.translatable("upgradednetherite.Disabled.TT"));
                     }
                  } else {
                     tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                     var10002 = new Object[1];
                     var10005 = FeatherUtil.intWearingFeatherArmor(Minecraft.getInstance().player);
                     var10002[0] = "§a§l" + var10005 + "/" + FeatherUtil.setFeatherArmor();
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.SetBonus.TT", var10002);
                     if (UpgradedNetheriteConfig.EnableWaterLavaWalking) {
                        tooltip.add(Component.translatable("upgradednetherite.Feather_Bonus.TT"));
                     }

                     if (UpgradedNetheriteConfig.EnableMultiJump) {
                        tooltip.add(Component.translatable("upgradednetherite.Feather_Bonus2.TT"));
                     }

                     if (UpgradedNetheriteConfig.EnableReduceFallDamage) {
                        tooltip.add(Component.translatable("upgradednetherite.Feather_Bonus3.TT"));
                     }

                     if (UpgradedNetheriteConfig.EnableLevitationImmune) {
                        tooltip.add(Component.translatable("upgradednetherite.Feather_Bonus4.TT"));
                     }
                  }
               } else {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  var10002 = new Object[1];
                  var10005 = EnderUtil.intWearingEnderArmor(Minecraft.getInstance().player);
                  var10002[0] = "§a§l" + var10005 + "/" + EnderUtil.setEnderArmor();
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.SetBonus.TT", var10002);
                  if (UpgradedNetheriteConfig.EnableVoidSave) {
                     tooltip.add(Component.translatable("upgradednetherite.Ender_Bonus.TT"));
                  }

                  if (UpgradedNetheriteConfig.EnablePreventAnger) {
                     tooltip.add(Component.translatable("upgradednetherite.Ender_Bonus2.TT"));
                  }

                  if (UpgradedNetheriteConfig.EnableBreakEnderArmor && UpgradedNetheriteConfig.EnableVoidSave) {
                     tooltip.add(Component.translatable("upgradednetherite.Ender_Bonus3.TT"));
                  }
               }
            } else {
               tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
               var10002 = new Object[1];
               var10005 = GoldUtil.intWearingGoldArmor(Minecraft.getInstance().player);
               var10002[0] = "§a§l" + var10005 + "/" + GoldUtil.setGoldArmor();
               TooltipHelper.addTWO(tooltip, "upgradednetherite.SetBonus.TT", var10002);
               if (UpgradedNetheriteConfig.EnablePiglinNeutral) {
                  tooltip.add(Component.translatable("upgradednetherite.Gold_Bonus.TT"));
               }

               if (UpgradedNetheriteConfig.EnableLuckBonus) {
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Bonus2.TT", new Object[]{"§6" + UpgradedNetheriteConfig.LuckBonus});
               }
            }
         } else {
            tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
            tooltip.add(Component.translatable("upgradednetherite.HoldShift.TT"));
         }
      }

   }
}
