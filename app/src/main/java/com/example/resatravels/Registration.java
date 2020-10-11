package com.example.resatravels;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Registration extends AppCompatActivity {

    private EditText txt_name,txt_email,txt_phone,txt_password;
    private Button register;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


        txt_name = (EditText) findViewById(R.id.editTextTextPersonName);
        txt_email = (EditText) findViewById(R.id.editTextTextEmailAddress);
        txt_phone = (EditText) findViewById(R.id.editTextPhone);
        txt_password =(EditText)  findViewById(R.id.editTextTextPassword);

        register = (Button) findViewById(R.id.buttonRegister);

        databaseReference = FirebaseDatabase.getInstance().getReference("RegisteredUser");
        firebaseAuth = FirebaseAuth.getInstance();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerUser();

            }
        });
    }

    private void registerUser() {

        final String name = txt_name.getText().toString();
        final String email = txt_email.getText().toString();
        final String phone = txt_phone.getText().toString();
        String password = txt_password.getText().toString();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(Registration.this,"Please enter name!",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(email)){
            Toast.makeText(Registration.this,"Please enter valid email!",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(phone)){
            Toast.makeText(Registration.this,"Please enter valid phone number!",Toast.LENGTH_LONG).show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(Registration.this,"Please enter valid password!",Toast.LENGTH_LONG).show();
        }else{
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(Registration.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                                String id = databaseReference.push().getKey();
                                RegisteredUser registeredUser = new RegisteredUser(id,name,email,phone);

                                FirebaseDatabase.getInstance().getReference("RegisteredUser").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(registeredUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(Registration.this,"Registration completed successfully!",Toast.LENGTH_LONG).show();
                                        Registration.this.finish();
                                    }
                                });
                            } else {

                                Toast.makeText(Registration.this,"Error! " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        }

    }
}