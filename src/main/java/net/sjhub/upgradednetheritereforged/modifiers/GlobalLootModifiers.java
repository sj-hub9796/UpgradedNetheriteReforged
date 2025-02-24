package net.sjhub.upgradednetheritereforged.modifiers;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.rolfmao.upgradedcore.helpers.RandHelper;
import net.sjhub.upgradednetheritereforged.UpgradedNetheriteMod;
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
   private static final RegistryObject<Codec<UpgradedHoeModifier>> UPGRADED_HOE_MODIFIER;
   private static final RegistryObject<Codec<AutoSmeltModifier>> AUTOSMELT_MODIFIER;
   private static final RegistryObject<Codec<EnderTeleportModifier>> ENDERTP_MODIFIER;

   static {
      GLM = DeferredRegister.create(Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, UpgradedNetheriteMod.MOD_ID);
      UPGRADED_HOE_MODIFIER = GLM.register("upgraded_hoe_tool", UpgradedHoeModifier.CODEC);
      AUTOSMELT_MODIFIER = GLM.register("auto_smelt_tool", AutoSmeltModifier.CODEC);
      ENDERTP_MODIFIER = GLM.register("ender_teleport_tool", EnderTeleportModifier.CODEC);
   }

   public static class UpgradedHoeModifier extends LootModifier {
      public static final Supplier<Codec<UpgradedHoeModifier>> CODEC = Suppliers.memoize(() -> {
         return RecordCodecBuilder.create((inst) -> {
            return codecStart(inst).apply(inst, UpgradedHoeModifier::new);
         });
      });

      public UpgradedHoeModifier(LootItemCondition[] conditionsIn) {
         super(conditionsIn);
      }

      @Nonnull
      @NotNull
      protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
         ItemStack tool = (ItemStack)context.getParamOrNull(LootContextParams.TOOL);
         Entity entity = (Entity)context.getParamOrNull(LootContextParams.THIS_ENTITY);
         BlockState blockState = (BlockState)context.getParamOrNull(LootContextParams.BLOCK_STATE);
         Player player = null;
         if (entity instanceof Player) {
            player = (Player)entity;
         }

         ObjectArrayList<ItemStack> itemStackList = new ObjectArrayList();
         if (player != null && tool != null && blockState != null) {
            if (GoldUtil.isGoldHoe(tool) && blockState.getBlock() instanceof CarrotBlock && ((CarrotBlock)blockState.getBlock()).isMaxAge(blockState)) {
               Integer FortuneLevel = 0;
               Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(tool);
               if (!enchantments.isEmpty() && enchantments.containsKey(Enchantments.BLOCK_FORTUNE)) {
                  FortuneLevel = (Integer)enchantments.get(Enchantments.BLOCK_FORTUNE);
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
                     if (ItemStack.isSameItem(stack, new ItemStack(Items.CARROT))) {
                        Integer goldCarrot = 0;

                        int carrotCount;
                        for(carrotCount = 0; carrotCount < stack.getCount(); ++carrotCount) {
                           if (RandHelper.rand(finalFortuneLevel, 100)) {
                              goldCarrot = goldCarrot + 1;
                           }
                        }

                        carrotCount = stack.getCount() - goldCarrot;
                        int goldenCarrotCount = goldCarrot;
                        stack.setCount(stack.getCount() - goldCarrot);
                        itemStackList.add(ItemHandlerHelper.copyStackWithSize(stack, carrotCount));
                        itemStackList.add(ItemHandlerHelper.copyStackWithSize(new ItemStack(Items.GOLDEN_CARROT), goldenCarrotCount));
                     } else {
                        itemStackList.add(ItemHandlerHelper.copyStackWithSize(stack, stack.getCount()));
                     }
                  }

                  return itemStackList;
               }
            }

            if (PoisonUtil.isPoisonHoe(tool) && blockState.getBlock() instanceof PotatoBlock && ((PotatoBlock)blockState.getBlock()).isMaxAge(blockState)) {
               ObjectListIterator var8 = generatedLoot.iterator();

               while(var8.hasNext()) {
                  ItemStack stack = (ItemStack)var8.next();
                  if (ItemStack.isSameItem(stack, new ItemStack(Items.POTATO))) {
                     itemStackList.add(ItemHandlerHelper.copyStackWithSize(new ItemStack(Items.POISONOUS_POTATO), stack.getCount()));
                  } else {
                     itemStackList.add(ItemHandlerHelper.copyStackWithSize(stack, stack.getCount()));
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
      public static final Supplier<Codec<AutoSmeltModifier>> CODEC = Suppliers.memoize(() -> {
         return RecordCodecBuilder.create((inst) -> {
            return codecStart(inst).apply(inst, AutoSmeltModifier::new);
         });
      });

      public AutoSmeltModifier(LootItemCondition[] conditionsIn) {
         super(conditionsIn);
      }

      @Nonnull
      @NotNull
      protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
         ItemStack tool = (ItemStack)context.getParamOrNull(LootContextParams.TOOL);
         Entity killer = (Entity)context.getParamOrNull(LootContextParams.DIRECT_KILLER_ENTITY);
         Entity entity = (Entity)context.getParamOrNull(LootContextParams.THIS_ENTITY);
         BlockState blockState = (BlockState)context.getParamOrNull(LootContextParams.BLOCK_STATE);
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
         if (blockState != null && player != null && player.isCrouching()) {
            return generatedLoot;
         } else if (!UpgradedNetheriteConfig.EnableAutoSmelt || tool != null && ToolUtil.getDisableEffect(tool)) {
            return generatedLoot;
         } else if ((player == null || !FireUtil.isFireToolOrWeapon(player.getMainHandItem()) || ToolUtil.getDisableEffect(player.getMainHandItem())) && (arrow == null || arrow.getTags().isEmpty() || !FireUtil.isFireProjectile(arrow))) {
            return generatedLoot;
         } else {
            if (tool != null) {
               Integer FortuneLevel = 0;
               Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(tool);
               if (!enchantments.isEmpty() && enchantments.containsKey(Enchantments.BLOCK_FORTUNE)) {
                  FortuneLevel = (Integer)enchantments.get(Enchantments.BLOCK_FORTUNE);
               }

               Integer FortuneLevel2 = FortuneLevel;
               generatedLoot.forEach((stack) -> {
                  itemStackList.add(this.autoSmelt(stack, context, context.getLevel(), FortuneLevel2));
               });
            } else {
               generatedLoot.forEach((stack) -> {
                  itemStackList.add(this.autoSmelt(stack, context, context.getLevel(), 0));
               });
            }

            return itemStackList;
         }
      }

      protected ItemStack autoSmelt(ItemStack stack, LootContext context, Level level, Integer fortuneLevel) {
         Integer countBonus = 0;
         Optional<ItemStack> iStackSmelt = Optional.ofNullable((ItemStack)context.getLevel().getRecipeManager().getRecipeFor(RecipeType.SMELTING, new SimpleContainer(new ItemStack[]{stack}), context.getLevel()).map((smeltingRecipe) -> {
            return smeltingRecipe.getResultItem(context.getLevel().registryAccess());
         }).filter((itemStack) -> {
            return !itemStack.isEmpty();
         }).map((itemStack) -> {
            return ItemHandlerHelper.copyStackWithSize(itemStack, stack.getCount() * itemStack.getCount());
         }).orElse(stack));
         if (UpgradedNetheriteConfig.EnableAutoSmeltFortune && fortuneLevel > 0 && (stack.toString().contains("ore") && iStackSmelt.toString().contains("ingot") || stack.toString().contains("log") && iStackSmelt.toString().contains("charcoal")) && fortuneLevel > 0) {
            Double rand = Math.random();
            if (rand >= (double)(2 / (fortuneLevel + 2))) {
               Integer randI = (int)(Math.random() * (double)(fortuneLevel + 1));
               countBonus = randI;
            }
         }

         Integer finalCountBonus = stack.getCount() + countBonus;
         return (ItemStack)iStackSmelt.map((itemStack) -> {
            return ItemHandlerHelper.copyStackWithSize(itemStack, finalCountBonus);
         }).orElse(stack);
      }

      public Codec<? extends IGlobalLootModifier> codec() {
         return (Codec)CODEC.get();
      }
   }

   private static class EnderTeleportModifier extends LootModifier {
      public static final Supplier<Codec<EnderTeleportModifier>> CODEC = Suppliers.memoize(() -> {
         return RecordCodecBuilder.create((inst) -> {
            return codecStart(inst).apply(inst, EnderTeleportModifier::new);
         });
      });

      public EnderTeleportModifier(LootItemCondition[] conditionsIn) {
         super(conditionsIn);
      }

      @Nonnull
      @NotNull
      protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
         Entity killer = (Entity)context.getParamOrNull(LootContextParams.DIRECT_KILLER_ENTITY);
         Entity entity = (Entity)context.getParamOrNull(LootContextParams.THIS_ENTITY);
         BlockState blockState = (BlockState)context.getParamOrNull(LootContextParams.BLOCK_STATE);
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
            ItemStack heldItem = player.getMainHandItem();
            if (EnderUtil.isEnderToolOrWeapon(player.getMainHandItem()) && !ToolUtil.getDisableEffect(heldItem) && heldItem.getTag() != null && heldItem.getTag().contains("UpgradedNetherite_Tagged") && heldItem.getTag().getBoolean("UpgradedNetherite_Tagged")) {
               Level level = player.level();
               String levelPath = level.dimension().location().getPath();
               if (!levelPath.equals(heldItem.getTag().getString("UpgradedNetherite_Dimension"))) {
                  return generatedLoot;
               }

               BlockPos blockPos = new BlockPos(heldItem.getTag().getIntArray("UpgradedNetherite_Position")[0], heldItem.getTag().getIntArray("UpgradedNetherite_Position")[1], heldItem.getTag().getIntArray("UpgradedNetherite_Position")[2]);
               BlockState state = level.getBlockState(blockPos);
               if (state.hasBlockEntity()) {
                  BlockEntity blockEntity = level.getBlockEntity(blockPos);
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
         } else if (arrow != null && UpgradedNetheriteConfig.EnableTeleportChest && EnderUtil.isEnderProjectile(arrow) && arrow.getPersistentData().getBoolean("UpgradedNetherite_Tagged")) {
            Level level = arrow.level();
            String levelPath = level.dimension().location().getPath();
            if (!levelPath.equals(arrow.getPersistentData().getString("UpgradedNetherite_Dimension"))) {
               return generatedLoot;
            }

            BlockPos blockPos = new BlockPos(arrow.getPersistentData().getIntArray("UpgradedNetherite_Position")[0], arrow.getPersistentData().getIntArray("UpgradedNetherite_Position")[1], arrow.getPersistentData().getIntArray("UpgradedNetherite_Position")[2]);
            BlockState state = level.getBlockState(blockPos);
            if (state.hasBlockEntity()) {
               BlockEntity blockEntity = level.getBlockEntity(blockPos);
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
