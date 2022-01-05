package com.example.rcr;


import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import java.util.ArrayList;
import java.util.List;


public class SignupActivity extends AppCompatActivity {
    public EditText mail;
    public EditText name;
    public EditText password = findViewById(R.id.editTextNumberPassword);
    public EditText confirm;
    FirebaseAuth Auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name = findViewById(R.id.editTextTextPersonName3);
        mail = findViewById(R.id.editTextTextEmailAddress2);
        confirm = findViewById(R.id.editTextNumberPassword2);
        Auth = FirebaseAuth.getInstance();

        Spinner Branch = findViewById(R.id.branch);


        List<String> branch = new ArrayList<>();
        branch.add(0, "Select branch:");
        branch.add("IT");
        branch.add("CSE");
        branch.add("ECE");
        branch.add("Electrical");
        branch.add("Chemical");
        branch.add("Mechanical");
        branch.add("Textile");
        branch.add("Production");

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, branch);
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Branch.setAdapter(myAdapter1);

        Branch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select branch:")) {
                    // do nothing
                } else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected:" + item, Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Spinner Branch = findViewById(R.id.branch);
                Branch.setSelection(0);
            }
        });
        Spinner Year = findViewById(R.id.year);

        List<String> year = new ArrayList<>();
        year.add(0, "Select Year");
        year.add("FY");
        year.add("SY");
        year.add("TY");

        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, year);
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Year.setAdapter(myAdapter2);

        Year.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Year:")) {

                } else {
                    String item1 = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "Selected" + item1, Toast.LENGTH_SHORT).show();

                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Spinner Year=findViewById(R.id.year);
                Year.setSelection(0);
            }
        });

        Spinner domain = findViewById(R.id.domain);

        List<String> Domain = new ArrayList<>();
        Domain.add(0, "Select Field of Interest");
        Domain.add("Software");
        Domain.add("Electronics");
        Domain.add("Mechanical");

        ArrayAdapter<String> myAdapter3 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Domain);
        myAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        domain.setAdapter(myAdapter3);

        domain.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getItemAtPosition(position).equals("Select Field of Interest")) {
                    // do nothing
                } else {
                    String item3 = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), "selected " + item3, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Spinner domain=findViewById(R.id.domain);
                domain.setSelection(0);
            }
        });

        Button signup = findViewById(R.id.button4);

        signup.setOnClickListener(v -> {
            String Name = name.getText().toString().trim();
            String Email = mail.getText().toString().trim();
            String Password = password.getText().toString().trim();
            String confirmation = confirm.getText().toString().trim();

            if (TextUtils.isEmpty(Name) || TextUtils.isEmpty(Email) || TextUtils.isEmpty(Password) || TextUtils.isEmpty(confirmation))
                Toast.makeText(SignupActivity.this, "You missed something:", Toast.LENGTH_SHORT).show();
            else if (!Password.equals(confirmation))
                Toast.makeText(SignupActivity.this, "The entered and confirmation password doesn't match.", Toast.LENGTH_SHORT).show();
            else if (Password.length() < 6)
                password.setError("The password length should be greater than 6.");

            Auth.createUserWithEmailAndPassword(Email, Password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    FirebaseUser user=Auth.getCurrentUser();
                    if (user != null) {
                        user.sendEmailVerification().addOnSuccessListener(unused -> Toast.makeText(SignupActivity.this, "Email verification has been sent:", Toast.LENGTH_SHORT).show()).
                                addOnFailureListener(e -> Log.d("Tag","On Failure:email not sent "+ e.getMessage() ));
                    }
                    Toast.makeText(SignupActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                } else
                    Toast.makeText(SignupActivity.this, "Registration unsuccessful", Toast.LENGTH_SHORT).show();
            });


        });
    }
}