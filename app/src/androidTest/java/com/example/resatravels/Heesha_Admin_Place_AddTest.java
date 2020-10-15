package com.example.resatravels;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import android.app.Activity;
import android.app.Instrumentation;
import androidx.test.rule.*;
import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class Heesha_Admin_Place_AddTest {

    @Rule
    public ActivityTestRule<Heesha_Admin_Place_Add> Heesha_Admin_Place_AddTest_Rule = new ActivityTestRule<Heesha_Admin_Place_Add>(Heesha_Admin_Place_Add.class);

    private Heesha_Admin_Place_Add mactivity = null;

    Instrumentation.ActivityMonitor monitor= getInstrumentation().addMonitor(Heesha_Admin_View.class.getName(),null,false);

    @Before
    public void setUp() throws Exception {
        mactivity = Heesha_Admin_Place_AddTest_Rule.getActivity();

    }

    @Test
    public void testLaunchNextActivity(){
        assertNotNull(mactivity.findViewById(R.id.view6));
        onView(withId(R.id.view6)).perform(click());

        Activity newactivity =  getInstrumentation().waitForMonitorWithTimeout(monitor,5000);
        assertNotNull(newactivity);
        newactivity.finish();
    }

    @After
    public void tearDown() throws Exception {
        mactivity = null;
    }


}