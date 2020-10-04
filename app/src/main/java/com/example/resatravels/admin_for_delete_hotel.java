package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class admin_for_delete_hotel extends AppCompatActivity {
    Button btn_delete;
    EditText hotelName, hotelId, tel1, tel2, email, street, city, country, postalCode;
    //Spinner hotelRating;
    DatabaseReference myRef;

    hotel_description hotel_dis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_for_delete_hotel);
        hotelName = findViewById(R.id.hotelName);
        hotelId = findViewById(R.id.hotelId);
        tel1 = findViewById(R.id.tel1);
        tel2 = findViewById(R.id.tel2);
        email = findViewById(R.id.email);
        street = findViewById(R.id.street);
        city = findViewById(R.id.city);
        country = findViewById(R.id.country);
        postalCode = findViewById(R.id.postalCode);

        btn_delete = findViewById(R.id.btn_update);
        hotel_dis = new hotel_description();

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef = FirebaseDatabase.getInstance().getReference().child("hotel_description").child("hotel_dis1");

                try{
                    myRef.removeValue();
                    Toast.makeText(getApplicationContext(),"Delete Success",Toast.LENGTH_SHORT).show();

                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Delete is not success", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}