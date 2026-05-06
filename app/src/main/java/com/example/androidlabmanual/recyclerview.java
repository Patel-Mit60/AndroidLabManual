package com.example.androidlabmanual;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class recyclerview extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_recycleview);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView recyclerView=findViewById(R.id.recycleview);

        List<item> items = new ArrayList<item>();
        items.add(new item("M.B.Patel","mit@gmail.com",R.drawable.a));
        items.add(new item("M.B.Patel","mit@gmail.com",R.drawable.b));
        items.add(new item("M.B.Patel","mit@gmail.com",R.drawable.c));
        items.add(new item("M.B.Patel","mit@gmail.com",R.drawable.d));
        items.add(new item("M.B.Patel","mit@gmail.com",R.drawable.e));
        items.add(new item("M.B.Patel","mit@gmail.com",R.drawable.f));
        items.add(new item("M.B.Patel","mit@gmail.com",R.drawable.g));
        items.add(new item("M.B.Patel","mit@gmail.com",R.drawable.h));
        items.add(new item("M.B.Patel","mit@gmail.com",R.drawable.a));
        items.add(new item("M.B.Patel","mit@gmail.com",R.drawable.b));
        items.add(new item("M.B.Patel","mit@gmail.com",R.drawable.c));
        items.add(new item("M.B.Patel","mit@gmail.com",R.drawable.d));
        items.add(new item("M.B.Patel","mit@gmail.com",R.drawable.e));
        items.add(new item("M.B.Patel","mit@gmail.com",R.drawable.f));
        items.add(new item("M.B.Patel","mit@gmail.com",R.drawable.g));
        items.add(new item("M.B.Patel","mit@gmail.com",R.drawable.h));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter(this,items));

    }
}