package com.example.admin.Ritesh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.MainActivity3333;
import com.example.admin.R;

public class MainActivity extends AppCompatActivity {

    EditText edt_email,edt_password;
    Button btn_admin_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_email = findViewById(R.id.edt_email);
        edt_password = findViewById(R.id.edt_password);
        btn_admin_login = findViewById(R.id.btn_admin_login);

        btn_admin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt_email.getText().toString().trim().isEmpty() || edt_password.getText().toString().trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "All fields are required!", Toast.LENGTH_SHORT).show();

                }else {
                    if (Patterns.EMAIL_ADDRESS.matcher(edt_email.getText().toString().trim()).matches()){

                        if (edt_email.getText().toString().trim().equals("admin@gmail.com") && edt_password.getText().toString().trim().equals("admin@123")) {
                            startActivity(new Intent(getApplicationContext(), MainActivity3333.class));
                            finish();
                        }else {
                            Toast.makeText(MainActivity.this, "Invalid Admin...!", Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(MainActivity.this, "Invalid email...!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}



