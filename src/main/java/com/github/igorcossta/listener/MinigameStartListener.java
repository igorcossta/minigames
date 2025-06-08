package com.github.igorcossta.listener;

import com.github.igorcossta.event.MinigameStartEvent;
import com.github.igorcossta.scheduler.GameCountdownScheduler;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MinigameStartListener implements Listener {
    private final GameCountdownScheduler gameCountdownScheduler;

    public MinigameStartListener(GameCountdownScheduler gameCountdownScheduler) {
        this.gameCountdownScheduler = gameCountdownScheduler;
    }

    @EventHandler
    void on(final MinigameStartEvent event) {
        System.out.println("Minigame is open!");
        gameCountdownScheduler.initStartCountdown("runner");
    }
}
