package net.avantguarde.kingdomlandmod.block;

import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.avantguarde.kingdomlandmod.screen.CreateKingdomScreen;

public class KingdomBlock extends Block {

    public KingdomBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, net.minecraft.core.BlockPos pos, Player player, BlockHitResult hitResult) {
        if (level.isClientSide) {
            // Ouvrir le menu GUI côté client
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                net.minecraft.client.Minecraft.getInstance().setScreen(new CreateKingdomScreen(pos));
            });
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.SUCCESS;
    }
}
