package com.myapplicationdev.android.rideforreward;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {

    TextView tvItalicTitle, tvPoints;
    Button btnRewards, btnTransports;
    DatabaseReference DBR;
    Member currMem;
    ArrayList<Integer> points = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage);

        tvItalicTitle = (TextView) findViewById(R.id.textViewTitle);
        tvPoints = (TextView) findViewById(R.id.idTextViewPoints);
        btnRewards = (Button) findViewById(R.id.idButtonRewards);
        btnTransports = (Button) findViewById(R.id.idButtonTransport);

        Intent i = getIntent();
        currMem = (Member) i.getSerializableExtra("currMember");

        DBR = FirebaseDatabase.getInstance().getReference().child("Member");

        tvItalicTitle.setText("Ride public transports to exchange points for rewards!");
        tvPoints.setText(currMem.getPoints() + " points");

        btnTransports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, TransportActivity.class);
                startActivity(i);
            }
        });

        btnRewards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this, RewardsActivity.class);
                startActivity(i);
            }
        });
    }
}
