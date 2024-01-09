package dev.kdam;

import io.github.kdamdev.khmerformat.KhmerNumeric;
import io.github.kdamdev.khmerformat.utils.Numeric;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class NumericTest extends TestCase {
    public NumericTest(String testName )
    {
        super( testName );
    }

    public static Test suite()
    {
        return new TestSuite( NumericTest.class );
    }
    public void testNumber()
    {
        Numeric numeric = KhmerNumeric.Numeric("123456789");
        Assert.assertEquals("១២៣៤៥៦៧៨៩", numeric.toKhmer());
    }

    public void testNumberComma()
    {
        Numeric numeric = KhmerNumeric.Numeric("123456789");
        Assert.assertEquals("១២៣,៤៥៦,៧៨៩", numeric.toKhmer(true));
    }
    public void testNumberText()
    {
        Numeric numeric = KhmerNumeric.Numeric("123456789");
        Assert.assertEquals("មួយរយម្ភៃបីលានបួនរយហាសិបប្រាំមួយពាន់ប្រាំពីររយប៉ែតសិបប្រាំបួន", numeric.toKhmerText());
    }
}
