package com.valeriotor.beyondtheveil.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
public interface UpdateStackTags {
    default void updateTagFromServer(Entity holder, ItemStack stack, CompoundTag tag){
        stack.setTag(tag);
    }
}
