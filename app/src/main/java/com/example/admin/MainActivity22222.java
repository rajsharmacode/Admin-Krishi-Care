package com.example.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.FirebaseDatabase;

public class MainActivity22222 extends AppCompatActivity {

    Button userpagebtn;
    Button categorybtn;
    Button cropknowdge;
    Button OrderPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main22222);
        userpagebtn=findViewById(R.id.userpagebtn);
        categorybtn=findViewById(R.id.categorybtn);
        cropknowdge=findViewById(R.id.cropknowdge);
        OrderPage=findViewById(R.id.OrderPage);
        userpagebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity22222.this,UserPage.class);
                startActivity(i);


            }
        });
        categorybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity22222.this,ShopCategoryPage.class);
                startActivity(i);


            }
        });
        cropknowdge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity22222.this,CropKnowledgePage.class);
                startActivity(i);


            }
        });
        OrderPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity22222.this,OrderStatus.class);
                startActivity(i);


            }
        });
    }
}