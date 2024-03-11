package com.valeriotor.beyondtheveil.util;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;

public class ItemHelper {
    public static CompoundTag checkTagCompound(ItemStack stack) {
        if(!stack.hasTag()) stack.setTag(new CompoundTag());
        return stack.getTag();
    }

    public static CompoundTag checkTagCompound(CompoundTag nbt, String key) {
        if(!nbt.contains(key)) nbt.put(key, new CompoundTag());
        return (CompoundTag) nbt.getCompound(key);
    }

    public static boolean checkBooleanTag(ItemStack stack, String key, boolean defaultValue) {
        if(!stack.hasTag()) stack.setTag(new CompoundTag());
        if(!stack.getTag().contains(key)) {
            stack.getTag().putBoolean(key, defaultValue);
            return defaultValue;
        }
        return stack.getTag().getBoolean(key);
    }

    public static int checkIntTag(ItemStack stack, String key, int defaultValue) {
        if(!stack.hasTag()) stack.setTag(new CompoundTag());
        if(!stack.getTag().contains(key)) {
            stack.getTag().putInt(key, Integer.valueOf(defaultValue));
            return defaultValue;
        }
        return stack.getTag().getInt(key);
    }

    public static long checkLongTag(ItemStack stack, String key, long defaultValue) {
        if(!ItemHelper.checkTagCompound(stack).contains(key)) {
            stack.getTag().putLong(key, Long.valueOf(defaultValue));
            return defaultValue;
        }
        return stack.getTag().getLong(key);
    }

    public static String checkStringTag(ItemStack stack, String key, String defaultValue) {
        CompoundTag nbt = ItemHelper.checkTagCompound(stack);
        if(!nbt.contains(key)) {
            nbt.putString(key, defaultValue);
            return defaultValue;
        }
        return nbt.getString(key);

    }
}
