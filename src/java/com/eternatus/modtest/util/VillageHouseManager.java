package com.eternatus.modtest.util;

import com.mojang.datafixers.util.Pair;
import com.eternatus.modtest.ModTest;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class VillageHouseManager {
    public static final List<String> VILLAGE_REPLACEMENT_POOLS = List.of(
            "minecraft:village/plains/houses",
            "minecraft:village/desert/houses",
            "minecraft:village/savanna/houses",
            "minecraft:village/snowy/houses",
            "minecraft:village/taiga/houses");
    private static final List<Pair<String, Consumer<StructureTemplatePool>>> REGISTRY = new ArrayList<>();

    public static void register(String pool, Consumer<StructureTemplatePool> addToPool) {
        REGISTRY.add(new Pair<>(pool, addToPool));
        ModTest.LOGGER.debug("registered addition to pool: " + pool);
    }

    public static StructureTemplatePool addToPool(StructureTemplatePool pool, StructurePoolElement element, int weight) {
        if (weight > 0) {
            if (pool != null) {
                ObjectArrayList<StructurePoolElement> templates = new ObjectArrayList<>(pool.templates);
                if(!templates.contains(element)) {
                    for (int i = 0; i < weight; i++) {
                        templates.add(element);
                    }
                    List<Pair<StructurePoolElement, Integer>> rawTemplates = new ArrayList(pool.rawTemplates);
                    rawTemplates.add(new Pair<>(element, weight));
                    pool.templates = templates;
                    pool.rawTemplates = rawTemplates;
                    ModTest.LOGGER.info("Added to village structure pool");
                }
            }
        }
        return pool;
    }

    public static void addAllHouses(RegistryAccess registryAccess) {
        try {
            for(String villagePool : VILLAGE_REPLACEMENT_POOLS){
                StructureTemplatePool pool = registryAccess.registryOrThrow(Registry.TEMPLATE_POOL_REGISTRY).getOptional(new ResourceLocation(villagePool)).orElse(null);
                if(pool != null){
                    String poolName = pool.getName().toString();
                    ModTest.LOGGER.info("Add All Houses: "+poolName);
                    for (Pair<String, Consumer<StructureTemplatePool>> pair : REGISTRY) {
                        if (poolName.equals(pair.getFirst())) {
                            pair.getSecond().accept(pool);
                        }
                    }
                }
            }
        }catch (Exception e){
            ModTest.LOGGER.error("Could not add village houses!");
            e.printStackTrace();
        }
    }
}