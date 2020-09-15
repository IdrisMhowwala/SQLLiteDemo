package com.e.sqllitedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button addSubject;
    private List<Subject> lists;
    private SQLHelper db;
    private Cursor data;
    private Adapter adapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addSubject=findViewById(R.id.add_subkject);
        lists=new ArrayList<>();

        db=new SQLHelper(this);
        data=db.getAllData();

        showData();

        recyclerView=findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2 ));

        adapter=new Adapter(this,lists);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        addSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,AddSubject.class));
            }
        });
    }

    private void showData() {
        if (data.getCount()==0){
            Toast.makeText(MainActivity.this,"There is No Data",Toast.LENGTH_LONG).show();
        }
        while (data.moveToNext()) {
            lists.add(new Subject(data.getString(0), data.getString(1), data.getString(2)));
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        showData();
    }
}