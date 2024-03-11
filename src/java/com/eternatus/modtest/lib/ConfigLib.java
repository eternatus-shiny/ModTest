package com.eternatus.modtest.lib;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import net.minecraftforge.common.ForgeConfig;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.commons.lang3.tuple.Pair;

import java.io.File;

public class ConfigLib {
//    //TODO implement config
    public static ForgeConfigSpec.ConfigValue<Double> caveBiomeMeanWidth;
    public static  ForgeConfigSpec.ConfigValue<Integer> caveBiomeMeanSeparation;
    public static ForgeConfigSpec.ConfigValue<Double> caveBiomeWidthRandomness;
    public static ForgeConfigSpec.ConfigValue<Double> caveBiomeSpacingRandomness;
    public static ForgeConfigSpec.ConfigValue<Integer> caveMapSearchAttempts;
    public static ForgeConfigSpec.ConfigValue<Integer> caveMapSearchWidth;
    public static ForgeConfigSpec.ConfigValue<Boolean> biomeAmbientLight;
    public static ForgeConfigSpec.ConfigValue<Boolean> biomeAmbientLightColoring;
    public static ForgeConfigSpec.ConfigValue<Boolean> biomeSkyOverrides;
    public static ForgeConfigSpec.ConfigValue<Boolean> biomeSkyFogOverrides;
    public static ForgeConfigSpec.ConfigValue<Boolean> biomeWaterFogOverrides;
    public static ForgeConfigSpec.ConfigValue<Boolean> caveMapsVisibleInThirdPerson;

    public static CommentedFileConfig cfg;
    public static CommentedFileConfig cfg_client;
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigSpec.Builder BUILDER_CLIENT = new ForgeConfigSpec.Builder();
    public static ForgeConfigSpec spec;
    public static ForgeConfigSpec spec_client;
    public ConfigLib(){
        cfg = CommentedFileConfig.builder(new File(FMLPaths.CONFIGDIR.get().toString(), References.MODID+"-common.toml")).sync().autosave().build();
        cfg.load();
        Config();
        ForgeConfigSpec spec = BUILDER.build();
        ConfigLib.spec = spec;
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, spec, cfg.getFile().getName());
        spec.setConfig(cfg);

        cfg_client = CommentedFileConfig.builder(new File(FMLPaths.CONFIGDIR.get().toString(), References.MODID+"-client.toml")).sync().autosave().build();
        cfg_client.load();
        Config_client();
        ForgeConfigSpec spec_client = BUILDER_CLIENT.build();
        ConfigLib.spec_client = spec_client;
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, spec_client, cfg_client.getFile().getName());
        spec_client.setConfig(cfg_client);
    }

    private void Config_client() {
        BUILDER_CLIENT.push("");
        BUILDER_CLIENT.comment("""
                Configuration file
                """);
        BUILDER_CLIENT.pop();
        BUILDER_CLIENT.push("VISUALS");
        caveMapsVisibleInThirdPerson = BUILDER_CLIENT.comment("whether to cave maps are visible when held by players from the third-person perspective.").define("cave_maps_visible_in_third_person", true);
        biomeAmbientLight = BUILDER_CLIENT.comment("true if some biomes, such as primordial caves, have ambient light that makes the biome easier to see in.").define("biome_ambient_light", true);
        biomeAmbientLightColoring = BUILDER_CLIENT.comment("true if some biomes, such as toxic caves, apply a color to ambient light. May conflict with shaders.").define("biome_ambient_light_coloring", true);
        biomeSkyOverrides = BUILDER_CLIENT.comment("true if some biomes, such as primordial caves, have an always well-lit sky when in them. May conflict with shaders.").define("biome_sky_overrides", true);
        biomeSkyFogOverrides = BUILDER_CLIENT.comment("true if some biomes, such as toxic caves, have an thicker fog to them. May conflict with shaders.").define("biome_sky_fog_overrides", true);
        biomeWaterFogOverrides = BUILDER_CLIENT.comment("true if some biomes, such as abyssal chasm, have an thicker water fog to them. May conflict with shaders.").define("biome_sky_fog_overrides", true);
    }

    public static void Config(){
        BUILDER.push("Generation");
        caveBiomeMeanWidth = BUILDER.comment("Average radius (in blocks) of an abyssal chasm biome.").defineInRange("cave_biome_mean_width", 300D, 10.0D, Double.MAX_VALUE);
        caveBiomeMeanSeparation = BUILDER.comment("Average separation (in blocks) between each Alex's Caves cave biome.").defineInRange("cave_biome_mean_separation", 900, 50, Integer.MAX_VALUE);
        caveBiomeWidthRandomness = BUILDER.comment("How irregularly shaped Alex's Caves cave biomes can generate. 0 = all biomes nearly circular. 1 = biomes completely squiggly in shape.").defineInRange("cave_biome_width_randomness", 0.15D, 0, 1D);
        caveBiomeSpacingRandomness = BUILDER.comment("Average spacing in between abyssal Chasm biomes. 0 = all biomes nearly perfectly equidistant. 1 = biomes completely randomly spread out, sometimes next to eachother.").defineInRange("cave_biome_spacing_randomness", 0.45D, 0, 1D);
        BUILDER.pop();

        BUILDER.push("");
        BUILDER.comment("""
                Configuration file
                """);
        caveMapSearchAttempts = BUILDER.comment("How many attempts to find a biome a cave map engages in when used. Increase this to increase the search radius, or decrease it to make them faster.").defineInRange("cave_map_search_attempts", 128000, 64, Integer.MAX_VALUE);
        caveMapSearchWidth = BUILDER.comment("How wide each search attempt scans for a biome. Increasing this generally makes cave biome maps faster - at the cost of losing fidelity(may skip biomes smaller than this in block width).").defineInRange("cave_map_search_width", 64, 4, 256);
}
