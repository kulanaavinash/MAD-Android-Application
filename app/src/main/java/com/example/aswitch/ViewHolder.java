package com.example.aswitch;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    View mView;

    public ViewHolder(View itenView){
        super(itenView);
        mView = itenView;
    }

    public void setDetails(Context ctx, String id, String name, String address, String conNO){

        TextView mTitleView1 = mView.findViewById(R.id.rTextView);
        TextView mTitleView2 = mView.findViewById(R.id.rTextView1);
        TextView mTitleView3 = mView.findViewById(R.id.rTextView2);
        TextView mTitleView4 = mView.findViewById(R.id.rTextView3);


        mTitleView1.setText(id);
        mTitleView2.setText(name);
        mTitleView3.setText(address);
        mTitleView4.setText(conNO);
    }

}
