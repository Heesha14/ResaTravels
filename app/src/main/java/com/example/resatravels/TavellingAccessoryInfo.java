package com.example.resatravels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

public class TavellingAccessoryInfo extends AppCompatActivity {

    Button buttonV;
    TextView name,description,price,quantity,total,key1;
    DatabaseReference databaseReference,keyReference;
    TravellingAccessory travellingAccessory;
    EditText inputQuantity;
    Double totalAmount;
    String key,nameValue,desValue,priceValue,requiredQuantityValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tavelling_accessory_info);

        name = findViewById(R.id.textNameCus);
        description = findViewById(R.id.textDesCus);
        price = findViewById(R.id.textPriceCus);
        quantity = findViewById(R.id.textRemQCus);
        key1 = findViewById(R.id.keyCus);

        Intent intent = getIntent();
        key = intent.getExtras().get("key").toString();
        keyReference = FirebaseDatabase.getInstance().getReference().child("TravellingAccessory").child(key);

        key1.setText(key);
        name.setText(intent.getStringExtra("name"));
        description.setText(intent.getStringExtra("description"));
        price.setText(intent.getStringExtra("price"));
        quantity.setText(intent.getStringExtra("quantity"));

        inputQuantity = (EditText) findViewById(R.id.editTextQun);
        total = findViewById(R.id.textViewTot);

        databaseReference = FirebaseDatabase.getInstance().getReference("TravellingAccessory");

        nameValue = name.getText().toString();
        desValue = description.getText().toString();
        priceValue = price.getText().toString();
        requiredQuantityValue = inputQuantity.getText().toString();

        //calling view total function
        buttonV = (Button) findViewById(R.id.button8);
        buttonV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewTotal();
            }
        });



    }


    public void confirmOrder(View view) {

        try{

            Intent intent = getIntent();
            String remQuantity = Objects.requireNonNull(intent.getExtras().get("quantity")).toString();

            //casting values
            Integer quantityInput = Integer.valueOf(inputQuantity.getText().toString());
            Integer remainingQuantity = Integer.valueOf(remQuantity);


            //validate user inputs
            if(remainingQuantity == 0){
                Toast.makeText(this, "Out of stock!",Toast.LENGTH_LONG).show();
            }else if (quantityInput == 0 || quantityInput < 0){
                Toast.makeText(this, "Quantity is Invalid!",Toast.LENGTH_LONG).show();
            }else if(quantityInput > remainingQuantity){
                Toast.makeText(this, "Please enter the quantity less than to remaining quantity!",Toast.LENGTH_LONG).show();
            }else {
                //open alert box to confirm the order
                openAlertDialog();

            }
        }catch (NumberFormatException e){

            Toast.makeText(this, "Please enter a valid quantity!",Toast.LENGTH_LONG).show();
        }
    }

    private void openAlertDialog() {
        Intent intent = getIntent();
        String remQuantity = Objects.requireNonNull(intent.getExtras().get("quantity")).toString();

        //casting values
        final Integer quantityInput = Integer.valueOf(inputQuantity.getText().toString());
        final Integer remainingQuantity = Integer.valueOf(remQuantity);
        
        AlertDialog alertDialog = new AlertDialog.Builder(TavellingAccessoryInfo.this)
                .setTitle("Confirm")
                .setMessage("Are you sure you want to confirm this order?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        final String saveCurrentDate,saveCurrentTime;

                        Calendar calForDate = Calendar.getInstance();
                        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
                        saveCurrentDate = currentDate.format(calForDate.getTime());

                        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
                        saveCurrentTime = currentTime.format(calForDate.getTime());

                        viewTotal();

                        final DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference().child("OrderedAccessories");

                        String id = ordersRef.push().getKey();
                        OrderedAccessories orderedAccessories = new OrderedAccessories();
                        orderedAccessories.setId(id);
                        orderedAccessories.setAccessoryKey(key);
                        orderedAccessories.setName(nameValue);
                        orderedAccessories.setDescription(desValue);
                        orderedAccessories.setPrice(priceValue);
                        orderedAccessories.setRequiredQuantity(inputQuantity.getText().toString());
                        orderedAccessories.setTotalAmount(totalAmount);
                        orderedAccessories.setDate(saveCurrentDate);
                        orderedAccessories.setTime(saveCurrentTime);

                        ordersRef.child(id).setValue(orderedAccessories);
                       HashMap<String, Object> ordersMap = new HashMap<>();

                        ordersRef.updateChildren(ordersMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){

                                    Integer updatedValue = remainingQuantity - quantityInput;
                                    String upValue = String.valueOf(updatedValue);
                                    keyReference.child("quantity").setValue(upValue);

                                }
                            }
                        });
                        
                        Toast.makeText(TavellingAccessoryInfo.this, "Successfully Confirmed!",Toast.LENGTH_LONG).show();
                        TavellingAccessoryInfo.this.finish();
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(TavellingAccessoryInfo.this, "Canceled!",Toast.LENGTH_LONG).show();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }




    public void viewTotal() {
        try{

            Intent intent = getIntent();
            String remQuantity = Objects.requireNonNull(intent.getExtras().get("quantity")).toString();
            String itemPrice = Objects.requireNonNull(intent.getExtras().get("price")).toString();
            //casting values
            Integer quantityInput = Integer.valueOf(inputQuantity.getText().toString());
            Integer remainingQuantity = Integer.valueOf(remQuantity);
            Double price = Double.valueOf(itemPrice);

            //validate user inputs
            if(remainingQuantity == 0){
                Toast.makeText(this, "Out of stock!",Toast.LENGTH_LONG).show();
            }else if (quantityInput == 0 || quantityInput < 0){
                Toast.makeText(this, "Quantity is Invalid!",Toast.LENGTH_LONG).show();
            }else if(quantityInput > remainingQuantity){
                Toast.makeText(this, "Please enter the quantity less than to remaining quantity!",Toast.LENGTH_LONG).show();
            }else {
                //calculate total amount and display it

                totalAmount = price * quantityInput;

                total.setText(String.valueOf(totalAmount));

            }
        }catch (NumberFormatException e){

            Toast.makeText(this, "Please enter a valid quantity!",Toast.LENGTH_LONG).show();
        }
    }

    //setup cancel button
    public void reset(View view) {
        inputQuantity.setText(null);
        inputQuantity.dispatchDisplayHint(View.VISIBLE);


    }
}