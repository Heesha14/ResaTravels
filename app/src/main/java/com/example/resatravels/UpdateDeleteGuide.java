package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class UpdateDeleteGuide extends AppCompatActivity {

    Button pb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete_guide);

        pb =(Button)findViewById(R.id.pbaguide);
        pb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateDeleteGuide.this, ActivityAdminGuide.class);
                startActivity(intent);

            }
        });
    }
}