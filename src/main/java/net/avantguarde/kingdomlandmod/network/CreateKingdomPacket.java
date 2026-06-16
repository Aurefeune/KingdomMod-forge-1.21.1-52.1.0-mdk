package net.avantguarde.kingdomlandmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.PacketDistributor;
import net.minecraft.core.BlockPos;

import java.util.function.Supplier;

public class CreateKingdomPacket {
    private final BlockPos blockPos;
    private final String kingdomName;

    public CreateKingdomPacket(BlockPos blockPos, String kingdomName) {
        this.blockPos = blockPos;
        this.kingdomName = kingdomName;
    }

    public CreateKingdomPacket(FriendlyByteBuf buf) {
        this.blockPos = buf.readBlockPos();
        this.kingdomName = buf.readUtf(32);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(this.blockPos);
        buf.writeUtf(this.kingdomName);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            DistExecutor.unsafeRunWhenOn(Dist.DEDICATED_SERVER, () -> () -> {
                // Logique serveur : créer le royaume
                net.minecraft.server.level.ServerPlayer player = context.getSender();
                if (player != null) {
                    player.displayClientMessage(
                            net.minecraft.network.chat.Component.literal("Fondation du royaume \"" + this.kingdomName + "\" ici !"),
                            false
                    );
                }
            });
        });
        return true;
    }
}
