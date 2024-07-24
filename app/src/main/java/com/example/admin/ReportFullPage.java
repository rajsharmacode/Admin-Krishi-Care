package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.admin.RajAdapters.CommentAdapters;
import com.example.admin.RajModel.CommentModel;
import com.example.admin.RajModel.ReportPostModel;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ReportFullPage extends AppCompatActivity {

    String ques,disc,image;

    TextView ques1,disc1;
    ImageView coverimg;
    RecyclerView recview;
    ArrayList<CommentModel> list;
    DatabaseReference reference;
    CommentAdapters adpt;
    EditText data1;
    ImageButton sendbtn;
    FirebaseUser user;

//    String uid;



    ImageView personimg11;
    TextView personamae11;
    ImageButton cometback;
    CheckBox cometlike;
    TextView cometcounter,cometcountercomment;
    ImageButton cometcomment,commetmenuitem;
    int position;
    MenuItem menuItem1;
    ImageView cometeditbtn;
    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_full_page);




        ques=getIntent().getStringExtra("question");
        disc=getIntent().getStringExtra("disc");
        image=getIntent().getStringExtra("image");
        String postid=getIntent().getStringExtra("postid");
        String personid=getIntent().getStringExtra("uid");
//        String personimg=getIntent().getStringExtra("personimg");
//        String personname=getIntent().getStringExtra("personname");

        ques1=findViewById(R.id.cometquestion);
        disc1=findViewById(R.id.cometdisc);
        coverimg=findViewById(R.id.comentimage);
        recview=findViewById(R.id.recyclerView4);
        sendbtn=findViewById(R.id.sendbtn);
        data1=findViewById(R.id.sendedittext);
        personamae11=findViewById(R.id.cometpersonname);
        personimg11=findViewById(R.id.cometpersonimage1);
        cometback=findViewById(R.id.cometback);
        cometlike=findViewById(R.id.cometlike);
        cometcounter=findViewById(R.id.cometcounter);
        cometcomment=findViewById(R.id.cometcomment);
        cometcountercomment=findViewById(R.id.cometcountercomment);
//        commetmenuitem=findViewById(R.id.commetmenuitem);
//        cometeditbtn=findViewById(R.id.cometeditbtn);

//        cometeditbtn.setVisibility(View.GONE);
//        user= FirebaseAuth.getInstance().getCurrentUser();
//        uid=user.getUid();


//        if(personid.equals(uid))
//        {
//            cometeditbtn.setVisibility(View.VISIBLE);
//            cometeditbtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//
//
//                    Intent i=new Intent(getApplicationContext(),UpdateCommPost.class);
//                    i.putExtra("question",ques);
//                    i.putExtra("disc",disc);
//                    i.putExtra("image",image);
//                    i.putExtra("postid",postid);
//                    i.putExtra("uid",personid);
//                    startActivity(i);
//
//                }
//            });
//        }





        cometlike.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(cometlike.isChecked())
                {
                    cometcounter.setText("1");

                }
                else
                {
                    cometcounter.setText("0");

                }
            }
        });
        cometback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        ques1.setText(ques);
        disc1.setText(disc);
        Glide.with(getApplicationContext()).load(image).into(coverimg);
//        personamae11.setText(personname);

        FirebaseDatabase.getInstance().getReference().child("Profileinfo").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String proname1=dataSnapshot.child(personid).child("proname").getValue(String.class);
                String pronumber1=dataSnapshot.child(personid).child("pronumber").getValue(String.class);
                String proemail1=dataSnapshot.child(personid).child("proemail").getValue(String.class);
                String proimage1=dataSnapshot.child(personid).child("proimage").getValue(String.class);
                String prostate1=dataSnapshot.child(personid).child("prostate").getValue(String.class);


                personamae11.setText(proname1);
                if(proimage1!=null)
                {
                    Glide.with(getApplicationContext()).load(proimage1).into(personimg11);//


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


//        Glide.with(getApplicationContext()).load(personimg).into(personimg11);//


//        Toast.makeText(getApplicationContext(), ques+disc+image, Toast.LENGTH_SHORT).show();

        list=new ArrayList<>();

        reference= FirebaseDatabase.getInstance().getReference();

        recview.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recview.setNestedScrollingEnabled(false);
        adpt=new CommentAdapters(list,getApplicationContext());
        recview.setAdapter(adpt);
        if(postid==null)
        {

        }else {
            reference.child("CommentSend").child(postid).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    count=0;
                    list.clear();
                    for(DataSnapshot snapshot: dataSnapshot.getChildren())
                    {

                        CommentModel data=snapshot.getValue(CommentModel.class);
                        list.add(data);
                        count++;
                        cometcountercomment.setText(String.valueOf(count));
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
        }


        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data= data1.getText().toString();

                try {

                    if(data.isEmpty())
                    {

                    }else
                    {
                        long timestamp = System.currentTimeMillis();

                        //get current user uid, since user is registered so we can get now

                        //setup data to add in db
//                    progressBar.setVisibility(View.VISIBLE);
                        String uid="Admin";
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("commentuser", data);
                        hashMap.put("personid", uid);


                        //set data to db
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("CommentSend").child(postid);
                        ref.push()
                                .setValue(hashMap)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
//                                    progressBar.setVisibility(View.GONE);
                                        //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
//                                    onBackPressed();
                                        data1.setText("");
                                        Toast.makeText(getApplicationContext(), "Comment inserted", Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(Exception e) {
                                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }



                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}