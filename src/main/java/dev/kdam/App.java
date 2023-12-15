package dev.kdam;

import dev.kdam.utils.KhmerLunarDateTime;
import dev.kdam.utils.KhmerNumeric;
import dev.kdam.utils.KhmerSolarDateTime;

import java.math.BigDecimal;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        //System.out.println( "Hello World!" );
        KhmerSolarDateTime solar = new KhmerSolarDateTime(01,12,2023);
        System.out.println( solar.toString());
        System.out.println( "-----------------" );
        KhmerNumeric numeric = new KhmerNumeric("123456789012345");
        System.out.println( numeric.toKhmer(true));
        System.out.println( numeric.toKhmer());
        System.out.println( numeric.toKhmerText() );
        System.out.println( "-----------------" );

        KhmerLunarDateTime lunar = new KhmerLunarDateTime(01,12,2023);
        System.out.println(lunar.toString());



    }
}
