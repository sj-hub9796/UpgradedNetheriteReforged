package net.sjhub.upgradednetheritereforged.utils;

import net.sjhub.upgradednetheritereforged.content.UpgradedNetheriteShield;
import net.minecraft.core.RegistryAccess;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShieldDecorationRecipe;
import net.minecraft.world.item.crafting.SimpleCraftingRecipeSerializer;
import net.minecraft.world.level.Level;

public class ShieldRecipes extends ShieldDecorationRecipe {
   public static final SimpleCraftingRecipeSerializer<ShieldRecipes> SERIALIZER = new SimpleCraftingRecipeSerializer(ShieldRecipes::new);

   public ShieldRecipes(ResourceLocation resourceLocation, CraftingBookCategory craftingBookCategory) {
      super(resourceLocation, craftingBookCategory);
   }

   public boolean matches(CraftingContainer inv, Level worldIn) {
      ItemStack itemstack = ItemStack.EMPTY;
      ItemStack itemstack1 = ItemStack.EMPTY;

      for(int i = 0; i < inv.getContainerSize(); ++i) {
         ItemStack itemstack2 = inv.getItem(i);
         if (!itemstack2.isEmpty()) {
            if (itemstack2.getItem() instanceof BannerItem) {
               if (!itemstack1.isEmpty()) {
                  return false;
               }

               itemstack1 = itemstack2;
            } else {
               if (!(itemstack2.getItem() instanceof UpgradedNetheriteShield)) {
                  return false;
               }

               if (!itemstack.isEmpty()) {
                  return false;
               }

               if (itemstack2.getTagElement("BlockEntityTag") != null) {
                  return false;
               }

               itemstack = itemstack2;
            }
         }
      }

      return !itemstack.isEmpty() && !itemstack1.isEmpty();
   }

   public ItemStack assemble(CraftingContainer inv, RegistryAccess p_267165_) {
      ItemStack itemstack = ItemStack.EMPTY;
      ItemStack itemstack1 = ItemStack.EMPTY;

      for(int i = 0; i < inv.getContainerSize(); ++i) {
         ItemStack itemstack2 = inv.getItem(i);
         if (!itemstack2.isEmpty()) {
            if (itemstack2.getItem() instanceof BannerItem) {
               itemstack = itemstack2;
            } else if (itemstack2.getItem() instanceof UpgradedNetheriteShield) {
               itemstack1 = itemstack2.copy();
            }
         }
      }

      if (itemstack1.isEmpty()) {
         return itemstack1;
      } else {
         CompoundTag compoundnbt = itemstack.getTagElement("BlockEntityTag");
         CompoundTag compoundnbt1 = compoundnbt == null ? new CompoundTag() : compoundnbt.copy();
         compoundnbt1.putInt("Base", ((BannerItem)itemstack.getItem()).getColor().getId());
         itemstack1.addTagElement("BlockEntityTag", compoundnbt1);
         return itemstack1;
      }
   }

   public boolean canCraftInDimensions(int width, int height) {
      return width * height >= 2;
   }

   public RecipeSerializer<?> getSerializer() {
      return SERIALIZER;
   }
}
