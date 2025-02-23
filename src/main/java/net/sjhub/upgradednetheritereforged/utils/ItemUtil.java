package net.sjhub.upgradednetheritereforged.utils;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

public class ItemUtil {

    public static Item valueOf(String id) {
        return valueOf("minecraft", id);
    }

    @Nullable
    public static Item valueOf(String namespace, String id) {
        Item item = null;
        id = id.toLowerCase().trim();
        ResourceLocation resourceLocation = new ResourceLocation(namespace, id);
        item = ForgeRegistries.ITEMS.getValue(resourceLocation);

        // check fallback
        if (item == null || (!id.equals("air") && item == Items.AIR)) {
            ResourceLocation fallbackResourceLocation = new ResourceLocation("minecraft", id);
            item = ForgeRegistries.ITEMS.getValue(fallbackResourceLocation);
        }

        return item;
    }
}
