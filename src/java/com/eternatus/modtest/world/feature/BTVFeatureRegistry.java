package com.valeriotor.beyondtheveil.world.feature;

import com.valeriotor.beyondtheveil.lib.References;
import com.valeriotor.beyondtheveil.world.feature.config.FillBiomeAboveConfiguration;
import com.valeriotor.beyondtheveil.world.feature.config.UndergroundRuinsFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
public class BTVFeatureRegistry {
    public static final DeferredRegister<Feature<?>> DEF_REG = DeferredRegister.create(ForgeRegistries.FEATURES, References.MODID);

//    public static final RegistryObject<Feature<UndergroundRuinsFeatureConfiguration>> UNDERGROUND_RUINS = DEF_REG.register("underground_ruins", () -> new UndergroundRuinsFeature(UndergroundRuinsFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<FillBiomeAboveConfiguration>> FILL_BIOME_ABOVE = DEF_REG.register("fill_biome_above", () -> new FillBiomeAboveFeature(FillBiomeAboveConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> FILL_IN_BUBBLES_WITH_WATER = DEF_REG.register("fill_in_bubbles_with_water", () -> new FillInBubblesWithWaterFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> BLACK_VENT = DEF_REG.register("black_vent", () -> new BlackVentFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> PING_PONG_SPONGE = DEF_REG.register("ping_pong_sponge", () -> new PingPongSpongeFeature(NoneFeatureConfiguration.CODEC));
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> ABYSSAL_BOULDER = DEF_REG.register("abyssal_boulder", () -> new AbyssalBoulderFeature(NoneFeatureConfiguration.CODEC));
}
