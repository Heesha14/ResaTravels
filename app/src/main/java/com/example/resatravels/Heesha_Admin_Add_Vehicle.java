package com.example.resatravels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


public class Heesha_Admin_Add_Vehicle extends AppCompatActivity {

    private String vehicle_type, vehicle_make, vehicle_plate, vehicle_location, vehicle_passenger, vehicle_price,vehicle_desc,vehicle_owner,vehicle_mobile,date2,time2;
    private Button AddVehicleButton;
    private ImageView InputVehicleImage;
    private EditText InputVehicleType, InputVehicleMake, InputVehiclePlate,InputVehiclePassenger,InputVehiclePrice,InputVehicleDesc,InputVehicleOwner,InputVehicleMobile;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String vehicleRandomKey, downloadImageUrl;
    private StorageReference VehicleImagesRef;
    private DatabaseReference VehicleRef;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heesha__admin__add__vehicle);

    VehicleImagesRef = FirebaseStorage.getInstance().getReference().child("Vehicle Images");
    VehicleRef = FirebaseDatabase.getInstance().getReference().child("Heesha_Vehicle");


    AddVehicleButton = (Button) findViewById(R.id.h_click_vehicle_add);
    InputVehicleImage = (ImageView) findViewById(R.id.select_product_image);
    InputVehicleType = (EditText) findViewById(R.id.h_input_type);
    InputVehicleMake = (EditText) findViewById(R.id.h_input_make);
    InputVehiclePlate = (EditText) findViewById(R.id.h_input_plate);
    InputVehiclePassenger = (EditText) findViewById(R.id.h_input_people);
    InputVehiclePrice = (EditText) findViewById(R.id.h_input_price);
    InputVehicleDesc = (EditText) findViewById(R.id.h_input_desc);
    InputVehicleOwner = (EditText) findViewById(R.id.h_input_name);
    InputVehicleMobile = (EditText) findViewById(R.id.h_input_mobile);
    loadingBar = new ProgressDialog(this);

        InputVehicleImage.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            OpenGallery();
        }
    });


        AddVehicleButton.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
            ValidateVehicleData();
        }
    });
}

    private void OpenGallery()
    {
        Intent galleryIntent = new Intent();
        galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
        galleryIntent.setType("image/*");
        startActivityForResult(galleryIntent, GalleryPick);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==GalleryPick  &&  resultCode==RESULT_OK  &&  data!=null)
        {
            ImageUri = data.getData();
            InputVehicleImage.setImageURI(ImageUri);
        }
    }



    private void ValidateVehicleData()
    {
        vehicle_type = InputVehicleType.getText().toString();
        vehicle_make = InputVehicleMake.getText().toString();
        vehicle_plate = InputVehiclePlate.getText().toString();
        vehicle_location = InputVehicleMake.getText().toString();
        vehicle_passenger = InputVehiclePassenger.getText().toString();
        vehicle_price = InputVehiclePrice.getText().toString();
        vehicle_desc = InputVehicleDesc.getText().toString();
        vehicle_owner = InputVehicleOwner.getText().toString();
        vehicle_mobile = InputVehicleMobile.getText().toString();

        if (ImageUri == null)
        {
            Toast.makeText(this, "Vehicle image is mandatory...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(vehicle_type))
        {
            Toast.makeText(this, "Please write vehicle type...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(vehicle_make))
        {
            Toast.makeText(this, "Please write vehicle make...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(vehicle_plate))
        {
            Toast.makeText(this, "Please write vehicle plate...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(vehicle_location))
        {
            Toast.makeText(this, "Please write vehicle location...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(vehicle_passenger))
        {
            Toast.makeText(this, "Please write vehicle passenger...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(vehicle_price))
        {
            Toast.makeText(this, "Please write vehicle price...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(vehicle_desc))
        {
            Toast.makeText(this, "Please write vehicle description...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(vehicle_owner))
        {
            Toast.makeText(this, "Please write vehicle owner...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(vehicle_mobile))
        {
            Toast.makeText(this, "Please write vehicle mobile...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StoreVehicleInformation();
        }
    }


    private void StoreVehicleInformation()
    {
        loadingBar.setTitle("Add New Product");
        loadingBar.setMessage("Dear Admin, please wait while we are adding the new product.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        date2 = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        time2 = currentTime.format(calendar.getTime());

        vehicleRandomKey = date2 + time2;


        final StorageReference filePath = VehicleImagesRef.child(ImageUri.getLastPathSegment() + vehicleRandomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);


        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(Heesha_Admin_Add_Vehicle.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(Heesha_Admin_Add_Vehicle.this, "Vehicle Image uploaded Successfully...", Toast.LENGTH_SHORT).show();

                Task<Uri> urlTask = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception
                    {
                        if (!task.isSuccessful())
                        {
                            throw task.getException();
                        }

                        downloadImageUrl = filePath.getDownloadUrl().toString();
                        return filePath.getDownloadUrl();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task)
                    {
                        if (task.isSuccessful())
                        {
                            downloadImageUrl = task.getResult().toString();

                            Toast.makeText(Heesha_Admin_Add_Vehicle.this, "got the Vehicle image Url Successfully...", Toast.LENGTH_SHORT).show();

                            SaveVehicleInfoToDatabase();
                        }
                    }
                });
            }
        });
    }

    private void SaveVehicleInfoToDatabase()
    {
        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("vid", vehicleRandomKey);
        productMap.put("date2", date2);
        productMap.put("time2", time2);
        productMap.put("vehicle_type", vehicle_type);
        productMap.put("vehicle_make", vehicle_make);
        productMap.put("vehicle_plate", vehicle_plate);
        productMap.put("vehicle_location", vehicle_location);
        productMap.put("vehicle_passenger", vehicle_passenger);
        productMap.put("vehicle_price", vehicle_price);
        productMap.put("vehicle_desc", vehicle_desc);
        productMap.put("vehicle_owner", vehicle_owner);
        productMap.put("vehicle_mobile", vehicle_mobile);
        productMap.put("image", downloadImageUrl);

        VehicleRef.child(vehicleRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(Heesha_Admin_Add_Vehicle.this, Heesha_Admin_List_of_Vehicles.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(Heesha_Admin_Add_Vehicle.this, "Product is added successfully..", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(Heesha_Admin_Add_Vehicle.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}