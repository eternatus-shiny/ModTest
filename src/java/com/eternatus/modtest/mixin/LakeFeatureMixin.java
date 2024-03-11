package com.valeriotor.beyondtheveil.mixin;

import com.valeriotor.beyondtheveil.world.biome.BTVBiomeRegistry;
import com.valeriotor.beyondtheveil.world.feature.FeaturePositionValidator;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LakeFeature.class)
public class LakeFeatureMixin {
    @Inject(
            method = {"Lnet/minecraft/world/level/levelgen/feature/LakeFeature;place(Lnet/minecraft/world/level/levelgen/feature/FeaturePlaceContext;)Z"},
            remap = true,
            cancellable = true,
            at = @At(value = "HEAD")
    )
    private void ac_place(FeaturePlaceContext context, CallbackInfoReturnable<Boolean> cir) {
        if (FeaturePositionValidator.isBiome(context, BTVBiomeRegistry.ABYSSAL_CHASM)) {
            cir.cancel();
        }
    }
}
