package com.example.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Enter_Comment extends AppCompatActivity {
    Button save;
    DBHelper mydb;
    EditText reviewcomment;
    RatingBar rate;
    float val;
    private String[] array,array2,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter__comment);

        save=(Button)findViewById(R.id.savereview);
        reviewcomment=(EditText)findViewById(R.id.rewiewc);
        rate=(RatingBar)findViewById(R.id.myrate);
        rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                String st= String.valueOf(v);
                val=v;
                Toast.makeText(Enter_Comment.this,st,Toast.LENGTH_SHORT).show();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mydb = new DBHelper(Enter_Comment.this);
                if (!readFileforID()) {

                    wirteFile();
                    readFileName();
                    mydb.insertDataReview(array[1], reviewcomment.getText().toString(), val);
                    readFileAndDB();
                }else {
                    update();
                }
                Intent intent=new Intent(Enter_Comment.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void wirteFile(){
        String textToSave = "true";
        String space=",";
        try {
            FileOutputStream fileOutputStream = openFileOutput("appreview.txt", Context.MODE_APPEND);
            fileOutputStream.write((textToSave+space).getBytes());
            fileOutputStream.close();

            Toast.makeText(this,"text Saved",Toast.LENGTH_SHORT).show();


        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public void readFileName(){
        try {
            FileInputStream fileInputStream = openFileInput("appdata.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer =new StringBuffer();


            String lines;
            while ((lines = bufferedReader.readLine()) != null){
                stringBuffer.append(lines + "\n");
            }
            String str =stringBuffer.toString();
            array = str.split(",");

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    public void readFileAndDB(){
        try {
            FileInputStream fileInputStream = openFileInput("appdata.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer =new StringBuffer();


            String lines;
            while ((lines = bufferedReader.readLine()) != null){
                stringBuffer.append(lines + "\n");
            }
            String str =stringBuffer.toString();
            array2 = str.split(",");


            Cursor res=mydb.getIDofReview(array2[1]);
            StringBuffer buffer1=new StringBuffer();
            while (res.moveToNext()){
                buffer1.append(res.getString(0)+"\n");
            }
//            Toast.makeText(this,"saved",Toast.LENGTH_SHORT).show();
            FileOutputStream fileOutputStream =openFileOutput("appreview.txt",Context.MODE_APPEND);
            fileOutputStream.write(buffer1.toString().getBytes());


        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void update(){
        readFileforID();
        readFileName();
        boolean isUpdated = mydb.updateDataReview(id[1],
                array[1],reviewcomment.getText().toString(),String.valueOf(val));
        if (isUpdated){
            Toast.makeText(this,"review edited",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this,"review  Not edited",Toast.LENGTH_SHORT).show();
        }
    }


    public boolean readFileforID(){

        try {
            FileInputStream fileInputStream = openFileInput("appreview.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer =new StringBuffer();


            String lines;
            while ((lines = bufferedReader.readLine()) != null){
                stringBuffer.append(lines + "\n");

            }

            String str =stringBuffer.toString();
            id = str.split(",");
//                Toast.makeText(this,id[1],Toast.LENGTH_SHORT).show();
            return true;

        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
