package net.sjhub.upgradednetheritereforged.modifiers;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rolfmao.upgradedcore.helpers.RandHelper;
import net.sjhub.upgradednetheritereforged.config.UpgradedNetheriteConfig;
import net.sjhub.upgradednetheritereforged.utils.ToolUtil;
import net.sjhub.upgradednetheritereforged.utils.check.EnderUtil;
import net.sjhub.upgradednetheritereforged.utils.check.FireUtil;
import net.sjhub.upgradednetheritereforged.utils.check.GoldUtil;
import net.sjhub.upgradednetheritereforged.utils.check.PoisonUtil;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import it.unimi.dsi.fastutil.objects.ObjectListIterator;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import javax.annotation.Nonnull;
import net.minecraft.core.BlockPos;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CarrotBlock;
import net.minecraft.world.level.block.PotatoBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries.Keys;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.jetbrains.annotations.NotNull;

public class GlobalLootModifiers {
   public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLM;
   private static final RegistryObject<Codec<GlobalLootModifiers.UpgradedHoeModifier>> UPGRADED_HOE_MODIFIER;
   private static final RegistryObject<Codec<GlobalLootModifiers.AutoSmeltModifier>> AUTOSMELT_MODIFIER;
   private static final RegistryObject<Codec<GlobalLootModifiers.EnderTeleportModifier>> ENDERTP_MODIFIER;

   static {
      GLM = DeferredRegister.create(Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, "upgradednetherite_reforged");
      UPGRADED_HOE_MODIFIER = GLM.register("upgraded_hoe_tool", GlobalLootModifiers.UpgradedHoeModifier.CODEC);
      AUTOSMELT_MODIFIER = GLM.register("auto_smelt_tool", GlobalLootModifiers.AutoSmeltModifier.CODEC);
      ENDERTP_MODIFIER = GLM.register("ender_teleport_tool", GlobalLootModifiers.EnderTeleportModifier.CODEC);
   }

   private static class UpgradedHoeModifier extends LootModifier {
      public static final Supplier<Codec<GlobalLootModifiers.UpgradedHoeModifier>> CODEC = Suppliers.memoize(() -> {
         return RecordCodecBuilder.create((inst) -> {
            return codecStart(inst).apply(inst, GlobalLootModifiers.UpgradedHoeModifier::new);
         });
      });

      public UpgradedHoeModifier(LootItemCondition[] conditionsIn) {
         super(conditionsIn);
      }

      @Nonnull
      @NotNull
      protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
         ItemStack tool = (ItemStack)context.m_78953_(LootContextParams.f_81463_);
         Entity entity = (Entity)context.m_78953_(LootContextParams.f_81455_);
         BlockState blockState = (BlockState)context.m_78953_(LootContextParams.f_81461_);
         Player player = null;
         if (entity instanceof Player) {
            player = (Player)entity;
         }

         ObjectArrayList<ItemStack> itemStackList = new ObjectArrayList();
         if (player != null && tool != null && blockState != null) {
            if (GoldUtil.isGoldHoe(tool) && blockState.m_60734_() instanceof CarrotBlock && ((CarrotBlock)blockState.m_60734_()).m_52307_(blockState)) {
               Integer FortuneLevel = 0;
               Map<Enchantment, Integer> enchantments = EnchantmentHelper.m_44831_(tool);
               if (!enchantments.isEmpty() && enchantments.containsKey(Enchantments.f_44987_)) {
                  FortuneLevel = (Integer)enchantments.get(Enchantments.f_44987_);
                  if (FortuneLevel >= 3) {
                     FortuneLevel = FortuneLevel + UpgradedNetheriteConfig.FortuneBonus + UpgradedNetheriteConfig.FortuneEnchantBonus;
                  } else {
                     FortuneLevel = FortuneLevel + UpgradedNetheriteConfig.FortuneBonus;
                  }
               }

               Integer finalFortuneLevel = FortuneLevel;
               ObjectListIterator var11 = generatedLoot.iterator();

               while(true) {
                  while(var11.hasNext()) {
                     ItemStack stack = (ItemStack)var11.next();
                     if (ItemStack.m_41746_(stack, new ItemStack(Items.f_42619_))) {
                        Integer goldCarrot = 0;

                        int carrotCount;
                        for(carrotCount = 0; carrotCount < stack.m_41613_(); ++carrotCount) {
                           if (RandHelper.rand(finalFortuneLevel, 100)) {
                              goldCarrot = goldCarrot + 1;
                           }
                        }

                        carrotCount = stack.m_41613_() - goldCarrot;
                        int goldenCarrotCount = goldCarrot;
                        stack.m_41764_(stack.m_41613_() - goldCarrot);
                        itemStackList.add(ItemHandlerHelper.copyStackWithSize(stack, carrotCount));
                        itemStackList.add(ItemHandlerHelper.copyStackWithSize(new ItemStack(Items.f_42677_), goldenCarrotCount));
                     } else {
                        itemStackList.add(ItemHandlerHelper.copyStackWithSize(stack, stack.m_41613_()));
                     }
                  }

                  return itemStackList;
               }
            }

            if (PoisonUtil.isPoisonHoe(tool) && blockState.m_60734_() instanceof PotatoBlock && ((PotatoBlock)blockState.m_60734_()).m_52307_(blockState)) {
               ObjectListIterator var8 = generatedLoot.iterator();

               while(var8.hasNext()) {
                  ItemStack stack = (ItemStack)var8.next();
                  if (ItemStack.m_41746_(stack, new ItemStack(Items.f_42620_))) {
                     itemStackList.add(ItemHandlerHelper.copyStackWithSize(new ItemStack(Items.f_42675_), stack.m_41613_()));
                  } else {
                     itemStackList.add(ItemHandlerHelper.copyStackWithSize(stack, stack.m_41613_()));
                  }
               }

               return itemStackList;
            }
         }

