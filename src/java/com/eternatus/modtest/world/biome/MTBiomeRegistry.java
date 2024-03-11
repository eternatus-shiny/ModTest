package com.eternatus.modtest.world.biome;

import com.eternatus.modtest.Registration;
import com.eternatus.modtest.lib.References;
import com.eternatus.modtest.util.BiomeSampler;
import com.eternatus.modtest.util.ExpandedBiomes;
import com.eternatus.modtest.world.BiomeEntryType;
import com.eternatus.modtest.world.SpawnBiomeData;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;

public class BTVBiomeRegistry {
    public static final ResourceKey<Biome> ABYSSAL = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(References.MODID, "abyssal"));
    public static final List<ResourceKey<Biome>> Modtest_BIOMES = List.of(ABYSSAL, Registration.INNSMOUTH);
    private static final Vec3 ABYSSAL_LIGHT_COLOR = new Vec3(0.5, 0.5, 1);
    private static final Vec3 DEFAULT_LIGHT_COLOR = new Vec3(1, 1, 1);

    public static void init(){
        ExpandedBiomes.addExpandedBiome(ABYSSAL, LevelStem.OVERWORLD);
    }

    public static float getBiomeAmbientLight(Holder<Biome> value) {
        return 0.0F;
    }
    public static float getBiomeFogNearness(Holder<Biome> value) {
        if (value.is(ABYSSAL)) {
            return -0.3F;
        }
        return 1.0F;
    }

    public static float getBiomeWaterFogFarness(Holder<Biome> value) {
        if (value.is(ABYSSAL)) {
            return 0.5F;
        }
        return 1.0F;
    }

    public static float getBiomeSkyOverride(Holder<Biome> value) {

        if (value.is(ABYSSAL)) {
            return 1.0F;
        }
        return 0.0F;
    }

    public static Vec3 getBiomeLightColorOverride(Holder<Biome> value) {

        if (value.is(ABYSSAL)) {
            return ABYSSAL_CHASM_LIGHT_COLOR;
        }
        return DEFAULT_LIGHT_COLOR;
    }
    public static float calculateBiomeSkyOverride(Entity player) {
        int i = Minecraft.getInstance().options.biomeBlendRadius().get();
        if (i == 0) {
            return MTBiomeRegistry.getBiomeSkyOverride(player.getLevel().getBiome(player.blockPosition()));
        } else {
            return BiomeSampler.sampleBiomesFloat(player.getLevel(), player.position(), BTVBiomeRegistry::getBiomeSkyOverride);
        }
    }
}
