package com.example.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CropKnowledgePage extends AppCompatActivity {

    Button cultivation,pestanddesase,croppractices;
    CardView cal,prac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop_knowledge_page);
//        croppractices=findViewById(R.id.croppractices);
        prac=findViewById(R.id.croppraccard);
//        cultivation=findViewById(R.id.cultivation);
        cal=findViewById(R.id.cultivationcard);


        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),CultivationPage.class));
            }
        });

        prac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),CropPracticesPage.class));
            }
        });

    }
}