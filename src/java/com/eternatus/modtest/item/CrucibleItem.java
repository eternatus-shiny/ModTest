package com.valeriotor.beyondtheveil.item;

import com.valeriotor.beyondtheveil.lib.ConfigLib;
import net.minecraft.network.chat.Component;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class CrucibleItem extends SwordItem  {
    private String descriptionId;

    public CrucibleItem(Properties pProperties){
        super(Tier.Crucible, ConfigLib.crucibleCooldown.get()*20+1,ConfigLib.crucibleCooldown.get(),pProperties);
//        super(Tiers.IRON, 120*20+1,120,pProperties);
    }

    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        pStack.setDamageValue(ConfigLib.crucibleCooldown.get()*20);
//        pStack.setDamageValue(120*20);
        return super.hurtEnemy(pStack,pTarget,pAttacker);
    }
    @Override
    public boolean isRepairable(ItemStack stack) {
        return false;
    }

    @Override
    public Rarity getRarity(ItemStack pStack) {
        return Rarity.EPIC;
    }

    @Override
    public float getDamage() {
        return ConfigLib.crucibleDamage.get()-1;
//        return 100-1;
    }
    @Override
    public int getMaxDamage(ItemStack stack) {
        return ConfigLib.crucibleDamage.get()-1;
    }


    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("lore.crucible"));
    }

    private enum Tier implements net.minecraft.world.item.Tier {
        Crucible(5,250,ConfigLib.crucibleCooldown.get().floatValue(),0,0, () -> {
            return null;
        });
        private final int level;
        private final int uses;
        private final float speed;
        private final float damage;
        private final int enchantmentValue;
        private final LazyLoadedValue<Ingredient> repairIngredient;

        private Tier(int pLevel, int pUses, float pSpeed, float pDamage, int pEnchantmentValue, Supplier<Ingredient> pRepairIngredient) {
            this.level = pLevel;
            this.uses = pUses;
            this.speed = pSpeed;
            this.damage = pDamage;
            this.enchantmentValue = pEnchantmentValue;
            this.repairIngredient = new LazyLoadedValue<>(pRepairIngredient);
        }

        public int getUses() {
            return this.uses;
        }

        public float getSpeed() {
            return this.speed;
        }

        public float getAttackDamageBonus() {
            return this.damage;
        }

        public int getLevel() {
            return this.level;
        }

        public int getEnchantmentValue() {
            return this.enchantmentValue;
        }

        public Ingredient getRepairIngredient() {
            return this.repairIngredient.get();
        }
    }

}
