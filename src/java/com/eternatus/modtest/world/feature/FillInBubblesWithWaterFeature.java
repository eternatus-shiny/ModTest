package com.eternatus.modtest.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class FillInBubblesWithWaterFeature  extends Feature<NoneFeatureConfiguration>{

    public FillInBubblesWithWaterFeature(Codec<NoneFeatureConfiguration> p_66836_) {
        super(p_66836_);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel worldgenlevel = context.level();
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                int k = context.origin().getX() + i;
                int l = context.origin().getZ() + j;
                int i1 = worldgenlevel.getSeaLevel() - 2;
                pos.set(k, i1, l);

            }
        }
        return true;
    }
}
