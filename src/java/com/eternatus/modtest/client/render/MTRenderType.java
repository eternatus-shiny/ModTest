package com.eternatus.beyondtheveil.client.render;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.ForgeRenderTypes;
public class MTRenderType extends RenderType{
    protected static final RenderStateShard.ShaderStateShard RENDERTYPE_SEPIA_SHADER = new RenderStateShard.ShaderStateShard(MTInternalShaders::getRenderTypeSepiaShader);
    public MTRenderType(String pName, VertexFormat pFormat, VertexFormat.Mode pMode, int pBufferSize, boolean pAffectsCrumbling, boolean pSortOnUpload, Runnable pSetupState, Runnable pClearState) {
        super(pName, pFormat, pMode, pBufferSize, pAffectsCrumbling, pSortOnUpload, pSetupState, pClearState);
    }

    public static RenderType getCaveMapBackground(ResourceLocation locationIn, boolean showBackground) {
        RenderType.CompositeState rendertype$state = RenderType.CompositeState.builder()
                .setShaderState(RENDERTYPE_TEXT_SHADER)
                .setTextureState(new RenderStateShard.TextureStateShard(locationIn, false, false))
                .setTransparencyState(TRANSLUCENT_TRANSPARENCY)
                .setLightmapState(LIGHTMAP)
                .setCullState(showBackground ? NO_CULL : CULL)
                .createCompositeState(false);
        return create("cave_map_background", DefaultVertexFormat.POSITION_COLOR_TEX_LIGHTMAP, VertexFormat.Mode.QUADS, 256, false, true, rendertype$state);
    }
    public static RenderType getBookWidget(ResourceLocation locationIn, boolean sepia) {
        if(sepia){
            return create("book_widget", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, false, true, RenderType.CompositeState.builder()
                    .setShaderState(RENDERTYPE_SEPIA_SHADER)
                    .setCullState(NO_CULL)
                    .setTextureState(new RenderStateShard.TextureStateShard(locationIn, false, false))
                    .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setOverlayState(OVERLAY)
                    .createCompositeState(true));
        }else{
            return ForgeRenderTypes.getUnlitTranslucent(locationIn);
        }

    }
}
