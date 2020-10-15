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

public class ActivityRetrieveGuide extends AppCompatActivity {
    ListView listView;
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    RegisterGuide guide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve_guide);
        guide = new RegisterGuide();
        listView = (ListView)findViewById(R.id.lvguide);
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference().child("Resa Travel").child("Guide");
        list = new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.list_layout_guide,R.id.name1,list);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //databaseReference = FirebaseDatabase.getInstance().getReference().child("Resa Travel").child("Guide");
                for(DataSnapshot ds: snapshot.getChildren()){
                    guide = ds.getValue(RegisterGuide.class);
                    list.add("First Name: " + guide.getFname() + "\n"
                            + "Last Name: " + guide.getLname() + "\n"
                            + "Age: " + guide.getAge() + "\n"
                            + "Date Of Birth: " + guide.getDob() + "\n"
                            + "Gender: " +  guide.getGender() + "\n"
                            + "Mobile: " +  guide.getMobile()+ "\n"
                            + "Email: " +  guide.getEmail() + "\n"
                            + "Address: "  + guide.getAddress() + "\n"
                            + "Experience: "+ guide.getExperience() + "\n"
                            + "Skills: " + guide.getSkills() + "\n"
                            + "Language: " + guide.getLanguage() + "\n"
                    );
               }
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent updateDelete = new Intent(ActivityRetrieveGuide.this, ActivityAdminGuide.class);
                        RegisterGuide g = (RegisterGuide) parent.getItemAtPosition(position);
                        updateDelete.putExtra("Age", g.getAge());
                        updateDelete.putExtra("Date Of Birth", g.getDob());
                        updateDelete.putExtra("Mobile", g.getMobile());
                        updateDelete.putExtra("Email", g.getEmail());
                        updateDelete.putExtra("Address", g.getAddress());
                        updateDelete.putExtra("Experience", g.getExperience());
                        updateDelete.putExtra("Skills", g.getSkills());
                        updateDelete.putExtra("Language", g.getLanguage());
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