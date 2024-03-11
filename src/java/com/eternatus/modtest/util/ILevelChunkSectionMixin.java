package com.valeriotor.beyondtheveil.util;

import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.PalettedContainerRO;

public interface ILevelChunkSectionMixin {
    void setBiome(PalettedContainerRO<Holder<Biome>> biomes);
}
