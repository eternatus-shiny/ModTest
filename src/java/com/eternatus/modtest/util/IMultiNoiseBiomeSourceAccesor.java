package com.eternatus.modtest.util;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
public interface IMultiNoiseBiomeSourceAccesor {
    void setLastSampledSeed(long seed);

    void setLastSampledDimension(ResourceKey<Level> dimension);
}
