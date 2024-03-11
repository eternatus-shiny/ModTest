package com.valeriotor.beyondtheveil.world.entity.hamletDweeller;

import com.valeriotor.beyondtheveil.lib.References;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.horse.Variant;

import java.util.Arrays;
import java.util.Comparator;

public enum ProfessionsEnum {
    FISHERMAN(0, 4),
    BARTENDER(1, 2),
    MINER(2, 1),
    LHKEEPER(3, 3),
    STOCKPILER(4, 3),
    DRUNK(5, 4),
    CARPENTER(6, 2),
    SCHOLAR(7, 3);

    private static final ProfessionsEnum[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(ProfessionsEnum::getId)).toArray((p_30989_) -> {
        return new ProfessionsEnum[p_30989_];
    });
    private final int id;
    private final int talkCount;

    private ProfessionsEnum(int pId, int talkCount) {
        this.id = pId;
        this.talkCount = talkCount;
    }

    public int getId() {
        return this.id;
    }

    public int getTalkCount() {
        return this.talkCount;
    }

    public static ProfessionsEnum byId(int pId) {
        return BY_ID[pId % BY_ID.length];
    }

    public static ProfessionsEnum getbyID(int pId) {
        return BY_ID[pId];
    }

    public String getNamebyID(int id){
        return byId(id).name();
    }

    @Override
    public String toString() {
        return "ProfessionsEnum{" +
                "id=" + id +
                ", talkCount=" + talkCount + ", name=" + getNamebyID(id) +
                '}';
    }
}
