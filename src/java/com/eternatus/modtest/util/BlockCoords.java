package com.valeriotor.beyondtheveil.util;

import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;

public class BlockCoords {
    private final Block block;
    private final byte[][] coords;

    public BlockCoords(Block block, byte[][] coords) {
        this.block = block;
        this.coords = coords;
    }

    public BlockCoords(Entry<Block, byte[][]> entry) {
        this.block = entry.getKey();
        this.coords = entry.getValue();
    }

    //TODO BTVChunkCache, getStateFromMeta?
//    public void generate(Level w, BlockPos center) {
//        for(byte[] xyzm : this.coords) {
//            BlockPos pos = center.offset(xyzm[0], xyzm[1], xyzm[2]);
//            w.setBlock(pos, block.getStateForPlacement(xyzm[3]), 2);
//        }
//    }
//
//    public void fillCache(BlockPos center, Map<Long, BTVChunkCache> chunks, Map<Long, Boolean> usedChunks) {
//        for(byte[] xyzm : this.coords) {
//            BlockPos pos = center.offset(xyzm[0], xyzm[1], xyzm[2]);
//            int chunkX = pos.getX() >> 4;
//            int chunkZ = pos.getZ() >> 4;
//            long cPos = ChunkPos.asLong(chunkX, chunkZ);
//            if(usedChunks != null && !usedChunks.containsKey(cPos))
//                usedChunks.put(cPos, false);
//            if(usedChunks == null || !usedChunks.get(cPos)) {
//                BTVChunkCache cache = chunks.get(cPos);
//                if(cache == null) {
//                    cache = new BTVChunkCache();
//                    chunks.put(cPos, cache);
//                }
//                cache.setBlockState(pos, block.getStateFromMeta(xyzm[3]));
//            }
//        }
//    }
//    public void fillCache(BlockPos center, BTVChunkCacheStore chunks, Map<Long, Boolean> usedChunks) {
//        fillCache(center, chunks.getChunkPosCacheMap(), usedChunks);
//    }

    public Block getBlock() {
        return block;
    }
}
