package net.sjhub.upgradednetheritereforged.content;

import com.rolfmao.upgradedcore.helpers.TooltipHelper;
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
import net.minecraft.world.item.HoeItem;
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

public class UpgradedNetheriteHoe extends HoeItem {
   public UpgradedNetheriteHoe(Tier tier, int attackDamageIn, float attackSpeedIn, Properties properties) {
      super(tier, attackDamageIn, attackSpeedIn, properties);
   }

   public boolean canApplyAtEnchantingTable(ItemStack itemStack, Enchantment enchantment) {
      return itemStack.m_41720_() == ModItems.CORRUPT_UPGRADED_NETHERITE_HOE.get() && enchantment == Enchantments.f_44962_ ? false : enchantment.f_44672_.m_7454_(itemStack.m_41720_());
   }

   public void m_6883_(ItemStack itemStack, Level level, Entity entity, int slot, boolean p_77663_5_) {
      if (itemStack.m_41720_() == ModItems.CORRUPT_UPGRADED_NETHERITE_HOE.get() && itemStack.m_41793_() && EnchantmentHelper.getTagEnchantmentLevel(Enchantments.f_44962_, itemStack) > 0) {
         Map<Enchantment, Integer> enchantments = EnchantmentHelper.m_44831_(itemStack);
         if (enchantments.containsKey(Enchantments.f_44962_)) {
            enchantments.remove(Enchantments.f_44962_);
            EnchantmentHelper.m_44865_(enchantments, itemStack);
         }
      }

   }

   public InteractionResultHolder<ItemStack> m_7203_(Level level, Player player, InteractionHand interactionHand) {
      ItemStack stack = player.m_21205_();
      if (player.m_6047_() && !player.m_36335_().m_41519_(stack.m_41720_()) && ToolUtil.isUsingEffectTool(player)) {
         ToolUtil.toggleDisableEffect(player);
         return new InteractionResultHolder(InteractionResult.SUCCESS, stack);
      } else {
         return super.m_7203_(level, player, interactionHand);
      }
   }

   public InteractionResult m_6225_(UseOnContext context) {
      InteractionResult iResult = super.m_6225_(context);
      if (iResult == InteractionResult.PASS && context.m_43723_() != null && context.m_43723_().m_6047_() && EnderUtil.isEnderTool(context.m_43722_()) && !context.m_43723_().m_36335_().m_41519_(context.m_43722_().m_41720_())) {
         iResult = ToolUtil.EnderSetTag(context);
      }

      if (iResult == InteractionResult.PASS) {
         iResult = super.m_7203_(context.m_43725_(), context.m_43723_(), context.m_43724_()).m_19089_();
      }

      return iResult;
   }

