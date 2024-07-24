package com.example.admin;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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

import java.util.HashMap;

public class Fullcultivation extends AppCompatActivity {

    TextView culresdisc,culrestitle;
    Button culresdelete,culresupdate;
    ImageView culresimg;









    String itemname,itemdisc,itemimg,itemqnt,itemprice,itemid,catkey;

    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference reference;
    FirebaseUser user;
    String uid;
    ActivityResultLauncher<String> launcher;
    Uri temp;
    String caldisc,calimg,culname;
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullcultivation);

        culresimg=findViewById(R.id.culresimg);
        culrestitle=findViewById(R.id.culrestitle);
        culresdisc=findViewById(R.id.culresdisc);
        culresdelete=findViewById(R.id.culresdelete);
        culresupdate=findViewById(R.id.culresupdate);

        String Readdata=getIntent().getStringExtra("readdata");
        String Readimg=getIntent().getStringExtra("readimg");
        String Readname=getIntent().getStringExtra("readname");
        String Readkey=getIntent().getStringExtra("calkey");



        if(Readkey==null)
        {
            Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();
        }

        
        reference=FirebaseDatabase.getInstance().getReference();
        reference.child("CaltivationTips").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                 caldisc = dataSnapshot.child(Readkey).child("caltdisc").getValue(String.class);
               calimg = dataSnapshot.child(Readkey).child("caltimg").getValue(String.class);
                culname = dataSnapshot.child(Readkey).child("caltname").getValue(String.class);
//                proimage1 = dataSnapshot.child(uid).child("proimage").getValue(String.class);
//                String prostate1 = dataSnapshot.child(uid).child("prostate").getValue(String.class);

                Glide.with(getApplicationContext()).load(calimg).into(culresimg);
                culresdisc.setText("            " + caldisc);
                culrestitle.setText(culname);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        reference.child("CaltivationTips").child(Readkey).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


//                proname.setText(proname1);
//                pronumber.setText(pronumber1);
//                proemail.setText(proemail1);
//                int defaultIndex1 = adapter.getPosition(prostate1);
//                countrySpinner.setSelection(defaultIndex1);
//                Glide.with(getApplicationContext()).load(proimage1).into(profileimage);//
//            }
//        }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//        reference.child("CaltivationTips").child(Readkey).addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


//                        progressBar.setVisibility(View.GONE);
//                        Toast.makeText(ProfilePage.this, "profilechlagya", Toast.LENGTH_SHORT).show();

//                progressBar.setVisibility(View.GONE);
//                if(dataSnapshot.exists())
//                {
//                        Map map=(Map) dataSnapshot.getValue();
//                        txt2.setText(map.toString());
//                    txt2.setText(dataSnapshot.child("rar").getKey().toString());
//                    System.out.println(dataSnapshot.child("rar").getKey());

                //    txt11.setText(map.toString());


//                    if(dataSnapshot.getKey().matches("rar"))
//                    {
//                        txt2.setText("raj"+map.toString());
//                        proname.setText(map.get("proname").toString());
//                        pronumber.setText(map.get("pronumber").toString());
//                        proemail.setText(map.get("proemail").toString());
//                        countrySpinner.setSelection(5);
//                        Toast.makeText(ProfilePage.this, map.get("proname").toString(), Toast.LENGTH_SHORT).show();
//                    }
//                }
//                String no=getIntent().getStringExtra("n3").toString();
                //  String phonenumber=getIntent().getStringExtra("mobile").toString();



//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });




//        database= FirebaseDatabase.getInstance();
//        storage= FirebaseStorage.getInstance();
//        launcher=registerForActivityResult(new ActivityResultContracts.GetContent()
//                , new ActivityResultCallback<Uri>() {
//                    @Override
//                    public void onActivityResult(Uri result) {
//
//                        if (result != null) {
//                            culresimg.setImageURI(result);
//
////                        progressBar.setVisibility(View.VISIBLE);
//                            final StorageReference reference = storage.getReference()
//                                    .child("ProfileImage1");
//
//                            reference.putFile(result).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                                @Override
//                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                        @Override
//                                        public void onSuccess(Uri uri) {
//                                            temp = uri;
////                                        progressBar.setVisibility(View.GONE);
//
////                                        database.getReference().child("Profileinfo").child(uid).child("proimage")
////                                                .setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
////                                                    @Override
////                                                    public void onSuccess(Void unused) {
////                                                        Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
////                                                    }
////                                                });
//                                        }
//                                    });
//                                }
//                            });
//                        }
//                    }
//                });
//        culresimg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                launcher.launch("image/*");
//            }
//        });
        culresupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i1=new Intent(getApplicationContext(), UpdateCaltivation.class);
                i1.putExtra("readdata",caldisc);
                i1.putExtra("readimg",calimg);
                i1.putExtra("readname",culname);
                i1.putExtra("calkey",Readkey);
                i1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i1);

            }
        });

        builder = new AlertDialog.Builder(this);
        culresdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                builder.setTitle("Do You Want to Delete this Cultivation Tips");
                builder.setMessage("Delete...?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        FirebaseDatabase.getInstance().getReference().child("raj")
//                                .child(getRef(position).getKey()).removeValue();

                        if(Readkey!=null)
                        {
                            FirebaseDatabase.getInstance().getReference().child("CaltivationTips").child(Readkey).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
//                                    Intent i=new Intent(getApplicationContext(),CultivationPage.class);
//                                    startActivity(i);
                                    Toast.makeText(getApplicationContext(), "Delete Successfully", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            });
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Wait", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(getApplicationContext(), Readkey, Toast.LENGTH_SHORT).show();
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();







//                AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
//                builder.setTitle("Delete ?")
//                        .setMessage("Do You Want to Delete this User")
//                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
////                                String userkey=model.getItemid();
////                                String catkey=model.getCatkey();
//                                if(Readkey!=null)
//                                {
//                                    FirebaseDatabase.getInstance().getReference().child("CaltivationTips").child(Readkey).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
//                                        @Override
//                                        public void onSuccess(Void unused) {
//                                            Toast.makeText(getApplicationContext(), "Product Deleted11", Toast.LENGTH_SHORT).show();
//                                        }
//                                    });
//                                }
//                                else
//                                {
//                                    Toast.makeText(getApplicationContext(), "Wait", Toast.LENGTH_SHORT).show();
//                                }
//                                Toast.makeText(getApplicationContext(), Readkey, Toast.LENGTH_SHORT).show();
//                            }
//                        })
//                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                // Negative button clicked
//                            }
//                        })
//                        .show();

            }
        });
    }
}