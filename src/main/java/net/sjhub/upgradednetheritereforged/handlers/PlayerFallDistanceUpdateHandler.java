package net.sjhub.upgradednetheritereforged.handlers;

import net.sjhub.upgradednetheritereforged.UpgradedNetheriteReforgedMod;
import net.sjhub.upgradednetheritereforged.packets.PacketPlayerFallDistanceUpdate;
import java.util.UUID;
import net.minecraft.server.level.ServerPlayer;

public class PlayerFallDistanceUpdateHandler {
   public static void PlayerFallDistanceUpdate(UUID player, Float fallDistance) {
      UpgradedNetheriteReforgedMod.packetInstance.sendToServer(new PacketPlayerFallDistanceUpdate(player, fallDistance));
   }

   public static void handlePlayerFallDistanceUpdate(ServerPlayer player, Float fallDistance) {
      player.f_19789_ = fallDistance;
   }
}
