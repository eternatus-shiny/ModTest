// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


/*public class CustomModel<T extends Entity> extends EntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "custommodel"), "main");
	private final ModelPart upper_body;
	private final ModelPart body;

	public CustomModel(ModelPart root) {
		this.upper_body = root.getChild("upper_body");
		this.body = root.getChild("body");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition upper_body = partdefinition.addOrReplaceChild("upper_body", CubeListBuilder.create().texOffs(0, 24).addBox(-5.5F, 0.0F, 0.0F, 11.0F, 11.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, -7.4F, 0.9092F, 0.0F, 0.0F));

		PartDefinition upper_bodyChild = upper_body.addOrReplaceChild("upper_bodyChild", CubeListBuilder.create().texOffs(32, 9).addBox(-4.0F, 0.0F, -8.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.0F, 5.3F, -0.7285F, 0.0F, 0.0F));

		PartDefinition upper_bodyChildChild = upper_bodyChild.addOrReplaceChild("upper_bodyChildChild", CubeListBuilder.create().texOffs(32, 0).addBox(-4.0F, 0.0F, -8.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 6.6F, 0.0F, 0.2198F, 0.0F, 0.0F));

		PartDefinition upper_bodyChild = upper_body.addOrReplaceChild("upper_bodyChild", CubeListBuilder.create().texOffs(52, 28).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.2F, 4.8F, 3.8F, -0.8652F, 0.2276F, 0.6829F));

		PartDefinition upper_bodyChildChild = upper_bodyChild.addOrReplaceChild("upper_bodyChildChild", CubeListBuilder.create().texOffs(40, 28).addBox(-2.0F, 0.0F, 0.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, 9.8F, 0.0F, -0.7285F, 0.0F, 0.0F));

		PartDefinition upper_bodyChild = upper_body.addOrReplaceChild("upper_bodyChild", CubeListBuilder.create().texOffs(52, 28).addBox(-1.5F, 0.0F, 0.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.2F, 4.8F, 3.8F, -0.8652F, -0.2276F, -0.5637F));

		PartDefinition upper_bodyChildChild = upper_bodyChild.addOrReplaceChild("upper_bodyChildChild", CubeListBuilder.create().texOffs(40, 28).addBox(-2.0F, 0.0F, 0.0F, 3.0F, 12.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.4F, 9.8F, 0.0F, -0.7285F, 0.0F, 0.0F));

		PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 42).addBox(-5.0F, 0.0F, 0.0F, 10.0F, 15.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, -1.0F, 0.182F, 0.0F, 0.0F));

		PartDefinition bodyChild = body.addOrReplaceChild("bodyChild", CubeListBuilder.create().texOffs(48, 50).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.8F, 12.0F, 1.8F, -0.6F, 0.0F, 0.0F));

		PartDefinition bodyChildChild = bodyChild.addOrReplaceChild("bodyChildChild", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 10.0F, 0.0F, 0.4554F, 0.0F, 0.0F));

		PartDefinition bodyChild = body.addOrReplaceChild("bodyChild", CubeListBuilder.create().texOffs(48, 50).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 10.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.8F, 12.0F, 1.8F, -0.6F, 0.0F, 0.0F));

		PartDefinition bodyChildChild = bodyChild.addOrReplaceChild("bodyChildChild", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 9.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.1F, 10.0F, 0.0F, 0.4554F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		upper_body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}*/