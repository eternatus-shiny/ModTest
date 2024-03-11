package com.valeriotor.beyondtheveil.client.render.item;

import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;

public class BTVItemRenderProperties implements IClientItemExtensions {
    public BlockEntityWithoutLevelRenderer getCustomRenderer() {
        return new BTVItemstackRenderer();
    }
}
