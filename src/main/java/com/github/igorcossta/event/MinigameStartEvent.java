package com.github.igorcossta.event;

import com.github.igorcossta.domain.Minigame;

public class MinigameStartEvent extends MinigameLifeCycleEvent {
    public MinigameStartEvent(Minigame minigame) {
        super(minigame);
    }
}
