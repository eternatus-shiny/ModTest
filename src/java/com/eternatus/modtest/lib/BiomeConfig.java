package com.eternatus.modtest.lib;

import com.google.common.reflect.TypeToken;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.eternatus.modtest.ModTest;
import com.eternatus.modtest.util.BiomeGenerationNoiseCondition;
import com.eternatus.modtest.util.EventReplaceBiome;
import com.eternatus.modtest.util.VoronoiGenerator;
import com.eternatus.modtest.world.biome.MTBiomeRarity;
import com.eternatus.modtest.world.biome.MTBiomeRegistry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.commons.io.FileUtils;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BiomeConfig {
    public static final Gson GSON = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).setPrettyPrinting().create();

    private static final String OVERWORLD = "minecraft:overworld";

    private static final BiomeGenerationNoiseCondition ABYSSAL_CONDITION = new BiomeGenerationNoiseCondition.Builder()
            .dimensions(OVERWORLD).distanceFromSpawn(400).alexscavesRarityOffset(3).continentalness(-0.95F, -0.65F).temperature(-1.0F, 0.5F).depth(0.2F, 1.5F).build();

    private static LinkedHashMap<ResourceKey<Biome>, BiomeGenerationNoiseCondition> biomes = new LinkedHashMap<>();

    public static void reloadConfig() {
        biomes.put(MTBiomeRegistry.ABYSSAL, getConfigData("abyssal", ABYSSAL_CONDITION));
    }
    public static ResourceKey<Biome> getBiomeForEvent(EventReplaceBiome event) {
        VoronoiGenerator.VoronoiInfo voronoiInfo = MTBiomeRarity.getRareBiomeInfoForQuad(event.getWorldSeed(), event.getX(), event.getZ());
        if(voronoiInfo != null){
            int foundRarityOffset = MTBiomeRarity.getRareBiomeOffsetId(voronoiInfo);
            for (Map.Entry<ResourceKey<Biome>, BiomeGenerationNoiseCondition> condition : biomes.entrySet()) {
                if (foundRarityOffset == condition.getValue().getRarityOffset() && condition.getValue().test(event, voronoiInfo)) {
                    return condition.getKey();
                }
            }
        }
        return null;
    }

    public static int getBiomeCount() {
        return biomes.size();
    }

    public static boolean isBiomeDisabledCompletely(ResourceKey<Biome> biome){
        BiomeGenerationNoiseCondition noiseCondition = biomes.get(biome);
        return noiseCondition != null && noiseCondition.isDisabledCompletely();
    }

    private static <T> T getOrCreateConfigFile(File configDir, String configName, T defaults, Type type, Predicate<T> isInvalid) {
        File configFile = new File(configDir, configName + ".json");
        if (!configFile.exists()) {
            try {
                FileUtils.write(configFile, GSON.toJson(defaults));
            } catch (IOException e) {
                modtest.LOGGER.error("Biome Generation Config: Could not write " + configFile, e);
            }
        }
        try {
            T found = GSON.fromJson(FileUtils.readFileToString(configFile), type);
            if (isInvalid.test(found)) {
                modtest.LOGGER.warn("Old Biome Generation Config format found for " + configName + ", replacing with new one.");
                try {
                    FileUtils.write(configFile, GSON.toJson(defaults));
                } catch (IOException e) {
                    modtest.LOGGER.error("Biome Generation Config: Could not write " + configFile, e);
                }
            } else {
                return found;
            }
        } catch (Exception e) {
            modtest.LOGGER.error("Biome Generation Config: Could not load " + configFile, e);
        }

        return defaults;
    }

    private static File getConfigDirectory() {
        Path configPath = FMLPaths.CONFIGDIR.get();
        Path jsonPath = Paths.get(configPath.toAbsolutePath().toString(), "modtest_biome_generation");
        return jsonPath.toFile();
    }

    private static BiomeGenerationNoiseCondition getConfigData(String fileName, BiomeGenerationNoiseCondition defaultConfigData) {
        BiomeGenerationNoiseCondition configData = getOrCreateConfigFile(getConfigDirectory(), fileName, defaultConfigData, new TypeToken<BiomeGenerationNoiseCondition>() {
        }.getType(), BiomeGenerationNoiseCondition::isInvalid);
        return configData;
    }
}
