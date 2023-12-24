package dev.kdam.khmerformat.Utils;

import dev.kdam.khmerformat.Entity.LunarDateTime;
import dev.kdam.khmerformat.Enum.DayOfWeek;
import dev.kdam.khmerformat.Enum.Era;
import dev.kdam.khmerformat.Enum.JourneyMoon;
import dev.kdam.khmerformat.Enum.ZodiacYear;
import dev.kdam.khmerformat.Helper.SoryaLeangsakHelper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * KhmerLunarDateTime
 */
public class KhmerLunarDateTime {
    private final LunarDateTime lunar;
    private final LocalDate localDate;
    private final SoryaLeangsakHelper helper;
    public KhmerLunarDateTime(int day, int month, int year) {
        this.lunar = new LunarDateTime();
        localDate = LocalDate.of( year, month, day);
        //initial Lesser Era
        this.helper = new SoryaLeangsakHelper(year);
    }
    /**
     *
     * @param month
     * @return 29 for odd month or 30 even month
     */
    public int getDayInfMonth(int month){
        return month % 2 == 0 ? 30 : 29;
    }

    public String getDayOfMonth(int day){
        int t = day % 15 == 0 ? 15 : day % 15;
        return t + " " + (day > 15 ? JourneyMoon.WANING.getLabel() : JourneyMoon.WAXING.getLabel());
    }
    public int[] mapSolarDayToLunarDay() {
        LocalDate start = LocalDate.of(this.localDate.getYear(), 1, 1);
        LocalDate current = LocalDate.of(this.localDate.getYear(), this.localDate.getMonth(), this.localDate.getDayOfMonth());
        long days = ChronoUnit.DAYS.between(start,current);
        SoryaLeangsakHelper lunarHelper = new SoryaLeangsakHelper(this.localDate.getYear());
        int tmp_d = 1;
        int tmp_m = 1;

        for (int month = 1; month < 15; month++) {
            System.out.println("Days in Month: " + this.getDayInfMonth(month));
            System.out.println("Days Remain: " + days +",month"+month);
            tmp_m = month;
            if(days < this.getDayInfMonth(month)){
                tmp_d = (int) days;
                break;
            }else {
                days -= this.getDayInfMonth(month);
            }
        }
        return new int[] {tmp_d, tmp_m};
    }
    public Long mapSolarYearToLunarYear() {
        LocalDate epoch = LocalDate.of(1900,1,1);
        LocalDate epochEst = LocalDate.of(this.localDate.getYear(),1,1);

        long dayBetween = ChronoUnit.DAYS.between(epoch,epochEst);
        //System.out.println("Before: " + dayBetween);
        for (int epoch_year = epoch.getYear(); epoch_year < epochEst.getYear(); epoch_year++){
            SoryaLeangsakHelper lunarHelper = new SoryaLeangsakHelper(epoch_year);
            if(lunarHelper.isAthikmeas())
                dayBetween -= 384;
            else if (lunarHelper.isChes30Days())
                dayBetween -= 355;
            else
                dayBetween -= 354;
        }
        //System.out.println("After: " +dayBetween);
        return dayBetween;
    }
    /**
     * @return String
     */
    public String calAnimalYear() {
        return ZodiacYear.year[(this.localDate.getYear() + 9) % 12];
    }

    /**
     * @return String
     */
    public String calEra() {
        return Era.sak[(this.localDate.getYear() + 2) % 10];
    }

    /**
     * @return
     */
    private String calDayOfWeek() {
        return DayOfWeek.day_of_week[0];
    }
    public String toString() {
        return String.format( "ថ្ងៃ%s %s ខែ%s ឆ្នាំ%s %s ព.ស.%s", this.calDayOfWeek(), this.lunar.getDayOfWeek(),this.lunar.getDayOfWeek(), this.calAnimalYear(), this.calEra(), this.lunar.getDayOfWeek());
    }
}
