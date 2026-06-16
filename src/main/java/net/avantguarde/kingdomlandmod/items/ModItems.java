package net.avantguarde.kingdomlandmod.items;

import net.avantguarde.kingdomlandmod.KingdomLandMod;
import net.avantguarde.kingdomlandmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(Registries.ITEM, KingdomLandMod.MOD_ID);

    public static final RegistryObject<Item> ROYAL_BLOCK_ITEM = ITEMS.register("kingdom_block",
            () -> new BlockItem(ModBlocks.KINGDOM_BLOCK.get(), new Item.Properties()));
}