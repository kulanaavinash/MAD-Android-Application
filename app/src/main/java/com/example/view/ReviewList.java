package com.example.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class ReviewList  extends ArrayAdapter<USER> {
    private static final String TAG="PersonListAdapter";
    private Context mContext;
    int mResource;

    public ReviewList(Context context, int resource, ArrayList<USER> objects) {
        super(context, resource, objects);
        mContext=context;
        mResource=resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String name=getItem(position).getName();
        String description=getItem(position).getDecription();
        String rating=getItem(position).getRatingBar();
        String image=getItem(position).getImage();

        USER user = new USER(name,description,rating,image);

        LayoutInflater inflater=LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tname=(TextView)convertView.findViewById(R.id.name1);
        TextView tdesc=(TextView)convertView.findViewById(R.id.desc);
        RatingBar bar=(RatingBar)convertView.findViewById(R.id.ratingBar);
        ImageView imageView=(ImageView)convertView.findViewById(R.id.userIcon);

        tname.setText(name);
        tdesc.setText(description);
        bar.setRating(Float.parseFloat(rating));
        imageView.setImageResource(R.drawable.google);
        return convertView;
    }
}
