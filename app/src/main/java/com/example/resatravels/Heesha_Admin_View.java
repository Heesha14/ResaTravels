package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Heesha_Admin_View extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heesha__admin__view);
    }


    public void redirectToAddVehicles(View view) {
        Intent intent = new Intent(Heesha_Admin_View.this, Heesha_Admin_Add_Vehicle.class);
        startActivity(intent);
    }
    public void redirectToListPlaces(View view) {
        Intent intent = new Intent(Heesha_Admin_View.this, Heesha_Admin_List_of_Places.class);
        startActivity(intent);
    }
    public void redirectToListVehicles(View view) {
        Intent intent = new Intent(Heesha_Admin_View.this, Heesha_Admin_List_of_Vehicles.class);
        startActivity(intent);
    }

    public void redirectToAddPlaces(View view) {
        Intent intent = new Intent(Heesha_Admin_View.this, Heesha_Admin_Place_Add.class);
        startActivity(intent);
    }
    public void redirectToHome(View view) {
        Intent intent = new Intent(Heesha_Admin_View.this, MainActivity.class);
        startActivity(intent);
    }
    public void redirectToUser(View view) {
        Intent intent = new Intent(Heesha_Admin_View.this, Heesha_User_View.class);
        startActivity(intent);
    }

}