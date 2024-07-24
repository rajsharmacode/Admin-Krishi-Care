package com.example.admin.RajAdapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.admin.R;
import com.example.admin.RajModel.ReportPostModel;
import com.example.admin.ReportFullPage;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReportedPostAdapters extends RecyclerView.Adapter<ReportedPostAdapters.holder9>{

    ArrayList<ReportPostModel> list;
    Context context;

    public ReportedPostAdapters(ArrayList<ReportPostModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public holder9 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(context).inflate(R.layout.reportpost_demo_icon,viewGroup,false);
        return new holder9(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder9 holder9, int i) {
        ReportPostModel model=list.get(i);
        holder9.question.setText(model.getCommques());
        holder9.disc.setText(model.getCommdisc());
//        myviewholder.pp.setText(model.get());
//        Glide.with(myviewholder.personprofileimg.getContext()).load(model.getCommproimg()).into(myviewholder.personprofileimg);
        Glide.with(holder9.img.getContext()).load(model.getCommimage()).into(holder9.img);


        String personid= model.getUid();
        FirebaseDatabase.getInstance().getReference().child("Profileinfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String proname1=dataSnapshot.child(personid).child("proname").getValue(String.class);
                String pronumber1=dataSnapshot.child(personid).child("pronumber").getValue(String.class);
                String proemail1=dataSnapshot.child(personid).child("proemail").getValue(String.class);
                String proimage1=dataSnapshot.child(personid).child("proimage").getValue(String.class);
                String prostate1=dataSnapshot.child(personid).child("prostate").getValue(String.class);


                holder9.pp.setText(proname1);
                if(proimage1!=null)
                {
                    Glide.with(holder9.personprofileimg.getContext()).load(proimage1).into(holder9.personprofileimg);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        holder9.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(context, ReportFullPage.class);
                i.putExtra("question",model.getCommques());
                i.putExtra("disc",model.getCommdisc());
                i.putExtra("image",model.getCommimage());
                i.putExtra("postid",model.getPostid());
                i.putExtra("uid",model.getUid());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
        holder9.reporteditbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                builder.setTitle("Delete ?")
                        .setMessage("Do You Want to Delete this Reported Post")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String userkey=model.getPostid();
                                if(userkey!=null)
                                {
                                    FirebaseDatabase.getInstance().getReference().child("AskCommunity").child(userkey).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(context, "Reported Post Deleted", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    FirebaseDatabase.getInstance().getReference().child("ReportCommPost").child(userkey).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(context, "Reported Post Deleted", Toast.LENGTH_SHORT).show();
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

        holder9.reportcancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                builder.setTitle("Delete ?")
                        .setMessage("Do You Want to Cancel this Reported Post")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                String userkey=model.getPostid();
                                if(userkey!=null)
                                {
                                    FirebaseDatabase.getInstance().getReference().child("ReportCommPost").child(userkey).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(context, "Reported Post Deleted", Toast.LENGTH_SHORT).show();
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
//                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Negative button clicked
//                            }
//                        })
                        .show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class holder9 extends RecyclerView.ViewHolder
    {

        TextView question,disc;
        ImageView img;
        CheckBox like;
        ImageView comment,more;
        ImageView personprofileimg;
        TextView pp,commcounter;
        ImageView deletepost;
        TextView comentcounter;
        int count=0;
        Button reporteditbtn,reportcancelbtn;
        public holder9(@NonNull View itemView) {
            super(itemView);


            question=itemView.findViewById(R.id.cometquestion);
            disc=itemView.findViewById(R.id.cometdisc);
            img=itemView.findViewById(R.id.comentimage);
//            like=itemView.findViewById(R.id.like);
//            comment=itemView.findViewById(R.id.comment);
//            more=itemView.findViewById(R.id.ib_more);
            personprofileimg=itemView.findViewById(R.id.personimage1);
            reportcancelbtn=itemView.findViewById(R.id.reportcancelbtn);
            reporteditbtn=itemView.findViewById(R.id.reporteditbtn);
            pp=itemView.findViewById(R.id.commproname);
//            commcounter=itemView.findViewById(R.id.commcounter);
//            deletepost=itemView.findViewById(R.id.deletepost);
//            comentcounter=itemView.findViewById(R.id.comentcounter);
        }
    }
}
