package com.valeriotor.beyondtheveil.world.entity.hamletDweeller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.valeriotor.beyondtheveil.Registration;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.Util;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.StructureTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerDataHolder;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.npc.VillagerType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.saveddata.maps.MapDecoration;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HamletDweellerTrades extends VillagerTrades {

    public static final Map<ProfessionsEnum, Int2ObjectMap<HamletDweellerTrades.ItemListing[]>> TRADES = Util.make(Maps.newHashMap(), (p_35633_) -> {
        p_35633_.put(ProfessionsEnum.BARTENDER, toIntMap(ImmutableMap.of(
                1,
                new HamletDweellerTrades.ItemListing[]{
                    new HamletDweellerTrades.ItemForItems(Registration.ALE.get(), 20, 16, 2,Items.EMERALD,3),
                    new HamletDweellerTrades.ItemForItems(Registration.MEAD.get(), 1, 16, 2,Items.IRON_PICKAXE,1),
                    new HamletDweellerTrades.ItemForItems(Registration.RUM.get(), 32, 16, 2,Items.STONE,1),
                    new HamletDweellerTrades.ItemForItems(Registration.VODKA.get(), 8, 16, 2,Items.CLAY_BALL,1),
                    new HamletDweellerTrades.ItemForItems(Registration.WINE.get(), 3, 6, 16, Items.IRON_INGOT, 1)
                }, 2,
                new HamletDweellerTrades.ItemListing[]{
                        new HamletDweellerTrades.ItemForItems(Registration.ALE.get(), 20, 16, 2,Items.EMERALD,3),
                        new HamletDweellerTrades.ItemForItems(Registration.MEAD.get(), 1, 16, 2,Items.IRON_PICKAXE,1),
                        new HamletDweellerTrades.ItemForItems(Registration.RUM.get(), 32, 16, 2,Items.STONE,1),
                        new HamletDweellerTrades.ItemForItems(Registration.VODKA.get(), 8, 16, 2,Items.CLAY_BALL,1),
                        new HamletDweellerTrades.ItemForItems(Registration.WINE.get(), 3, 6, 16, Items.IRON_INGOT, 1)
                }, 3,
                new HamletDweellerTrades.ItemListing[]{
                        new HamletDweellerTrades.ItemForItems(Registration.ALE.get(), 20, 16, 2,Items.EMERALD,3),
                        new HamletDweellerTrades.ItemForItems(Registration.MEAD.get(), 1, 16, 2,Items.IRON_PICKAXE,1),
                        new HamletDweellerTrades.ItemForItems(Registration.RUM.get(), 32, 16, 2,Items.STONE,1),
                        new HamletDweellerTrades.ItemForItems(Registration.VODKA.get(), 8, 16, 2,Items.CLAY_BALL,1),
                        new HamletDweellerTrades.ItemForItems(Registration.WINE.get(), 3, 6, 16, Items.IRON_INGOT, 1)
                }, 4,
                new HamletDweellerTrades.ItemListing[]{
                        new HamletDweellerTrades.ItemForItems(Registration.ALE.get(), 20, 16, 2,Items.EMERALD,3),
                        new HamletDweellerTrades.ItemForItems(Registration.MEAD.get(), 1, 16, 2,Items.IRON_PICKAXE,1),
                        new HamletDweellerTrades.ItemForItems(Registration.RUM.get(), 32, 16, 2,Items.STONE,1),
                        new HamletDweellerTrades.ItemForItems(Registration.VODKA.get(), 8, 16, 2,Items.CLAY_BALL,1),
                        new HamletDweellerTrades.ItemForItems(Registration.WINE.get(), 3, 6, 16, Items.IRON_INGOT, 1)
                }, 5,
                new HamletDweellerTrades.ItemListing[]{
                        new HamletDweellerTrades.ItemForItems(Registration.ALE.get(), 20, 16, 2,Items.EMERALD,3),
                        new HamletDweellerTrades.ItemForItems(Registration.MEAD.get(), 1, 16, 2,Items.IRON_PICKAXE,1),
                        new HamletDweellerTrades.ItemForItems(Registration.RUM.get(), 32, 16, 2,Items.STONE,1),
                        new HamletDweellerTrades.ItemForItems(Registration.VODKA.get(), 8, 16, 2,Items.CLAY_BALL,1),
                        new HamletDweellerTrades.ItemForItems(Registration.WINE.get(), 3, 6, 16, Items.IRON_INGOT, 1)
        })));
        p_35633_.put(ProfessionsEnum.CARPENTER, toIntMap(ImmutableMap.of(
                1,
                new HamletDweellerTrades.ItemListing[]{
                        new HamletDweellerTrades.EmeraldForItems(Items.ROTTEN_FLESH, 32, 16, 2),
                        new HamletDweellerTrades.ItemsForEmeralds(Items.REDSTONE, 1, 2, 1)
                }, 2,
                new HamletDweellerTrades.ItemListing[]{
                        new HamletDweellerTrades.EmeraldForItems(Items.GOLD_INGOT, 3, 12, 10),
                        new HamletDweellerTrades.ItemsForEmeralds(Items.LAPIS_LAZULI, 1, 1, 5)
                }, 3,
                new HamletDweellerTrades.ItemListing[]{
                        new HamletDweellerTrades.EmeraldForItems(Items.RABBIT_FOOT, 2, 12, 20),
                        new HamletDweellerTrades.ItemsForEmeralds(Blocks.GLOWSTONE, 4, 1, 12, 10)
                }, 4,
                new HamletDweellerTrades.ItemListing[]{
                        new HamletDweellerTrades.EmeraldForItems(Items.SCUTE, 4, 12, 30),
                        new HamletDweellerTrades.EmeraldForItems(Items.GLASS_BOTTLE, 9, 12, 30),
                        new HamletDweellerTrades.ItemsForEmeralds(Items.ENDER_PEARL, 5, 1, 15)
                }, 5,
                new HamletDweellerTrades.ItemListing[]{
                        new HamletDweellerTrades.EmeraldForItems(Items.NETHER_WART, 22, 12, 30),
                        new HamletDweellerTrades.ItemsForEmeralds(Items.EXPERIENCE_BOTTLE, 3, 1, 30)
                })));
    });

    private static Int2ObjectMap<HamletDweellerTrades.ItemListing[]> toIntMap(ImmutableMap<Integer, HamletDweellerTrades.ItemListing[]> pMap) {
        return new Int2ObjectOpenHashMap<>(pMap);
    }

    static class ItemForItems implements HamletDweellerTrades.ItemListing {
        private final Item item;
        private final Item presio;
        private final int cost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;
        private final int cantidad_presio;

        public ItemForItems(ItemLike pItem, int pCost, int pMaxUses, int pVillagerxp, ItemLike presio, int cantidad_presio){
            this.item = pItem.asItem();
            this.cost = pCost;
            this.maxUses = pMaxUses;
            this.villagerXp = pVillagerxp;
            this.priceMultiplier = 0.05F;
            this.presio = presio.asItem();
            this.cantidad_presio = cantidad_presio;

        }

        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            ItemStack itemstack = new ItemStack(this.presio, this.cost);
            return new MerchantOffer(itemstack, new ItemStack(this.item,this.cantidad_presio), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }
    static class EmeraldForItems implements HamletDweellerTrades.ItemListing {
        private final Item item;
        private final int cost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public EmeraldForItems(ItemLike pItem, int pCost, int pMaxUses, int pVillagerXp) {
            this.item = pItem.asItem();
            this.cost = pCost;
            this.maxUses = pMaxUses;
            this.villagerXp = pVillagerXp;
            this.priceMultiplier = 0.05F;
        }

        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            ItemStack itemstack = new ItemStack(this.item, this.cost);
            return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }
    static class EmeraldsForVillagerTypeItem implements HamletDweellerTrades.ItemListing {
        private final Map<VillagerType, Item> trades;
        private final int cost;
        private final int maxUses;
        private final int villagerXp;

        public EmeraldsForVillagerTypeItem(int pCost, int pMaxUses, int pVillagerXp, Map<VillagerType, Item> pTrades) {
            Registry.VILLAGER_TYPE.stream().filter((p_35680_) -> {
                return !pTrades.containsKey(p_35680_);
            }).findAny().ifPresent((p_35677_) -> {
                throw new IllegalStateException("Missing trade for villager type: " + Registry.VILLAGER_TYPE.getKey(p_35677_));
            });
            this.trades = pTrades;
            this.cost = pCost;
            this.maxUses = pMaxUses;
            this.villagerXp = pVillagerXp;
        }

        @Nullable
        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            if (pTrader instanceof VillagerDataHolder) {
                ItemStack itemstack = new ItemStack(this.trades.get(((VillagerDataHolder)pTrader).getVillagerData().getType()), this.cost);
                return new MerchantOffer(itemstack, new ItemStack(Items.EMERALD), this.maxUses, this.villagerXp, 0.05F);
            } else {
                return null;
            }
        }
    }

    static class EnchantBookForEmeralds implements HamletDweellerTrades.ItemListing {
        private final int villagerXp;

        public EnchantBookForEmeralds(int pVillagerXp) {
            this.villagerXp = pVillagerXp;
        }

        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            List<Enchantment> list = Registry.ENCHANTMENT.stream().filter(Enchantment::isTradeable).collect(Collectors.toList());
            Enchantment enchantment = list.get(pRandom.nextInt(list.size()));
            int i = Mth.nextInt(pRandom, enchantment.getMinLevel(), enchantment.getMaxLevel());
            ItemStack itemstack = EnchantedBookItem.createForEnchantment(new EnchantmentInstance(enchantment, i));
            int j = 2 + pRandom.nextInt(5 + i * 10) + 3 * i;
            if (enchantment.isTreasureOnly()) {
                j *= 2;
            }

            if (j > 64) {
                j = 64;
            }

            return new MerchantOffer(new ItemStack(Items.EMERALD, j), new ItemStack(Items.BOOK), itemstack, 12, this.villagerXp, 0.2F);
        }
    }

    static class EnchantedItemForEmeralds implements HamletDweellerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int baseEmeraldCost;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public EnchantedItemForEmeralds(Item pItem, int pBaseEmeraldCost, int pMaxUses, int pVillagerXp) {
            this(pItem, pBaseEmeraldCost, pMaxUses, pVillagerXp, 0.05F);
        }

        public EnchantedItemForEmeralds(Item pItem, int pBaseEmeraldCost, int pMaxUses, int pVillagerXp, float pPriceMultiplier) {
            this.itemStack = new ItemStack(pItem);
            this.baseEmeraldCost = pBaseEmeraldCost;
            this.maxUses = pMaxUses;
            this.villagerXp = pVillagerXp;
            this.priceMultiplier = pPriceMultiplier;
        }

        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            int i = 5 + pRandom.nextInt(15);
            ItemStack itemstack = EnchantmentHelper.enchantItem(pRandom, new ItemStack(this.itemStack.getItem()), i, false);
            int j = Math.min(this.baseEmeraldCost + i, 64);
            ItemStack itemstack1 = new ItemStack(Items.EMERALD, j);
            return new MerchantOffer(itemstack1, itemstack, this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    public interface ItemListing {
        @Nullable
        MerchantOffer getOffer(Entity pTrader, RandomSource pRandom);
    }

    static class ItemsAndEmeraldsToItems implements HamletDweellerTrades.ItemListing {
        private final ItemStack fromItem;
        private final int fromCount;
        private final int emeraldCost;
        private final ItemStack toItem;
        private final int toCount;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsAndEmeraldsToItems(ItemLike pFromItem, int pFromCount, Item pToItem, int pToCount, int pMaxUses, int pVillagerXp) {
            this(pFromItem, pFromCount, 1, pToItem, pToCount, pMaxUses, pVillagerXp);
        }

        public ItemsAndEmeraldsToItems(ItemLike pFromItem, int pFromCount, int pEmeraldCost, Item pToItem, int pToCount, int pMaxUses, int pVillagerXp) {
            this.fromItem = new ItemStack(pFromItem);
            this.fromCount = pFromCount;
            this.emeraldCost = pEmeraldCost;
            this.toItem = new ItemStack(pToItem);
            this.toCount = pToCount;
            this.maxUses = pMaxUses;
            this.villagerXp = pVillagerXp;
            this.priceMultiplier = 0.05F;
        }

        @Nullable
        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(this.fromItem.getItem(), this.fromCount), new ItemStack(this.toItem.getItem(), this.toCount), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class ItemsForEmeralds implements HamletDweellerTrades.ItemListing {
        private final ItemStack itemStack;
        private final int emeraldCost;
        private final int numberOfItems;
        private final int maxUses;
        private final int villagerXp;
        private final float priceMultiplier;

        public ItemsForEmeralds(Block pBlock, int pEmeraldCost, int pNumberOfItems, int pMaxUses, int pVillagerXp) {
            this(new ItemStack(pBlock), pEmeraldCost, pNumberOfItems, pMaxUses, pVillagerXp);
        }

        public ItemsForEmeralds(Item pItem, int pEmeraldCost, int pNumberOfItems, int pVillagerXp) {
            this(new ItemStack(pItem), pEmeraldCost, pNumberOfItems, 12, pVillagerXp);
        }

        public ItemsForEmeralds(Item pItem, int pEmeraldCost, int pNumberOfItems, int pMaxUses, int pVillagerXp) {
            this(new ItemStack(pItem), pEmeraldCost, pNumberOfItems, pMaxUses, pVillagerXp);
        }

        public ItemsForEmeralds(ItemStack pItemStack, int pEmeraldCost, int pNumberOfItems, int pMaxUses, int pVillagerXp) {
            this(pItemStack, pEmeraldCost, pNumberOfItems, pMaxUses, pVillagerXp, 0.05F);
        }

        public ItemsForEmeralds(ItemStack pItemStack, int pEmeraldCost, int pNumberOfItems, int pMaxUses, int pVillagerXp, float pPriceMultiplier) {
            this.itemStack = pItemStack;
            this.emeraldCost = pEmeraldCost;
            this.numberOfItems = pNumberOfItems;
            this.maxUses = pMaxUses;
            this.villagerXp = pVillagerXp;
            this.priceMultiplier = pPriceMultiplier;
        }

        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(this.itemStack.getItem(), this.numberOfItems), this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class SuspiciousStewForEmerald implements HamletDweellerTrades.ItemListing {
        final MobEffect effect;
        final int duration;
        final int xp;
        private final float priceMultiplier;

        public SuspiciousStewForEmerald(MobEffect pEffect, int pDuration, int pXp) {
            this.effect = pEffect;
            this.duration = pDuration;
            this.xp = pXp;
            this.priceMultiplier = 0.05F;
        }

        @Nullable
        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            ItemStack itemstack = new ItemStack(Items.SUSPICIOUS_STEW, 1);
            SuspiciousStewItem.saveMobEffect(itemstack, this.effect, this.duration);
            return new MerchantOffer(new ItemStack(Items.EMERALD, 1), itemstack, 12, this.xp, this.priceMultiplier);
        }
    }

    static class TippedArrowForItemsAndEmeralds implements HamletDweellerTrades.ItemListing {
        /** An ItemStack that can have potion effects written to it. */
        private final ItemStack toItem;
        private final int toCount;
        private final int emeraldCost;
        private final int maxUses;
        private final int villagerXp;
        private final Item fromItem;
        private final int fromCount;
        private final float priceMultiplier;

        public TippedArrowForItemsAndEmeralds(Item pFromItem, int pFromCount, Item pToItem, int pToCount, int pEmeraldCost, int pMaxUses, int pVillagerXp) {
            this.toItem = new ItemStack(pToItem);
            this.emeraldCost = pEmeraldCost;
            this.maxUses = pMaxUses;
            this.villagerXp = pVillagerXp;
            this.fromItem = pFromItem;
            this.fromCount = pFromCount;
            this.toCount = pToCount;
            this.priceMultiplier = 0.05F;
        }

        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            ItemStack itemstack = new ItemStack(Items.EMERALD, this.emeraldCost);
            List<Potion> list = Registry.POTION.stream().filter((p_35804_) -> {
                return !p_35804_.getEffects().isEmpty() && PotionBrewing.isBrewablePotion(p_35804_);
            }).collect(Collectors.toList());
            Potion potion = list.get(pRandom.nextInt(list.size()));
            ItemStack itemstack1 = PotionUtils.setPotion(new ItemStack(this.toItem.getItem(), this.toCount), potion);
            return new MerchantOffer(itemstack, new ItemStack(this.fromItem, this.fromCount), itemstack1, this.maxUses, this.villagerXp, this.priceMultiplier);
        }
    }

    static class TreasureMapForEmeralds implements HamletDweellerTrades.ItemListing {
        private final int emeraldCost;
        private final TagKey<Structure> destination;
        private final String displayName;
        private final MapDecoration.Type destinationType;
        private final int maxUses;
        private final int villagerXp;

        public TreasureMapForEmeralds(int pEmeraldCost, TagKey<Structure> pDestination, String pDisplayName, MapDecoration.Type pDestinationType, int pMaxUses, int pVillagerXp) {
            this.emeraldCost = pEmeraldCost;
            this.destination = pDestination;
            this.displayName = pDisplayName;
            this.destinationType = pDestinationType;
            this.maxUses = pMaxUses;
            this.villagerXp = pVillagerXp;
        }

        @Nullable
        public MerchantOffer getOffer(Entity pTrader, RandomSource pRandom) {
            if (!(pTrader.level instanceof ServerLevel)) {
                return null;
            } else {
                ServerLevel serverlevel = (ServerLevel)pTrader.level;
                BlockPos blockpos = serverlevel.findNearestMapStructure(this.destination, pTrader.blockPosition(), 100, true);
                if (blockpos != null) {
                    ItemStack itemstack = MapItem.create(serverlevel, blockpos.getX(), blockpos.getZ(), (byte)2, true, true);
                    MapItem.renderBiomePreviewMap(serverlevel, itemstack);
                    MapItemSavedData.addTargetDecoration(itemstack, blockpos, "+", this.destinationType);
                    itemstack.setHoverName(Component.translatable(this.displayName));
                    return new MerchantOffer(new ItemStack(Items.EMERALD, this.emeraldCost), new ItemStack(Items.COMPASS), itemstack, this.maxUses, this.villagerXp, 0.2F);
                } else {
                    return null;
                }
            }
        }
    }
}
