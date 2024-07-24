package com.example.admin.Ritesh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.admin.Adapter.Adapter;
import com.example.admin.Model.Model;
import com.example.admin.R;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class CategoryAddActivity extends AppCompatActivity {
    Button btn_addCategory;
    ImageView btn_back_category;
    RecyclerView recyclerView;
    Adapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_add);

        btn_back_category = findViewById(R.id.btn_category_back);
        btn_addCategory = findViewById(R.id.btn_addCategory);

        btn_back_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //Onclick
        btn_addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View mdialogView = LayoutInflater.from(CategoryAddActivity.this).inflate(R.layout.custom_category_dialog, null);
                Dialog dialog = new Dialog(CategoryAddActivity.this);
                dialog.setContentView(mdialogView);
                Button dialog_btn_cancel = dialog.findViewById(R.id.dialog_btn_cancel);
                Button dialog_btn_add = dialog.findViewById(R.id.dialog_btn_add);
                EditText dialog_categoryName = dialog.findViewById(R.id.dialog_categoryName);
                EditText dialog_category_img_link = dialog.findViewById(R.id.dialog_category_img_link);

                // Set click listener for the cancel button
                dialog_btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss(); // Dismiss the dialog when cancel button is clicked
                    }
                });

                // Set click listener for the add button
                dialog_btn_add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        insertData();
                        dialog.dismiss(); // Dismiss the dialog after handling the action
                    }

                    private void insertData() {
                        Map<String,Object> map = new HashMap<>();
                        map.put("Tittle",dialog_categoryName.getText().toString());
                        map.put("purl",dialog_category_img_link.getText().toString());
                        FirebaseDatabase.getInstance().getReference().child("Category").push()
                                .setValue(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(CategoryAddActivity.this, "Category add successfully...", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(CategoryAddActivity.this, "Failled", Toast.LENGTH_SHORT).show();
                                    }
                                }
                        );
                    }
                });
                // Show the dialog
                dialog.show();
            }
        });


        recyclerView = findViewById(R.id.rec_view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Model>options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Category"), Model.class)
                        .build();


        adapter= new Adapter(options);
        recyclerView.setAdapter(adapter);

        adapter.notifyDataSetChanged();

    }
    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.startListening();
    }

}
