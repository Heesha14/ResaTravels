package com.example.resatravels;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register_driver extends AppCompatActivity {
    EditText pfirst, plast, page, pmobile, pskills, pexperience, pemail, paddress, pdob, pinsurance;
    Spinner pspinner;
    Button pbregister;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rigister_driver);

        databaseReference = FirebaseDatabase.getInstance().getReference("Travel");
        pfirst = findViewById(R.id.rdfirst);
        plast = findViewById(R.id.rdlast);
        page = findViewById(R.id.rdage);
        pinsurance = findViewById(R.id.rdinsuranceno);
        pmobile = findViewById(R.id.rdmobile);
        pskills = findViewById(R.id.rdskills);
        pexperience = findViewById(R.id.rdexperience);
        pemail = findViewById(R.id.rgemail);
        paddress = findViewById(R.id.rdaddress);
        pdob = findViewById(R.id.rddob);
        pspinner = findViewById(R.id.rdspinner);

        pbregister = findViewById(R.id.brdsubmit);

        pbregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Resa Travel").child("Driver");



              /*  //modified= if error occor change this
                databaseReference= (DatabaseReference) FirebaseDatabase.getInstance().getReference("Travel").child("Guide").orderByChild("pfirst");*/
                int no;
                try {
                    if (TextUtils.isEmpty(pfirst.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty first name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(plast.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty last name", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(page.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty age", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(pspinner.getSelectedItem().toString()))
                        Toast.makeText(getApplicationContext(), "Empty gender", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(pdob.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty date of birth", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(pmobile.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Contact number", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(pemail.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty email", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(paddress.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty address", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(pexperience.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty experience", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(pskills.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty skills", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(pinsurance.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty insurance number", Toast.LENGTH_SHORT).show();

                    else {
                        String fname = pfirst.getText().toString().trim();
                        String lname = plast.getText().toString().trim();
                        no = Integer.parseInt(page.getText().toString());
                        String insurance = pinsurance.getText().toString().trim();
                        String gender = pspinner.getSelectedItem().toString();
                        String dob = pdob.getText().toString().trim();
                        String mobile = pmobile.getText().toString().trim();
                        String email = pemail.getText().toString().trim();
                        String address = paddress.getText().toString().trim();
                        String experience = pexperience.getText().toString().trim();
                        String skills = pskills.getText().toString().trim();
                        String id = databaseReference.push().getKey();
                        RegisterDriver driver = new RegisterDriver(id, fname, lname, no, insurance, gender, dob, mobile, email, address,  experience, skills);
                        databaseReference.child(id).setValue(driver);
                        Toast.makeText(getApplicationContext(), "Successfully inserted", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("Number is needed " + nfe);
                }
            }
        });
    }
}
