package com.valeriotor.beyondtheveil.client.render.entity;

import com.google.common.collect.Maps;
import com.valeriotor.beyondtheveil.client.model.entity.DeepOneModel;
import com.valeriotor.beyondtheveil.client.model.entity.HamletDwellerModel;
import com.valeriotor.beyondtheveil.entity.HamletDwellerEntity;
import com.valeriotor.beyondtheveil.lib.References;
import com.valeriotor.beyondtheveil.world.entity.hamletDweeller.ProfessionsEnum;
import net.minecraft.Util;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.horse.Variant;
import org.jetbrains.annotations.NotNull;

import java.sql.Ref;
import java.util.Map;

import static com.valeriotor.beyondtheveil.entity.HamletDwellerEntitye.ProfessionsEnum.*;

public class HamletDwellerRenderer extends LivingEntityRenderer<LivingEntity, HamletDwellerModel> {

    public static final Map<ProfessionsEnum, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(ProfessionsEnum.class), (p_114874_) -> {
                p_114874_.put(ProfessionsEnum.FISHERMAN, new ResourceLocation(References.MODID, "textures/entity/dweller/fisherman.png"));
                p_114874_.put(ProfessionsEnum.BARTENDER, new ResourceLocation(References.MODID, "textures/entity/dweller/bartender.png"));
                p_114874_.put(ProfessionsEnum.MINER,new ResourceLocation(References.MODID, "textures/entity/dweller/miner.png"));
                p_114874_.put(ProfessionsEnum.LHKEEPER,new ResourceLocation(References.MODID, "textures/entity/dweller/lhkeeper.png"));
                p_114874_.put(ProfessionsEnum.STOCKPILER,new ResourceLocation(References.MODID, "textures/entity/dweller/stockpiler.png"));
                p_114874_.put(ProfessionsEnum.DRUNK,new ResourceLocation(References.MODID, "textures/entity/dweller/drunk.png"));
                p_114874_.put(ProfessionsEnum.CARPENTER,new ResourceLocation(References.MODID, "textures/entity/dweller/carpenter.png"));
                p_114874_.put(ProfessionsEnum.SCHOLAR,new ResourceLocation(References.MODID, "textures/entity/dweller/scholar.png"));
            });

    public HamletDwellerRenderer(EntityRendererProvider.Context context) {
        super(context, new HamletDwellerModel(context.bakeLayer(HamletDwellerModel.LAYER_LOCATION)), 0.5F);
    }


    @Override
    protected boolean shouldShowName(LivingEntity pEntity) {
        return super.shouldShowName(pEntity) && (pEntity.shouldShowName() || pEntity.hasCustomName() && pEntity == this.entityRenderDispatcher.crosshairPickEntity);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntity pEntity) {
        return LOCATION_BY_VARIANT.get(((HamletDwellerEntity)pEntity).getProfession());
    }


//    @Override
//    public ResourceLocation getTextureLocation(LivingEntity pEntity) {
//        return TEXTURE;
//    }

}
