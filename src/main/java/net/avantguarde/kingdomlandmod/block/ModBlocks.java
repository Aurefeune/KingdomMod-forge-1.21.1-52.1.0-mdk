package net.avantguarde.kingdomlandmod.block;

import net.avantguarde.kingdomlandmod.KingdomLandMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, KingdomLandMod.MOD_ID);

    public static final RegistryObject<Block> KINGDOM_BLOCK = BLOCKS.register("kingdom_block",
            () -> new KingdomBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.GOLD)
                    .strength(3.0f, 6.0f)));
}
