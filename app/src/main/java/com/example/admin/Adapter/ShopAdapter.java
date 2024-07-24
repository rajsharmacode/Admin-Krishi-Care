package com.example.admin.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.admin.R;
import com.example.admin.Model.ShopModel;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class ShopAdapter extends FirebaseRecyclerAdapter<ShopModel,ShopAdapter.myviewholder>{

    public ShopAdapter(@NonNull FirebaseRecyclerOptions<ShopModel> options) {

        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, @SuppressLint("RecyclerView")final int position, @NonNull ShopModel model) {
        holder.name.setText("Item name: "+model.getP_name());
        holder.price.setText("Price: "+model.getP_price());
        holder.desc.setText("Description: "+model.getP_desc());
        Glide.with(holder.img.getContext()).load(model.getP_url()).into(holder.img);

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.name.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Deleted data can't be restore.");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        FirebaseDatabase.getInstance().getReference().child("Shop").child(getRef(position).getKey()).removeValue();
                        Toast.makeText(holder.name.getContext(), "Shop item successfully deleted...", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(holder.name.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });

        holder.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*final DialogPlus dialogPlus = DialogPlus.newDialog(holder.name.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_alertdialog))
                        .setExpanded(true,1700)
                        .create();
                dialogPlus.show();*/

                View mdialogView = LayoutInflater.from(holder.name.getContext()).inflate(R.layout.shop_item_update_dialog, null);
                Dialog dialog = new Dialog(holder.name.getContext());
                dialog.setContentView(mdialogView);

                EditText url = dialog.findViewById(R.id.url);
                EditText name = dialog.findViewById(R.id.name);
                EditText price = dialog.findViewById(R.id.price);
                EditText desc = dialog.findViewById(R.id.desc);

                Button update = dialog.findViewById(R.id.btnUpdate);

                url.setText(model.getP_url());
                name.setText(model.getP_name());
                price.setText(model.getP_price());
                desc.setText(model.getP_desc());

                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Map<String,Object>map = new HashMap<>();
                        map.put("P_url",url.getText().toString());
                        map.put("P_name",name.getText().toString());
                        map.put("P_price",price.getText().toString());
                        map.put("P_desc",desc.getText().toString());

                        FirebaseDatabase.getInstance().getReference().child("Shop").child(getRef(position).getKey()).updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.name.getContext(), "Data updated successfully...", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.name.getContext(), "Error while updating...", Toast.LENGTH_SHORT).show();
                                    }
                                });

                    }
                });
                dialog.show();
            }
        });

    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rec_shop,parent,false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder{

        CircleImageView img;
        TextView name,price,desc;

        ImageView btnEdit,btnDelete;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.P_URL);
            name = itemView.findViewById(R.id.tv_P_name);
            price = itemView.findViewById(R.id.tv_P_Price);
            desc = itemView.findViewById(R.id.tv_P_desc);

            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
