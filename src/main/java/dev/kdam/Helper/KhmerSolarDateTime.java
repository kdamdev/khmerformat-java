package dev.kdam.Helper;

import dev.kdam.Utils.SolarMonth;
import dev.kdam.entities.SolarDateTime;

/**
 *
 */
public class KhmerSolarDateTime {
    private final SolarDateTime solar;
    public KhmerSolarDateTime(int day, int month, int year) {
        this.solar = new SolarDateTime();

        this.solar.setDay( new KhmerNumeric(day).toKhmer());
        this.solar.setMonth( SolarMonth.month[--month] );
        this.solar.setYear( new KhmerNumeric(year).toKhmer());
    }

    public KhmerSolarDateTime() {
        this.solar = new SolarDateTime();
    }

    public String toString() {
        return String.format( "ថ្ងៃទី%s ខែ%s ឆ្នាំ%s" , this.solar.getDay(), this.solar.getMonth(), this.solar.getYear());
    }
}
