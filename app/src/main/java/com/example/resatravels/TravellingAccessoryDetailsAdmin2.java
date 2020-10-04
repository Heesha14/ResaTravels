package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TravellingAccessoryDetailsAdmin2 extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travelling_accessory_details_admin2);

        button = (Button) findViewById(R.id.button12);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUpdateTravellingAccessoryDetails();
            }
        });

    }

    public void openUpdateTravellingAccessoryDetails(){
        Intent intent = new Intent (this, UpdateAccessoryDetailsAdmin.class);
        startActivity(intent);
    }
}