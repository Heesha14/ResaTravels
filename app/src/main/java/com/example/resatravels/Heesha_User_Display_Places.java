package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Heesha_User_Display_Places extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heesha__user__display__places);
    }
    public void redirectToVehicle(View view){
        Intent intent = new Intent(this, Heesha_User_Display_Vehicles.class);
        startActivity(intent);
    }
}