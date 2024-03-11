package com.valeriotor.beyondtheveil.item;

import com.valeriotor.beyondtheveil.Registration;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.StatType;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;


import static com.valeriotor.beyondtheveil.Registration.DRINK;

public class Drink extends Item {
    private static final int DRINK_DURATION = 32;

    public Drink(Properties pProperties) {
        super(pProperties);
    }

    @Override
//    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pLivingEntity) {
//        if(pStack.getItem() == Registration.CUP.get()) return pStack;
//        Player player = pLivingEntity instanceof Player ? (Player)pLivingEntity : null;
//        if (player instanceof ServerPlayer) {
//            CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer)player, pStack);
//        }
//        if(pLivingEntity instanceof Player){
//            player.getFoodData().eat(6,4);
//            if (!pLevel.isClientSide) {
//                MobEffectInstance mobEffectsInstance = new MobEffectInstance(MobEffects.CONFUSION);
//                if (mobEffectsInstance.getEffect().isInstantenous()) {
//                    mobEffectsInstance.getEffect().applyInstantenousEffect(player, player, pLivingEntity, mobEffectsInstance.getAmplifier(), 1.0D);
//                } else {
//                    pLivingEntity.addEffect(new MobEffectInstance(mobEffectsInstance));
//                }
//            }
//            if(pStack.getItem() == Registration.RUM.get()) doRumDebug(player);
//            if(player == null || !player.isCreative()){
//                pStack.shrink(1);
//                player.getInventory().add(new ItemStack(Registration.CUP.get()));
//            }
//            if(pLivingEntity instanceof ServerPlayer){
//                CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer) player,pStack);
//            }
//            if (player != null) {
//                player.awardStat(Stats.ITEM_USED.get(this));
//                if (!player.getAbilities().instabuild) {
//                    pStack.shrink(1);
//                }
//            }
//        }
//        pLivingEntity.gameEvent(GameEvent.DRINK);
//        return pStack;
//    }

    public ItemStack finishUsingItem(ItemStack pStack, Level pLevel, LivingEntity pEntityLiving) {
        if(pStack.is(Registration.CUP.get())) return pStack;
        if (!pLevel.isClientSide) pEntityLiving.addEffect(new MobEffectInstance(MobEffects.CONFUSION,300)); // FORGE - move up so stack.shrink does not turn stack into air
        if (pEntityLiving instanceof ServerPlayer serverplayer) {
            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, pStack);
            serverplayer.awardStat(Stats.ITEM_USED.get(this));
        }

        if (pEntityLiving instanceof Player && !((Player)pEntityLiving).getAbilities().instabuild) {
            Player player = (Player) pEntityLiving;
            pStack.shrink(1);
            player.getFoodData().eat(6,4);
        }

        return pStack.isEmpty() ? new ItemStack(Registration.CUP.get()) : pStack;
    }

    @Override
    public int getUseDuration(ItemStack pStack) {
        return 32;
    }

    @Override
    public SoundEvent getDrinkingSound() {
        return super.getDrinkingSound();
    }

    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        if(pPlayer.getItemInHand(pUsedHand).getItem() == Registration.CUP.get()) return new InteractionResultHolder<ItemStack>(InteractionResult.FAIL,pPlayer.getItemInHand(pUsedHand));
        return ItemUtils.startUsingInstantly(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        return Rarity.RARE;
    }

//    public static enum DrinkEnum {
//        RUM("rum"),
//        WINE("wine"),
//        ALE("ale"),
//        VODKA("vodka"),
//        MEAD("mead"),
//        CUP("cup");
//
//        private final String id;
//
//        private DrinkEnum(String id){
//            this.id = id;
//        }
//
//        public String getName() {
//            return this.name();
//        }
//
//        public String getId() {
//            return this.id;
//        }
//
//        public DrinkEnum byId(String id){
//            return DrinkEnum.valueOf(id);
//        }
//    }

}
