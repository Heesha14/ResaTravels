package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Heesha_User_Add_Booking extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heesha__user__add__booking);
        Button btn_booking_vehicle = findViewById(R.id.h_user_book_cancel);
        btn_booking_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openHeesha_User_Cancel_Booking_Display_Vehicles();
            }

        });
    }

    private void openHeesha_User_Cancel_Booking_Display_Vehicles() {
        Intent intent= new Intent(this, Heesha_User_Display_Vehicles.class);
        startActivity(intent);
    }
}