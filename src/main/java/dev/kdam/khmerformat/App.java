package dev.kdam.khmerformat;

import dev.kdam.khmerformat.Utils.KhmerLunarDate;

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

        KhmerLunarDate lunar = new KhmerLunarDate(5,1,2023);
        System.out.println(lunar.getDayOfWeek());
        System.out.println(lunar.getDayOfMonth());
        System.out.println(lunar.getMonth());
        System.out.println( "+++++++++++++" );

    }
}
