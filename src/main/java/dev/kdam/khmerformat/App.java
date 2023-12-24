package dev.kdam.khmerformat;

import dev.kdam.khmerformat.Utils.KhmerLunarDateTime;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;

/**
 *
 *
 */
public class App 
{
    public static void main( String[] args ) {
//        System.out.println( "Hello World!" );
//        KhmerSolarDateTime solar = new KhmerSolarDateTime(01,12,2023);
//        System.out.println( solar.toString());
//        System.out.println( "-----------------" );
//        KhmerNumeric numeric = new KhmerNumeric("123456789012345");
//        System.out.println( numeric.toKhmer(true));
//        System.out.println( numeric.toKhmer());
//        System.out.println( numeric.toKhmerText() );
//        System.out.println( "-----------------" );

        KhmerLunarDateTime lunar = new KhmerLunarDateTime(5,2,2023);
        System.out.println(lunar.toString());
        System.out.println(lunar.mapSolarYearToLunarYear());
        System.out.println( "-----------------" );
        System.out.println(Arrays.toString(lunar.mapSolarDayToLunarDay()));
        System.out.println( "+++++++++++++" );

    }
}
