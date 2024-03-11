package com.eternatus.modtest.world.carver;

import com.eternatus.modtest.lib.References;
import net.minecraft.world.level.levelgen.carver.CaveCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BTVCarverRegistry {

    public static final DeferredRegister<WorldCarver<?>> DEF_REG = DeferredRegister.create(ForgeRegistries.WORLD_CARVERS, References.MODID);

    public static RegistryObject<WaterBubbleCarver> WATER_BUBBLE_CARVER = DEF_REG.register("water_bubble_carver", () -> new WaterBubbleCarver(CaveCarverConfiguration.CODEC));

}
