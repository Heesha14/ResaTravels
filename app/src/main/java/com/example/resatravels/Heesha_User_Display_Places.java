package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Heesha_User_Display_Places extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heesha__user__display__places);

        Button btn_place_vehicle = findViewById(R.id.h_click_from_place_to_vehicle);
        btn_place_vehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openHeesha_User_Display_Vehicles();
            }

        });
    }

    private void openHeesha_User_Display_Vehicles() {
        Intent intent= new Intent(this, Heesha_User_Display_Vehicles.class);
        startActivity(intent);
    }
}
