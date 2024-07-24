package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.RajAdapters.CultivationAdapters;
import com.example.admin.RajModel.CultivationModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CultivationPage extends AppCompatActivity {

    RecyclerView recview;
    CultivationAdapters adpt1;

    ArrayList<CultivationModel> list66;
    DatabaseReference reference;
    SearchView searchView;
    ImageView caltback;

    Button cultinserrtbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cultivation_page);


        recview=findViewById(R.id.cultrecview);
        cultinserrtbtn=findViewById(R.id.cultinserrtbtn);

        cultinserrtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), InsertCultivation.class));
            }
        });


        list66=new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference();
        recview.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
//        recview.setNestedScrollingEnabled(false);
        adpt1=new CultivationAdapters(list66,getApplicationContext());
        recview.setAdapter(adpt1);
        reference.child("CaltivationTips").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list66.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    CultivationModel data=snapshot.getValue(CultivationModel.class);
                    list66.add(data);
                }
                adpt1.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });




    }
}