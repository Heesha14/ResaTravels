package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Heesha_Admin_List_of_Vehicles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heesha__admin__list_of__vehicles);
    }

    public void redirectToListPlaces(View view){
        Intent intent2 = new Intent(this, Heesha_Admin_List_of_Places.class);
        startActivity(intent2);
    }

    public void redirectToAddVehicle(View view){
        Intent intent = new Intent(this, Heesha_Admin_Add_Vehicle.class);
        startActivity(intent);
    }

    public void redirectToEditVehicle(View view){
        Intent intent = new Intent(this, Heesha_Admin_Edit_Vehicle.class);
        startActivity(intent);
    }


}