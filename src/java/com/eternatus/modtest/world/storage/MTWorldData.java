package com.eternatus.modtest.world.storage;

import com.eternatus.modtest.world.map.CaveBiomeMapWorldWorker;
import it.unimi.dsi.fastutil.objects.ObjectArraySet;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.saveddata.SavedData;
import net.minecraft.world.level.storage.DimensionDataStorage;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.WorldWorkerManager;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;
public class MTWorldData extends SavedData {
    private static Map<MinecraftServer, MTWorldData> dataMap = new HashMap<>();

    private MinecraftServer server;
    private static final String IDENTIFIER = "modtest_world_data";
    private static final String IDENTIFIER_server = "modtest_server_data";
    private CaveBiomeMapWorldWorker lastMapWorker = null;

    private MTWorldData(){
        super();
    }

    public MTWorldData(MinecraftServer server){
        super();
        this.server = server;
    }


    public static MTWorldData get(Level pLevel){
        if(pLevel instanceof ServerLevel){
            ServerLevel overworld = pLevel.getServer().getLevel(Level.OVERWORLD);
            assert overworld != null;
            DimensionDataStorage storage = overworld.getDataStorage();
            MTWorldData data = storage.computeIfAbsent(MTWorldData::load, MTWorldData::new, IDENTIFIER);
            if(data != null){
                data.setDirty();
            }
            return data;
        }
        return null;
    }

    public static MTWorldData get(MinecraftServer server){
        MTWorldData fromMap = dataMap.get(server);
        if(fromMap == null){
            DimensionDataStorage storage = server.getLevel(Level.OVERWORLD).getDataStorage();
            MTWorldData data = storage.computeIfAbsent((tag) -> load(server, tag), () -> new MTWorldData(server), IDENTIFIER_server);
            if (data != null) {
                data.setDirty();
            }
            dataMap.put(server, data);
            return data;
        }
        return fromMap;
    }

    public static MTWorldData load(CompoundTag nbt) {
        MTWorldData data = new MTWorldData();
        return data;
    }

    public static MTWorldData load(MinecraftServer server, CompoundTag tag) {
        MTWorldData data = new MTWorldData(server);
        return data;
    }

    @Override
    public CompoundTag save(CompoundTag compound) {
        return compound;
    }
    public void fillOutCaveMap(UUID uuid, ItemStack map, ServerLevel serverLevel, BlockPos center, Player player){
        if(lastMapWorker != null){
            lastMapWorker.onWorkComplete(lastMapWorker.getLastFoundBiome());
        }
        lastMapWorker = new CaveBiomeMapWorldWorker(map, serverLevel, center, player, uuid);
        WorldWorkerManager.addWorker(lastMapWorker);
    }

    public boolean isCaveMapTicking(){
        return lastMapWorker != null && lastMapWorker.hasWork();
    }

}
