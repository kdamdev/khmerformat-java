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
        KhmerNewYearHelper helper   = new KhmerNewYearHelper(localDate.getYear());
        this.dayOfWeek              = DayOfWeek.day_of_week[localDate.getDayOfWeek().getValue() % 7];
        int[] dayAndMonth           = mapSolarYearToLunarYear(localDate, helper );

        this.dayOfMonth             = getLunarDayOfMonth(dayAndMonth[0]);
        this.month                  = getLunarMonth(dayAndMonth[1], helper.isAthikmeas());
        this.zodiacYear             = findZodiacYear(helper.getNewYearDay(), dayAndMonth, localDate.getYear());
        this.era                    = findEra(helper.getLeungsakDay(), dayAndMonth);
        this.beYear                 = new KhmerNumeric(findBeYear(dayAndMonth)).toKhmer();
        System.out.println(Arrays.toString(helper.getNewYearDay()));
        System.out.println(Arrays.toString(helper.getKhmerNewYearTime()));
        System.out.println(Arrays.toString(helper.getLeungsakDay()));
    }
    private String findZodiacYear(int[] newYearDay, int[] currentDay, int year) {
        return currentDay[0] >= newYearDay[0] && currentDay[1] >= newYearDay[1] ? ZodiacYear.year[((year + 9) % 12) - 1] : ZodiacYear.year[((year + 8) % 12) - 1];
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
            }else {
                month -= 1;
            }
        }
        return LunarMonth.month[month == 13 ? 0 : month - 1];
    }

    /**
     *
     * @param month
     * @return 29 for odd month or 30 even month, if 13th month return 30
     */
    private int getDayInMonth(int month, KhmerNewYearHelper helper){
        if (helper.isAthikmeas() && month >= 8) {
            if (month == 8 || month == 9) {
                return 30;
            } else {
                month -= 1;
            }
        }
        else {
            if (helper.isChes30Days()) return 30;
        }
        return month % 2 == 0 ? 30 : 29;
    }
    private int getDayInYear(KhmerNewYearHelper soryaLeungsakHelper) {
        return soryaLeungsakHelper.isAthikmeas() ? 384 : soryaLeungsakHelper.isChes30Days() ? 355 : 354;
    }

    private String getLunarDayOfMonth(int day) {
        int t = day % 15 == 0 ? 15 : day % 15;
        //System.out.println("getLunarDayOfMonth=>" + day);
        //new KhmerNumeric(t).toKhmer() +
        return new KhmerNumeric(t).toKhmer() + " " + (day > 15 ? JourneyMoon.WANING.getLabel() : JourneyMoon.WAXING.getLabel());
    }
    private int[] mapSolarYearToLunarYear(LocalDate epochEst, KhmerNewYearHelper helper) {
        LocalDate epoch = LocalDate.of(2014,1,1); // ១កើត ខែបុស្ស
        int tmp_year = epoch.getYear();

        int tmp_d = 1;
        int tmp_m = 1;
        if(epochEst.isAfter(epoch)) {
            long dayBetween = ChronoUnit.DAYS.between(epoch, epochEst) + 1;
            int totalDayYear =  getDayInYear(new KhmerNewYearHelper(tmp_year));
            while ( dayBetween > totalDayYear) {
                dayBetween -= totalDayYear;
                tmp_year++;
                totalDayYear = getDayInYear(new KhmerNewYearHelper(tmp_year));
            }
            tmp_d = (int) dayBetween;
        }else{
            long dayBetween = ChronoUnit.DAYS.between(epoch, epochEst) - 1;
            //int totalDayYear = getDayInYear(new KhmerNewYearHelper(year));

            System.out.println("dayBetween" + dayBetween );

            while (dayBetween < 0) {
                tmp_year--;
                int totalDayYear = getDayInYear(new KhmerNewYearHelper(tmp_year));
                dayBetween += totalDayYear;
                System.out.println("year: " + tmp_year + ",totalDayYear: " + totalDayYear );
                System.out.println("R dayBetween: " + dayBetween );
            }
            //System.out.println("L totalDayYear: " + totalDayYear );
            //System.out.println("L year: " + year );
            //System.out.println("L dayBetween: " + dayBetween );
        }

        //System.out.println("dayBetween" + dayBetween);
        //cal day
        if(tmp_d > 0) {
            for (int m = 2; m <= 14; m++) { // ចន្ទគតិ ខែទី២ បុស្ស=មករា
                tmp_m = m; // ខែទី១ មិគសិរ=ធ្នូ,
                int daysOfMonth = getDayInMonth(m, new KhmerNewYearHelper( tmp_year ));
                if(tmp_d <= daysOfMonth ) {
                    //tmp_d = (int) dayBetween;
                    break;
                }else {
                    tmp_d -= daysOfMonth;
                }
            }
        }
        System.out.println("tmp_m: " + tmp_m);
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
