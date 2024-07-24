package com.example.admin;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BannerAdePage extends AppCompatActivity {
    Spinner spinner;

    ArrayAdapter<String> adapter;


    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference reference;
    FirebaseUser user;
    String uid;
    ActivityResultLauncher<String> launcher;
    Uri temp;

    ImageView pickbannerimage;
    ImageView userbackbtn;
    Button changebannerbtn;
    ProgressBar progressBar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_ade_page);


        ImageSlider imageSlider=findViewById(R.id.imageSlider);
         spinner=findViewById(R.id.spinner);
        pickbannerimage=findViewById(R.id.pickbannerimage);
        changebannerbtn=findViewById(R.id.changebannerbtn);
        userbackbtn=findViewById(R.id.userbackbtn);
        progressBar2=findViewById(R.id.progressBar2);

        userbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        final List<SlideModel> model=new ArrayList<>();
        model.clear();
        FirebaseDatabase.getInstance().getReference().child("BannerSlider").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                model.clear();
                for(DataSnapshot data: dataSnapshot.getChildren()) {
                    model.add(new SlideModel(data.child("url").getValue().toString(), ScaleTypes.FIT));
//                    model.clear();
                }
                imageSlider.setImageList(model,ScaleTypes.FIT);
                model.clear();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



            String[] countryCodes = {"slide1", "slide2", "slide3", "slide4"};

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryCodes);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(adapter);
            int defaultIndex = adapter.getPosition("Slide1");
            spinner.setSelection(defaultIndex);




        database= FirebaseDatabase.getInstance();
        storage= FirebaseStorage.getInstance();
        String datakey=FirebaseDatabase.getInstance().getReference().child("AskCommunity").push().getKey();

        launcher=registerForActivityResult(new ActivityResultContracts.GetContent()
                , new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {

                        if (result != null) {
                            progressBar2.setVisibility(View.VISIBLE);
                            pickbannerimage.setImageURI(result);
//                        progressBar.setVisibility(View.VISIBLE);
                            final StorageReference reference = storage.getReference()
                                    .child("BannerIamge/"+datakey);

                            reference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri uri) {
                                            temp = uri;
                                            progressBar2.setVisibility(View.GONE);
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
        pickbannerimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcher.launch("image/*");
            }
        });

        final List<SlideModel> model1=new ArrayList<>();
        FirebaseDatabase.getInstance().getReference().child("BannerSlider").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot data: dataSnapshot.getChildren())
                    model1.add(new SlideModel(data.child("url").getValue().toString(),ScaleTypes.FIT));

                imageSlider.setImageList(model1,ScaleTypes.FIT);

                imageSlider.setItemClickListener(new ItemClickListener() {
                    @Override
                    public void onItemSelected(int i) {

                    }
                    @Override
                    public void doubleClick(int i) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        changebannerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    long timestamp = System.currentTimeMillis();

                    //get current user uid, since user is registered so we can get now

//                    String uidd=user.getUid();
                    //setup data to add in db
//                    progressBar.setVisibility(View.VISIBLE);

//                    String categoryyname=prename;
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
//                        HashMap<String, Object> hashMap = new HashMap<>();
//                        hashMap.put("url", .getText().toString());
//                        hashMap.put("title", preimg);
////                    hashMap.put("userkey", uidd);
//
//
//                        //set data to db
//                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("ShopCategory");
//                        ref.child(catkey)
//                                .updateChildren(hashMap)
//                                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<Void> task) {
//                                        Toast.makeText(getApplicationContext(), "Updated1", Toast.LENGTH_SHORT).show();
//                                        finish();
//                                    }
//                                });
//                        Toast.makeText(BannerAdePage.this, "", Toast.LENGTH_SHORT).show();
                    }else
                    {
                        String str=spinner.getSelectedItem().toString();
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("url", temp.toString());
//                        hashMap.put("title", spinner.getSelectedItem().toString());
//                    hashMap.put("userkey", uidd);


                        //set data to db
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("BannerSlider");
                        ref.child(str)//spinner.getSelectedItem().toString()
                                .updateChildren(hashMap)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        Toast.makeText(getApplicationContext(), "Banner Changed Successfully", Toast.LENGTH_SHORT).show();
//                                        finish();
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