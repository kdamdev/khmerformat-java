package io.github.kdamdev;

import io.github.kdamdev.khmerformat.KhmerDate;
import io.github.kdamdev.khmerformat.utils.LunarDate;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.time.LocalDate;

/**
 * Unit test for Khmer Format Library.
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
     * testLeungSak Test :-)
     */
    public void testLeungSak2024()
    {
        LunarDate lunar = KhmerDate.LunarDate(LocalDate.of(2024, 4,16 ));
        Assert.assertEquals("ថ្ងៃអង្គារ ៨ កើត ខែចេត្រ ឆ្នាំរោង ឆស័ក ព.ស.២៥៦៧", lunar.toString());
    }
    public void testVanaBat2024()
    {
        LunarDate lunar = KhmerDate.LunarDate(LocalDate.of(2024, 4,15 ));
        Assert.assertEquals("ថ្ងៃចន្ទ ៧ កើត ខែចេត្រ ឆ្នាំរោង បញ្ចស័ក ព.ស.២៥៦៧", lunar.toString());
    }
    public void testNewYear2024()
    {
        LunarDate lunar = KhmerDate.LunarDate(LocalDate.of(2024, 4, 13 ));
        Assert.assertEquals("ថ្ងៃសៅរ៍ ៥ កើត ខែចេត្រ ឆ្នាំរោង បញ្ចស័ក ព.ស.២៥៦៧", lunar.toString());
    }
    public void testYear2014()
    {
        LunarDate lunar = KhmerDate.LunarDate(LocalDate.of(2014, 1, 1 ));
        Assert.assertEquals(lunar.getDayOfMonth(), "១ កើត");
    }

    public void testEndYear2023()
    {
        LunarDate lunar = KhmerDate.LunarDate(LocalDate.of(2023, 12, 31 ));
        Assert.assertEquals("ថ្ងៃអាទិត្យ ៤ រោច ខែមិគសិរ ឆ្នាំថោះ បញ្ចស័ក ព.ស.២៥៦៧", lunar.toString());
    }

    public void testEndYear2024()
    {
        LunarDate lunar = KhmerDate.LunarDate(LocalDate.of(2024, 12, 31 ));
        Assert.assertEquals("ថ្ងៃអង្គារ ២ កើត ខែបុស្ស ឆ្នាំរោង ឆស័ក ព.ស.២៥៦៨", lunar.toString());
    }
    public void testNewYear1878()
    {
        LunarDate lunar = KhmerDate.LunarDate(LocalDate.of(1878, 4, 12 ));
        Assert.assertEquals("ថ្ងៃសុក្រ ១០ កើត ខែចេត្រ ឆ្នាំខាល នព្វស័ក ព.ស.២៤២១", lunar.toString());
    }

    public void test12Feb2000()
    {
        LunarDate lunar = KhmerDate.LunarDate(LocalDate.of(2000, 2, 12 ));
        Assert.assertEquals("ថ្ងៃសៅរ៍ ៨ កើត ខែមាឃ ឆ្នាំថោះ ឯកស័ក ព.ស.២៥៤៣", lunar.toString());
    }
}
