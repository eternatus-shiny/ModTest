package com.valeriotor.beyondtheveil.world.feature;

import com.mojang.serialization.Codec;
import com.valeriotor.beyondtheveil.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
public class PingPongSpongeFeature extends Feature<NoneFeatureConfiguration>{
    public PingPongSpongeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        RandomSource randomsource = context.random();
        WorldGenLevel level = context.level();
        BlockPos.MutableBlockPos trenchBottom = new BlockPos.MutableBlockPos();
        trenchBottom.set(context.origin());
        while (!level.getBlockState(trenchBottom).getFluidState().isEmpty() && trenchBottom.getY() > level.getMinBuildHeight()) {
            trenchBottom.move(0, -1, 0);
        }
        if (!level.getBlockState(trenchBottom.below()).is(Registration.DARK_SAND.get()) || context.origin().getY() - trenchBottom.getY() < 15) {
            return false;
        }
        int height = (int) Math.ceil(randomsource.nextFloat() * 3.5F);
        BlockPos genAt = trenchBottom.immutable();
        for (int i = 0; i <= height; i++) {
            BlockPos trunk = genAt.above(i);
        }
        return true;
    }

    private static boolean canReplace(BlockState state) {
        return state.getFluidState().is(FluidTags.WATER);
    }
}
