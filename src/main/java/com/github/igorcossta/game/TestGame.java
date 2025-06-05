package com.github.igorcossta.game;

import com.github.igorcossta.Minigames;
import com.github.igorcossta.infra.bukkit.event.GameEndedEvent;
import com.github.igorcossta.infra.bukkit.event.GameIsOpenEvent;
import com.github.igorcossta.infra.bukkit.event.GameStartedEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TestGame implements Game {
    private final Minigames instance;

    private List<UUID> activePlayers = new ArrayList<>();
    private Boolean isRunning = false;
    private Boolean isOpenToJoin = false;

    private double spawnX, spawnY, spawnZ;
    private double exitX, exitY, exitZ;
    private String world;

    public TestGame(Minigames instance) {
        this.instance = instance;
    }

    @Override
    public String getName() {
        return "Test Game";
    }

    @Override
    public void open() {
        if (isRunning())
            throw new RuntimeException("Game is running at that time");
        if (isOpen())
            throw new RuntimeException("Game is already open");

        this.isOpenToJoin = true;
        instance.raise(new GameIsOpenEvent(getName()));
    }

    @Override
    public void start() {
        if (isRunning())
            throw new RuntimeException("Game is already running");
        if (!isOpen())
            throw new RuntimeException("Game must be opened first");
        if (activePlayers.isEmpty())
            throw new RuntimeException("Game can't be started because participants is 0");

        isRunning = true;
        isOpenToJoin = false;
        instance.raise(new GameStartedEvent(activePlayers, spawnX, spawnY, spawnZ, world, getName()));
    }

    @Override
    public void end() {
        if (!isRunning() && !isOpen())
            throw new RuntimeException("Game can't be ended because there's no game active");

        instance.raise(new GameEndedEvent(activePlayers, exitX, exitY, exitZ, world, getName()));
        isRunning = false;
        isOpenToJoin = false;
        activePlayers = new ArrayList<>();
    }

    @Override
    public void addPlayer(UUID playerId) {
        if (isRunning())
            throw new RuntimeException("You can't join the game because it's already began");
        if (!isOpen())
            throw new RuntimeException("You can't join the game because it's not open");
        if (activePlayers.contains(playerId))
            throw new RuntimeException("You are already participating the game");

        activePlayers.add(playerId);
    }

    @Override
    public void removePlayer(UUID playerId) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public boolean isRunning() {
        return isRunning;
    }

    @Override
    public boolean isOpen() {
        return isOpenToJoin;
    }

    @Override
    public String getResultForPlayer(UUID playerId) {
        throw new RuntimeException("not implemented");
    }

    @Override
    public List<UUID> getActivePlayers() {
        return activePlayers;
    }

    @Override
    public void setSpawnLocation(double x, double y, double z) {
        this.spawnX = x;
        this.spawnY = y;
        this.spawnZ = z;
    }

    @Override
    public void setExitLocation(double x, double y, double z) {
        this.exitX = x;
        this.exitY = y;
        this.exitZ = z;
    }

    @Override
    public void setWorld(String world) {
        this.world = world;
    }
}