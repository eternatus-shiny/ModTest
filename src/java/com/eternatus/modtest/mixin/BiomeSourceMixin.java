package com.valeriotor.beyondtheveil.mixin;

import com.google.common.base.Suppliers;
import com.google.common.collect.ImmutableSet;
import com.valeriotor.beyondtheveil.util.ExpandedBiomeSource;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Supplier;

@Mixin(BiomeSource.class)
public class BiomeSourceMixin implements ExpandedBiomeSource {

    @Shadow
    public Supplier<Set<Holder<Biome>>> lazyPossibleBiomes;

    private boolean expanded;
    private Map<ResourceKey<Biome>, Holder<Biome>> map = new HashMap<>();

    @Override
    public void setResourceKeyMap(Map<ResourceKey<Biome>, Holder<Biome>> map) {
        this.map = map;
    }

    @Override
    public Map<ResourceKey<Biome>, Holder<Biome>> getResourceKeyMap() {
        return map;
    }

    @Override
    public void expandBiomesWith(Set<Holder<Biome>> newGenBiomes) {
        if(!expanded){
            ImmutableSet.Builder<Holder<Biome>> builder = ImmutableSet.builder();
            builder.addAll(this.lazyPossibleBiomes.get());
            builder.addAll(newGenBiomes);
            lazyPossibleBiomes = Suppliers.memoize(builder::build);
            expanded = true;
        }
    }
}
