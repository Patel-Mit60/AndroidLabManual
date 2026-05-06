package com.example.androidlabmanual;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AlarmActivity extends AppCompatActivity {

    private Button btnSetAlarm, btnCancelAlarm;
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_alarm);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Request notification permission for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101);
            }
        }

        btnSetAlarm = findViewById(R.id.btnSetAlarm);
        btnCancelAlarm = findViewById(R.id.btnCancelAlarm);
        alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        btnSetAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long triggerTime = System.currentTimeMillis() + 5000;
                Intent intent = new Intent(AlarmActivity.this, AlarmReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    if (alarmManager.canScheduleExactAlarms()) {
                        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
                        Toast.makeText(AlarmActivity.this, "Exact Alarm set for 5 seconds", Toast.LENGTH_SHORT).show();
                    } else {
                        // If cannot schedule exact, use inexact or ask for permission
                        alarmManager.set(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
                        Toast.makeText(AlarmActivity.this, "Inexact Alarm set (Permission missing)", Toast.LENGTH_SHORT).show();
                        // Optional: Redirect to settings
                        Intent intentSettings = new Intent(android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM);
                        startActivity(intentSettings);
                    }
                } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
                    Toast.makeText(AlarmActivity.this, "Alarm set for 5 seconds", Toast.LENGTH_SHORT).show();
                } else {
                    alarmManager.setExact(AlarmManager.RTC_WAKEUP, triggerTime, pendingIntent);
                    Toast.makeText(AlarmActivity.this, "Alarm set for 5 seconds", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnCancelAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AlarmActivity.this, AlarmReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);
                alarmManager.cancel(pendingIntent);
                Toast.makeText(AlarmActivity.this, "Alarm Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
