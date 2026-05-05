package com.example.androidlabmanual;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Date_time_alert_progress extends AppCompatActivity {

EditText timeselect,dateselect;
Button  alert,progress;

Calendar clndr = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_date_time_alert_progress);

    timeselect=findViewById(R.id.timeselect);
    timeselect.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar currenttime = Calendar.getInstance();
            int hour = currenttime.get(Calendar.HOUR_OF_DAY);
            int minit = currenttime.get(Calendar.MINUTE);

            TimePickerDialog timePicker = new TimePickerDialog(Date_time_alert_progress.this, (View, HourOfDay, selectedminit) ->
            {
                String AmPm = (HourOfDay >= 12) ? "PM" : "AM";
                int displayHour = (HourOfDay > 12) ? HourOfDay - 12 : (HourOfDay == 0 ? 12 : HourOfDay);

                String timeString = String.format(Locale.getDefault(), "%02d:%02d %s", displayHour, selectedminit, AmPm);

                timeselect.setText(timeString);
            }, hour, minit, true);
            timePicker.show();
        }
    });
    dateselect=findViewById(R.id.dateselect);
        DatePickerDialog.OnDateSetListener dtpc = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                clndr.set(Calendar.YEAR, year);
                clndr.set(Calendar.MONTH, month);
                clndr.set(Calendar.DAY_OF_MONTH, day);
                updatelable();

            }

            private void updatelable() {
                String myFormat="dd/MM/yy";
                SimpleDateFormat dateFormat=new SimpleDateFormat(myFormat, Locale.UK);
                dateselect.setText(dateFormat.format(clndr.getTime()));
            }
        };
        dateselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new  DatePickerDialog(Date_time_alert_progress.this,dtpc,clndr.get(Calendar.YEAR),clndr.get(Calendar.MONTH),clndr.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        alert=findViewById(R.id.alert);
        alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Date_time_alert_progress.this);
                builder.setTitle("Are you sure !");
                builder.setMessage("Are you sure!!!");
                builder.setCancelable(true);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(getApplicationContext(),"Continue",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog=builder.create();
                dialog.show();
            }
        });

        progress=findViewById(R.id.progress);
        progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressDialog progressDialog = new ProgressDialog(Date_time_alert_progress.this);
                progressDialog.setTitle("Loading....");
                progressDialog.setMessage("Loading Your Massage");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.setCancelable(false);
                progressDialog.show();
                new Handler().postDelayed(()->{
                    progressDialog.dismiss();
                },3000);
            }
        });
    }
}