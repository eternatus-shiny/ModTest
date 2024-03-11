package com.valeriotor.beyondtheveil.client;

import com.valeriotor.beyondtheveil.Registration;
import com.valeriotor.beyondtheveil.animation.AnimationRegistry;
import com.valeriotor.beyondtheveil.client.gui.GearBenchGui;
import com.valeriotor.beyondtheveil.client.model.entity.*;
//import com.valeriotor.beyondtheveil.client.model.entity.layer.ChestWoundModel;
//import com.valeriotor.beyondtheveil.client.model.entity.layer.WoundModel;
import com.valeriotor.beyondtheveil.client.render.blockentity.*;
import com.valeriotor.beyondtheveil.client.render.entity.*;
//import com.valeriotor.beyondtheveil.client.research.ResearchRegistryClient;
//import com.valeriotor.beyondtheveil.item.MemoryPhialItem;
import com.valeriotor.beyondtheveil.lib.References;
import net.minecraft.client.Minecraft;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.color.item.ItemColors;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import org.jetbrains.annotations.NotNull;

@Mod.EventBusSubscriber(modid = References.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    public static void init(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(Registration.GEAR_BENCH_CONTAINER.get(), GearBenchGui::new);
            ItemBlockRenderTypes.setRenderLayer(Registration.DAMP_CANOPY.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(Registration.DAMP_FILLED_CANOPY.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(Registration.FISH_BARREL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(Registration.IDOL.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(Registration.SLUG_BAIT.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(Registration.LAMP.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(Registration.FUME_SPREADER.get(), type -> type != null && (type.equals(RenderType.solid()) || type.equals(RenderType.translucent())));
            ItemBlockRenderTypes.setRenderLayer(Registration.SLEEP_CHAMBER.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(Registration.GEAR_BENCH.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(Registration.HEART.get(), RenderType.cutout());
//            ItemBlockRenderTypes.setRenderLayer(Registration.WATERY_CRADLE.get(), type -> type != null && (type.equals(RenderType.solid()) || type.equals(RenderType.translucent())));
            ItemBlockRenderTypes.setRenderLayer(Registration.MEMORY_SIEVE.get(), type -> type != null && (type.equals(RenderType.solid()) || type.equals(RenderType.translucent())));
//            ItemBlockRenderTypes.setRenderLayer(Registration.FLASK_LARGE.get(), RenderType.translucent());
//            ItemBlockRenderTypes.setRenderLayer(Registration.FLASK_MEDIUM.get(), RenderType.translucent());
//            ItemBlockRenderTypes.setRenderLayer(Registration.FLASK_SMALL.get(), RenderType.translucent());
//            ResearchRegistryClient.registerConnectionsAndRecipes();
//            MiscModels.createInstance();
            BlockColors blockColors = Minecraft.getInstance().getBlockColors();
            blockColors.register((pState, pLevel, pPos, pTintIndex) -> 0x287082, Registration.MEMORY_SIEVE.get());
            ItemColors itemColors = Minecraft.getInstance().getItemColors();
//            itemColors.register(new MemoryPhialItem.MemoryPhialColor(), Registration.MEMORY_PHIAL.get());
        });

    }


//    @SubscribeEvent
//    public static void onRegisterKeybindings(RegisterKeyMappingsEvent event) {
//        event.register(KeyBindings.reminisce);
//    }


    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(DeepOneModel.LAYER_LOCATION, DeepOneModel::createBodyLayer);
        event.registerLayerDefinition(BloodSkeletonCrawlingModel.LAYER_LOCATION, BloodSkeletonCrawlingModel::createBodyLayer);
        event.registerLayerDefinition(BloodZombieModel.LAYER_LOCATION, BloodZombieModel::createBodyLayer);
        event.registerLayerDefinition(HamletDwellerModel.LAYER_LOCATION, HamletDwellerModel::createBodyLayer);
        event.registerLayerDefinition(FletumModel.LAYER_LOCATION, FletumModel::createBodyLayer);
        event.registerLayerDefinition(CanoeModel.LAYER_LOCATION, CanoeModel::createBodyLayer);
//        event.registerLayerDefinition(BloodWraithModel.LAYER_LOCATION, BloodWraithModel::createBodyLayer);
//        event.registerLayerDefinition(TestNautilus.LAYER_LOCATION, TestNautilus::createBodyLayer);
//        event.registerLayerDefinition(CrawlerModel.LAYER_LOCATION, CrawlerModel::createBodyLayer);
//        event.registerLayerDefinition(WeeperModel.LAYER_LOCATION, WeeperModel::createBodyLayer);



        // RenderLayer models
//        event.registerLayerDefinition(WoundModel.LAYER_LOCATION, WoundModel::createBodyLayer);
//        event.registerLayerDefinition(ChestWoundModel.LAYER_LOCATION, ChestWoundModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.@NotNull RegisterRenderers event) {
        event.registerEntityRenderer(Registration.DEEP_ONE.get(), DeepOneRenderer::new);
        event.registerEntityRenderer(Registration.BLOOD_SKELETON.get(), BloodSkeletonRenderer::new);
        event.registerEntityRenderer(Registration.BLOOD_ZOMBIE.get(), BloodZombieRenderer::new);
        event.registerEntityRenderer(Registration.HAMLET_DWELLER.get(), HamletDwellerRenderer::new);
        event.registerEntityRenderer(Registration.FLETUM.get(), FletumRenderer::new);
        event.registerEntityRenderer(Registration.CANOE.get(), CanoeRenderer::new);
//        event.registerEntityRenderer(Registration.BLOOD_WRAITH.get(), BloodWraithRenderer::new);
//        event.registerEntityRenderer(Registration.NAUTILUS.get(), NautilusRenderer::new);
//        event.registerEntityRenderer(Registration.CRAWLER.get(), CrawlerRenderer::new);
//        event.registerEntityRenderer(Registration.WEEPER.get(), WeeperRenderer::new);

        event.registerBlockEntityRenderer(Registration.HEART_BE.get(), HeartBER::new);
        event.registerBlockEntityRenderer(Registration.MEMORY_SIEVE_BE.get(), MemorySieveBER::new);
//        event.registerBlockEntityRenderer(Registration.WATERY_CRADLE_BE.get(), WateryCradleBER::new);
//        event.registerBlockEntityRenderer(Registration.FLASK_SHELF_BE.get(), FlaskShelfBER::new);
//        event.registerBlockEntityRenderer(Registration.SURGERY_BED_BE.get(), SurgeryBedBER::new);
//        event.registerBlockEntityRenderer(Registration.FLASK_BE.get(), FlaskBER::new);
    }

    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.AddLayers event) {

    }

    @SubscribeEvent
    public static void onEntityRenderersEvent(EntityRenderersEvent.AddLayers event) {
        AnimationRegistry.loadAnimations(true);
    }

    public static boolean isConnectionPresent() {
        return Minecraft.getInstance().getConnection() != null && Minecraft.getInstance().getConnection().getConnection() != null;
    }

    //@SubscribeEvent
    //public static void onModelRegistryEvent(ModelRegistryEvent event) {
    //    //ModelLoaderRegistry.registerLoader(FlaskModelLoader.FLASK_LOADER, new FlaskModelLoader());
    //    //ModelLoaderRegistry.registerLoader(FlaskShelfModelLoader.FLASK_SHELF_LOADER, new FlaskShelfModelLoader());
    //}

    //@SubscribeEvent
    //public static void onModelBakeEvent(ModelBakeEvent event) {
    //    OrphanLoaderRegistry.cacheBlockModel("flask_large");
    //    OrphanLoaderRegistry.bakeModels(event);
    //}

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event){
        ItemBlockRenderTypes.setRenderLayer(Registration.SOURCE_FLUID_TEARS.get(),RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registration.FLOWING_FLUID_TEARS.get(), RenderType.translucent());
    }


}