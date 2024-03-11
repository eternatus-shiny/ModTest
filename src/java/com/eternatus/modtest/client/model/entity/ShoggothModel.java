package com.valeriotor.beyondtheveil.client.model.entity;

// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.valeriotor.beyondtheveil.lib.References;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class ShoggothModel extends EntityModel<LivingEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "shoggoth"), "main");
	private final ModelPart root;

	public ShoggothModel(ModelPart root) {
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition mouthBack = root.addOrReplaceChild("mouthBack", CubeListBuilder.create().texOffs(21, 17).addBox(-14.0F, 0.0F, 0.0F, 28.0F, 25.0F, 25.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(9.0F, -15.0F, 8.0F, -0.182F, 0.0F, 0.0F));

		PartDefinition mouthBackChild = mouthBack.addOrReplaceChild("mouthBackChild", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -16.0F, 0.0F, 4.0F, 16.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 20.0F, -0.5918F, 0.0F, 0.0F));

		PartDefinition mouthBackChildChild = mouthBackChild.addOrReplaceChild("mouthBackChildChild", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -10.0F, -0.9F, 3.0F, 10.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.3F, 1.0F, -0.6829F, 0.0F, 0.0F));

		PartDefinition mouthBackChildChildChild = mouthBackChildChild.addOrReplaceChild("mouthBackChildChildChild", CubeListBuilder.create().texOffs(6, 77).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, 0.0F, 0.7285F, 0.0F, 0.0F));

		PartDefinition mouthBackChildChildChild_ = mouthBackChildChild.addOrReplaceChild("mouthBackChildChildChild_", CubeListBuilder.create().texOffs(-8, 77).addBox(-4.0F, 0.0F, 0.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -1.0F, 1.3203F, 0.0F, 0.0F));

		PartDefinition mouthRight = root.addOrReplaceChild("mouthRight", CubeListBuilder.create().texOffs(51, 44).addBox(-4.0F, 0.0F, 0.0F, 4.0F, 25.0F, 23.0F, new CubeDeformation(0.0F)), PartPose.offset(24.0F, -15.0F, -15.0F));

		PartDefinition mouthBottom = root.addOrReplaceChild("mouthBottom", CubeListBuilder.create().texOffs(28, 19).addBox(-15.0F, 0.0F, 0.0F, 30.0F, 8.0F, 47.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 10.0F, -15.0F));

		PartDefinition mouthBottomChild = mouthBottom.addOrReplaceChild("mouthBottomChild", CubeListBuilder.create().texOffs(0, 22).addBox(-3.0F, -24.0F, 0.0F, 5.0F, 24.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, 0.0F, 0.0F));

		PartDefinition mouthBottomChild_ = mouthBottom.addOrReplaceChild("mouthBottomChild_", CubeListBuilder.create().texOffs(13, 22).addBox(-3.0F, -24.0F, 0.0F, 5.0F, 24.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, 0.0F, 0.0F));

		PartDefinition mouthUpper = root.addOrReplaceChild("mouthUpper", CubeListBuilder.create().texOffs(0, 0).addBox(-11.0F, 0.0F, 0.0F, 22.0F, 1.0F, 23.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, -15.0F, -15.0F));

		PartDefinition mouthUpperChild = mouthUpper.addOrReplaceChild("mouthUpperChild", CubeListBuilder.create().texOffs(19, 0).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 24.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.0F, 1.0F, 0.0F));

		PartDefinition mouthUpperChild_ = mouthUpper.addOrReplaceChild("mouthUpperChild_", CubeListBuilder.create().texOffs(19, 0).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 24.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition mouthUpperChild__ = mouthUpper.addOrReplaceChild("mouthUpperChild__", CubeListBuilder.create().texOffs(-13, 0).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 24.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 1.0F, 0.0F));

		PartDefinition sideSmall = root.addOrReplaceChild("sideSmall", CubeListBuilder.create().texOffs(0, 0).addBox(-22.0F, 0.0F, 0.0F, 22.0F, 7.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, 10.0F, -18.0F, 0.0911F, 0.0F, 0.182F));

		PartDefinition sideLarge = root.addOrReplaceChild("sideLarge", CubeListBuilder.create().texOffs(26, 1).addBox(-22.0F, 0.0F, 0.0F, 22.0F, 18.0F, 27.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, -1.0F, 0.0F, 0.0F, 0.0F, 0.2276F));

		PartDefinition eyeTentacleLower = root.addOrReplaceChild("eyeTentacleLower", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -13.0F, 0.0F, 6.0F, 13.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2F, -13.5F, -7.0F, 0.2731F, 0.0F, 0.0F));

		PartDefinition eyeTentacleLowerChild = eyeTentacleLower.addOrReplaceChild("eyeTentacleLowerChild", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -17.0F, 0.0F, 4.0F, 17.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -11.0F, 1.5F, 0.2731F, 0.0F, 0.0F));

		PartDefinition eyeTentacleLowerChildChild = eyeTentacleLowerChild.addOrReplaceChild("eyeTentacleLowerChildChild", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -14.0F, 0.0F, 3.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -15.0F, 0.0F, 0.8652F, 0.0F, 0.0F));

		PartDefinition eyeTentacleLowerChildChildChild = eyeTentacleLowerChildChild.addOrReplaceChild("eyeTentacleLowerChildChildChild", CubeListBuilder.create().texOffs(10, 54).addBox(-3.0F, -5.0F, 0.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -10.0F, -1.5F, 0.1367F, 0.0F, 0.0F));

		PartDefinition mouthLeft = root.addOrReplaceChild("mouthLeft", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 25.0F, 23.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -15.0F, -15.0F));

		PartDefinition rightTentacleLower = root.addOrReplaceChild("rightTentacleLower", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -14.0F, 0.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(22.0F, -4.0F, 15.3F, 0.0F, -1.639F, -1.1839F));

		PartDefinition rightTentacleLowerChild = rightTentacleLower.addOrReplaceChild("rightTentacleLowerChild", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -12.0F, 0.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.0F, 0.0F, -0.5463F, 0.0F, 0.2731F));

		PartDefinition bottom = root.addOrReplaceChild("bottom", CubeListBuilder.create().texOffs(50, 15).addBox(-25.0F, 0.0F, 0.0F, 52.0F, 8.0F, 52.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, -21.0F));

		PartDefinition bottomChild = bottom.addOrReplaceChild("bottomChild", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, 0.0F, -6.0F, 16.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-21.0F, -0.2F, 19.0F, 0.0F, 1.1839F, 0.0F));

		PartDefinition bottomChildChild = bottomChild.addOrReplaceChild("bottomChildChild", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, 0.0F, -17.0F, 12.0F, 9.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -6.0F));

		PartDefinition bottomChildChildChild = bottomChildChild.addOrReplaceChild("bottomChildChildChild", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 0.0F, -10.0F, 8.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.9F, -15.7F));

		PartDefinition bottomChildChildChildChild = bottomChildChildChild.addOrReplaceChild("bottomChildChildChildChild", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -8.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -8.0F));

		PartDefinition bottomChild_ = bottom.addOrReplaceChild("bottomChild_", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, 0.0F, -6.0F, 16.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(18.0F, -2.0F, 8.0F, 0.0F, -0.7285F, 0.0F));

		PartDefinition bottomChildChild_ = bottomChild.addOrReplaceChild("bottomChildChild_", CubeListBuilder.create().texOffs(67, 33).addBox(-6.0F, 0.0F, -17.0F, 12.0F, 9.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -6.0F));

		PartDefinition bottomChildChildChild_ = bottomChildChild_.addOrReplaceChild("bottomChildChildChild_", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 0.0F, -10.0F, 8.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.9F, -15.0F));

		PartDefinition bottomChildChildChildChild_ = bottomChildChildChild_.addOrReplaceChild("bottomChildChildChildChild_", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -8.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -8.0F));

		PartDefinition bottomChild_A = bottom.addOrReplaceChild("bottomChild", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, 0.0F, -6.0F, 16.0F, 8.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.0F, -0.1F, 51.0F, 0.0F, -2.8684F, 0.0F));

		PartDefinition bottomChildChild_A = bottomChild_A.addOrReplaceChild("bottomChildChild", CubeListBuilder.create().texOffs(68, 96).addBox(-6.0F, 0.0F, -17.0F, 12.0F, 9.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -6.0F));

		PartDefinition bottomChildChildChild_A = bottomChildChild_A.addOrReplaceChild("bottomChildChildChild", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, 0.0F, -10.0F, 8.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.8F, -15.0F));

		PartDefinition bottomChildChildChildChild_A = bottomChildChildChild_A.addOrReplaceChild("bottomChildChildChildChild", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, 0.0F, -8.0F, 6.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -8.0F));

		PartDefinition leftTentacleLower = root.addOrReplaceChild("leftTentacleLower", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -14.0F, 0.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-6.0F, 0.0F, 8.0F, 0.0F, 0.0F, 0.3643F));

		PartDefinition leftTentacleLowerChild = leftTentacleLower.addOrReplaceChild("leftTentacleLowerChild", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -12.0F, 0.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -14.0F, 0.0F, -0.5463F, 0.0F, 0.2731F));

		PartDefinition otherChunk = root.addOrReplaceChild("otherChunk", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, 0.0F, 0.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(-12.0F, 2.0F, -7.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}