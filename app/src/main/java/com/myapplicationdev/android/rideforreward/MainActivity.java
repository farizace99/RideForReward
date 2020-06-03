package com.myapplicationdev.android.rideforreward;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    EditText etEmail, etPassword;
    Button btnLogin;
    DatabaseReference DBR;
    Member Member;
    int z = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etEmail = (EditText) findViewById(R.id.editTextEmailLogin);
        etPassword = (EditText) findViewById(R.id.editTextPasswordLogin);
        DBR = FirebaseDatabase.getInstance().getReference().child("Member");
        btnLogin = findViewById(R.id.buttonLogin);

        TextView register = (TextView) findViewById(R.id.textViewLinkRegister);
        register.setMovementMethod(LinkMovementMethod.getInstance());

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegistrationActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBR.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int count = (int) dataSnapshot.getChildrenCount();
                        for (z = 1; z < count + 1; z++) {
                            DBR = FirebaseDatabase.getInstance().getReference().child("Member").child(String.valueOf(z));

                            DBR.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    String user = etEmail.getText().toString().trim();
                                    String pw = etPassword.getText().toString().trim();
                                    String un = dataSnapshot.child("email").getValue().toString();
                                    String p = dataSnapshot.child("password").getValue().toString();

                                    if (un.contentEquals(user) && p.contentEquals(pw)) {
                                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                                        int poi = Integer.parseInt(dataSnapshot.child("points").getValue().toString());
                                        Member curMember = new Member(un,p,poi);
                                        intent.putExtra("currMember", curMember);
                                        startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(MainActivity.this, "Please enter the correct login details", Toast.LENGTH_LONG).show();
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
