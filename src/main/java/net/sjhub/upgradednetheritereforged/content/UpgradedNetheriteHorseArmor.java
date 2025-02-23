package net.sjhub.upgradednetheritereforged.content;

import com.rolfmao.upgradedcore_old.helpers.TooltipHelper;
import net.sjhub.upgradednetheritereforged.config.UpgradedNetheriteConfig;
import net.sjhub.upgradednetheritereforged.init.ModItems;
import java.util.List;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class UpgradedNetheriteHorseArmor extends HorseArmorItem {
   public UpgradedNetheriteHorseArmor(int armorValue, String namespaceIn, String pathIn, Properties properties) {
      super(armorValue, new ResourceLocation(namespaceIn, pathIn), properties);
   }

   @OnlyIn(Dist.CLIENT)
   public void appendHoverText(ItemStack itemStack, Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
      super.appendHoverText(itemStack, level, tooltip, tooltipFlag);
      if (!UpgradedNetheriteConfig.DisableTooltips) {
         Item horseArmor = itemStack.getItem();
         if (horseArmor != ModItems.NETHERITE_ARMOR_HORSE.get()) {
            if (Screen.hasShiftDown()) {
               if (horseArmor != ModItems.CORRUPT_UPGRADED_NETHERITE_ARMOR_HORSE.get()) {
               }

               if (horseArmor == ModItems.GOLD_UPGRADED_NETHERITE_ARMOR_HORSE.get()) {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Gold_Horse_Bonus.TT"));
               }

               if (horseArmor == ModItems.FIRE_UPGRADED_NETHERITE_ARMOR_HORSE.get() && (UpgradedNetheriteConfig.EnableLavaSpeed || UpgradedNetheriteConfig.EnableFireImmune)) {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                  if (UpgradedNetheriteConfig.EnableFireImmune) {
                     tooltip.add(Component.translatable("upgradednetherite.Fire_Bonus.TT"));
                  }

                  if (UpgradedNetheriteConfig.EnableLavaSpeed) {
                     tooltip.add(Component.translatable("upgradednetherite.Fire_Bonus2.TT"));
                  }
               }

               if (horseArmor == ModItems.ENDER_UPGRADED_NETHERITE_ARMOR_HORSE.get() && UpgradedNetheriteConfig.EnableVoidSave) {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Ender_Bonus.TT"));
               }

               if (horseArmor == ModItems.WATER_UPGRADED_NETHERITE_ARMOR_HORSE.get() && (UpgradedNetheriteConfig.EnableWaterLavaWalking || UpgradedNetheriteConfig.EnableWaterSpeed)) {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                  if (UpgradedNetheriteConfig.EnableWaterBreath) {
                     tooltip.add(Component.translatable("upgradednetherite.Water_Bonus.TT"));
                  }

                  if (UpgradedNetheriteConfig.EnableWaterSpeed) {
                     tooltip.add(Component.translatable("upgradednetherite.Water_Bonus2.TT"));
                  }
               }

               if (horseArmor == ModItems.WITHER_UPGRADED_NETHERITE_ARMOR_HORSE.get() && UpgradedNetheriteConfig.EnableWitherImmune) {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Wither_Bonus.TT"));
               }

               if (horseArmor == ModItems.POISON_UPGRADED_NETHERITE_ARMOR_HORSE.get() && (UpgradedNetheriteConfig.EnablePoisonImmune || UpgradedNetheriteConfig.EnableClimbWall)) {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                  if (UpgradedNetheriteConfig.EnablePoisonImmune) {
                     tooltip.add(Component.translatable("upgradednetherite.Poison_Bonus.TT"));
                  }

                  if (UpgradedNetheriteConfig.EnableClimbWall) {
                     tooltip.add(Component.translatable("upgradednetherite.Poison_Bonus2.TT"));
                  }
               }

               if (horseArmor == ModItems.PHANTOM_UPGRADED_NETHERITE_ARMOR_HORSE.get() && UpgradedNetheriteConfig.EnableFallImmune) {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Phantom_Bonus.TT"));
               }

               if (horseArmor == ModItems.FEATHER_UPGRADED_NETHERITE_ARMOR_HORSE.get() && (UpgradedNetheriteConfig.EnableWaterLavaWalking || UpgradedNetheriteConfig.EnableReduceFallDamage)) {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                  if (UpgradedNetheriteConfig.EnableWaterLavaWalking) {
                     tooltip.add(Component.translatable("upgradednetherite.Feather_Bonus.TT"));
                  }

                  if (UpgradedNetheriteConfig.EnableReduceFallDamage) {
                     tooltip.add(Component.translatable("upgradednetherite.Feather_Bonus3.TT"));
                  }
               }

               if (horseArmor == ModItems.CORRUPT_UPGRADED_NETHERITE_ARMOR_HORSE.get()) {
                  tooltip.add(Component.translatable("upgradednetherite.Malus.TT"));
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Corrupt_Horse_Bonus.TT", new Object[]{"§6" + UpgradedNetheriteConfig.HealthMalus + "%"});
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Corrupt_Horse_Bonus2.TT"));
               }

               if (horseArmor == ModItems.ECHO_UPGRADED_NETHERITE_ARMOR_HORSE.get() && (UpgradedNetheriteConfig.EnableReduceDamageEchoArmor || UpgradedNetheriteConfig.EnableHealEchoArmor)) {
                  tooltip.add(Component.translatable("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.translatable("upgradednetherite.Bonus.TT"));
                  if (UpgradedNetheriteConfig.EnableReduceDamageEchoArmor) {
                     TooltipHelper.addTWO(tooltip, "upgradednetherite.Echo_Bonus2.TT", new Object[]{"§6" + UpgradedNetheriteConfig.ReduceDamageEchoArmor + "%"});
                  }

                  if (UpgradedNetheriteConfig.EnableHealEchoArmor) {
                     tooltip.add(Component.translatable("upgradednetherite.Echo_Bonus3.TT"));
                  }
               }
            } else {
               tooltip.add(Component.translatable("upgradednetherite.HoldShift.TT"));
            }
         }
      }

   }
}
