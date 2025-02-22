package net.sjhub.upgradednetheritereforged.content;

import com.rolfmao.upgradedcore.helpers.TooltipHelper;
import net.sjhub.upgradednetheritereforged.client.ShieldRenderer;
import net.sjhub.upgradednetheritereforged.config.UpgradedNetheriteConfig;
import net.sjhub.upgradednetheritereforged.init.ModItems;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class UpgradedNetheriteShield extends ShieldItem {
   public UpgradedNetheriteShield(Properties properties) {
      super(properties);
   }

   public void initializeClient(Consumer<IClientItemExtensions> consumer) {
      consumer.accept(new IClientItemExtensions() {
         public BlockEntityWithoutLevelRenderer getCustomRenderer() {
            return ShieldRenderer.instance;
         }
      });
   }

   public boolean canApplyAtEnchantingTable(ItemStack itemStack, Enchantment enchantment) {
      return itemStack.m_41720_() == ModItems.CORRUPT_UPGRADED_NETHERITE_SHIELD.get() && enchantment == Enchantments.f_44962_ ? false : enchantment.f_44672_.m_7454_(itemStack.m_41720_());
   }

   public void m_6883_(ItemStack itemStack, Level level, Entity entity, int slot, boolean p_77663_5_) {
      if (itemStack.m_41720_() == ModItems.CORRUPT_UPGRADED_NETHERITE_SHIELD.get() && itemStack.m_41793_() && EnchantmentHelper.getTagEnchantmentLevel(Enchantments.f_44962_, itemStack) > 0) {
         Map<Enchantment, Integer> enchantments = EnchantmentHelper.m_44831_(itemStack);
         if (enchantments.containsKey(Enchantments.f_44962_)) {
            enchantments.remove(Enchantments.f_44962_);
            EnchantmentHelper.m_44865_(enchantments, itemStack);
         }
      }

   }

   public boolean m_6832_(ItemStack toRepair, ItemStack repair) {
      return Items.f_42418_.equals(repair.m_41720_());
   }

   @OnlyIn(Dist.CLIENT)
   public void m_7373_(ItemStack itemStack, Level level, List<Component> tooltip, TooltipFlag tooltipFlag) {
      super.m_7373_(itemStack, level, tooltip, tooltipFlag);
      if (!UpgradedNetheriteConfig.DisableTooltips) {
         Item shield = itemStack.m_41720_();
         if (shield != ModItems.NETHERITE_SHIELD.get()) {
            if (Screen.m_96638_()) {
               Object[] var10002;
               float var10005;
               if (shield == ModItems.GOLD_UPGRADED_NETHERITE_SHIELD.get()) {
                  tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.m_237115_("upgradednetherite.WhenBlocking.TT"));
                  var10002 = new Object[2];
                  var10005 = (float)UpgradedNetheriteConfig.DamageBonusGoldWeapon;
                  var10002[0] = "§6" + var10005 / 20.0F + "%";
                  var10005 = (float)UpgradedNetheriteConfig.DamageBonusGoldWeapon;
                  var10002[1] = "§6" + var10005 / 2.0F + "%";
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Gold_Shield.TT", var10002);
               } else if (shield == ModItems.FIRE_UPGRADED_NETHERITE_SHIELD.get()) {
                  tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.m_237115_("upgradednetherite.WhenBlocking.TT"));
                  var10002 = new Object[2];
                  var10005 = (float)UpgradedNetheriteConfig.DamageBonusFireWeapon;
                  var10002[0] = "§6" + var10005 / 20.0F + "%";
                  var10005 = (float)UpgradedNetheriteConfig.DamageBonusFireWeapon;
                  var10002[1] = "§6" + var10005 / 2.0F + "%";
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Fire_Shield.TT", var10002);
               } else if (shield == ModItems.ENDER_UPGRADED_NETHERITE_SHIELD.get()) {
                  tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.m_237115_("upgradednetherite.WhenBlocking.TT"));
                  var10002 = new Object[2];
                  var10005 = (float)UpgradedNetheriteConfig.DamageBonusEnderWeapon;
                  var10002[0] = "§6" + var10005 / 20.0F + "%";
                  var10005 = (float)UpgradedNetheriteConfig.DamageBonusEnderWeapon;
                  var10002[1] = "§6" + var10005 / 2.0F + "%";
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Ender_Shield.TT", var10002);
               } else if (shield == ModItems.WATER_UPGRADED_NETHERITE_SHIELD.get()) {
                  tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.m_237115_("upgradednetherite.WhenBlocking.TT"));
                  var10002 = new Object[2];
                  var10005 = (float)UpgradedNetheriteConfig.DamageBonusWaterWeapon;
                  var10002[0] = "§6" + var10005 / 20.0F + "%";
                  var10005 = (float)UpgradedNetheriteConfig.DamageBonusWaterWeapon;
                  var10002[1] = "§6" + var10005 / 2.0F + "%";
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Water_Shield.TT", var10002);
               } else if (shield == ModItems.WITHER_UPGRADED_NETHERITE_SHIELD.get()) {
                  tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.m_237115_("upgradednetherite.WhenBlocking.TT"));
                  var10002 = new Object[2];
                  var10005 = (float)UpgradedNetheriteConfig.DamageBonusWitherWeapon;
                  var10002[0] = "§6" + var10005 / 20.0F + "%";
                  var10005 = (float)UpgradedNetheriteConfig.DamageBonusWitherWeapon;
                  var10002[1] = "§6" + var10005 / 2.0F + "%";
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Wither_Shield.TT", var10002);
               } else if (shield == ModItems.POISON_UPGRADED_NETHERITE_SHIELD.get()) {
                  tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.m_237115_("upgradednetherite.WhenBlocking.TT"));
                  var10002 = new Object[2];
                  var10005 = (float)UpgradedNetheriteConfig.DamageBonusPoisonWeapon;
                  var10002[0] = "§6" + var10005 / 20.0F + "%";
                  var10005 = (float)UpgradedNetheriteConfig.DamageBonusPoisonWeapon;
                  var10002[1] = "§6" + var10005 / 2.0F + "%";
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Poison_Shield.TT", var10002);
               } else if (shield == ModItems.PHANTOM_UPGRADED_NETHERITE_SHIELD.get()) {
                  tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.m_237115_("upgradednetherite.WhenBlocking.TT"));
                  var10002 = new Object[2];
                  var10005 = (float)UpgradedNetheriteConfig.DamageBonusPhantomWeapon;
                  var10002[0] = "§6" + var10005 / 20.0F + "%";
                  var10005 = (float)UpgradedNetheriteConfig.DamageBonusPhantomWeapon;
                  var10002[1] = "§6" + var10005 / 2.0F + "%";
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Phantom_Shield.TT", var10002);
               } else if (shield == ModItems.FEATHER_UPGRADED_NETHERITE_SHIELD.get()) {
                  tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.m_237115_("upgradednetherite.WhenBlocking.TT"));
                  tooltip.add(Component.m_237115_("upgradednetherite.Feather_Shield.TT"));
               } else if (shield == ModItems.CORRUPT_UPGRADED_NETHERITE_SHIELD.get()) {
                  tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.m_237115_("upgradednetherite.WhenBlocking.TT"));
                  var10002 = new Object[2];
                  var10005 = (float)UpgradedNetheriteConfig.DamageBonusCorruptWeapon;
                  var10002[0] = "§6" + var10005 / 20.0F + "%";
                  var10005 = (float)UpgradedNetheriteConfig.DamageBonusCorruptWeapon;
                  var10002[1] = "§6" + var10005 / 2.0F + "%";
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Corrupt_Shield.TT", var10002);
               } else if (shield == ModItems.ECHO_UPGRADED_NETHERITE_SHIELD.get()) {
                  tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.m_237115_("upgradednetherite.WhenBlocking.TT"));
                  var10002 = new Object[2];
                  var10005 = (float)UpgradedNetheriteConfig.DamageBonusEchoWeapon;
                  var10002[0] = "§6" + var10005 / 20.0F + "%";
                  var10005 = (float)UpgradedNetheriteConfig.DamageBonusEchoWeapon;
                  var10002[1] = "§6" + var10005 / 2.0F + "%";
                  TooltipHelper.addTWO(tooltip, "upgradednetherite.Echo_Shield.TT", var10002);
               } else {
                  tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
                  tooltip.add(Component.m_237115_("upgradednetherite.Disabled.TT"));
               }
            } else {
               tooltip.add(Component.m_237115_("upgradednetherite.Blank.TT"));
               tooltip.add(Component.m_237115_("upgradednetherite.HoldShift.TT"));
            }
         }
      }

   }
}
