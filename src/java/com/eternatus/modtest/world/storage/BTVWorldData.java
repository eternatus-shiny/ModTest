package com.valeriotor.beyondtheveil.world.storage;

import com.valeriotor.beyondtheveil.world.map.CaveBiomeMapWorldWorker;
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
public class BTVWorldData extends SavedData {
    private static Map<MinecraftServer, BTVWorldData> dataMap = new HashMap<>();

    private MinecraftServer server;
    private static final String IDENTIFIER = "beyondtheveil_world_data";
    private static final String IDENTIFIER_server = "beyondtheveil_server_data";
    private CaveBiomeMapWorldWorker lastMapWorker = null;

    private BTVWorldData(){
        super();
    }

    public BTVWorldData(MinecraftServer server){
        super();
        this.server = server;
    }


    public static BTVWorldData get(Level pLevel){
        if(pLevel instanceof ServerLevel){
            ServerLevel overworld = pLevel.getServer().getLevel(Level.OVERWORLD);
            assert overworld != null;
            DimensionDataStorage storage = overworld.getDataStorage();
            BTVWorldData data = storage.computeIfAbsent(BTVWorldData::load, BTVWorldData::new, IDENTIFIER);
            if(data != null){
                data.setDirty();
            }
            return data;
        }
        return null;
    }

    public static BTVWorldData get(MinecraftServer server){
        BTVWorldData fromMap = dataMap.get(server);
        if(fromMap == null){
            DimensionDataStorage storage = server.getLevel(Level.OVERWORLD).getDataStorage();
            BTVWorldData data = storage.computeIfAbsent((tag) -> load(server, tag), () -> new BTVWorldData(server), IDENTIFIER_server);
            if (data != null) {
                data.setDirty();
            }
            dataMap.put(server, data);
            return data;
        }
        return fromMap;
    }

    public static BTVWorldData load(CompoundTag nbt) {
        BTVWorldData data = new BTVWorldData();
        return data;
    }

    public static BTVWorldData load(MinecraftServer server, CompoundTag tag) {
        BTVWorldData data = new BTVWorldData(server);
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
