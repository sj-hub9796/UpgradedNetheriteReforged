package net.sjhub.upgradednetheritereforged;

import com.mojang.logging.LogUtils;
import com.rolfmao.upgradedcore_old.client.BowModel;
import com.rolfmao.upgradedcore_old.client.CrossBowModel;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.sjhub.upgradednetheritereforged.config.ConfigHolder;
import net.sjhub.upgradednetheritereforged.config.UpgradedNetheriteConfig;
import net.sjhub.upgradednetheritereforged.data.AdvancementData;
import net.sjhub.upgradednetheritereforged.data.LootTableData;
import net.sjhub.upgradednetheritereforged.data.RecipeData;
import net.sjhub.upgradednetheritereforged.handlers.ArmorEventHandler;
import net.sjhub.upgradednetheritereforged.handlers.EventHandler;
import net.sjhub.upgradednetheritereforged.handlers.HorseArmorEventHandler;
import net.sjhub.upgradednetheritereforged.handlers.SoulboundEventHandler;
import net.sjhub.upgradednetheritereforged.handlers.ToolEventHandler;
import net.sjhub.upgradednetheritereforged.handlers.WeaponEventHandler;
import net.sjhub.upgradednetheritereforged.init.ModCreativeModeTabs;
import net.sjhub.upgradednetheritereforged.init.ModItems;
import net.sjhub.upgradednetheritereforged.init.UpgradedNetheriteEffects;
import net.sjhub.upgradednetheritereforged.modifiers.GlobalLootModifiers;
import net.sjhub.upgradednetheritereforged.modifiers.LootTableModifier;
import net.sjhub.upgradednetheritereforged.modifiers.ModLootModifiers;
import net.sjhub.upgradednetheritereforged.packets.PacketEntityFallDistanceUpdate;
import net.sjhub.upgradednetheritereforged.packets.PacketPlayerFallDistanceUpdate;
import java.nio.file.Path;
import net.minecraft.SharedConstants;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.PathPackResources;
import net.minecraft.server.packs.repository.Pack;
import net.minecraft.server.packs.repository.PackSource;
import net.minecraft.server.packs.repository.Pack.Info;
import net.minecraft.server.packs.repository.Pack.Position;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.CreativeModeTab.TabVisibility;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.event.AddPackFindersEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry.ChannelBuilder;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;

@Mod("upgradednetherite_reforged")
public class UpgradedNetheriteMod {
   public static final Logger LOGGER = LogUtils.getLogger();
   public static final String MOD_ID = "upgradednetherite_reforged";
   public static SimpleChannel packetInstance;

   public UpgradedNetheriteMod() {
      IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

      ModCreativeModeTabs.register(eventBus);

      eventBus.addListener(this::setup);
      eventBus.addListener(this::doClientStuff);
      eventBus.addListener(this::dataSetup);
      eventBus.addListener(this::addAltPack);
      eventBus.addListener(this::addOldPack);

      UpgradedNetheriteEffects.EFFECTS.register(eventBus);
      ModItems.ITEMS.register(eventBus);
      GlobalLootModifiers.GLM.register(eventBus);
      ModLootModifiers.register(eventBus);
      ModEventSubscriber.create(eventBus);
      eventBus.addListener(this::addCreative);

      MinecraftForge.EVENT_BUS.register(this);
      MinecraftForge.EVENT_BUS.register(new EventHandler());
      MinecraftForge.EVENT_BUS.register(new ArmorEventHandler());
      MinecraftForge.EVENT_BUS.register(new ToolEventHandler());
      MinecraftForge.EVENT_BUS.register(new WeaponEventHandler());
      MinecraftForge.EVENT_BUS.register(new HorseArmorEventHandler());
      MinecraftForge.EVENT_BUS.register(new SoulboundEventHandler());

      ModLoadingContext.get().registerConfig(Type.CLIENT, ConfigHolder.CLIENT_SPEC);
      ModLoadingContext.get().registerConfig(Type.SERVER, ConfigHolder.SERVER_SPEC);
   }

