package com.example.admin.Adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.admin.Model.Model;
import com.example.admin.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Adapter extends FirebaseRecyclerAdapter<Model, Adapter.MyViewHolder> {

    public Adapter(@NonNull FirebaseRecyclerOptions<Model> options) {

        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView")final int position, @NonNull Model model) {
        //Show or set data in RecycleView
        holder.categoryname.setText(model.getTittle());
        /*holder.categoryname1.setText(model.getTittle());*/
        Glide.with(holder.img.getContext()).load(model.getPurl()).into(holder.img);
        /*Glide.with(holder.img1.getContext()).load(model.getPurl()).into(holder.img1);*/

        //Update Category
        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_alertdialog))
                        .setExpanded(true,800)
                        .create();

                View myView = dialogPlus.getHolderView();

                EditText update_url = myView.findViewById(R.id.recView_update_edtText_Purl);
                EditText update_category = myView.findViewById(R.id.recView_update_edtText_Categoryname);
                Button update_btn = myView.findViewById(R.id.update_btn_recView);
                Button cancel_btn = myView.findViewById(R.id.cancel_btn_recView);

                update_url.setText(model.getPurl());
                update_category.setText(model.getTittle());

                dialogPlus.show();

                update_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object> map = new HashMap<>();
                        map.put("Tittle",update_category.getText().toString());
                        map.put("purl",update_url.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Category").child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                }
                        );
                    }
                });

                cancel_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialogPlus.dismiss();
                    }
                });

            }
        });

        //Delete Category
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference().child("Category").child(getRef(position).getKey()).removeValue();
            }
        });

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_list_item,parent,false);
        return new MyViewHolder(view);

    }


    class  MyViewHolder extends RecyclerView.ViewHolder{

        CircleImageView img,img1;
        Button update,delete;
        TextView categoryname,categoryname1;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.Picon);
            img1 = itemView.findViewById(R.id.Picon1);
            categoryname1 = itemView.findViewById(R.id.category_tittle1);
            categoryname = itemView.findViewById(R.id.Category_tittle);
            update = itemView.findViewById(R.id.category_btn_update);
            delete = itemView.findViewById(R.id.category_btn_delete);
        }
    }
}
