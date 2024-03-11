package com.valeriotor.beyondtheveil.world.entity.hamletDweeller;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraftforge.eventbus.api.Event;

import java.util.List;

public class HamletDweellerTradesEvent extends Event{
        protected Int2ObjectMap<List<HamletDweellerTrades.ItemListing>> trades;
        protected ProfessionsEnum type;

        public HamletDweellerTradesEvent(Int2ObjectMap<List<HamletDweellerTrades.ItemListing>> trades, ProfessionsEnum type)
        {
            this.trades = trades;
            this.type = type;
        }

        public Int2ObjectMap<List<HamletDweellerTrades.ItemListing>> getTrades()
        {
            return trades;
        }

        public ProfessionsEnum getType()
        {
            return type;
        }

}
