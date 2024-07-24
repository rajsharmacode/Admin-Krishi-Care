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

import com.example.admin.RajAdapters.CropPracticesAdapters;
import com.example.admin.RajAdapters.CultivationAdapters;
import com.example.admin.RajModel.CropPracticesModel;
import com.example.admin.RajModel.CultivationModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CropPracticesPage extends AppCompatActivity {

    RecyclerView recview;
//    CultivationAdapters adpt1;

//    ArrayList<CultivationModel> list66;

//    CultivationAdapters adpt1;
    CropPracticesAdapters adpt1;


    ArrayList<CropPracticesModel> list66;
    DatabaseReference reference;
    SearchView searchView;
    ImageView caltback;

    Button cultinserrtbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_practices_page);



        recview=findViewById(R.id.practrecview);
        cultinserrtbtn=findViewById(R.id.practinserrtbtn);

        cultinserrtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), InsertCropPractices.class));
            }
        });


        list66=new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference();
        recview.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
//        recview.setNestedScrollingEnabled(false);
        adpt1=new CropPracticesAdapters(list66,getApplicationContext());
        recview.setAdapter(adpt1);
        reference.child("ReadPractices").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list66.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {
                    CropPracticesModel data=snapshot.getValue(CropPracticesModel.class);
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