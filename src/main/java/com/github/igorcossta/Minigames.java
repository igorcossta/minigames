package com.github.igorcossta;

import com.github.igorcossta.application.SchedulerManagement;
import com.github.igorcossta.game.TestGame;
import com.github.igorcossta.game.Game;
import com.github.igorcossta.infra.bukkit.command.MinigamesCommand;
import com.github.igorcossta.infra.bukkit.listener.GameEndedListener;
import com.github.igorcossta.infra.bukkit.listener.GameIsOpenListener;
import com.github.igorcossta.infra.bukkit.listener.GameStartedListener;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

public final class Minigames extends JavaPlugin {
    private Minigames instance;
    private SchedulerManagement schedulerManagement;

    private Game game;

    @Override
    public void onEnable() {
        this.instance = this;
        this.schedulerManagement = new SchedulerManagement();

        this.game = new TestGame(instance);

        this.getCommand("minigames").setExecutor(new MinigamesCommand(instance));
        this.getServer().getPluginManager().registerEvents(new GameStartedListener(instance), this);
        this.getServer().getPluginManager().registerEvents(new GameEndedListener(instance), this);
        this.getServer().getPluginManager().registerEvents(new GameIsOpenListener(instance), this);
    }

    public Game getGame() {
        return this.game;
    }

    public SchedulerManagement getSchedulerManagement() {
        return schedulerManagement;
    }

    public void raise(Event event) {
        if (event == null)
            throw new RuntimeException("Event cannot be null");
        this.getServer().getPluginManager().callEvent(event);
    }
}
