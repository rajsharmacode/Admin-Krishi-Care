package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;

public class UserAddinFirebaseNumber extends AppCompatActivity {


    PhoneAuthProvider phoneAuthProvider;
    EditText edt_input_mobile_no;
    Button btn_getOTP;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_addin_firebase_number);




        edt_input_mobile_no = findViewById(R.id.enter_mobile_number);
        btn_getOTP = findViewById(R.id.btn_GetOtp);

        final ProgressBar progressBar = findViewById(R.id.progressbar_sending_otp);

        btn_getOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
                reference.child("numberkeliye").setValue(edt_input_mobile_no.getText().toString());

                if (!edt_input_mobile_no.getText().toString().trim().isEmpty()){
                    if((edt_input_mobile_no.getText().toString().trim()).length()==10){

                        progressBar.setVisibility(View.VISIBLE);
                        btn_getOTP.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                                "+91" + edt_input_mobile_no.getText().toString(),
                                60, TimeUnit.SECONDS,
                                UserAddinFirebaseNumber.this,
                                new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                                    @Override
                                    public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                        progressBar.setVisibility(View.GONE);
                                        btn_getOTP.setVisibility(View.VISIBLE);
                                    }

                                    @Override
                                    public void onVerificationFailed(@NonNull FirebaseException e) {
                                        progressBar.setVisibility(View.GONE);
                                        btn_getOTP.setVisibility(View.VISIBLE);
                                        Toast.makeText(UserAddinFirebaseNumber.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onCodeSent(@NonNull String backendOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                        progressBar.setVisibility(View.GONE);
                                        btn_getOTP.setVisibility(View.VISIBLE);

                                        Intent i = new Intent(getApplicationContext(), UserAddinFirebaseOtp.class);
                                        i.putExtra("mobile",edt_input_mobile_no.getText().toString());
                                        i.putExtra("backend_OTP",backendOTP);
                                        startActivity(i);
                                    }
                                }
                        );
                       /*Intent i = new Intent(getApplicationContext(),OtpVerificationActivity.class);
                       i.putExtra("mobile",edt_input_mobile_no.getText().toString());
                       startActivity(i);*/
                    }else {
                        Toast.makeText(UserAddinFirebaseNumber.this, "Please enter correct mobile number", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(UserAddinFirebaseNumber.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}