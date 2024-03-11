package com.valeriotor.beyondtheveil.client.render.item;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.math.Vector3f;
import com.valeriotor.beyondtheveil.Registration;
import com.valeriotor.beyondtheveil.client.render.BTVRenderType;
import com.valeriotor.beyondtheveil.item.CaveMapItem;
import com.valeriotor.beyondtheveil.client.render.misc.CaveMapRenderHelper;
import com.valeriotor.beyondtheveil.client.render.misc.CaveMapRenderer;
import com.valeriotor.beyondtheveil.client.render.misc.DefaultMapBackgrounds;
import com.valeriotor.beyondtheveil.lib.ConfigLib;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.TagParser;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

import java.util.List;

import static net.minecraft.core.BlockPos.getX;
import static net.minecraft.core.BlockPos.getY;

public class BTVItemstackRenderer extends BlockEntityWithoutLevelRenderer {
    public static boolean sepiaFlag = false;
    public BTVItemstackRenderer() {
        super(null, null);
    }

    @Override
    public void renderByItem(ItemStack itemStackIn, ItemTransforms.TransformType transformType, PoseStack poseStack, MultiBufferSource bufferIn, int combinedLightIn, int combinedOverlayIn) {
        ClientLevel level = Minecraft.getInstance().level;
        float partialTick = Minecraft.getInstance().getPartialTick();
        boolean heldIn3d = transformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND || transformType == ItemTransforms.TransformType.THIRD_PERSON_RIGHT_HAND || transformType == ItemTransforms.TransformType.FIRST_PERSON_RIGHT_HAND || transformType == ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND;
        boolean left = transformType == ItemTransforms.TransformType.THIRD_PERSON_LEFT_HAND || transformType == ItemTransforms.TransformType.FIRST_PERSON_LEFT_HAND;

        if (itemStackIn.is(Registration.CAVE_MAP.get())) {
            poseStack.translate(0.5F, 0.5f, 0.5f);
            ItemStack spriteItem = new ItemStack(Registration.CAVE_MAP_SPRITE.get());
            spriteItem.setTag(itemStackIn.getTag());
            boolean done = CaveMapItem.isFilled(itemStackIn) && !CaveMapItem.isLoading(itemStackIn);
            if(done){
                spriteItem = new ItemStack(Registration.CAVE_MAP_FILLED_SPRITE.get());
            }else if(CaveMapItem.isLoading(itemStackIn)){
                spriteItem = new ItemStack(Registration.CAVE_MAP_LOADING_SPRITE.get());
            }
            if (transformType.firstPerson() && done) {
                Player player = Minecraft.getInstance().player;
                ItemStack offhandHeldItem = player.getItemInHand(InteractionHand.OFF_HAND);
                boolean renderingSmallMap = !offhandHeldItem.isEmpty();
                boolean offhand = offhandHeldItem.equals(itemStackIn);
                poseStack.pushPose();
                if(renderingSmallMap){
                    poseStack.translate(left ? 0.5F : -0.5F, 0.35, 0);
                    CaveMapRenderHelper.renderOneHandedCaveMap(poseStack, bufferIn, combinedLightIn, 0, offhand ? player.getMainArm().getOpposite() : player.getMainArm(), 0, itemStackIn);
                }else{
                    poseStack.translate(left ? 0.55F : -0.55F, 0.525F, 0.75F);
                    CaveMapRenderHelper.renderTwoHandedCaveMap(poseStack, bufferIn, combinedLightIn, partialTick, 0, 0, itemStackIn);
                }
                poseStack.popPose();
            } else if(heldIn3d && ConfigLib.caveMapsVisibleInThirdPerson.get() && done){
                poseStack.translate(left ? 0.15F : -0.15F, 0.25F, 0.05F);
                poseStack.scale(1.5F, 1.5F, 1.5F);
                CaveMapRenderHelper.renderCaveMap(poseStack, bufferIn, combinedLightIn, itemStackIn, true);
            }else{
                renderStaticItemSpriteWithLighting(spriteItem, transformType, combinedLightIn, combinedOverlayIn, poseStack, bufferIn, level);
            }
        }
    }
    private void renderStaticItemSpriteWithLighting(ItemStack spriteItem, ItemTransforms.TransformType transformType, int combinedLightIn, int combinedOverlayIn, PoseStack poseStack, MultiBufferSource bufferIn, ClientLevel level) {
        if(sepiaFlag){
            BakedModel bakedmodel = Minecraft.getInstance().getItemRenderer().getModel(spriteItem, Minecraft.getInstance().level, null, 0);
            ItemWidget.renderSepiaItem(poseStack, bakedmodel, spriteItem, Minecraft.getInstance().renderBuffers().bufferSource());
        }else{
            Minecraft.getInstance().getItemRenderer().renderStatic(spriteItem, transformType, transformType != ItemTransforms.TransformType.GUI ? combinedLightIn : 240, combinedOverlayIn, poseStack, bufferIn, 0);
        }
    }

