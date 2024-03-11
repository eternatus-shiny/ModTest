package com.eternatus.modtest.networking.proxy;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.UUID;

public class CommonProxy {
    public void commonInit() {

    }
    public void clientInit() {
    }

    public void blockRenderingEntity(UUID id) {
    }

    public void releaseRenderingEntity(UUID id) {
    }

    public void setVisualFlag(int flag) {
    }

    public void playWorldEvent(int messageId, Level level, BlockPos blockPos) {
    }
    public void playWorldSound(@Nullable Object soundEmitter, byte type) {

    }
    public void setRenderViewEntity(Player player, Entity entity) {
    }

    public void resetRenderViewEntity(Player player) {
    }
    public Object getISTERProperties() {
        return null;
    }
    public Player getClientSidePlayer() {
        return null;
    }
    public float getPartialTicks() {
        return 1.0F;
    }
}
