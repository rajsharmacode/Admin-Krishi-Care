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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.admin.FullitemPage;
import com.example.admin.Model.ShopModel;
import com.example.admin.R;
import com.example.admin.RajModel.ItemoModel;
import com.example.admin.UpdateItem;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemAdapters extends RecyclerView.Adapter<ItemAdapters.holder4>{

    ArrayList<ItemoModel> list;
    Context context;

    public ItemAdapters(ArrayList<ItemoModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder4 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_demo_icon,viewGroup,false);
        return new holder4(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder4 holder4, int i) {
        ItemoModel model=list.get(i);
        holder4.itemname.setText(model.getItemname());
        holder4.itemprice.setText("₹ "+String.valueOf(Float.parseFloat(model.getItemprice())));
        Glide.with(holder4.itemimage.getContext()).load(model.getItemimg()).into(holder4.itemimage);

        holder4.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                Intent i1=new Intent(context.getApplicationContext(), FullitemPage.class);
                i1.putExtra("itemdisc",model.getItemdisc());
                i1.putExtra("itemimg",model.getItemimg());
                i1.putExtra("itemprice",model.getItemprice());
                i1.putExtra("itemname",model.getItemname());
                i1.putExtra("itemqnt",model.getItemqnt());
                i1.putExtra("itemid",model.getItemid());
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i1);
            }
        });



        holder4.itemdeletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                builder.setTitle("Delete ?")
                        .setMessage("Do You Want to Delete this Product")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String userkey=model.getItemid();
                                String catkey=model.getCatkey();
                                if(userkey!=null)
                                {
                                    FirebaseDatabase.getInstance().getReference().child("Shopitemdata").child(catkey).child(userkey).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(context, "Product Deleted11", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                                else
                                {
                                    Toast.makeText(context, "Wait", Toast.LENGTH_SHORT).show();
                                }
                                Toast.makeText(context, userkey, Toast.LENGTH_SHORT).show();
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

        holder4.itemupdatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(context.getApplicationContext(), UpdateItem.class);
                i1.putExtra("itemdisc",model.getItemdisc());
                i1.putExtra("itemimg",model.getItemimg());
                i1.putExtra("itemprice",model.getItemprice());
                i1.putExtra("itemname",model.getItemname());
                i1.putExtra("itemqnt",model.getItemqnt());
                i1.putExtra("itemid",model.getItemid());
                i1.putExtra("catkey",model.getCatkey());
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i1);
            }
        });
        /*holder6.adtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

                    String temp= model.getItemid();
                    Intent i=new Intent(context.getApplicationContext(),AddtoCartAdapter.class);
                    i.putExtra("raajid",model.getItemid());
                    //progressBar.setVisibility(View.VISIBLE);
                    HashMap<String, Object> hashMap = new HashMap<>();
                    //Intent i=new Intent(AddressPage.this, InvoiceItemPage.class);
                    hashMap.put("itemdisc",model.getItemdisc());
                    hashMap.put("itemimg",model.getItemimg());
                    hashMap.put("itemprice",model.getItemprice());
                    hashMap.put("itemname",model.getItemname());
                    hashMap.put("itemqnt",model.getItemqnt());
                    hashMap.put("itemid",model.getItemid());

                    //set data to db
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("AddtoCart");
                    ref.child(temp)
                            .setValue(hashMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    //progressBar.setVisibility(View.GONE);
                                    //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
                                    Toast.makeText(context.getApplicationContext(), "Your's Cart is Added", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {
                                    Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }catch (Exception e)
                {
                    Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(context, "Data Added To Cart", Toast.LENGTH_SHORT).show();
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder4 extends RecyclerView.ViewHolder
    {
        ImageView itemimage;
        Button itemdeletebtn,itemupdatebtn;
        TextView itemname,itemprice;
        public holder4(@NonNull View itemView) {
            super(itemView);

            itemname=itemView.findViewById(R.id.itemname1);
            itemprice=itemView.findViewById(R.id.itemprice1);
            itemimage=itemView.findViewById(R.id.itemimg1);
            itemupdatebtn=itemView.findViewById(R.id.itemupdatebtn);
            itemdeletebtn=itemView.findViewById(R.id.itemdeletebtn);
        }
    }
}
