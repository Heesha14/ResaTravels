package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Heesha_User_Display_Vehicles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heesha__user__display__vehicles);

        Button btn_vehicle_booking = findViewById(R.id.h_click_from_vehicle_to_booking);
        btn_vehicle_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHeesha_User_Add_Booking();
            }

        });
    }

    private void openHeesha_User_Add_Booking() {
        Intent intent= new Intent(this,Heesha_User_Add_Booking.class);
        startActivity(intent);
    }
}