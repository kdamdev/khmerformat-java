package dev.kdam.khmerformat.utils;

import dev.kdam.khmerformat.enums.*;
import dev.kdam.khmerformat.helper.KhmerNewYearHelper;

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
        KhmerNewYearHelper helper   = new KhmerNewYearHelper( localDate.getYear() );
        this.dayOfWeek              = DayOfWeek.day_of_week[localDate.getDayOfWeek().getValue() % 7];
        int[] dayAndMonth           = mapSolarYearToLunarYear(localDate, helper );
        this.dayOfMonth             = getLunarDayOfMonth(dayAndMonth[0]);
        this.month                  = getLunarMonth(dayAndMonth[1], helper.isAthikmeas());
        this.zodiacYear             = findZodiacYear(localDate.getYear());
        this.era                    = findEra(helper.getLeungsakDay(), dayAndMonth);
        this.beYear                 = new KhmerNumeric( findBeYear(dayAndMonth)).toKhmer();
        System.out.println( getLunarMonth(helper.getNewYearDay()[1], helper.isAthikmeas()) );
        System.out.println( getLunarDayOfMonth(helper.getNewYearDay()[0]) );
    }
    private String findZodiacYear(int year) {
        return ZodiacYear.year[(year + 9) % 12];
    }
    /**
     * find Buddhist Year base on Visak Bochea
     * @param dayAndMonth
     * @return
     */
    private int findBeYear(int[] dayAndMonth) {
        return localDate.getYear() + (dayAndMonth[1] > 6 || (dayAndMonth[1] == 6 && dayAndMonth[0] >= 16) ? 544 : 543);
    }

    /**
     * find ERA base on day of leung sak
     * @param leungSak
     * @param currentDate
     * @return string
     */
    private String findEra(int[] leungSak, int[] currentDate) {
        return Era.sak[currentDate[1] > leungSak[1] || (currentDate[0] >= leungSak[0] && currentDate[1] == leungSak[1]) ? (localDate.getYear() + 2) % 10 : (localDate.getYear() + 1) % 10];
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
        //System.out.println("getLunarDayOfMonth=>" + day);
        //new KhmerNumeric(t).toKhmer() +
        return new KhmerNumeric(t).toKhmer() + " " + (day > 15 ? JourneyMoon.WANING.getLabel() : JourneyMoon.WAXING.getLabel());
    }
    public int[] mapSolarYearToLunarYear(LocalDate epochEst, KhmerNewYearHelper leangsakHelper) {
        LocalDate epoch = LocalDate.of(1900,1,1);
        long dayBetween = ChronoUnit.DAYS.between(epoch,epochEst) + 1;
        for (int epoch_year = epoch.getYear(); epoch_year < epochEst.getYear(); epoch_year++){
            dayBetween -= getDayInYear(new KhmerNewYearHelper(epoch_year));
        }
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
