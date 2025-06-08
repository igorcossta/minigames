package com.github.igorcossta.util.time;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class DateTime {
    public static boolean isSameDay(DayOfWeek currentDay, DayOfWeek scheduledDay) {
        return currentDay == scheduledDay;
    }

    public static boolean isSameTime(LocalTime currentTime, LocalTime scheduledTime) {
        return currentTime.getHour() == scheduledTime.getHour()
                && currentTime.getMinute() == scheduledTime.getMinute();
    }
}
