package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {
    EditText name;
    EditText password;
    Button b;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        b=(Button)findViewById(R.id.button2);
        auth=FirebaseAuth.getInstance();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=name.getText().toString();
                String p=password.getText().toString();
                register(n,p);

                }

            });
        }

    private void register(String n, String p) {
        auth.createUserWithEmailAndPassword(n,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Register.this, "Successfully registered", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(Register.this, "Registration failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}