   private void setup(FMLCommonSetupEvent event) {
      packetInstance = ChannelBuilder.named(new ResourceLocation("upgradednetherite_reforged", "main")).networkProtocolVersion(() -> {
         return "1";
      }).clientAcceptedVersions("1"::equals).serverAcceptedVersions("1"::equals).simpleChannel();
      packetInstance.registerMessage(1, PacketPlayerFallDistanceUpdate.class, PacketPlayerFallDistanceUpdate::encode, PacketPlayerFallDistanceUpdate::decode, PacketPlayerFallDistanceUpdate::handle);
      packetInstance.registerMessage(2, PacketEntityFallDistanceUpdate.class, PacketEntityFallDistanceUpdate::encode, PacketEntityFallDistanceUpdate::decode, PacketEntityFallDistanceUpdate::handle);
   }

   private void doClientStuff(FMLClientSetupEvent event) {
      event.enqueueWork(() -> {
         BowModel.setupBowModelProperties((Item)ModItems.NETHERITE_BOW.get());
         BowModel.setupBowModelProperties((Item)ModItems.GOLD_UPGRADED_NETHERITE_BOW.get());
         BowModel.setupBowModelProperties((Item)ModItems.FIRE_UPGRADED_NETHERITE_BOW.get());
         BowModel.setupBowModelProperties((Item)ModItems.ENDER_UPGRADED_NETHERITE_BOW.get());
         BowModel.setupBowModelProperties((Item)ModItems.WATER_UPGRADED_NETHERITE_BOW.get());
         BowModel.setupBowModelProperties((Item)ModItems.WITHER_UPGRADED_NETHERITE_BOW.get());
         BowModel.setupBowModelProperties((Item)ModItems.POISON_UPGRADED_NETHERITE_BOW.get());
         BowModel.setupBowModelProperties((Item)ModItems.PHANTOM_UPGRADED_NETHERITE_BOW.get());
         BowModel.setupBowModelProperties((Item)ModItems.FEATHER_UPGRADED_NETHERITE_BOW.get());
         BowModel.setupBowModelProperties((Item)ModItems.CORRUPT_UPGRADED_NETHERITE_BOW.get());
         BowModel.setupBowModelProperties((Item)ModItems.ECHO_UPGRADED_NETHERITE_BOW.get());
         CrossBowModel.setupCrossBowModelProperties((Item)ModItems.NETHERITE_CROSSBOW.get());
         CrossBowModel.setupCrossBowModelProperties((Item)ModItems.GOLD_UPGRADED_NETHERITE_CROSSBOW.get());
         CrossBowModel.setupCrossBowModelProperties((Item)ModItems.FIRE_UPGRADED_NETHERITE_CROSSBOW.get());
         CrossBowModel.setupCrossBowModelProperties((Item)ModItems.ENDER_UPGRADED_NETHERITE_CROSSBOW.get());
         CrossBowModel.setupCrossBowModelProperties((Item)ModItems.WATER_UPGRADED_NETHERITE_CROSSBOW.get());
         CrossBowModel.setupCrossBowModelProperties((Item)ModItems.WITHER_UPGRADED_NETHERITE_CROSSBOW.get());
         CrossBowModel.setupCrossBowModelProperties((Item)ModItems.POISON_UPGRADED_NETHERITE_CROSSBOW.get());
         CrossBowModel.setupCrossBowModelProperties((Item)ModItems.PHANTOM_UPGRADED_NETHERITE_CROSSBOW.get());
         CrossBowModel.setupCrossBowModelProperties((Item)ModItems.FEATHER_UPGRADED_NETHERITE_CROSSBOW.get());
         CrossBowModel.setupCrossBowModelProperties((Item)ModItems.CORRUPT_UPGRADED_NETHERITE_CROSSBOW.get());
         CrossBowModel.setupCrossBowModelProperties((Item)ModItems.ECHO_UPGRADED_NETHERITE_CROSSBOW.get());
      });
   }

   public void dataSetup(GatherDataEvent event) {
      event.getGenerator().addProvider(event.includeServer(), new AdvancementData(event.getGenerator().getPackOutput(), event.getLookupProvider(), event.getExistingFileHelper()));
      event.getGenerator().addProvider(event.includeServer(), new RecipeData(event.getGenerator().getPackOutput()));
      event.getGenerator().addProvider(event.includeServer(), new LootTableData(event.getGenerator().getPackOutput()));
   }

