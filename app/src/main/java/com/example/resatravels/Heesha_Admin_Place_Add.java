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
    private String pattern = "^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$";

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
        Intent intent1 = new Intent(this,Heesha_Admin_List_of_Places.class);
        startActivity(intent1);
    }

    public void redirectToHome(View view) {
        Intent intent1 = new Intent(this,Heesha_Admin_View.class);
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
        loadingBar.setTitle("Add New Place Image");
        loadingBar.setMessage("Dear Admin, please wait while we are adding the new place.");
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
                Toast.makeText(Heesha_Admin_Place_Add.this, "Place Image uploaded Successfully...", Toast.LENGTH_SHORT).show();

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

                            Toast.makeText(Heesha_Admin_Place_Add.this, "Place image Url Successfully added...", Toast.LENGTH_SHORT).show();

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
            Toast.makeText(this, "Please enter place name...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(province))
        {
            Toast.makeText(this, "Please enter place province...", Toast.LENGTH_SHORT).show();
        }
        else if (!TextUtils.isEmpty(province) && !((province.equalsIgnoreCase("Southern") || (province.equalsIgnoreCase("Western") || province.equalsIgnoreCase("Central") || (province.equalsIgnoreCase("Western")||province.equalsIgnoreCase("Uva") || (province.equalsIgnoreCase("North Western")||province.equalsIgnoreCase("North Central") || (province.equalsIgnoreCase("Eastern")||province.equalsIgnoreCase("Sabaragamuwa") )))))) )
        {
            Toast.makeText(this, "Entered province is invalid", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(mobile))
        {
            Toast.makeText(this, "Please enter mobile...", Toast.LENGTH_SHORT).show();
        }
        else if (!TextUtils.isEmpty(mobile) && !mobile.matches(pattern))
        {
            Toast.makeText(this, "Mobile number is invalid...", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(description))
        {
            Toast.makeText(this, "Please enter description...", Toast.LENGTH_SHORT).show();
        }
        else
        {
            StorePlaceInformation();
        }
    }



    private void SavePlaceInfoToDatabase()
    {
        HashMap<String, Object> placeMap = new HashMap<>();
        placeMap.put("pid", placeRandomKey);
        placeMap.put("date", date);
        placeMap.put("time", time);
        placeMap.put("pname", placeName);
        placeMap.put("province", province);
        placeMap.put("mobile", mobile);
        placeMap.put("description", description);
        placeMap.put("image", downloadImageUrl);

        PlacesRef.child(placeRandomKey).updateChildren(placeMap)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Intent intent = new Intent(Heesha_Admin_Place_Add.this, Heesha_Admin_List_of_Places.class);
                            //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
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



