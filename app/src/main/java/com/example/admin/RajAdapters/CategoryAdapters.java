package com.example.admin.RajAdapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.admin.AddUserdataPage;
import com.example.admin.ItemPage;
import com.example.admin.R;
import com.example.admin.RajModel.CategoryModel;
import com.example.admin.UpdateCategory;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CategoryAdapters extends RecyclerView.Adapter<CategoryAdapters.holder3>{
    ArrayList<CategoryModel> list;
    Context context;

    public CategoryAdapters(ArrayList<CategoryModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder3 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.category_demo_icon,viewGroup,false);
        return new holder3(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder3 holder3, int i) {
        CategoryModel model=list.get(i);
        holder3.catname.setText(model.getCatname());
        Glide.with(holder3.catimg.getContext()).load(model.getCatimage()).into(holder3.catimg);
        holder3.catimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempda=model.getCatname();
                String userkey1=model.getUserkey();
                Intent i=new Intent(context.getApplicationContext(), ItemPage.class);
                i.putExtra("catname22",tempda);
                i.putExtra("userkey",userkey1);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                context.startActivity(i);
            }
        });
        holder3.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(context.getApplicationContext(), UpdateCategory.class);
                i1.putExtra("catimage",model.getCatimage());
                i1.putExtra("catname",model.getCatname());
                i1.putExtra("userkey",model.getUserkey());
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i1);
            }
        });

        holder3.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                builder.setTitle("Delete ?")
                        .setMessage("Do You Want to Delete this Category")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String userkey=model.getUserkey();
                                if(userkey!=null)
                                {
                                    FirebaseDatabase.getInstance().getReference().child("ShopCategory").child(userkey).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(context, "Category Deleted", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                else
                                {
                                    Toast.makeText(context, "Wait", Toast.LENGTH_SHORT).show();
                                }
//                                Toast.makeText(context, userkey, Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                // Negative button clicked
                            }
                        })
                        .show();
            }
        });
        holder3.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder3 extends RecyclerView.ViewHolder
    {
        ImageView catimg;
        TextView catname;
        CardView cardView1;
        Button delete,update;
        public holder3(@NonNull View itemView) {
            super(itemView);

            catimg=itemView.findViewById(R.id.catimage);
            catname=itemView.findViewById(R.id.catname);
            update=itemView.findViewById(R.id.catupdatebtn);
            delete=itemView.findViewById(R.id.catdeletebtn);
//            cardView1=itemView.findViewById(R.id.gridview1);
        }
    }
}
