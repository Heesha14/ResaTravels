package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toast.makeText(MainActivity.this, "Firebase Connection Successful", Toast.LENGTH_LONG).show();

        button = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTravellingAccessoryCus();
            }
        });

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegistration();
            }
        });

        button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTravellingAccesssoryAdmin1();
            }
        });

    }

    private void openRegistration() {
        Intent intent = new Intent (MainActivity.this, Registration.class);
        startActivity(intent);
    }

    private void openTravellingAccesssoryAdmin1() {
        Intent intent = new Intent (MainActivity.this, TravellingAccesssoryAdmin1.class);
        startActivity(intent);
    }

    public void openTravellingAccessoryCus(){
        Intent intent = new Intent (MainActivity.this, TravellingAccessoryCus.class);
        startActivity(intent);
    }



}
