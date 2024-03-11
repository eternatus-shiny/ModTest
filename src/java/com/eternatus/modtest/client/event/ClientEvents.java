package com.eternarus.modtest.client.event;

import com.mojang.blaze3d.systems.RenderSystem;
import com.eternarus.modtest.ModTest;
import com.eternarus.modtest.lib.ConfigLib;
import com.eternarus.modtest.networking.proxy.ClientProxy;
import com.eternarus.modtest.util.BiomeSampler;
import com.eternarus.modtest.world.biome.MTBiomeRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.FogRenderer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.FogType;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.ViewportEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class ClientEvents {
    private static float lastSampledFogNearness = 0.0F;
    private static float lastSampledWaterFogFarness = 0.0F;
    private static Vec3 lastSampledFogColor = Vec3.ZERO;
    private static Vec3 lastSampledWaterFogColor = Vec3.ZERO;

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void fogRender(ViewportEvent.RenderFog event) {
        if (event.isCanceled()) {
            //another mod has cancelled fog rendering.
            return;
        }
        //some mods incorrectly set the RenderSystem fog start and end directly, so this will have to do as a band-aid...
        float defaultFarPlaneDistance = RenderSystem.getShaderFogEnd();
        float defaultNearPlaneDistance = RenderSystem.getShaderFogStart();

        Entity player = Minecraft.getInstance().getCameraEntity();
        FluidState fluidstate = player.getLevel().getFluidState(event.getCamera().getBlockPosition());
        BlockState blockState = player.getLevel().getBlockState(event.getCamera().getBlockPosition());
        if (event.getCamera().getFluidInCamera() == FogType.WATER && ConfigLib.biomeWaterFogOverrides.get()) {
            float farness = lastSampledWaterFogFarness;
            if (farness != 1.0F) {
                event.setCanceled(true);
                event.setFarPlaneDistance(defaultFarPlaneDistance * farness);
            }
        } else if (event.getMode() == FogRenderer.FogMode.FOG_TERRAIN && ConfigLib.biomeSkyFogOverrides.get()) {
            float nearness = lastSampledFogNearness;
            boolean flag = Math.abs(nearness) - 1.0F < 0.01F;
            if (flag) {
                event.setCanceled(true);
                event.setNearPlaneDistance(defaultNearPlaneDistance * nearness);
            }
        }
    }
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void fogColor(ViewportEvent.ComputeFogColor event) {
        Entity player = Minecraft.getInstance().player;
        BlockState blockState = player.getLevel().getBlockState(event.getCamera().getBlockPosition());
        if (event.getCamera().getFluidInCamera() == FogType.NONE && ConfigLib.biomeSkyFogOverrides.get()) {
            float override = ClientProxy.acSkyOverrideAmount;
            float setR = event.getRed();
            float setG = event.getGreen();
            float setB = event.getBlue();
            boolean flag = false;
            if (override != 0.0F) {
                flag = true;
                Vec3 vec3 = lastSampledFogColor;
                setR = (float) (vec3.x - setR) * override + setR;
                setG = (float) (vec3.y - setG) * override + setG;
                setB = (float) (vec3.z - setB) * override + setB;
            }
            if (flag) {
                event.setRed(setR);
                event.setGreen(setG);
                event.setBlue(setB);
            }
        } else if (event.getCamera().getFluidInCamera() == FogType.WATER && ConfigLib.biomeWaterFogOverrides.get()) {
            int i = Minecraft.getInstance().options.biomeBlendRadius().get();
            float override = ClientProxy.acSkyOverrideAmount;
            if (override != 0) {
                Vec3 vec3 = lastSampledWaterFogColor;
                event.setRed((float) (event.getRed() + (vec3.x - event.getRed()) * override));
                event.setGreen((float) (event.getGreen() + (vec3.y - event.getGreen()) * override));
                event.setBlue((float) (event.getBlue() + (vec3.z - event.getBlue()) * override));
            }
        }
    }
    private static float calculateBiomeAmbientLight(Entity player) {
        int i = Minecraft.getInstance().options.biomeBlendRadius().get();
        if (i == 0) {
            return MTBiomeRegistry.getBiomeAmbientLight(player.getLevel().getBiome(player.blockPosition()));
        } else {
            return BiomeSampler.sampleBiomesFloat(player.getLevel(), player.position(), MTBiomeRegistry::getBiomeAmbientLight);
        }
    }

    private static Vec3 calculateBiomeLightColor(Entity player) {
        int i = Minecraft.getInstance().options.biomeBlendRadius().get();
        if (i == 0) {
            return MTBiomeRegistry.getBiomeLightColorOverride(player.getLevel().getBiome(player.blockPosition()));
        } else {
            return BiomeSampler.sampleBiomesVec3(player.getLevel(), player.position(), MTBiomeRegistry::getBiomeLightColorOverride);
        }
    }

    private static float calculateBiomeFogNearness(Entity player) {
        int i = Minecraft.getInstance().options.biomeBlendRadius().get();
        float nearness;
        if (i == 0) {
            nearness = MTBiomeRegistry.getBiomeFogNearness(player.getLevel().getBiome(player.blockPosition()));
        } else {
            nearness = BiomeSampler.sampleBiomesFloat(player.getLevel(), player.position(), MTBiomeRegistry::getBiomeFogNearness);
        }
        return nearness;
    }

    private static float calculateBiomeWaterFogFarness(Entity player) {
        int i = Minecraft.getInstance().options.biomeBlendRadius().get();
        float farness;
        if (i == 0) {
            farness = MTBiomeRegistry.getBiomeWaterFogFarness(player.getLevel().getBiome(player.blockPosition()));
        } else {
            farness = BiomeSampler.sampleBiomesFloat(player.getLevel(), player.position(), MTBiomeRegistry::getBiomeWaterFogFarness);
        }
        return farness;
    }

    private static Vec3 calculateBiomeFogColor(Entity player) {
        int i = Minecraft.getInstance().options.biomeBlendRadius().get();
        Vec3 vec3;
        if (i == 0) {
            vec3 = ((ClientLevel) player.getLevel()).effects().getBrightnessDependentFogColor(Vec3.fromRGB24(player.getLevel().getBiomeManager().getNoiseBiomeAtPosition(player.blockPosition()).value().getFogColor()), 1.0F);
        } else {
            vec3 = ((ClientLevel) player.getLevel()).effects().getBrightnessDependentFogColor(BiomeSampler.sampleBiomesVec3(player.getLevel(), player.position(), biomeHolder -> Vec3.fromRGB24(biomeHolder.value().getFogColor())), 1.0F);
        }
        return vec3;
    }

    private Vec3 calculateBiomeWaterFogColor(Entity player) {
        int i = Minecraft.getInstance().options.biomeBlendRadius().get();
        Vec3 vec3;
        if (i == 0) {
            vec3 = ((ClientLevel) player.getLevel()).effects().getBrightnessDependentFogColor(Vec3.fromRGB24(player.getLevel().getBiomeManager().getNoiseBiomeAtPosition(player.blockPosition()).value().getWaterFogColor()), 1.0F);
        } else {
            vec3 = ((ClientLevel) player.getLevel()).effects().getBrightnessDependentFogColor(BiomeSampler.sampleBiomesVec3(player.getLevel(), player.position(), biomeHolder -> Vec3.fromRGB24(biomeHolder.value().getWaterFogColor())), 1.0F);
        }
        return vec3;
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            Entity cameraEntity = Minecraft.getInstance().cameraEntity;
            float partialTicks = ModTest.PROXY.getPartialTicks();
            if (ClientProxy.shaderLoadAttemptCooldown > 0) {
                ClientProxy.shaderLoadAttemptCooldown--;
            }
            if (cameraEntity != null) {
                ClientProxy.acSkyOverrideAmount = MTBiomeRegistry.calculateBiomeSkyOverride(cameraEntity);
                if (ClientProxy.acSkyOverrideAmount > 0) {
                    ClientProxy.acSkyOverrideColor = BiomeSampler.sampleBiomesVec3(Minecraft.getInstance().level, Minecraft.getInstance().cameraEntity.position(), biomeHolder -> Vec3.fromRGB24(biomeHolder.value().getSkyColor()));
                }
                ClientProxy.lastBiomeLightColorPrev = ClientProxy.lastBiomeLightColor;
                ClientProxy.lastBiomeLightColor = calculateBiomeLightColor(cameraEntity);
                ClientProxy.lastBiomeAmbientLightAmountPrev = ClientProxy.lastBiomeAmbientLightAmount;
                ClientProxy.lastBiomeAmbientLightAmount = calculateBiomeAmbientLight(cameraEntity);
                lastSampledFogNearness = calculateBiomeFogNearness(cameraEntity);
                lastSampledWaterFogFarness = calculateBiomeWaterFogFarness(cameraEntity);
                if (cameraEntity.level instanceof ClientLevel) { //fixes crash with beholder
                    lastSampledFogColor = calculateBiomeFogColor(cameraEntity);
                    lastSampledWaterFogColor = calculateBiomeWaterFogColor(cameraEntity);
                }
            }
        }
    }
}
