package net.sjhub.upgradednetheritereforged.mixin;

import net.sjhub.upgradednetheritereforged.config.UpgradedNetheriteConfig;
import net.sjhub.upgradednetheritereforged.utils.check.WaterUtil;
import java.util.List;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.monster.ElderGuardian;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin({ElderGuardian.class})
public class MixinElderGuardian {
   @ModifyVariable(
      method = {"customServerAiStep"},
      at = @At(
   value = "INVOKE",
   target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V"
)
   )
   private List cleanList(List<ServerPlayer> list) {
      if (UpgradedNetheriteConfig.EnableElderGuardianDebuffImmune) {
         list.removeIf(WaterUtil::isWearingWaterArmor);
      }

      return list;
   }
}
