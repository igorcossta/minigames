package com.github.igorcossta.scheduler;

import com.github.igorcossta.Minigames;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class GameCountdownScheduler {
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public void initStartCountdown(final String minigameName) {
        AtomicInteger counter = new AtomicInteger(0);
        final ScheduledFuture<?>[] futureRef = new ScheduledFuture[1];
        Runnable task = () -> {
            int count = counter.incrementAndGet();
            System.out.println("Running async countdown task at: " + java.time.LocalTime.now());

            if (count >= 5 && futureRef[0] != null) {
                System.out.println("Countdown complete. Cancelling task.");
                futureRef[0].cancel(false);
                Minigames.getMinigame(minigameName).stop();
            }
        };

        futureRef[0] = executorService.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
    }
}
