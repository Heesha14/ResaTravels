package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TravellingAccesssoryAdmin1 extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travelling_accesssory_admin1);

        button = (Button) findViewById(R.id.button11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddTravellingAccessoryDetails();
            }
        });

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTravellingAccessoryDetailsAdmin2();
            }
        });
    }

    public void openAddTravellingAccessoryDetails(){
        Intent intent = new Intent (this, AddAccessoryDetailsAdmin.class);
        startActivity(intent);
    }

    public void openTravellingAccessoryDetailsAdmin2(){
        Intent intent = new Intent (this, TravellingAccessoryDetailsAdmin2.class);
        startActivity(intent);
    }
}