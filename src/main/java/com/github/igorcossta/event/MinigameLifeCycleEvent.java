package com.github.igorcossta.event;

import com.github.igorcossta.domain.Minigame;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

abstract class MinigameLifeCycleEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();
    private final Minigame minigame;

    MinigameLifeCycleEvent(Minigame minigame) {
        this.minigame = minigame;
    }

    MinigameLifeCycleEvent(boolean isAsync, Minigame minigame) {
        super(isAsync);
        this.minigame = minigame;
    }

    public Minigame getMinigame() {
        return this.minigame;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
