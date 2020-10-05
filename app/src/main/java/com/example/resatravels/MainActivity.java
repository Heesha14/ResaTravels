package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button button;

import android.widget.Button;
import android.content.Intent;
import android.view.View;
public class MainActivity extends AppCompatActivity {
    public Button button;

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



        button = (Button)findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, All_Safari_Vehicle.class);
                startActivity(intent);
            }


        });



        button = (Button)findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(MainActivity.this, register_Vehicle_safari.class);
                startActivity(intent);
            }


        });

    }

    public void openTravellingAccessoryCus(){
        Intent intent = new Intent (MainActivity.this, TravellingAccessoryCus.class);
        startActivity(intent);
    }


    public void redirectToPlace(View view){
        Intent intent = new Intent(this, Heesha_User_Display_Places.class);
        startActivity(intent);
    }
    public void redirectToPlace3(View view){
        Intent intent2 = new Intent(this, Heesha_Admin_List_of_Places.class);
        startActivity(intent2);
    }

    public void redirectToPlace4(View view){
        Intent intent = new Intent(this, Heesha_Admin_Add_Vehicle.class);
        startActivity(intent);
    }
    public void redirectToPlace5(View view){
        Intent intent2 = new Intent(this, Heesha_User_Profile.class);
        startActivity(intent2);
    }
    public void redirectToPlace6(View view){
        Intent intent = new Intent(this, Heesha_Admin_Edit_Vehicle.class);
        startActivity(intent);
    }

    public void redirectToPlace7(View view){
        Intent intent = new Intent(this, Heesha_Admin_List_of_Places.class);
        startActivity(intent);
    }



}


}

