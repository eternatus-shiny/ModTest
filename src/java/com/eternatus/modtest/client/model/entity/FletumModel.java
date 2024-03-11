package com.valeriotor.beyondtheveil.client.model.entity;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.valeriotor.beyondtheveil.client.render.entity.HamletDwellerRenderer;
import com.valeriotor.beyondtheveil.entity.HamletDwellerEntity;
import com.valeriotor.beyondtheveil.lib.References;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class FletumModel extends EntityModel<LivingEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(References.MODID, "fletum"), "main");
	private final ModelPart root;

	public FletumModel(ModelPart root) {
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition shape10_2 = root.addOrReplaceChild("shape10_2", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.0F, 0.0F, 10.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 15.2F, -6.0F));

		PartDefinition shape10_1 = root.addOrReplaceChild("shape10_1", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, 0.0F, 0.0F, 8.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(7.8F, 15.2F, -1.2F));

		PartDefinition shape10 = root.addOrReplaceChild("shape10", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, 0.0F, 0.0F, 10.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(2.5F, 13.8F, -2.4F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

    @Override
    public void setupAnim(LivingEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

    }
}