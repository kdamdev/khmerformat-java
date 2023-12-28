package dev.kdam.khmerformat.Utils;

import dev.kdam.khmerformat.Enum.*;
import dev.kdam.khmerformat.Helper.KhmerNewYearHelper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
        KhmerNewYearHelper helper = new KhmerNewYearHelper( localDate.getYear() );
        this.dayOfWeek      = DayOfWeek.day_of_week[localDate.getDayOfWeek().getValue() % 7];
        int[] dayAndMonth   = mapSolarYearToLunarYear(localDate, helper );
        this.dayOfMonth     = getLunarDayOfMonth(dayAndMonth[0]);
        this.month          = getLunarMonth(dayAndMonth[1], helper.isAthikmeas());
        this.zodiacYear     = ZodiacYear.year[(localDate.getYear() + 9) % 12]; // base khmer new year
        this.era            = Era.sak[(localDate.getYear() + 2) % 10]; // base khmer new year
        this.beYear         = new KhmerNumeric(localDate.getYear() + (dayAndMonth[1] > 6 || (dayAndMonth[1] == 6 && dayAndMonth[0] > 15) ? 544 : 543)).toKhmer();
        System.out.println( helper.getAverageOfSun(363).getReasey());
        System.out.println( helper.getAverageOfSun(363).getAngsar());
        System.out.println( helper.getAverageOfSun(363).getLibda());
    }

    private String getLunarMonth(int month, boolean isLeapYear) {
        if (isLeapYear && month >= 8) {
            if (month == 8 || month == 9) {
                return LunarMonth.leapYearMonth[month - 8];
            } else {
                return LunarMonth.month[month - 2];
            }
        }
        return LunarMonth.month[--month];
    }

    /**
     *
     * @param month
     * @return 29 for odd month or 30 even month, if 13th month return 30
     */
    public int getDayInMonth(int month, KhmerNewYearHelper leangsakHelper){
        if (leangsakHelper.isAthikmeas() && month >= 8) {
            if (month == 8 || month == 9) {
                return 30;
            } else {
                month -= 2;
            }
        }
        else {
            if (leangsakHelper.isChes30Days()) return 30;
        }
        return month % 2 == 0 ? 30 : 29;
    }
    public int getDayInYear(KhmerNewYearHelper soryaLeungsakHelper) {
        return soryaLeungsakHelper.isAthikmeas() ? 384 : soryaLeungsakHelper.isChes30Days() ? 355 : 354;
    }

    public String getLunarDayOfMonth(int day) {
        int t = day % 15 == 0 ? 15 : day % 15;
        return new KhmerNumeric(t).toKhmer() + " " + (day > 15 ? JourneyMoon.WANING.getLabel() : JourneyMoon.WAXING.getLabel());
    }
    public int[] mapSolarYearToLunarYear(LocalDate epochEst, KhmerNewYearHelper leangsakHelper) {
        LocalDate epoch = LocalDate.of(1900,1,1);
        long dayBetween = ChronoUnit.DAYS.between(epoch,epochEst) + 1;
        for (int epoch_year = epoch.getYear(); epoch_year < epochEst.getYear(); epoch_year++){
            dayBetween -= getDayInYear(new KhmerNewYearHelper(epoch_year));
        }
        System.out.println("Remain Days: " + dayBetween);
        //calculate day and month
        int tmp_d = 1;
        int tmp_m = 1;
        if(dayBetween > 0)
            for (int m = 2; m < 13; m++){
                tmp_m = m;
                int daysOfMonth = getDayInMonth(m, leangsakHelper);
                if(dayBetween <= daysOfMonth ) {
                    tmp_d = (int) dayBetween;
                    break;
                }else {
                    dayBetween -= daysOfMonth;
                }
            }
        else{
            tmp_d = (int) (getDayInMonth(1, leangsakHelper) - Math.abs(dayBetween));
        }
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
