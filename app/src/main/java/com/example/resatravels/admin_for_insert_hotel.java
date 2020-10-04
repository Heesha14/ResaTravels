package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class admin_for_insert_hotel extends AppCompatActivity {
    EditText hotelName,hotelId,tel1,email,tel2,street,city,country,postalCode;
    Button btn_save;

    DatabaseReference dbRef;
    hotel_description hotel_des;
    private void clearControls() {
        hotelName.setText("");
        hotelId.setText("");
        tel1.setText("");
        tel2.setText("");
        street.setText("");
        city.setText("");
        country.setText("");
        email.setText("");
        postalCode.setText("");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_for_insert_hotel);

        hotelName = findViewById(R.id.hotelName);
        hotelId = findViewById(R.id.hotelId);
        tel1 = findViewById(R.id.tel1);
        tel2 = findViewById(R.id.tel2);
        street = findViewById(R.id.street);
        city = findViewById(R.id.city);
        country = findViewById(R.id.country);
        email = findViewById(R.id.email);
        postalCode=findViewById(R.id.postalCode);


        btn_save= findViewById(R.id.btn_save);
        hotel_des = new hotel_description();
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("hotel_description");
                try {
                    if (TextUtils.isEmpty(hotelName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Hotel Name : ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(hotelId.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Hotel ID : ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(tel1.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Telephone Number1 : ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(email.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Email : ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(street.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Street : ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(city.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty City : ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(country.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Country : ", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(postalCode.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Postal Code : ", Toast.LENGTH_SHORT).show();
                    else {
                        hotel_des.setHotelName(hotelName.getText().toString().trim());
                        hotel_des.setHotelId(hotelId.getText().toString().trim());
                        hotel_des.setTel1(tel1.getText().toString().trim());
                        hotel_des.setTel2(tel2.getText().toString().trim());
                        hotel_des.setStreet(street.getText().toString().trim());
                        hotel_des.setCity(city.getText().toString().trim());
                        hotel_des.setCountry(country.getText().toString().trim());
                        hotel_des.setEmail(email.getText().toString().trim());
                        hotel_des.setPostalCode(postalCode.getText().toString().trim());

                        //dbRef.push().setValue(std);
                        dbRef.child("hotel_description3").setValue(hotel_des);

                        Toast.makeText(getApplicationContext(), "Data saved success", Toast.LENGTH_SHORT).show();
                        Intent i=new Intent(new Intent (admin_for_insert_hotel.this, admin_for_availability_insert.class));
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