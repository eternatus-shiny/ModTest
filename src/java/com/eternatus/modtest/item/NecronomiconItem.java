package com.valeriotor.beyondtheveil.item;

import com.valeriotor.beyondtheveil.Registration;
import net.minecraft.world.item.Item;

public class NecronomiconItem extends Item {
    public NecronomiconItem(){
        super(new Item.Properties().stacksTo(1).tab(Registration.TAB));
    }
}
