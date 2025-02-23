package net.sjhub.upgradednetheritereforged.utils;

import net.sjhub.upgradednetheritereforged.config.UpgradedNetheriteConfig;
import net.sjhub.upgradednetheritereforged.utils.check.EnderUtil;
import net.sjhub.upgradednetheritereforged.utils.check.FeatherUtil;
import net.sjhub.upgradednetheritereforged.utils.check.FireUtil;
import net.sjhub.upgradednetheritereforged.utils.check.PhantomUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import org.apache.commons.lang3.tuple.ImmutablePair;

public class ToolUtil {
   public static InteractionResult EnderSetTag(UseOnContext context) {
      if (context.getPlayer().isCrouching() && !context.getPlayer().f_19853_.isClientSide) {
         IItemHandler iitemhandler = null;
         Player player = context.getPlayer();
         ItemStack heldItem = context.getItemInHand();
         int x = context.getClickedPos().m_123341_();
         int y = context.getClickedPos().m_123342_();
         int z = context.getClickedPos().m_123343_();
         BlockPos blockpos = context.getClickedPos();
         Level world = context.getLevel();
         BlockState state = world.getBlockState(blockpos);
         if (state.m_155947_()) {
            BlockEntity tileentity = world.getBlockEntity(blockpos);
            if (tileentity != null) {
               iitemhandler = (IItemHandler)((ImmutablePair)tileentity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).map((capability) -> {
                  return ImmutablePair.of(capability, tileentity);
               }).get()).getKey();
            }
         }

         if (iitemhandler != null && !player.getCooldowns().isOnCooldown(heldItem.getItem()) && UpgradedNetheriteConfig.EnableTeleportChest) {
            String dim = world.dimension().location().getPath();
            if (heldItem.getTag() != null && heldItem.getTag().contains("UpgradedNetherite_Tagged") && heldItem.getTag().getBoolean("UpgradedNetherite_Tagged") && dim.equals(heldItem.getTag().getString("UpgradedNetherite_Dimension")) && context.getClickedPos().m_123341_() == heldItem.getTag().getIntArray("UpgradedNetherite_Position")[0] && context.getClickedPos().m_123342_() == heldItem.getTag().getIntArray("UpgradedNetherite_Position")[1] && context.getClickedPos().m_123343_() == heldItem.getTag().getIntArray("UpgradedNetherite_Position")[2]) {
               heldItem.getOrCreateTag().remove("UpgradedNetherite_Position");
               heldItem.getOrCreateTag().remove("UpgradedNetherite_Dimension");
               heldItem.getOrCreateTag().remove("UpgradedNetherite_Tagged");
               player.getCooldowns().addCooldown(heldItem.getItem(), 10);
               return InteractionResult.sidedSuccess(true);
            }

            heldItem.getOrCreateTag().putIntArray("UpgradedNetherite_Position", new int[]{x, y, z});
            heldItem.getOrCreateTag().putString("UpgradedNetherite_Dimension", player.f_19853_.dimension().location().getPath());
            heldItem.getOrCreateTag().putBoolean("UpgradedNetherite_Tagged", true);
            player.getCooldowns().addCooldown(heldItem.getItem(), 10);
            return InteractionResult.sidedSuccess(true);
         }
      }

      return InteractionResult.PASS;
   }

   public static void toggleDisableEffect(Player player) {
      ItemStack heldItem = player.getMainHandItem();
      heldItem.getOrCreateTag().putBoolean("UpgradedNetherite_DisableEffect", !getDisableEffect(heldItem));
      player.getCooldowns().addCooldown(heldItem.getItem(), 10);
   }

   public static boolean getDisableEffect(ItemStack stack) {
      return stack.getTag() != null && stack.getTag().contains("UpgradedNetherite_DisableEffect") && stack.getTag().getBoolean("UpgradedNetherite_DisableEffect");
   }

   public static boolean isUsingEffectTool(Player player) {
      return FireUtil.isFireToolOrWeapon(player.getMainHandItem()) && UpgradedNetheriteConfig.EnableAutoSmelt || EnderUtil.isEnderToolOrWeapon(player.getMainHandItem()) && UpgradedNetheriteConfig.EnableTeleportChest || PhantomUtil.isPhantomToolOrWeapon(player.getMainHandItem()) && UpgradedNetheriteConfig.EnableGlowingEffect || FeatherUtil.isFeatherToolOrWeapon(player.getMainHandItem()) && UpgradedNetheriteConfig.EnableAttractItem;
   }
}
