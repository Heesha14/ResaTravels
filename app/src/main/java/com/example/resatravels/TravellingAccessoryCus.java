package com.example.resatravels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class TravellingAccessoryCus extends AppCompatActivity {


    DatabaseReference databaseReference;
    ListView listView;
    List<TravellingAccessory> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travelling_accessory_cus);

        list = new ArrayList<>();
        listView = (ListView) findViewById(R.id.listViewCus);

        databaseReference = FirebaseDatabase.getInstance().getReference("TravellingAccessory");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TravellingAccessory travellingAccessory = list.get(i);
                Intent intent = new Intent(getApplicationContext(), TavellingAccessoryInfo.class);

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

                TravellingAccessoryCusList adapter = new TravellingAccessoryCusList(TravellingAccessoryCus.this, list );
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }



        });
    }

}