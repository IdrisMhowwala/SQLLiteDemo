package com.e.sqllitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddSubject extends AppCompatActivity {

    SQLHelper sqlHelper;
    EditText title;
    EditText description;
    Button addSubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        title=findViewById(R.id.title);
        description=findViewById(R.id.description);
        addSubject=findViewById(R.id.button);

        sqlHelper=new SQLHelper(this);

        addSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titleText=title.getText().toString();
                String descriptionText=description.getText().toString();
                boolean isInserted=sqlHelper.insertData(titleText,descriptionText);
                if (isInserted==true){
                    Toast.makeText(AddSubject.this,"Data has been Inserted",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AddSubject.this,MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();
                }
                else {
                    Toast.makeText(AddSubject.this,"Failed TO Insert Data",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}