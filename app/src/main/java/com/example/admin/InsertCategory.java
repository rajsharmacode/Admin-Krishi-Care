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

public class InsertCategory extends AppCompatActivity {



    ImageButton catinsertimg;
    EditText catinsertcatname;
    Button catinsertaddbtn;


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
        setContentView(R.layout.activity_insert_category);

        catinsertimg=findViewById(R.id.catinsertimg);
        catinsertaddbtn=findViewById(R.id.catinsertaddbtn);
        catinsertcatname=findViewById(R.id.catinsertcatname);
        progressBar5=findViewById(R.id.progressBar5);

        progressBar5.setVisibility(View.GONE);


        database= FirebaseDatabase.getInstance();
        storage= FirebaseStorage.getInstance();
        String datakey=FirebaseDatabase.getInstance().getReference().child("AskCommunity").push().getKey();
        launcher=registerForActivityResult(new ActivityResultContracts.GetContent()
                , new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {

                        if (result != null) {
                            catinsertimg.setImageURI(result);
                            progressBar5.setVisibility(View.VISIBLE);
//                        progressBar.setVisibility(View.VISIBLE);
                            final StorageReference reference = storage.getReference()
                                    .child("InsertCategory/"+datakey);

                            reference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            temp = uri;
                                            progressBar5.setVisibility(View.GONE);
//                                        progressBar.setVisibility(View.GONE);

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
        catinsertimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcher.launch("image/*");
            }
        });


        catinsertaddbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    long timestamp = System.currentTimeMillis();

                    //get current user uid, since user is registered so we can get now

//                    String uidd=user.getUid();

                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("ShopCategory");

                    String userkey2=ref.push().getKey();
                    String categoryname=catinsertcatname.getText().toString();
                    if(userkey2==null)
                    {
                        Toast.makeText(InsertCategory.this, "null", Toast.LENGTH_SHORT).show();
                    }else {

                        if(temp==null)
                        {
                            Toast.makeText(InsertCategory.this, "Fill all dataor wait", Toast.LENGTH_SHORT).show();
                        }else
                        {
                            HashMap<String, Object> hashMap = new HashMap<>();
                            hashMap.put("catimage", temp.toString());
                            hashMap.put("catname", categoryname);
                            hashMap.put("userkey", userkey2);
//                            hashMap.put("proimage", temp.toString());
//                            hashMap.put("proemail", "");
//                            hashMap.put("prostate", "");
//                            hashMap.put("userkey", userkey2);
//                    hashMap.put("userkey", uidd);


                            //set data to db
//                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Profileinfo");
                            ref.child(userkey2)
                                    .setValue(hashMap)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            Toast.makeText(getApplicationContext(), "Category inserted", Toast.LENGTH_SHORT).show();
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