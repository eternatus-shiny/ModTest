package com.eternatus.modtest;

import com.google.gson.Gson;
import com.mojang.serialization.Codec;
import com.eternatus.modtest.client.ClientSetup;
import com.eternatus.modtest.event.HamletDwellerEvent;
import com.eternatus.modtest.event.ModEvents;
import com.eternatus.modtest.lib.BiomeConfig;
import com.eternatus.modtest.lib.ConfigLib;
import com.eternatus.modtest.lib.References;
import com.eternatus.modtest.networking.Messages.UpdateCaveBiomeMapTagMessage;
import com.eternatus.modtest.networking.Messages.UpdateItemTagMessage;
import com.eternatus.modtest.networking.Messages.WorldEventMessage;
import com.eternatus.modtest.networking.proxy.CommonProxy;
import com.eternatus.modtest.networking.proxy.ClientProxy;
import com.eternatus.modtest.util.ExpandedBiomeSource;
import com.eternatus.modtest.util.ExpandedBiomes;
import com.eternatus.modtest.util.VillageHouseManager;
import com.eternatus.modtest.world.biome.MTBiomeRarity;
import com.eternatus.modtest.world.biome.MTBiomeRegistry;
import com.eternatus.modtest.world.carver.MTCarverRegistry;
import com.eternatus.modtest.world.feature.MTFeatureRegistry;
import com.eternatus.modtest.world.worldgen.surface.MTSurfaceRules;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.event.server.ServerAboutToStartEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.server.ServerLifecycleHooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Mod(References.MODID)
public class ModTest{
    public static final Logger LOGGER = LogManager.getLogger("modtest");
    public static final Gson GSON = new Gson();
    public static final String MODID = "";
    private static final String PROTOCOL_VERSION = Integer.toString(1);
    private static final ResourceLocation PACKET_NETWORK_NAME = new ResourceLocation("beyondtheveil:main_channel");
    public static CommonProxy PROXY = DistExecutor.runForDist(() -> ClientProxy::new, () -> CommonProxy::new);
    public static final SimpleChannel NETWORK_WRAPPER = NetworkRegistry.ChannelBuilder
            .named(PACKET_NETWORK_NAME)
            .clientAcceptedVersions(PROTOCOL_VERSION::equals)
            .serverAcceptedVersions(PROTOCOL_VERSION::equals)
            .networkProtocolVersion(() -> PROTOCOL_VERSION)
            .simpleChannel();

    public BeyondTheVeil(){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        new ConfigLib();
        Registration.init();
	    FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
	    // Register the enqueueIMC method for modloading
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        final DeferredRegister<Codec<? extends BiomeModifier>> serializers = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, References.MODID);
        serializers.register(bus);
        bus.addListener(this::clientSetup);
        bus.addListener(this::loadConfig);
        bus.addListener(this::reloadConfig);
        MTFeatureRegistry.DEF_REG.register(bus);
        MTCarverRegistry.DEF_REG.register(bus);
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ModEvents());
        MinecraftForge.EVENT_BUS.register(PROXY);
        PROXY.commonInit();
        MTBiomeRegistry.init();

        //CONFIG
//        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigLib.SPEC, References.MODID + "-common.toml");

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modbus.addListener(ClientSetup::init));
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::loadComplete);

    }
    private void setup(final FMLCommonSetupEvent event){
        // some preinit code
        int packetsRegistered = 0;
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, UpdateItemTagMessage.class, UpdateItemTagMessage::write, UpdateItemTagMessage::read, UpdateItemTagMessage::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, UpdateCaveBiomeMapTagMessage.class, UpdateCaveBiomeMapTagMessage::write, UpdateCaveBiomeMapTagMessage::read, UpdateCaveBiomeMapTagMessage::handle);
        NETWORK_WRAPPER.registerMessage(packetsRegistered++, WorldEventMessage.class, WorldEventMessage::write, WorldEventMessage::read, WorldEventMessage::handle);
        event.enqueueWork(() ->{
            MTSurfaceRules.setup();
        });
    }
    private void enqueueIMC(final InterModEnqueueEvent event){
        // Some example code to dispatch IMC to another mod
        InterModComms.sendTo("examplemod", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    private void clientSetup(final FMLClientSetupEvent event) {
        event.enqueueWork(() -> PROXY.clientInit());
    }
    public static <MSG> void sendMSGToAll(MSG message) {
        for (ServerPlayer player : ServerLifecycleHooks.getCurrentServer().getPlayerList().getPlayers()) {
            sendNonLocal(message, player);
        }
    }
    public static <MSG> void sendNonLocal(MSG msg, ServerPlayer player) {
        NETWORK_WRAPPER.sendTo(msg, player.connection.connection, NetworkDirection.PLAY_TO_CLIENT);
    }
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event){
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }
    private void loadComplete(final FMLLoadCompleteEvent event) {
        MinecraftForge.EVENT_BUS.register(new HamletDwellerEvent());
    }

    private void reloadConfig(final ModConfigEvent.Reloading event) {
        BiomeConfig.reloadConfig();
    }

    private void loadConfig(final ModConfigEvent.Loading event) {
        BiomeConfig.reloadConfig();
    }

    @SubscribeEvent
    public void onServerAboutToStart(ServerAboutToStartEvent event) {
        ModTest.LOGGER.info("Init MTBiomeRarity");
        MTBiomeRarity.init();
        RegistryAccess registryAccess = event.getServer().registryAccess();
        VillageHouseManager.addAllHouses(registryAccess);
        Registry<Biome> allBiomes = registryAccess.registryOrThrow(Registry.BIOME_REGISTRY);
        Map<ResourceKey<Biome>, Holder<Biome>> biomeMap = new HashMap<>();
        for(ResourceKey<Biome> biomeResourceKey : allBiomes.registryKeySet()){
            Optional<Holder<Biome>> holderOptional = allBiomes.getHolder(biomeResourceKey);
            holderOptional.ifPresent(biomeHolder -> biomeMap.put(biomeResourceKey, biomeHolder));
        }
        for (Map.Entry<ResourceKey<LevelStem>, LevelStem> entry : event.getServer().getWorldData().worldGenSettings().dimensions().entrySet()) {
            if(entry.getValue().generator().getBiomeSource() instanceof ExpandedBiomeSource expandedBiomeSource){
                expandedBiomeSource.setResourceKeyMap(biomeMap);
                Set<Holder<Biome>> biomeHolders = ExpandedBiomes.buildBiomeList(registryAccess, entry.getKey());
                expandedBiomeSource.expandBiomesWith(biomeHolders);
            }
        }
    }
}
