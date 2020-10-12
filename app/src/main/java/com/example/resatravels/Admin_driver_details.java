package com.example.resatravels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Admin_driver_details extends AppCompatActivity {

    EditText pfirst, plast, page, pmobile, pskills, pexperience, pemail, paddress, pdob, pnic;
    Spinner pspinner;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    Button pbupdate, pbdelete;
    RegisterGuide guide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_driver_details);
        guide = new RegisterGuide();
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference().child("Resa Travel").child("Driver");

        pfirst = findViewById(R.id.edfirst);
        plast = findViewById(R.id.edlast);
        page = findViewById(R.id.edage);
        pmobile = findViewById(R.id.edmobile);
        pskills = findViewById(R.id.edskills);
        pexperience = findViewById(R.id.edexperience);
        pspinner = findViewById(R.id.edspinner);
        pemail = findViewById(R.id.edemail);
        paddress = findViewById(R.id.edaddress);
        pdob = findViewById(R.id.eddob);
        pnic = findViewById(R.id.ednic);

        pbdelete = findViewById(R.id.baddelete);
        pbupdate = findViewById(R.id.badupdate);

        pfirst.setText(getIntent().getStringExtra("pfirst"));
        plast.setText(getIntent().getStringExtra("plast"));
        page.setText(getIntent().getStringExtra("page"));
        pmobile.setText(getIntent().getStringExtra("pmobile"));
        pskills.setText(getIntent().getStringExtra("pskills"));
        pexperience.setText(getIntent().getStringExtra("pexperience"));
        //pspinner.setText(getIntent().getStringExtra("pspinner"));
        // pspinner.setSelection (Integer.parseInt(getIntent().getStringExtra("pspinner")));
        pemail.setText(getIntent().getStringExtra("pemail"));
        paddress.setText(getIntent().getStringExtra("paddress"));
        pdob.setText(getIntent().getStringExtra("pdob"));
        pnic.setText(getIntent().getStringExtra("pnic"));


        pbupdate.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Driver");
                databaseReference.removeValue();
                Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
            }
        });

        //delete
       /* pbdelete.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                dbref = FirebaseDatabase.getInstance().getReference().child("Driver");
                dbref.removeValue();
                Toast.makeText(getApplicationContext(), "Successfully deleted", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    public void btnUpdateD(View view) {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getRef().child("egfirst").setValue(pfirst.getText().toString());
                snapshot.getRef().child("eglast").setValue(plast.getText().toString());
                snapshot.getRef().child("egmobile").setValue(pmobile.getText().toString());
                snapshot.getRef().child("egmail").setValue(pemail.getText().toString());
                snapshot.getRef().child("egaddress").setValue(paddress.getText().toString());
                snapshot.getRef().child("egexperience").setValue(pexperience.getText().toString());
                snapshot.getRef().child("egnic").setValue(pnic.getText().toString());
                snapshot.getRef().child("egskills").setValue(pskills.getText().toString());

                Toast.makeText(Admin_driver_details.this, "Record Updated", Toast.LENGTH_SHORT).show();
                Admin_driver_details.this.finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
            public void btnDeleteD(View view) {
                databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Admin_driver_details.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                            Admin_driver_details.this.finish();
                        } else {
                            Toast.makeText(Admin_driver_details.this, "Record Not Deleted", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        }
