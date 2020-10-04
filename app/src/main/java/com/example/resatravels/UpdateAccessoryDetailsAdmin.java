package com.example.resatravels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UpdateAccessoryDetailsAdmin extends AppCompatActivity {

    TextView keyId;
    private Button buttonSave , buttonCanc;
    EditText upName,upDes,upPrice,upQuantity;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_accessory_details_admin);

        upName = (EditText) findViewById(R.id.updateName);
        upDes = (EditText) findViewById(R.id.updateDescription);
        upPrice = (EditText) findViewById(R.id.updatePrice);
        upQuantity = (EditText) findViewById(R.id.updateQuantity);
        keyId = (TextView) findViewById(R.id.keyid);

        buttonSave = (Button) findViewById(R.id.buttonSave) ;
        buttonCanc = (Button) findViewById(R.id.buttonCanc) ;

        Intent intent = getIntent();
        String key = intent.getExtras().get("key").toString();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("TravellingAccessory").child(key);
        keyId.setText(key);

        upName.setText(intent.getStringExtra("name"));
        upDes.setText(intent.getStringExtra("description"));
        upPrice.setText(intent.getStringExtra("price"));
        upQuantity.setText(intent.getStringExtra("quantity"));


    }



    //setup update button
    public void btnUpdate_click(View view) {


        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                if(TextUtils.isEmpty(upName.getText().toString())){

                    Toast.makeText(UpdateAccessoryDetailsAdmin.this,"Name should be entered!",Toast.LENGTH_LONG).show();

                }else if(TextUtils.isEmpty(upPrice.getText().toString())){

                    Toast.makeText(UpdateAccessoryDetailsAdmin.this, "Price should be entered!",Toast.LENGTH_LONG).show();

                }else if(TextUtils.isEmpty(upQuantity.getText().toString())){

                    Toast.makeText(UpdateAccessoryDetailsAdmin.this, "Quantity should be entered!",Toast.LENGTH_LONG).show();

                }else{
                    snapshot.getRef().child("name").setValue(upName.getText().toString());
                    snapshot.getRef().child("description").setValue(upDes.getText().toString());
                    snapshot.getRef().child("price").setValue(upPrice.getText().toString());
                    snapshot.getRef().child("quantity").setValue(upQuantity.getText().toString());
                    Toast.makeText(UpdateAccessoryDetailsAdmin.this, "Travelling Accessory updated successfully!",Toast.LENGTH_LONG).show();
                    UpdateAccessoryDetailsAdmin.this.finish();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    //setup cancel button
    public void reset(View view) {
        upName.setText(null);
        upDes.setText(null);
        upPrice.setText(null);
        upQuantity.setText(null);


    }


}