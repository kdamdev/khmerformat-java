package dev.kdam;

import dev.kdam.khmerformat.KhmerDate;
import dev.kdam.khmerformat.utils.LunarDate;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.time.LocalDate;

/**
 * Unit test for simple App.
 */
public class LunarTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public LunarTest(String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( LunarTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testLeungSak()
    {
        LunarDate lunar = KhmerDate.LunarDate(LocalDate.of(2024, 4,16 ));
        Assert.assertEquals(lunar.toString(), "ថ្ងៃអង្គារ ៨ កើត ខែចេត្រ ឆ្នាំរោង ឆស័ក ព.ស.២៥៦៧");
    }
    public void testVanaBat()
    {
        LunarDate lunar = KhmerDate.LunarDate(LocalDate.of(2024, 4,15 ));
        Assert.assertEquals(lunar.toString(), "ថ្ងៃចន្ទ ៧ កើត ខែចេត្រ ឆ្នាំរោង បញ្ចស័ក ព.ស.២៥៦៧");
    }
    public void testNewYear()
    {
        LunarDate lunar = KhmerDate.LunarDate(LocalDate.of(2024, 4, 13 ));
        Assert.assertEquals(lunar.toString(), "ថ្ងៃសៅរ៍ ៥ កើត ខែចេត្រ ឆ្នាំរោង បញ្ចស័ក ព.ស.២៥៦៧");
    }
    public void testEpoch()
    {
        LunarDate lunar = KhmerDate.LunarDate(LocalDate.of(2014, 1,1 ));
//        System.out.println(lunar.getDayOfWeek());
//        System.out.println(lunar.getDayOfMonth());
//        System.out.println(lunar.getMonth());
//        System.out.println(lunar.getZodiacYear());
//        System.out.println(lunar.getEra());
//        System.out.println(lunar.getBeYear());
        Assert.assertEquals(lunar.getDayOfMonth(), "១ កើត");
    }
}
