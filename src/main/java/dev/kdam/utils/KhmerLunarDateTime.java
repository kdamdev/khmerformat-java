package dev.kdam.utils;

import dev.kdam.constraints.AnimalYear;
import dev.kdam.constraints.DayOfWeek;
import dev.kdam.constraints.Era;
import dev.kdam.constraints.Numeric;
import dev.kdam.entities.LunarDateTime;
import jdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;

import java.time.LocalDateTime;

/**
 * KhmerLunarDateTime
 */
public class KhmerLunarDateTime {
    private LunarDateTime lunar;
    private LocalDateTime localDateTime;
    public KhmerLunarDateTime(int day, int month, int year) {
        this.lunar = new LunarDateTime();
        localDateTime = LocalDateTime.of( year, month, day,0,0,0 );
    }
    public int calLesserEra() {
        return 0;
    }
    /**
     * @return String
     */
    public String calAnimalYear() {
        return AnimalYear.year[(this.localDateTime.getYear() + 9) % 12];
    }

    /**
     * @return String
     */
    public String calEra() {
        return Era.sak[(this.localDateTime.getYear() + 2) % 10];
    }

    /**
     * @return
     */
    private String calDayOfWeek() {
        return DayOfWeek.day_of_week[this.localDateTime.getDayOfWeek().getValue()];
    }
    public String toString() {
        return String.format( "ថ្ងៃ%s %s ខែ%s ឆ្នាំ%s %s ព.ស.%s", this.calDayOfWeek(), this.lunar.getDayOfWeek(),this.lunar.getDayOfWeek(), this.calAnimalYear(), this.calEra(), this.lunar.getDayOfWeek());
    }
}
