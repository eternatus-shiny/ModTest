package com.valeriotor.beyondtheveil.item;

import com.mojang.logging.LogUtils;
import com.valeriotor.beyondtheveil.Registration;
import com.valeriotor.beyondtheveil.entity.FletumEntity;
import com.valeriotor.beyondtheveil.tile.LacrymatoryBE;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BaseSpawner;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SpawnerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class HeldFletumItem extends Item {
    private static final Logger LOGGER = LogUtils.getLogger();

    public HeldFletumItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
//        LOGGER.info("Se llama al metodo");
        Level level = pContext.getLevel();
//        LOGGER.info(level.toString());
        LacrymatoryBE lacrymatory = null;
        if (!(level instanceof ServerLevel)) {
            return InteractionResult.SUCCESS;
        } else {
            ItemStack itemstack = pContext.getItemInHand();
            BlockPos blockpos = pContext.getClickedPos();
            Direction direction = pContext.getClickedFace();
            BlockState blockstate = level.getBlockState(blockpos);

            if(level.getBlockEntity(blockpos) instanceof LacrymatoryBE){
                lacrymatory  = (LacrymatoryBE) level.getBlockEntity(blockpos);
            }

            BlockPos blockpos1;
            if (blockstate.getCollisionShape(level, blockpos).isEmpty()) {
                blockpos1 = blockpos;
            } else {
                blockpos1 = blockpos.relative(direction);
            }
//            LOGGER.info("Ñe!");

            EntityType<?> entitytype = Registration.FLETUM.get();
            LOGGER.info(entitytype.toString());
            Entity fletum = entitytype.spawn((ServerLevel) level, itemstack, pContext.getPlayer(), blockpos1, MobSpawnType.TRIGGERED, true, !Objects.equals(blockpos, blockpos1) && direction == Direction.UP);
            if (fletum != null) {
                if(fletum instanceof FletumEntity fletumEntity){
                    if(lacrymatory!=null && lacrymatory.setWeeper(fletumEntity)) {
                        fletumEntity.setLacrymatory(blockpos);
                    } else if(lacrymatory!=null){
                        pContext.getPlayer().sendSystemMessage(Component.translatable("interact.lacrymatory.full"));
                        return InteractionResult.FAIL;
                    }
                }
                level.gameEvent(pContext.getPlayer(), GameEvent.ENTITY_PLACE, blockpos);
            }
            if(lacrymatory!=null){
                pContext.getPlayer().sendSystemMessage(Component.translatable("interact.lacrymatory.succes", Component.translatable("entity.fletum.name")));
            }
            itemstack.shrink(1);
            return InteractionResult.CONSUME;
        }
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("lore.heldfletum"));
    }
}
