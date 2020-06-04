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
    DatabaseReference DBRS;
    ArrayList<Integer> points = new ArrayList<>();
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
        DBRS = FirebaseDatabase.getInstance().getReference().child("Member").child("1");

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

                DBRS.child("points").setValue(points.get(0) - 2600 + "");
                Toast.makeText(ProductPurchaseActivity.this, "Trade successful, please check your email for more details to claim the reward", Toast.LENGTH_LONG).show();

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
