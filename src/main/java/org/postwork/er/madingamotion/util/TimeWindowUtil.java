package org.postwork.er.madingamotion.util;

import org.postwork.er.madingamotion.domain.StudentActivity;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;

public class TimeWindowUtil {

    public static boolean withIn24Hours(List<StudentActivity> activities) {
        if (activities.isEmpty()) {
            return false;
        }

        LocalDateTime min = activities.stream()
                .map(a -> a.timestamp)
                .min(LocalDateTime::compareTo)
                .orElse(LocalDateTime.now());

        LocalDateTime max = activities.stream()
                .map(a -> a.timestamp)
                .max(LocalDateTime::compareTo)
                .orElse(LocalDateTime.now());

        return Duration.between(min, max).toHours() <= 24;
    }
}
