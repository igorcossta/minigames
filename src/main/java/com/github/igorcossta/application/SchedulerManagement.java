package com.github.igorcossta.application;

import org.bukkit.Bukkit;

import java.util.Map;
import java.util.concurrent.*;

public class SchedulerManagement {
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    private final Map<String, ScheduledFuture<?>> broadcastTasks = new ConcurrentHashMap<>();

    public void initBroadcastSchedulerFor(String gameName, String message) {
        if (broadcastTasks.containsKey(gameName))
            stopBroadcastSchedulerFor(gameName);

        ScheduledFuture<?> task = executor.scheduleAtFixedRate(() -> {
            Bukkit.broadcastMessage(message);
        }, 0, 10, TimeUnit.SECONDS);

        broadcastTasks.put(gameName, task);
    }

    public void stopBroadcastSchedulerFor(String gameName) {
        ScheduledFuture<?> task = broadcastTasks.remove(gameName);
        if (task != null) {
            task.cancel(true);
        }
    }
}
