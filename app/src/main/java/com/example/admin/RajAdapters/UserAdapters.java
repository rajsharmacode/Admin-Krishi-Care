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
import com.example.admin.AddUserdataPage;
import com.example.admin.R;
import com.example.admin.RajModel.UserModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapters extends RecyclerView.Adapter<UserAdapters.holder1>{
    ArrayList<UserModel> list;
    Context context;

    public UserAdapters(ArrayList<UserModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder1 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.user_demo_icon,viewGroup,false);
        return new holder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder1 holder1, int i) {
        UserModel model=list.get(i);

        holder1.username.setText(model.getProname());
        holder1.usernumber.setText("+91"+String.valueOf(model.getPronumber()));
        Glide.with(holder1.userimg.getContext()).load(model.getProimage()).into(holder1.userimg);

//        AlertDialog.Builder builder;
//        builder = new AlertDialog.Builder(context);

        holder1.userdeletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                builder.setTitle("Delete ?")
                        .setMessage("Do You Want to Delete this User")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String userkey=model.getUserkey();
                                if(userkey!=null)
                                {
                                        FirebaseDatabase.getInstance().getReference().child("Profileinfo").child(userkey).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                Toast.makeText(context, "User Deleted", Toast.LENGTH_SHORT).show();
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

        holder1.usereditbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(context.getApplicationContext(), AddUserdataPage.class);
                i1.putExtra("username",model.getProname());
                i1.putExtra("usernumber",model.getPronumber());
                i1.putExtra("userimg",model.getProimage());
                i1.putExtra("userkey",model.getUserkey());
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder1 extends RecyclerView.ViewHolder
    {

//        AlertDialog.Builder builder;
        CircleImageView userimg;

        TextView username,usernumber;
        ImageView usereditbtn,userdeletebtn;

        public holder1(@NonNull View itemView) {
            super(itemView);

            userimg=itemView.findViewById(R.id.userimg);
            username=itemView.findViewById(R.id.username);
            usernumber=itemView.findViewById(R.id.usernumber);
            usereditbtn=itemView.findViewById(R.id.usereditbtn);
            userdeletebtn=itemView.findViewById(R.id.userdeletebtn);


        }
    }
}
