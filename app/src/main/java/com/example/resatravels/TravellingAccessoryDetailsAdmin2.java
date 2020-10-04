package com.example.resatravels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TravellingAccessoryDetailsAdmin2 extends AppCompatActivity {

    private Button buttonU,buttonD;
    TextView name,description,price,quantity,key1;
    DatabaseReference databaseReference;
    TravellingAccessory travellingAccessory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travelling_accessory_details_admin2);

        //open update travelling accessory details
        buttonU = (Button) findViewById(R.id.buttonUp);
        buttonU.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUpdateTravellingAccessoryDetails();
            }
        });

        name = findViewById(R.id.textName);
        description = findViewById(R.id.textDescription);
        price = findViewById(R.id.textPrice);
        quantity = findViewById(R.id.textQuantity);
        key1 = findViewById(R.id.key);

        Intent intent = getIntent();
        String key = intent.getExtras().get("key").toString();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("TravellingAccessory").child(key);

        key1.setText(key);
        name.setText(intent.getStringExtra("name"));
        description.setText(intent.getStringExtra("description"));
        price.setText(intent.getStringExtra("price"));
        quantity.setText(intent.getStringExtra("quantity"));

        //delete travelling accessory details
        /*buttonD = (Button) findViewById(R.id.buttonCan);
        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseReference.removeValue();
            }
        });*/
    }

    public void openUpdateTravellingAccessoryDetails(){
        //TravellingAccessory travellingAccessory = new TravellingAccessory();
       Intent intent = new Intent (getApplicationContext(), UpdateAccessoryDetailsAdmin.class);

        String keyValue = key1.getText().toString();
        String nameValue = name.getText().toString();
        String desValue = description.getText().toString();
        String priceValue = price.getText().toString();
        String quantityValue = quantity.getText().toString();


        intent.putExtra("key", keyValue);
        intent.putExtra("name", nameValue);
        intent.putExtra("description", desValue);
        intent.putExtra("price", priceValue);
        intent.putExtra("quantity", quantityValue);
        startActivity(intent);


    }

    public void btnDelete_click(View view) {
        databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()){
                    Toast.makeText(TravellingAccessoryDetailsAdmin2.this, "Travelling Accessory deleted successfully!",Toast.LENGTH_LONG).show();
                    TravellingAccessoryDetailsAdmin2.this.finish();
                }else {
                    Toast.makeText(TravellingAccessoryDetailsAdmin2.this, "Travelling Accessory cannot be deleted!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}