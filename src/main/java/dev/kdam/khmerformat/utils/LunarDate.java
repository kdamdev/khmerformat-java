package dev.kdam.khmerformat.utils;

import dev.kdam.khmerformat.enums.*;
import dev.kdam.khmerformat.helper.KhmerNewYearHelper;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

/**
 * KhmerLunarDate
 */
public class LunarDate {
    private String dayOfWeek = "";
    private String dayOfMonth = "";
    private String month = "";
    private String zodiacYear = "";
    private String era = "";
    private String beYear = "";
    public LunarDate(LocalDate localDate) {
        KhmerNewYearHelper helper   = new KhmerNewYearHelper(localDate.getYear());
        this.dayOfWeek              = DayOfWeek.day_of_week[localDate.getDayOfWeek().getValue() % 7];
        int[] dayAndMonth           = mapSolarYearToLunarYear(localDate, helper );

        this.dayOfMonth             = getLunarDayOfMonth(dayAndMonth[0]);
        this.month                  = getLunarMonth(dayAndMonth[1], dayAndMonth[3] == 1);
        this.zodiacYear             = findZodiacYear(helper.getNewYearDay(), dayAndMonth);
        this.era                    = findEra(helper.getLeungsakDay(), dayAndMonth);
        this.beYear                 = new Numeric(findBeYear(dayAndMonth)).toKhmer();
    }
    private String findZodiacYear(int[] newYearDay, int[] currentDate) {
        int index = (currentDate[2] + 9) % 12;
        return (currentDate[0] >= newYearDay[0] && currentDate[1] >= newYearDay[1]) || currentDate[1] > newYearDay[1] ? ZodiacYear.year[index] : ZodiacYear.year[index - 1];
    }
    /**
     * find Buddhist Year base on Visak Bochea
     * @param currentDate
     * @return
     */
    private int findBeYear(int[] currentDate) {
        return currentDate[2] + (currentDate[1] > 6 || (currentDate[1] == 6 && currentDate[0] >= 16) ? 544 : 543);
    }

    /**
     * find ERA base on day of leung sak
     * @param leungSak
     * @param currentDate
     * @return string
     */
    private String findEra(int[] leungSak, int[] currentDate) {
        return Era.sak[currentDate[1] > leungSak[1] || (currentDate[0] >= leungSak[0] && currentDate[1] == leungSak[1]) ? (currentDate[2] + 2) % 10 : (currentDate[2] + 1) % 10];
    }
    private String getLunarMonth(int month, boolean isLeapYear) {
        if (isLeapYear) {
            return LunarMonth.leapYearMonth[month == 14 ? 0 : month - 1];
        }
        return LunarMonth.month[month == 13 ? 0 : month - 1];
    }

    /**
     *
     * @param month
     * @return 29 for odd month or 30 even month, if 13th month return 30
     */
    private int getDayInMonth(int month, KhmerNewYearHelper helper){
        if(helper.isAthikmeas() && month >= 8) {
            if(month == 8 || month == 9) return 30;
            else month -= 1;
        }
        else{
            if(helper.isChes30Days() && month == 7) return 30;
        }
        return month % 2 == 0 ? 30 : 29;
    }
    private int getDayInYear(KhmerNewYearHelper soryaLeungsakHelper) {
        return soryaLeungsakHelper.isAthikmeas() ? 384 : soryaLeungsakHelper.isChes30Days() ? 355 : 354;
    }

    private String getLunarDayOfMonth(int day) {
        int t = day % 15 == 0 ? 15 : day % 15;
        return new Numeric(t).toKhmer() + " " + (day > 15 ? JourneyMoon.WANING.getLabel() : JourneyMoon.WAXING.getLabel());
    }
    private int[] mapSolarYearToLunarYear(LocalDate epochEst, KhmerNewYearHelper helper) {
        LocalDate epoch = LocalDate.of(2014,1,1); // ត្រូវតែ ១កើត ខែបុស្ស, 1900, 1938, 1957, 2014, 2033, 2071, 2090, 2185

        long tmp_d = 0;
        int tmp_m = 0;

        if(epochEst.isAfter(epoch) || epochEst.isEqual(epoch)) {
            tmp_d = ChronoUnit.DAYS.between(epoch, epochEst) + 1;
            int tmp_y = epoch.getYear();
            for ( int tmp_year = tmp_y; tmp_year < epochEst.getYear(); tmp_year++ ) {
                int totalDayYear =  getDayInYear(new KhmerNewYearHelper(tmp_year));
                if(tmp_d < totalDayYear) break;
                tmp_d -= totalDayYear;
                tmp_y = tmp_year;
            }
            // cal day
            tmp_y += 1;
            KhmerNewYearHelper epochHelper = new KhmerNewYearHelper(tmp_y);
            for (int m = 2; m <= 14; m++) { // ចន្ទគតិ ខែទី២ បុស្ស=មករា
                tmp_m = m; // ខែទី១ មិគសិរ=ធ្នូ
                int daysOfMonth = getDayInMonth(tmp_m, epochHelper);
                if(tmp_d <= daysOfMonth ) {
                    break;
                }else {
                    tmp_d -= daysOfMonth;
                }
            }
            return new int[] {(int) tmp_d, tmp_m == 14 ? 1 : tmp_m, tmp_y, epochHelper.isAthikmeas() ? 1 : 0};
        }else{
            tmp_d = ChronoUnit.DAYS.between(epoch, epochEst) + 1;
            int tmp_y  = epoch.getYear() - 1;
            int totalDayYear = getDayInYear(new KhmerNewYearHelper(tmp_y));
            while (Math.abs(tmp_d) > totalDayYear) {
                tmp_y--;
                tmp_d += totalDayYear;
                totalDayYear = getDayInYear(new KhmerNewYearHelper(tmp_y));
            }
            KhmerNewYearHelper epochHelper = new KhmerNewYearHelper( tmp_y );
            tmp_m = epochHelper.isAthikmeas() ? 14 : 13;
            while (tmp_m >= 2) { // ចន្ទគតិ ខែទី២ បុស្ស=មករា
                int daysOfMonth = getDayInMonth(tmp_m, epochHelper);
                tmp_d += daysOfMonth;
                if(tmp_d > 0 ) {
                    break;
                }
                tmp_m--;
            }
            return new int[] {(int) tmp_d, tmp_m, tmp_y, epochHelper.isAthikmeas() ? 1 : 0};
        }
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
