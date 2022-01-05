package com.example.rcr;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login_activity extends AppCompatActivity {
    EditText editTextTextEmailAddress,editTextTextPassword;
    Button button3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(v -> {

            String username = editTextTextEmailAddress.getText().toString();
            String password = editTextTextPassword.getText().toString();

            if(username.equals("Suchit") && (password.equals("12345")))
            {
                Toast.makeText( login_activity.this, "Welcome to RCR!!",Toast.LENGTH_SHORT).show();

            }else
            {
                Toast.makeText(login_activity.this,"Invalid Credentails",Toast.LENGTH_SHORT).show();
            }

        });
    }
}

