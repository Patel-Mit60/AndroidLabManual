package com.example.androidlabmanual;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyviewHolder extends RecyclerView.ViewHolder {

    ImageView img1;

    TextView txt3,txt4;
    public MyviewHolder(@NonNull View itemView) {
        super(itemView);

        img1=itemView.findViewById(R.id.img1);
        txt3=itemView.findViewById(R.id.txt3);
        txt4=itemView.findViewById(R.id.txt4);
    }
}
