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

public class ActivityAdminGuide extends AppCompatActivity {
    EditText pfirst, plast, page, pmobile, pskills, pexperience, pemail, paddress, pdob, planguage;
    Spinner pspinner;
    DatabaseReference databaseReference;
    FirebaseDatabase database;
    Button pbupdate, pbdelete;
    RegisterGuide guide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_guide);

        //String key = getIntent().getExtras().get("key").toString();
        //databaseReference = FirebaseDatabase.getInstance().getReference().child(key);
        guide = new RegisterGuide();
        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference().child("Resa Travel").child("Guide");

        pfirst = findViewById(R.id.egfirst);
        plast = findViewById(R.id.eglast);
        page = findViewById(R.id.egage);
        pmobile = findViewById(R.id.egmobile);
        pskills = findViewById(R.id.egskills);
        pexperience = findViewById(R.id.egexperience);
        pspinner = findViewById(R.id.egspinner);
        pemail = findViewById(R.id.egemail);
        paddress = findViewById(R.id.egaddress);
        pdob = findViewById(R.id.egdob);
        planguage = findViewById(R.id.eglanguage);

        pbdelete = findViewById(R.id.begdelete);
        pbupdate = findViewById(R.id.begupdate);

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
        planguage.setText(getIntent().getStringExtra("planguage"));
        //setSpinText();

        pbupdate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Guide").child("fname");
                databaseReference.child("fname").setValue(pfirst.getText().toString().trim());
                databaseReference.child("address").setValue(paddress.getText().toString().trim());
                Toast.makeText(getApplicationContext(), "Successfully updated", Toast.LENGTH_SHORT).show();
            }});
    }
    public void btnDelete(View view) {
        databaseReference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ActivityAdminGuide.this, "Record Deleted", Toast.LENGTH_SHORT).show();
                    ActivityAdminGuide.this.finish();
                }else{
                    Toast.makeText(ActivityAdminGuide.this, "Record Not Deleted", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }


  /*  private void setSpinText(Spinner spin, String text) {
        for(int i= 0; i < spin.getAdapter().getCount(); i++)
        {
            if(spin.getAdapter().getItem(i).toString().contains(text))
            {
                spin.setSelection(i);
            }
        }
    }*/



  public void btnUpdate(View view) {

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                snapshot.getRef().child("egfirst").setValue(pfirst.getText().toString());
                snapshot.getRef().child("eglast").setValue(plast.getText().toString());
                snapshot.getRef().child("egmobile").setValue(pmobile.getText().toString());
                snapshot.getRef().child("egmail").setValue(pemail.getText().toString());
                snapshot.getRef().child("egaddress").setValue(paddress.getText().toString());
                snapshot.getRef().child("egexperience").setValue(pexperience.getText().toString());
                snapshot.getRef().child("eglanguage").setValue(planguage.getText().toString());
                snapshot.getRef().child("egskills").setValue(pskills.getText().toString());

                Toast.makeText(ActivityAdminGuide.this, "Record Updated", Toast.LENGTH_SHORT).show();
                ActivityAdminGuide.this.finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}