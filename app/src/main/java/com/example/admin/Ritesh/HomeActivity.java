package com.example.admin.Ritesh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.admin.R;

public class HomeActivity extends AppCompatActivity {

    CardView btn_category,btn_item,btn_shop,btn_order,btn_community,btn_report;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_category = findViewById(R.id.btn_category);
        btn_item = findViewById(R.id.btn_item);
        btn_shop = findViewById(R.id.btn_shop);
        btn_order = findViewById(R.id.btn_order);
        btn_community = findViewById(R.id.btn_community);
        btn_report = findViewById(R.id.btn_report);


        btn_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CategoryAddActivity.class));
            }
        });

        btn_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ItemAddActivity.class));
            }
        });

        btn_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ShopActivity.class));
            }
        });

        btn_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_community.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), AdminCommunityActivity.class));
            }
        });

        btn_report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}