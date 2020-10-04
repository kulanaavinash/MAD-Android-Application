package com.example.aswitch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout layoutOrder,layoutRider,layoutReport,layoutDelivery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layoutOrder = (LinearLayout) findViewById(R.id.layoutOrder);
        layoutRider = (LinearLayout) findViewById(R.id.layoutRider);
        layoutReport = (LinearLayout) findViewById(R.id.layoutReport);
        layoutDelivery = (LinearLayout) findViewById(R.id.layoutDelivery);


        layoutOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        layoutRider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });
    }

    public void openActivity2(){
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }

    public void openActivity3(){
        Intent intent = new Intent(this, MainActivity4.class);
        startActivity(intent);
    }
}