package net.sjhub.upgradednetheritereforged.data;

import net.sjhub.upgradednetheritereforged.init.ModItems;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.Advancement.Builder;
import net.minecraft.advancements.critereon.InventoryChangeTrigger.TriggerInstance;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;

public class AdvancementData extends ForgeAdvancementProvider {
   public AdvancementData(PackOutput output, CompletableFuture<Provider> registries, ExistingFileHelper existingFileHelper) {
      super(output, registries, existingFileHelper, List.of(new AdvancementData.UpgradedNetheriteAdvancements()));
   }

   public static class UpgradedNetheriteAdvancements implements AdvancementGenerator {
      public void generate(Provider provider, Consumer<Advancement> consumer, ExistingFileHelper existingFileHelper) {
         Builder.m_138353_().m_138396_(new ResourceLocation("minecraft:nether/netherite_armor")).m_138371_((ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_CHESTPLATE.get(), Component.m_237115_("advancement.upgradednetherite.upgraded_armor"), Component.m_237115_("advancement.upgradednetherite.upgraded_armor.desc"), (ResourceLocation)null, FrameType.CHALLENGE, true, true, true).m_138360_(RequirementsStrategy.f_15979_).m_138386_("gold_upgraded_netherite", TriggerInstance.m_43199_(new ItemLike[]{(ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_HELMET.get(), (ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_CHESTPLATE.get(), (ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_LEGGINGS.get(), (ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_BOOTS.get()})).m_138386_("fire_upgraded_netherite", TriggerInstance.m_43199_(new ItemLike[]{(ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_HELMET.get(), (ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_CHESTPLATE.get(), (ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_LEGGINGS.get(), (ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_BOOTS.get()})).m_138386_("ender_upgraded_netherite", TriggerInstance.m_43199_(new ItemLike[]{(ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_HELMET.get(), (ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_CHESTPLATE.get(), (ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_LEGGINGS.get(), (ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_BOOTS.get()})).m_138386_("water_upgraded_netherite", TriggerInstance.m_43199_(new ItemLike[]{(ItemLike)ModItems.WATER_UPGRADED_NETHERITE_HELMET.get(), (ItemLike)ModItems.WATER_UPGRADED_NETHERITE_CHESTPLATE.get(), (ItemLike)ModItems.WATER_UPGRADED_NETHERITE_LEGGINGS.get(), (ItemLike)ModItems.WATER_UPGRADED_NETHERITE_BOOTS.get()})).m_138386_("wither_upgraded_netherite", TriggerInstance.m_43199_(new ItemLike[]{(ItemLike)ModItems.WITHER_UPGRADED_NETHERITE_HELMET.get(), (ItemLike)ModItems.WITHER_UPGRADED_NETHERITE_CHESTPLATE.get(), (ItemLike)ModItems.WITHER_UPGRADED_NETHERITE_LEGGINGS.get(), (ItemLike)ModItems.WITHER_UPGRADED_NETHERITE_BOOTS.get()})).m_138386_("poison_upgraded_netherite", TriggerInstance.m_43199_(new ItemLike[]{(ItemLike)ModItems.POISON_UPGRADED_NETHERITE_HELMET.get(), (ItemLike)ModItems.POISON_UPGRADED_NETHERITE_CHESTPLATE.get(), (ItemLike)ModItems.POISON_UPGRADED_NETHERITE_LEGGINGS.get(), (ItemLike)ModItems.POISON_UPGRADED_NETHERITE_BOOTS.get()})).m_138386_("phantom_upgraded_netherite", TriggerInstance.m_43199_(new ItemLike[]{(ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_HELMET.get(), (ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_CHESTPLATE.get(), (ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_LEGGINGS.get(), (ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_BOOTS.get()})).m_138386_("feather_upgraded_netherite", TriggerInstance.m_43199_(new ItemLike[]{(ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_HELMET.get(), (ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_CHESTPLATE.get(), (ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_LEGGINGS.get(), (ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_BOOTS.get()})).m_138386_("corrupt_upgraded_netherite", TriggerInstance.m_43199_(new ItemLike[]{(ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_HELMET.get(), (ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_CHESTPLATE.get(), (ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_LEGGINGS.get(), (ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_BOOTS.get()})).m_138386_("echo_upgraded_netherite", TriggerInstance.m_43199_(new ItemLike[]{(ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_HELMET.get(), (ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_CHESTPLATE.get(), (ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_LEGGINGS.get(), (ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_BOOTS.get()})).save(consumer, new ResourceLocation("upgradednetherite_reforged", "upgraded_netherite_armor"), existingFileHelper);
         Builder.m_138353_().m_138396_(new ResourceLocation("minecraft:husbandry/obtain_netherite_hoe")).m_138371_((ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_HOE.get(), Component.m_237115_("advancement.upgradednetherite.upgraded_hoe"), Component.m_237115_("advancement.upgradednetherite.upgraded_hoe.desc"), (ResourceLocation)null, FrameType.CHALLENGE, true, true, true).m_138360_(RequirementsStrategy.f_15979_).m_138386_("gold_upgraded_netherite_hoe", TriggerInstance.m_43199_(new ItemLike[]{(ItemLike)ModItems.GOLD_UPGRADED_NETHERITE_HOE.get()})).m_138386_("fire_upgraded_netherite_hoe", TriggerInstance.m_43199_(new ItemLike[]{(ItemLike)ModItems.FIRE_UPGRADED_NETHERITE_HOE.get()})).m_138386_("ender_upgraded_netherite_hoe", TriggerInstance.m_43199_(new ItemLike[]{(ItemLike)ModItems.ENDER_UPGRADED_NETHERITE_HOE.get()})).m_138386_("water_upgraded_netherite_hoe", TriggerInstance.m_43199_(new ItemLike[]{(ItemLike)ModItems.WATER_UPGRADED_NETHERITE_HOE.get()})).m_138386_("poison_upgraded_netherite_hoe", TriggerInstance.m_43199_(new ItemLike[]{(ItemLike)ModItems.POISON_UPGRADED_NETHERITE_HOE.get()})).m_138386_("phantom_upgraded_netherite_hoe", TriggerInstance.m_43199_(new ItemLike[]{(ItemLike)ModItems.PHANTOM_UPGRADED_NETHERITE_HOE.get()})).m_138386_("feather_upgraded_netherite_hoe", TriggerInstance.m_43199_(new ItemLike[]{(ItemLike)ModItems.FEATHER_UPGRADED_NETHERITE_HOE.get()})).m_138386_("corrupt_upgraded_netherite_hoe", TriggerInstance.m_43199_(new ItemLike[]{(ItemLike)ModItems.CORRUPT_UPGRADED_NETHERITE_HOE.get()})).m_138386_("echo_upgraded_netherite_hoe", TriggerInstance.m_43199_(new ItemLike[]{(ItemLike)ModItems.ECHO_UPGRADED_NETHERITE_HOE.get()})).save(consumer, new ResourceLocation("upgradednetherite_reforged", "upgraded_netherite_hoe"), existingFileHelper);
      }
   }
}
