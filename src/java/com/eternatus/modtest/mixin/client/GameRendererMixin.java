package com.eternatus.modtest.mixin.client;
import com.mojang.blaze3d.vertex.PoseStack;
import com.eternatus.modtest.modtest;
import com.eternatus.modtest.networking.proxy.ClientProxy;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderBuffers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public class GameRendererMixin {
    @Shadow
    private float darkenWorldAmount;

    @Shadow
    @Final
    private RenderBuffers renderBuffers;


    @Inject(
            method = {"Lnet/minecraft/client/renderer/GameRenderer;render(FJZ)V"},
            remap = true,
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/mojang/blaze3d/platform/Lighting;setupFor3DItems()V",
                    shift = At.Shift.AFTER
            )
    )
    public void ac_render(float partialTick, long nanos, boolean idk, CallbackInfo ci) {
        ((ClientProxy) ModTest.PROXY).preScreenRender(partialTick);
    }
}
