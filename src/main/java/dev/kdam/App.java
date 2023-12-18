package dev.kdam;

import dev.kdam.Helper.KhmerLunarDateTime;
import dev.kdam.Helper.KhmerNumeric;
import dev.kdam.Helper.KhmerSolarDateTime;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        KhmerSolarDateTime solar = new KhmerSolarDateTime(01,12,2023);
        System.out.println( solar.toString());
        System.out.println( "-----------------" );
        KhmerNumeric numeric = new KhmerNumeric("123456789012345");
        System.out.println( numeric.toKhmer(true));
        System.out.println( numeric.toKhmer());
        System.out.println( numeric.toKhmerText() );
        System.out.println( "-----------------" );

        KhmerLunarDateTime lunar = new KhmerLunarDateTime(01,12,2016);
        System.out.println(lunar.toString());
        System.out.println(lunar.calculateSoryaLeangsak().get( 0 ).getLesserEra());
        System.out.println(lunar.calculateSoryaLeangsak().get( 0 ).getHarkun());
        System.out.println(lunar.calculateSoryaLeangsak().get( 0 ).getKromathopol());
        System.out.println(lunar.calculateSoryaLeangsak().get( 0 ).getAvaman());
        System.out.println(lunar.calculateSoryaLeangsak().get( 0 ).getBodethey());
        System.out.println( "-----------------" );
        System.out.println(lunar.calculateSoryaLeangsak().get( 1 ).getLesserEra());
        System.out.println(lunar.calculateSoryaLeangsak().get( 1 ).getHarkun());
        System.out.println(lunar.calculateSoryaLeangsak().get( 1 ).getKromathopol());
        System.out.println(lunar.calculateSoryaLeangsak().get( 1 ).getAvaman());
        System.out.println(lunar.calculateSoryaLeangsak().get( 1 ).getBodethey());
        System.out.println( "-----------------" );

        int year = 2024;
        int month = 2;
        int day = 29;

        String dayOfWeek = calculateDayOfWeekBuiltIn(year, month, day);
        System.out.println("The day of the week for " + day + "/" + month + "/" + year + " is: " + dayOfWeek);

        String dayOfWeek1 = calculateDayOfWeek(year, month, day);
        System.out.println("The day of the week for " + day + "/" + month + "/" + year + " is: " + dayOfWeek1);


    }

    public static String calculateDayOfWeekBuiltIn(int year, int month, int day) {
        LocalDate date = LocalDate.of(year, month, day);
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek.toString();
    }

    public static String calculateDayOfWeek(int year, int month, int day) {
        String[] daysOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};

        // Adjusting January and February for Zeller's Congruence
        if (month < 3) {
            month += 12;
            year--;
        }

        int century = year / 100;
        year %= 100;

        // Zeller's Congruence formula without month adjustment
        int dayOfWeek = (day + ((13 * (month + 1)) / 5) + year + (year / 4) + (century / 4) - (2 * century)) % 7;

        // Adjusting for Java's DayOfWeek constants (Sunday = 0, Monday = 1, ..., Saturday = 6)
        dayOfWeek = (dayOfWeek + 6) % 7; // Shifting to align with Java's DayOfWeek

        return daysOfWeek[dayOfWeek];
    }
}
