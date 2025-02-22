package net.sjhub.upgradednetheritereforged.handlers;

import com.rolfmao.upgradedcore.compat.ExternalMods;
import net.sjhub.upgradednetheritereforged.config.UpgradedNetheriteConfig;
import net.sjhub.upgradednetheritereforged.utils.check.EchoUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerRespawnEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import top.theillusivec4.curios.api.CuriosApi;

@EventBusSubscriber(
   modid = "upgradednetherite_reforged",
   bus = Bus.FORGE
)
public class SoulboundEventHandler {
   private final Map<String, List<ItemStack>> transfertItemList = new HashMap();
   private final Map<String, List<Integer>> transfertItemSlot = new HashMap();
   private final Map<String, List<ItemStack>> transfertArmorList = new HashMap();
   private final Map<String, List<Integer>> transfertArmorSlot = new HashMap();
   private final Map<String, List<ItemStack>> transfertOffhandList = new HashMap();
   private final Map<String, List<Integer>> transfertOffhandSlot = new HashMap();
   private final Map<String, List<ItemStack>> transfertCuriosList = new HashMap();
   private final Map<String, List<Integer>> transfertCuriosSlot = new HashMap();

   @SubscribeEvent(
      priority = EventPriority.HIGH
   )
   public void onLivingDeathEvent(LivingDeathEvent event) {
      if (event.getEntity() instanceof Player && (UpgradedNetheriteConfig.EnableSoulbound || UpgradedNetheriteConfig.EnableKeepItemsChance)) {
         Player player = (Player)event.getEntity();
         if (this.transfertArmorList.containsKey(player.m_20148_().toString())) {
            this.transfertArmorList.remove(player.m_20148_().toString());
            this.transfertArmorSlot.remove(player.m_20148_().toString());
         }

         if (this.transfertItemList.containsKey(player.m_20148_().toString())) {
            this.transfertItemList.remove(player.m_20148_().toString());
            this.transfertItemSlot.remove(player.m_20148_().toString());
         }

         if (this.transfertOffhandList.containsKey(player.m_20148_().toString())) {
            this.transfertOffhandList.remove(player.m_20148_().toString());
            this.transfertOffhandSlot.remove(player.m_20148_().toString());
         }

         if (this.transfertCuriosList.containsKey(player.m_20148_().toString())) {
            this.transfertCuriosList.remove(player.m_20148_().toString());
            this.transfertCuriosSlot.remove(player.m_20148_().toString());
         }

         List<ItemStack> validTransferArmorList = new ArrayList();
         List<Integer> validTransferArmorSlot = new ArrayList();
         List<ItemStack> validTransferItemList = new ArrayList();
         List<Integer> validTransferItemSlot = new ArrayList();
         List<ItemStack> validTransferOffhandList = new ArrayList();
         List<Integer> validTransferOffhandSlot = new ArrayList();
         List<ItemStack> validTransferCuriosList = new ArrayList();
         List<Integer> validTransferCuriosSlot = new ArrayList();
         Boolean isWearingEchoArmor = EchoUtil.isWearingEchoArmor(player);
         List<ItemStack> armorsInventory = player.m_150109_().f_35975_;

         int i;
         for(int i = 0; i < armorsInventory.size(); ++i) {
            ItemStack itemStack = (ItemStack)armorsInventory.get(i);
            i = player.m_217043_().m_188503_(100) + 1;
            if ((EchoUtil.isEchoSoulbound(itemStack) && UpgradedNetheriteConfig.EnableSoulbound || UpgradedNetheriteConfig.EnableKeepItemsChance && isWearingEchoArmor && i <= UpgradedNetheriteConfig.KeepItemsChance) && !EnchantmentHelper.m_44924_(itemStack)) {
               validTransferArmorList.add(itemStack.m_41777_());
               validTransferArmorSlot.add(i);
               itemStack.m_41774_(itemStack.m_41613_());
            }
         }

         List<ItemStack> itemsInventory = player.m_150109_().f_35974_;

         for(int i = 0; i < itemsInventory.size(); ++i) {
            ItemStack itemStack = (ItemStack)itemsInventory.get(i);
            int nextInt = player.m_217043_().m_188503_(100) + 1;
            if ((EchoUtil.isEchoSoulbound(itemStack) && UpgradedNetheriteConfig.EnableSoulbound || UpgradedNetheriteConfig.EnableKeepItemsChance && isWearingEchoArmor && nextInt <= UpgradedNetheriteConfig.KeepItemsChance) && !EnchantmentHelper.m_44924_(itemStack)) {
               validTransferItemList.add(itemStack.m_41777_());
               validTransferItemSlot.add(i);
               itemStack.m_41774_(itemStack.m_41613_());
            }
         }

         List<ItemStack> offhandInventory = player.m_150109_().f_35976_;

         for(i = 0; i < offhandInventory.size(); ++i) {
            ItemStack itemStack = (ItemStack)offhandInventory.get(i);
            int nextInt = player.m_217043_().m_188503_(100) + 1;
            if ((EchoUtil.isEchoSoulbound(itemStack) && UpgradedNetheriteConfig.EnableSoulbound || UpgradedNetheriteConfig.EnableKeepItemsChance && isWearingEchoArmor && nextInt <= UpgradedNetheriteConfig.KeepItemsChance) && !EnchantmentHelper.m_44924_(itemStack)) {
               validTransferOffhandList.add(itemStack.m_41777_());
               validTransferOffhandSlot.add(i);
               itemStack.m_41774_(itemStack.m_41613_());
            }
         }

         if (ExternalMods.CURIOS.isLoaded()) {
            CuriosApi.getCuriosHelper().getEquippedCurios(player).ifPresent((value) -> {
               for(int i = 0; i < value.getSlots(); ++i) {
                  ItemStack itemStack = value.getStackInSlot(i);
                  int nextInt = player.m_217043_().m_188503_(100) + 1;
                  if ((EchoUtil.isEchoSoulbound(itemStack) && UpgradedNetheriteConfig.EnableSoulbound || UpgradedNetheriteConfig.EnableKeepItemsChance && isWearingEchoArmor && nextInt <= UpgradedNetheriteConfig.KeepItemsChance) && !EnchantmentHelper.m_44924_(itemStack)) {
                     validTransferCuriosList.add(itemStack.m_41777_());
                     validTransferCuriosSlot.add(i);
                     itemStack.m_41774_(itemStack.m_41613_());
                  }
               }

            });
         }

         if (validTransferArmorList.size() > 0) {
            this.transfertArmorList.put(player.m_20148_().toString(), validTransferArmorList);
            this.transfertArmorSlot.put(player.m_20148_().toString(), validTransferArmorSlot);
         }

         if (validTransferItemList.size() > 0) {
            this.transfertItemList.put(player.m_20148_().toString(), validTransferItemList);
            this.transfertItemSlot.put(player.m_20148_().toString(), validTransferItemSlot);
         }

         if (validTransferOffhandList.size() > 0) {
            this.transfertOffhandList.put(player.m_20148_().toString(), validTransferOffhandList);
            this.transfertOffhandSlot.put(player.m_20148_().toString(), validTransferOffhandSlot);
         }

         if (validTransferCuriosList.size() > 0) {
            this.transfertCuriosList.put(player.m_20148_().toString(), validTransferCuriosList);
            this.transfertCuriosSlot.put(player.m_20148_().toString(), validTransferCuriosSlot);
         }
      }

   }

