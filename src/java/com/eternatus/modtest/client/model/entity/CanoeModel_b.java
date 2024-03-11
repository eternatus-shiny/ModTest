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

public class CanoeModel_b extends ListModel<CanoeEntity> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(References.MODID, "canoe"), "main");
	private final ModelPart ModelCanoe;
	private final ModelPart bb_main;
	private final ImmutableList<ModelPart> parts;

	public CanoeModel_b(ModelPart root) {
		this.ModelCanoe = root.getChild("ModelCanoe");
		this.bb_main = root.getChild("bb_main");
		ImmutableList.Builder<ModelPart> builder = new ImmutableList.Builder<>();
		builder.add(ModelCanoe.getChild("ModelCanoe"),bb_main.getChild("bb_main"));
		this.parts = builder.build();
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition ModelCanoe = partdefinition.addOrReplaceChild("ModelCanoe", CubeListBuilder.create()
		.texOffs(0, 83).addBox(8.0F, -5.9F, -33.5F, 1.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 73).addBox(-8.0F, -5.9F, -33.5F, 16.0F, 9.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(85, 64).addBox(-4.0F, -5.9F, -45.5F, 8.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 64).addBox(-9.5F, -1.9F, -11.0F, 19.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 30).addBox(-10.0F, -5.9F, -23.5F, 20.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(0, 42).addBox(-6.0F, -5.9F, -40.5F, 12.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(12, 83).addBox(-4.0F, -5.9F, 40.5F, 8.0F, 4.0F, 5.0F, new CubeDeformation(0.0F))
		.texOffs(0, 42).addBox(-6.0F, -5.9F, 33.5F, 12.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 19).addBox(-8.0F, 3.1F, -33.4F, 16.0F, 1.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 83).addBox(-9.0F, -5.9F, -33.5F, 1.0F, 9.0F, 10.0F, new CubeDeformation(0.0F))
		.texOffs(0, 30).addBox(-10.0F, -5.9F, 22.5F, 20.0F, 11.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(85, 65).addBox(-10.5F, -5.9F, -22.5F, 1.0F, 11.0F, 45.0F, new CubeDeformation(0.0F))
		.texOffs(0, 64).addBox(-10.0F, 4.9F, -22.5F, 20.0F, 1.0F, 45.0F, new CubeDeformation(0.0F))
		.texOffs(85, 65).addBox(9.5F, -5.9F, -22.5F, 1.0F, 11.0F, 45.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-8.0F, -5.9F, 23.5F, 16.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(93, 0).addBox(-9.0F, -29.0F, -33.0F, 18.0F, 8.0F, 56.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		ModelCanoe.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public Iterable<ModelPart> parts() {
		return this.parts;
	}

	public ModelPart waterPatch() {
		return this.bb_main;
	}

	@Override
	public void setupAnim(CanoeEntity pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

	}
}