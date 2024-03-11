package com.eternatus.modtest.client.render;

import net.minecraft.client.renderer.ShaderInstance;

import javax.annotation.Nullable;
public class MTInternalShaders {
    private static ShaderInstance renderTypeSepiaShader;

    @Nullable
    public static ShaderInstance getRenderTypeSepiaShader() {
        return renderTypeSepiaShader;
    }
    public static void setRenderTypeSepiaShader(ShaderInstance instance) {
        renderTypeSepiaShader = instance;
    }
}
