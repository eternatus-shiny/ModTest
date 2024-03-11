package com.valeriotor.beyondtheveil.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.valeriotor.beyondtheveil.container.GearBenchContainer;
import com.valeriotor.beyondtheveil.lib.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class GearBenchGui extends AbstractContainerScreen<GearBenchContainer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(References.MODID, "textures/gui/gear_bench.png");

    public GearBenchGui(GearBenchContainer pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 176;
        this.imageHeight = 192;
    }

    @Override
    protected void init() {
        super.init();
    }

    @Override
    public void render(PoseStack guiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, pMouseX, pMouseY, pPartialTick);
        this.renderTooltip(guiGraphics, pMouseX, pMouseY);
    }


    protected void renderLabels(PoseStack guiGraphics, int pMouseX, int pMouseY) {
    }

    @Override
    protected void renderBg(PoseStack guiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int relX = (width - imageWidth) / 2;
        int relY = height / 2 - 116;
        this.blit(guiGraphics, relX, relY, 0, 0, this.imageWidth, this.imageHeight);
    }
}
