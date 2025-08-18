package org.example.utils;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for time-related functions.
 */
public class TimeUtil {
    public static String nowIso() {
        return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
    }
}