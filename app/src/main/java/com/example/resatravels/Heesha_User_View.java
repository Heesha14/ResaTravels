package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Heesha_User_View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heesha__user__view);
    }

    public void redirectToVehicles(View view) {
        Intent intent = new Intent(Heesha_User_View.this, Heesha_User_Display_Vehicles.class);
        startActivity(intent);
    }
    public void redirectToPlaces(View view) {
        Intent intent = new Intent(Heesha_User_View.this, Heesha_User_Display_Places.class);
        startActivity(intent);
    }
    public void redirectToBoking(View view) {
        Intent intent = new Intent(Heesha_User_View.this, Heesha_User_Add_Booking.class);
        startActivity(intent);
    }

    public void redirectToHome(View view) {
        Intent intent = new Intent(Heesha_User_View.this, MainActivity.class);
        startActivity(intent);
    }

    public void redirectToAdmin(View view) {
        Intent intent = new Intent(Heesha_User_View.this, Heesha_Admin_View.class);
        startActivity(intent);
    }

}