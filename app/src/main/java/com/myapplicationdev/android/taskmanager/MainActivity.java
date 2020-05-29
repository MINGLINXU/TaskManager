package com.myapplicationdev.android.taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    CustomAdapter ca;
    ArrayList<Task> tasks = new ArrayList<Task>();
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);
        btnAdd = findViewById(R.id.btnAdd);


        ca = new CustomAdapter(MainActivity.this, R.layout.row, tasks);
        lv.setAdapter(ca);

        DBHelper db = new DBHelper(MainActivity.this);
        ArrayList<Task> data = db.getAllTask();
        db.close();
        for (int i = 0; i < data.size(); i++) {
            tasks.add(new Task(data.get(i).getId(), data.get(i).getName(), data.get(i).getDescription(),
                    data.get(i).getSecond()));
        }

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivity(intent);
            }
        });
    }

}
