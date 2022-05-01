package com.example.dnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class addnotes extends AppCompatActivity {

    EditText title,description;
    Button addnote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnotes);

        title=findViewById(R.id.title2);
        description=findViewById(R.id.description2);
        addnote=findViewById(R.id.button2);

        addnote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(!TextUtils.isEmpty(title.getText().toString()) && !TextUtils.isEmpty(description.getText().toString()))     //or if(!title.getText().toString().equals("") && !description.getText().toString().equals(""));
               {
                    databaseclass db=new databaseclass(addnotes.this);
                    db.addnotefun(title.getText().toString(),description.getText().toString());
                   Intent intent= new Intent(addnotes.this,MainActivity.class);
                   intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                   startActivity(intent);
                   finish();
               }
               else
               {
                   Toast.makeText(addnotes.this,"Both field require",Toast.LENGTH_SHORT).show();
               }

            }
        });
    }
}