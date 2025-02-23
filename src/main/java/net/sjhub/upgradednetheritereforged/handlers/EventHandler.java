package net.sjhub.upgradednetheritereforged.handlers;

import net.sjhub.upgradednetheritereforged.config.UpgradedNetheriteConfig;
import net.sjhub.upgradednetheritereforged.init.UpgradedNetheriteEffects;
import net.sjhub.upgradednetheritereforged.utils.DurabilityUtil;
import net.sjhub.upgradednetheritereforged.utils.check.CorruptUtil;
import net.sjhub.upgradednetheritereforged.utils.check.EchoUtil;
import net.sjhub.upgradednetheritereforged.utils.check.FireUtil;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ViewportEvent.RenderFog;
import net.minecraftforge.event.entity.EntityTeleportEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingTickEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ForgeRegistries;

@EventBusSubscriber(
   modid = UpgradedNetheriteMod.MOD_ID,
   bus = Bus.FORGE
)
public class EventHandler {
   @SubscribeEvent(
      receiveCanceled = true,
      priority = EventPriority.HIGHEST
   )
   public static void onEnderTeleport(EntityTeleportEvent event) {
      if (event.getEntity() instanceof LivingEntity && ((LivingEntity)event.getEntity()).hasEffect((MobEffect)UpgradedNetheriteEffects.ENDER_ANCHOR.get())) {
         event.setCanceled(true);
      }

   }

   @SubscribeEvent
   @OnlyIn(Dist.CLIENT)
   public static void onFogDensity(RenderFog event) {
      LocalPlayer player = Minecraft.getInstance().player;
      if (player != null) {
         if (FireUtil.isWearingFireArmor(player) && event.getCamera().getFluidInCamera() == FogType.LAVA) {
            event.setFarPlaneDistance(event.getFarPlaneDistance() + 3.0F);
            event.setCanceled(true);
         }

      }
   }

   @SubscribeEvent
   public void onLivingUpdate(LivingTickEvent event) {
      if (event.getEntity() instanceof Player) {
         Player player = (Player)event.getEntity();
         ItemStack heldItem = player.getMainHandItem();
         if (CorruptUtil.intWearingCorrupt(player, false) > 0 || CorruptUtil.isCorruptToolOrWeapon(heldItem)) {
            DurabilityUtil.CorruptDurabilityGain(player);
         }
      }

      if (event.getEntity().hasEffect((MobEffect)UpgradedNetheriteEffects.ATTRACTION.get())) {
         Entity target = event.getEntity();
         double tx = target.getX();
         double ty = target.getY();
         double tz = target.getZ();
         List<LivingEntity> entities = target.level().getEntitiesOfClass(LivingEntity.class, new AABB(tx - 5.0D, ty - 5.0D, tz - 5.0D, tx + 5.0D, ty + 5.0D, tz + 5.0D));
         int pulled = 0;
         Iterator var11 = entities.iterator();

         while(true) {
            LivingEntity entity;
            do {
               do {
                  do {
                     do {
                        do {
                           if (!var11.hasNext()) {
                              return;
                           }

                           entity = (LivingEntity)var11.next();
                        } while(!entity.isAlive());
                     } while(entity == event.getEntity());
                  } while(target instanceof WitherBoss);
               } while(target instanceof EnderDragon);
            } while(entity instanceof Player && !(target instanceof Player));

            if (pulled > 32) {
               break;
            }

            Vec3 entityVector = new Vec3(entity.getX(), entity.getY(), entity.getZ());
            Vec3 finalVector = (new Vec3(tx, ty, tz)).subtract(entityVector);
            if (entity.getY() < ty && target.onGround()) {
               finalVector = (new Vec3(tx, ty + 0.1D, tz)).subtract(entityVector);
               entity.fallDistance = 0.0F;
            }

            if (Math.sqrt(finalVector.x * finalVector.x + finalVector.y * finalVector.y + finalVector.z * finalVector.z) > 1.8D) {
               finalVector = finalVector.normalize();
               entity.setDeltaMovement(finalVector.x * 0.15000000596046448D, finalVector.y * 0.15000000596046448D, finalVector.z * 0.15000000596046448D);
               ++pulled;
            }
         }
      }

   }

   @SubscribeEvent
   public void onItemTooltipEvent(ItemTooltipEvent event) {
      ItemStack itemStack = event.getItemStack();
      if (EchoUtil.isEchoSoulbound(itemStack) && UpgradedNetheriteConfig.EnableSoulbound) {
         Iterator var3 = event.getToolTip().iterator();

         while(var3.hasNext()) {
            Component line = (Component)var3.next();
            if (line instanceof Component && (line.getString().contains("item.unbreakable") || line.getString().contains("item.canBreak") || line.getString().contains("item.canPlace") || line.getString().contains("item.durability"))) {
               event.getToolTip().add(event.getToolTip().indexOf(line), Component.translatable("upgradednetherite_reforged.Echo_Tool.TT"));
               break;
            }

            if (ForgeRegistries.ITEMS.getKey(event.getItemStack().getItem()).toString().equals(line.getString())) {
               event.getToolTip().add(event.getToolTip().indexOf(line), Component.translatable("upgradednetherite_reforged.Echo_Tool.TT"));
               break;
            }

            if (line instanceof Component && line.getString().contains("item.nbt_tags")) {
               event.getToolTip().add(event.getToolTip().indexOf(line), Component.translatable("upgradednetherite_reforged.Echo_Tool.TT"));
               break;
            }

            if (event.getToolTip().indexOf(line) == event.getToolTip().size() - 1) {
               event.getToolTip().add(event.getToolTip().indexOf(line) + 1, Component.translatable("upgradednetherite_reforged.Echo_Tool.TT"));
               break;
            }
         }
      }

   }
}
