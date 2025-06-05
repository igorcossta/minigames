package com.github.igorcossta.infra.bukkit.event;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

abstract class GameEvent extends Event {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
