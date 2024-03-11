package com.valeriotor.beyondtheveil;



import com.mojang.math.Vector3f;
import com.valeriotor.beyondtheveil.block.*;
import com.valeriotor.beyondtheveil.block.fluids.Tears_type;
import com.valeriotor.beyondtheveil.container.GearBenchContainer;
import com.valeriotor.beyondtheveil.crafting.GearBenchRecipeRegistry;
import com.valeriotor.beyondtheveil.entity.*;
import com.valeriotor.beyondtheveil.item.*;
import com.valeriotor.beyondtheveil.lib.References;
import com.valeriotor.beyondtheveil.tile.*;
import com.valeriotor.beyondtheveil.potions.*;
import net.minecraft.core.Registry;
import net.minecraft.data.tags.BiomeTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.*;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Registration{

  private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, References.MODID);
  public static final DeferredRegister<FluidType> FLUID_TYPE = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, References.MODID);
  private static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, References.MODID);
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, References.MODID);
  private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, References.MODID);
  private static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, References.MODID);
  private static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, References.MODID);
  private static final DeferredRegister<SoundEvent> SOUND_EVENT_DEFERRED_REGISTER = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, References.MODID);
  private static final DeferredRegister<Potion> POTIONS = DeferredRegister.create(ForgeRegistries.POTIONS,References.MODID);
  public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, References.MODID);

  public static final CreativeModeTab TAB = new CreativeModeTab(References.MODID) {
    @Override
    public ItemStack makeIcon() {
      return new ItemStack(IDOL.get());
    }
  };

  public static void init(){
    IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
    BLOCKS.register(bus);
    FLUID_TYPE.register(bus);
    FLUIDS.register(bus);
    ITEMS.register(bus);
    BLOCK_ENTITIES.register(bus);
    CONTAINERS.register(bus);
    ENTITIES.register(bus);
    SOUND_EVENT_DEFERRED_REGISTER.register(bus);
    GearBenchRecipeRegistry.register(bus);
    MOB_EFFECTS.register(bus);
    POTIONS.register(bus);
  }

  //###################################################################################################################################################################
  //          TAGS
  //###################################################################################################################################################################
  public static final TagKey<Biome> HAS_NO_ANCIENT_CITIES_IN = registerBiomeTag("has_no_ancient_cities_in");
  public static final TagKey<Biome> OVERRIDE_UNDERWATER_AMBIENCE_IN = registerBiomeTag("override_underwater_ambience_in");
  public static final TagKey<Biome> ONLY_AMBIENT_LOOP_UNDERWATER = registerBiomeTag("only_ambient_loop_underwater");
  public static final TagKey<Biome> CAVE_MAP_BORDER_ON = registerBiomeTag("cave_map_border_on");
  public static final TagKey<Biome> OVERRIDE_ALL_VANILLA_MUSIC_IN = registerBiomeTag("override_all_vanilla_music_in");
  public static final TagKey<Biome> BEYONDTHEVEIL_BIOMES = registerBiomeTag("beyondtheveil_biomes");
  public static final TagKey<Item> RESTRICTED_BIOME_LOCATORS = registerItemTag("restricted_biome_locators");
  //###################################################################################################################################################################
  //          MOB EFFECTS
  //###################################################################################################################################################################
  public static final RegistryObject<MobEffect> TERROR = MOB_EFFECTS.register("terror", TerrorMobEffect::new);
  public static final RegistryObject<MobEffect> HEARTBREAK = MOB_EFFECTS.register("heartbreak",HeartBreakMobEffect::new);
  public static final RegistryObject<MobEffect> FOLLY = MOB_EFFECTS.register("folly",FollyMobEffect::new);

  //###################################################################################################################################################################
  //          FOOD PROPERTIES
  //###################################################################################################################################################################
  public static final FoodProperties DRINK = new FoodProperties.Builder().alwaysEat().effect(()-> new MobEffectInstance(MobEffects.CONFUSION, 3000),1).build();

  //###################################################################################################################################################################
  //          SOUNDS
  //###################################################################################################################################################################
      public static final RegistryObject<SoundEvent> BENEATH_THE_SURFACE = registerSoundEvent("beneath_the_surface");
      public static final RegistryObject<SoundEvent> DWELLER_HURT = registerSoundEvent("dweller_hurt");
      public static final RegistryObject<SoundEvent> DWELLER_IDLE = registerSoundEvent("dweller_idle");
      public static final RegistryObject<SoundEvent> FLETUM_WEEPING = registerSoundEvent("fletum_weeping");

  //###################################################################################################################################################################
  //          BIOMES
  //###################################################################################################################################################################
    public static final ResourceKey<Biome> INNSMOUTH = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(References.MODID, "innsmouth"));
  //###################################################################################################################################################################
  //          ENTITYS
  //###################################################################################################################################################################
    public static final RegistryObject<EntityType<DeepOneEntity>> DEEP_ONE = ENTITIES.register("deep_one",()->EntityType.Builder.of(DeepOneEntity::new,MobCategory.MONSTER).sized(0.6F,2.1F).clientTrackingRange(32).build("deep_one"));
    public static final RegistryObject<EntityType<BloodSkeletonEntity>> BLOOD_SKELETON = ENTITIES.register("blood_skeleton",()->EntityType.Builder.of(BloodSkeletonEntity::new,MobCategory.MONSTER).sized(0.7F,5F).clientTrackingRange(32).build("blood_skeleton"));
    public static final RegistryObject<EntityType<BloodZombieEntity>> BLOOD_ZOMBIE = ENTITIES.register("blood_zombie",()->EntityType.Builder.of(BloodZombieEntity::new,MobCategory.MONSTER).sized(0.7F,5F).clientTrackingRange(32).build("blood_zombie"));
    //public static final RegistryObject<EntityType<BloodWraithEntity>> BLOOD_WRAITH = ENTITIES.register("blood_wraith",()->EntityType.Builder.of(BloodWraithEntity::new,MobCategory.MONSTER).sized(0.7F,5F).clientTrackingRange(32).build("blood_wraith"));
    //public static final RegistryObject<EntityType<NautilusEntity>> NAUTILUS = ENTITIES.register("nautilus",()->EntityType.Builder.of(NautilusEntity::new,MobCategory.MISC).sized(7,5F).clientTrackingRange(32).build("nautilus"));
    //public static final RegistryObject<EntityType<CrawlerEntity>> CRAWLER = ENTITIES.register("crawler",()->EntityType.Builder.of(CrawlerEntity::new,MobCategory.CREATURE).sized(1.2F,0.7F).clientTrackingRange(32).build("crawler"));
    public static final RegistryObject<EntityType<WeeperEntity>> Weeper = ENTITIES.register("weeper",()-> {
        EntityType.Builder<WeeperEntity> weeperEntityBuilder = EntityType.Builder.of(WeeperEntity::new, MobCategory.CREATURE);
        weeperEntityBuilder.sized(1.8F, 0.7F);
        weeperEntityBuilder.clientTrackingRange(32);
        return weeperEntityBuilder.build("weeper");
    });
    //public static final RegistryObject<EntityType<HamletDwellerEntity>> HAMLET_DWELLER = ENTITIES.register("hamlet_dweller",()->EntityType.Builder.of(HamletDwellerEntity::new, MobCategory.CREATURE).sized(1.8F, 0.7F).clientTrackingRange(32).build("hamlet_dweller"));
    public static final RegistryObject<EntityType<HamletDwellerEntity>> HAMLET_DWELLER = ENTITIES.register("hamlet_dweller", () -> EntityType.Builder.of(HamletDwellerEntity::new,
            MobCategory.MISC).sized(0.6F, 1.95F).clientTrackingRange(10).build("hamlet_dweller"));

  public static final RegistryObject<EntityType<FletumEntity>> FLETUM = ENTITIES.register("fletum", () -> EntityType.Builder.of(FletumEntity::new,
          MobCategory.CREATURE).sized(0.8F, 0.7F).clientTrackingRange(10).build("fletum"));

  public static final RegistryObject<EntityType<CanoeEntity>> CANOE = ENTITIES.register("canoe", () -> EntityType.Builder.<CanoeEntity>of(CanoeEntity::new,
          MobCategory.MISC).sized(2.5F,  0.5625F).clientTrackingRange(10).setCustomClientFactory(CanoeEntity::new).build("canoe"));
  //###################################################################################################################################################################
  //          BLOCKS
  //###################################################################################################################################################################

  public static final BlockBehaviour.Properties DAMP_WOOD_PROPERTIES = BlockBehaviour.Properties.of(Material.WOOD).strength(2f).sound(SoundType.WOOD);
  public static final RegistryObject<Block> DAMP_WOOD = BLOCKS.register("damp_wood", () -> new Block(DAMP_WOOD_PROPERTIES));
  public static final RegistryObject<RotatedPillarBlock> DAMP_LOG = BLOCKS.register("damp_log", () -> new RotatedPillarBlock(DAMP_WOOD_PROPERTIES));
  public static final RegistryObject<StairBlock> DAMP_WOOD_STAIR = BLOCKS.register("damp_wood_stair", () -> new StairBlock(() -> DAMP_WOOD.get().defaultBlockState(), DAMP_WOOD_PROPERTIES));
  public static final RegistryObject<FenceBlock> DAMP_WOOD_FENCE = BLOCKS.register("damp_wood_fence", () -> new FenceBlock(DAMP_WOOD_PROPERTIES));
  public static final RegistryObject<SlabBlock> DAMP_WOOD_SLAB = BLOCKS.register("damp_wood_slab", () -> new SlabBlock(DAMP_WOOD_PROPERTIES));
  public static final RegistryObject<Block> FISH_BARREL = BLOCKS.register("fish_barrel", () -> new FishBarrelBlock(DAMP_WOOD_PROPERTIES));
  public static final RegistryObject<Block> SLUG_BAIT = BLOCKS.register("slug_bait", () -> new SlugBaitBlock(DAMP_WOOD_PROPERTIES));

  public static final BlockBehaviour.Properties DAMP_CANOPY_PROPERTIES = BlockBehaviour.Properties.of(Material.WOOD).strength(2f).sound(SoundType.WOOD).noOcclusion();
  public static final RegistryObject<Block> DAMP_CANOPY = BLOCKS.register("damp_canopy", () -> new DampCanopyBlock(DAMP_CANOPY_PROPERTIES));
  public static final RegistryObject<Block> DAMP_FILLED_CANOPY = BLOCKS.register("damp_filled_canopy", () -> new DampFilledCanopyBlock(DAMP_CANOPY_PROPERTIES));

  public static final BlockBehaviour.Properties DARK_SAND_PROPERTIES = BlockBehaviour.Properties.of(Material.SAND).strength(3f).sound(SoundType.SAND);
  public static final RegistryObject<Block> DARK_SAND = BLOCKS.register("dark_sand", () -> new Block(DARK_SAND_PROPERTIES));
  public static final RegistryObject<Block> DAMP_STONE = BLOCKS.register("dark_stone", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(3f,6f).sound(SoundType.DEEPSLATE)));

  public static final BlockBehaviour.Properties BRICK_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(3.0f, 7.0f);
  public static final RegistryObject<Block> WORN_BRICKS = BLOCKS.register("worn_bricks", () -> new Block(DAMP_WOOD_PROPERTIES));
  public static final RegistryObject<StairBlock> WORN_BRICK_STAIR = BLOCKS.register("worn_brick_stair", () -> new StairBlock(() -> WORN_BRICKS.get().defaultBlockState(), DAMP_WOOD_PROPERTIES));
  public static final RegistryObject<SlabBlock> WORN_BRICKS_SLAB = BLOCKS.register("worn_bricks_slab", () -> new SlabBlock(DAMP_WOOD_PROPERTIES));
  public static final RegistryObject<WallBlock> WORN_BRICKS_WALL = BLOCKS.register("worn_bricks_wall", () -> new WallBlock(DAMP_WOOD_PROPERTIES));


  public static final RegistryObject<Block> BLOOD_BRICK = BLOCKS.register("blood_brick", () -> new Block(BRICK_PROPERTIES));
  public static final RegistryObject<SlabBlock> BLOOD_BRICK_SLAB = BLOCKS.register("blood_brick_slab", () -> new SlabBlock(BRICK_PROPERTIES));
  public static final RegistryObject<StairBlock> BLOOD_BRICK_STAIR = BLOCKS.register("blood_brick_stair", () -> new StairBlock(() -> BLOOD_BRICK.get().defaultBlockState(), BRICK_PROPERTIES));
  public static final RegistryObject<WallBlock> BLOOD_BRICK_WALL = BLOCKS.register("blood_brick_wall", () -> new WallBlock(BRICK_PROPERTIES));
  public static final RegistryObject<Block> BLOOD_SMOOTH_STONE = BLOCKS.register("blood_smooth_stone", () -> new Block(BRICK_PROPERTIES));
  public static final RegistryObject<SlabBlock> BLOOD_SMOOTH_STONE_SLAB = BLOCKS.register("blood_smooth_stone_slab", () -> new SlabBlock(BRICK_PROPERTIES));
  public static final RegistryObject<StairBlock> BLOOD_SMOOTH_STONE_STAIR = BLOCKS.register("blood_smooth_stone_stair", () -> new StairBlock(() -> BLOOD_SMOOTH_STONE.get().defaultBlockState(), BRICK_PROPERTIES));

  public static final RegistryObject<Block> BLUE_BRICK = BLOCKS.register("blue_brick", () -> new Block(BRICK_PROPERTIES));
  //public static final RegistryObject<Block> WATERY_CRADLE = BLOCKS.register("watery_cradle", () -> new WateryCradleBlock(BRICK_PROPERTIES));
  public static final RegistryObject<Block> SACRIFICE_ALTAR = BLOCKS.register("sacrifice_altar", () -> new SacrificeAltarBlock(BRICK_PROPERTIES));
  public static final RegistryObject<Block> MEMORY_SIEVE = BLOCKS.register("memory_sieve", () -> new MemorySieveBlock(BRICK_PROPERTIES));
  public static final RegistryObject<Block> LACRYMATORY = BLOCKS.register("lacrymatory", ()-> new LacrymatoryBlock(BRICK_PROPERTIES));
  //public static final RegistryObject<Block> FLASK_LARGE = BLOCKS.register("flask_large", () -> new FlaskBlock(BRICK_PROPERTIES, FlaskBlock.FlaskSize.LARGE));
  //public static final RegistryObject<Block> FLASK_MEDIUM = BLOCKS.register("flask_medium", () -> new FlaskBlock(BRICK_PROPERTIES, FlaskBlock.FlaskSize.MEDIUM));
  //public static final RegistryObject<Block> FLASK_SMALL = BLOCKS.register("flask_small", () -> new FlaskBlock(BRICK_PROPERTIES, FlaskBlock.FlaskSize.SMALL));
  //public static final RegistryObject<FlaskShelfBlock> FLASK_SHELF = BLOCKS.register("flask_shelf", () -> new FlaskShelfBlock(BRICK_PROPERTIES));
  //public static final RegistryObject<SurgeryBedBlock> SURGERY_BED = BLOCKS.register("surgery_bed", () -> new SurgeryBedBlock(BRICK_PROPERTIES));
  
  public static final BlockBehaviour.Properties ELDER_BRICK_PROPERTIES = BlockBehaviour.Properties.of(Material.STONE).strength(3.0f, 7.0f);
  public static final RegistryObject<Block> ELDER_STONE_BRICK = BLOCKS.register("elder_stone_brick", () -> new Block(ELDER_BRICK_PROPERTIES));
  public static final RegistryObject<Block> ELDER_STONE_BRICK_CHISELED = BLOCKS.register("elder_stone_brick_chiseled", () -> new Block(ELDER_BRICK_PROPERTIES));
  public static final RegistryObject<WallBlock> ELDER_STONE_BRICK_WALL = BLOCKS.register("elder_stone_brick_wall", () -> new WallBlock(ELDER_BRICK_PROPERTIES));
  public static final RegistryObject<SlabBlock> ELDER_STONE_BRICK_SLAB = BLOCKS.register("elder_stone_brick_slab", () -> new SlabBlock(ELDER_BRICK_PROPERTIES));
  public static final RegistryObject<StairBlock> ELDER_STONE_BRICK_STAIR = BLOCKS.register("elder_stone_brick_stair", () -> new StairBlock(() -> ELDER_STONE_BRICK.get().defaultBlockState(), ELDER_BRICK_PROPERTIES));
  public static final RegistryObject<Block> ELDER_BRICK = BLOCKS.register("elder_brick", () -> new Block(ELDER_BRICK_PROPERTIES));
  public static final RegistryObject<SlabBlock> ELDER_BRICK_SLAB = BLOCKS.register("elder_brick_slab", () -> new SlabBlock(ELDER_BRICK_PROPERTIES));
  public static final RegistryObject<StairBlock> ELDER_BRICK_STAIR = BLOCKS.register("elder_brick_stair", () -> new StairBlock(() -> ELDER_BRICK.get().defaultBlockState(), ELDER_BRICK_PROPERTIES));
  public static final RegistryObject<WallBlock> ELDER_BRICK_WALL = BLOCKS.register("elder_brick_wall", () -> new WallBlock(ELDER_BRICK_PROPERTIES));
  public static final RegistryObject<Block> ELDER_SMOOTH_STONE = BLOCKS.register("elder_smooth_stone", () -> new Block(ELDER_BRICK_PROPERTIES));
  public static final RegistryObject<SlabBlock> ELDER_SMOOTH_STONE_SLAB = BLOCKS.register("elder_smooth_stone_slab", () -> new SlabBlock(ELDER_BRICK_PROPERTIES));
  public static final RegistryObject<StairBlock> ELDER_SMOOTH_STONE_STAIR = BLOCKS.register("elder_smooth_stone_stair", () -> new StairBlock(() -> ELDER_SMOOTH_STONE.get().defaultBlockState(), ELDER_BRICK_PROPERTIES));
