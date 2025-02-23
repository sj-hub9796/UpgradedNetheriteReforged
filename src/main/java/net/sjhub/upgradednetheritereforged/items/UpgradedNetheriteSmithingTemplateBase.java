package net.sjhub.upgradednetheritereforged.items;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.SmithingTemplateItem;

import java.util.List;

public class UpgradedNetheriteSmithingTemplateBase extends SmithingTemplateItem {

    private static final Component TRANSCEND_UPGRADE = Component.translatable("upgrade.minecraft.netherite_upgrade").withStyle(ChatFormatting.GRAY);
    private static final Component TRANSCEND_APPLIES_TO = Component.translatable("item.minecraft.smithing_template.netherite_upgrade.applies_to").withStyle(ChatFormatting.BLUE);
    private static final Component TRANSCEND_INGREDIENTS = Component.translatable("item.minecraft.smithing_template.netherite_upgrade.ingredients").withStyle(ChatFormatting.BLUE);
    private static final Component TRANSCEND_BASE_SLOT_DESCRIPTION = Component.translatable("item.minecraft.smithing_template.netherite_upgrade.base_slot_description");
    private static final Component TRANSCEND_ADDITIONS_SLOT_DESCRIPTION = Component.translatable("item.minecraft.smithing_template.netherite_upgrade.additions_slot_description");

    public UpgradedNetheriteSmithingTemplateBase(Component pAppliesTo, Component pIngredients, Component pUpdradeDescription, Component pBaseSlotDescription, Component pAdditionsSlotDescription, List<ResourceLocation> pBaseSlotEmptyIcons, List<ResourceLocation> pAdditonalSlotEmptyIcons) {
        super(pAppliesTo, pIngredients, pUpdradeDescription, pBaseSlotDescription, pAdditionsSlotDescription, pBaseSlotEmptyIcons, pAdditonalSlotEmptyIcons);
    }

    public static UpgradedNetheriteSmithingTemplateBase createTranscendTemplate() {
        return new UpgradedNetheriteSmithingTemplateBase(
                TRANSCEND_APPLIES_TO,
                TRANSCEND_INGREDIENTS,
                TRANSCEND_UPGRADE,
                TRANSCEND_BASE_SLOT_DESCRIPTION,
                TRANSCEND_ADDITIONS_SLOT_DESCRIPTION,
                createNetheriteUpgradeIconList(),
                createNetheriteUpgradeMaterialList()
                );
    }
}
