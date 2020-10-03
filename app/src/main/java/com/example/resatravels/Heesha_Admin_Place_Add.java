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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.UploadTask;

public class Heesha_Admin_Place_Add extends AppCompatActivity {

    private String placeName, province, mobile, description, date, time;
    private Button AddPlace;
    private ImageView InputPlaceImage;
    private EditText InputPlaceName, InputPlaceProvince, InputPlaceMobile, InputPlaceDesc;
    private static final int GalleryPick = 1;
    private Uri ImageUri;
    private String placeRandomKey, downloadImageUrl;
    private StorageReference PlaceImagesRef;
    private DatabaseReference PlacesRef;
    private ProgressDialog loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heesha__admin__place__add);

        PlaceImagesRef = FirebaseStorage.getInstance().getReference().child("Places Images");
        PlacesRef = FirebaseDatabase.getInstance().getReference().child("Heesha_Places_Model");


        AddPlace = (Button) findViewById(R.id.h_admin_add_place_confirm);
        InputPlaceImage = (ImageView) findViewById(R.id.select_place_image);
        InputPlaceName = (EditText) findViewById(R.id.h_admin_add_place_input_name);
        InputPlaceProvince = (EditText) findViewById(R.id.h_admin_add_place_input_province);
        InputPlaceMobile = (EditText) findViewById(R.id.h_admin_add_place_input_mobile);
        InputPlaceDesc = (EditText) findViewById(R.id.h_admin_add_place_input_description);
        loadingBar = new ProgressDialog(this);

        InputPlaceImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenGallery();
            }
        });


        AddPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValidatePlaceData();
            }
        });
    }

    public void redirectToPlace1(View view) {
        Intent intent1 = new Intent(this, Heesha_Admin_Edit_place.class);
        startActivity(intent1);
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
            InputPlaceImage.setImageURI(ImageUri);
        }
    }


    private void StorePlaceInformation()
    {
        loadingBar.setTitle("Add New Product");
        loadingBar.setMessage("Dear Admin, please wait while we are adding the new product.");
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.show();

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MMM dd, yyyy");
        date = currentDate.format(calendar.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        time = currentTime.format(calendar.getTime());

        placeRandomKey = date + time;


        final StorageReference filePath = PlaceImagesRef.child(ImageUri.getLastPathSegment() + placeRandomKey + ".jpg");

        final UploadTask uploadTask = filePath.putFile(ImageUri);


        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e)
            {
                String message = e.toString();
                Toast.makeText(Heesha_Admin_Place_Add.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                loadingBar.dismiss();
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
            {
                Toast.makeText(Heesha_Admin_Place_Add.this, "Product Image uploaded Successfully...", Toast.LENGTH_SHORT).show();

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

                            Toast.makeText(Heesha_Admin_Place_Add.this, "got the Product image Url Successfully...", Toast.LENGTH_SHORT).show();

                            SavePlaceInfoToDatabase();
                        }
                    }
                });
            }
        });
    }


    private void ValidatePlaceData()
    {
        placeName = InputPlaceName.getText().toString();
        province = InputPlaceProvince.getText().toString();
        mobile = InputPlaceMobile.getText().toString();
        description = InputPlaceDesc.getText().toString();

        if (ImageUri == null)
        {
            Toast.makeText(this, "Place image is mandatory...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(placeName))
        {
            Toast.makeText(this, "Please write place name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(province))
        {
            Toast.makeText(this, "Please write place province...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(mobile))
        {
            Toast.makeText(this, "Please write mobile...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(description))
        {
            Toast.makeText(this, "Please write description...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StorePlaceInformation();
        }
    }



    private void SavePlaceInfoToDatabase()
    {
        HashMap<String, Object> productMap = new HashMap<>();
        productMap.put("pid", placeRandomKey);
        productMap.put("date", date);
        productMap.put("time", time);
        productMap.put("pname", placeName);
        productMap.put("province", province);
        productMap.put("mobile", mobile);
        productMap.put("description", description);
        productMap.put("image", downloadImageUrl);

        PlacesRef.child(placeRandomKey).updateChildren(productMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(Heesha_Admin_Place_Add.this, Heesha_Admin_List_of_Places.class);
                            startActivity(intent);

                            loadingBar.dismiss();
                            Toast.makeText(Heesha_Admin_Place_Add.this, "Place is added successfully..", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            loadingBar.dismiss();
                            String message = task.getException().toString();
                            Toast.makeText(Heesha_Admin_Place_Add.this, "Error: " + message, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



}