/*
  public static final RegistryObject<LiquidBlock> SEDATIVE_BLOCK = BLOCKS.register("sedative_block", () -> new LiquidBlock(Registration.SOURCE_FLUID_SEDATIVE, BlockBehaviour.Properties.copy(Blocks.WATER)));
  public static final RegistryObject<LiquidBlock> SOFTENER_BLOCK = BLOCKS.register("softener_block", () -> new LiquidBlock(Registration.SOURCE_FLUID_SOFTENER, BlockBehaviour.Properties.copy(Blocks.WATER)));
  public static final RegistryObject<LiquidBlock> COAGULANT_BLOCK = BLOCKS.register("coagulant_block", () -> new LiquidBlock(Registration.SOURCE_FLUID_COAGULANT, BlockBehaviour.Properties.copy(Blocks.WATER)));*/
  public static final RegistryObject<LiquidBlock> TEARS_BLOCK = BLOCKS.register("tears_block", () -> new LiquidBlock(Registration.SOURCE_FLUID_TEARS, BlockBehaviour.Properties.copy(Blocks.WATER)));

  public static final RegistryObject<Block> IDOL = BLOCKS.register("idol", () -> new IdolBlock(BlockBehaviour.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).dynamicShape()));
  public static final RegistryObject<Block> LAMP = BLOCKS.register("lamp", () -> new LampBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(2f).lightLevel(state -> 12).sound(SoundType.GLASS)));

  public static final RegistryObject<Block> FUME_SPREADER = BLOCKS.register("fume_spreader", () -> new FumeSpreaderBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(3f).sound(SoundType.GLASS).noOcclusion()));
  public static final RegistryObject<Block> SLEEP_CHAMBER = BLOCKS.register("sleep_chamber", () -> new SleepChamberBlock(BlockBehaviour.Properties.of(Material.HEAVY_METAL).strength(4f, 7f).sound(SoundType.WOOD)));
  public static final RegistryObject<Block> GEAR_BENCH = BLOCKS.register("gear_bench", () -> new GearBenchBlock(BlockBehaviour.Properties.of(Material.DECORATION).strength(4f, 7f).sound(SoundType.WOOD)));
  public static final RegistryObject<Block> HEART = BLOCKS.register("heart", () -> new HeartBlock(BlockBehaviour.Properties.of(Material.FROGLIGHT).strength(0.5F)));
  //###################################################################################################################################################################
  //          ITEMS
  //###################################################################################################################################################################
  public static final RegistryObject<Item> IDOL_ITEM = fromBlock(IDOL);
  public static final RegistryObject<Item> NECRONOMICON = ITEMS.register("necronomicon", NecronomiconItem::new);
  public static final RegistryObject<Item> DAMP_LOG_ITEM = fromBlock(DAMP_LOG);
  public static final RegistryObject<Item> DAMP_WOOD_ITEM = fromBlock(DAMP_WOOD);
  public static final RegistryObject<Item> DAMP_WOOD_STAIRS_ITEM = fromBlock(DAMP_WOOD_STAIR);
  public static final RegistryObject<Item> DAMP_WOOD_SLAB_ITEM = fromBlock(DAMP_WOOD_SLAB);
  public static final RegistryObject<Item> DAMP_WOOD_FENCE_ITEM = fromBlock(DAMP_WOOD_FENCE);
  public static final RegistryObject<Item> FISH_BARREL_ITEM = fromBlock(FISH_BARREL);
  public static final RegistryObject<Item> SLUG_BAIT_ITEM = fromBlock(SLUG_BAIT);

  public static final RegistryObject<Item> DAMP_CANOPY_ITEM = fromBlock(DAMP_CANOPY);
  public static final RegistryObject<Item> DAMP_FILLED_CANOPY_ITEM = fromBlock(DAMP_FILLED_CANOPY);
  
  public static final RegistryObject<Item> DARK_SAND_ITEM = fromBlock(DARK_SAND);
  public static final RegistryObject<Item> DARK_STONE_ITEM = fromBlock(DAMP_STONE);
  
  public static final RegistryObject<Item> WORN_BRICKS_ITEM = fromBlock(WORN_BRICKS);
  public static final RegistryObject<Item> WORN_BRICK_STAIR_ITEM = fromBlock(WORN_BRICK_STAIR);
  public static final RegistryObject<Item> WORN_BRICK_SLAB = fromBlock(WORN_BRICKS_SLAB);
  public static final RegistryObject<Item> WORN_BRICK_WALL = fromBlock(WORN_BRICKS_WALL);
  public static final RegistryObject<Item> BLOOD_BRICK_ITEM = fromBlock(BLOOD_BRICK);
  public static final RegistryObject<Item> BLOOD_BRICK_STAIR_ITEM = fromBlock(BLOOD_BRICK_STAIR);
  public static final RegistryObject<Item> BLOOD_BRICK_SLAB_ITEM = fromBlock(BLOOD_BRICK_SLAB);
  public static final RegistryObject<Item> BLOOD_BRICK_WALL_ITEM = fromBlock(BLOOD_BRICK_WALL);
  public static final RegistryObject<Item> BLOOD_SMOOTH_STONE_ITEM = fromBlock(BLOOD_SMOOTH_STONE);
  public static final RegistryObject<Item> BLOOD_SMOOTH_STONE_STAIR_ITEM = fromBlock(BLOOD_SMOOTH_STONE_STAIR);
  public static final RegistryObject<Item> BLOOD_SMOOTH_STONE_SLAB_ITEM = fromBlock(BLOOD_SMOOTH_STONE_SLAB);

  public static final RegistryObject<Item> BLUE_BRICK_ITEM = fromBlock(BLUE_BRICK);
  public static final RegistryObject<Item> SACRIFICE_ALTAR_ITEM = fromBlock(SACRIFICE_ALTAR);
  public static final RegistryObject<Item> MEMORY_SIEVE_ITEM = fromBlock(MEMORY_SIEVE);
  //public static final RegistryObject<Item> FLASK_LARGE_ITEM = fromBlock(FLASK_LARGE);
  //public static final RegistryObject<Item> FLASK_MEDIUM_ITEM = fromBlock(FLASK_MEDIUM);
  //public static final RegistryObject<Item> FLASK_SMALL_ITEM = fromBlock(FLASK_SMALL);
  //public static final RegistryObject<Item> FLASK_SHELF_ITEM = fromBlock(FLASK_SHELF);
  //public static final RegistryObject<Item> SURGERY_BED_ITEM = fromBlock(SURGERY_BED);
  public static final RegistryObject<Item> LACRYMATORY_ITEM = fromBlock(LACRYMATORY);
  public static final RegistryObject<Item> ELDER_STONE_BRICK_ITEM = fromBlock(ELDER_STONE_BRICK);
  public static final RegistryObject<Item> ELDER_STONE_BRICK_CHISELED_ITEM = fromBlock(ELDER_STONE_BRICK_CHISELED);
  public static final RegistryObject<Item> ELDER_STONE_BRICK_STAIR_ITEM = fromBlock(ELDER_STONE_BRICK_STAIR);
  public static final RegistryObject<Item> ELDER_STONE_BRICK_SLAB_ITEM = fromBlock(ELDER_STONE_BRICK_SLAB);
  public static final RegistryObject<Item> ELDER_STONE_BRICK_WALL_ITEM = fromBlock(ELDER_STONE_BRICK_WALL);
  public static final RegistryObject<Item> ELDER_SMOOTH_STONE_ITEM = fromBlock(ELDER_SMOOTH_STONE);
  public static final RegistryObject<Item> ELDER_SMOOTH_STONE_STAIR_ITEM = fromBlock(ELDER_SMOOTH_STONE_STAIR);
  public static final RegistryObject<Item> ELDER_SMOOTH_STONE_SLAB_ITEM = fromBlock(ELDER_SMOOTH_STONE_SLAB);

  public static final RegistryObject<Item> ELDER_BRICK_ITEM = fromBlock(ELDER_BRICK);
  public static final RegistryObject<Item> ELDER_BRICK_STAIR_ITEM = fromBlock(ELDER_BRICK_STAIR);
  public static final RegistryObject<Item> ELDER_BRICK_SLAB_ITEM = fromBlock(ELDER_BRICK_SLAB);
  public static final RegistryObject<Item> ELDER_BRICK_WALL_ITEM = fromBlock(ELDER_BRICK_WALL);

  public static final Item.Properties ITEM_PROPERTIES = new Item.Properties().tab(TAB);
  public static final Item.Properties FOOD_PROPERTIES = new Item.Properties().tab(TAB).stacksTo(64).food(DRINK);
  public static final RegistryObject<Item> ONIRIC_INCENSE = ITEMS.register("oniric_incense", () -> new Item(ITEM_PROPERTIES));
  public static final RegistryObject<Item> SLUG = ITEMS.register("slug", () -> new Item(ITEM_PROPERTIES));
  public static final RegistryObject<Item> CANOEITEM = ITEMS.register("canoe", () -> new CanoeItem(ITEM_PROPERTIES));
  public static final RegistryObject<Item> RUM = ITEMS.register("drink_rum", () -> new Drink(FOOD_PROPERTIES));
  public static final RegistryObject<Item> WINE = ITEMS.register("drink_wine", () -> new Drink(FOOD_PROPERTIES));
  public static final RegistryObject<Item> ALE = ITEMS.register("drink_ale", () -> new Drink(FOOD_PROPERTIES));
  public static final RegistryObject<Item> VODKA = ITEMS.register("drink_vodka", () -> new Drink(FOOD_PROPERTIES));
  public static final RegistryObject<Item> MEAD = ITEMS.register("drink_mead", () -> new Drink(FOOD_PROPERTIES));
  public static final RegistryObject<Item> CUP = ITEMS.register("drink_empty", () -> new Drink(FOOD_PROPERTIES));
  public static final RegistryObject<Item> FLUTE = ITEMS.register("flute", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> WOLF_MEDALLION = ITEMS.register("wolf_medallion", () -> new Item(ITEM_PROPERTIES));
  public static final RegistryObject<Item> TABLET = ITEMS.register("tablet", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> BRONZE_SPHERE = ITEMS.register("bronze_sphere", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> REDSTONE_WEED_SEED = ITEMS.register("redstone_weed_seed", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> GHOST_WEED_SEED = ITEMS.register("ghost_weed_seed", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> VANILLA_WEED_SEED = ITEMS.register("vanilla_weed_seed", () -> new Item(ITEM_PROPERTIES));
  public static final RegistryObject<Item> SPINE = ITEMS.register("spine", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> HELD_WEEPER = ITEMS.register("held_weeper", () -> new Item(ITEM_PROPERTIES));
  public static final RegistryObject<Item> HELD_FLETUM = ITEMS.register("held_fletum", () -> new HeldFletumItem(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> HELD_SHOGGOTH = ITEMS.register("held_shoggoth", () -> new Item(ITEM_PROPERTIES));
  public static final RegistryObject<Item> SURGERY_TOOLS = ITEMS.register("surgery_tools", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> BONE_TIARA = ITEMS.register("bone_tiara", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> BLEEDING_BELT = ITEMS.register("bleeding_belt", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> DREAM_BOTTLE = ITEMS.register("dream_bottle", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> SHOGGOTH_MAP = ITEMS.register("shoggoth_map", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> BLOOD_COVENANT = ITEMS.register("blood_covenant", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> REVELATION_RING = ITEMS.register("revelation_ring", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> AZACNO_CHARM = ITEMS.register("azacno charm", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> BLOOD_CROWN = ITEMS.register("blood_crown", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> SIGIL_ZOMBIE = ITEMS.register("sigil_zombie", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> SIGIL_SKELLIE = ITEMS.register("sigil_skellie", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> SIGIL_PLAYER = ITEMS.register("sigil_player", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> SIGIL_PATHWAY = ITEMS.register("sigil_pathway", () -> new Item(ITEM_PROPERTIES));
  public static final RegistryObject<Item> SACRIFICIAL_KNIFE = ITEMS.register("sacrificial_knife", () -> new Item(ITEM_PROPERTIES));
  public static final RegistryObject<Item> GEAR = ITEMS.register("gear", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> SURGEON_SUMMONS = ITEMS.register("surgeon_summons", () -> new Item(ITEM_PROPERTIES));
  public static final RegistryObject<Item> CRUCIBLE = ITEMS.register("crucible", () -> new CrucibleItem(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> BLACK_MIRROR = ITEMS.register("black_mirror", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> FLESH_CARBON_TOKEN = ITEMS.register("flesh_carbon_token", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> SCALPEL = ITEMS.register("scalpel", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> FORCEPS = ITEMS.register("forceps", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> TONGS = ITEMS.register("tongs", () -> new Item(ITEM_PROPERTIES));
//  public static final RegistryObject<Item> SEWING_NEEDLE = ITEMS.register("sewing_needle", () -> new Item(ITEM_PROPERTIES));


  //public static final RegistryObject<Item> BLACKJACK = ITEMS.register("blackjack", BlackjackItem::new);
  //public static final RegistryObject<HeldVillagerItem> HELD_VILLAGER = ITEMS.register("held_villager", HeldVillagerItem::new);
  //public static final RegistryObject<Item> CORAL_STAFF = ITEMS.register("coral_staff", CoralStaffItem::new);
  //public static final RegistryObject<Item> MEMORY_PHIAL = ITEMS.register("memory_phial", MemoryPhialItem::new);

  //public static final RegistryObject<Item> SYRINGE = ITEMS.register("syringe", SyringeItem::new);
  public static final RegistryObject<Item> LAMP_ITEM = fromBlock(LAMP);

  public static final RegistryObject<Item> FUME_SPREADER_ITEM = fromBlock(FUME_SPREADER);
  public static final RegistryObject<Item> SLEEP_CHAMBER_ITEM = fromBlock(SLEEP_CHAMBER);
  public static final RegistryObject<Item> GEAR_BENCH_ITEM = fromBlock(GEAR_BENCH);

  public static final RegistryObject<Item> HEART_ITEM = fromBlock(HEART);

  /*
  public static final RegistryObject<Item> SEDATIVE_BUCKET = ITEMS.register("sedative_bucket", () -> new BucketItem(Registration.SOURCE_FLUID_SEDATIVE, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
  public static final RegistryObject<Item> SOFTENER_BUCKET = ITEMS.register("softener_bucket", () -> new BucketItem(Registration.SOURCE_FLUID_SOFTENER, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
  public static final RegistryObject<Item> COAGULANT_BUCKET = ITEMS.register("coagulant_bucket", () -> new BucketItem(Registration.SOURCE_FLUID_COAGULANT, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));*/
  public static final RegistryObject<Item> TEARS_BUCKET = ITEMS.register("tears_bucket", () -> new BucketItem(Registration.SOURCE_FLUID_TEARS, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));
  public static final RegistryObject<Item> CAVE_MAP = ITEMS.register("cave_map", () -> new CaveMapItem(new Item.Properties().stacksTo(1).tab(TAB)));
  public static final RegistryObject<Item> CAVE_MAP_SPRITE = ITEMS.register("cave_map_inventory", () -> new Item(new Item.Properties()));
  public static final RegistryObject<Item> CAVE_MAP_LOADING_SPRITE = ITEMS.register("cave_map_loading", () -> new Item(new Item.Properties()));
  public static final RegistryObject<Item> CAVE_MAP_FILLED_SPRITE = ITEMS.register("cave_map_filled", () -> new Item(new Item.Properties()));


  //###################################################################################################################################################################
  //          SPAWN EGG
  //###################################################################################################################################################################
  public static final Item.Properties SPAWNEGG_PROPERTIES = new Item.Properties().tab(CreativeModeTab.TAB_MISC);
  public static final RegistryObject<ForgeSpawnEggItem> DEEP_ONE_SPAWN_EGG = ITEMS.register("deep_one", () -> new ForgeSpawnEggItem(DEEP_ONE, 0xF52A37, 0x589BCD, SPAWNEGG_PROPERTIES));
  public static final RegistryObject<ForgeSpawnEggItem> BLOOD_SKELETON_SPAWN_EGG = ITEMS.register("blood_skeleton", () -> new ForgeSpawnEggItem(BLOOD_SKELETON, 0xF52A37, 0x589BCD, SPAWNEGG_PROPERTIES));
  public static final RegistryObject<ForgeSpawnEggItem> BLOOD_ZOMBIE_SPAWN_EGG = ITEMS.register("blood_zombie", () -> new ForgeSpawnEggItem(BLOOD_ZOMBIE, 0xF52A37, 0x589BCD, SPAWNEGG_PROPERTIES));
  public static final RegistryObject<ForgeSpawnEggItem> HAMLET_DWELLER_SPAWN_EGG = ITEMS.register("hamlet_dweller", () -> new ForgeSpawnEggItem(HAMLET_DWELLER, 0xF52A37, 0x589BCD, SPAWNEGG_PROPERTIES));
  //public static final RegistryObject<Item> BLOOD_WRAITH_EGG = ITEMS.register("blood_wraith", () -> new ForgeSpawnEggItem(BLOOD_WRAITH, 0xF52A37, 0x589BCD, SPAWNEGG_PROPERTIES));
  public static final RegistryObject<ForgeSpawnEggItem> FLETUM_SPAWN_EGG = ITEMS.register("fletum", () -> new ForgeSpawnEggItem(FLETUM, 0xF52A37, 0x589BCD, SPAWNEGG_PROPERTIES));

  //###################################################################################################################################################################
  //          FLUID_TYPE
  //###################################################################################################################################################################

//  public static final RegistryObject<FluidType> SEDATIVE_FLUID_TYPE = FLUID_TYPE.register("sedative_fluid_type", () -> new SurgicalFluidType(FluidType.Properties.create().lightLevel(2).density(15).viscosity(5), new ResourceLocation(References.MODID, "block/fluids/sedative_still"), new ResourceLocation(References.MODID, "block/fluids/sedative_flow"), new ResourceLocation(References.MODID, "block/fluids/sedative_overlay"), 0xFFC13719));
//  public static final RegistryObject<FluidType> SOFTENER_FLUID_TYPE = FLUID_TYPE.register("softener_fluid_type", () -> new SurgicalFluidType(FluidType.Properties.create().lightLevel(2).density(15).viscosity(5), new ResourceLocation(References.MODID, "block/fluids/softener_still"), new ResourceLocation(References.MODID, "block/fluids/softener_flow"), new ResourceLocation(References.MODID, "block/fluids/softener_overlay"), 0xFFF4C42F));
//  public static final RegistryObject<FluidType> COAGULANT_FLUID_TYPE = FLUID_TYPE.register("coagulant_fluid_type", () -> new SurgicalFluidType(FluidType.Properties.create().lightLevel(2).density(15).viscosity(5), new ResourceLocation(References.MODID, "block/fluids/coagulant_still"), new ResourceLocation(References.MODID, "block/fluids/coagulant_flow"), new ResourceLocation(References.MODID, "block/fluids/coagulant_overlay"), 0xFFBA10CD));
  public static final RegistryObject<FluidType> TEARS_FLUID_TYPE = FLUID_TYPE.register("tears_fluid_type", () -> new Tears_type(new ResourceLocation(References.MODID, "block/fluids/tears_still"), new ResourceLocation(References.MODID, "block/fluids/tears_flow"), new ResourceLocation(References.MODID, "block/fluids/tears_overlay"), 0xFFBA10CD, new Vector3f(),FluidType.Properties.create().lightLevel(2).density(15).viscosity(5)));
//
//  //###################################################################################################################################################################
//  //          FLUID
//  //###################################################################################################################################################################
//
//  public static final RegistryObject<FlowingFluid> SOURCE_FLUID_SEDATIVE = FLUIDS.register("sedative_fluid_source", () -> new ForgeFlowingFluid.Source(Registration.SEDATIVE_PROPERTIES));
//  public static final RegistryObject<FlowingFluid> FLOWING_FLUID_SEDATIVE = FLUIDS.register("sedative_fluid_flowing", () -> new ForgeFlowingFluid.Source(Registration.SEDATIVE_PROPERTIES));
//  public static final ForgeFlowingFluid.Properties SEDATIVE_PROPERTIES = new ForgeFlowingFluid.Properties(Registration.SEDATIVE_FLUID_TYPE, SOURCE_FLUID_SEDATIVE, FLOWING_FLUID_SEDATIVE).slopeFindDistance(2).levelDecreasePerBlock(2).block(Registration.SEDATIVE_BLOCK).bucket(Registration.SEDATIVE_BUCKET);
//
//  public static final RegistryObject<FlowingFluid> SOURCE_FLUID_SOFTENER = FLUIDS.register("softener_fluid_source", () -> new ForgeFlowingFluid.Source(Registration.SOFTENER_PROPERTIES));
//  public static final RegistryObject<FlowingFluid> FLOWING_FLUID_SOFTENER = FLUIDS.register("softener_fluid_flowing", () -> new ForgeFlowingFluid.Flowing(Registration.SOFTENER_PROPERTIES));
//  public static final ForgeFlowingFluid.Properties SOFTENER_PROPERTIES = new ForgeFlowingFluid.Properties(Registration.SOFTENER_FLUID_TYPE, SOURCE_FLUID_SOFTENER, FLOWING_FLUID_SOFTENER).slopeFindDistance(2).levelDecreasePerBlock(2).block(Registration.SOFTENER_BLOCK).bucket(Registration.SOFTENER_BUCKET);
//
//  public static final RegistryObject<FlowingFluid> SOURCE_FLUID_COAGULANT = FLUIDS.register("coagulant_fluid_source", () -> new ForgeFlowingFluid.Source(Registration.COAGULANT_PROPERTIES));
//  public static final RegistryObject<FlowingFluid> FLOWING_FLUID_COAGULANT = FLUIDS.register("coagulant_fluid_flowing", () -> new ForgeFlowingFluid.Flowing(Registration.COAGULANT_PROPERTIES));
//  public static final ForgeFlowingFluid.Properties COAGULANT_PROPERTIES = new ForgeFlowingFluid.Properties(Registration.COAGULANT_FLUID_TYPE, SOURCE_FLUID_COAGULANT, FLOWING_FLUID_COAGULANT).slopeFindDistance(2).levelDecreasePerBlock(2).block(Registration.COAGULANT_BLOCK).bucket(Registration.COAGULANT_BUCKET);
//
  public static final RegistryObject<FlowingFluid> SOURCE_FLUID_TEARS = FLUIDS.register("tears_fluid_source", () -> new ForgeFlowingFluid.Source(Registration.TEARS_PROPERTIES));
  public static final RegistryObject<FlowingFluid> FLOWING_FLUID_TEARS = FLUIDS.register("tears_fluid_flowing", () -> new ForgeFlowingFluid.Flowing(Registration.TEARS_PROPERTIES));
  public static final ForgeFlowingFluid.Properties TEARS_PROPERTIES = new ForgeFlowingFluid.Properties(Registration.TEARS_FLUID_TYPE, SOURCE_FLUID_TEARS, FLOWING_FLUID_TEARS).slopeFindDistance(2).levelDecreasePerBlock(2).block(Registration.TEARS_BLOCK).bucket(Registration.TEARS_BUCKET);

  //###################################################################################################################################################################
  //          TILE_ENTITY
  //###################################################################################################################################################################
  public static final RegistryObject<BlockEntityType<GearBenchBE>> GEAR_BENCH_BE = BLOCK_ENTITIES.register(GEAR_BENCH.getId().getPath(), () -> BlockEntityType.Builder.of(GearBenchBE::new, GEAR_BENCH.get()).build(null));
  public static final RegistryObject<BlockEntityType<SlugBaitBE>> SLUG_BAIT_BE = BLOCK_ENTITIES.register(SLUG_BAIT.getId().getPath(), () -> BlockEntityType.Builder.of(SlugBaitBE::new, SLUG_BAIT.get()).build(null));
  public static final RegistryObject<BlockEntityType<HeartBE>> HEART_BE = BLOCK_ENTITIES.register(HEART.getId().getPath(), () -> BlockEntityType.Builder.of(HeartBE::new, HEART.get()).build(null));
  public static final RegistryObject<BlockEntityType<FumeSpreaderBE>> FUME_SPREADER_BE = BLOCK_ENTITIES.register(FUME_SPREADER.getId().getPath(), () -> BlockEntityType.Builder.of(FumeSpreaderBE::new, FUME_SPREADER.get()).build(null));
  public static final RegistryObject<BlockEntityType<MemorySieveBE>> MEMORY_SIEVE_BE = BLOCK_ENTITIES.register(MEMORY_SIEVE.getId().getPath(), () -> BlockEntityType.Builder.of(MemorySieveBE::new, MEMORY_SIEVE.get()).build(null));
  //public static final RegistryObject<BlockEntityType<WateryCradleBE>> WATERY_CRADLE_BE = BLOCK_ENTITIES.register(WATERY_CRADLE.getId().getPath(), () -> BlockEntityType.Builder.of(WateryCradleBE::new, WATERY_CRADLE.get()).build(null));
  public static final RegistryObject<BlockEntityType<LacrymatoryBE>> LACRYMATORY_BE = BLOCK_ENTITIES.register(LACRYMATORY.getId().getPath(), () -> BlockEntityType.Builder.of(LacrymatoryBE::new, LACRYMATORY.get()).build(null));

  //###################################################################################################################################################################
  //          CONTAINERS
  //###################################################################################################################################################################
  public static final RegistryObject<MenuType<GearBenchContainer>> GEAR_BENCH_CONTAINER = CONTAINERS.register(GEAR_BENCH.getId().getPath(), () -> IForgeMenuType.create((windowId, inv, data) -> new GearBenchContainer(windowId, data.readBlockPos(), inv, inv.player)));

  /*public static final RegistryObject<CreativeModeTab> TAB_ = CREATIVE_TAB.register("items", () -> CreativeModeTab.builder().icon(() -> new ItemStack(BLACK_MIRROR.get())).title(Component.translatable("creative_tab.beyondtheveil")).displayItems((features, output) -> {
                output.accept(MEMORY_SIEVE.get());
                output.accept(FLASK_LARGE.get());
                output.accept(FLASK_MEDIUM.get());
                output.accept(FLASK_SMALL.get());

                output.accept(DAMP_WOOD.get());
                output.accept(DARK_SAND.get());
                output.accept(DAMP_LOG.get());
                output.accept(DAMP_WOOD_STAIRS.get());
                output.accept(DAMP_CANOPY.get());
                output.accept(DAMP_FILLED_CANOPY.get());
                output.accept(DAMP_WOOD_FENCE.get()); 
                output.accept(WORN_BRICKS.get());
                output.accept(WORN_BRICK_STAIRS.get());
                output.accept(WORN_BRICKS_SLAB.get());
                output.accept(WORN_BRICKS_WALL.get());
                output.accept(ELDER_STONE_BRICK.get());
                output.accept(ELDER_STONE_BRICK_CHISELED.get());
                output.accept(ELDER_STONE_BRICK_SLAB.get());
                output.accept(ELDER_STONE_BRICK_STAIRS.get());
                output.accept(ELDER_STONE_BRICK_WALL.get());
                output.accept(ELDER_BRICK.get());
                output.accept(ELDER_BRICK_SLAB.get());
                output.accept(ELDER_BRICK_STAIRS.get());
                output.accept(ELDER_BRICK_WALL.get());
                output.accept(ELDER_SMOOTH_STONE.get());
                output.accept(ELDER_SMOOTH_STONE_SLAB.get());
                output.accept(BLOOD_BRICK.get());
                output.accept(BLOOD_BRICK_STAIRS.get());
                output.accept(BLOOD_BRICK_SLAB.get());
                output.accept(BLOOD_BRICK_SLAB.get());
                output.accept(BLOOD_BRICK_STAIRS.get());
                output.accept(BLOOD_BRICK_WALL.get());
                output.accept(BLOOD_SMOOTH_STONE.get());
                output.accept(BLOOD_SMOOTH_STONE_SLAB.get());
                output.accept(BLUE_BRICKS.get());
                output.accept(IDOL.get());
                output.accept(FISH_BARREL.get());
                output.accept(SLUG_BAIT.get());
                output.accept(LAMP.get());

                output.accept(FUME_SPREADER.get());
                output.accept(SLEEP_CHAMBER.get());
                output.accept(GEAR_BENCH.get());
                output.accept(WATERY_CRADLE.get());
                output.accept(FLASK_SHELF.get());
                output.accept(SURGERY_BED.get());

                output.accept(SACRIFICE_ALTAR.get());

                output.accept(HEART.get());

                output.accept(ONIRIC_INCENSE.get());
                output.accept(SLUG_CATCHER.get());
                output.accept(SLUG.get());
                output.accept(CANOE.get());
                output.accept(RUM.get());
                output.accept(WINE.get());
                output.accept(ALE.get());
                output.accept(VODKA.get());
                output.accept(MEAD.get());
                output.accept(CUP.get());
                output.accept(FLUTE.get());

                output.accept(WOLF_MEDALLION.get());
                output.accept(TABLET.get());
                output.accept(BRONZE_SPHERE.get());
                output.accept(REDSTONE_WEED_SEEDS.get());
                output.accept(GHOST_WEED_SEEDS.get());
                output.accept(VANILLA_WEED_SEEDS.get());
                output.accept(BLACKJACK.get());
                output.accept(SPINE.get());
                output.accept(HELD_VILLAGER.get());
                output.accept(HELD_WEEPER.get());
                output.accept(HELD_FLETUM.get());
                output.accept(HELD_SHOGGOTH.get());
                output.accept(SURGERY_TOOLS.get());
                output.accept(BONE_TIARA.get());
                output.accept(BLEEDING_BELT.get());
                output.accept(DREAM_BOTTLE.get());
                output.accept(SHOGGOTH_MAP.get());
                output.accept(BLOOD_COVENANT.get());
                output.accept(REVELATION_RING.get());
                output.accept(AZACNO_CHARM.get());
                output.accept(BLOOD_CROWN.get());
                output.accept(CORAL_STAFF.get());
                output.accept(SIGIL_ZOMBIE.get());
                output.accept(SIGIL_SKELLIE.get());
                output.accept(SIGIL_PLAYER.get());
                output.accept(SIGIL_PATHWAY.get());
                output.accept(SACRIFICIAL_KNIFE.get());
                output.accept(MEMORY_PHIAL.get());
                output.accept(NECRONOMICON.get());
                output.accept(GEAR.get());
                output.accept(SURGEON_SUMMONS.get());
                output.accept(CRUCIBLE.get());
                output.accept(BLACK_MIRROR.get());
                output.accept(FLESH_CARBON_TOKEN.get());
                output.accept(SYRINGE.get());
                output.accept(SCALPEL.get());
                output.accept(FORCEPS.get());
                output.accept(TONGS.get());
                output.accept(SEWING_NEEDLE.get());

                output.accept(SEDATIVE_BUCKET.get());
                output.accept(SOFTENER_BUCKET.get());
                output.accept(COAGULANT_BUCKET.get());

                output.accept(DEEP_ONE_EGG.get());
                output.accept(BLOOD_SKELETON_EGG.get());
                output.accept(BLOOD_ZOMBIE_EGG.get());
                output.accept(BLOOD_WRAITH_EGG.get());

    }).build());*/

  private static <B extends Block> RegistryObject<Item> fromBlock(RegistryObject<B> blockObject) {
        return ITEMS.register(blockObject.getId().getPath(), () -> new BlockItem(blockObject.get(), ITEM_PROPERTIES));
  }
  private static RegistryObject<SoundEvent> registerSoundEvent(String name){
    ResourceLocation id = new ResourceLocation(References.MODID, name);
    return  SOUND_EVENT_DEFERRED_REGISTER.register(name, () -> new SoundEvent(id));
  }

  /** Used for terror and possibly other effects.
   *
   */
//  public static boolean isScaryEntity(LivingEntity e) {
//    if(e instanceof Player && ((Player)e).getCapability(PlayerDataProvider.PLAYER_DATA,null).resolve(PlayerDataLib.TRANSFORMED)); return true;
//    return e instanceof DeepOneEntity /*|| e instanceof StarspawnEntity || e instanceof ShoggothEntity */|| e instanceof BloodZombieEntity || e instanceof BloodSkeletonEntity /*|| e instanceof CrazedWeeperEntity*/;
//  }

  /** Used for terror and possibly other effects.
   *
   */
  public static boolean isFearlessEntity(LivingEntity e) {
    return
            e instanceof DeepOneEntity ||
            e instanceof WeeperEntity ||
            e instanceof FletumEntity ||
            /*e instanceof StarspawnEntity ||
            e instanceof ShoggothEntity ||*/
            e instanceof BloodZombieEntity /*||
            e instanceof CrazedWeeperEntity ||
            e instanceof IctyaEntity*/;
  }


  public static boolean isHostileEntity(LivingEntity ent) {
    if(ent.getMobType()== MobType.UNDEAD) return true;
    if(ent instanceof LivingEntity) {
      return true;
    }
    return false;
  }

  private static TagKey<Biome> registerBiomeTag(String name) {
    return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(References.MODID, name));
  }
  private static TagKey<Item> registerItemTag(String name) {
    return ItemTags.create(new ResourceLocation(References.MODID,name));
  }

}
