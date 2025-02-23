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
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class UpgradedNetheriteShovel extends ShovelItem {
   public UpgradedNetheriteShovel(Tier tier, float attackDamageIn, float attackSpeedIn, Properties properties) {
      super(tier, attackDamageIn, attackSpeedIn, properties);
   }

   public boolean canApplyAtEnchantingTable(ItemStack itemStack, Enchantment enchantment) {
      return itemStack.getItem() == ModItems.CORRUPT_UPGRADED_NETHERITE_SHOVEL.get() && enchantment == Enchantments.MENDING ? false : enchantment.category.canEnchant(itemStack.getItem());
   }

   public void inventoryTick(ItemStack itemStack, Level level, Entity entity, int slot, boolean p_77663_5_) {
      if (itemStack.getItem() == ModItems.CORRUPT_UPGRADED_NETHERITE_SHOVEL.get() && itemStack.isEnchanted() && EnchantmentHelper.getTagEnchantmentLevel(Enchantments.MENDING, itemStack) > 0) {
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
      if (iResult == InteractionResult.PASS && context.getPlayer() != null && context.getPlayer().isCrouching() && EnderUtil.isEnderTool(context.getItemInHand()) && !context.getPlayer().getCooldowns().isOnCooldown(context.getItemInHand().getItem())) {
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
            if (GoldUtil.isGoldTool(itemStack) && UpgradedNetheriteConfig.EnableFortuneBonus) {
               tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
               tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
               float EnchantBonus = 0.0F;
               Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(itemStack);
               if (!enchantments.isEmpty() && enchantments.containsKey(Enchantments.BLOCK_FORTUNE)) {
                  int EnchantLevel = (Integer)enchantments.get(Enchantments.BLOCK_FORTUNE);
                  EnchantBonus = (float)EnchantLevel;
               }

               if (EnchantBonus >= 3.0F) {
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Tool.TT", new Object[]{"§6" + (UpgradedNetheriteConfig.FortuneBonus + UpgradedNetheriteConfig.FortuneEnchantBonus)});
               } else {
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Tool.TT", new Object[]{"§6" + UpgradedNetheriteConfig.FortuneBonus});
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Tool2.TT", new Object[]{"§d" + UpgradedNetheriteConfig.FortuneEnchantBonus});
               }
            } else if (FireUtil.isFireTool(itemStack) && UpgradedNetheriteConfig.EnableAutoSmelt) {
               tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
               tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
               if (ToolUtil.getDisableEffect(itemStack)) {
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Fire_Tool.TT", new Object[]{"§c• "});
               } else {
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Fire_Tool.TT", new Object[]{"§7• "});
               }
            } else if (EnderUtil.isEnderTool(itemStack) && UpgradedNetheriteConfig.EnableTeleportChest) {
               tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
               tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
               if (ToolUtil.getDisableEffect(itemStack)) {
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Ender_Tool.TT", new Object[]{"§c• "});
               } else {
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Ender_Tool.TT", new Object[]{"§7• "});
               }

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
            } else if (WaterUtil.isWaterTool(itemStack) && UpgradedNetheriteConfig.EnableMiningSpeedUnderwater) {
               tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
               tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
               tooltip.add(Component.translatable("upgradednetherite.Water_Tool.TT"));
            } else if (PhantomUtil.isPhantomTool(itemStack) && (UpgradedNetheriteConfig.EnableGlowingEffect || UpgradedNetheriteConfig.EnableReachEffect)) {
               tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
               tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
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
               if (UpgradedNetheriteConfig.EnableFortuneBonusCorruptTool) {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                  if (Minecraft.getInstance().player != null && CorruptUtil.intWearingCorrupt(Minecraft.getInstance().player, true) > 0) {
                     Object[] var10002 = new Object[1];
                     int var10005 = CorruptUtil.intWearingCorrupt(Minecraft.getInstance().player, true);
                     var10002[0] = "§6" + var10005 * UpgradedNetheriteConfig.FortuneBonusCorruptTool;
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Corrupt_Tool2.TT", var10002);
                  }

                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Corrupt_Tool.TT", new Object[]{"§d" + UpgradedNetheriteConfig.FortuneBonusCorruptTool});
               }
            } else if (EchoUtil.isEchoTool(itemStack) && UpgradedNetheriteConfig.EnableBonusExpEcho) {
               tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
               tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
               if (UpgradedNetheriteConfig.EnableBonusExpEcho) {
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Echo_Tool2.TT", new Object[0]);
               }
            } else {
               tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
               tooltip.add(Component.translatable("upgradednetherite.Disabled.TT"));
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
