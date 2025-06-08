package com.github.igorcossta.listener;

import com.github.igorcossta.event.MinigameStartEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MinigameStartListener implements Listener {
    @EventHandler
    void on(final MinigameStartEvent event) {
        System.out.println("Minigame is open!");
    }
}
