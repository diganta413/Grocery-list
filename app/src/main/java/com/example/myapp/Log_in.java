package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Log_in extends AppCompatActivity {
    EditText name;
    EditText password;
    Button b;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        name=(EditText)findViewById(R.id.email1);
        password=(EditText)findViewById(R.id.pass);
        b=(Button)findViewById(R.id.button4);
        auth=FirebaseAuth.getInstance();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String n=name.getText().toString();
                String p=password.getText().toString();
                sign(n,p);
            }
        });
    }

    private void sign(String n, String p) {
        auth.signInWithEmailAndPassword(n,p).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(Log_in.this, "Log in successfull", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Log_in.this,MainActivity2.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(Log_in.this, "Log in failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}