package com.myapplicationdev.android.rideforreward;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ProductPurchaseActivity extends AppCompatActivity {

    ImageView iv;
    TextView tvPoints, tvProductLink;
    Button btnTrade, btnCancel;
    DatabaseReference DBR;
    Member Member;
    int z = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.productpurchase);

        iv = findViewById(R.id.imageView);
        tvPoints = findViewById(R.id.textViewPoints);
        btnCancel = findViewById(R.id.buttonCancel);
        btnTrade = findViewById(R.id.buttonTrade);
        tvProductLink = findViewById(R.id.textViewProductLink);
        tvProductLink.setMovementMethod(LinkMovementMethod.getInstance());
        DBR = FirebaseDatabase.getInstance().getReference().child("Member");

        Intent i = getIntent();
        final RewardsClass reward = (RewardsClass) i.getSerializableExtra("reward");

        iv.setImageResource(reward.getImg());
        tvPoints.setText(reward.getPoints() + " points required");

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent z = new Intent(ProductPurchaseActivity.this, RewardsActivity.class);
                startActivity(z);
            }
        });

        btnTrade.setOnClickListener(new View.OnClickListener() {
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
                                    int points = reward.getPoints();
                                    int pt = Integer.parseInt(dataSnapshot.child("points").getValue().toString());

                                    int ptleft = pt - points;

                                    DBR = FirebaseDatabase.getInstance().getReference("Member").child(String.valueOf(z)).child("points");


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
