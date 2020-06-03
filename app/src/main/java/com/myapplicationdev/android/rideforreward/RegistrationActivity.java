package com.myapplicationdev.android.rideforreward;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegistrationActivity extends AppCompatActivity {

    DatabaseReference DBR;
    Member Member;
    int id = 0;
    Button btnRegister;
    EditText etEmail, etPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration);

        TextView login = (TextView) findViewById(R.id.textViewLinkLogin);
        btnRegister = findViewById(R.id.buttonRegister);
        etEmail = findViewById(R.id.editTextEmail);
        etPass = findViewById(R.id.editTextPassword);
        DBR = FirebaseDatabase.getInstance().getReference().child("Member");
        Member = new Member();

        login.setMovementMethod(LinkMovementMethod.getInstance());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        DBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    id = (int) dataSnapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = etEmail.getText().toString().trim();
                String pw = etPass.getText().toString();
                if (email.contains("@") && email.contains(".com") && email.endsWith(".com") && !pw.isEmpty()) {
                    Member.setEmail(etEmail.getText().toString().trim());
                    Member.setPassword(etPass.getText().toString());
                    DBR.child(String.valueOf(id + 1)).setValue(Member);
                    Toast.makeText(RegistrationActivity.this, "Account registered successful", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(intent);

                } else if (email.isEmpty() && pw.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Email and password cannot be empty", Toast.LENGTH_LONG).show();

                } else if (email.isEmpty() && !pw.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Email cannot be empty", Toast.LENGTH_LONG).show();

                } else if (pw.isEmpty() && !!!email.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Password cannot be empty", Toast.LENGTH_LONG).show();

                } else if ((!email.contains("@") && !email.endsWith(".com") && !email.contains(".com")) || pw.isEmpty()) {
                    Toast.makeText(RegistrationActivity.this, "Please enter a valid email, password cannot be empty", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
