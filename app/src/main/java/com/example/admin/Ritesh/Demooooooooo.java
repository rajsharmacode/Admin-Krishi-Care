package com.example.admin.Ritesh;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import com.example.admin.R;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class Demooooooooo extends AppCompatActivity {


    Button btn_AddProduct,btn_ShowProduct;
    FirebaseDatabase database;
    FirebaseStorage storage;
    ImageButton btn_ImageUrl;
    Uri u;
    ProgressBar progressBar;
    ActivityResultLauncher launcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demooooooooo);
    }
}