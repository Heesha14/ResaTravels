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

public class Admin_For_Update extends AppCompatActivity {
    Button btn_update;
    EditText hotelName, hotelId, tel1, tel2, email, street, city, country, postalCode;
    //Spinner hotelRating;
    DatabaseReference myRef;

    hotel_description hotel_dis;
    private void clearControls(){
        hotelName.setText("");
        hotelId.setText("");
        tel1.setText("");
        tel2.setText("");
        email.setText("");
        street.setText("");
        city.setText("");
        country.setText("");
        postalCode.setText("");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__for__update);

        hotelName = findViewById(R.id.hotelName);
        hotelId = findViewById(R.id.hotelId);
        tel1 = findViewById(R.id.tel1);
        tel2 = findViewById(R.id.tel2);
        email = findViewById(R.id.email);
        street = findViewById(R.id.street);
        city = findViewById(R.id.city);
        country = findViewById(R.id.country);
        postalCode = findViewById(R.id.postalCode);

        btn_update = findViewById(R.id.btn_update);
        hotel_dis = new hotel_description();

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef = FirebaseDatabase.getInstance().getReference();

                try{
                    myRef.child("hotel_description").child("hotel_dis1").child("hotelId").setValue(hotelId.getText().toString().trim());
                    myRef.child("hotel_description/hotel_dis1/tel1").setValue(tel1.getText().toString().trim());
                    myRef.child("hotel_description/hotel_dis1/tel2").setValue(tel2.getText().toString().trim());
                    myRef.child("hotel_description/hotel_dis1/email").setValue(email.getText().toString().trim());
                    myRef.child("hotel_description/hotel_dis1/street").setValue(street.getText().toString().trim());
                    myRef.child("hotel_description/hotel_dis1/city").setValue(city.getText().toString().trim());
                    myRef.child("hotel_description/hotel_dis1/country").setValue(country.getText().toString().trim());
                    myRef.child("hotel_description/hotel_dis1/postalCode").setValue(postalCode.getText().toString().trim());

                    Toast.makeText(getApplicationContext(),"Update Success",Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(new Intent (Admin_For_Update.this, activity_admin_for_availability_Update.class));
                    startActivity(i);
                    clearControls();
                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(), "Update is not success", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}