   @OnlyIn(Dist.CLIENT)
   public void m_7373_(ItemStack itemStack, Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
      super.m_7373_(itemStack, level, tooltip, tooltipFlag);
      if (!UpgradedNetheriteConfig.DisableTooltips) {
         if (Screen.m_96638_()) {
            if (GoldUtil.isGoldTool(itemStack) && UpgradedNetheriteConfig.EnableFortuneBonus) {
               tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
               tooltip.add(Component.m_237115_("upgradednetherite.Bonus.TT"));
               float EnchantBonus = 0.0F;
               Map<Enchantment, Integer> enchantments = EnchantmentHelper.m_44831_(itemStack);
               if (!enchantments.isEmpty() && enchantments.containsKey(Enchantments.f_44987_)) {
                  int EnchantLevel = (Integer)enchantments.get(Enchantments.f_44987_);
                  EnchantBonus = (float)EnchantLevel;
               }

               if (EnchantBonus >= 3.0F) {
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Tool.TT", new Object[]{"§6" + (UpgradedNetheriteConfig.FortuneBonus + UpgradedNetheriteConfig.FortuneEnchantBonus)});
               } else {
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Tool.TT", new Object[]{"§6" + UpgradedNetheriteConfig.FortuneBonus});
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Tool2.TT", new Object[]{"§d" + UpgradedNetheriteConfig.FortuneEnchantBonus});
               }

               tooltip.add(Component.m_237115_("upgradednetherite.Gold_Hoe.TT"));
            } else if (FireUtil.isFireTool(itemStack) && UpgradedNetheriteConfig.EnableAutoSmelt) {
               tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
               tooltip.add(Component.m_237115_("upgradednetherite.Bonus.TT"));
               if (ToolUtil.getDisableEffect(itemStack)) {
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Fire_Tool.TT", new Object[]{"§c• "});
               } else {
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Fire_Tool.TT", new Object[]{"§7• "});
               }
            } else if (EnderUtil.isEnderTool(itemStack) && UpgradedNetheriteConfig.EnableTeleportChest) {
               tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
               tooltip.add(Component.m_237115_("upgradednetherite.Bonus.TT"));
               if (ToolUtil.getDisableEffect(itemStack)) {
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Ender_Tool.TT", new Object[]{"§c• "});
               } else {
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Ender_Tool.TT", new Object[]{"§7• "});
               }

               if (itemStack.m_41783_() != null && itemStack.m_41783_().m_128441_("UpgradedNetherite_Tagged") && itemStack.m_41783_().m_128471_("UpgradedNetherite_Tagged")) {
                  String world = level.m_46472_().m_135782_().m_135815_();
                  String var10001;
                  if (!world.equals(itemStack.m_41783_().m_128461_("UpgradedNetherite_Dimension"))) {
                     tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                     tooltip.add(Component.m_237115_("upgradednetherite.Target.TT"));
                     tooltip.add(Component.m_237115_("upgradednetherite.Ender_Dim.TT"));
                     var10001 = itemStack.m_41783_().m_128461_("UpgradedNetherite_Dimension");
                     tooltip.add(Component.m_237113_("§7• §c" + var10001 + "§7 : §3" + itemStack.m_41783_().m_128465_("UpgradedNetherite_Position")[0] + "§7, §3" + itemStack.m_41783_().m_128465_("UpgradedNetherite_Position")[1] + "§7, §3" + itemStack.m_41783_().m_128465_("UpgradedNetherite_Position")[2] + "§7."));
                  } else {
                     tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                     tooltip.add(Component.m_237115_("upgradednetherite.Target.TT"));
                     var10001 = itemStack.m_41783_().m_128461_("UpgradedNetherite_Dimension");
                     tooltip.add(Component.m_237113_("§7• §9" + var10001 + "§7 : §3" + itemStack.m_41783_().m_128465_("UpgradedNetherite_Position")[0] + "§7, §3" + itemStack.m_41783_().m_128465_("UpgradedNetherite_Position")[1] + "§7, §3" + itemStack.m_41783_().m_128465_("UpgradedNetherite_Position")[2] + "§7."));
                  }
               }
            } else if (WaterUtil.isWaterTool(itemStack) && UpgradedNetheriteConfig.EnableMiningSpeedUnderwater) {
               tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
               tooltip.add(Component.m_237115_("upgradednetherite.Bonus.TT"));
               tooltip.add(Component.m_237115_("upgradednetherite.Water_Tool.TT"));
            } else if (PoisonUtil.isPoisonTool(itemStack)) {
               tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
               tooltip.add(Component.m_237115_("upgradednetherite.Bonus.TT"));
               tooltip.add(Component.m_237115_("upgradednetherite.Poison_Hoe.TT"));
            } else if (PhantomUtil.isPhantomTool(itemStack) && (UpgradedNetheriteConfig.EnableGlowingEffect || UpgradedNetheriteConfig.EnableReachEffect)) {
               tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
               tooltip.add(Component.m_237115_("upgradednetherite.Bonus.TT"));
               if (UpgradedNetheriteConfig.EnableGlowingEffect) {
                  if (ToolUtil.getDisableEffect(itemStack)) {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Phantom_Tool.TT", new Object[]{"§c• "});
                  } else {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Phantom_Tool.TT", new Object[]{"§7• "});
                  }
               }

               if (UpgradedNetheriteConfig.EnableReachEffect) {
                  tooltip.add(Component.m_237115_("upgradednetherite.Phantom_Tool2.TT"));
               }
            } else if (FeatherUtil.isFeatherTool(itemStack) && UpgradedNetheriteConfig.EnableAttractItem) {
               tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
               tooltip.add(Component.m_237115_("upgradednetherite.Bonus.TT"));
               if (ToolUtil.getDisableEffect(itemStack)) {
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Feather_Tool.TT", new Object[]{"§c• "});
               } else {
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Feather_Tool.TT", new Object[]{"§7• "});
               }
            } else if (CorruptUtil.isCorruptTool(itemStack)) {
               tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
               tooltip.add(Component.m_237115_("upgradednetherite.Malus.TT"));
               tooltip.add(Component.m_237115_("upgradednetherite.Corrupt_Bonus2.TT"));
               tooltip.add(Component.m_237115_("upgradednetherite.Corrupt_Enchant.TT"));
               if (UpgradedNetheriteConfig.EnableFortuneBonusCorruptTool) {
                  tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.m_237115_("upgradednetherite.Bonus.TT"));
                  if (Minecraft.m_91087_().f_91074_ != null && CorruptUtil.intWearingCorrupt(Minecraft.m_91087_().f_91074_, true) > 0) {
                     Object[] var10002 = new Object[1];
                     int var10005 = CorruptUtil.intWearingCorrupt(Minecraft.m_91087_().f_91074_, true);
                     var10002[0] = "§6" + var10005 * UpgradedNetheriteConfig.FortuneBonusCorruptTool;
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Corrupt_Tool2.TT", var10002);
                  }

                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Corrupt_Tool.TT", new Object[]{"§d" + UpgradedNetheriteConfig.FortuneBonusCorruptTool});
               }
            } else if (EchoUtil.isEchoTool(itemStack) && UpgradedNetheriteConfig.EnableBonusExpEcho) {
               tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
               tooltip.add(Component.m_237115_("upgradednetherite.Bonus.TT"));
               if (UpgradedNetheriteConfig.EnableBonusExpEcho) {
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Echo_Tool2.TT", new Object[0]);
               }
            } else {
               tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
               tooltip.add(Component.m_237115_("upgradednetherite.Disabled.TT"));
            }
         } else {
            tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
            tooltip.add(Component.m_237115_("upgradednetherite.HoldShift.TT"));
            if (EnderUtil.isEnderTool(itemStack)) {
               tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
               if (itemStack.m_41783_() != null && itemStack.m_41783_().m_128441_("UpgradedNetherite_Tagged") && itemStack.m_41783_().m_128471_("UpgradedNetherite_Tagged")) {
                  tooltip.add(Component.m_237115_("upgradednetherite.Ender_ToolTar.TT"));
               } else {
                  tooltip.add(Component.m_237115_("upgradednetherite.Ender_ToolReq.TT"));
               }
            }
         }
      }

   }
}
