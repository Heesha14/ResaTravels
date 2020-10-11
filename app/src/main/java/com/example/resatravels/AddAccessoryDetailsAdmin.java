package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddAccessoryDetailsAdmin extends AppCompatActivity {

    private Button addButton,cancelButton;
    private EditText txt_name,txt_description,txt_price,txt_quantity;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_accessory_details_admin);

        //casting views
        addButton = (Button) findViewById(R.id.buttonAdd);
        cancelButton = (Button) findViewById(R.id.buttonCancel);
        txt_name = (EditText) findViewById(R.id.editTextName);
        txt_description = (EditText) findViewById(R.id.editTextDescription);
        txt_price = (EditText) findViewById(R.id.editTextPrice);
        txt_quantity = (EditText) findViewById(R.id.editTextQuantity);

        databaseReference = FirebaseDatabase.getInstance().getReference("TravellingAccessory");

        //add button actions
        addButton.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addAccessory();
            }
        }));
    }

    private void addAccessory() {

        String name = txt_name.getText().toString();
        String description = txt_description.getText().toString();
        String price = txt_price.getText().toString();
        String quantity = txt_quantity.getText().toString();

        if(TextUtils.isEmpty(name)){

            Toast.makeText(this, "Name should be entered!",Toast.LENGTH_LONG).show();

        }else if(TextUtils.isEmpty(price)){

            Toast.makeText(this, "Price should be entered!",Toast.LENGTH_LONG).show();

        }else if(TextUtils.isEmpty(quantity)){

            Toast.makeText(this, "Quantity should be entered!",Toast.LENGTH_LONG).show();

        }else {
            String id = databaseReference.push().getKey();
            TravellingAccessory travellingAccessory = new TravellingAccessory(id,name,description,price,quantity);

            databaseReference.child(id).setValue(travellingAccessory);
            Toast.makeText(this, "Travelling accessory added successfully!",Toast.LENGTH_LONG).show();
            AddAccessoryDetailsAdmin.this.finish();
        }



    }

    //setup cancel button
    public void reset(View view) {
        txt_name.setText(null);
        txt_name.dispatchDisplayHint(View.VISIBLE);
        txt_description.setText(null);
        txt_description.dispatchDisplayHint(View.VISIBLE);
        txt_price.setText(null);
        txt_price.dispatchDisplayHint(View.VISIBLE);
        txt_quantity.setText(null);
        txt_quantity.dispatchDisplayHint(View.VISIBLE);

    }
}