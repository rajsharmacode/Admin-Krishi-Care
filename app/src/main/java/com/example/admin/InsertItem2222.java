package com.example.admin;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class InsertItem2222 extends AppCompatActivity {


    ImageButton iteminsertimg;
    Button iteminsertbtn;
    EditText iteminsertname,iteminsertprice,iteminsertdesc,iteminsertqnt;




    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference reference;
    FirebaseUser user;
    String uid;
    ActivityResultLauncher<String> launcher;
    Uri temp;
    ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_item2222);

        iteminsertimg=findViewById(R.id.iteminsertimg);
        iteminsertname=findViewById(R.id.iteminsertname);
        iteminsertprice=findViewById(R.id.iteminsertprice);
        iteminsertdesc=findViewById(R.id.iteminsertdesc);
        iteminsertqnt=findViewById(R.id.iteminsertqnt);
        iteminsertbtn=findViewById(R.id.iteminsertbtn);
        progressBar2=findViewById(R.id.progressBar2);


        String categorykey=getIntent().getStringExtra("categorukey");
        String categoryname=getIntent().getStringExtra("categoryname");

        progressBar2.setVisibility(View.GONE);

        database= FirebaseDatabase.getInstance();
        storage= FirebaseStorage.getInstance();
        String datakey=FirebaseDatabase.getInstance().getReference().child("AskCommunity").push().getKey();
        launcher=registerForActivityResult(new ActivityResultContracts.GetContent()
                , new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {

                        if (result != null) {
                            iteminsertimg.setImageURI(result);

                            progressBar2.setVisibility(View.VISIBLE);
//                        progressBar.setVisibility(View.VISIBLE);
                            final StorageReference reference = storage.getReference()
                                    .child("Insertitem/"+datakey);

                            reference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            temp = uri;
                                        progressBar2.setVisibility(View.GONE);

//                                        database.getReference().child("Profileinfo").child(uid).child("proimage")
//                                                .setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
//                                                    @Override
//                                                    public void onSuccess(Void unused) {
//                                                        Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
//                                                    }
//                                                });
                                        }
                                    });
                                }
                            });
                        }
                    }
                });
        iteminsertimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcher.launch("image/*");
            }
        });


        iteminsertbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    long timestamp = System.currentTimeMillis();

                    //get current user uid, since user is registered so we can get now

//                    String uidd=user.getUid();

                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Shopitemdata");

                    String userkey2=ref.push().getKey();
                    if(categorykey==null)
                    {
                        Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();
                    }else {

                        if(temp==null)
                        {
                            Toast.makeText(getApplicationContext(), "Fill all dataor wait", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("itemdisc",iteminsertdesc.getText().toString());
                            hashMap.put("itemimg",temp.toString());
                            hashMap.put("itemprice",iteminsertprice.getText().toString());
                            hashMap.put("itemname",iteminsertname.getText().toString());
                            hashMap.put("itemqnt",iteminsertqnt.getText().toString());
                            hashMap.put("itemid",userkey2);
                            hashMap.put("catkey",categorykey);
//                    hashMap.put("userkey", uidd);


                            //set data to db
//                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Profileinfo");
                            ref.child(categorykey)
                                    .child(userkey2)
                                    .setValue(hashMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getApplicationContext(), "Product inserted", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }
                                    });
                        }
                        //setup data to add in db
//                    progressBar.setVisibility(View.VISIBLE);



//                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void unused) {
//                                    progressBar.setVisibility(View.GONE);
//                                    //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
//                                    Toast.makeText(ProfilePage.this, "Profile inserted", Toast.LENGTH_SHORT).show();
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(Exception e) {
//                                    Toast.makeText(ProfilePage.this, e.getMessage(), Toast.LENGTH_SHORT).show();
//                                }
//                            });
                    }
                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"rraj"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}