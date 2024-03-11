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

public class CrazedWeeperModel extends EntityModel<LivingEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "crazedweeper"), "main");
	private final ModelPart root;

	public CrazedWeeperModel(ModelPart root) {
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition RightArm = root.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(44, 7).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.5F, 1.0F, -1.0F, -0.7465F, -0.6807F, 0.0F));

		PartDefinition RightArmChild = RightArm.addOrReplaceChild("RightArmChild", CubeListBuilder.create().texOffs(56, 54).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.0F, 0.0F, 0.0F, 0.0F, 0.2276F));

		PartDefinition RightArmChildChild = RightArmChild.addOrReplaceChild("RightArmChildChild", CubeListBuilder.create().texOffs(56, 54).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.4F, 0.0F, 0.8196F, 0.0F, 0.6829F));

		PartDefinition RightArmChild_ = RightArm.addOrReplaceChild("RightArmChild_", CubeListBuilder.create().texOffs(56, 54).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 10.3F, 0.5F, 0.0F, 0.2269F, -0.2269F));

		PartDefinition RightArmChildChild_ = RightArmChild_.addOrReplaceChild("RightArmChildChild_", CubeListBuilder.create().texOffs(56, 54).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.4F, 0.0F, 0.7741F, 0.0F, 0.0F));

		PartDefinition LeftLeg = root.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(0, 22).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 12.0F, 0.0F));

		PartDefinition HeadFront = root.addOrReplaceChild("HeadFront", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, 0.0F, 0.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -2.2F, -11.0F));

		PartDefinition HeadLeft = root.addOrReplaceChild("HeadLeft", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, 0.0F, -1.0F, 8.0F, 9.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.7F, -7.5F, 2.6F));

		PartDefinition LeftArm = root.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(44, 7).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 11.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 0.8F, -1.0F, -0.75F, 0.6829F, 0.0F));

		PartDefinition LeftArmChild = LeftArm.addOrReplaceChild("LeftArmChild", CubeListBuilder.create().texOffs(56, 54).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 10.0F, 1.0F, -0.2731F, 0.0F, 0.0F));

		PartDefinition LeftArmChildChild = LeftArmChild.addOrReplaceChild("LeftArmChildChild", CubeListBuilder.create().texOffs(56, 54).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.4F, 0.0F, 0.7741F, 0.0F, 0.0F));

		PartDefinition LeftArmChild_ = LeftArm.addOrReplaceChild("LeftArmChild_", CubeListBuilder.create().texOffs(56, 54).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 10.0F, 1.5F, 0.0F, -0.2276F, 0.2276F));

		PartDefinition LeftArmChildChild_ = LeftArmChild.addOrReplaceChild("LeftArmChildChild_", CubeListBuilder.create().texOffs(56, 54).addBox(-1.0F, 0.0F, 0.0F, 1.0F, 6.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.4F, 0.0F, 0.8196F, 0.0F, 0.6829F));

		PartDefinition RightLeg = root.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(0, 22).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 12.0F, 0.0F));

		PartDefinition CentralBody = root.addOrReplaceChild("CentralBody", CubeListBuilder.create().texOffs(16, 20).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition HeadRight = root.addOrReplaceChild("HeadRight", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, 0.0F, 0.0F, 8.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(10.8F, -5.7F, 1.8F));

		PartDefinition Body = root.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 38).addBox(-4.0F, 0.0F, -3.0F, 8.0F, 18.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition HeadSpine = root.addOrReplaceChild("HeadSpine", CubeListBuilder.create().texOffs(56, 54).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition HeadSpineChild = HeadSpine.addOrReplaceChild("HeadSpineChild", CubeListBuilder.create().texOffs(56, 54).addBox(-1.0F, -9.0F, 0.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, -0.2276F, 0.0F, 0.182F));

		PartDefinition HeadSpineChild_ = HeadSpine.addOrReplaceChild("HeadSpineChild_", CubeListBuilder.create().texOffs(56, 54).addBox(0.0F, -9.0F, -1.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -3.0F, 0.0F, 0.4098F, 0.0F, 0.0911F));

		PartDefinition HeadSpineChild__ = HeadSpine.addOrReplaceChild("HeadSpineChild__", CubeListBuilder.create().texOffs(56, 54).addBox(0.0F, -9.0F, -1.0F, 1.0F, 9.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 0.0F, 0.182F, 0.0F, -0.4098F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}