package com.eternatus.modtest.mixin;

import com.mojang.datafixers.util.Pair;
import com.eternatus.modtest.Constants;
import com.eternatus.modtest.util.EventReplaceBiome;
import com.eternatus.modtest.util.ExpandedBiomeSource;
import com.eternatus.modtest.util.IMultiNoiseBiomeSourceAccesor;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.Event;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.stream.Stream;

@Mixin(value = MultiNoiseBiomeSource.class, priority = -420)
public class MultiNoiseBiomeSourceMixin implements IMultiNoiseBiomeSourceAccesor {
    private int lastSampledX;
    private int lastSampledY;
    private int lastSampledZ;

    private long lastSampledWorldSeed;

    private ResourceKey<Level> lastSampledDimension;

    @Inject(at = @At("HEAD"),
            remap = Constants.REMAPREFS,
            method = "Lnet/minecraft/world/level/biome/MultiNoiseBiomeSource;getNoiseBiome(IIILnet/minecraft/world/level/biome/Climate$Sampler;)Lnet/minecraft/core/Holder;"
    )
    private void citadel_getNoiseBiomeCoords(int x, int y, int z, Climate.Sampler sampler, CallbackInfoReturnable<Holder<Biome>> cir) {
        lastSampledX = x;
        lastSampledY = y;
        lastSampledZ = z;
    }

    @Inject(at = @At("RETURN"),
            cancellable = true,
            remap = Constants.REMAPREFS,
            method = "Lnet/minecraft/world/level/biome/MultiNoiseBiomeSource;getNoiseBiome(Lnet/minecraft/world/level/biome/Climate$TargetPoint;)Lnet/minecraft/core/Holder;"
    )
    private void citadel_getNoiseBiome(Climate.TargetPoint targetPoint, CallbackInfoReturnable<Holder<Biome>> cir) {
        float f = Climate.unquantizeCoord(targetPoint.continentalness());
        float f1 = Climate.unquantizeCoord(targetPoint.erosion());
        float f2 = Climate.unquantizeCoord(targetPoint.temperature());
        float f3 = Climate.unquantizeCoord(targetPoint.humidity());
        float f4 = Climate.unquantizeCoord(targetPoint.weirdness());
        float f5 = Climate.unquantizeCoord(targetPoint.depth());
        EventReplaceBiome event = new EventReplaceBiome((ExpandedBiomeSource) this, cir.getReturnValue(), lastSampledX, lastSampledY, lastSampledZ, f, f1, f2, f3, f4, f5);
        MinecraftForge.EVENT_BUS.post(event);
        if(event.getResult() == Event.Result.ALLOW){
            cir.setReturnValue(event.getBiomeToGenerate());
        }

    }


    @Override
    public void setLastSampledSeed(long seed) {
        lastSampledWorldSeed = seed;
    }

    @Override
    public void setLastSampledDimension(ResourceKey<Level> dimension) {
        lastSampledDimension = dimension;
    }
}