         return generatedLoot;
      }

      public Codec<? extends IGlobalLootModifier> codec() {
         return (Codec)CODEC.get();
      }
   }

   private static class AutoSmeltModifier extends LootModifier {
      public static final Supplier<Codec<GlobalLootModifiers.AutoSmeltModifier>> CODEC = Suppliers.memoize(() -> {
         return RecordCodecBuilder.create((inst) -> {
            return codecStart(inst).apply(inst, GlobalLootModifiers.AutoSmeltModifier::new);
         });
      });

      public AutoSmeltModifier(LootItemCondition[] conditionsIn) {
         super(conditionsIn);
      }

      @Nonnull
      @NotNull
      protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
         ItemStack tool = (ItemStack)context.m_78953_(LootContextParams.f_81463_);
         Entity killer = (Entity)context.m_78953_(LootContextParams.f_81459_);
         Entity entity = (Entity)context.m_78953_(LootContextParams.f_81455_);
         BlockState blockState = (BlockState)context.m_78953_(LootContextParams.f_81461_);
         Player player = null;
         Projectile arrow = null;
         if (entity instanceof Player) {
            player = (Player)entity;
         }

         if (player == null && killer instanceof Player) {
            player = (Player)killer;
         }

         if (killer instanceof Projectile) {
            arrow = (Projectile)killer;
         }

         ObjectArrayList<ItemStack> itemStackList = new ObjectArrayList();
         if (blockState != null && player != null && player.m_6047_()) {
            return generatedLoot;
         } else if (!UpgradedNetheriteConfig.EnableAutoSmelt || tool != null && ToolUtil.getDisableEffect(tool)) {
            return generatedLoot;
         } else if ((player == null || !FireUtil.isFireToolOrWeapon(player.m_21205_()) || ToolUtil.getDisableEffect(player.m_21205_())) && (arrow == null || arrow.m_19880_().isEmpty() || !FireUtil.isFireProjectile(arrow))) {
            return generatedLoot;
         } else {
            if (tool != null) {
               Integer FortuneLevel = 0;
               Map<Enchantment, Integer> enchantments = EnchantmentHelper.m_44831_(tool);
               if (!enchantments.isEmpty() && enchantments.containsKey(Enchantments.f_44987_)) {
                  FortuneLevel = (Integer)enchantments.get(Enchantments.f_44987_);
               }

               generatedLoot.forEach((stack) -> {
                  itemStackList.add(this.autoSmelt(stack, context, context.m_78952_(), FortuneLevel));
               });
            } else {
               generatedLoot.forEach((stack) -> {
                  itemStackList.add(this.autoSmelt(stack, context, context.m_78952_(), 0));
               });
            }

            return itemStackList;
         }
      }

      protected ItemStack autoSmelt(ItemStack stack, LootContext context, Level level, Integer fortuneLevel) {
         Integer countBonus = 0;
         Optional<ItemStack> iStackSmelt = Optional.ofNullable((ItemStack)context.m_78952_().m_7465_().m_44015_(RecipeType.f_44108_, new SimpleContainer(new ItemStack[]{stack}), context.m_78952_()).map((smeltingRecipe) -> {
            return smeltingRecipe.m_8043_(context.m_78952_().m_9598_());
         }).filter((itemStack) -> {
            return !itemStack.m_41619_();
         }).map((itemStack) -> {
            return ItemHandlerHelper.copyStackWithSize(itemStack, stack.m_41613_() * itemStack.m_41613_());
         }).orElse(stack));
         if (UpgradedNetheriteConfig.EnableAutoSmeltFortune && fortuneLevel > 0 && (stack.toString().contains("ore") && iStackSmelt.toString().contains("ingot") || stack.toString().contains("log") && iStackSmelt.toString().contains("charcoal")) && fortuneLevel > 0) {
            Double rand = Math.random();
            if (rand >= (double)(2 / (fortuneLevel + 2))) {
               Integer randI = (int)(Math.random() * (double)(fortuneLevel + 1));
               countBonus = randI;
            }
         }

         Integer finalCountBonus = stack.m_41613_() + countBonus;
         return (ItemStack)iStackSmelt.map((itemStack) -> {
            return ItemHandlerHelper.copyStackWithSize(itemStack, finalCountBonus);
         }).orElse(stack);
      }

      public Codec<? extends IGlobalLootModifier> codec() {
         return (Codec)CODEC.get();
      }
   }

   private static class EnderTeleportModifier extends LootModifier {
      public static final Supplier<Codec<GlobalLootModifiers.EnderTeleportModifier>> CODEC = Suppliers.memoize(() -> {
         return RecordCodecBuilder.create((inst) -> {
            return codecStart(inst).apply(inst, GlobalLootModifiers.EnderTeleportModifier::new);
         });
      });

      public EnderTeleportModifier(LootItemCondition[] conditionsIn) {
         super(conditionsIn);
      }

      @Nonnull
      @NotNull
      protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
         Entity killer = (Entity)context.m_78953_(LootContextParams.f_81459_);
         Entity entity = (Entity)context.m_78953_(LootContextParams.f_81455_);
         BlockState blockState = (BlockState)context.m_78953_(LootContextParams.f_81461_);
         Projectile arrow = null;
         Player player = null;
         if (blockState != null && entity instanceof Player) {
            player = (Player)entity;
         } else if (blockState == null && killer instanceof Player) {
            player = (Player)killer;
         } else if (blockState == null && killer instanceof Projectile) {
            arrow = (Projectile)killer;
         }

         if (player != null && UpgradedNetheriteConfig.EnableTeleportChest) {
            ItemStack heldItem = player.m_21205_();
            if (EnderUtil.isEnderToolOrWeapon(player.m_21205_()) && !ToolUtil.getDisableEffect(heldItem) && heldItem.m_41783_() != null && heldItem.m_41783_().m_128441_("UpgradedNetherite_Tagged") && heldItem.m_41783_().m_128471_("UpgradedNetherite_Tagged")) {
               Level level = player.f_19853_;
               String levelPath = level.m_46472_().m_135782_().m_135815_();
               if (!levelPath.equals(heldItem.m_41783_().m_128461_("UpgradedNetherite_Dimension"))) {
                  return generatedLoot;
               }

               BlockPos blockPos = new BlockPos(heldItem.m_41783_().m_128465_("UpgradedNetherite_Position")[0], heldItem.m_41783_().m_128465_("UpgradedNetherite_Position")[1], heldItem.m_41783_().m_128465_("UpgradedNetherite_Position")[2]);
               BlockState state = level.m_8055_(blockPos);
               if (state.m_155947_()) {
                  BlockEntity blockEntity = level.m_7702_(blockPos);
                  if (blockEntity != null) {
                     IItemHandler iItemHandler = (IItemHandler)((ImmutablePair)blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).map((capability) -> {
                        return ImmutablePair.of(capability, blockEntity);
                     }).get()).getKey();
                     if (iItemHandler != null) {
                        ObjectArrayList<ItemStack> itemStackList = new ObjectArrayList();
                        generatedLoot.forEach((stack) -> {
                           itemStackList.add(ItemHandlerHelper.insertItemStacked(iItemHandler, stack, false));
                        });
                        return itemStackList;
                     }
                  }
               }
            }
         } else if (arrow != null && UpgradedNetheriteConfig.EnableTeleportChest && EnderUtil.isEnderProjectile(arrow) && arrow.getPersistentData().m_128471_("UpgradedNetherite_Tagged")) {
            Level level = arrow.f_19853_;
            String levelPath = level.m_46472_().m_135782_().m_135815_();
            if (!levelPath.equals(arrow.getPersistentData().m_128461_("UpgradedNetherite_Dimension"))) {
               return generatedLoot;
            }

            BlockPos blockPos = new BlockPos(arrow.getPersistentData().m_128465_("UpgradedNetherite_Position")[0], arrow.getPersistentData().m_128465_("UpgradedNetherite_Position")[1], arrow.getPersistentData().m_128465_("UpgradedNetherite_Position")[2]);
            BlockState state = level.m_8055_(blockPos);
            if (state.m_155947_()) {
               BlockEntity blockEntity = level.m_7702_(blockPos);
               if (blockEntity != null) {
                  IItemHandler iItemHandler = (IItemHandler)((ImmutablePair)blockEntity.getCapability(ForgeCapabilities.ITEM_HANDLER).map((capability) -> {
                     return ImmutablePair.of(capability, blockEntity);
                  }).get()).getKey();
                  if (iItemHandler != null) {
                     ObjectArrayList<ItemStack> itemStackList = new ObjectArrayList();
                     generatedLoot.forEach((stack) -> {
                        itemStackList.add(ItemHandlerHelper.insertItemStacked(iItemHandler, stack, false));
                     });
                     return itemStackList;
                  }
               }
            }
         }

         return generatedLoot;
      }

      public Codec<? extends IGlobalLootModifier> codec() {
         return (Codec)CODEC.get();
      }
   }
}
