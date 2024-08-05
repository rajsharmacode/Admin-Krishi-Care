package com.example.admin.NotUsed;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.admin.R;

public class MessageActivity extends AppCompatActivity {


    ImageButton btn_comment_back;
    ImageButton img_msg_button;
    EditText editText_message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        btn_comment_back = findViewById(R.id.btn_comment_back);
        btn_comment_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getApplicationContext(),AdminCommunityActivity.class));
            }
        });

        img_msg_button = findViewById(R.id.img_msg_button);
        editText_message = findViewById(R.id.editText_message);



        img_msg_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*----------------------------------------------------------------------------------*/
            }
        });



    }
}