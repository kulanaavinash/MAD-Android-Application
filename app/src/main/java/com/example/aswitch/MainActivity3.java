package com.example.aswitch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class MainActivity3 extends AppCompatActivity {
    TextView a,b,c,d;
    Button btn;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        a=(TextView) findViewById(R.id.name);
        b=(TextView) findViewById(R.id.age);
        c=(TextView) findViewById(R.id.ht);
        d=(TextView) findViewById(R.id.ph);
        btn=(Button) findViewById(R.id.btnload);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reff = FirebaseDatabase.getInstance().getReference().child("Member").child("1");
                reff.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        String name = dataSnapshot.child("name").getValue().toString();
                        String age = dataSnapshot.child("age").getValue().toString();
                        String ht = dataSnapshot.child("ht").getValue().toString();
                        String ph = dataSnapshot.child("ph").getValue().toString();
                        a.setText(name);
                        b.setText(age);
                        c.setText(ht);
                        d.setText(ph);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
}