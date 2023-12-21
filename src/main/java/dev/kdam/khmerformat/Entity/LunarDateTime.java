package dev.kdam.khmerformat.Entity;

/**
 * LunarDateTime
 */
public class LunarDateTime {
    private String day;
    private String month;
    private String year;
    private String dayOfWeek;
    private String zodiac;
    private String era;

    public LunarDateTime() {
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
