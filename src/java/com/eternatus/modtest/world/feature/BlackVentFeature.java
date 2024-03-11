package com.valeriotor.beyondtheveil.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class BlackVentFeature extends Feature<NoneFeatureConfiguration> {
    public BlackVentFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        RandomSource randomsource = context.random();
        WorldGenLevel level = context.level();
        BlockPos.MutableBlockPos ventBottom = new BlockPos.MutableBlockPos();
        ventBottom.set(context.origin());
        while (!level.getBlockState(ventBottom).getFluidState().isEmpty() && ventBottom.getY() > level.getMinBuildHeight()) {
            ventBottom.move(0, -1, 0);
        }
        if (level.getBlockState(ventBottom.below()).equals(Blocks.TUFF.defaultBlockState())) {
            drawVent(level, ventBottom.above().immutable(), randomsource);
            return true;
        }
        return false;
    }

    private static void drawVent(WorldGenLevel level, BlockPos ventBottom, RandomSource randomsource) {
        int height = randomsource.nextInt(6) + 2;
        ventBottom = ventBottom.below();
        level.setBlock(ventBottom.north(), Blocks.TUFF.defaultBlockState(), 3);
        level.setBlock(ventBottom.south(), Blocks.TUFF.defaultBlockState(), 3);
        level.setBlock(ventBottom.east(), Blocks.TUFF.defaultBlockState(), 3);
        level.setBlock(ventBottom.west(), Blocks.TUFF.defaultBlockState(), 3);
        level.setBlock(ventBottom.below(), Blocks.TUFF.defaultBlockState(), 3);
        level.setBlock(ventBottom, Blocks.LAVA.defaultBlockState(), 3);
    }
}
