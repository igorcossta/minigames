package com.github.igorcossta.game;

import java.util.List;
import java.util.UUID;

public interface Game {
    String getName();

    void open();

    void start();

    void end();

    void addPlayer(UUID playerId);

    void removePlayer(UUID playerId);

    boolean isRunning();

    boolean isOpen();

    String getResultForPlayer(UUID playerId);

    List<UUID> getActivePlayers();

    void setSpawnLocation(double x, double y, double z);

    void setExitLocation(double x, double y, double z);

    void setWorld(String world);
}
