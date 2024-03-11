package com.valeriotor.beyondtheveil.util;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class DelayedMessage {

    public int ticksToSend;
    public String message;
    public Player receiver;

    public DelayedMessage(int ticksToSend, String message, Player receiver) {
        this.ticksToSend = ticksToSend;
        this.message = message;
        this.receiver = receiver;
    }

    public boolean update() {
        this.ticksToSend--;
        if(this.ticksToSend <= 0) {
            receiver.displayClientMessage(Component.literal(message), true);
            return true;
        }
        return false;
    }

}
