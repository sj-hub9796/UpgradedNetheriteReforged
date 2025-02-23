package net.sjhub.upgradednetheritereforged.handlers;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.rolfmao.upgradedcore_old.helpers.TextHelper;
import net.sjhub.upgradednetheritereforged.config.UpgradedNetheriteConfig;
import net.sjhub.upgradednetheritereforged.utils.ToolUtil;
import net.sjhub.upgradednetheritereforged.utils.check.EchoUtil;
import net.sjhub.upgradednetheritereforged.utils.check.FeatherUtil;
import net.sjhub.upgradednetheritereforged.utils.check.PhantomUtil;
import net.sjhub.upgradednetheritereforged.utils.check.WaterUtil;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.BreakSpeed;
import net.minecraftforge.event.level.BlockEvent.BreakEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(
   modid = "upgradednetherite_reforged",
   bus = Bus.FORGE
)
public class ToolEventHandler {
   private Multimap<Attribute, AttributeModifier> ReachAttributeMap() {
      Multimap<Attribute, AttributeModifier> attributesDefault = HashMultimap.create();
      attributesDefault.put((Attribute)ForgeMod.BLOCK_REACH.get(), new AttributeModifier(UUID.fromString("ca095c88-32c8-11eb-adc1-0242ac120002"), "upgradednetherite:reach_bonus", (double)UpgradedNetheriteConfig.BonusReachTool, Operation.ADDITION));
      return attributesDefault;
   }

   private Multimap<Attribute, AttributeModifier> AttackRangeAttributeMap() {
      Multimap<Attribute, AttributeModifier> attributesDefault = HashMultimap.create();
      attributesDefault.put((Attribute)ForgeMod.ENTITY_REACH.get(), new AttributeModifier(UUID.fromString("ea87f882-cd65-4b5c-9f49-0be421f62df9"), "upgradednetherite:attackrange_bonus", (double)UpgradedNetheriteConfig.BonusReachTool, Operation.ADDITION));
      return attributesDefault;
   }

   @SubscribeEvent
   public void onLivingUpdate(LivingTickEvent event) {
      if (event.getEntity() instanceof Player) {
         Player player = (Player)event.getEntity();
         ItemStack heldItem = player.getMainHandItem();
         if (PhantomUtil.isPhantomToolOrWeapon(heldItem) && UpgradedNetheriteConfig.EnableReachEffect) {
            player.getAttributes().addTransientAttributeModifiers(this.ReachAttributeMap());
            player.getAttributes().addTransientAttributeModifiers(this.AttackRangeAttributeMap());
         } else if (!PhantomUtil.isPhantomToolOrWeapon(heldItem) || !UpgradedNetheriteConfig.EnableReachEffect) {
            player.getAttributes().removeAttributeModifiers(this.ReachAttributeMap());
            player.getAttributes().removeAttributeModifiers(this.AttackRangeAttributeMap());
         }

         if (!player.level().isClientSide && PhantomUtil.isPhantomToolOrWeapon(heldItem) && UpgradedNetheriteConfig.EnableGlowingEffect && !ToolUtil.getDisableEffect(player.getMainHandItem())) {
            List<Entity> list = player.level().getEntities(player, player.getBoundingBox().inflate(10.0D, 10.0D, 10.0D), (entity) -> {
               return entity instanceof Enemy;
            });
            if (!list.isEmpty()) {
               if (list.size() > 1) {
                  player.displayClientMessage(TextHelper.TCWO("upgradednetherite_reforged.Phantom_Tool_Detect2.TT", new Object[]{"§c" + list.size()}), true);
               } else {
                  player.displayClientMessage(TextHelper.TCWO("upgradednetherite_reforged.Phantom_Tool_Detect.TT", new Object[]{"§c" + list.size()}), true);
               }
            }
         }

         if (FeatherUtil.isFeatherToolOrWeapon(heldItem) && UpgradedNetheriteConfig.EnableAttractItem && !ToolUtil.getDisableEffect(player.getMainHandItem()) && !player.isCrouching()) {
            double px = player.getX();
            double py = player.getY();
            double pz = player.getZ();
            int count = 0;
            List<ItemEntity> items = player.level().getEntitiesOfClass(ItemEntity.class, new AABB(px - 5.0D, py - 5.0D, pz - 5.0D, px + 5.0D, py + 5.0D, pz + 5.0D));
            Iterator var12 = items.iterator();

            while(var12.hasNext()) {
               ItemEntity item = (ItemEntity)var12.next();
               if (item.isAlive() && !item.getItem().isEmpty() && !item.getPersistentData().getBoolean("PreventRemoteMovement")) {
                  if (count > 128) {
                     break;
                  }

                  Vec3 entityVector = new Vec3(item.getX(), item.getY(), item.getZ());
                  Vec3 finalVector = (new Vec3(px, py + 0.5D, pz)).subtract(entityVector);
                  if (Math.sqrt(finalVector.x * finalVector.x + finalVector.y * finalVector.y + finalVector.z * finalVector.z) > 1.0D) {
                     finalVector = finalVector.normalize();
                  }

                  item.setDeltaMovement(finalVector.x * 0.30000001192092896D, finalVector.y * 0.30000001192092896D, finalVector.z * 0.30000001192092896D);
                  item.setNoPickUpDelay();
                  ++count;
               }
            }
         }
      }

   }

   @SubscribeEvent
   public void onBreakBlock(BreakEvent event) {
      Player player = event.getPlayer();
      ItemStack heldItem = player.getMainHandItem();
      if (EchoUtil.isEchoToolOrWeapon(heldItem) && event.getExpToDrop() > 0 && UpgradedNetheriteConfig.EnableBonusExpEcho) {
         int nextInt;
         int exp;
         if (UpgradedNetheriteConfig.MaxExpEcho < UpgradedNetheriteConfig.MinExpEcho) {
            exp = 0;
         } else if (UpgradedNetheriteConfig.MaxExpEcho == UpgradedNetheriteConfig.MinExpEcho) {
            exp = UpgradedNetheriteConfig.MinExpEcho;
         } else {
            nextInt = player.getRandom().nextInt(UpgradedNetheriteConfig.MaxExpEcho - UpgradedNetheriteConfig.MinExpEcho + 1);
            exp = UpgradedNetheriteConfig.MinExpEcho + nextInt;
         }

         event.setExpToDrop(event.getExpToDrop() + exp);
      }

   }

   @SubscribeEvent
   public void onBreakSpeed(BreakSpeed event) {
      if (event.getEntity() instanceof Player) {
         Player player = event.getEntity();
         if (WaterUtil.isWaterToolOrWeapon(player.getMainHandItem()) && player.isEyeInFluid(FluidTags.WATER) && UpgradedNetheriteConfig.EnableMiningSpeedUnderwater) {
            event.setNewSpeed(event.getOriginalSpeed() * 2.0F);
         }
      }

   }
}
