package com.example.khaier.helper;

import java.time.Duration;
import java.time.LocalDateTime;

public class TimeSinceFormatterHelper {

    public static String formatTimeSince(LocalDateTime creationDate) {

        LocalDateTime currentDate = LocalDateTime.now();
        Duration duration = Duration.between(creationDate, currentDate);

        long days = duration.toDays();
        long hours = duration.toHours() % 24;
        long minutes = duration.toMinutes() % 60;
        long seconds = duration.getSeconds() % 60;


        String createdSince;

        if (days >= 365) {
            createdSince = formatYears(days);
        } else if (days >= 30) {
            createdSince = formatMonths(days);
        } else if (days > 0) {
            createdSince = formatDays(days);
        } else if (hours > 0) {
            createdSince = formatHours(hours);
        } else if (minutes > 0) {
            createdSince = formatMinutes(minutes);
        } else {
            createdSince = formatSeconds(seconds);
        }
        return createdSince;

    }

    private static String formatSeconds(long seconds) {
        String createdSince;
        if (seconds == 0) {
            createdSince = "الان";
        } else if(seconds == 1){
            createdSince = "منذ ثانية";
        } else if (seconds == 2) {
            createdSince = "منذ ثانيتين";
        } else if (seconds <= 10) {
            createdSince = "منذ " + seconds + " ثواني";
        }else {
            createdSince = "منذ " + seconds + " ثانية";
        }
        return createdSince;
    }

    private static String formatMinutes(long minutes) {
        String createdSince;
        if (minutes == 1) {
            createdSince = "منذ دقيقة";
        } else if (minutes == 2) {
            createdSince = "منذ دقيقتين";
        } else if (minutes <= 10) {
            createdSince = "منذ " + minutes + " دقائق";
        } else {
            createdSince = "منذ " + minutes + " دقيقة";
        }

        return createdSince;
    }

    private static String formatHours(long hours) {
        String createdSince;
        if (hours == 2) {
            createdSince = "منذ ساعتين";
        } else if (hours == 1) {
            createdSince = "منذ ساعة";
        } else if (hours <= 10) {
            createdSince = "منذ " + hours + " ساعات";
        } else {
            createdSince = "منذ " + hours + " ساعة";
        }
        return createdSince;
    }

    private static String formatDays(long days) {
        String createdSince;
        if (days == 2) {
            createdSince = "منذ يومين";
        } else if (days == 1) {
            createdSince = "منذ يوم";
        } else if (days <= 10) {
            createdSince = "منذ " + days + " أيام";
        } else {
            createdSince = "منذ " + days + " يوم";
        }
        return createdSince;
    }

    private static String formatMonths(long days) {
        String createdSince;
        long months = days / 30;
        if (months == 2) {
            createdSince = "منذ شهرين";
        } else if (months == 1) {
            createdSince = "منذ شهر";
        } else if (months <= 10) {
            createdSince = "منذ " + months + " أشهر";
        } else {
            createdSince = "منذ " + months + " شهر";
        }
        return createdSince;

    }

    private static String formatYears(long days) {
        String createdSince;
        long years = days / 365;
        if (years == 2) {
            createdSince = "منذ سنتين";
        } else if (years == 1) {
            createdSince = "منذ سنة";
        } else if (years <= 10) {
            createdSince = "منذ " + years + " سنوات";
        } else {
            createdSince = "منذ " + years + " سنة";
        }
        return createdSince;
    }
}
