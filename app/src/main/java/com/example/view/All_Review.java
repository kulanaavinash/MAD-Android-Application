package com.example.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class All_Review extends AppCompatActivity {
    DBHelper mydb;
    Button button;
    String[] ssname,ssdesc,ssrate;
    private static final String TAG="MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__review);

        //DB part
        mydb=new DBHelper(this);
        mydb.insertDataReview("Pathum","highly recommended with your purchase cheap and with good performance",4);
        mydb.insertDataReview("Ushan","It's okay. Packed in a Duper. to Krasnoyarsk 2 days, of which 12 days in SriLanka. I recommend the producer. took on the stock and coupon 9200 rubles. For such a price tag, the Aparat is quite good",3);
        mydb.insertDataReview("Avinash","excellent seller, quickly sent the phone, delivery to Dehiwala in 2 days all as in the description, came in factory packaging, global version ",3);
        mydb.insertDataReview("Mandara","The item was shipped in 3 days only. 4 days on the way. The box is heavily crumpled, but the phone is whole. Includes fast charging, cover, Type-C wire, paper clip and documentation. On the screen there is a factory film. Let's see how it will behave in the work",3);
        mydb.insertDataReview("Anu","Many thanks to the seller! Quickly responded to my request, changed the color of the phone and sent. Very fast delivery, purchase is satisfied, I recommend the seller",5);
        button=(Button)findViewById(R.id.getrev);



        Cursor res=mydb.getAllDataReview();
        if (res.getCount() == 0){
            showMessage("Error","Nothing");
            return;
        }

        StringBuffer name=new StringBuffer();
        StringBuffer desc=new StringBuffer();
        StringBuffer rate=new StringBuffer();;

        while (res.moveToNext()){

            name.append(res.getString(1)+",");
            desc.append(res.getString(2)+",");
            rate.append(res.getString(3)+",");
        }
        String sname = name.toString();
        String sdesc = desc.toString();
        String srate = rate.toString();

        ssname = sname.split(",");
        ssdesc = sdesc.split(",");
        ssrate = srate.split(",");




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=mydb.getAllDataReview();
                if (res.getCount() == 0){
                    showMessage("Error","Nothing");
                    return;
                }
                StringBuffer buffer=new StringBuffer();

                while (res.moveToNext()){
                    buffer.append("Id : "+res.getString(0)+"\n");
                    buffer.append("Name : "+res.getString(1)+"\n");
                    buffer.append("Description : "+res.getString(2)+"\n");
                    buffer.append("Rating : "+res.getString(3)+"\n");

                }
                showMessage("Data",buffer.toString());
            }
        });


        //other

        ListView list=(ListView)findViewById(R.id.list);
        Log.d(TAG,"onCreate: Started.");

        USER user=new USER(ssname[0],ssdesc[0],ssrate[0],"R.drawable.common_google_signin_btn_icon_dark");
        USER user1=new USER(ssname[1],ssdesc[1],ssrate[1],"R.drawable.common_google_signin_btn_icon_dark");
        USER user2=new USER(ssname[2],ssdesc[2],ssrate[2],"R.drawable.common_google_signin_btn_icon_dark");
        USER user3=new USER(ssname[3],ssdesc[3],ssrate[3],"R.drawable.common_google_signin_btn_icon_dark");
        USER user4=new USER(ssname[4],ssdesc[4],ssrate[4],"R.drawable.common_google_signin_btn_icon_dark");

        ArrayList<USER> reviewers=new ArrayList<USER>();
        reviewers.add(user);
        reviewers.add(user1);
        reviewers.add(user2);
        reviewers.add(user3);
        reviewers.add(user4);

        ReviewList adapter=new ReviewList(this,R.layout.activity_layout,reviewers);
        list.setAdapter(adapter);
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}