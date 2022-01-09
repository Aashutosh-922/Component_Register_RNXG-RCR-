package com.example.rcr;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class users extends AppCompatActivity {
    DatabaseReference databaseReference4,databaseReference5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        Spinner spinner2=findViewById(R.id.spinner2);
        EditText borrow=findViewById(R.id.editTextNumber4);
        EditText returned = findViewById(R.id.editTextNumber5);

        databaseReference4= FirebaseDatabase.getInstance().getReference();
        databaseReference5=FirebaseDatabase.getInstance().getReference();

        Button button=findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String string = spinner2.getSelectedItem().toString();
                String br = borrow.getText().toString();
                String rr = returned.getText().toString();
                databaseReference4.child("Borrowed").child(string).setValue(br);
                databaseReference5.child("Returned").child(string).setValue(rr);
            }
        });


        List<String> Component = new ArrayList<>();
        Component.add(0, "Select component:");
        Component.add("7-Segment Green");
        Component.add("Arduino Mega");
        Component.add("Arduino Nano");
        Component.add("Bluetooth Module");
        Component.add("DC Motor");
        Component.add("IR sensor");
        Component.add("LCD Displays");
        Component.add("Motor Driver");
        Component.add("Servo Motor");
        Component.add("UltraSonic sensor");
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Component);
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner2.setAdapter(myAdapter2);
    }
}