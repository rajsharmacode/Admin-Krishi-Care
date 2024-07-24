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

public class UpdateCategory extends AppCompatActivity {

    ImageButton catupdateimg;
    EditText catupdatecatname;
    Button catupdateaddbtn;


    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference reference;
    FirebaseUser user;
    String uid;
    ActivityResultLauncher<String> launcher;
    Uri temp;

    ProgressBar progressBar5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_category);


        catupdateimg=findViewById(R.id.catupdateimg);
        catupdateaddbtn=findViewById(R.id.catupdateaddbtn);
        catupdatecatname=findViewById(R.id.catupdatecatname);
        progressBar5=findViewById(R.id.progressBar5);

        progressBar5.setVisibility(View.GONE);

        String prename=getIntent().getStringExtra("catname");
        String preimg=getIntent().getStringExtra("catimage");
        String catkey=getIntent().getStringExtra("userkey");

        catupdatecatname.setText(prename);
        Glide.with(getApplicationContext()).load(preimg).into(catupdateimg);
        database= FirebaseDatabase.getInstance();
        storage= FirebaseStorage.getInstance();
        String datakey=FirebaseDatabase.getInstance().getReference().child("AskCommunity").push().getKey();

        launcher=registerForActivityResult(new ActivityResultContracts.GetContent()
                , new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {

                        if (result != null) {
                            catupdateimg.setImageURI(result);
                            progressBar5.setVisibility(View.VISIBLE);

//                        progressBar.setVisibility(View.VISIBLE);
                            final StorageReference reference = storage.getReference()
                                    .child("UpdateCategory/"+datakey);

                            reference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            temp = uri;

                                        progressBar5.setVisibility(View.GONE);

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
        catupdateimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcher.launch("image/*");
            }
        });


        catupdateaddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    long timestamp = System.currentTimeMillis();

                    //get current user uid, since user is registered so we can get now

//                    String uidd=user.getUid();
                    //setup data to add in db
//                    progressBar.setVisibility(View.VISIBLE);

                    String categoryyname=prename;
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
                        hashMap.put("catname", catupdatecatname.getText().toString());
                        hashMap.put("catimage", preimg);
//                    hashMap.put("userkey", uidd);


                        //set data to db
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("ShopCategory");
                        ref.child(catkey)
                                .updateChildren(hashMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(getApplicationContext(), "Updated1", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                    }else
                    {
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("catname", catupdatecatname.getText().toString());
                        hashMap.put("catimage", temp.toString());
//                    hashMap.put("userkey", uidd);


                        //set data to db
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("ShopCategory");
                        ref.child(catkey)
                                .updateChildren(hashMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(getApplicationContext(), "Updated2", Toast.LENGTH_SHORT).show();
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