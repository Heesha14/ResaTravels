package com.example.resatravels;

import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.Toast;

        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;
        import com.squareup.picasso.Picasso;

        import java.util.HashMap;

public class Heesha_Maintain_Vehicle extends AppCompatActivity {

    private Button applychangebtn,deletebtn;
    private EditText v_type,v_make,v_plate,v_location,v_passenger,v_price,v_desc,v_owner,v_mobile;
    private ImageView vehicle_image;
    private String vehcileID = "";
    private DatabaseReference vehicleRef,delRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heesha__maintain__vehicle);

        vehcileID = getIntent().getStringExtra("vid");

        vehicleRef = FirebaseDatabase.getInstance().getReference().child("Heesha_Vehicle").child(vehcileID);

        applychangebtn = findViewById(R.id.h_vehicle_btn);
        v_type = findViewById(R.id.h_input_type_v);
        v_make = findViewById(R.id.h_input_make_v);
        v_plate = findViewById(R.id.h_input_plate_v);
        v_location = findViewById(R.id.h_input_location_v);
        v_passenger = findViewById(R.id.h_input_people_v);
        v_price = findViewById(R.id.h_input_price_v);
        v_desc = findViewById(R.id.h_input_desc_v);
        v_owner = findViewById(R.id.h_input_name_v);
        v_mobile = findViewById(R.id.h_input_mobile_v);
        vehicle_image = findViewById(R.id.h_vehicle_image);
        deletebtn = findViewById(R.id.h_vehicle_btn1);

       displayVehicleInfo();

        applychangebtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                applychanges1();

            }});


        deletebtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                deleteVehicleInfo();

            }});

    }

    private void applychanges1(){
        String vType = v_type.getText().toString();
        String vMake = v_make.getText().toString();
        String vPlate = v_plate.getText().toString();
        String vLocation = v_location.getText().toString();
        String vPassenger = v_passenger.getText().toString();
        String vPrice = v_price.getText().toString();
        String vDesc = v_desc.getText().toString();
        String vOwner = v_owner.getText().toString();
        String vMobile = v_mobile.getText().toString();

        if(vType.equals("")){
            Toast.makeText(Heesha_Maintain_Vehicle.this, "Vehicle Type cannot be empty..", Toast.LENGTH_SHORT).show();
        }
        else if(vMake.equals("")){
            Toast.makeText(Heesha_Maintain_Vehicle.this, "Vehicle Make cannot be empty..", Toast.LENGTH_SHORT).show();
        }
        else if(vPlate.equals("")){
            Toast.makeText(Heesha_Maintain_Vehicle.this, "Vehicle Registration Plate cannot be empty..", Toast.LENGTH_SHORT).show();
        }
        else if(vLocation.equals("")){
            Toast.makeText(Heesha_Maintain_Vehicle.this, "Location of vehicle cannot be empty..", Toast.LENGTH_SHORT).show();
        }
        else if(vPassenger.equals("")){
            Toast.makeText(Heesha_Maintain_Vehicle.this, "Maximum Passengers cannot be empty..", Toast.LENGTH_SHORT).show();
        }
        else if(vPrice.equals("")){
            Toast.makeText(Heesha_Maintain_Vehicle.this, "Maximum Passengers cannot be empty..", Toast.LENGTH_SHORT).show();
        }
        else if(vDesc.equals("")){
            Toast.makeText(Heesha_Maintain_Vehicle.this, "Description cannot be empty..", Toast.LENGTH_SHORT).show();
        }
        else if(vOwner.equals("")){
            Toast.makeText(Heesha_Maintain_Vehicle.this, "vehicle Owner cannot be empty..", Toast.LENGTH_SHORT).show();
        }
        else if(vMobile.equals("")){
            Toast.makeText(Heesha_Maintain_Vehicle.this, "vMobile is added successfully..", Toast.LENGTH_SHORT).show();
        }
        else
        {

            HashMap<String, Object> vehicleMap = new HashMap<>();
            vehicleMap.put("vid", vehcileID);
            vehicleMap.put("vehicle_type", vType);
            vehicleMap.put("vehicle_make", vMake);
            vehicleMap.put("vehicle_plate", vPlate);
            vehicleMap.put("vehicle_location", vLocation);
            vehicleMap.put("vehicle_passenger", vPassenger);
            vehicleMap.put("vehicle_price", vPrice);
            vehicleMap.put("vehicle_desc", vDesc);
            vehicleMap.put("vehicle_owner", vOwner);
            vehicleMap.put("vehicle_mobile", vMobile);

            vehicleRef.updateChildren(vehicleMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Heesha_Maintain_Vehicle.this, "Vehcle updated successfully..", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Heesha_Maintain_Vehicle.this, Heesha_Admin_List_of_Vehicles.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });

        }}

    private void displayVehicleInfo(){
        vehicleRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    String vtype = dataSnapshot.child("vehicle_type").getValue().toString();
                    String vmake = dataSnapshot.child("vehicle_make").getValue().toString();
                    String vplate = dataSnapshot.child("vehicle_plate").getValue().toString();
                    String vlocation = dataSnapshot.child("vehicle_location").getValue().toString();
                    String vpassenger = dataSnapshot.child("vehicle_passenger").getValue().toString();
                    String vprice = dataSnapshot.child("vehicle_price").getValue().toString();
                    String vdesc = dataSnapshot.child("vehicle_desc").getValue().toString();
                    String vowner = dataSnapshot.child("vehicle_owner").getValue().toString();
                    String vmobile = dataSnapshot.child("vehicle_mobile").getValue().toString();
                    String vimage = dataSnapshot.child("image").getValue().toString();

                    v_type.setText(vtype);
                    v_make.setText(vmake);
                    v_plate.setText(vplate);
                    v_location.setText(vlocation);
                    v_passenger.setText(vpassenger);
                    v_price.setText(vprice);
                    v_desc.setText(vdesc);
                    v_owner.setText(vowner);
                    v_mobile.setText(vmobile);
                    Picasso.get().load(vimage).into(vehicle_image);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void deleteVehicleInfo(){

        delRef = FirebaseDatabase.getInstance().getReference().child("Heesha_Vehicle");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(vehcileID).exists()){
                    delRef = FirebaseDatabase.getInstance().getReference().child("Heesha_Vehicle").child(vehcileID);
                    delRef.removeValue();
                    Toast.makeText(Heesha_Maintain_Vehicle.this, "deleted  successfully..", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Heesha_Maintain_Vehicle.this, Heesha_Admin_List_of_Vehicles.class);
                    startActivity(intent);
                    finish();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}

