package dev.kdam;

import dev.kdam.utils.KhmerNumeric;
import dev.kdam.utils.SolarDateTime;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
        SolarDateTime solar = new SolarDateTime(01,12,2023);
        System.out.println( solar.toString());
        System.out.println( "-----------------" );
        KhmerNumeric numeric = new KhmerNumeric(1000.00000000005);
        System.out.println( numeric.toKhmer(true));
        System.out.println( numeric.toKhmer());
        System.out.println( numeric.toKhmerText() );
    }
}
