package com.valeriotor.beyondtheveil.client.render.entity;

import com.valeriotor.beyondtheveil.client.model.entity.FletumModel;
import com.valeriotor.beyondtheveil.lib.References;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class FletumRenderer extends LivingEntityRenderer<LivingEntity, FletumModel> {

    public static final ResourceLocation TEXTURE = new ResourceLocation(References.MODID, "textures/entity/weeper.png");

    public FletumRenderer(EntityRendererProvider.Context context) {
        super(context, new FletumModel(context.bakeLayer(FletumModel.LAYER_LOCATION)), 1F);
    }

    @Override
    protected boolean shouldShowName(LivingEntity pEntity) {
        return super.shouldShowName(pEntity) && (pEntity.shouldShowName() || pEntity.hasCustomName() && pEntity == this.entityRenderDispatcher.crosshairPickEntity);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntity pEntity) {
        return TEXTURE;
    }
}
