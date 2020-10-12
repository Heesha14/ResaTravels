package com.example.resatravels;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class Heesha_User_Add_Booking extends AppCompatActivity {

    public static final String TAG1 = "sum";
    private static final String TAG = "display price";
    String result,result2;
    Button mButton,btn_book_vehicle,resetBtn;
    EditText mEdit,mEdit2,mEdit3;
    TextView answer,showprice;
    String sum;
    int number1,number2,number3,ans;
    private String vehcileID = "";
    static String sum1;
    private DatabaseReference vehicleRef;
    private EditText inputText;
    private RecyclerView searchList;
    private String SearchText,priceee;
    private static String priceconf;
    private String place, vehicle, register_plate, check_date, date3, time3;
    private int no_adults,no_children,days;
    private Button AddBookingButton;
    private EditText InputPlace, InputVehicle, InputRegisterPlate,InputCheckDate,InputNoChild,InputNoAdult,InputDays;
    private String bookingRandomKey;
    private ProgressDialog loadingBar;

    public void User_Cancel_Booking_Display_Vehicles(View view){
        Intent intentc = new Intent(this, Heesha_User_Display_Vehicles.class);
        startActivity(intentc);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heesha__user__add__booking);

        inputText = findViewById(R.id.h_user_input_plateno);
        searchList = findViewById(R.id.search_list);
        searchList.setLayoutManager(new LinearLayoutManager(Heesha_User_Add_Booking.this));

        btn_book_vehicle = findViewById(R.id.h_user_book_confirm);
        mButton = findViewById(R.id.btn_total);
        resetBtn = findViewById(R.id.h_user_book_cancel);
        mEdit = findViewById(R.id.h_user_input_childNo);
        mEdit2 = findViewById(R.id.h_user_input_adultNo);
        mEdit3 = findViewById(R.id.h_user_input_daysNo);
        answer = findViewById(R.id.tot_answer);
        showprice = findViewById(R.id.display_price);


        InputPlace = (EditText) findViewById(R.id.h_user_input_place);
        InputVehicle = (EditText) findViewById(R.id.h_user_input_vehicle_type);
        InputCheckDate = (EditText) findViewById(R.id.h_user_book_date);
        InputNoChild = (EditText) findViewById(R.id.h_user_input_childNo);
        InputNoAdult = (EditText) findViewById(R.id.h_user_input_adultNo);
        InputDays = (EditText) findViewById(R.id.h_user_input_daysNo);
        loadingBar = new ProgressDialog(this);

        resetBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                ClearControls();
            }

        });

        btn_book_vehicle.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                SearchText = inputText.getText().toString();
                fillFields();
            }

        });

    }

    public void ClearControls() {

        InputPlace.setText("");
        InputVehicle.setText("");
        InputCheckDate.setText("");
        InputNoChild.setText("");
        InputNoAdult.setText("");
        InputDays.setText("");
    }

    private void fillFields() {
        String pplace= InputPlace.getText().toString();
        String pvehicle= InputVehicle.getText().toString();
        String pplate= inputText.getText().toString();
        if(pplace.equals("")){
            Toast.makeText(Heesha_User_Add_Booking.this, "Enter place", Toast.LENGTH_SHORT).show();
        }
        else if(pvehicle.equals("")){
            Toast.makeText(Heesha_User_Add_Booking.this, "Enter Vehicle Type", Toast.LENGTH_SHORT).show();
        }
        else if(pplate.equals("")){
            Toast.makeText(Heesha_User_Add_Booking.this, "Enter Registration Plate", Toast.LENGTH_SHORT).show();
        }
        else
        onStart();
    }

    @Override
    public void onStart() {
        super.onStart();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Heesha_Vehicle");

        FirebaseRecyclerOptions<Heesha_Vehicle> options = new FirebaseRecyclerOptions.Builder<Heesha_Vehicle>()
                .setQuery(reference.orderByChild("vehicle_plate").startAt(SearchText).endAt(SearchText),Heesha_Vehicle.class)
                .build();
        FirebaseRecyclerAdapter<Heesha_Vehicle,Heesha_Vehicle_View_Holder> adapter = new FirebaseRecyclerAdapter<Heesha_Vehicle, Heesha_Vehicle_View_Holder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull Heesha_Vehicle_View_Holder holder, int position, @NonNull final Heesha_Vehicle model) {
                holder.txtvehicle_price.setText("Rs. "+model.getVehicle_price() + " per day");
                Picasso.get().load(model.getImage()).into(holder.vehicleView1);
                showprice.setText("Änswer = " + model.getVehicle_price());
                ans = Integer.parseInt(model.getVehicle_price());

                if(model.getVehicle_price() == null)
                    //Toast.makeText(Heesha_User_Add_Booking.this, "Invalid Vehicle number",Toast.LENGTH_LONG).show();
                    NavigateToActivitys();

                else
                    NavigateToSecondActivitys();

                holder.vehicleView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(Heesha_User_Add_Booking.this, Heesha_Maintain_Places.class);
                        intent.putExtra("vid", model.getVid());
                        startActivity(intent);
                    }});

            }

            @NonNull
            @Override
            public Heesha_Vehicle_View_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_heesha_admin_vehicle_layout, parent, false);
                Heesha_Vehicle_View_Holder holder = new Heesha_Vehicle_View_Holder(view);
                return holder;
            }
        };
        searchList.setAdapter(adapter);
        adapter.startListening();
    }

    private void NavigateToActivitys() {

        if(!InputVehicle.getText().toString().isEmpty())
            Toast.makeText(Heesha_User_Add_Booking.this, "Invalid Vehicle number",Toast.LENGTH_LONG).show();
    }


    private void NavigateToSecondActivitys() {

        if(mEdit.getText().toString().isEmpty() || mEdit2.getText().toString().isEmpty()||mEdit3.getText().toString().isEmpty()||InputCheckDate.getText().toString().isEmpty()||InputPlace.getText().toString().isEmpty()||InputVehicle.getText().toString().isEmpty())
            Toast.makeText(Heesha_User_Add_Booking.this, "Fill all  fields!",Toast.LENGTH_LONG).show();

        else {
            number1 = Integer.parseInt(mEdit.getText().toString());
            number2 = Integer.parseInt(mEdit2.getText().toString());
            number3 = Integer.parseInt(mEdit3.getText().toString());
            sum = String.valueOf(((number1 + number2)*number3)*ans);
            answer.setText("Änswer = " + sum);
            sum1 = sum;
            alertMessage();
        }

    }

    public void alertMessage(){

        AlertDialog.Builder builder = new AlertDialog.Builder(Heesha_User_Add_Booking.this);
        builder.setMessage("Thank you")
                .setPositiveButton("ok", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //Toast.makeText(Heesha_User_Add_Booking.this, "Booking Successfully Confirmed!",Toast.LENGTH_LONG).show();
                        ValidateBookingData();
                    }

                });

        builder.setMessage("Thank you")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(Heesha_User_Add_Booking.this, "Booking Cancelled!",Toast.LENGTH_LONG).show();

                    }

                });
        AlertDialog alert = builder.create();
        alert.setTitle("Confirm Booking for Rs: "+sum1);
        alert.show();
    }

    private void ValidateBookingData() {

        place = InputPlace.getText().toString();
        vehicle = InputVehicle.getText().toString();
        register_plate = inputText.getText().toString();
        check_date = InputCheckDate.getText().toString();
        no_adults = Integer.parseInt(InputNoAdult.getText().toString());
        no_children = Integer.parseInt(InputNoChild.getText().toString());
        days = Integer.parseInt(InputDays.getText().toString());

        loadingBar.setTitle("Booking is successfully confirmed");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        date3 = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        time3 = currentTime.format(calendar.getTime());

        bookingRandomKey = date3 + time3;
        SaveBookingInfoToDatabase();
    }

    private void SaveBookingInfoToDatabase() {
       DatabaseReference BookingRef = FirebaseDatabase.getInstance().getReference().child("Heesha_User_Booking");

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
                            Intent intent = new Intent(Heesha_User_Add_Booking.this, Heesha_User_View.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(Heesha_User_Add_Booking.this, "Booking successful..", Toast.LENGTH_SHORT).show();
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

    public void redirectToHome(View view) {
        Intent intent = new Intent(Heesha_User_Add_Booking.this, Heesha_User_View.class);
        startActivity(intent);
    }

}