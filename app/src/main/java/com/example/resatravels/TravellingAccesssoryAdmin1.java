package com.example.resatravels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TravellingAccesssoryAdmin1 extends AppCompatActivity {

    private Button button;
    DatabaseReference databaseReference;
    ListView listView;
    List<TravellingAccessory> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travelling_accesssory_admin1);

        button = (Button) findViewById(R.id.button11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddTravellingAccessoryDetails();
            }
        });



        list = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listView);

        databaseReference = FirebaseDatabase.getInstance().getReference("TravellingAccessory");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                Intent intent = new Intent(TravellingAccesssoryAdmin1.this, TravellingAccessoryDetailsAdmin2.class);
                TravellingAccessory travellingAccessory = (TravellingAccessory) adapterView.getItemAtPosition(i);
                intent.putExtra("name", travellingAccessory.getName());
                intent.putExtra("description", travellingAccessory.getDescription());
                intent.putExtra("price", travellingAccessory.getPrice());
                intent.putExtra("quantity", travellingAccessory.getQuantity());
                intent.putExtra("key", travellingAccessory.getId());
                startActivity(intent);

            }
        });



    }


    @Override
    protected void onStart() {
        super.onStart();


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for(DataSnapshot accessorySnapshot : snapshot.getChildren()){
                    TravellingAccessory travellingAccessory = accessorySnapshot.getValue(TravellingAccessory.class);
                    list.add(travellingAccessory);
                }

                TravellingAccessoryList adapter = new TravellingAccessoryList(TravellingAccesssoryAdmin1.this, list );
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }



        });
    }

    public void openAddTravellingAccessoryDetails(){
        Intent intent = new Intent (this, AddAccessoryDetailsAdmin.class);
        startActivity(intent);
    }




}