   private void addCreative(BuildCreativeModeTabContentsEvent event) {
      if (event.getTabKey() == CreativeModeTabs.COMBAT) {
         event.getEntries().putAfter(new ItemStack(Items.BOW), new ItemStack((ItemLike)ModItems.NETHERITE_BOW.get()), TabVisibility.PARENT_AND_SEARCH_TABS);
         event.getEntries().putAfter(new ItemStack(Items.CROSSBOW), new ItemStack((ItemLike)ModItems.NETHERITE_CROSSBOW.get()), TabVisibility.PARENT_AND_SEARCH_TABS);
         event.getEntries().putAfter(new ItemStack(Items.SHIELD), new ItemStack((ItemLike)ModItems.NETHERITE_SHIELD.get()), TabVisibility.PARENT_AND_SEARCH_TABS);
         event.getEntries().putAfter(new ItemStack(Items.DIAMOND_HORSE_ARMOR), new ItemStack((ItemLike)ModItems.NETHERITE_HORSE_ARMOR.get()), TabVisibility.PARENT_AND_SEARCH_TABS);
      }

      if (event.getTab() == ModCreativeModeTabs.UPGRADED_NETHERITE_TAB.get()) {
         event.accept(ModItems.NETHERITE_TRANSCEND_SMITHING_TEMPLATE);
         event.accept(ModItems.GOLD_ESSENCE);
         event.accept(ModItems.GOLD_UPGRADED_NETHERITE_INGOT);
         event.accept(ModItems.GOLD_UPGRADED_NETHERITE_SWORD);
         event.accept(ModItems.GOLD_UPGRADED_NETHERITE_SHIELD);
         event.accept(ModItems.GOLD_UPGRADED_NETHERITE_BOW);
         event.accept(ModItems.GOLD_UPGRADED_NETHERITE_CROSSBOW);
         event.accept(ModItems.GOLD_UPGRADED_NETHERITE_SHOVEL);
         event.accept(ModItems.GOLD_UPGRADED_NETHERITE_PICKAXE);
         event.accept(ModItems.GOLD_UPGRADED_NETHERITE_AXE);
         event.accept(ModItems.GOLD_UPGRADED_NETHERITE_HOE);
         event.accept(ModItems.GOLD_UPGRADED_NETHERITE_HELMET);
         event.accept(ModItems.GOLD_UPGRADED_NETHERITE_CHESTPLATE);
         event.accept(ModItems.GOLD_UPGRADED_NETHERITE_LEGGINGS);
         event.accept(ModItems.GOLD_UPGRADED_NETHERITE_BOOTS);
         event.accept(ModItems.GOLD_UPGRADED_NETHERITE_HORSE_ARMOR);
         event.accept(ModItems.FIRE_ESSENCE);
         event.accept(ModItems.FIRE_UPGRADED_NETHERITE_INGOT);
         event.accept(ModItems.FIRE_UPGRADED_NETHERITE_SWORD);
         event.accept(ModItems.FIRE_UPGRADED_NETHERITE_SHIELD);
         event.accept(ModItems.FIRE_UPGRADED_NETHERITE_BOW);
         event.accept(ModItems.FIRE_UPGRADED_NETHERITE_CROSSBOW);
         event.accept(ModItems.FIRE_UPGRADED_NETHERITE_SHOVEL);
         event.accept(ModItems.FIRE_UPGRADED_NETHERITE_PICKAXE);
         event.accept(ModItems.FIRE_UPGRADED_NETHERITE_AXE);
         event.accept(ModItems.FIRE_UPGRADED_NETHERITE_HOE);
         event.accept(ModItems.FIRE_UPGRADED_NETHERITE_HELMET);
         event.accept(ModItems.FIRE_UPGRADED_NETHERITE_CHESTPLATE);
         event.accept(ModItems.FIRE_UPGRADED_NETHERITE_LEGGINGS);
         event.accept(ModItems.FIRE_UPGRADED_NETHERITE_BOOTS);
         event.accept(ModItems.FIRE_UPGRADED_NETHERITE_HORSE_ARMOR);
         event.accept(ModItems.ENDER_ESSENCE);
         event.accept(ModItems.ENDER_UPGRADED_NETHERITE_INGOT);
         event.accept(ModItems.ENDER_UPGRADED_NETHERITE_SWORD);
         event.accept(ModItems.ENDER_UPGRADED_NETHERITE_SHIELD);
         event.accept(ModItems.ENDER_UPGRADED_NETHERITE_BOW);
         event.accept(ModItems.ENDER_UPGRADED_NETHERITE_CROSSBOW);
         event.accept(ModItems.ENDER_UPGRADED_NETHERITE_SHOVEL);
         event.accept(ModItems.ENDER_UPGRADED_NETHERITE_PICKAXE);
         event.accept(ModItems.ENDER_UPGRADED_NETHERITE_AXE);
         event.accept(ModItems.ENDER_UPGRADED_NETHERITE_HOE);
         event.accept(ModItems.ENDER_UPGRADED_NETHERITE_HELMET);
         event.accept(ModItems.ENDER_UPGRADED_NETHERITE_CHESTPLATE);
         event.accept(ModItems.ENDER_UPGRADED_NETHERITE_LEGGINGS);
         event.accept(ModItems.ENDER_UPGRADED_NETHERITE_BOOTS);
         event.accept(ModItems.ENDER_UPGRADED_NETHERITE_HORSE_ARMOR);
         event.accept(ModItems.WATER_ESSENCE);
         event.accept(ModItems.WATER_UPGRADED_NETHERITE_INGOT);
         event.accept(ModItems.WATER_UPGRADED_NETHERITE_SWORD);
         event.accept(ModItems.WATER_UPGRADED_NETHERITE_SHIELD);
         event.accept(ModItems.WATER_UPGRADED_NETHERITE_BOW);
         event.accept(ModItems.WATER_UPGRADED_NETHERITE_CROSSBOW);
         event.accept(ModItems.WATER_UPGRADED_NETHERITE_SHOVEL);
         event.accept(ModItems.WATER_UPGRADED_NETHERITE_PICKAXE);
         event.accept(ModItems.WATER_UPGRADED_NETHERITE_AXE);
         event.accept(ModItems.WATER_UPGRADED_NETHERITE_HOE);
         event.accept(ModItems.WATER_UPGRADED_NETHERITE_HELMET);
         event.accept(ModItems.WATER_UPGRADED_NETHERITE_CHESTPLATE);
         event.accept(ModItems.WATER_UPGRADED_NETHERITE_LEGGINGS);
         event.accept(ModItems.WATER_UPGRADED_NETHERITE_BOOTS);
         event.accept(ModItems.WATER_UPGRADED_NETHERITE_HORSE_ARMOR);
         event.accept(ModItems.WITHER_ESSENCE);
         event.accept(ModItems.WITHER_UPGRADED_NETHERITE_INGOT);
         event.accept(ModItems.WITHER_UPGRADED_NETHERITE_SWORD);
         event.accept(ModItems.WITHER_UPGRADED_NETHERITE_SHIELD);
         event.accept(ModItems.WITHER_UPGRADED_NETHERITE_BOW);
         event.accept(ModItems.WITHER_UPGRADED_NETHERITE_CROSSBOW);
         event.accept(ModItems.WITHER_UPGRADED_NETHERITE_AXE);
         event.accept(ModItems.WITHER_UPGRADED_NETHERITE_HELMET);
         event.accept(ModItems.WITHER_UPGRADED_NETHERITE_CHESTPLATE);
         event.accept(ModItems.WITHER_UPGRADED_NETHERITE_LEGGINGS);
         event.accept(ModItems.WITHER_UPGRADED_NETHERITE_BOOTS);
         event.accept(ModItems.WITHER_UPGRADED_NETHERITE_HORSE_ARMOR);
         event.accept(ModItems.POISON_ESSENCE);
         event.accept(ModItems.POISON_UPGRADED_NETHERITE_INGOT);
         event.accept(ModItems.POISON_UPGRADED_NETHERITE_SWORD);
         event.accept(ModItems.POISON_UPGRADED_NETHERITE_SHIELD);
         event.accept(ModItems.POISON_UPGRADED_NETHERITE_BOW);
         event.accept(ModItems.POISON_UPGRADED_NETHERITE_CROSSBOW);
         event.accept(ModItems.POISON_UPGRADED_NETHERITE_AXE);
         event.accept(ModItems.POISON_UPGRADED_NETHERITE_HOE);
         event.accept(ModItems.POISON_UPGRADED_NETHERITE_HELMET);
         event.accept(ModItems.POISON_UPGRADED_NETHERITE_CHESTPLATE);
         event.accept(ModItems.POISON_UPGRADED_NETHERITE_LEGGINGS);
         event.accept(ModItems.POISON_UPGRADED_NETHERITE_BOOTS);
         event.accept(ModItems.POISON_UPGRADED_NETHERITE_HORSE_ARMOR);
         event.accept(ModItems.PHANTOM_ESSENCE);
         event.accept(ModItems.PHANTOM_UPGRADED_NETHERITE_INGOT);
         event.accept(ModItems.PHANTOM_UPGRADED_NETHERITE_SWORD);
         event.accept(ModItems.PHANTOM_UPGRADED_NETHERITE_SHIELD);
         event.accept(ModItems.PHANTOM_UPGRADED_NETHERITE_BOW);
         event.accept(ModItems.PHANTOM_UPGRADED_NETHERITE_CROSSBOW);
         event.accept(ModItems.PHANTOM_UPGRADED_NETHERITE_SHOVEL);
         event.accept(ModItems.PHANTOM_UPGRADED_NETHERITE_PICKAXE);
         event.accept(ModItems.PHANTOM_UPGRADED_NETHERITE_AXE);
         event.accept(ModItems.PHANTOM_UPGRADED_NETHERITE_HOE);
         event.accept(ModItems.PHANTOM_UPGRADED_NETHERITE_HELMET);
         event.accept(ModItems.PHANTOM_UPGRADED_NETHERITE_CHESTPLATE);
         event.accept(ModItems.PHANTOM_UPGRADED_NETHERITE_LEGGINGS);
         event.accept(ModItems.PHANTOM_UPGRADED_NETHERITE_BOOTS);
         event.accept(ModItems.PHANTOM_UPGRADED_NETHERITE_HORSE_ARMOR);
         event.accept(ModItems.FEATHER_ESSENCE);
         event.accept(ModItems.FEATHER_UPGRADED_NETHERITE_INGOT);
         event.accept(ModItems.FEATHER_UPGRADED_NETHERITE_SWORD);
         event.accept(ModItems.FEATHER_UPGRADED_NETHERITE_SHIELD);
         event.accept(ModItems.FEATHER_UPGRADED_NETHERITE_BOW);
         event.accept(ModItems.FEATHER_UPGRADED_NETHERITE_CROSSBOW);
         event.accept(ModItems.FEATHER_UPGRADED_NETHERITE_SHOVEL);
         event.accept(ModItems.FEATHER_UPGRADED_NETHERITE_PICKAXE);
         event.accept(ModItems.FEATHER_UPGRADED_NETHERITE_AXE);
         event.accept(ModItems.FEATHER_UPGRADED_NETHERITE_HOE);
         event.accept(ModItems.FEATHER_UPGRADED_NETHERITE_HELMET);
         event.accept(ModItems.FEATHER_UPGRADED_NETHERITE_CHESTPLATE);
         event.accept(ModItems.FEATHER_UPGRADED_NETHERITE_LEGGINGS);
         event.accept(ModItems.FEATHER_UPGRADED_NETHERITE_BOOTS);
         event.accept(ModItems.FEATHER_UPGRADED_NETHERITE_HORSE_ARMOR);
         event.accept(ModItems.CORRUPT_ESSENCE);
         event.accept(ModItems.CORRUPT_UPGRADED_NETHERITE_INGOT);
         event.accept(ModItems.CORRUPT_UPGRADED_NETHERITE_SWORD);
         event.accept(ModItems.CORRUPT_UPGRADED_NETHERITE_SHIELD);
         event.accept(ModItems.CORRUPT_UPGRADED_NETHERITE_BOW);
         event.accept(ModItems.CORRUPT_UPGRADED_NETHERITE_CROSSBOW);
         event.accept(ModItems.CORRUPT_UPGRADED_NETHERITE_SHOVEL);
         event.accept(ModItems.CORRUPT_UPGRADED_NETHERITE_PICKAXE);
         event.accept(ModItems.CORRUPT_UPGRADED_NETHERITE_AXE);
         event.accept(ModItems.CORRUPT_UPGRADED_NETHERITE_HOE);
         event.accept(ModItems.CORRUPT_UPGRADED_NETHERITE_HELMET);
         event.accept(ModItems.CORRUPT_UPGRADED_NETHERITE_CHESTPLATE);
         event.accept(ModItems.CORRUPT_UPGRADED_NETHERITE_LEGGINGS);
         event.accept(ModItems.CORRUPT_UPGRADED_NETHERITE_BOOTS);
         event.accept(ModItems.CORRUPT_UPGRADED_NETHERITE_HORSE_ARMOR);
         event.accept(ModItems.ECHO_ESSENCE);
         event.accept(ModItems.ECHO_UPGRADED_NETHERITE_INGOT);
         event.accept(ModItems.ECHO_UPGRADED_NETHERITE_SWORD);
         event.accept(ModItems.ECHO_UPGRADED_NETHERITE_SHIELD);
         event.accept(ModItems.ECHO_UPGRADED_NETHERITE_BOW);
         event.accept(ModItems.ECHO_UPGRADED_NETHERITE_CROSSBOW);
         event.accept(ModItems.ECHO_UPGRADED_NETHERITE_SHOVEL);
         event.accept(ModItems.ECHO_UPGRADED_NETHERITE_PICKAXE);
         event.accept(ModItems.ECHO_UPGRADED_NETHERITE_AXE);
         event.accept(ModItems.ECHO_UPGRADED_NETHERITE_HOE);
         event.accept(ModItems.ECHO_UPGRADED_NETHERITE_HELMET);
         event.accept(ModItems.ECHO_UPGRADED_NETHERITE_CHESTPLATE);
         event.accept(ModItems.ECHO_UPGRADED_NETHERITE_LEGGINGS);
         event.accept(ModItems.ECHO_UPGRADED_NETHERITE_BOOTS);
         event.accept(ModItems.ECHO_UPGRADED_NETHERITE_HORSE_ARMOR);
      }

   }

