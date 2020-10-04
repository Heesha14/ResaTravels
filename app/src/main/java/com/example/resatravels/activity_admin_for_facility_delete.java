package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_admin_for_facility_delete extends AppCompatActivity {
    EditText foodsAndDrinks,outDoor,CleaningServices,SafetyFeatures,HealthAndWellnessFacilities,Internet,Parking;
    Button btn_delete;
    DatabaseReference dbRef;

    hotel_facilities hotel_fac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_for_facility_delete);
        foodsAndDrinks = findViewById(R.id.foodsAndDrinks);
        outDoor = findViewById(R.id.outDoor);
        CleaningServices = findViewById(R.id.CleaningServices);
        SafetyFeatures = findViewById(R.id.SafetyFeatures);
        HealthAndWellnessFacilities = findViewById(R.id.HealthAndWellnessFacilities);
        Internet = findViewById(R.id.Internet);
        Parking = findViewById(R.id.Parking);

        btn_delete = findViewById(R.id.btn_delete);
        hotel_fac = new hotel_facilities();

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("hotel_facilities").child("hotel_fac1");

                try{
                    dbRef.removeValue();
                    Toast.makeText(getApplicationContext(),"Delete Success",Toast.LENGTH_SHORT).show();

                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Delete is not success", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}