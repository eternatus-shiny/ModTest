package com.eternatus.modtest.mixin;

import com.eternatus.modtest.Constants;
import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.NoiseBasedChunkGenerator;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.SurfaceRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import com.eternatus.modtest.util.SurfaceRulesManager;

import java.util.HashMap;
import java.util.function.Function;

@Mixin(NoiseBasedChunkGenerator.class)
public class NoiseBasedChunkGeneratorMixin {
    private boolean mergedSurfaceRules = false;
    private SurfaceRules.RuleSource mergedSurfaceRule = null;
    private final Function<SurfaceRules.RuleSource, SurfaceRules.RuleSource> rulesToMerge = Util.memoize(SurfaceRulesManager::mergeOverworldRules);
    private final HashMap<NoiseGeneratorSettings, SurfaceRules.RuleSource> mergedRulesMap = new HashMap<>();

    @Redirect(
            method = {"Lnet/minecraft/world/level/levelgen/NoiseBasedChunkGenerator;buildSurface(Lnet/minecraft/world/level/chunk/ChunkAccess;Lnet/minecraft/world/level/levelgen/WorldGenerationContext;Lnet/minecraft/world/level/levelgen/RandomState;Lnet/minecraft/world/level/StructureManager;Lnet/minecraft/world/level/biome/BiomeManager;Lnet/minecraft/core/Registry;Lnet/minecraft/world/level/levelgen/blending/Blender;)V"},
            remap = Constants.REMAPREFS,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/NoiseGeneratorSettings;surfaceRule()Lnet/minecraft/world/level/levelgen/SurfaceRules$RuleSource;")
    )
    private SurfaceRules.RuleSource citadel_buildSurface_surfaceRuleRedirect(NoiseGeneratorSettings noiseGeneratorSettings) {
        return getMergedRulesFor(noiseGeneratorSettings.surfaceRule());
    }

    @Redirect(
            method = {"Lnet/minecraft/world/level/levelgen/NoiseBasedChunkGenerator;applyCarvers(Lnet/minecraft/server/level/WorldGenRegion;JLnet/minecraft/world/level/levelgen/RandomState;Lnet/minecraft/world/level/biome/BiomeManager;Lnet/minecraft/world/level/StructureManager;Lnet/minecraft/world/level/chunk/ChunkAccess;Lnet/minecraft/world/level/levelgen/GenerationStep$Carving;)V"},
            remap = Constants.REMAPREFS,
            at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/NoiseGeneratorSettings;surfaceRule()Lnet/minecraft/world/level/levelgen/SurfaceRules$RuleSource;")
    )
    private SurfaceRules.RuleSource citadel_applyCarvers_surfaceRuleRedirect(NoiseGeneratorSettings noiseGeneratorSettings) {
        return getMergedRulesFor(noiseGeneratorSettings.surfaceRule());
    }

    private SurfaceRules.RuleSource getMergedRulesFor(SurfaceRules.RuleSource rules){
        if (!mergedSurfaceRules) {
            mergedSurfaceRules = true;
            mergedSurfaceRule = SurfaceRulesManager.mergeOverworldRules(rules);
        }
        return mergedSurfaceRule == null ? rules : mergedSurfaceRule;
    }
}
