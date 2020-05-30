package com.myapplicationdev.android.taskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity {

    EditText etName, etDescription, etSecond;
    Button btnAdd, btnCancel;
    int reqCode = 12345;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        etName = findViewById(R.id.etName);
        etDescription = findViewById(R.id.etDescription);
        etSecond = findViewById(R.id.etSeconds);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(AddActivity.this);
                db.insertTask(etName.getText().toString(),etDescription.getText().toString(),Integer.parseInt(etSecond.getText().toString()));
                db.close();
                Calendar cal = Calendar.getInstance();
                int seconds = Integer.parseInt(etSecond.getText().toString());
                cal.add(Calendar.SECOND, seconds);
                Intent intent = new Intent(AddActivity.this, ScheduledNotificationReceiver.class);
                intent.putExtra("name", etName.getText().toString());
                PendingIntent pendingIntent = PendingIntent.getBroadcast(AddActivity.this, reqCode, intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager) getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);

                Intent intentBack = new Intent(AddActivity.this, MainActivity.class);
                startActivity(intentBack);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
