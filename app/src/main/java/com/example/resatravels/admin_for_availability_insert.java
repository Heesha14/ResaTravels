package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class admin_for_availability_insert extends AppCompatActivity {
    EditText foodsAndDrinks,outDoor,CleaningServices,SafetyFeatures,HealthAndWellnessFacilities,Internet,Parking;
    Button btn_FSave;
    DatabaseReference dbRef;

    hotel_facilities hotel_ava;

    private void clearControls() {
        hotel_ava.setFoodsAndDrinks("");
        hotel_ava.setOutDoor("");
        hotel_ava.setCleaningServices("");
        hotel_ava.setSafetyFeatures("");
        hotel_ava.setHealthAndWellnessFacilities("");
        hotel_ava.setInternet("");
        hotel_ava.setParking("");
    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_for_facility_insert);

        foodsAndDrinks = findViewById(R.id.foodsAndDrinks);
        outDoor = findViewById(R.id.outDoor);
        CleaningServices = findViewById(R.id.CleaningServices);
        SafetyFeatures = findViewById(R.id.SafetyFeatures);
        HealthAndWellnessFacilities = findViewById(R.id.HealthAndWellnessFacilities);
        Internet = findViewById(R.id.Internet);
        Parking = findViewById(R.id.Parking);


        btn_FSave= findViewById(R.id.btn_FSave);
        hotel_ava = new hotel_facilities();
        btn_FSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("hotel_facilities");
                try {
                    if (TextUtils.isEmpty(foodsAndDrinks.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Foods And Drinks ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(outDoor.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty OutDoor ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(CleaningServices.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Cleaning Services ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(SafetyFeatures.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Safety Features ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(HealthAndWellnessFacilities.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Health And Wellness Facilities ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(Internet.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Internet ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(Parking.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Parking ", Toast.LENGTH_SHORT).show();

                    else {
                        hotel_ava.setFoodsAndDrinks(foodsAndDrinks.getText().toString().trim());
                        hotel_ava.setOutDoor(outDoor.getText().toString().trim());
                        hotel_ava.setCleaningServices(CleaningServices.getText().toString().trim());
                        hotel_ava.setSafetyFeatures(SafetyFeatures.getText().toString().trim());
                        hotel_ava.setHealthAndWellnessFacilities(HealthAndWellnessFacilities.getText().toString().trim());
                        hotel_ava.setInternet(Internet.getText().toString().trim());
                        hotel_ava.setParking(Parking.getText().toString().trim());

                        //dbRef.push().setValue(std);
                        dbRef.child("hotel_facilities").setValue(hotel_ava);

                        Toast.makeText(getApplicationContext(), "Data saved success", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(new Intent (admin_for_availability_insert.this, Rules.class));
                        startActivity(i);
                        clearControls();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Data saved unsuccessfull", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}