// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


/*public class ModelDeepOne<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "modeldeepone"), "main");
	private final ModelPart root;

	public ModelDeepOne(ModelPart root) {
		this.root = root.getChild("root");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Head = root.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(50, 16).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, -5.73F));

		PartDefinition HeadChild = Head.addOrReplaceChild("HeadChild", CubeListBuilder.create().texOffs(83, 12).addBox(-0.5F, 3.0F, 8.3F, 1.0F, 9.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -21.0F, -2.0F, -0.6109F, 0.0F, 0.0F));

		PartDefinition HeadChild = Head.addOrReplaceChild("HeadChild", CubeListBuilder.create().texOffs(81, 15).addBox(-1.0F, 0.0F, -5.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -2.0F, 1.0F));

		PartDefinition HeadChild = Head.addOrReplaceChild("HeadChild", CubeListBuilder.create().texOffs(106, 25).addBox(-3.0F, 0.0F, -5.0F, 6.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 1.0F));

		PartDefinition HeadChild = Head.addOrReplaceChild("HeadChild", CubeListBuilder.create().texOffs(81, 15).mirror().addBox(-1.0F, 0.0F, -5.0F, 1.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(4.0F, -2.0F, 1.0F));

		PartDefinition HeadChild = Head.addOrReplaceChild("HeadChild", CubeListBuilder.create().texOffs(106, 20).addBox(-8.0F, 0.0F, 0.0F, 8.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -2.0F, 1.0F));

		PartDefinition RightUpperArm = root.addOrReplaceChild("RightUpperArm", CubeListBuilder.create().texOffs(94, 0).addBox(-1.5F, 0.0F, -1.4F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -7.0F, 0.0F, 0.0F, 0.0F, 0.4363F));

		PartDefinition RightUpperArmChild = RightUpperArm.addOrReplaceChild("RightUpperArmChild", CubeListBuilder.create().texOffs(94, 0).addBox(-12.5F, 5.0F, 5.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(11.8F, 3.0F, 0.0F, -0.8551F, 0.0F, 0.0F));

		PartDefinition DorsalFin = root.addOrReplaceChild("DorsalFin", CubeListBuilder.create().texOffs(52, 1).addBox(-0.5F, -11.0F, 0.0F, 1.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 8.0F, 0.409F, 0.0F, 0.0F));

		PartDefinition LeftUpperLeg = root.addOrReplaceChild("LeftUpperLeg", CubeListBuilder.create().texOffs(94, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 8.0F, 3.0F, -0.6F, 0.0F, 0.0F));

		PartDefinition LeftUpperLegChild = LeftUpperLeg.addOrReplaceChild("LeftUpperLegChild", CubeListBuilder.create().texOffs(94, 0).addBox(3.5F, 0.0F, 3.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, 12.0F, -3.5F, 0.9F, 0.0F, 0.0F));

		PartDefinition RightUpperLeg = root.addOrReplaceChild("RightUpperLeg", CubeListBuilder.create().texOffs(94, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 8.0F, 3.0F, -0.6F, 0.0F, 0.0F));

		PartDefinition RightUpperLegChild = RightUpperLeg.addOrReplaceChild("RightUpperLegChild", CubeListBuilder.create().texOffs(94, 0).addBox(-6.5F, 0.0F, 3.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, 12.0F, -3.5F, 0.9F, 0.0F, 0.0F));

		PartDefinition LowerBody = root.addOrReplaceChild("LowerBody", CubeListBuilder.create().texOffs(94, 0).addBox(-5.0F, 0.0F, -1.0F, 10.0F, 10.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

		PartDefinition LeftUpperArm = root.addOrReplaceChild("LeftUpperArm", CubeListBuilder.create().texOffs(94, 0).addBox(-1.5F, 0.0F, -1.4F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -7.0F, 0.0F, 0.0F, 0.0F, -0.4363F));

		PartDefinition LeftUpperArmChild = LeftUpperArm.addOrReplaceChild("LeftUpperArmChild", CubeListBuilder.create().texOffs(94, 0).addBox(10.4F, 5.0F, 5.0F, 2.0F, 11.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.8F, 3.0F, 0.0F, -0.8551F, 0.0F, 0.0F));

		PartDefinition Body = root.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-7.5F, 0.0F, 0.0F, 15.0F, 13.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -7.0F, 0.3718F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 32);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}*/