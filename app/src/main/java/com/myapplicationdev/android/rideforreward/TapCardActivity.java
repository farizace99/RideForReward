package com.myapplicationdev.android.rideforreward;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TapCardActivity extends AppCompatActivity {

    Button btnDone;
    DatabaseReference DBR;
    DatabaseReference DBRS;
    ArrayList<Integer> points = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tapcard);

        btnDone = findViewById(R.id.buttonDone);
        DBR = FirebaseDatabase.getInstance().getReference().child("Member");
        DBRS = FirebaseDatabase.getInstance().getReference().child("Member").child("1");

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DBRS.child("points").setValue(points.get(0) + 100 + "");

                Intent i = new Intent(TapCardActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        points.clear();

        DBR.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                DBR = FirebaseDatabase.getInstance().getReference().child("Member");

                DBR.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for (DataSnapshot pointsSnap : dataSnapshot.getChildren()) {
                            points.add(Integer.parseInt(pointsSnap.child("points").getValue().toString()));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
}

