package net.avantguarde.kingdomlandmod.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;
import net.avantguarde.kingdomlandmod.KingdomLandMod;
import net.avantguarde.kingdomlandmod.network.KingdomPackets;
import net.avantguarde.kingdomlandmod.network.CreateKingdomPacket;
import net.minecraft.core.BlockPos;

public class CreateKingdomScreen extends Screen {
    private static final int WINDOW_WIDTH = 256;
    private static final int WINDOW_HEIGHT = 200;
    
    private EditBox kingdomNameInput;
    private final BlockPos blockPos;
    private int windowX;
    private int windowY;

    public CreateKingdomScreen(BlockPos blockPos) {
        super(Component.literal("Create Kingdom"));
        this.blockPos = blockPos;
    }

    @Override
    protected void init() {
        // Calcul de la position du menu (centré)
        this.windowX = (this.width - WINDOW_WIDTH) / 2;
        this.windowY = (this.height - WINDOW_HEIGHT) / 2;

        // Champ de saisie pour le nom du royaume
        this.kingdomNameInput = new EditBox(this.font, windowX + 20, windowY + 50, 216, 20, Component.literal("Kingdom Name"));
        this.kingdomNameInput.setMaxLength(32);
        this.kingdomNameInput.setValue("");
        this.addRenderableWidget(this.kingdomNameInput);
        this.setInitialFocus(this.kingdomNameInput);

        // Bouton "Create Kingdom"
        this.addRenderableWidget(Button.builder(Component.literal("Create Kingdom"), button -> this.onCreateKingdom())
                .pos(windowX + 50, windowY + 100)
                .width(156)
                .build());

        // Bouton "Cancel"
        this.addRenderableWidget(Button.builder(Component.literal("Cancel"), button -> this.onClose())
                .pos(windowX + 50, windowY + 130)
                .width(156)
                .build());
    }

    private void onCreateKingdom() {
        String kingdomName = this.kingdomNameInput.getValue().trim();
        
        if (kingdomName.isEmpty()) {
            // Optionnel : afficher un message d'erreur
            return;
        }

        // Envoyer le packet au serveur
        KingdomPackets.CHANNEL.sendToServer(new CreateKingdomPacket(this.blockPos, kingdomName));
        
        // Fermer le menu
        this.onClose();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(guiGraphics, pMouseX, pMouseY, pPartialTick);

        // Fond du menu
        guiGraphics.fill(this.windowX, this.windowY, this.windowX + WINDOW_WIDTH, this.windowY + WINDOW_HEIGHT, 0xFF8B4513);

        // Titre
        guiGraphics.drawCenteredString(this.font, Component.literal("Create Kingdom"), 
                this.windowX + WINDOW_WIDTH / 2, this.windowY + 15, 0xFFFFFF);

        // Label
        guiGraphics.drawString(this.font, Component.literal("Kingdom Name:"), 
                this.windowX + 20, this.windowY + 35, 0xFFFFFF);

        this.kingdomNameInput.render(guiGraphics, pMouseX, pMouseY, pPartialTick);

        super.render(guiGraphics, pMouseX, pMouseY, pPartialTick);
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(null);
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}
