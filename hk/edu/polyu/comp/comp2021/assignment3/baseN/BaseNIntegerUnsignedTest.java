package hk.edu.polyu.comp.comp2021.assignment3.baseN;

import org.junit.Before;
import org.junit.Test;
import static hk.edu.polyu.comp.comp2021.assignment3.baseN.BaseNIntegerUnsigned.*;

import static org.junit.Assert.*;

public class BaseNIntegerUnsignedTest {
    int v0 = 0;
    int v1 = 0;
    int v2 = 1;
    int v3 = 160;
    int v4 = 95;
    int v5 = 32;
    int v6 = 999;
    int v8 = 17575;

    int base11 = 11;
    int base5 = 5;
    int base1 = 1;
    int base2 = 2;
    int base10 = 10;
    int base26 = 26;
    int base27 = 27;
    int base25 = 25;

    BaseNIntegerUnsigned u0;
    BaseNIntegerUnsigned u1;
    BaseNIntegerUnsigned u2;
    BaseNIntegerUnsigned u3;
    BaseNIntegerUnsigned u4;
    BaseNIntegerUnsigned u5;
    BaseNIntegerUnsigned u6;
    BaseNIntegerUnsigned u8;
    BaseNIntegerUnsigned u9;
    BaseNIntegerUnsigned u10;

    String u1Magnitude = "A";
    String u2Magnitude = "B";
    String u3Magnitude = "BDG";
    String u4Magnitude = "DEA";
    String u5Magnitude = "BBC";
    String u7Magnitude = "CBB";
    String u8Magnitude = "ZZZ";
    String u9Magnitude = "BBB";
    String u10Magnitude = "ZZY";


    String u1String = "A(11)";
    String u2String = "B(11)";
    String u3String = "BDG(11)";
    String u4String = "DEA(5)";
    String u5String = "BBC(5)";

    @Before
    public void before(){
        u0 = new BaseNIntegerUnsigned(encode(v0, base11), base11);
        u1 = new BaseNIntegerUnsigned(DIGIT_ZERO + encode(v1, base11), base11);
        u2 = new BaseNIntegerUnsigned(DIGIT_ZERO + (DIGIT_ZERO + encode(v2, base11)), base11); //"AAB(11)"
        u3 = new BaseNIntegerUnsigned(encode(v3, base11), base11);

        u4 = new BaseNIntegerUnsigned(encode(v4, base5), base5);
        u5 = new BaseNIntegerUnsigned(encode(v5, base5), base5);

        u6 = new BaseNIntegerUnsigned(encode(v6, base10), base10);

        u8 = new BaseNIntegerUnsigned(u8Magnitude, base26);
        u9 = new BaseNIntegerUnsigned(u9Magnitude, base26);
        u10 = new BaseNIntegerUnsigned(u10Magnitude, base26);

    }

    @Test
    public void testConstructor01(){
        assertEquals(v0, u0.toInteger());
        assertEquals(v1, u1.toInteger());
        assertEquals(v2, u2.toInteger());
        assertEquals(v3, u3.toInteger());
        assertEquals(v4, u4.toInteger());
        assertEquals(v5, u5.toInteger());
        assertEquals(v8, u8.toInteger());

        // If flaged, u1 ("AA") was not equal to "A". Technically ignorable but the design of the test suggest that
        // you should run magnitude through WithoutLeadingZeros() in the constructor before assigning it
        assertEquals(u1Magnitude, u1.getMagnitude());
    }

    @Test
    public void testIsValidBase01() {
        assertTrue(BaseNIntegerUnsigned.isValidBase(base11));
        assertTrue(BaseNIntegerUnsigned.isValidBase(base5));
        assertTrue(BaseNIntegerUnsigned.isValidBase(base26));
        assertFalse(BaseNIntegerUnsigned.isValidBase(base1));
        assertTrue(BaseNIntegerUnsigned.isValidBase(base2));
        assertFalse(BaseNIntegerUnsigned.isValidBase(base27));
    }

    @Test
    public void testIsValidMagnitude01() {
        assertTrue(BaseNIntegerUnsigned.isValidMagnitude(u1Magnitude, base11));
        assertFalse(BaseNIntegerUnsigned.isValidMagnitude(u3Magnitude, base5));

        //Testing on the "corner values" (first and last)
        assertFalse(BaseNIntegerUnsigned.isValidMagnitude(u5Magnitude, base2));
        assertFalse(BaseNIntegerUnsigned.isValidMagnitude(u7Magnitude, base2));

        //Testing for edges of base 26
        assertTrue(BaseNIntegerUnsigned.isValidMagnitude(u8Magnitude, base26));
        assertFalse(BaseNIntegerUnsigned.isValidMagnitude(u8Magnitude, base25));


    }

    @Test
    public void testToInteger01(){
        assertEquals(v3, u3.toInteger());
        assertEquals(v4, u4.toInteger());

        //testing for base 26
        assertEquals(v8, u8.toInteger());
    }

    @Test
    public void testToString01(){
        assertEquals(u1String, u1.toString());
        assertEquals(u2String, u2.toString());
        assertEquals(u3String, u3.toString());
    }

    @Test
    public void testEquals01(){
        assertEquals(u0, u1);
        assertNotEquals(u1, u2);
        assertNotEquals(u1, new Object());
    }

    @Test
    public void testCompare01(){
        assertTrue(u1.compare(u2) < 0);
        assertTrue(u2.compare(u1) > 0);
        assertTrue(u0.compare(u1) == 0);
        assertTrue(u8.compare(u8) == 0);
        assertTrue(u2.compare(u2) == 0);
        assertTrue(u1.compare(u1) == 0);
        assertTrue(u10.compare(u10) == 0);
    }

    @Test
    public void testEncode01(){
        assertEquals(u2Magnitude, BaseNIntegerUnsigned.encode(v2, base5));
        assertEquals(u3Magnitude, BaseNIntegerUnsigned.encode(v3, base11));
    }

    @Test
    public void testAdd01(){
        assertEquals(v2 + v3, u2.add(u3).toInteger());
        assertEquals(v1 + v2, u1.add(u2).toInteger());

        // Testing for the last carry being taken in account
        assertEquals( v6 + v6, u6.add(u6).toInteger());

        // Testing to add the max of base 26 to itself
        assertEquals( v8 + v8, u8.add(u8).toInteger());
    }

    @Test
    public void testSubtract01(){
        assertEquals(v2 - v1, u2.subtract(u1).toInteger());
        assertEquals(v4 - v5, u4.subtract(u5).toInteger());
        assertEquals(v4 - v5, u4.subtract(u5).toInteger());
        assertEquals(v6 - v6, u6.subtract(u6).toInteger());

        //Testing to substracting the max of base 26 to itself
        assertEquals( v8 - v8, u8.subtract(u8).toInteger());

    }

}
