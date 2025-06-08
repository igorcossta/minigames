package com.github.igorcossta.domain.games;

import com.github.igorcossta.Minigames;
import com.github.igorcossta.domain.Minigame;
import com.github.igorcossta.event.MinigameStartEvent;
import com.github.igorcossta.listener.minigames.generic.AmazingGenericListener;
import com.github.igorcossta.listener.minigames.generic.AwesomeGenericListener;
import org.bukkit.event.Listener;

import java.util.Set;

public class GenericMinigame implements Minigame {
    private final Minigames instance;
    private final Set<Listener> listeners;

    public GenericMinigame(Minigames instance) {
        this.instance = instance;
        this.listeners = Set.of(new AwesomeGenericListener(), new AmazingGenericListener());
    }

    @Override
    public void start() {
        instance.registerListeners(listeners);
        instance.raiseEvent(new MinigameStartEvent(this));
    }

    @Override
    public void stop() {
        instance.unregisterListeners(listeners);
    }
}
