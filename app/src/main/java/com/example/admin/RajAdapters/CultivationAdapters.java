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
import com.example.admin.Fullcultivation;
import com.example.admin.R;
import com.example.admin.RajModel.CategoryModel;
import com.example.admin.RajModel.CultivationModel;
import com.example.admin.UpdateCaltivation;

import java.util.ArrayList;

public class CultivationAdapters extends RecyclerView.Adapter<CultivationAdapters.holder5>{

    ArrayList<CultivationModel> list;
    Context context;

    public CultivationAdapters(ArrayList<CultivationModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder5 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.cropknowledge_demo_icon,viewGroup,false);
        return new holder5(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder5 holder5, int i) {
        CultivationModel model=list.get(i);
        holder5.readname.setText(model.getCaltname());
        Glide.with(holder5.readimg.getContext()).load(model.getCaltimg()).into(holder5.readimg);

        holder5.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(context.getApplicationContext(), Fullcultivation.class);
                i1.putExtra("readdata",model.getCaltdisc());
                i1.putExtra("readimg",model.getCaltimg());
                i1.putExtra("readname",model.getCaltname());
                i1.putExtra("calkey",model.getCalkey());
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder5 extends RecyclerView.ViewHolder
    {
        TextView readname;
        ImageView readimg;
        public holder5(@NonNull View itemView) {
            super(itemView);

            readname=itemView.findViewById(R.id.readname);
            readimg=itemView.findViewById( R.id.readimg);
        }
    }
}
