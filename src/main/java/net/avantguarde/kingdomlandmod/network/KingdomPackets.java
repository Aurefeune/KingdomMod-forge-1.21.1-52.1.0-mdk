package net.avantguarde.kingdomlandmod.network;

import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.avantguarde.kingdomlandmod.KingdomLandMod;

public class KingdomPackets {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new net.minecraft.resources.ResourceLocation(KingdomLandMod.MOD_ID, "messages"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;

    public static void register() {
        // Enregistrer les packets C2S (Client to Server)
        CHANNEL.messageBuilder(CreateKingdomPacket.class, packetId++, NetworkDirection.PLAY_TO_SERVER)
                .decoder(CreateKingdomPacket::new)
                .encoder(CreateKingdomPacket::toBytes)
                .consumerMainThread(CreateKingdomPacket::handle)
                .add();
    }
}
