package io.github.kdamdev.khmerformat;

import io.github.kdamdev.khmerformat.utils.LunarDate;
import io.github.kdamdev.khmerformat.utils.SolarDate;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * KhmerDate
 */
public class KhmerDate {
    public static SolarDate SolarDate(LocalDate localDate) {
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.of("Asia/Phnom_Penh")); // convert cambodia time zone
        return new SolarDate(zonedDateTime.toLocalDate());
    }

    public static LunarDate LunarDate(LocalDate localDate) {
        ZonedDateTime zonedDateTime = localDate.atStartOfDay(ZoneId.of("Asia/Phnom_Penh")); // convert cambodia time zone
        return new LunarDate(zonedDateTime.toLocalDate());
    }
}
