package com.eternatus.modtest.world.worldgen.surface;
import com.eternatus.modtest.Registration;
import com.eternatus.modtest.util.SurfaceRulesManager;
import com.eternatus.modtest.world.biome.MTBiomeRegistry;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Noises;
import net.minecraft.world.level.levelgen.SurfaceRules;
import net.minecraft.world.level.levelgen.VerticalAnchor;

public class MTSurfaceRules {

    public static void setup() {
        SurfaceRulesManager.registerOverworldSurfaceRule(SurfaceRules.isBiome(MTBiomeRegistry.ABYSSA), createAbyssalRules());
    }
    public static SurfaceRules.RuleSource createAbyssalRules() {
        SurfaceRules.RuleSource abyssmarine = SurfaceRules.state(Registration.STONE.get().defaultBlockState());
        SurfaceRules.RuleSource deepslate = SurfaceRules.state(Blocks.DEEPSLATE.defaultBlockState());
        SurfaceRules.RuleSource stone = SurfaceRules.state(Blocks.STONE.defaultBlockState());
        SurfaceRules.ConditionSource normalDeepslateCondition = SurfaceRules.verticalGradient("deepslate", VerticalAnchor.absolute(0), VerticalAnchor.absolute(8));
        SurfaceRules.RuleSource stoneOrDeepslate = SurfaceRules.sequence(SurfaceRules.ifTrue(normalDeepslateCondition, deepslate), stone);
        return SurfaceRules.sequence(bedrock(), SurfaceRules.ifTrue(SurfaceRules.DEEP_UNDER_FLOOR, stoneOrDeepslate), SurfaceRules.ifTrue(SurfaceRules.abovePreliminarySurface(), deepslate), abyssmarine);
    }

    private static SurfaceRules.RuleSource bedrock() {
        SurfaceRules.RuleSource bedrock = SurfaceRules.state(Blocks.BEDROCK.defaultBlockState());
        SurfaceRules.ConditionSource bedrockCondition = SurfaceRules.verticalGradient("bedrock", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5));
        return SurfaceRules.ifTrue(bedrockCondition, bedrock);
    }

}
