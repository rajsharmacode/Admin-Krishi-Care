package com.example.admin.RajAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.admin.OrderStatus;
import com.example.admin.OrderStatusFullPage;
import com.example.admin.R;
import com.example.admin.RajModel.OrderStatusModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class OrderStatusAdapters extends RecyclerView.Adapter<OrderStatusAdapters.holder7>{

    ArrayList<OrderStatusModel> list;
    Context context;

    public OrderStatusAdapters(ArrayList<OrderStatusModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder7 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.order_status_demo_icon,viewGroup,false);
        return new holder7(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder7 holder7, int i) {
        OrderStatusModel model=list.get(i);
        holder7.itemname.setText(model.getItemname());
        holder7.size.setText(model.getItemqnt());
        holder7.itemprice.setText("₹ "+String.valueOf(Float.parseFloat(model.getItemprice())));
        Glide.with(holder7.itemimage.getContext()).load(model.getItemimg()).into(holder7.itemimage);

        holder7.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(context, OrderStatusFullPage.class);
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
        holder7.cartconfrm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemid= model.getItemid();

                try {

                    Date currentDate = new Date();

                    // Format the date and time
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String formattedDate = dateFormat.format(currentDate);

                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                    String formattedTime = timeFormat.format(currentDate);
//                        String temp= model.getItemid();
//                        Intent i=new Intent(context.getApplicationContext(), AddtoCartAdapter.class);
//                        i.putExtra("raajid",model.getItemid());
//                        //progressBar.setVisibility(View.VISIBLE);
                    HashMap<String, Object> hashMap = new HashMap<>();
                    //Intent i=new Intent(AddressPage.this, InvoiceItemPage.class);

                    hashMap.put("itemdisc",model.getItemdisc());
                    hashMap.put("itemimg",model.getItemimg());
                    hashMap.put("itemprice",model.getItemprice());
                    hashMap.put("itemname",model.getItemname());
                    hashMap.put("itemqnt",model.getItemqnt());
                    hashMap.put("itemid",itemid);
                    hashMap.put("orderid",model.getOrderid());
                    hashMap.put("ordertime", model.getOrdertime());
                    hashMap.put("orderstats","Order Delevered (Completed)");
                    hashMap.put("userid",model.getUserid());

                    //set data to db
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("OrderCompleteCancel");
                    ref.child(itemid)
                            .setValue(hashMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    //progressBar.setVisibility(View.GONE);
                                    //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
                                    Toast.makeText(context.getApplicationContext(), "sucessss", Toast.LENGTH_SHORT).show();
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


                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("orderstats","Order Delevered (Completed)");
//                        hashMap.put("itemid",itemid);
//                    hashMap.put("userkey", uidd);


                //set data to db
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("MyOrder");
                ref.child(itemid)
                        .updateChildren(hashMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context.getApplicationContext(), "Product updeted", Toast.LENGTH_SHORT).show();

                            }
                        });

                if(itemid!=null)
                {
                    FirebaseDatabase.getInstance().getReference().child("OrderStatus").child(itemid).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(context, "Order Confurmed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(context, "Wait", Toast.LENGTH_SHORT).show();
                }
            }
        });

        holder7.cartcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemid= model.getItemid();

                try {

                    Date currentDate = new Date();

                    // Format the date and time
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                    String formattedDate = dateFormat.format(currentDate);

                    SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                    String formattedTime = timeFormat.format(currentDate);
//                        String temp= model.getItemid();
//                        Intent i=new Intent(context.getApplicationContext(), AddtoCartAdapter.class);
//                        i.putExtra("raajid",model.getItemid());
//                        //progressBar.setVisibility(View.VISIBLE);
                    HashMap<String, Object> hashMap = new HashMap<>();
                    //Intent i=new Intent(AddressPage.this, InvoiceItemPage.class);



                    hashMap.put("itemdisc",model.getItemdisc());
                    hashMap.put("itemimg",model.getItemimg());
                    hashMap.put("itemprice",model.getItemprice());
                    hashMap.put("itemname",model.getItemname());
                    hashMap.put("itemqnt",model.getItemqnt());
                    hashMap.put("itemid",itemid);
                    hashMap.put("orderid",model.getOrderid());
                    hashMap.put("ordertime", model.getOrdertime());
                    hashMap.put("orderstats","Order Canceled");
                    hashMap.put("userid",model.getUserid());

                    //set data to db
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("OrderCompleteCancel");
                    ref.child(itemid)
                            .setValue(hashMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    //progressBar.setVisibility(View.GONE);
                                    //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
                                    Toast.makeText(context.getApplicationContext(), "sucessss", Toast.LENGTH_SHORT).show();
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


                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("orderstats","Order Canceled");
//                        hashMap.put("itemid",itemid);
//                    hashMap.put("userkey", uidd);


                //set data to db
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference("MyOrder");
                ref.child(itemid)
                        .updateChildren(hashMap)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context.getApplicationContext(), "Product updeted", Toast.LENGTH_SHORT).show();

                            }
                        });

                if(itemid!=null)
                {
                    FirebaseDatabase.getInstance().getReference().child("OrderStatus").child(itemid).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(context, "Order Canceled", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else
                {
                    Toast.makeText(context, "Wait", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        holder6.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i1=new Intent(context, FullItemdetailPage.class);
//                i1.putExtra("itemdisc",model.getItemdisc());
//                i1.putExtra("itemimg",model.getItemimg());
//                i1.putExtra("itemprice",model.getItemprice());
//                i1.putExtra("itemname",model.getItemname());
//                i1.putExtra("itemqnt",model.getItemqnt());
//                i1.putExtra("itemid",model.getItemid());
//                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                context.startActivity(i1);
//            }
//        });
//        holder6.adtocart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//
//                    String temp= model.getItemid();
//                    Intent i=new Intent(context.getApplicationContext(),AddtoCartAdapter.class);
//                    i.putExtra("raajid",model.getItemid());
//                    //progressBar.setVisibility(View.VISIBLE);
//                    HashMap<String, Object> hashMap = new HashMap<>();
//                    //Intent i=new Intent(AddressPage.this, InvoiceItemPage.class);
//                    hashMap.put("itemdisc",model.getItemdisc());
//                    hashMap.put("itemimg",model.getItemimg());
//                    hashMap.put("itemprice",model.getItemprice());
//                    hashMap.put("itemname",model.getItemname());
//                    hashMap.put("itemqnt",model.getItemqnt());
//                    hashMap.put("itemid",model.getItemid());
//
//                    //set data to db
//                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("AddtoCart");
//                    ref.child(temp)
//                            .setValue(hashMap)
//                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void unused) {
//                                    //progressBar.setVisibility(View.GONE);
//                                    //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
//                                    Toast.makeText(context.getApplicationContext(), "Your's Cart is Added", Toast.LENGTH_SHORT).show();
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(Exception e) {
//                                    Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                }catch (Exception e)
//                {
//                    Toast.makeText(context.getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
//                }
////                Toast.makeText(context, "Data Added To Cart", Toast.LENGTH_SHORT).show();
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder7 extends RecyclerView.ViewHolder
    {
        ImageView itemimage;
        Button cartconfrm,cartcancel;
        TextView itemname,itemprice,size;
        public holder7(@NonNull View itemView) {
            super(itemView);



            itemname=itemView.findViewById(R.id.cartname);
            itemprice=itemView.findViewById(R.id.cartprice);
            itemimage=itemView.findViewById(R.id.cartimg);
            size=itemView.findViewById(R.id.cartsize);
            cartcancel=itemView.findViewById(R.id.cartcancel);
            cartconfrm=itemView.findViewById(R.id.cartconfrm);
        }
    }
}
