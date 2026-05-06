package com.example.androidlabmanual;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button Loginbtn,expint,implicit1,fragment,UIElement1,recy1,date,sharepref1;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        Loginbtn=findViewById(R.id.Loginbtn);
        expint=findViewById(R.id.expint);
        fragment=findViewById(R.id.fragment);
        UIElement1=findViewById(R.id.UIElement1);
        implicit1=findViewById(R.id.implicit1);
        recy1=findViewById(R.id.recy1);
        date=findViewById(R.id.date);
        sharepref1=findViewById(R.id.sharepref1);

        Loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Login=new Intent(MainActivity.this, Login.class);
                startActivity(Login);
            }
        });

        expint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent explicitint=new Intent(MainActivity.this, MainActivity2.class);
                startActivity(explicitint);
            }
        });

        fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fragment1=new Intent(MainActivity.this, Fragment.class);
                startActivity(fragment1);
            }
        });

        UIElement1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent UE=new Intent(MainActivity.this,UIElement.class);
                startActivity(UE);
            }
        });

        implicit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent implicit=new Intent(Intent.ACTION_VIEW);
                implicit.setData(Uri.parse("https://www.youtube.com"));
                startActivity(implicit);
            }
        });

        recy1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent recy1=new Intent(MainActivity.this, recyclerview.class);
                startActivity(recy1);
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent date=new Intent(MainActivity.this,Date_time_alert_progress.class);
                startActivity(date);
            }
        });

        sharepref1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent share=new Intent(MainActivity.this,Shared_Preferences.class);
                startActivity(share);
            }
        });
    }
}