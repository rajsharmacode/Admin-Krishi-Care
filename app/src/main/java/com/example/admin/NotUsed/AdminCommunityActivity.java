package com.example.admin.NotUsed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.admin.Adapter.CommunityAdapter;
import com.example.admin.Model.CommunityModel;
import com.example.admin.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminCommunityActivity extends AppCompatActivity {

    CircleImageView btn_flot;
    RecyclerView recyclerView;
    CommunityAdapter adapter;

    ImageView btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_community);

        btn_flot = findViewById(R.id.btn_flot);
        recyclerView = findViewById(R.id.rec_Community);
        btnBack = findViewById(R.id.btnBackAdmin);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });
        

        btn_flot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddCommunityActivity.class));
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerOptions<CommunityModel>options = new FirebaseRecyclerOptions.Builder<CommunityModel>()
                .setQuery(FirebaseDatabase.getInstance().getReference().child("Community"),CommunityModel.class)
                .build();

        adapter = new CommunityAdapter(options);
        recyclerView.setAdapter(adapter);




    }
    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}