package com.eternatus.modtest.mixin.client;

import com.eternatus.modtest.Registration;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.Holder;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.Musics;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.biome.Biome;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import javax.annotation.Nullable;

@Mixin(value = Minecraft.class, priority = -100)
public abstract class MinecraftMixin {

    @Shadow @Nullable public LocalPlayer player;

    @Shadow @Final public Gui gui;

    @Inject(method = "Lnet/minecraft/client/Minecraft;getSituationalMusic()Lnet/minecraft/sounds/Music;",
            at = @At("HEAD"),
            cancellable = true)
    private void ac_getSituationalMusic(CallbackInfoReturnable<Music> cir) {
        if(this.player != null){
            Holder<Biome> holder = this.player.getLevel().getBiome(this.player.blockPosition());
            if(holder.is(Registration.OVERRIDE_ALL_VANILLA_MUSIC_IN)){
                cir.setReturnValue(holder.value().getBackgroundMusic().orElse(Musics.GAME));
            }
        }
    }
}
