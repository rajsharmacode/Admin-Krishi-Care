package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OrderCompleteFullPage extends AppCompatActivity {

    TextView ordid,ordtime,ordstat;

    ImageView ordimg;
    TextView ordname,ordprice,ordsize;

    TextView ordaddress;


    TextView ordmrp,orddiscount,ordsellprice,ordshipping,ordgranttoal;

    String itemname,itemdisc,itemimg,itemqnt,itemprice,itemid,orderid,orderstat,ordertime;

    DatabaseReference reference;
    FirebaseUser user;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_complete_full_page);

        ordid=findViewById(R.id.myorderid);
        ordtime=findViewById(R.id.myossrdertime);
        ordstat=findViewById(R.id.myorderstat);

        ordname=findViewById(R.id.ordname);
        ordprice=findViewById(R.id.ordprice);
        ordsize=findViewById(R.id.ordsize);
        ordimg=findViewById(R.id.ordimg);

        ordaddress=findViewById(R.id.ordalladdress);

        ordmrp=findViewById(R.id.ordmrp);
        orddiscount=findViewById(R.id.orddiscount);
        ordsellprice=findViewById(R.id.ordsellingpr);
        ordshipping=findViewById(R.id.ordshipping);
        ordgranttoal=findViewById(R.id.ordtotalgrantprice);




        itemdisc=getIntent().getStringExtra("itemdisc");
        itemimg=getIntent().getStringExtra("itemimg");
        itemprice=getIntent().getStringExtra("itemprice");
        itemname=getIntent().getStringExtra("itemname");
        itemqnt=getIntent().getStringExtra("itemqnt");
        itemqnt=getIntent().getStringExtra("itemqnt");
        itemid=getIntent().getStringExtra("itemid");
        orderid=getIntent().getStringExtra("orderid");
        orderstat=getIntent().getStringExtra("orderstat");
        ordertime=getIntent().getStringExtra("ordertime");
        uid=getIntent().getStringExtra("userid");


        ordid.setText("Order ID: "+orderid);
        ordtime.setText("Order Date: "+ordertime);
        ordstat.setText("Order Status: "+orderstat);


        ordname.setText(itemname);
        ordprice.setText("Price: ₹ "+itemprice);
        ordsize.setText("Size: "+itemqnt);
        Glide.with(getApplicationContext()).load(itemimg).into(ordimg);


//
//        user= FirebaseAuth.getInstance().getCurrentUser();
//        uid=user.getUid();


        reference= FirebaseDatabase.getInstance().getReference();
        reference.child("AddressOfUser").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String name=dataSnapshot.child(uid).child("name").getValue(String.class);
                String phno=dataSnapshot.child(uid).child("phno").getValue(String.class);
                String pincode=dataSnapshot.child(uid).child("pincode").getValue(String.class);
                String city=dataSnapshot.child(uid).child("city").getValue(String.class);
                String state=dataSnapshot.child(uid).child("state").getValue(String.class);
                String plot=dataSnapshot.child(uid).child("plot").getValue(String.class);
                String address=dataSnapshot.child(uid).child("address").getValue(String.class);
                if(name==null)
                {
//                    temp=1;
                }else
                {
                    ordaddress.setText(name+"\n"+phno+"\n"+plot+" "+address+"\n"+city+" "+state+" "+pincode);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        float amt=Float.valueOf(itemprice);
        ordmrp.setText("₹ "+amt);
        float disc=(float) (amt*(0.11));
        orddiscount.setText("₹ "+String.valueOf(disc));
        float realprice=(float)(amt-29);
        ordsellprice.setText("₹ "+String.valueOf(realprice));
        float shipcrg=(float) 58;
        ordshipping.setText("₹ "+String.valueOf(shipcrg));
        float gt=(float)(amt-disc+shipcrg);
        float grantamout=gt;
        ordgranttoal.setText("₹ "+String.valueOf(gt));

    }
}