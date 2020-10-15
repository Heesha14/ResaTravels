package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TravellingAccessoryCus extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travelling_accessory_cus);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTravellingAccessoryInfo();
            }
        });
    }

    public void openTravellingAccessoryInfo(){
        Intent intent = new Intent (this, TavellingAccessoryInfo.class);
        startActivity(intent);
    }
}