    public class ItemWidget extends BookWidget {

        @Expose
        private String item;
        @Expose
        private String nbt;
        @Expose
        private boolean sepia;

        @Expose(serialize = false, deserialize = false)
        private ItemStack actualItem = ItemStack.EMPTY;

        private static final RenderType SEPIA_ITEM_RENDER_TYPE = BTVRenderType.getBookWidget(TextureAtlas.LOCATION_BLOCKS, true);

        public ItemWidget(int displayPage, String item, String nbt, boolean sepia, int x, int y, float scale) {
            super(displayPage, Type.ITEM, x, y, scale);
            this.item = item;
            this.nbt = nbt;
            this.sepia = sepia;
        }

        public void render(PoseStack poseStack, MultiBufferSource.BufferSource bufferSource, float partialTicks, boolean onFlippingPage) {
            if (actualItem == null && item != null) {
                actualItem = new ItemStack(ForgeRegistries.ITEMS.getValue(new ResourceLocation(item)));
                if (nbt != null && !nbt.isEmpty()) {
                    CompoundTag tag = null;
                    try {
                        tag = TagParser.parseTag(nbt);
                    } catch (CommandSyntaxException e) {
                        e.printStackTrace();
                    }
                    actualItem.setTag(tag);
                }
            }
            float scale = 16.0F * getScale();

            poseStack.pushPose();
            poseStack.translate(getX(), getY(), 0);
            poseStack.translate(0, 0, 50);
            renderItem(actualItem, poseStack, bufferSource, sepia, scale);
            poseStack.popPose();

        }

