package com.valeriotor.beyondtheveil.client.model.entity;// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.valeriotor.beyondtheveil.client.render.entity.HamletDwellerRenderer;
import com.valeriotor.beyondtheveil.entity.CanoeEntity;
import com.valeriotor.beyondtheveil.entity.HamletDwellerEntity;
import com.valeriotor.beyondtheveil.lib.References;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class CanoeModel_a extends ListModel<CanoeEntity> {

    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(References.MODID, "canoe"), "main");
	private final ModelPart root;
	private final ImmutableList<ModelPart> parts;

	public CanoeModel_a(ModelPart root) {
		this.root = root.getChild("root");
		ImmutableList.Builder<ModelPart> builder = new ImmutableList.Builder<>();
		builder.add(root.getChild("root"),root.getChild("root").getChild("shape7_1"),root.getChild("root").getChild("shape4_2"),root.getChild("root").getChild("shape11_1")
				,root.getChild("root").getChild("shape15"),root.getChild("root").getChild("shape4_1"),root.getChild("root").getChild("shape10_1"),root.getChild("root").getChild("shape11")
				,root.getChild("root").getChild("shape10"),root.getChild("root").getChild("shape6"),root.getChild("root").getChild("shape7"),root.getChild("root").getChild("shape4")
				,root.getChild("root").getChild("LeftSide"),root.getChild("root").getChild("Bottom"),root.getChild("root").getChild("RightSide"),root.getChild("root").getChild("shape9"));
		this.parts = builder.build();
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition shape7_1 = root.addOrReplaceChild("shape7_1", CubeListBuilder.create().texOffs(0, 30).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, -5.9F, -33.5F));

		PartDefinition shape4_2 = root.addOrReplaceChild("shape4_2", CubeListBuilder.create().texOffs(120, 14).addBox(-8.0F, 0.0F, 0.0F, 16.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.9F, -33.5F));

		PartDefinition shape11_1 = root.addOrReplaceChild("shape11_1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.9F, -45.5F));

		PartDefinition shape15 = root.addOrReplaceChild("shape15", CubeListBuilder.create().texOffs(0, 12).addBox(-9.5F, 0.0F, 0.0F, 19.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.9F, -11.0F));

		PartDefinition shape4_1 = root.addOrReplaceChild("shape4_1", CubeListBuilder.create().texOffs(200, 52).addBox(-20.0F, 0.0F, 0.0F, 20.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, -5.9F, -23.5F));

		PartDefinition shape10_1 = root.addOrReplaceChild("shape10_1", CubeListBuilder.create().texOffs(133, 28).addBox(-6.0F, 0.0F, 0.0F, 12.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.9F, -40.5F));

		PartDefinition shape11 = root.addOrReplaceChild("shape11", CubeListBuilder.create().texOffs(120, 0).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.9F, 40.5F));

		PartDefinition shape10 = root.addOrReplaceChild("shape10", CubeListBuilder.create().texOffs(133, 28).addBox(-6.0F, 0.0F, 0.0F, 12.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.9F, 33.5F));

		PartDefinition shape6 = root.addOrReplaceChild("shape6", CubeListBuilder.create().texOffs(70, 0).addBox(-8.0F, 0.0F, -11.0F, 16.0F, 1.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.1F, -22.4F));

		PartDefinition shape7 = root.addOrReplaceChild("shape7", CubeListBuilder.create().texOffs(0, 30).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -5.9F, -33.5F));

		PartDefinition shape4 = root.addOrReplaceChild("shape4", CubeListBuilder.create().texOffs(200, 52).addBox(-20.0F, 0.0F, 0.0F, 20.0F, 11.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, -5.9F, 22.5F));

		PartDefinition LeftSide = root.addOrReplaceChild("LeftSide", CubeListBuilder.create().texOffs(0, 7).addBox(-0.5F, 0.0F, -22.5F, 1.0F, 11.0F, 45.0F, new CubeDeformation(0.0F)), PartPose.offset(-10.0F, -5.9F, 0.0F));

		PartDefinition Bottom = root.addOrReplaceChild("Bottom", CubeListBuilder.create().texOffs(126, 0).addBox(-10.0F, 0.0F, -22.5F, 20.0F, 1.0F, 45.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.9F, 0.0F));

		PartDefinition RightSide = root.addOrReplaceChild("RightSide", CubeListBuilder.create().texOffs(0, 7).addBox(-0.5F, 0.0F, -22.5F, 1.0F, 11.0F, 45.0F, new CubeDeformation(0.0F)), PartPose.offset(10.0F, -5.9F, 0.0F));

		PartDefinition shape9 = root.addOrReplaceChild("shape9", CubeListBuilder.create().texOffs(80, 24).addBox(-8.0F, 0.0F, 0.0F, 16.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.9F, 23.5F));

		return LayerDefinition.create(meshdefinition, 256, 64);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public Iterable<ModelPart> parts() {
		return this.parts;
	}


	@Override
	public void setupAnim(CanoeEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

	}
}