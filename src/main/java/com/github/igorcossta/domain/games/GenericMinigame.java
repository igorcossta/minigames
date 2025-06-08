package com.github.igorcossta.domain.games;

import com.github.igorcossta.Minigames;
import com.github.igorcossta.domain.Minigame;
import com.github.igorcossta.event.MinigameStartEvent;

public class GenericMinigame implements Minigame {
    private final Minigames instance;

    public GenericMinigame(Minigames instance) {
        this.instance = instance;
    }

    @Override
    public void start() {
        instance.raiseEvent(new MinigameStartEvent(this));
    }

    @Override
    public void stop() {
        throw new RuntimeException("not implemented");
    }
}
