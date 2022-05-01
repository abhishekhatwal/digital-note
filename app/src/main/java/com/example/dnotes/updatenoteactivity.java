package com.example.dnotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class updatenoteactivity extends AppCompatActivity {

    EditText updatetitle,updatedescription;
    Button updatebutton;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatenoteactivity);

        updatetitle=findViewById(R.id.updatetitle);
        updatedescription=findViewById(R.id.updatedescription);
        updatebutton=findViewById(R.id.updatebutton);

        Intent i= getIntent();
        updatetitle.setText(i.getStringExtra("title"));
        updatedescription.setText(i.getStringExtra("description"));
        id=i.getStringExtra("id");

        updatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!TextUtils.isEmpty(updatetitle.getText().toString()) && !TextUtils.isEmpty(updatedescription.getText().toString()))
                {
                    databaseclass db=new databaseclass(updatenoteactivity.this);
                    db.updatenote(updatetitle.getText().toString(),updatedescription.getText().toString(),id);

                    Intent i=new Intent(updatenoteactivity.this,MainActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(updatenoteactivity.this,"Both field required",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}