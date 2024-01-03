package dev.kdam.khmerformat.utils;

import dev.kdam.khmerformat.enums.SolarMonth;
import dev.kdam.khmerformat.entity.SolarDateTime;

/**
 *
 */
public class KhmerSolarDate {
    private final SolarDateTime solar;
    public KhmerSolarDate(int day, int month, int year) {
        this.solar = new SolarDateTime();

        this.solar.setDay( new KhmerNumeric(day).toKhmer());
        this.solar.setMonth( SolarMonth.month[--month] );
        this.solar.setYear( new KhmerNumeric(year).toKhmer());
    }

    public KhmerSolarDate() {
        this.solar = new SolarDateTime();
    }

    public String toString() {
        return String.format( "ថ្ងៃទី%s ខែ%s ឆ្នាំ%s" , this.solar.getDay(), this.solar.getMonth(), this.solar.getYear());
    }
}
