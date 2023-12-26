package dev.kdam.khmerformat.Utils;

import dev.kdam.khmerformat.Enum.*;
import dev.kdam.khmerformat.Helper.SoryaLeangsakHelper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

/**
 * KhmerLunarDate
 */
public class KhmerLunarDate {
    private final LocalDate localDate;
    private String dayOfWeek = "";
    private String dayOfMonth = "";
    private String month = "";
    private String zodiacYear = "";
    private String era = "";
    private String beYear = "";
    public KhmerLunarDate(int day, int month, int year) {
        localDate = LocalDate.of( year, month, day);
        this.init();
    }
    public KhmerLunarDate(){
        localDate = LocalDate.now();
        this.init();
    }
    public void init(){
        SoryaLeangsakHelper helper = new SoryaLeangsakHelper(localDate.getYear());
        this.dayOfWeek      = DayOfWeek.day_of_week[localDate.getDayOfWeek().getValue() % 7];
        int[] dayAndMonth   = mapSolarYearToLunarYear(localDate);
        this.dayOfMonth     = getDayOfMonth(dayAndMonth[0]);
        //this.month          = LunarMonth.month[dayAndMonth[1] - 1];
        this.zodiacYear     = ZodiacYear.year[(localDate.getYear() + 9) % 12]; //base khmer new year
        this.era            = Era.sak[(localDate.getYear() + 2) % 10]; //base khmer new year
        this.beYear         = new KhmerNumeric(localDate.getYear() + (dayAndMonth[0] >= 1 && dayAndMonth[1] >= 5 ? 544 : 543)).toKhmer();
        System.out.println( Arrays.toString( dayAndMonth ) );
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
        return new KhmerNumeric(t).toKhmer() + " " + (day > 15 ? JourneyMoon.WANING.getLabel() : JourneyMoon.WAXING.getLabel());
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
        for (int m = 1; m < 15; m++){
            tmp_m = m;
            int daysOfMonth = this.getDayInfMonth(m);
            if(dayBetween < daysOfMonth ){
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

    public String toString() {
        return String.format( "ថ្ងៃ%s %s ខែ%s ឆ្នាំ%s %s ព.ស.%s", dayOfWeek, dayOfMonth, month, zodiacYear, era, beYear);
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
