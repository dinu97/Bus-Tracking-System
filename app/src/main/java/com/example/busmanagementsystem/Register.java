package com.example.busmanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    EditText UserName,Password,FullName,ContactNo,NIC,Email;
    Button Register;
    TextView Registered;
    FirebaseAuth auth;
    Passenger  passenger;
    DatabaseReference reff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Register = (Button) findViewById(R.id.btn_passengerReg);
        UserName = (EditText) findViewById(R.id.txt_Uname);
        Password = (EditText) findViewById(R.id.txt_pass);
        FullName = (EditText) findViewById(R.id.txt_fullName);
        ContactNo = (EditText) findViewById(R.id.txt_contact);
        NIC = (EditText) findViewById(R.id.txt_nic);
        Email = (EditText) findViewById(R.id.txt_mail);
        Registered = (TextView) findViewById(R.id.txt_click);
        auth = FirebaseAuth.getInstance();
        passenger = new Passenger();
        reff = FirebaseDatabase.getInstance().getReference().child("Passenger");

        Registered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent launchActivity1= new Intent(Register.this, Login.class);
                startActivity(launchActivity1);
            }
        });


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passenger.setUname(UserName.getText().toString().trim());
                passenger.setPass(Password.getText().toString().trim());
                passenger.setFullName(FullName.getText().toString().trim());
                passenger.setContact(ContactNo.getText().toString().trim());
                passenger.setNic(NIC.getText().toString().trim());
                passenger.setEmail(Email.getText().toString().trim());
                String email = Email.getText().toString();
                final String password = Password.getText().toString();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (Password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                //Toast.makeText(Register.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                // progressBar.setVisibility(View.GONE);
                                // If sign in fails, display a message to the user. If sign in succeeds
                                // the auth state listener will be notified and logic to handle the
                                // signed in user can be handled in the listener.
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Register.this, " failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    startActivity(new Intent(Register.this, MainActivity.class));
                                    finish();
                                    reff.push().setValue(passenger);
                                    Toast.makeText(Register.this, "successfull Registerd." ,
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }


        });


    }
}
