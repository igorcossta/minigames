package com.github.igorcossta.infra.bukkit.listener;

import com.github.igorcossta.Minigames;
import com.github.igorcossta.infra.bukkit.event.GameEndedEvent;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.Objects;

public class GameEndedListener implements Listener {
    private final Minigames instance;

    public GameEndedListener(Minigames instance) {
        this.instance = instance;
    }

    @EventHandler
    void on(GameEndedEvent event) {
        instance.getSchedulerManagement().stopBroadcastSchedulerFor(event.getGameName());
        if (!event.getActivePlayers().isEmpty()) {
            World world = Bukkit.getWorld(event.getWorld());
            event.getActivePlayers()
                    .stream()
                    .map(Bukkit::getPlayer)
                    .filter(Objects::nonNull)
                    .forEach(player -> player.teleport(new Location(world, event.getExitX(), event.getExitY(), event.getExitZ())));
        }
    }
}
