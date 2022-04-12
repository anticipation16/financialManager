package com.example.finman.utility;

import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class DateTimeParser {
    /**
     * Formats and returns an ISO8601 date time string in the current time zone
     *
     * @param iso8601DateTime ISO8601 date time string
     * @return Extracted time and date string in current time zone
     */
    public static String getDateTimeString(String iso8601DateTime) {
        Instant instant = Instant.parse(iso8601DateTime);
        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(instant, TimeZone.getDefault().toZoneId());
        String time = DateTimeFormatter.ofPattern("hh:mma").format(zonedDateTime);
        String date = DateTimeFormatter.ofPattern("yyyy/MM/dd").format(zonedDateTime);
        return date + " " + time;
    }
}
