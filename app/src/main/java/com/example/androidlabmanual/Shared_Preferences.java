package com.example.androidlabmanual;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class Shared_Preferences extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    EditText share,display;
    Button sub,show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_shared_preferences);


        share=findViewById(R.id.share);
        display=findViewById(R.id.display);
        sub=findViewById(R.id.sub);
        show=findViewById(R.id.show);

        sharedPreferences = getSharedPreferences("MY DATA",MODE_PRIVATE);
        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String string=share.getText().toString();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("User Name",string);
                editor.commit();
                Toast.makeText(getApplicationContext(),"Data are saved",Toast.LENGTH_SHORT).show();
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String savename=sharedPreferences.getString("User Name","No Data Found");
                display.setText(savename);
            }
        });
    }
}