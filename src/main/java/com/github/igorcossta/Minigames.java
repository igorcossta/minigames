package com.github.igorcossta;

import com.github.igorcossta.domain.Minigame;
import com.github.igorcossta.domain.games.GenericMinigame;
import com.github.igorcossta.listener.MinigameStartListener;
import com.github.igorcossta.scheduler.GameLaunchScheduler;
import com.github.igorcossta.util.config.MainConfig;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

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

}
