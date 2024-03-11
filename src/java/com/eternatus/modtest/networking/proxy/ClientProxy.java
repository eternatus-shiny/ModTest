package com.eternatus.modtest.networking.proxy;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.eternatus.modtest.ModTest;
import com.eternatus.modtest.client.event.ClientEvents;
import com.eternatus.modtest.client.render.item.BTVItemRenderProperties;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.client.CameraType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.FallingBlockRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.*;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.*;
public class ClientProxy extends CommonProxy{
    private final BTVItemRenderProperties isterProperties = new BTVItemRenderProperties();
    public static float acSkyOverrideAmount;
    public static Vec3 acSkyOverrideColor = Vec3.ZERO;
    public static Vec3 lastBiomeLightColorPrev = Vec3.ZERO;
    public static float lastBiomeAmbientLightAmountPrev = 0;
    public static Vec3 lastBiomeLightColor = Vec3.ZERO;
    public static float lastBiomeAmbientLightAmount = 0;
    public static int shaderLoadAttemptCooldown = 0;
    public void commonInit() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::onItemColors);
    }

    public void clientInit(){
        MinecraftForge.EVENT_BUS.register(new ClientEvents());
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    }
    public void onItemColors(RegisterColorHandlersEvent.Item event) {
        BeyondTheVeil.LOGGER.info("loaded in item colorizer");
    }
    public Player getClientSidePlayer() {
        return Minecraft.getInstance().player;
    }
    @Override
    public Object getISTERProperties() {
        return isterProperties;
    }
    public static Vec3 processSkyColor(Vec3 colorIn, float partialTick){
        return colorIn;
    }
    public void preScreenRender(float partialTick) {
        float screenEffectIntensity = Minecraft.getInstance().options.screenEffectScale().get().floatValue();
    }
    public float getPartialTicks() {
        return Minecraft.getInstance().getPartialTick();
    }
}
