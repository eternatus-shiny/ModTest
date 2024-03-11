package com.eternatus.modtest.event;


import com.mojang.logging.LogUtils;
import com.eternatus.modtest.ModTest;
import com.eternatus.modtest.Registration;
import com.eternatus.modtest.entity.FletumEntity;
import com.eternatus.modtest.entity.HamletDwellerEntity;
import com.eternatus.modtest.lib.BiomeConfig;
import com.eternatus.modtest.lib.References;
import com.eternatus.modtest.util.EventReplaceBiome;
import com.eternatus.modtest.util.ExpandedBiomeSource;
import com.eternatus.modtest.world.biome.MTBiomeRarity;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.monster.Drowned;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.slf4j.Logger;

import java.util.List;


@Mod.EventBusSubscriber(modid = References.MODID)
public class ModEvents {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public void onReplaceBiome(EventReplaceBiome event) {
        ResourceKey<Biome> biome = BiomeConfig.getBiomeForEvent(event);
        if (biome != null) {
            Holder<Biome> biomeHolder = event.getBiomeSource().getResourceKeyMap().get(biome);
            if (biomeHolder != null) {
                event.setResult(Event.Result.ALLOW);
                event.setBiomeToGenerate(biomeHolder);
            }
        }
    }
    @SubscribeEvent
    public void playerTick(TickEvent.PlayerTickEvent event) {
        if (!event.player.isCreative()) {
            if (event.player.getItemInHand(InteractionHand.MAIN_HAND).is(Registration.RESTRICTED_BIOME_LOCATORS)) {
                checkAndDestroyExploitItem(event.player, EquipmentSlot.MAINHAND);
            }
            if (event.player.getItemInHand(InteractionHand.OFF_HAND).is(Registration.RESTRICTED_BIOME_LOCATORS)) {
                checkAndDestroyExploitItem(event.player, EquipmentSlot.OFFHAND);
            }
        }
    }
    private static void checkAndDestroyExploitItem(Player player, EquipmentSlot slot) {
        ItemStack itemInHand = player.getItemBySlot(slot);
        if (itemInHand.is(Registration.RESTRICTED_BIOME_LOCATORS)) {
            CompoundTag tag = itemInHand.getTag();
            if (tag != null) {
                if (itemTagContains(tag, "BiomeKey", false) || itemTagContains(tag, "Structure", true) || itemTagContains(tag, "structurecompass:structureName", true) || itemTagContains(tag, "StructureKey", true)) {
                    itemInHand.shrink(1);
                    player.broadcastBreakEvent(slot);
                    if (!player.getLevel().isClientSide) {
                        player.displayClientMessage(Component.translatable("item.alexscaves.natures_compass_warning"), true);
                    }
                }
            }
        }
    }
    private static boolean itemTagContains(CompoundTag tag, String tagID, boolean allowUndergroundCabin) {
        if (tag.contains(tagID)) {
            String resourceLocation = tag.getString(tagID);
            if (resourceLocation.contains("alexscaves:") && (!allowUndergroundCabin || !resourceLocation.contains("underground_cabin"))) {
                return true;
            }
        }
        return false;
    }
}
