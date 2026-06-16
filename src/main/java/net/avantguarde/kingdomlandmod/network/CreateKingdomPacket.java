package net.avantguarde.kingdomlandmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.avantguarde.kingdomlandmod.KingdomLandMod;
import net.minecraft.core.BlockPos;

public class CreateKingdomPacket implements CustomPacketPayload {
    public static final ResourceLocation ID = new ResourceLocation(KingdomLandMod.MOD_ID, "create_kingdom");
    
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

    @Override
    public void write(FriendlyByteBuf buf) {
        buf.writeBlockPos(this.blockPos);
        buf.writeUtf(this.kingdomName);
    }

    @Override
    public ResourceLocation id() {
        return ID;
    }

    @Override
    public Type type() {
        return Type.PLAY;
    }

    public BlockPos getBlockPos() {
        return this.blockPos;
    }

    public String getKingdomName() {
        return this.kingdomName;
    }
}
