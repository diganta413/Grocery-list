package com.example.myapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Item extends AppCompatActivity {
    Button b;
    EditText t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        t=(EditText) findViewById(R.id.name);
        b=(Button)findViewById(R.id.del);
        Intent intent = getIntent();
        final String str = intent.getStringExtra("Id");
        final String str1=intent.getStringExtra("name");

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                 {
                     String s=t.getText().toString();
                     if(!TextUtils.isEmpty(s)) {
                         Item_info i=new Item_info(s,str);
                         FirebaseDatabase.getInstance().getReference().child("new list_view").child(str).setValue(i);
                     }
                     else
                     {
                         Toast.makeText(Item.this, "Emter some name", Toast.LENGTH_SHORT).show();
                     }

                }
            }
        });

    }
}