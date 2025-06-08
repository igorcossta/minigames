package com.github.igorcossta.util.config;

import com.github.igorcossta.Minigames;
import org.bukkit.configuration.ConfigurationSection;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class MainConfig extends ConfigLoader {
    private Map<String, MinigameSchedule> schedulers;

    public MainConfig(String fileName, Minigames instance) {
        super(fileName, instance);
    }

    public MinigameSchedule getSchedulerFor(String minigame) {
        return schedulers.get(minigame);
    }

    public void registerSchedulers() {
        Map<String, MinigameSchedule> schedules = new HashMap<>();

        ConfigurationSection section = getConfiguration().getConfigurationSection("schedulers");
        if (section == null) {
            this.schedulers = Collections.emptyMap();
            return;
        }

        for (String minigame : section.getKeys(false)) {
            String rawTime = section.getString(minigame + ".time");
            if (rawTime == null) {
                instance.getLogger().warning("Missing 'time' entry for minigame scheduler: " + minigame);
                continue;
            }

            List<DayAndTime> daysAndTimes = new ArrayList<>();

            for (String scheduler : rawTime.split(",")) {
                String[] parts = scheduler.trim().split("-");
                if (parts.length != 2) {
                    instance.getLogger().warning("Invalid scheduler format: " + scheduler + " for " + minigame);
                    continue;
                }

                String dayOfWeek = parts[0];
                String time = parts[1];

                try {
                    DayAndTime dayAndTime = new DayAndTime(dayOfWeek, time);
                    daysAndTimes.add(dayAndTime);
                } catch (Exception e) {
                    instance.getLogger().severe("Error parsing scheduler for " + minigame + ": " + e.getMessage());
                }
            }

            try {
                schedules.put(minigame, new MinigameSchedule(minigame, daysAndTimes));
            } catch (IllegalArgumentException e) {
                instance.getLogger().severe("Invalid schedule for " + minigame + ": " + e.getMessage());
            }
        }

        this.schedulers = schedules;
    }


    public record MinigameSchedule(String minigameName, List<DayAndTime> times) {
        public MinigameSchedule {
            Set<DayAndTime> unique = new HashSet<>(times);
            if (unique.size() != times.size()) {
                throw new IllegalArgumentException("Schedule contains duplicate day and time entries for: " + minigameName);
            }
        }
    }

    public static class DayAndTime {
        private final DayOfWeek dayOfWeek;
        private final LocalTime time;

        public DayAndTime(String dayOfWeek, String time) {
            try {
                this.dayOfWeek = DayOfWeek.valueOf(dayOfWeek.trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Invalid day of week: " + dayOfWeek);
            }

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mma", Locale.ENGLISH);
                this.time = LocalTime.parse(time.trim().toUpperCase(), formatter);
            } catch (DateTimeParseException e) {
                throw new IllegalArgumentException("Invalid time format: " + time + ". Use format like 10:00AM");
            }
        }

        public DayOfWeek getDayOfWeek() {
            return dayOfWeek;
        }

        public LocalTime getTime() {
            return time;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            DayAndTime that = (DayAndTime) o;
            return dayOfWeek == that.dayOfWeek && Objects.equals(time, that.time);
        }

        @Override
        public int hashCode() {
            return Objects.hash(dayOfWeek, time);
        }

        @Override
        public String toString() {
            return dayOfWeek + " at " + time;
        }
    }
}
