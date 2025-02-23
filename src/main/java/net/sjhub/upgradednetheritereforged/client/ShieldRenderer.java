package net.sjhub.upgradednetheritereforged.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.datafixers.util.Pair;
import net.sjhub.upgradednetheritereforged.UpgradedNetheriteMod;
import net.sjhub.upgradednetheritereforged.content.UpgradedNetheriteShield;
import net.sjhub.upgradednetheritereforged.init.ModItems;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BannerRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.core.Holder;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.level.block.entity.BannerBlockEntity;
import net.minecraft.world.level.block.entity.BannerPattern;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
   value = {Dist.CLIENT},
   modid = UpgradedNetheriteMod.MOD_ID,
   bus = Bus.MOD
)
public class ShieldRenderer extends BlockEntityWithoutLevelRenderer {
   public static ShieldRenderer instance;

   public ShieldRenderer(BlockEntityRenderDispatcher p_172550_, EntityModelSet p_172551_) {
      super(p_172550_, p_172551_);
   }

   @SubscribeEvent
   public static void onRegisterReloadListener(RegisterClientReloadListenersEvent event) {
      instance = new ShieldRenderer(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
      event.registerReloadListener(instance);
   }

   public void renderByItem(ItemStack stack, ItemDisplayContext p_239207_2_, PoseStack matrixStack, MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
      matrixStack.pushPose();
      matrixStack.scale(1.0F, -1.0F, -1.0F);
      boolean flag = stack.getTagElement("BlockEntityTag") != null;
      Material rendermaterial = flag ? ModelBakery.SHIELD_BASE : ModelBakery.NO_PATTERN_SHIELD;
      Item var10 = stack.getItem();
      if (var10 instanceof UpgradedNetheriteShield) {
         UpgradedNetheriteShield shield = (UpgradedNetheriteShield)var10;
         if (shield == ModItems.NETHERITE_SHIELD.get()) {
            rendermaterial = ShieldTextures.LOCATION_NETHERITE_SHIELD_BASE;
         } else if (shield == ModItems.GOLD_UPGRADED_NETHERITE_SHIELD.get()) {
            rendermaterial = ShieldTextures.LOCATION_GOLD_UPGRADED_NETHERITE_SHIELD_BASE;
         } else if (shield == ModItems.FIRE_UPGRADED_NETHERITE_SHIELD.get()) {
            rendermaterial = ShieldTextures.LOCATION_FIRE_UPGRADED_NETHERITE_SHIELD_BASE;
         } else if (shield == ModItems.ENDER_UPGRADED_NETHERITE_SHIELD.get()) {
            rendermaterial = ShieldTextures.LOCATION_ENDER_UPGRADED_NETHERITE_SHIELD_BASE;
         } else if (shield == ModItems.WATER_UPGRADED_NETHERITE_SHIELD.get()) {
            rendermaterial = ShieldTextures.LOCATION_WATER_UPGRADED_NETHERITE_SHIELD_BASE;
         } else if (shield == ModItems.WITHER_UPGRADED_NETHERITE_SHIELD.get()) {
            rendermaterial = ShieldTextures.LOCATION_WITHER_UPGRADED_NETHERITE_SHIELD_BASE;
         } else if (shield == ModItems.POISON_UPGRADED_NETHERITE_SHIELD.get()) {
            rendermaterial = ShieldTextures.LOCATION_POISON_UPGRADED_NETHERITE_SHIELD_BASE;
         } else if (shield == ModItems.PHANTOM_UPGRADED_NETHERITE_SHIELD.get()) {
            rendermaterial = ShieldTextures.LOCATION_PHANTOM_UPGRADED_NETHERITE_SHIELD_BASE;
         } else if (shield == ModItems.FEATHER_UPGRADED_NETHERITE_SHIELD.get()) {
            rendermaterial = ShieldTextures.LOCATION_FEATHER_UPGRADED_NETHERITE_SHIELD_BASE;
         } else if (shield == ModItems.CORRUPT_UPGRADED_NETHERITE_SHIELD.get()) {
            rendermaterial = ShieldTextures.LOCATION_CORRUPT_UPGRADED_NETHERITE_SHIELD_BASE;
         } else if (shield == ModItems.ECHO_UPGRADED_NETHERITE_SHIELD.get()) {
            rendermaterial = ShieldTextures.LOCATION_ECHO_UPGRADED_NETHERITE_SHIELD_BASE;
         }
      }

      VertexConsumer ivertexbuilder = rendermaterial.sprite().wrap(ItemRenderer.getFoilBufferDirect(buffer, this.shieldModel.renderType(rendermaterial.atlasLocation()), true, stack.hasFoil()));
      this.shieldModel.handle().render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
      if (flag) {
         List<Pair<Holder<BannerPattern>, DyeColor>> list = BannerBlockEntity.createPatterns(ShieldItem.getColor(stack), BannerBlockEntity.getItemPatterns(stack));
         BannerRenderer.renderPatterns(matrixStack, buffer, combinedLight, combinedOverlay, this.shieldModel.plate(), rendermaterial, false, list, stack.hasFoil());
      } else {
         this.shieldModel.plate().render(matrixStack, ivertexbuilder, combinedLight, combinedOverlay, 1.0F, 1.0F, 1.0F, 1.0F);
      }

      matrixStack.popPose();
   }
}
