package com.example.admin.NotUsed;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.admin.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AddCommunityActivity extends AppCompatActivity {

    EditText edt_addUpdate;
    ImageView btnBack;
    ImageButton img_choose_btn;
    Button btn_comm_post;
    Uri u;
    ProgressBar progressBar;
    ActivityResultLauncher launcher;
    FirebaseDatabase database;
    FirebaseStorage storage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_community);

        edt_addUpdate = findViewById(R.id.edt_addUpdate);
        btnBack = findViewById(R.id.btnBack);
        img_choose_btn = findViewById(R.id.img_choose_btn);
        btn_comm_post = findViewById(R.id.btn_comm_post);
        progressBar = findViewById(R.id.P_bar);

        database = FirebaseDatabase.getInstance();
        storage = FirebaseStorage.getInstance();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
            }
        });

        launcher = registerForActivityResult(new ActivityResultContracts.GetContent()
                ,new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        img_choose_btn.setImageURI(uri);
                        u=uri;



                    }
                });

        img_choose_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launcher.launch("image/*");
                progressBar.setVisibility(View.VISIBLE);
                img_choose_btn.setImageURI(u);
            }
        });

        btn_comm_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(AddCommunityActivity.this, "mic testing", Toast.LENGTH_SHORT).show();
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                Date date = new Date();
                String d = dateFormat.format(date).toString();


                StorageReference reference = storage.getReference().child("Community_Image");
                reference.putFile(u).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                Map<String,Object>map = new HashMap<>();
                                map.put("Content",edt_addUpdate.getText().toString());
                                map.put("ImgURL",uri.toString());
                                map.put("TimeDate",d);
                                database.getReference().child("Community").push().setValue(map)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                progressBar.setVisibility(View.GONE);
                                                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

                                            }
                                        });
                            }
                        });
                    }
                });
            }
        });
    }
}


