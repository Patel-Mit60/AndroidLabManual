package com.example.androidlabmanual;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class Fragment extends AppCompatActivity {

    ViewPager2 viewPage;

    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fragment);


        viewPage = findViewById(R.id.viewpage);
        tabLayout = findViewById(R.id.tablayout);

        ViewPageAdepter ad=new ViewPageAdepter(this);
        viewPage.setAdapter(ad);

        new TabLayoutMediator(tabLayout,viewPage,
                (tab, i) -> {
                    if (i ==0){
                        tab.setText("Login");
                    }
                    else {
                        tab.setText("Register");
                    }
                }).attach();
    }
}