package dev.kdam.khmerformat.Utils;

import dev.kdam.khmerformat.Enum.*;
import dev.kdam.khmerformat.Helper.SoryaLeangsakHelper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * KhmerLunarDate
 */
public class KhmerLunarDate {
    private final LocalDate localDate;
    private final String dayOfWeek;
    private String dayOfMonth;
    private String month;
    private String zodiacYear;
    private String era;
    private String beYear;
    public KhmerLunarDate(int day, int month, int year) {
        localDate = LocalDate.of( year, month, day);
        SoryaLeangsakHelper helper = new SoryaLeangsakHelper(year);
        this.dayOfWeek = DayOfWeek.day_of_week[localDate.getDayOfWeek().getValue() % 7];
        int[] dayAndMonth = mapSolarYearToLunarYear(localDate);
        this.dayOfMonth = getDayOfMonth(dayAndMonth[0]);
        this.month = LunarMonth.month[dayAndMonth[1]];
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
        long days = ChronoUnit.DAYS.between(start,current) ;//+ this.mapSolarYearToLunarYear();
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
    public int[] mapSolarYearToLunarYear(LocalDate epochEst) {
        LocalDate epoch = LocalDate.of(1900,1,1);
        long dayBetween = ChronoUnit.DAYS.between(epoch,epochEst);
        for (int epoch_year = epoch.getYear(); epoch_year < epochEst.getYear(); epoch_year++){
            SoryaLeangsakHelper lunarHelper = new SoryaLeangsakHelper(epoch_year);
            if(lunarHelper.isAthikmeas())
                dayBetween -= 384;
            else if (lunarHelper.isChes30Days())
                dayBetween -= 355;
            else
                dayBetween -= 354;
        }
        //calculate day and month
        int tmp_d = 1;
        int tmp_m = 1;
        for (int m =1; m < 15; m++){
            tmp_m = m;
            if(dayBetween < this.getDayInfMonth(m)){
                tmp_d = (int) dayBetween;
                break;
            }else {
                dayBetween -= this.getDayInfMonth(m);
            }
        }
        if(tmp_d < 0 )
            tmp_m--;
        else
            tmp_d++;
        return new int[] {tmp_d, tmp_m};
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
        return "";
        //int[] daysAndMonth = this.mapSolarDayToLunarDay();
        //return String.format( "ថ្ងៃ%s %s ខែ%s ឆ្នាំ%s %s ព.ស.%s", this.lunar.getDayOfWeek(), this.getDayOfMonth(daysAndMonth[0]), this.lunar.getDayOfWeek(), this.calAnimalYear(), this.calEra(), this.lunar.getDayOfWeek());
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getDayOfMonth() {
        return dayOfMonth;
    }

    public String getMonth() {
        return month;
    }

    public String getZodiacYear() {
        return zodiacYear;
    }

    public String getEra() {
        return era;
    }

    public String getBeYear() {
        return beYear;
    }
}
