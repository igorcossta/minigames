package com.github.igorcossta.infra.bukkit.event;

import java.util.List;
import java.util.UUID;

public class GameStartedEvent extends GameEvent {
    private final List<UUID> activePlayers;
    private final double spawnX, spawnY, spawnZ;
    private final String world;
    private final String gameName;

    public GameStartedEvent(List<UUID> activePlayers,
                            double spawnX,
                            double spawnY,
                            double spawnZ,
                            String world, String gameName) {
        this.activePlayers = activePlayers;
        this.spawnX = spawnX;
        this.spawnY = spawnY;
        this.spawnZ = spawnZ;
        this.world = world;
        this.gameName = gameName;
    }

    public List<UUID> getActivePlayers() {
        return activePlayers;
    }

    public double getSpawnX() {
        return spawnX;
    }

    public double getSpawnY() {
        return spawnY;
    }

    public double getSpawnZ() {
        return spawnZ;
    }

    public String getWorld() {
        return world;
    }

    public String getGameName() {
        return gameName;
    }
}
