package net.sjhub.upgradednetheritereforged.handlers;

import net.sjhub.upgradednetheritereforged.UpgradedNetheriteMod;
import net.sjhub.upgradednetheritereforged.packets.PacketPlayerFallDistanceUpdate;
import java.util.UUID;
import net.minecraft.server.level.ServerPlayer;

public class PlayerFallDistanceUpdateHandler {
   public static void PlayerFallDistanceUpdate(UUID player, Float fallDistance) {
      UpgradedNetheriteMod.packetInstance.sendToServer(new PacketPlayerFallDistanceUpdate(player, fallDistance));
   }

   public static void handlePlayerFallDistanceUpdate(ServerPlayer player, Float fallDistance) {
      player.fallDistance = fallDistance;
   }
}
