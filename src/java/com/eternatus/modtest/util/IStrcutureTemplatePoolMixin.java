package com.valeriotor.beyondtheveil.util;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;

import java.util.List;

public interface IStrcutureTemplatePoolMixin {
    ObjectArrayList<StructurePoolElement> getTemplate();
    void setTemplate(ObjectArrayList<StructurePoolElement> templates);
    List<Pair<StructurePoolElement, Integer>> getrawTemplates();
    void setrawTemplates(List<Pair<StructurePoolElement, Integer>> rawTemplates);
}
