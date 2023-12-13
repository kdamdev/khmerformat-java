package dev.kdam.utils;

import dev.kdam.constraints.SolarMonth;
import dev.kdam.entities.SolarDateTimeEntity;

/**
 *
 */
public class SolarDateTime {
    private final SolarDateTimeEntity solar;
    public SolarDateTime(int day, int month, int year) {
        this.solar = new SolarDateTimeEntity();

        this.solar.setDay( new KhmerNumeric(day).toKhmer());
        this.solar.setMonth( SolarMonth.month[--month] );
        this.solar.setYear( new KhmerNumeric(year).toKhmer());
    }

    public String toString() {
        return String.format( "ថ្ងៃទី%s ខែ%s ឆ្នាំ%s" , this.solar.getDay(), this.solar.getMonth(), this.solar.getYear());
    }
}
