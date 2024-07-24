package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.admin.RajAdapters.ReportedPostAdapters;
import com.example.admin.RajModel.ReportPostModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReportCommPage extends AppCompatActivity {

    RecyclerView recview;
    ReportedPostAdapters adpt;
//    ExtendedFloatingActionButton floatingbtn;
    ArrayList<ReportPostModel> list;
    DatabaseReference reference;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_comm_page);

        recview=findViewById(R.id.reportpost);

        list= new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference();
        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adpt=new ReportedPostAdapters(list,getApplicationContext());
        recview.setAdapter(adpt);
        reference.child("ReportCommPost").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    ReportPostModel data=snapshot.getValue(ReportPostModel.class);
                    list.add(data);
                }
                adpt.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}