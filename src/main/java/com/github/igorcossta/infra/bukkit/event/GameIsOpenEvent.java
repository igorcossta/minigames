package com.github.igorcossta.infra.bukkit.event;

public class GameIsOpenEvent extends GameEvent {
    private final String gameName;

    public GameIsOpenEvent(String gameName) {
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }
}
