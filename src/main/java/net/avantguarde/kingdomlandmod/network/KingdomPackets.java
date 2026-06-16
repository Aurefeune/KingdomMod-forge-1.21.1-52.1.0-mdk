package net.avantguarde.kingdomlandmod.network;

import net.minecraftforge.network.ChannelBuilder;
import net.minecraftforge.network.SimpleChannel;
import net.avantguarde.kingdomlandmod.KingdomLandMod;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.network.PacketDistributor;

public class KingdomPackets {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = ChannelBuilder
            .named(new net.minecraft.resources.ResourceLocation(KingdomLandMod.MOD_ID, "messages"))
            .serverAccepts(PROTOCOL_VERSION, (status, version) -> true)
            .clientAccepts(PROTOCOL_VERSION, (status, version) -> true)
            .build();

    public static void register() {
        CHANNEL.messageBuilder(CreateKingdomPacket.class)
                .decoder(CreateKingdomPacket::new)
                .encoder(CreateKingdomPacket::write)
                .consumerMainThreadSafe((packet, context) -> {
                    context.enqueueWork(() -> {
                        net.minecraft.server.level.ServerPlayer player = context.getSender();
                        if (player != null) {
                            player.displayClientMessage(
                                    net.minecraft.network.chat.Component.literal("Fondation du royaume \"" + packet.getKingdomName() + "\" ici !"),
                                    false
                            );
                        }
                    });
                })
                .build();
    }
}
