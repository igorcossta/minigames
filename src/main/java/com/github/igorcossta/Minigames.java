package com.github.igorcossta;

import com.github.igorcossta.domain.Minigame;
import com.github.igorcossta.domain.games.GenericMinigame;
import com.github.igorcossta.infrastructure.AwesomeListener;
import com.github.igorcossta.listener.MinigameStartListener;
import com.github.igorcossta.scheduler.GameLaunchScheduler;
import com.github.igorcossta.util.config.MainConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Minigames extends JavaPlugin {
    private Minigames instance;
    private MainConfig mainConfig;
    private GameLaunchScheduler gameLaunchScheduler;
    private static Map<String, Minigame> minigames = new HashMap<>();

    @Override
    public void onEnable() {
        this.instance = this;

        loadConfigurations();
        loadMinigames();

        this.getServer().getPluginManager().registerEvents(new MinigameStartListener(), this.instance);
        this.getServer().getPluginManager().registerEvents(new AwesomeListener(this.mainConfig, this.instance), this.instance);
    }

    private void loadConfigurations() {
        this.mainConfig = new MainConfig("config", this.instance);
        this.mainConfig.registerSchedulers();
        this.gameLaunchScheduler = new GameLaunchScheduler(this.mainConfig);
        this.gameLaunchScheduler.initBackgroundService();
    }

    private void loadMinigames() {
        minigames.put("runner", new GenericMinigame(this.instance));
    }

    public static Minigame getMinigame(String minigame) {
        return minigames.get(minigame);
    }

    public void raiseEvent(Event event) {
        if (event == null)
            throw new IllegalArgumentException("Event cannot be null");
        Bukkit.getScheduler().runTask(this.instance, () -> {
            instance.getServer().getPluginManager().callEvent(event);
        });
    }

    public void registerListeners(Set<Listener> listeners) {
        for (Listener listener : listeners) {
            this.instance.getServer().getPluginManager().registerEvents(listener, this.instance);
            this.instance.getLogger().info("Listener '%s' registered.".formatted(listener.getClass().getSimpleName()));
        }
    }

    public void unregisterListeners(Set<Listener> listeners) {
        for (Listener listener : listeners) {
            HandlerList.unregisterAll(listener);
            this.instance.getLogger().info("Listener '%s' unregistered.".formatted(listener.getClass().getSimpleName()));
        }
    }
}
