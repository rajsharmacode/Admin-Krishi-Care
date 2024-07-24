package com.example.admin;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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

public class UpdateItem extends AppCompatActivity {

    ImageButton iteminsertimg;
    Button iteminsertbtn;
    EditText iteminsertname,iteminsertprice,iteminsertdesc,iteminsertqnt;



    String itemname,itemdisc,itemimg,itemqnt,itemprice,itemid,catkey;

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
        setContentView(R.layout.activity_update_item);


        iteminsertimg=findViewById(R.id.iteminsertimg);
        iteminsertname=findViewById(R.id.iteminsertname);
        iteminsertprice=findViewById(R.id.iteminsertprice);
        iteminsertdesc=findViewById(R.id.iteminsertdesc);
        iteminsertqnt=findViewById(R.id.iteminsertqnt);
        iteminsertbtn=findViewById(R.id.iteminsertbtn);
        progressBar2=findViewById(R.id.progressBar2);



        progressBar2.setVisibility(View.GONE);
        itemdisc=getIntent().getStringExtra("itemdisc");
        itemimg=getIntent().getStringExtra("itemimg");
        itemprice=getIntent().getStringExtra("itemprice");
        itemname=getIntent().getStringExtra("itemname");
        itemqnt=getIntent().getStringExtra("itemqnt");
        catkey=getIntent().getStringExtra("catkey");
        itemid=getIntent().getStringExtra("itemid");


        iteminsertname.setText(itemname);
        iteminsertprice.setText(itemprice);
        iteminsertdesc.setText(itemdisc);
        iteminsertqnt.setText(itemqnt);
        Glide.with(getApplicationContext()).load(itemimg).into(iteminsertimg);


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
                                    .child("updateitem/"+datakey);

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

                    //get current user uid, since user is registered so we can get now

//                    String uidd=user.getUid();
                    //setup data to add in db
//                    progressBar.setVisibility(View.VISIBLE);

                    if(temp==null)
                    {
//                        HashMap<String, Object> hashMap = new HashMap<>();
//                        hashMap.put("proname", name.getText().toString());
//                        hashMap.put("pronumber", number.getText().toString());
//                        hashMap.put("proimage", temp.toString());
////                    hashMap.put("userkey", uidd);
//
//
//                        //set data to db
//                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Profileinfo");
//                        ref.child(userkey)
//                                .updateChildren(hashMap)
//                                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        Toast.makeText(getApplicationContext(), "Profile inserted", Toast.LENGTH_SHORT).show();
//
//                                    }
//                                });
//                        Toast.makeText(AddUserdataPage.this, "Add Profile img", Toast.LENGTH_SHORT).show();
//                    }else
//                    {
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("itemdisc",iteminsertdesc.getText().toString());
                        hashMap.put("itemimg",itemimg);
                        hashMap.put("itemprice",iteminsertprice.getText().toString());
                        hashMap.put("itemname",iteminsertname.getText().toString());
                        hashMap.put("itemqnt",iteminsertqnt.getText().toString());
//                        hashMap.put("itemid",itemid);
//                    hashMap.put("userkey", uidd);


                        //set data to db
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Shopitemdata");
                        ref.child(catkey).child(itemid)
                                .updateChildren(hashMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(getApplicationContext(), "Product updeted", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                    }else
                    {
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("itemdisc",iteminsertdesc.getText().toString());
                        hashMap.put("itemimg",temp.toString());
                        hashMap.put("itemprice",iteminsertprice.getText().toString());
                        hashMap.put("itemname",iteminsertname.getText().toString());
                        hashMap.put("itemqnt",iteminsertqnt.getText().toString());
//                    hashMap.put("userkey", uidd);


                        //set data to db
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Shopitemdata");
                        ref.child(catkey)
                                .child(itemid)
                                .updateChildren(hashMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(getApplicationContext(), "Profile inserted", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                    }




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
                }catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"rraj"+ e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
}