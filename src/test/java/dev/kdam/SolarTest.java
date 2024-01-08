package dev.kdam;

import dev.kdam.khmerformat.KhmerDate;
import dev.kdam.khmerformat.KhmerNumeric;
import dev.kdam.khmerformat.utils.Numeric;
import dev.kdam.khmerformat.utils.SolarDate;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.time.LocalDate;

public class SolarTest extends TestCase {
    public SolarTest(String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( SolarTest.class );
    }
    public void testToString()
    {
        SolarDate solarDate = KhmerDate.SolarDate(LocalDate.of(2024, 4, 13 ));
//        System.out.println(solarDate.getDay());
//        System.out.println(solarDate.getMonth());
//        System.out.println(solarDate.getYear());
        Assert.assertEquals("ថ្ងៃទី១៣ ខែមេសា ឆ្នាំ២០២៤", solarDate.toString());
    }
}
