package com.github.igorcossta.infra.bukkit.event;

import java.util.List;
import java.util.UUID;

public class GameEndedEvent extends GameEvent {
    private final List<UUID> activePlayers;
    private final double exitX, exitY, exitZ;
    private final String world;
    private final String gameName;

    public GameEndedEvent(List<UUID> activePlayers,
                          double exitX,
                          double exitY,
                          double exitZ,
                          String world, String gameName) {
        this.activePlayers = activePlayers;
        this.exitX = exitX;
        this.exitY = exitY;
        this.exitZ = exitZ;
        this.world = world;
        this.gameName = gameName;
    }

    public List<UUID> getActivePlayers() {
        return activePlayers;
    }

    public double getExitX() {
        return exitX;
    }

    public double getExitY() {
        return exitY;
    }

    public double getExitZ() {
        return exitZ;
    }

    public String getWorld() {
        return world;
    }

    public String getGameName() {
        return gameName;
    }
}
