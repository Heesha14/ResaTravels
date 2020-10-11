package com.example.resatravels;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    private TavellingAccessoryInfo tavellingAccessoryInfo;


    @Before
    public void setUp(){
        tavellingAccessoryInfo = new TavellingAccessoryInfo();
    }

    @Test
    public void viewTotal(){

        assertEquals(1000.0, 500.0 * 2 ,.2);
        assertEquals(6000.0, 1000.0 * 6,.2);
        assertEquals(2500.0, 2500.0 * 1,.2);
        assertEquals(10000.0, 1000.0 * 10,.2);
    }

    @Test
    public void confirmOrder(){

        assertEquals(5, 10 - 5);
        assertEquals(100, 200 - 100);
        assertEquals(0, 10 - 10);
        assertEquals(1, 1000 - 999);

    }


    /*
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }*/
}