package com.example.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class FullitemPage extends AppCompatActivity {
    String itemname,itemdisc,itemimg,itemqnt,itemprice,itemid;

    TextView name,price,qnt,disc;
    ImageView fullimg;
    Button addbtn,buybtn;
    ImageView back;
    TextView fulltoolbarname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullitem_page);


        itemdisc=getIntent().getStringExtra("itemdisc");
        itemimg=getIntent().getStringExtra("itemimg");
        itemprice=getIntent().getStringExtra("itemprice");
        itemname=getIntent().getStringExtra("itemname");
        itemqnt=getIntent().getStringExtra("itemqnt");
        itemqnt=getIntent().getStringExtra("itemqnt");
        itemid=getIntent().getStringExtra("itemid");

        name=findViewById(R.id.fullname);
        price=findViewById(R.id.fullprice);
        fullimg=findViewById(R.id.fullimg);
        disc=findViewById(R.id.fulldisc);
        qnt=findViewById(R.id.fullqnt);
//        buybtn=findViewById(R.id.buybtn);
//        addbtn=findViewById(R.id.addtocartbtn);
        back=findViewById(R.id.addtocartback);
        fulltoolbarname=findViewById(R.id.fulltoolbarname);


        String data=getIntent().getStringExtra("catname22");
        fulltoolbarname.setText("Product Details");


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        name.setText("  "+itemname);
        price.setText("₹ "+itemprice);
        disc.setText(itemdisc);
        qnt.setText(itemqnt);
        Glide.with(getApplicationContext()).load(itemimg).into(fullimg);


//        buybtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(getApplicationContext(),InvoiceItemPage.class);
//                i.putExtra("itemdisc",itemdisc);
//                i.putExtra("itemimg",itemimg);
//                i.putExtra("itemprice",itemprice);
//                i.putExtra("itemname",itemname);
//                i.putExtra("itemqnt",itemqnt);
////                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(i);
//            }
//        });


       /* addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {

//                    Intent i=new Intent(getApplicationContext(), AddtoCartAdapter.class);
                    //progressBar.setVisibility(View.VISIBLE);
                    HashMap<String, Object> hashMap = new HashMap<>();
                    //Intent i=new Intent(AddressPage.this, InvoiceItemPage.class);
                    hashMap.put("itemdisc",itemdisc);
                    hashMap.put("itemimg",itemimg);
                    hashMap.put("itemprice",itemprice);
                    hashMap.put("itemname",itemname);
                    hashMap.put("itemqnt",itemqnt);
                    hashMap.put("itemid",itemid);

                    //set data to db
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("AddtoCart");
                    ref.child(itemid)
                            .setValue(hashMap)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    //progressBar.setVisibility(View.GONE);
                                    //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
                                    Toast.makeText(getApplicationContext(), "Your's Cart is Added", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(Exception e) {
                                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
//                Toast.makeText(context, "Data Added To Cart", Toast.LENGTH_SHORT).show();
            }
        });*/

    }
}