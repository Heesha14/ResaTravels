package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Heesha_Admin_List_of_Places extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heesha__admin__list_of__places);
    }

    public void redirectToListVehicles(View view){
        Intent intent2 = new Intent(this, Heesha_Admin_List_of_Vehicles.class);
        startActivity(intent2);
    }

    public void redirectToAddPlace(View view){
        Intent intent = new Intent(this, Heesha_Admin_Place_Add.class);
        startActivity(intent);
    }

    public void redirectToEditPlace(View view){
        Intent intent = new Intent(this, Heesha_Admin_Edit_place.class);
        startActivity(intent);
    }

}