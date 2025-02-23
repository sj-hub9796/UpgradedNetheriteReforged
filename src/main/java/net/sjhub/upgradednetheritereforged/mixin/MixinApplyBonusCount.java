package net.sjhub.upgradednetheritereforged.mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.sjhub.upgradednetheritereforged.config.UpgradedNetheriteConfig;
import net.sjhub.upgradednetheritereforged.utils.check.CorruptUtil;
import net.sjhub.upgradednetheritereforged.utils.check.GoldUtil;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Map;

@Mixin(ApplyBonusCount.class)
public class MixinApplyBonusCount {

   @Shadow @Final Enchantment enchantment;

   @ModifyVariable(method = "run", index = 4, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/enchantment/EnchantmentHelper;getItemEnchantmentLevel(Lnet/minecraft/world/item/enchantment/Enchantment;Lnet/minecraft/world/item/ItemStack;)I"))
   private int upgradedFortune(int enchantLevel, ItemStack itemStack, LootContext lootContext) {
      if (this.enchantment == Enchantments.BLOCK_FORTUNE) {
         ItemStack tool = (ItemStack)lootContext.getParamOrNull(LootContextParams.TOOL);
         Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(tool);
         if (GoldUtil.isGoldTool(tool) && UpgradedNetheriteConfig.EnableFortuneBonus) {
            if (!enchantments.isEmpty() && enchantments.containsKey(Enchantments.BLOCK_FORTUNE)) {
               int EnchantLevel = (Integer)enchantments.get(Enchantments.BLOCK_FORTUNE);
               return EnchantLevel >= 3 ? enchantLevel + UpgradedNetheriteConfig.FortuneBonus + UpgradedNetheriteConfig.FortuneEnchantBonus : enchantLevel + UpgradedNetheriteConfig.FortuneBonus;
            } else {
               return enchantLevel + UpgradedNetheriteConfig.FortuneBonus;
            }
         } else {
            return CorruptUtil.isCorruptTool(tool) && UpgradedNetheriteConfig.EnableFortuneBonusCorruptTool && lootContext.getParamOrNull(LootContextParams.THIS_ENTITY) instanceof Player ? enchantLevel + CorruptUtil.intWearingCorrupt((Player)lootContext.getParamOrNull(LootContextParams.THIS_ENTITY), true) * UpgradedNetheriteConfig.FortuneBonusCorruptTool : enchantLevel;
         }
      } else {
         return enchantLevel;
      }
   }
}
