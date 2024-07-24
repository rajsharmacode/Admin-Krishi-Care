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

public class UpdateCaltivation extends AppCompatActivity {



    Button cultiupdsubmit;
    ImageButton cultiupdimg;
    EditText cultiupddisc,cultiupdtitle;



    String itemname,itemdisc,itemimg,itemqnt,itemprice,itemid,catkey;

    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference reference;
    FirebaseUser user;
    String uid;
    ActivityResultLauncher<String> launcher;
    Uri temp;

    ProgressBar progressBar4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_caltivation);


        cultiupdimg=findViewById(R.id.cultiupdimg);
        cultiupddisc=findViewById(R.id.cultiupddisc);
        cultiupdsubmit=findViewById(R.id.cultiupdsubmit);
        cultiupdtitle=findViewById(R.id.cultiupdtitle);
        progressBar4=findViewById(R.id.progressBar4);




        progressBar4.setVisibility(View.GONE);
        String Readdata=getIntent().getStringExtra("readdata");
        String Readimg=getIntent().getStringExtra("readimg");
        String Readname=getIntent().getStringExtra("readname");
        String Readkey=getIntent().getStringExtra("calkey");

        Glide.with(getApplicationContext()).load(Readimg).into(cultiupdimg);
        cultiupddisc.setText("            "+Readdata);
        cultiupdtitle.setText(Readname);
        database= FirebaseDatabase.getInstance();
        storage= FirebaseStorage.getInstance();
        String datakey=FirebaseDatabase.getInstance().getReference().child("AskCommunity").push().getKey();

        launcher=registerForActivityResult(new ActivityResultContracts.GetContent()
                , new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {

                        if (result != null) {
                            cultiupdimg.setImageURI(result);

                            progressBar4.setVisibility(View.VISIBLE);
//                        progressBar.setVisibility(View.VISIBLE);
                            final StorageReference reference = storage.getReference()
                                    .child("Updatecultivation/"+datakey);

                            reference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            temp = uri;
                                            progressBar4.setVisibility(View.GONE);
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
        cultiupdimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcher.launch("image/*");
            }
        });

        cultiupdsubmit.setOnClickListener(new View.OnClickListener() {
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
                        hashMap.put("caltdisc",cultiupddisc.getText().toString());
                        hashMap.put("caltimg",Readimg);
                        hashMap.put("caltname",cultiupdtitle.getText().toString());
//                        hashMap.put("itemid",itemid);
//                    hashMap.put("userkey", uidd);


                        //set data to db
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("CaltivationTips");
                        ref.child(Readkey)
                                .updateChildren(hashMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(getApplicationContext(), "Updeted Successfully", Toast.LENGTH_SHORT).show();
                                        finish();
                                    }
                                });
                    }else
                    {
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("caltdisc",cultiupddisc.getText().toString());
                        hashMap.put("caltimg",temp.toString());
                        hashMap.put("caltname",cultiupdtitle.getText().toString());
//                    hashMap.put("userkey", uidd);


                        //set data to db
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("CaltivationTips");
                        ref.child(Readkey)
                                .updateChildren(hashMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(getApplicationContext(), "Updeted Successfully", Toast.LENGTH_SHORT).show();
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