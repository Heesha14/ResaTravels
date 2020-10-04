package com.example.resatravels;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Objects;

public class TavellingAccessoryInfo extends AppCompatActivity {

    TextView name,description,price,quantity,total;
    DatabaseReference databaseReference;
    TravellingAccessory travellingAccessory;
    EditText inputQuantity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tavelling_accessory_info);

        name = findViewById(R.id.textNameCus);
        description = findViewById(R.id.textDesCus);
        price = findViewById(R.id.textPriceCus);
        quantity = findViewById(R.id.textRemQCus);

        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        description.setText(intent.getStringExtra("description"));
        price.setText(intent.getStringExtra("price"));
        quantity.setText(intent.getStringExtra("quantity"));

        inputQuantity = findViewById(R.id.editTextQun);
        total = findViewById(R.id.textViewTot);

        databaseReference = FirebaseDatabase.getInstance().getReference("TravellingAccessory");




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
        AlertDialog alertDialog = new AlertDialog.Builder(TavellingAccessoryInfo.this)
                .setTitle("Confirm")
                .setMessage("Are you sure you want to confirm this order?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        
                        Toast.makeText(TavellingAccessoryInfo.this, "Successfully Confirmed!",Toast.LENGTH_LONG).show();
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


    public void viewTotal(View view) {
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
                Double totalAmount;
                totalAmount = price * quantityInput;

                total.setText(String.valueOf(totalAmount));

            }
        }catch (NumberFormatException e){

            Toast.makeText(this, "Please enter a valid quantity!",Toast.LENGTH_LONG).show();
        }
    }
}