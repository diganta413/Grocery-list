package com.example.myapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity2 extends AppCompatActivity {
    Button b;
    ListView view;
    Button b1;
    EditText t;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        b=(Button)findViewById(R.id.button5);
        b1=(Button)findViewById(R.id.add);
        view=(ListView)findViewById(R.id.list);
        t=(EditText)findViewById(R.id.name);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Toast.makeText(MainActivity2.this, "Signed out successfull", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainActivity2.this,MainActivity.class);
                startActivity(i);
            }
        });
        final ArrayList<String> identity=new ArrayList<>();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s=t.getText().toString();
                DatabaseReference db=FirebaseDatabase.getInstance().getReference().child("new list_view");
                if(!TextUtils.isEmpty(s)) {
                    String id = db.push().getKey();
                    Item_info i2=new Item_info(s,id);



                    FirebaseDatabase.getInstance().getReference().child("new list_view").child(id).setValue(i2);
                }
                else
                {
                    Toast.makeText(MainActivity2.this, "Enter name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        final ArrayList<String> list=new ArrayList<>();
        final ArrayAdapter<String> adapter =new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        view.setAdapter(adapter);

        final DatabaseReference r=FirebaseDatabase.getInstance().getReference().child("new list_view");
        r.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                identity.clear();


                for(DataSnapshot snap:snapshot.getChildren())
                {
                    Item_info i3=snap.getValue(Item_info.class);


                    identity.add(i3.getId());
                     list.add(i3.getName());
                }
                adapter.notifyDataSetChanged();


            }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, final View view, int i, long l) {
                AlertDialog.Builder b=new AlertDialog.Builder(MainActivity2.this);
                b.setCancelable(true);
                b.setTitle("Update or delete");
                final String id3=identity.get(i);
                final String name=((TextView)view).getText().toString();
                b.setMessage("Press update to update or delete to delete");
                b.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent=new Intent(MainActivity2.this,Item.class);

                        intent.putExtra("name",name);

                        intent.putExtra("Id",id3);

                        startActivity(intent);
                    }
                });
                b.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        FirebaseDatabase.getInstance().getReference().child("new list_view").child(id3).removeValue();


                    }
                });
                AlertDialog dialog=b.create();
                dialog.show();

            }
        });






    }


}