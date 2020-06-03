package com.myapplicationdev.android.rideforreward;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RewardsActivity extends AppCompatActivity {

    ArrayAdapter aa;
    ArrayList<RewardsClass> rewards;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rewards);

        lv = (ListView) findViewById(R.id.lvRewardItems);

        rewards = new ArrayList<RewardsClass>();

        rewards.add(new RewardsClass("BlendJet One", "The best and affordable blender you can find.", R.drawable.blendjetone, 2600));
        rewards.add(new RewardsClass("GooglePlayGiftCard", "$20 google play gift card.", R.drawable.googleplaygiftcard, 1900));
        rewards.add(new RewardsClass("Razer Mamba Wireless Gaming Mouse", "Great for gaming.", R.drawable.razermambawirelessgamingmouse, 3400));
        rewards.add(new RewardsClass("SOUNDPEATS True Wireless Earbuds", "Great sound quality at affordable price.", R.drawable.soundpeatstruewirelessearbuds, 2700));

        aa = new RewardsAdapter(this, R.layout.rewardsrow, rewards);
        lv.setAdapter(aa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                RewardsClass selected = rewards.get(position);
                RewardsClass reward = new RewardsClass(selected.getName(), selected.getDescription(), selected.getImg(), selected.getPoints());
                Intent i = new Intent(RewardsActivity.this, ProductPurchaseActivity.class);
                i.putExtra("reward", reward);
                startActivity(i);
            }
        });
    }
}
