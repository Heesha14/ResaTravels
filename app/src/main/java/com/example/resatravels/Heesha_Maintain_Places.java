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

public class Heesha_Maintain_Places extends AppCompatActivity {

    private Button applychnagebtn,deletebtn;
    private EditText place,mobile,province,desc;
    private ImageView placeimage;
    private String placeID = "";
    private DatabaseReference placesRef,delRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heesha__maintain__places);

        placeID = getIntent().getStringExtra("pid");

        placesRef = FirebaseDatabase.getInstance().getReference().child("Heesha_Places_Model").child(placeID);

        applychnagebtn = findViewById(R.id.h_select_place_btn);
        place = findViewById(R.id.h_admin_add_placename);
        mobile = findViewById(R.id.heesha_admin_add_placemobile);
        province = findViewById(R.id.h_admin_add_placeprovince);
        desc = findViewById(R.id.h_admin_add_placedescription);
        placeimage = findViewById(R.id.place_image);
        deletebtn = findViewById(R.id.h_delete_place_btn);

        displayPlaceInfo();

        applychnagebtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                applychanges();

            }});

        deletebtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                deletePlaceInfo();

            }});

    }

    private void applychanges(){
        String pplace= place.getText().toString();
        String pmobile = mobile.getText().toString();
        String pprovince = province.getText().toString();
        String pdesc = desc.getText().toString();

        if(pplace.equals("")){
            Toast.makeText(Heesha_Maintain_Places.this, "place cannot be empty..", Toast.LENGTH_SHORT).show();
        }
        else if(pmobile.equals("")){
            Toast.makeText(Heesha_Maintain_Places.this, "mobile cannot be empty..", Toast.LENGTH_SHORT).show();
        }
        else if(pdesc.equals("")){
            Toast.makeText(Heesha_Maintain_Places.this, "description cannot be empty..", Toast.LENGTH_SHORT).show();
        }
        else if(pprovince.equals("")){
            Toast.makeText(Heesha_Maintain_Places.this, "province cannot be empty..", Toast.LENGTH_SHORT).show();
        }
        else
        {
            HashMap<String, Object> placeMap = new HashMap<>();
            placeMap.put("pid", placeID);
            placeMap.put("pname", pplace);
            placeMap.put("province", pprovince);
            placeMap.put("mobile", pmobile);
            placeMap.put("description", pdesc);

            placesRef.updateChildren(placeMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Heesha_Maintain_Places.this, "changes applied successfully..", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Heesha_Maintain_Places.this, Heesha_Admin_List_of_Places.class);
                        startActivity(intent);
                        finish();
                    }
                }
            });

        }}

    private void displayPlaceInfo(){
        placesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.exists()){
                    String pplace = dataSnapshot.child("pname").getValue().toString();
                    String pprovince = dataSnapshot.child("province").getValue().toString();
                    String pmobile = dataSnapshot.child("mobile").getValue().toString();
                    String pdescription = dataSnapshot.child("description").getValue().toString();
                    String pimage = dataSnapshot.child("image").getValue().toString();

                    place.setText(pplace);
                    mobile.setText(pmobile);
                    province.setText(pprovince);
                    desc.setText(pdescription);
                    Picasso.get().load(pimage).into(placeimage);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void deletePlaceInfo(){
        delRef = FirebaseDatabase.getInstance().getReference().child("Heesha_Places_Model");
        delRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if(dataSnapshot.child(placeID).exists()){
                    delRef = FirebaseDatabase.getInstance().getReference().child("Heesha_Places_Model").child(placeID);
                    delRef.removeValue();
                    Toast.makeText(Heesha_Maintain_Places.this, "deleted  successfully..", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Heesha_Maintain_Places.this, Heesha_Admin_List_of_Places.class);
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