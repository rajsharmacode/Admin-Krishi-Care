package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity3333 extends AppCompatActivity {

    TextView maintotaluser,maintotalcategory,maintotalitem,maintotalposts;
    CardView maincardbanner,maincardshop,maincardcontactus,maincardorder,maincarduser,maincardcroppractices,maincardreport,mancardnotification;



    ImageButton logout_imgBtn;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3333);


        maintotaluser=findViewById(R.id.maintotaluser);
        maintotalcategory=findViewById(R.id.maintotalcategory);
        maintotalitem=findViewById(R.id.maintotalitem);
        maintotalposts=findViewById(R.id.maintotalposts);

        maincardbanner=findViewById(R.id.maincardbanner);
        maincardshop=findViewById(R.id.maincardshop);
        maincardcontactus=findViewById(R.id.maincardcontactus);
        maincardorder=findViewById(R.id.maincardorder);
        maincarduser=findViewById(R.id.maincarduser);
        maincardcroppractices=findViewById(R.id.maincardcroppractices);
        maincardreport=findViewById(R.id.maincardreport);
//        mancardnotification=findViewById(R.id.mancardnotification);

        logout_imgBtn=findViewById(R.id.logout_imgBtn);

        TotalCount();
        maincarduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity3333.this,UserPage.class);
                startActivity(i);
            }
        });
        maincardcroppractices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity3333.this,CropKnowledgePage.class);
                startActivity(i);
            }
        });
        maincardshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity3333.this,ShopCategoryPage.class);
                startActivity(i);
            }
        });
        maincardorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity3333.this,OrderStatus.class);
                startActivity(i);
            }
        });
        maincardreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity3333.this,ReportCommPage.class);
                startActivity(i);
            }
        });

        maincardcontactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity3333.this,ContactUsPage.class);
                startActivity(i);
            }
        });

        logout_imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        maincardbanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(MainActivity3333.this, BannerAdePage.class);
                startActivity(i);
            }
        });


    }

    private void TotalCount() {
        reference= FirebaseDatabase.getInstance().getReference();
        reference.child("Profileinfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count=0;
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot snapshot: dataSnapshot.getChildren())
                    {
                        count++;
                    }
                }
                maintotaluser.setText("User: "+String.valueOf(count));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        reference.child("ShopCategory").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count=0;
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot snapshot: dataSnapshot.getChildren())
                    {
                        count++;
                    }
                }
                maintotalcategory.setText("Category: "+String.valueOf(count));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
        reference.child("AskCommunity").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count=0;
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot snapshot: dataSnapshot.getChildren())
                    {
                        count++;
                    }
                }
                maintotalposts.setText("Posts: "+String.valueOf(count));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });



        reference.child("AllItemProduct").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int count=0;
                if(dataSnapshot.exists())
                {
                    for(DataSnapshot snapshot: dataSnapshot.getChildren())
                    {
                        count++;
                    }
                }
                maintotalitem.setText("Items: "+String.valueOf(count));
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }
}