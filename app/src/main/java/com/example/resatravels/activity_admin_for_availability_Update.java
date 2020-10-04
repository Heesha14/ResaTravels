package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class activity_admin_for_availability_Update extends AppCompatActivity {

    EditText foodsAndDrinks,  outDoor, CleaningServices, SafetyFeatures, HealthAndWellnessFacilities, Internet, Parking;
    Button btn_delete;

    DatabaseReference myRef;

    hotel_facilities hotel_ava;
    private void clearControls(){
        foodsAndDrinks.setText("");
        outDoor.setText("");
        CleaningServices.setText("");
        SafetyFeatures.setText("");
        HealthAndWellnessFacilities.setText("");
        Internet.setText("");
        Parking.setText("");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_for_facility__update);
        foodsAndDrinks = findViewById(R.id.foodsAndDrinks);
        outDoor = findViewById(R.id.outDoor);
        CleaningServices = findViewById(R.id.CleaningServices);
        SafetyFeatures = findViewById(R.id.SafetyFeatures);
        HealthAndWellnessFacilities = findViewById(R.id.HealthAndWellnessFacilities);
        Internet = findViewById(R.id.Internet);
        Parking = findViewById(R.id.Parking);

        btn_delete = findViewById(R.id.btn_delete);
        hotel_ava = new hotel_facilities();

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef = FirebaseDatabase.getInstance().getReference();

                try{
                    myRef.child("hotel_facilities").child("hotel_ava").child("hotelId");
                    myRef.child("hotel_facilities/hotel_ava/foodsAndDrinks").setValue(foodsAndDrinks.getText().toString().trim());
                    myRef.child("hotel_facilities/hotel_ava/outDoor").setValue(outDoor.getText().toString().trim());
                    myRef.child("hotel_facilities/hotel_ava/CleaningServices").setValue(CleaningServices.getText().toString().trim());
                    myRef.child("hotel_facilities/hotel_ava/SafetyFeatures").setValue(SafetyFeatures.getText().toString().trim());
                    myRef.child("hotel_facilities/hotel_ava/HealthAndWellnessFacilities").setValue(HealthAndWellnessFacilities.getText().toString().trim());
                    myRef.child("hotel_facilities/hotel_ava/Internet").setValue(Internet.getText().toString().trim());
                    myRef.child("hotel_facilities/hotel_ava/Parking").setValue(Parking.getText().toString().trim());

                    Toast.makeText(getApplicationContext(),"Update Success",Toast.LENGTH_SHORT).show();
                    clearControls();
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Update is not success", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}