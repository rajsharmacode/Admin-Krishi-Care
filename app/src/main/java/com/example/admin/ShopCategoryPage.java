package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.RajAdapters.CategoryAdapters;
import com.example.admin.RajModel.CategoryModel;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ShopCategoryPage extends AppCompatActivity {


    RecyclerView recview;
    DatabaseReference df;
    CategoryAdapters adpt;
    TextView txtdemo;
//    ExtendedFloatingActionButton floatingbtn;
    Button btnn;

    ArrayList<CategoryModel> list;
    DatabaseReference reference;
    Button categoryinsertbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_category_page);


        recview=(RecyclerView) findViewById(R.id.categoryrecview);
        categoryinsertbtn=findViewById(R.id.categoryinsertbtn);
//        txtdemo=(TextView) view.findViewById(R.id.catid);


        list=new ArrayList<>();

        reference= FirebaseDatabase.getInstance().getReference();
        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//        recview.setNestedScrollingEnabled(false);
        adpt=new CategoryAdapters(list,getApplicationContext());
        recview.setAdapter(adpt);
        reference.child("ShopCategory").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                list.clear();
                for(DataSnapshot snapshot: dataSnapshot.getChildren())
                {

                    CategoryModel data=snapshot.getValue(CategoryModel.class);
                    list.add(data);
//                    list.clear();

                }
//                adpt.notifyAll();
                adpt.notifyDataSetChanged();


//                adpt=new Cmodel33333(list,);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }

        });

        categoryinsertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),InsertCategory.class));
            }
        });

    }
}