package com.example.admin.Adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.admin.Ritesh.MessageActivity;
import com.example.admin.Model.CommunityModel;
import com.example.admin.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class CommunityAdapter extends FirebaseRecyclerAdapter<CommunityModel,CommunityAdapter.myviewholder>{
    public CommunityAdapter(@NonNull FirebaseRecyclerOptions<CommunityModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull CommunityModel model) {
        holder.txtUpdate.setText(model.getContent());
        Glide.with(holder.img.getContext()).load(model.getImgURL()).into(holder.img);
        holder.txtTimeDate.setText(model.getTimeDate());


        holder.msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(holder.img.getContext(), MessageActivity.class);
                v.getContext().startActivity(i);

            }
        });
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_community_post,parent,false);
        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder{

        ImageView img;
        TextView txtUpdate;
        TextView txtTimeDate;
        ImageButton msg;
        ImageButton more;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            img = itemView.findViewById(R.id.Community_image);
            txtUpdate = itemView.findViewById(R.id.comm_question);
            txtTimeDate = itemView.findViewById(R.id.comm_timedate);

            msg = itemView.findViewById(R.id.iv_comment);
            more = itemView.findViewById(R.id.iv_more);




        }
    }


}


