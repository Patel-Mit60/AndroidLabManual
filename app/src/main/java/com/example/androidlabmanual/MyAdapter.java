package com.example.androidlabmanual;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyviewHolder> {
    Context context;

    List<item> items;
    public MyAdapter(Context context, List<item> items) {
        this.context = context;
        this.items = items;
    }



    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyviewHolder(LayoutInflater.from(context).inflate(R.layout.iteam_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        holder.txt3.setText(items.get(position).getName());
        holder.txt4.setText(items.get(position).getEmail());
        holder.img1.setImageResource(items.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
