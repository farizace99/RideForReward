package com.myapplicationdev.android.rideforreward;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RewardsAdapter extends ArrayAdapter<RewardsClass> {

    private ArrayList<RewardsClass> reward;
    private Context context;
    private TextView tvName, tvDesc, tvPoints;
    private ImageView ivRewards;

    public RewardsAdapter(Context context, int resource, ArrayList<RewardsClass> objects) {
        super(context, resource, objects);
        reward = objects;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.rewardsrow, parent, false);

        ivRewards = (ImageView) rowView.findViewById(R.id.image);
        tvName = (TextView) rowView.findViewById(R.id.textViewName);
        tvDesc = (TextView) rowView.findViewById(R.id.textViewDescription);
        tvPoints = rowView.findViewById(R.id.tvPoints);


        RewardsClass pos = reward.get(position);
        tvName.setText(pos.getName());
        tvDesc.setText(pos.getDescription());
        ivRewards.setImageResource(pos.getImg());
        tvPoints.setText("(" + pos.getPoints() + " points are required)");

        return rowView;
    }
}
