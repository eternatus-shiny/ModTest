package com.valeriotor.beyondtheveil.util;

import com.google.common.collect.ImmutableList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PlayerTimer {
    public final UUID player;
    private final MinecraftServer server;
    private int timer;
    private final List<Consumer<Player>> finalActions;
    private final List<Consumer<Player>> continuosActions;
    private final List<Predicate<Player>> interrupts;
    private final List<Predicate<Player>> finishers;
    private String name = "";

    public PlayerTimer(Player player) {
        this(player, null, 100);
    }

    public PlayerTimer(Player player, Consumer<Player> finalAction) {
        this(player, finalAction, 100);
    }

    public PlayerTimer(Player player, Consumer<Player> action, int timer) {
        this.player = player.getUUID();
        this.server = player.getServer();
        this.timer = timer;
        this.continuosActions = ImmutableList.of();
        this.interrupts = ImmutableList.of();
        this.finishers = ImmutableList.of();
        if(action != null)
            this.finalActions = ImmutableList.of(action);
        else
            this.finalActions = ImmutableList.of();
    }

    private PlayerTimer(Player player, int timer, List<Consumer<Player>> continuosActions, List<Consumer<Player>> finalActions, List<Predicate<Player>> interrupts, List<Predicate<Player>> finishers) {
        this.player = player.getUUID();
        this.server = player.getServer();
        this.timer = timer;
        this.continuosActions = ImmutableList.copyOf(continuosActions);
        this.finalActions = ImmutableList.copyOf(finalActions);
        this.interrupts = ImmutableList.copyOf(interrupts);
        this.finishers = ImmutableList.copyOf(finishers);
    }

    public boolean update() {
        Player p = this.getPlayer();
        if(p == null) return true;
        for(Predicate<Player> finisher : finishers) if(finisher.test(p)) {
            for(Consumer<Player> action : finalActions) action.accept(p);
            return true;
        }
        for(Predicate<Player> interrupt : interrupts) if(interrupt.test(p)) return true;
        if(timer > 0) {
            if(!p.isDeadOrDying()) {
                timer--;
                for(Consumer<Player> action : continuosActions) action.accept(p);
            }
            return false;
        } else if(timer == 0) {
            timer--;
            for(Consumer<Player> action : finalActions) action.accept(p);
        }
        return true;

    }

    public boolean isDone() {
        return timer <= 0;
    }

    public int getTimer() {
        return this.timer;
    }

    public Player getPlayer() {
        return this.server.getPlayerList().getPlayer(player);
    }

    public PlayerTimer setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return this.name;
    }

    public boolean corresponds(String name, Player player) {
        return this.name.equals(name) && this.player.equals(player.getUUID());
    }

    public boolean equals(Object object) {
        if(!(object instanceof PlayerTimer)) return false;
        PlayerTimer timer = (PlayerTimer) object;
        return timer.getName().equals(this.name) && timer.player.equals(this.player);
    }

    @Override
    public int hashCode() {
        int code = this.name.hashCode();
        code = 31 * code + player.hashCode();
        return code;
    }

    public void terminateEarly() {
        this.timer = -1;
        for(Consumer<Player> action : finalActions) action.accept(this.getPlayer());
    }

    public PlayerTimer copyForNewPlayer(Player player) {
        return new PlayerTimer(player, timer, continuosActions, finalActions, interrupts, finishers).setName(name);
    }

    public static class PlayerTimerBuilder {

        private List<Consumer<Player>> finalActions = new ArrayList<>();
        private List<Consumer<Player>> continuosActions = new ArrayList<>();
        private List<Predicate<Player>> interrupts = new ArrayList<>();
        private List<Predicate<Player>> finishers = new ArrayList<>();
        private int timer = 100;
        private final Player player;
        private String name = "";

        public PlayerTimerBuilder(Player player) {
            this.player = player;
        }

        public PlayerTimerBuilder setTimer(int timer) {
            this.timer = timer;
            return this;
        }

        public PlayerTimerBuilder addFinalAction(Consumer<Player> action) {
            finalActions.add(action);
            return this;
        }

        public PlayerTimerBuilder addContinuosAction(Consumer<Player> action) {
            continuosActions.add(action);
            return this;
        }

        public PlayerTimerBuilder addInterrupt(Predicate<Player> action) {
            interrupts.add(action);
            return this;
        }

        public PlayerTimerBuilder addFinisher(Predicate<Player> action) {
            finishers.add(action);
            return this;
        }

        public PlayerTimerBuilder setName(String name) {
            this.name = name;
            return this;
        }

        public PlayerTimer toPlayerTimer() {
            return new PlayerTimer(this.player, this.timer, this.continuosActions, this.finalActions, this.interrupts, this.finishers).setName(this.name);
        }

    }
}
