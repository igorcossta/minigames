package com.github.igorcossta.infra.bukkit.listener;

import com.github.igorcossta.Minigames;
import com.github.igorcossta.infra.bukkit.event.GameStartedEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

public class GameStartedListener implements Listener {
    private final Minigames instance;

    public GameStartedListener(Minigames instance) {
        this.instance = instance;
    }

    @EventHandler
    void on(GameStartedEvent event) {
        instance.getSchedulerManagement().initBroadcastSchedulerFor(event.getGameName(), "Game [" + event.getGameName() + "] is running! Watch now!");
        if (!event.getActivePlayers().isEmpty()) {
            World world = Bukkit.getWorld(event.getWorld());
            event.getActivePlayers()
                    .stream()
                    .map(Bukkit::getPlayer)
                    .filter(Objects::nonNull)
                    .forEach(player -> player.teleport(new Location(world, event.getSpawnX(), event.getSpawnY(), event.getSpawnZ())));
        }
    }
}
