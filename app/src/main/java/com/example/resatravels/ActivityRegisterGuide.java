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

public class ActivityRegisterGuide extends AppCompatActivity {
    EditText pfirst, plast, page, pmobile, pskills, pexperience, pemail, paddress, pdob, planguage;
    Spinner pspinner;
    DatabaseReference databaseReference;
    Button pbcancel, pbregister, cbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_guide);

        databaseReference = FirebaseDatabase.getInstance().getReference("Travel");
        pfirst = findViewById(R.id.edfirst);
        plast = findViewById(R.id.rglast);
        page = findViewById(R.id.rgage);
        pmobile = findViewById(R.id.rgmobile);
        pskills = findViewById(R.id.rgskills);
        pexperience = findViewById(R.id.rgexperience);
        pspinner = findViewById(R.id.rgspinner);
        pemail = findViewById(R.id.rgemail);
        paddress = findViewById(R.id.rgaddress);
        pdob = findViewById(R.id.rgdob);
        planguage = findViewById(R.id.rglanguage);
        pbcancel = findViewById(R.id.brgcancel);
        pbregister = findViewById(R.id.brgregister);



        cbtn = findViewById(R.id.brgcancel);
        cbtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                finish();
            }

        });

        pbregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference().child("Resa Travel").child("Guide");

              /*  //modified= if error occor change this
                databaseReference= (DatabaseReference) FirebaseDatabase.getInstance().getReference("Travel").child("Guide").orderByChild("pfirst");*/
                int no;
                String em =
                        "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                                +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                                +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                                +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                                +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                                +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";
                String phon = "^[0-9]{10}$";
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
                    else if (TextUtils.isEmpty(pmobile.getText().toString()) || !pmobile.getText().toString().matches(phon))
                        Toast.makeText(getApplicationContext(), "Invalid Contact number", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(pemail.getText().toString()) || !pemail.getText().toString().matches(em))
                        Toast.makeText(getApplicationContext(), "Invalid email", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(paddress.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty address", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(pexperience.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty experience", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(pskills.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty skills", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(planguage.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty language", Toast.LENGTH_SHORT).show();

                    else {
                        String fname = pfirst.getText().toString().trim();
                        String lname = plast.getText().toString().trim();
                        no = Integer.parseInt(page.getText().toString());
                        String gender = pspinner.getSelectedItem().toString();
                        String dob = pdob.getText().toString().trim();
                        String mobile = pmobile.getText().toString().trim();
                        String email = pemail.getText().toString().trim();
                        String address = paddress.getText().toString().trim();
                        String experience = pexperience.getText().toString().trim();
                        String skills = pskills.getText().toString().trim();
                        String language = planguage.getText().toString().trim();
                        String id = databaseReference.push().getKey();
                        RegisterGuide guide = new RegisterGuide(id, fname, lname, no, gender, dob, mobile, email, address,  experience, skills, language);
                        databaseReference.child(id).setValue(guide);
                        Toast.makeText(getApplicationContext(), "Successfully inserted", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException nfe) {
                    System.out.println("Number is needed " + nfe);
                }
            }
        });
    }
}





