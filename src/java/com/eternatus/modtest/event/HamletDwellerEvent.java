package com.valeriotor.beyondtheveil.event;


import com.valeriotor.beyondtheveil.entity.HamletDwellerEntity;
import com.valeriotor.beyondtheveil.world.entity.hamletDweeller.ProfessionsEnum;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class HamletDwellerEvent {
    @SubscribeEvent
    public void onVillagerClick(PlayerInteractEvent.EntityInteract e) {
        HamletDwellerEvent.onHamletClick(e.getEntity(), e.getLevel(), e.getHand(), e.getTarget(), null);
    }
    public static InteractionResult onHamletClick(Player player, Level world, InteractionHand hand, Entity target, EntityHitResult hitResult){
        if(target instanceof HamletDwellerEntity){
            HamletDwellerEntity hamletDweller = (HamletDwellerEntity) target;
            if(hamletDweller.getProfession() == ProfessionsEnum.BARTENDER || hamletDweller.getProfession() == ProfessionsEnum.CARPENTER){
                for (MerchantOffer offer : hamletDweller.getOffers()) {
                    resetMerchantOffer(offer);
                }
            }
        }
        return InteractionResult.PASS;
    }

    public static void resetMerchantOffer(MerchantOffer offer) {
        offer.resetUses();
    }
}
