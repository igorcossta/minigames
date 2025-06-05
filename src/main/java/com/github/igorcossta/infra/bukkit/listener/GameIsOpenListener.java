package com.github.igorcossta.infra.bukkit.listener;

import com.github.igorcossta.Minigames;
import com.github.igorcossta.infra.bukkit.event.GameIsOpenEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class GameIsOpenListener implements Listener {
    private final Minigames instance;

    public GameIsOpenListener(Minigames instance) {
        this.instance = instance;
    }

    @EventHandler
    void on(GameIsOpenEvent event) {
        instance.getSchedulerManagement().initBroadcastSchedulerFor(event.getGameName(), "Game [" + event.getGameName() + "] is open! Join now!");
    }
}
