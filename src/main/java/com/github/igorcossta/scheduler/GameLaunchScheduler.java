package com.github.igorcossta.scheduler;

import com.github.igorcossta.Minigames;
import com.github.igorcossta.util.config.MainConfig;
import com.github.igorcossta.util.time.DateTime;
import org.jetbrains.annotations.NotNull;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class GameLaunchScheduler {
    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
    private final MainConfig mainConfig;

    public GameLaunchScheduler(MainConfig mainConfig) {
        this.mainConfig = mainConfig;
    }

    // TODO: safe ignore minigames that start at same time: e.g runner start at 01:00 PM every sunday and the parkour too
    public void initBackgroundService() {
        Map<String, MainConfig.MinigameSchedule> schedulers = mainConfig.getSchedulers();

        Runnable task = () -> {
            System.out.println("Running async task at: " + java.time.LocalTime.now());
            schedulers.forEach((s, minigameSchedule) -> minigameSchedule.times().forEach(checkIfDayAndTimeMatchCurrentDate(minigameSchedule)));
        };

        executorService.scheduleAtFixedRate(task, 0, 60, TimeUnit.SECONDS);
    }

    private static @NotNull Consumer<MainConfig.DayAndTime> checkIfDayAndTimeMatchCurrentDate(MainConfig.MinigameSchedule minigameSchedule) {
        return dayAndTime -> {
            ZonedDateTime currentTime = ZonedDateTime.now(ZoneId.systemDefault());
            if (DateTime.isSameDay(currentTime.getDayOfWeek(), dayAndTime.getDayOfWeek())) {
                if (DateTime.isSameTime(currentTime.toLocalTime(), dayAndTime.getTime())) {
                    System.out.println("The game %s is going to be started".formatted(minigameSchedule.minigameName()));
                    Minigames.getMinigame(minigameSchedule.minigameName()).start();
                }
            }
        };
    }
}
