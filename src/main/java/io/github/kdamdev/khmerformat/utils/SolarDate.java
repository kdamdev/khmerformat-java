package io.github.kdamdev.khmerformat.utils;

import io.github.kdamdev.khmerformat.enums.SolarMonth;

import java.time.LocalDate;

/**
 *
 */
public class SolarDate {
    private final String day;
    private final String month;
    private final String year;
    public SolarDate(LocalDate localDate) {
        day = new Numeric(localDate.getDayOfMonth()).toKhmer();
        month = SolarMonth.month[localDate.getMonthValue() - 1];
        year = new Numeric(localDate.getYear()).toKhmer();
    }

    public String toString() {
        return String.format( "ថ្ងៃទី%s ខែ%s ឆ្នាំ%s" , day, month, year);
    }
    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }
}
