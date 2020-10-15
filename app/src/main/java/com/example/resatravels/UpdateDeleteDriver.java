package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class UpdateDeleteDriver extends AppCompatActivity {
    Button pb1;
   // DatabaseReference databaseReference;
    //FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_driver);
        pb1 =(Button)findViewById(R.id.pbadr);
        pb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateDeleteDriver.this, Admin_driver_details.class);
                startActivity(intent);
            }
        });
    }
}