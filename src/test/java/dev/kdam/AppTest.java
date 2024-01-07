package dev.kdam;

import dev.kdam.khmerformat.utils.KhmerLunarDate;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testLeungSak()
    {
        KhmerLunarDate lunar = new KhmerLunarDate(16, 4,2024 );
        Assert.assertEquals(lunar.toString(), "ថ្ងៃអង្គារ ៨ កើត ខែចេត្រ ឆ្នាំរោង ឆស័ក ព.ស.២៥៦៧");
    }
    public void testVanaBat()
    {
        KhmerLunarDate lunar = new KhmerLunarDate(15, 4,2024 );
        Assert.assertEquals(lunar.toString(), "ថ្ងៃចន្ទ ៧ កើត ខែចេត្រ ឆ្នាំរោង បញ្ចស័ក ព.ស.២៥៦៧");
    }
    public void testNewYear()
    {
        KhmerLunarDate lunar = new KhmerLunarDate(13, 4,2024 );
        Assert.assertEquals(lunar.toString(), "ថ្ងៃសៅរ៍ ៥ កើត ខែចេត្រ ឆ្នាំរោង បញ្ចស័ក ព.ស.២៥៦៧");
    }
    public void testEpoch()
    {
        KhmerLunarDate lunar = new KhmerLunarDate(1, 1,2014 );
        Assert.assertEquals(lunar.getDayOfMonth(), "១ កើត");
    }
}
