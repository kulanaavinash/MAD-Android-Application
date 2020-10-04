package com.example.aswitch;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class MainActivity4 extends AppCompatActivity {

    private EditText txtId, txtName, txtAdd, txtConNo;
    private Button butSave, butShow, butUpdate, butDelete,button,button2;
    private DatabaseReference dbRef;
    private Riders std;
    ProgressDialog pd;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        txtId =(EditText) findViewById(R.id.EtInputID);
        txtName =(EditText) findViewById(R.id.EtInputName);
        txtAdd =(EditText) findViewById(R.id.EtInputAddress);
        txtConNo =(EditText) findViewById(R.id.EtInputConNo);

        butSave =(Button) findViewById(R.id.ButSave);
        butShow =(Button) findViewById(R.id.ButShow);
        butUpdate =(Button) findViewById(R.id.ButUpdate);
        butDelete =(Button) findViewById(R.id.ButDelete);
        button =(Button) findViewById(R.id.nbtn);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });
        butShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });


        std = new Riders();


        butSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                dbRef = FirebaseDatabase.getInstance().getReference().child("Riders");
                try{
                    if(TextUtils.isEmpty(txtId.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Empty Id", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(txtName.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Name", Toast.LENGTH_SHORT);
                    else if (TextUtils.isEmpty(txtAdd.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Address", Toast.LENGTH_SHORT);
                    else if (TextUtils.isEmpty(txtConNo.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Empty Contact", Toast.LENGTH_SHORT);
                    else {
                        std.setId(txtId.getText().toString().trim());
                        std.setName(txtName.getText().toString().trim());
                        std.setAddress(txtAdd.getText().toString().trim());
                        std.setConNO(txtConNo.getText().toString().trim());
                        String key = dbRef.push().getKey();
                        dbRef.child(key).setValue(std);
                        Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
                        clearControls();
                    }

                }
                catch (NumberFormatException nfe) {
                    Toast.makeText(getApplicationContext(), "Invalid Contact No", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void openActivity3() {
        Intent intent = new Intent(this, MainActivity6.class);
        startActivity(intent);
    }

    public void openActivity2(){
        Intent intent = new Intent(this, MainActivity5.class);
        startActivity(intent);
    }

    private void clearControls() {
        txtId.setText("");
        txtName.setText("");
        txtAdd.setText("");
        txtConNo.setText("");
    }


}