package com.example.aswitch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.PrivateKey;
import java.util.ArrayList;

public class MainActivity7 extends AppCompatActivity {

    EditText txtID, txtName, txtAdd,txtConNO;
    Button butUpdate, butDelete;
    DatabaseReference dbRef;
    Riders std;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        TextView txtID = (TextView) findViewById(R.id.Id);
        final TextView txtName =(TextView) findViewById(R.id.Pname);
        final TextView txtAdd =(TextView) findViewById(R.id.Padd);
        TextView txtConNO =(TextView) findViewById(R.id.con);

        butUpdate = findViewById(R.id.btupdate);
        butDelete = findViewById(R.id.btdelete);

        std = new Riders();

        butUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Riders").child("Std1");
                dbRef.removeValue();
                Toast.makeText(getApplicationContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();

            }
        });

        butUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbRef = FirebaseDatabase.getInstance().getReference();
                dbRef.child("Riders").child("Std1").child("name").setValue(txtName.getText().toString().trim());
                dbRef.child("Riders/Std1/address").setValue(txtAdd.getText().toString().trim());
                Toast.makeText(getApplicationContext(), "Successfully Updated", Toast.LENGTH_SHORT).show();
                clearControls();
            }
        });

    }

    private void clearControls() {
        txtID.setText("");
        txtName.setText("");
        txtAdd.setText("");
        txtConNO.setText("");
    }


}