   @SubscribeEvent(
      priority = EventPriority.HIGHEST
   )
   public void onPlayerRespawnEvent(PlayerRespawnEvent event) {
      if (UpgradedNetheriteConfig.EnableSoulbound || UpgradedNetheriteConfig.EnableKeepItemsChance) {
         Player player = event.getEntity();
         List toTransfert;
         List toSlot;
         int i;
         if (this.transfertArmorList.containsKey(player.m_20148_().toString())) {
            toTransfert = (List)this.transfertArmorList.get(player.m_20148_().toString());
            toSlot = (List)this.transfertArmorSlot.get(player.m_20148_().toString());

            for(i = 0; i < toTransfert.size(); ++i) {
               player.m_150109_().f_35975_.set((Integer)toSlot.get(i), ((ItemStack)toTransfert.get(i)).m_41777_());
            }
         }

         if (this.transfertItemList.containsKey(player.m_20148_().toString())) {
            toTransfert = (List)this.transfertItemList.get(player.m_20148_().toString());
            toSlot = (List)this.transfertItemSlot.get(player.m_20148_().toString());

            for(i = 0; i < toTransfert.size(); ++i) {
               player.m_150109_().f_35974_.set((Integer)toSlot.get(i), ((ItemStack)toTransfert.get(i)).m_41777_());
            }
         }

         if (this.transfertOffhandList.containsKey(player.m_20148_().toString())) {
            toTransfert = (List)this.transfertOffhandList.get(player.m_20148_().toString());
            toSlot = (List)this.transfertOffhandSlot.get(player.m_20148_().toString());

            for(i = 0; i < toTransfert.size(); ++i) {
               player.m_150109_().f_35976_.set((Integer)toSlot.get(i), ((ItemStack)toTransfert.get(i)).m_41777_());
            }
         }

         if (ExternalMods.CURIOS.isLoaded() && this.transfertCuriosList.containsKey(player.m_20148_().toString())) {
            toTransfert = (List)this.transfertCuriosList.get(player.m_20148_().toString());
            toSlot = (List)this.transfertCuriosSlot.get(player.m_20148_().toString());
            CuriosApi.getCuriosHelper().getEquippedCurios(player).ifPresent((value) -> {
               for(int i = 0; i < toTransfert.size(); ++i) {
                  value.setStackInSlot((Integer)toSlot.get(i), ((ItemStack)toTransfert.get(i)).m_41777_());
               }

            });
         }

         this.transfertArmorList.remove(player.m_20148_().toString());
         this.transfertArmorSlot.remove(player.m_20148_().toString());
         this.transfertItemList.remove(player.m_20148_().toString());
         this.transfertItemSlot.remove(player.m_20148_().toString());
         this.transfertOffhandList.remove(player.m_20148_().toString());
         this.transfertOffhandSlot.remove(player.m_20148_().toString());
         this.transfertCuriosList.remove(player.m_20148_().toString());
         this.transfertCuriosSlot.remove(player.m_20148_().toString());
      }

   }
}
