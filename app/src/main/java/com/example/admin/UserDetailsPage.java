package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.RajAdapters.UserAdapters;
import com.example.admin.RajModel.UserModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserDetailsPage extends AppCompatActivity {

    RecyclerView recview;

    ArrayList<UserModel> list;
    DatabaseReference reference;
    UserAdapters adpt;
    ImageButton userbackbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details_page);

//        recview=findViewById(R.id.userrecview);
//        userbackbtn=findViewById(R.id.userbackbtn);
//
//
//        list=new ArrayList<>();
//        reference= FirebaseDatabase.getInstance().getReference();
//        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        adpt=new UserAdapters(list,getApplicationContext());
//        recview.setAdapter(adpt);
//        reference.child("Profileinfo").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists())
//                {
////                    emptycartimg.setVisibility(View.GONE);
//                    list.clear();
//                    for(DataSnapshot snapshot: dataSnapshot.getChildren())
//                    {
//                        UserModel data=snapshot.getValue(UserModel.class);
//                        list.add(data);
//                    }
//                }
//                else
//                {
//                    list.clear();
//                    Toast.makeText(getApplicationContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
//                }
//                adpt.notifyDataSetChanged();
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//        });
//        adpt.notifyDataSetChanged();
    }
}
