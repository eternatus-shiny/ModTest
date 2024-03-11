package com.eternatus.modtest;

import com.mojang.math.Vector3f;
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
  //          BIOMES
  //###################################################################################################################################################################
    public static final ResourceKey<Biome> INNSMOUTH = ResourceKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(References.MODID, "innsmouth"));
  
  private static TagKey<Biome> registerBiomeTag(String name) {
    return TagKey.create(Registry.BIOME_REGISTRY, new ResourceLocation(References.MODID, name));
  }
  private static TagKey<Item> registerItemTag(String name) {
    return ItemTags.create(new ResourceLocation(References.MODID,name));
  }

}
