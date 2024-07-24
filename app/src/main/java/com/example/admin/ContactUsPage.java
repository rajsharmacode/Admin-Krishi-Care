package com.example.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class ContactUsPage extends AppCompatActivity {


    EditText adminaddress,adminemail,adminmobile,adminwhatapp;
    Button adminaddcontactbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us_page);

        adminaddcontactbtn=findViewById(R.id.adminaddcontactbtn);
        adminaddress=findViewById(R.id.adminaddress);
        adminemail=findViewById(R.id.adminemail);
        adminwhatapp=findViewById(R.id.adminwhatapp);
        adminmobile=findViewById(R.id.adminmobile);






        adminaddcontactbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(adminaddress.getText().toString().isEmpty())
                {
                    adminaddress.setError("Fill Detial");
                }
                else if(adminemail.getText().toString().isEmpty())
                {
                    adminemail.setError("Fill Detial");
                }
                else if(adminwhatapp.getText().toString().isEmpty())
                {
                    adminwhatapp.setError("Fill Detial");
                }
                else if(adminmobile.getText().toString().isEmpty())
                {
                    adminmobile.setError("Fill Detial");
                }
                else
                {
                    try {
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("address",adminaddress.getText().toString());
                        hashMap.put("email",adminemail.getText().toString());
                        hashMap.put("mobile",adminmobile.getText().toString());
                        hashMap.put("whatsapp",adminwhatapp.getText().toString());

                        //set data to db
                        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("AdminContactUs");
                        ref.child("admincontactusid")
                                .setValue(hashMap)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        //progressBar.setVisibility(View.GONE);
                                        //Glide.with(getApplicationContext()).load(temp.toString()).into(profileimage);
                                        Toast.makeText(getApplicationContext(), "Contact Updated", Toast.LENGTH_SHORT).show();
                                        finish();
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

                }

            }
        });



    }
}