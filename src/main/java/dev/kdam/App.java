package dev.kdam;

import dev.kdam.utils.KhmerNumeric;
import dev.kdam.utils.KhmerSolarDateTime;

import java.math.BigDecimal;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
        KhmerSolarDateTime solar = new KhmerSolarDateTime(01,12,2023);
        System.out.println( solar.toString());
        System.out.println( "-----------------" );
        KhmerNumeric numeric = new KhmerNumeric(  "738474659311");
        System.out.println( numeric.toKhmer(true));
        System.out.println( numeric.toKhmer());
        System.out.println( numeric.toKhmerText() );
        System.out.println( "-----------------" );
    }
}
