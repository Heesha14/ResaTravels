package com.example.resatravels;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Rules extends AppCompatActivity {
    EditText rule;
    Button btn_save;
    DatabaseReference dbRef;

    rules_class r;

    private void clearControls() {
        rule.setText("");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        rule = findViewById(R.id.rule);


        btn_save = findViewById(R.id.btn_save);
        r = new rules_class();
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("rules");
                try {
                    if (TextUtils.isEmpty(rule.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an OrderNo", Toast.LENGTH_SHORT).show();
                    else {
                        r.setRule(rule.getText().toString().trim());


                        //dbRef.push().setValue(std);
                        dbRef.child("rule1").setValue(r);

                        Toast.makeText(getApplicationContext(), "Data saved success", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(new Intent(Rules.this, MainActivity.class));
                        startActivity(i);
                        clearControls();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "Data saved unsuccessfull", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(new Intent(Rules.this, Rules.class));
                }
            }
        });
    }
}