        public static void renderItem(ItemStack itemStack, PoseStack poseStack, MultiBufferSource.BufferSource bufferSource, boolean sepia, float scale) {
            if (itemStack == null) {
                return;
            }
            BakedModel bakedmodel = Minecraft.getInstance().getItemRenderer().getModel(itemStack, Minecraft.getInstance().level, null, 0);
            poseStack.pushPose();
            try {
                poseStack.scale(scale, scale, scale);
                CaveBookScreen.fixLighting();
                if (!sepia) {
                    poseStack.mulPose(Vector3f.YP.rotationDegrees(180F));
                    poseStack.mulPose(Vector3f.ZP.rotationDegrees(180F));
                } else {
                    poseStack.mulPose(Vector3f.ZN.rotationDegrees(180F));
                    poseStack.scale(-1F, 1F, 1F);
                    BTVItemstackRenderer.sepiaFlag = true;
                }
                if (sepia && !bakedmodel.isCustomRenderer()) {
                    renderSepiaItem(poseStack, bakedmodel, itemStack, bufferSource);
                } else {
                    Minecraft.getInstance().getItemRenderer().render(itemStack, ItemTransforms.TransformType.GUI, false, poseStack, bufferSource, 240, OverlayTexture.NO_OVERLAY, bakedmodel);
                }
                if (sepia) {
                    BTVItemstackRenderer.sepiaFlag = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            poseStack.popPose();
        }

        public static void renderSepiaItem(PoseStack poseStack, BakedModel bakedmodel, ItemStack itemStack, MultiBufferSource.BufferSource bufferSource) {
            poseStack.pushPose();
            bakedmodel = net.minecraftforge.client.ForgeHooksClient.handleCameraTransforms(poseStack, bakedmodel, ItemTransforms.TransformType.GUI, false);
            poseStack.translate(-0.5F, -0.5F, -0.5F);
            for (net.minecraft.client.renderer.RenderType rt : bakedmodel.getRenderTypes(itemStack, false)) {
                renderModel(poseStack.last(), bufferSource.getBuffer(SEPIA_ITEM_RENDER_TYPE), 1.0F, null, bakedmodel, 1.0F, 1.0F, 1.0F, 240, OverlayTexture.NO_OVERLAY, ModelData.EMPTY, rt);
            }
            poseStack.popPose();
        }

        private static void renderModel(PoseStack.Pose p_111068_, VertexConsumer p_111069_, float alpha, @Nullable BlockState p_111070_, BakedModel p_111071_, float p_111072_, float p_111073_, float p_111074_, int p_111075_, int p_111076_, ModelData modelData, net.minecraft.client.renderer.RenderType renderType) {
            RandomSource randomsource = RandomSource.create();
            long i = 42L;

            for (Direction direction : Direction.values()) {
                randomsource.setSeed(42L);
                renderQuadList(p_111068_, p_111069_, p_111072_, p_111073_, p_111074_, alpha, p_111071_.getQuads(p_111070_, direction, randomsource, modelData, renderType), p_111075_, p_111076_);
            }

            randomsource.setSeed(42L);
            renderQuadList(p_111068_, p_111069_, p_111072_, p_111073_, p_111074_, alpha, p_111071_.getQuads(p_111070_, (Direction) null, randomsource, modelData, renderType), p_111075_, p_111076_);
        }

        private static void renderQuadList(PoseStack.Pose p_111059_, VertexConsumer p_111060_, float p_111061_, float p_111062_, float p_111063_, float alpha, List<BakedQuad> p_111064_, int p_111065_, int p_111066_) {
            for (BakedQuad bakedquad : p_111064_) {
                float f;
                float f1;
                float f2;
                f = Mth.clamp(p_111061_, 0.0F, 1.0F);
                f1 = Mth.clamp(p_111062_, 0.0F, 1.0F);
                f2 = Mth.clamp(p_111063_, 0.0F, 1.0F);
                p_111060_.putBulkData(p_111059_, bakedquad, new float[]{1.0F, 1.0F, 1.0F, 1.0F}, f, f1, f2, alpha, new int[]{p_111065_, p_111065_, p_111065_, p_111065_}, p_111066_, false);
            }

        }
    }
    public abstract class BookWidget {

        @Expose
        @SerializedName("display_page")
        private int displayPage;
        @Expose
        private Type type;
        @Expose
        private int x;
        @Expose
        private int y;
        @Expose
        private float scale;

        public BookWidget(int displayPage, Type type, int x, int y, float scale) {
            this.displayPage = displayPage;
            this.type = type;
            this.x = x;
            this.y = y;
            this.scale = scale;
        }

        public int getDisplayPage() {
            return displayPage;
        }

        public Type getType() {
            return type;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public float getScale() {
            return scale;
        }

        public abstract void render(PoseStack poseStack, MultiBufferSource.BufferSource bufferSource, float partialTicks, boolean onFlippingPage);

        public enum Type {

            ITEM(ItemWidget.class);
            private final Class<? extends BookWidget> widgetClass;

            Type(Class<? extends BookWidget> widgetClass) {
                this.widgetClass = widgetClass;
            }

            public Class<? extends BookWidget> getWidgetClass() {
                return widgetClass;
            }

        }
    }

    public class CaveBookScreen{
        public static void fixLighting(){
            Vector3f light0 = new Vector3f(1, 1.0F, -1.0F);
            Vector3f light1 = new Vector3f(1, 1.0F, -1.0F);
            RenderSystem.setShaderLights(light0, light1);
        }
    }
}
