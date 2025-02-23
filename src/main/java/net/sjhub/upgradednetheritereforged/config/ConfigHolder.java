package net.sjhub.upgradednetheritereforged.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import org.apache.commons.lang3.tuple.Pair;

public final class ConfigHolder {
   public static final ForgeConfigSpec CLIENT_SPEC;
   public static final ForgeConfigSpec SERVER_SPEC;
   static final ClientConfig CLIENT;
   static final ServerConfig SERVER;

   static {
      Pair<ClientConfig, ForgeConfigSpec> clientPair = new Builder().configure(ClientConfig::new);
      CLIENT = clientPair.getLeft();
      CLIENT_SPEC = clientPair.getRight();

      Pair<ServerConfig, ForgeConfigSpec> serverPair = new Builder().configure(ServerConfig::new);
      SERVER = serverPair.getLeft();
      SERVER_SPEC = serverPair.getRight();
   }
}
