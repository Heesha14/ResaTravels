package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Heesha_User_Add_Booking extends AppCompatActivity {

    public void User_Cancel_Booking_Display_Vehicles(View view){
        Intent intentc = new Intent(this, Heesha_User_Display_Vehicles.class);
        startActivity(intentc);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heesha__user__add__booking);
        Button btn_book_vehicle = findViewById(R.id.h_user_book_confirm);
        btn_book_vehicle.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }

    private void openDialog() {
        Heesha_Booking_Confirm_Pop_Up dialogPopUp = new Heesha_Booking_Confirm_Pop_Up();
        dialogPopUp.show(getSupportFragmentManager(),"example dialog");
    }

    private void openHeesha_User_Cancel_Booking_Display_Vehicles() {
        Intent intent= new Intent(this, Heesha_User_Display_Vehicles.class);
        startActivity(intent);
    }



}