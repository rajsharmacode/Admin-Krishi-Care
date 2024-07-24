package com.example.admin.Ritesh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.admin.Adapter.ShopAdapter;
import com.example.admin.Model.ShopModel;
import com.example.admin.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    ImageView shop_iv_back;
    RecyclerView recView;
    DatabaseReference dbRef;
    ShopAdapter adapter;
    ArrayList<ShopAdapter> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        shop_iv_back = findViewById(R.id.shop_iv_back);
        shop_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        recView = findViewById(R.id.rec_ShopItems);
        recView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ShopModel> options =
                new FirebaseRecyclerOptions.Builder<ShopModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Shop"), ShopModel.class)
                        .build();

        adapter = new ShopAdapter(options);
        recView.setAdapter(adapter);

    }

    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }

}