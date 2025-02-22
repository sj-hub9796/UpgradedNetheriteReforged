package net.sjhub.upgradednetheritereforged.handlers;

import net.sjhub.upgradednetheritereforged.UpgradedNetheriteMod;
import net.sjhub.upgradednetheritereforged.packets.PacketEntityFallDistanceUpdate;
import net.minecraft.world.entity.Entity;

public class EntityFallDistanceUpdateHandler {
   public static void EntityFallDistanceUpdate(Integer entityId, Float fallDistance) {
      UpgradedNetheriteMod.packetInstance.sendToServer(new PacketEntityFallDistanceUpdate(entityId, fallDistance));
   }

   public static void handleEntityFallDistanceUpdate(Entity entity, Float fallDistance) {
      entity.f_19789_ = fallDistance;
   }
}
