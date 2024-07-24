package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.admin.R;
import com.example.admin.RajAdapters.UserAdapters;
import com.example.admin.RajModel.UserModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserPage extends AppCompatActivity {
    RecyclerView recview;

    ArrayList<UserModel> list;
//    ArrayList<UserModel> list;
    DatabaseReference reference;
    UserAdapters adpt;
//    UserAdapters adpt;
    ImageButton userbackbtn;
    ProgressBar userprogressBar;

    ExtendedFloatingActionButton floatingbtn;
//    Button insertbtn;



    FloatingActionButton insertbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
//        setTheme(R.style.Theme_AppCompat);
//        setTheme(R.style.Theme_AppCompat);
//        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_page);

        recview=findViewById(R.id.userrecview);
        userbackbtn=findViewById(R.id.userbackbtn);
        userprogressBar=findViewById(R.id.userprogressBar);
        insertbtn=findViewById(R.id.insertbtn);
//        floatingbtn=findViewById(R.id.floatingbtn);



        list=new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference();
        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adpt=new UserAdapters(list,getApplicationContext());
        recview.setAdapter(adpt);
        reference.child("Profileinfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                {
//                    emptycartimg.setVisibility(View.GONE);
                    list.clear();
                    for(DataSnapshot snapshot: dataSnapshot.getChildren())
                    {
                        UserModel data=snapshot.getValue(UserModel.class);
                        list.add(data);
                    }
                }
                else
                {
                    list.clear();
                    Toast.makeText(getApplicationContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
                }
                adpt.notifyDataSetChanged();
                userprogressBar.setVisibility(View.GONE);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }

        });
        adpt.notifyDataSetChanged();
        insertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                startActivity(new Intent(getApplicationContext(),InsertUserPage.class));
                startActivity(new Intent(getApplicationContext(),UserAddinFirebaseNumber.class));
            }
        });
    }
}