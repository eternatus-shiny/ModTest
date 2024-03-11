//package com.valeriotor.beyondtheveil.world.feature;
//
//import com.google.common.collect.Sets;
//import com.mojang.serialization.Codec;
//import com.valeriotor.beyondtheveil.Registration;
//import com.valeriotor.beyondtheveil.world.feature.config.UndergroundRuinsFeatureConfiguration;
//import net.minecraft.core.BlockPos;
//import net.minecraft.util.RandomSource;
//import net.minecraft.world.level.WorldGenLevel;
//import net.minecraft.world.level.block.Blocks;
//import net.minecraft.world.level.levelgen.structure.BoundingBox;
//import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
//
//import java.util.Set;
//
//public class DeepOneRuinsFeature extends UndergroundRuinsFeature {
//
//    public DeepOneRuinsFeature(Codec<UndergroundRuinsFeatureConfiguration> codec) {
//        super(codec);
//    }
//
//    @Override
//    protected boolean canGenerateAt(WorldGenLevel level, BlockPos blockpos) {
//        return level.getBlockState(blockpos).is(Registration.DARK_SAND.get());
//    }
//
//    @Override
//    public void processMarker(String marker, WorldGenLevel level, BlockPos pos, RandomSource randomsource) {
//        if (marker.equals("submarine")) {
//            level.setBlock(pos, Blocks.CAVE_AIR.defaultBlockState(), 3);
//        }
//        if (marker.equals("submarine_damaged")) {
//            level.setBlock(pos, Blocks.WATER.defaultBlockState(), 3);
//        }
//    }
//
//    public void processBoundingBox(WorldGenLevel level, BoundingBox boundingBox, RandomSource randomsource) {
//        BoundingBox box = new BoundingBox(boundingBox.minX(), boundingBox.minY(), boundingBox.minZ(), boundingBox.maxX(), boundingBox.minY() + 1, boundingBox.maxZ());
//        Set<BlockPos> supportsNeededBelow = Sets.newHashSet();
//        BlockPos.betweenClosedStream(box).forEach((pos) -> {
//            if (!level.getBlockState(pos).is(Registration.DARK_SAND.get()) && !level.getBlockState(pos).is(Blocks.WATER)) {
//                if (level.getBlockState(pos.below()).is(Blocks.WATER) || !level.getBlockState(pos.below()).isCollisionShapeFullBlock(level, pos.below())) {
//                    supportsNeededBelow.add(pos.immutable());
//                }
//            }
//        });
//        BlockPos.MutableBlockPos grounded = new BlockPos.MutableBlockPos();
//        for (BlockPos pos : supportsNeededBelow) {
//            grounded.set(pos.getX(), pos.getY() - 1, pos.getZ());
//            while ((level.getBlockState(grounded).is(Blocks.WATER) || !level.getBlockState(grounded).isCollisionShapeFullBlock(level, grounded)) && grounded.getY() > level.getMinBuildHeight()) {
//                level.setBlock(grounded, Registration.WORN_BRICKS.get().defaultBlockState(), 3);
//                grounded.move(0, -1, 0);
//            }
//        }
//    }
//
//    @Override
//    public StructurePlaceSettings modifyPlacementSettings(StructurePlaceSettings structureplacesettings) {
//        return structureplacesettings.setKeepLiquids(false);
//    }
//}
