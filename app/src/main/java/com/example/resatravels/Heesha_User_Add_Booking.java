package com.example.resatravels;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Heesha_User_Add_Booking extends AppCompatActivity {
    private String place, vehicle, register_plate, check_date, date3, time3;
    private int no_adults,no_children,days;
    private Button AddBookingButton;
    private EditText InputPlace, InputVehicle, InputRegisterPlate,InputCheckDate,InputNoChild,InputNoAdult,InputDays;
    private String bookingRandomKey, downloadImageUrl;
    private DatabaseReference BookingRef;
    private ProgressDialog loadingBar;

    public void User_Cancel_Booking_Display_Vehicles(View view){
        Intent intentc = new Intent(this, Heesha_User_Display_Vehicles.class);
        startActivity(intentc);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heesha__user__add__booking);
        Button btn_book_vehicle = findViewById(R.id.h_user_book_confirm);

        BookingRef = FirebaseDatabase.getInstance().getReference().child("Heesha_User_Booking");


        AddBookingButton = (Button) findViewById(R.id.h_user_book_confirm);
        InputPlace = (EditText) findViewById(R.id.h_user_input_place);
        InputVehicle = (EditText) findViewById(R.id.h_user_input_vehicle_type);
        InputRegisterPlate = (EditText) findViewById(R.id.h_user_input_plateno);
        InputCheckDate = (EditText) findViewById(R.id.h_user_book_date);
        InputNoChild = (EditText) findViewById(R.id.h_user_input_childNo);
        InputNoAdult = (EditText) findViewById(R.id.h_user_input_adultNo);
        InputDays = (EditText) findViewById(R.id.h_user_input_daysNo);
        loadingBar = new ProgressDialog(this);


        AddBookingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                ValidateBookingData();
            }
        });


//        btn_book_vehicle.setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view) {
//                openDialog();
//            }
//        });
    }

    private void openDialog() {
        Heesha_Booking_Confirm_Pop_Up dialogPopUp = new Heesha_Booking_Confirm_Pop_Up();
        dialogPopUp.show(getSupportFragmentManager(),"example dialog");
    }

    private void openHeesha_User_Cancel_Booking_Display_Vehicles() {
        Intent intent= new Intent(this, Heesha_User_Display_Vehicles.class);
        startActivity(intent);
    }


    private void ValidateBookingData()
    {
        place = InputPlace.getText().toString();
        vehicle = InputVehicle.getText().toString();
        register_plate = InputRegisterPlate.getText().toString();
        check_date = InputCheckDate.getText().toString();
        no_adults = Integer.parseInt(InputNoAdult.getText().toString());
        no_children = Integer.parseInt(InputNoChild.getText().toString());
        days = Integer.parseInt(InputDays.getText().toString());


        if (TextUtils.isEmpty(place))
        {
            Toast.makeText(this, "Please write product place...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(vehicle))
        {
            Toast.makeText(this, "Please write product vehicle...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(register_plate))
        {
            Toast.makeText(this, "Please write register_plate name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(check_date))
        {
            Toast.makeText(this, "Please write product check_date...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(InputNoAdult.getText().toString()))
        {
            Toast.makeText(this, "Please write no_adults no_adults...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(InputNoChild.getText().toString()))
        {
            Toast.makeText(this, "Please write product no_children...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(InputDays.getText().toString()))
        {
            Toast.makeText(this, "Please write days...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //StoreBookingInformation();
            loadingBar.setTitle("Add New Product");
            loadingBar.setMessage("Dear Admin, please wait while we are adding the new product.");
            loadingBar.setCanceledOnTouchOutside(false);
            loadingBar.show();

            Calendar calendar = Calendar.getInstance();

            SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
            date3 = currentDate.format(calendar.getTime());

            SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
            time3 = currentTime.format(calendar.getTime());

            bookingRandomKey = date3 + time3;
            SaveProductInfoToDatabase();
        }
    }


    private void SaveProductInfoToDatabase()
    {
        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("bid", bookingRandomKey);
        productMap.put("date3", date3);
        productMap.put("time3", time3);
        productMap.put("place", place);
        productMap.put("vehicle", vehicle);
        productMap.put("register_plate", register_plate);
        productMap.put("check_date", check_date);
        productMap.put("no_adults", no_adults);
        productMap.put("no_children", no_children);
        productMap.put("days", days);

        BookingRef.child(bookingRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(Heesha_User_Add_Booking.this, Heesha_Admin_Place_Add.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(Heesha_User_Add_Booking.this, "Product is added successfully..", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(Heesha_User_Add_Booking.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }






}