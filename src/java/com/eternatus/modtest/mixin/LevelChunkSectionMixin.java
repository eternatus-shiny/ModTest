package com.valeriotor.beyondtheveil.mixin;

import com.valeriotor.beyondtheveil.util.ILevelChunkSectionMixin;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.PalettedContainerRO;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LevelChunkSection.class)
public class LevelChunkSectionMixin implements ILevelChunkSectionMixin {

    @Shadow private PalettedContainerRO<Holder<Biome>> biomes;

    @Override
    public void setBiome(PalettedContainerRO<Holder<Biome>> biomes) {
        this.biomes = biomes;
    }
}
