package dev.kdam;

import dev.kdam.Helper.KhmerLunarDateTime;
import dev.kdam.Helper.KhmerNumeric;
import dev.kdam.Helper.KhmerSolarDateTime;
import dev.kdam.Utils.JourneyMoon;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Hello world!
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
//        2008
//        System.out.println( "-----------------" );

        KhmerLunarDateTime lunar = new KhmerLunarDateTime( 1, 1, 2023 );
        System.out.println( lunar.toString() );
        System.out.println( lunar.calculateSoryaLeangsak().get( 0 ).getLesserEra() );
        System.out.println( lunar.calculateSoryaLeangsak().get( 0 ).getHarkun() );
        System.out.println( lunar.calculateSoryaLeangsak().get( 0 ).getKromathopol() );
        System.out.println( lunar.calculateSoryaLeangsak().get( 0 ).getAvaman() );
        System.out.println( lunar.calculateSoryaLeangsak().get( 0 ).getBodethey() );
        System.out.println( "-----------------" );
        System.out.println( lunar.calculateSoryaLeangsak().get( 1 ).getLesserEra() );
        System.out.println( lunar.calculateSoryaLeangsak().get( 1 ).getHarkun() );
        System.out.println( lunar.calculateSoryaLeangsak().get( 1 ).getKromathopol() );
        System.out.println( lunar.calculateSoryaLeangsak().get( 1 ).getAvaman() );
        System.out.println( lunar.calculateSoryaLeangsak().get( 1 ).getBodethey() );
        System.out.println( "-----------------" );
        System.out.println( lunar.isAkthimeas() );
        ///


    }
}
