package com.example.admin.RajAdapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.admin.R;
import com.example.admin.RajModel.CommentModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CommentAdapters extends RecyclerView.Adapter<CommentAdapters.holder10>{


    ArrayList<CommentModel> list;
    Context context;

    public CommentAdapters(ArrayList<CommentModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder10 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.comment_demo_icon,viewGroup,false);
        return new holder10(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder10 holder10, int i) {

        CommentModel model1= list.get(i);

        holder10.commentdata.setText(model1.getCommentuser());


        String personid= model1.getPersonid();



        FirebaseDatabase.getInstance().getReference().child("Profileinfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String proname1=dataSnapshot.child(personid).child("proname").getValue(String.class);
                String pronumber1=dataSnapshot.child(personid).child("pronumber").getValue(String.class);
                String proemail1=dataSnapshot.child(personid).child("proemail").getValue(String.class);
                String proimage1=dataSnapshot.child(personid).child("proimage").getValue(String.class);
                String prostate1=dataSnapshot.child(personid).child("prostate").getValue(String.class);

                holder10.personname.setText(proname1);
                if(proimage1!=null)
                {
                    Glide.with(holder10.personimg.getContext()).load(proimage1).into(holder10.personimg);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder10 extends RecyclerView.ViewHolder
    {

        TextView commentdata;
        TextView personname;
        ImageView personimg;

        public holder10(@NonNull View itemView) {
            super(itemView);

            commentdata=itemView.findViewById(R.id.commenttextsend);
            personimg=itemView.findViewById(R.id.personimage1);
            personname=itemView.findViewById(R.id.personname);
        }
    }
}
