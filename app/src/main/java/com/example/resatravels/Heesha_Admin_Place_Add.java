package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Heesha_Admin_Place_Add extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heesha__admin__place__add);
    }

    public void redirectToPlace1(View view) {
        Intent intent1 = new Intent(this,Heesha_Admin_List_of_Places.class);
        startActivity(intent1);
    }
}
