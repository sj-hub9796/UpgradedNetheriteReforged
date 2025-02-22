package net.sjhub.upgradednetheritereforged.content;

import com.rolfmao.upgradedcore.helpers.TooltipHelper;
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
      return CorruptUtil.isCorruptArmor(stack) && enchantment == Enchantments.f_44962_ ? false : enchantment.f_44672_.m_7454_(stack.m_41720_());
   }

   public void m_6883_(ItemStack itemStack, Level level, Entity entity, int slot, boolean p_77663_5_) {
      if (CorruptUtil.isCorruptArmor(itemStack) && itemStack.m_41793_() && EnchantmentHelper.getTagEnchantmentLevel(Enchantments.f_44962_, itemStack) > 0) {
         Map<Enchantment, Integer> enchantments = EnchantmentHelper.m_44831_(itemStack);
         if (enchantments.containsKey(Enchantments.f_44962_)) {
            enchantments.remove(Enchantments.f_44962_);
            EnchantmentHelper.m_44865_(enchantments, itemStack);
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
   public void m_7373_(ItemStack itemStack, Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
      super.m_7373_(itemStack, level, tooltip, tooltipFlag);
      if (!UpgradedNetheriteConfig.DisableTooltips) {
         if (Screen.m_96638_()) {
            Object[] var10002;
            int var10005;
            if (!GoldUtil.isGoldArmor(itemStack) || !UpgradedNetheriteConfig.EnablePiglinNeutral && !UpgradedNetheriteConfig.EnableLuckBonus) {
               if (FireUtil.isFireArmor(itemStack) && (UpgradedNetheriteConfig.EnableFireImmune || UpgradedNetheriteConfig.EnableLavaSpeed)) {
                  tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                  var10002 = new Object[1];
                  var10005 = FireUtil.intWearingFireArmor(Minecraft.m_91087_().f_91074_);
                  var10002[0] = "§a§l" + var10005 + "/" + FireUtil.setFireArmor();
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.SetBonus.TT", var10002);
                  if (UpgradedNetheriteConfig.EnableFireImmune) {
                     tooltip.add(Component.m_237115_("upgradednetherite.Fire_Bonus.TT"));
                  }

                  if (UpgradedNetheriteConfig.EnableLavaSpeed) {
                     tooltip.add(Component.m_237115_("upgradednetherite.Fire_Bonus2.TT"));
                  }
               } else if (!EnderUtil.isEnderArmor(itemStack) || !UpgradedNetheriteConfig.EnableVoidSave && !UpgradedNetheriteConfig.EnablePreventAnger) {
                  if (WaterUtil.isWaterArmor(itemStack) && (UpgradedNetheriteConfig.EnableWaterBreath || UpgradedNetheriteConfig.EnableWaterSpeed || UpgradedNetheriteConfig.EnableElderGuardianDebuffImmune)) {
                     tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                     var10002 = new Object[1];
                     var10005 = WaterUtil.intWearingWaterArmor(Minecraft.m_91087_().f_91074_);
                     var10002[0] = "§a§l" + var10005 + "/" + WaterUtil.setWaterArmor();
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.SetBonus.TT", var10002);
                     if (UpgradedNetheriteConfig.EnableWaterBreath) {
                        tooltip.add(Component.m_237115_("upgradednetherite.Water_Bonus.TT"));
                     }

                     if (UpgradedNetheriteConfig.EnableWaterSpeed) {
                        tooltip.add(Component.m_237115_("upgradednetherite.Water_Bonus2.TT"));
                     }

                     if (UpgradedNetheriteConfig.EnableElderGuardianDebuffImmune) {
                        tooltip.add(Component.m_237115_("upgradednetherite.Water_Bonus3.TT"));
                     }
                  } else if (WitherUtil.isWitherArmor(itemStack) && UpgradedNetheriteConfig.EnableWitherImmune) {
                     tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                     var10002 = new Object[1];
                     var10005 = WitherUtil.intWearingWitherArmor(Minecraft.m_91087_().f_91074_);
                     var10002[0] = "§a§l" + var10005 + "/" + WitherUtil.setWitherArmor();
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.SetBonus.TT", var10002);
                     tooltip.add(Component.m_237115_("upgradednetherite.Wither_Bonus.TT"));
                  } else if (PoisonUtil.isPoisonArmor(itemStack) && (UpgradedNetheriteConfig.EnablePoisonImmune || UpgradedNetheriteConfig.EnableClimbWall)) {
                     tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                     var10002 = new Object[1];
                     var10005 = PoisonUtil.intWearingPoisonArmor(Minecraft.m_91087_().f_91074_);
                     var10002[0] = "§a§l" + var10005 + "/" + PoisonUtil.setPoisonArmor();
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.SetBonus.TT", var10002);
                     if (UpgradedNetheriteConfig.EnablePoisonImmune) {
                        tooltip.add(Component.m_237115_("upgradednetherite.Poison_Bonus.TT"));
                     }

                     if (UpgradedNetheriteConfig.EnableClimbWall) {
                        tooltip.add(Component.m_237115_("upgradednetherite.Poison_Bonus2.TT"));
                     }
                  } else if (PhantomUtil.isPhantomArmor(itemStack) && (UpgradedNetheriteConfig.EnableFallImmune || UpgradedNetheriteConfig.EnableStepHeight)) {
                     tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                     var10002 = new Object[1];
                     var10005 = PhantomUtil.intWearingPhantomArmor(Minecraft.m_91087_().f_91074_);
                     var10002[0] = "§a§l" + var10005 + "/" + PhantomUtil.setPhantomArmor();
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.SetBonus.TT", var10002);
                     if (UpgradedNetheriteConfig.EnableFallImmune) {
                        tooltip.add(Component.m_237115_("upgradednetherite.Phantom_Bonus.TT"));
                     }

                     if (UpgradedNetheriteConfig.EnableStepHeight) {
                        tooltip.add(Component.m_237115_("upgradednetherite.Phantom_Bonus2.TT"));
                     }
                  } else if (!FeatherUtil.isFeatherArmor(itemStack) || !UpgradedNetheriteConfig.EnableWaterLavaWalking && !UpgradedNetheriteConfig.EnableMultiJump && !UpgradedNetheriteConfig.EnableReduceFallDamage && !UpgradedNetheriteConfig.EnableLevitationImmune) {
                     if (CorruptUtil.isCorruptArmor(itemStack)) {
                        tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                        tooltip.add(Component.m_237115_("upgradednetherite.Malus.TT"));
                        if (UpgradedNetheriteConfig.EnableHealthMalus) {
                           TooltipHelper.addTWO(tooltip, "upgradednetherite.Corrupt_Bonus.TT", new Object[]{"§6" + UpgradedNetheriteConfig.HealthMalus + "%"});
                        }

                        tooltip.add(Component.m_237115_("upgradednetherite.Corrupt_Bonus2.TT"));
                        tooltip.add(Component.m_237115_("upgradednetherite.Corrupt_Enchant.TT"));
                     } else if (EchoUtil.isEchoArmor(itemStack) && (UpgradedNetheriteConfig.EnableKeepItemsChance || UpgradedNetheriteConfig.EnableReduceDamageEchoArmor || UpgradedNetheriteConfig.EnableHealEchoArmor)) {
                        tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                        var10002 = new Object[1];
                        var10005 = EchoUtil.intWearingEchoArmor(Minecraft.m_91087_().f_91074_);
                        var10002[0] = "§a§l" + var10005 + "/" + EchoUtil.setEchoArmor();
                        TooltipHelper.addTWO(tooltip, "upgradednetherite.SetBonus.TT", var10002);
                        if (UpgradedNetheriteConfig.EnableKeepItemsChance) {
                           TooltipHelper.addTWO(tooltip, "upgradednetherite.Echo_Bonus.TT", new Object[]{"§6" + UpgradedNetheriteConfig.KeepItemsChance + "%"});
                        }

                        if (UpgradedNetheriteConfig.EnableReduceDamageEchoArmor) {
                           TooltipHelper.addTWO(tooltip, "upgradednetherite.Echo_Bonus2.TT", new Object[]{"§6" + UpgradedNetheriteConfig.ReduceDamageEchoArmor + "%"});
                        }

                        if (UpgradedNetheriteConfig.EnableHealEchoArmor) {
                           tooltip.add(Component.m_237115_("upgradednetherite.Echo_Bonus3.TT"));
                        }
                     } else {
                        tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                        tooltip.add(Component.m_237115_("upgradednetherite.Disabled.TT"));
                     }
                  } else {
                     tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                     var10002 = new Object[1];
                     var10005 = FeatherUtil.intWearingFeatherArmor(Minecraft.m_91087_().f_91074_);
                     var10002[0] = "§a§l" + var10005 + "/" + FeatherUtil.setFeatherArmor();
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.SetBonus.TT", var10002);
                     if (UpgradedNetheriteConfig.EnableWaterLavaWalking) {
                        tooltip.add(Component.m_237115_("upgradednetherite.Feather_Bonus.TT"));
                     }

                     if (UpgradedNetheriteConfig.EnableMultiJump) {
                        tooltip.add(Component.m_237115_("upgradednetherite.Feather_Bonus2.TT"));
                     }

                     if (UpgradedNetheriteConfig.EnableReduceFallDamage) {
                        tooltip.add(Component.m_237115_("upgradednetherite.Feather_Bonus3.TT"));
                     }

                     if (UpgradedNetheriteConfig.EnableLevitationImmune) {
                        tooltip.add(Component.m_237115_("upgradednetherite.Feather_Bonus4.TT"));
                     }
                  }
               } else {
                  tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                  var10002 = new Object[1];
                  var10005 = EnderUtil.intWearingEnderArmor(Minecraft.m_91087_().f_91074_);
                  var10002[0] = "§a§l" + var10005 + "/" + EnderUtil.setEnderArmor();
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.SetBonus.TT", var10002);
                  if (UpgradedNetheriteConfig.EnableVoidSave) {
                     tooltip.add(Component.m_237115_("upgradednetherite.Ender_Bonus.TT"));
                  }

                  if (UpgradedNetheriteConfig.EnablePreventAnger) {
                     tooltip.add(Component.m_237115_("upgradednetherite.Ender_Bonus2.TT"));
                  }

                  if (UpgradedNetheriteConfig.EnableBreakEnderArmor && UpgradedNetheriteConfig.EnableVoidSave) {
                     tooltip.add(Component.m_237115_("upgradednetherite.Ender_Bonus3.TT"));
                  }
               }
            } else {
               tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
               var10002 = new Object[1];
               var10005 = GoldUtil.intWearingGoldArmor(Minecraft.m_91087_().f_91074_);
               var10002[0] = "§a§l" + var10005 + "/" + GoldUtil.setGoldArmor();
               TooltipHelper.addTWO(tooltip, "upgradednetherite.SetBonus.TT", var10002);
               if (UpgradedNetheriteConfig.EnablePiglinNeutral) {
                  tooltip.add(Component.m_237115_("upgradednetherite.Gold_Bonus.TT"));
               }

               if (UpgradedNetheriteConfig.EnableLuckBonus) {
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Bonus2.TT", new Object[]{"§6" + UpgradedNetheriteConfig.LuckBonus});
               }
            }
         } else {
            tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
            tooltip.add(Component.m_237115_("upgradednetherite.HoldShift.TT"));
         }
      }

   }
}
