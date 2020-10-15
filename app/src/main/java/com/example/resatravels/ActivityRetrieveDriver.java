package com.example.resatravels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ActivityRetrieveDriver extends AppCompatActivity {
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    RegisterDriver driver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_driver);
        driver = new RegisterDriver();
        listView = (ListView)findViewById(R.id.lvdriver);
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference().child("Resa Travel").child("Driver");
        list = new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.list_layout_guide,R.id.name1,list);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot ds: snapshot.getChildren()) {

                    driver = ds.getValue(RegisterDriver.class);
                    list.add("First Name: " + driver.getRdfirst() + "\n"
                            + "Last Name: " + driver.getRdlast() + "\n"
                            + "Age: " + driver.getRdage() + "\n"
                            + "NIC: " + driver.getRdinsurance() + "\n"
                            + "Date Of Birth: " + driver.getRddob() + "\n"
                            + "Gender: " + driver.getRggender() + "\n"
                            + "Mobile: " + driver.getRdmobile() + "\n"
                            + "Email: " + driver.getRdemail() + "\n"
                            + "Address: " + driver.getRdaddress() + "\n"
                            + "Experience: "+ driver.getExperience() + "\n"
                            + "Skills: " + driver.getSkills() + "\n"
                    );
                }
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent updateDelete = new Intent(ActivityRetrieveDriver.this, Admin_driver_details.class);
                        RegisterDriver d = (RegisterDriver) parent.getItemAtPosition(position);
                        updateDelete.putExtra("Age", d.getRdage());
                        updateDelete.putExtra("Date Of Birth", d.getRdaddress());
                        updateDelete.putExtra("Mobile", d.getRdmobile());
                        updateDelete.putExtra("Email", d.getRdemail());
                        updateDelete.putExtra("Address", d.getRdaddress());
                        updateDelete.putExtra("Experience", d.getExperience());
                        updateDelete.putExtra("Skills", d.getSkills());
                        updateDelete.putExtra("NIC", d.getRdinsurance ());
                        startActivity(updateDelete);
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}