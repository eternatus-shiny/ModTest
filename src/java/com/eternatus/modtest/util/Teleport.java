package com.valeriotor.beyondtheveil.util;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;

public class Teleport {

    private final ServerLevel world;
    private final double x;
    private final double y;
    private final double z;

    public Teleport(ServerLevel worldIn, double posX, double posY, double posZ) {
        this.world = worldIn;
        this.x = posX;
        this.y = posY;
        this.z = posZ;
    }



    public void placeInPortal(Entity entityIn, float rotationYaw) {
        entityIn.setPos(x, y, z);
        entityIn.setDeltaMovement(0,0,0);
    }
}
