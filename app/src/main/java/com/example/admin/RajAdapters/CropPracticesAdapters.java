package com.example.admin.RajAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.admin.FullCropPractices;
import com.example.admin.Fullcultivation;
import com.example.admin.R;
import com.example.admin.RajModel.CropPracticesModel;
import com.example.admin.RajModel.CultivationModel;

import java.util.ArrayList;

public class CropPracticesAdapters extends RecyclerView.Adapter<CropPracticesAdapters.holder6>{

    ArrayList<CropPracticesModel> list;
    Context context;

    public CropPracticesAdapters(ArrayList<CropPracticesModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder6 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.cropknowledge_demo_icon,viewGroup,false);
        return new holder6(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder6 holder6, int i) {
        CropPracticesModel model=list.get(i);
        holder6.readname.setText(model.getReadname());
        Glide.with(holder6.readimg.getContext()).load(model.getReadimg()).into(holder6.readimg);

        holder6.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(context.getApplicationContext(), FullCropPractices.class);
                i1.putExtra("readdata",model.getReaddata());
                i1.putExtra("readimg",model.getReadimg());
                i1.putExtra("readname",model.getReadname());
                i1.putExtra("calkey1",model.getReadkey());
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder6 extends RecyclerView.ViewHolder
    {
        TextView readname;
        ImageView readimg;
        public holder6(@NonNull View itemView) {
            super(itemView);

            readname=itemView.findViewById(R.id.readname);
            readimg=itemView.findViewById( R.id.readimg);
        }
    }
}
