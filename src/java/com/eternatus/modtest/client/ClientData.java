package com.eternatus.modtest.client;

import com.eternatus.modtest.lib.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ClientPlayerNetworkEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Mod.EventBusSubscriber(modid = References.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientData {

    private static ClientData instance = new ClientData();

    public static ClientData getInstance() {
        return instance;
    }

    public static void newInstance() {
        instance = new ClientData();
    }

    public HitResult getClientHitResult() {
        return Minecraft.getInstance().hitResult;
    }
}

