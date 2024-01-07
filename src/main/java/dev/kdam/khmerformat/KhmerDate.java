package dev.kdam.khmerformat;

import dev.kdam.khmerformat.utils.KhmerLunarDate;
import dev.kdam.khmerformat.utils.KhmerSolarDate;

import java.time.LocalDate;

/**
 * KhmerDate
 */
public class KhmerDate {
    public static KhmerSolarDate SolarDate(LocalDate localDate) {
        return new KhmerSolarDate();
    }

    public static KhmerLunarDate LunarDate(LocalDate localDate) {
        return new KhmerLunarDate();
    }
}
