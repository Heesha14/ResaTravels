package com.example.resatravels;

import android.app.Activity;
import android.app.Instrumentation;
import android.view.View;

import androidx.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.*;

public class Heesha_Admin_Add_VehicleTest {


    @Rule
    public ActivityTestRule<Heesha_Admin_Add_Vehicle> Heesha_Admin_Add_VehicleTest_Rule = new ActivityTestRule<Heesha_Admin_Add_Vehicle>(Heesha_Admin_Add_Vehicle.class);

    private Heesha_Admin_Add_Vehicle mactivity = null;

    @Before
    public void setUp() throws Exception {
        mactivity = Heesha_Admin_Add_VehicleTest_Rule.getActivity();

    }

    @Test
    public void testLaunchNextActivity(){
        View view = mactivity.findViewById(R.id.h_input_type);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mactivity = null;
    }

}