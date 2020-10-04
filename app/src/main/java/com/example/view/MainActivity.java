package com.example.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class MainActivity extends AppCompatActivity {
    private Button review;
    DBHelper mydb;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        readFile();
        if (readFile()) {
            FragmentTransaction fr = getSupportFragmentManager().beginTransaction();
            fr.add(R.id.fragment, new add_comment());
            fr.commit();
        }else {
            FragmentTransaction fr = getSupportFragmentManager().beginTransaction();
            fr.add(R.id.fragment, new add_comment());
            fr.commit();
        }

        review=(Button)findViewById(R.id.reviewbt);
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,All_Review.class);
                startActivity(intent);

            }
        });



    }

    public boolean readFile(){
        try {
            FileInputStream fileInputStream = openFileInput("appreview.txt");
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream);


            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer =new StringBuffer();

            String lines;
            if ((lines = bufferedReader.readLine()) != null){
                return true;
            }
            while ((lines = bufferedReader.readLine()) != null){
                stringBuffer.append(lines);
            }

        } catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }


}
