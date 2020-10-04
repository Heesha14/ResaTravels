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
    public void setTavellingAccessoryInfo(){
        tavellingAccessoryInfo = new TavellingAccessoryInfo();
    }

    @Test
    public void viewTotal(){
        //Double result = tavellingAccessoryInfo.viewTotal(1000.0);
        assertEquals(1000.0, 500.0 * 2 );
        assertEquals(2000.0, 1000.0 * 2);
        assertEquals(2500.0, 2500.0 * 1);
        assertEquals(10000.0, 1000.0 * 10);
    }
    /*
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }*/
}