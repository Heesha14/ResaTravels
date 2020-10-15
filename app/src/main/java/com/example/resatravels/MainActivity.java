package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity{
        public Button button,button2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);


            button = (Button) findViewById(R.id.button5);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openTravellingAccessoryCus();
                }
            });


            button2 = (Button) findViewById(R.id.button2);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    redirectToPlaces();
                }
            });

//
//            button = (Button) findViewById(R.id.button1);
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(MainActivity.this, All_Safari_Vehicle.class);
//                    startActivity(intent);
//                }
//
//
//            });
//
//
//            button = (Button) findViewById(R.id.button2);
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(MainActivity.this, register_Vehicle_safari.class);
//                    startActivity(intent);
//                }
//
//
//            });

        }

        public void openTravellingAccessoryCus() {
            Intent intent = new Intent(MainActivity.this, TravellingAccessoryCus.class);
            startActivity(intent);
        }
        public void redirectToPlaces() {
            Intent intent = new Intent(MainActivity.this, Heesha_User_View.class);
            startActivity(intent);
        }


    }