   private void addAltPack(AddPackFindersEvent event) {
      if (event.getPackType() == PackType.CLIENT_RESOURCES) {
         Path path = ModList.get().getModFileById("upgradednetherite_reforged").getFile().findResource(new String[]{"packs/alt"});
         PathPackResources pathPackResources = new PathPackResources(ModList.get().getModFileById("upgradednetherite_reforged").getFile().getFileName() + ":" + path, path, true);
         event.addRepositorySource((source) -> {
            source.accept(Pack.create("builtin/alt", Component.literal("Upgraded Netherite Alt"), false, (string) -> {
               return pathPackResources;
            }, new Info(Component.literal("Alternative Texture for Upgraded Netherite"), SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA), SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES), FeatureFlagSet.of(), !UpgradedNetheriteConfig.AltTextures), PackType.CLIENT_RESOURCES, Position.TOP, false, PackSource.BUILT_IN));
         });
      }

   }

   private void addOldPack(AddPackFindersEvent event) {
      if (event.getPackType() == PackType.CLIENT_RESOURCES) {
         Path path = ModList.get().getModFileById("upgradednetherite_reforged").getFile().findResource(new String[]{"packs/old"});
         PathPackResources pathPackResources = new PathPackResources(ModList.get().getModFileById("upgradednetherite_reforged").getFile().getFileName() + ":" + path, path, true);
         event.addRepositorySource((source) -> {
            source.accept(Pack.create("builtin/old", Component.literal("Upgraded Netherite Old"), false, (string) -> {
               return pathPackResources;
            }, new Info(Component.literal("Old Texture of Upgraded Netherite"), SharedConstants.getCurrentVersion().getPackVersion(PackType.SERVER_DATA), SharedConstants.getCurrentVersion().getPackVersion(PackType.CLIENT_RESOURCES), FeatureFlagSet.of(), !UpgradedNetheriteConfig.OldTextures), PackType.CLIENT_RESOURCES, Position.TOP, false, PackSource.BUILT_IN));
         });
      }

   }
}
