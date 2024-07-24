package com.example.admin.RajAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.admin.OrderCompleteFullPage;
import com.example.admin.OrderStatusFullPage;
import com.example.admin.R;
import com.example.admin.RajModel.OrderStatusModel;

import java.util.ArrayList;

public class OrderCompleteCancelAdapters extends RecyclerView.Adapter<OrderCompleteCancelAdapters.holder15>{

    ArrayList<OrderStatusModel> list;
    Context context;

    public OrderCompleteCancelAdapters(ArrayList<OrderStatusModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder15 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.ordercomplete_cancel_demo_icon,viewGroup,false);
        return new holder15(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder15 holder15, int i) {
        OrderStatusModel model=list.get(i);
        holder15.itemname.setText(model.getItemname());
        holder15.size.setText(model.getItemqnt());
        holder15.itemprice.setText("₹ "+String.valueOf(Float.parseFloat(model.getItemprice())));
        holder15.status.setText("Order Status:"+model.getOrderstats());
        Glide.with(holder15.itemimage.getContext()).load(model.getItemimg()).into(holder15.itemimage);

        holder15.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(context, OrderCompleteFullPage.class);
                i1.putExtra("itemdisc",model.getItemdisc());
                i1.putExtra("itemimg",model.getItemimg());
                i1.putExtra("itemprice",model.getItemprice());
                i1.putExtra("itemname",model.getItemname());
                i1.putExtra("itemqnt",model.getItemqnt());
                i1.putExtra("itemid",model.getItemid());
                i1.putExtra("orderid",model.getOrderid());
                i1.putExtra("ordertime",model.getOrdertime());
                i1.putExtra("orderstat",model.getOrderstats());
                i1.putExtra("userid",model.getUserid());
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder15 extends RecyclerView.ViewHolder
    {
        ImageView itemimage;
//        Button cartconfrm,cartcancel;
        TextView itemname,itemprice,size,status;
        public holder15(@NonNull View itemView) {
            super(itemView);


            itemname=itemView.findViewById(R.id.cartname);
            itemprice=itemView.findViewById(R.id.cartprice);
            itemimage=itemView.findViewById(R.id.cartimg);
            size=itemView.findViewById(R.id.cartsize);
            status=itemView.findViewById(R.id.cartstats);
//            cartcancel=itemView.findViewById(R.id.cartcancel);
//            cartconfrm=itemView.findViewById(R.id.cartconfrm);
        }
    }
}
