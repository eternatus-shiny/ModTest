package com.valeriotor.beyondtheveil.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;

public class WorldHelper {
    public static BlockPos findClosestBiomeOfType(Biome biome, BlockPos pos, Level w, int range) {
        BlockPos pos1;
        for(int i = 0; i*50 < range; i++) {
            for(int j = -i; j <= i; j++) {
                int addendum = (j == -i || j == i) ? 1 : 2*i;
                for(int k = -i; k <= i; k += addendum) {
                    pos1 = pos.offset(j*50, 0, k*50);
                    if(w.getBiome(pos1).get() == biome) return pos1;
                }
            }
        }
        return null;
    }
}
