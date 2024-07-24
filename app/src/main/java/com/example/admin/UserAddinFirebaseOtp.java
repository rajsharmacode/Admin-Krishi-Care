package com.example.admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class UserAddinFirebaseOtp extends AppCompatActivity {

    EditText otp_edt1,otp_edt2,otp_edt3,otp_edt4,otp_edt5,otp_edt6;
    TextView tv_Resent_otp,tv_show_mobile_no;
    Button btn_OTP_Verify;
    String getOTPbackend;
    ImageView img_btn_back;

    FirebaseDatabase database;
    FirebaseStorage storage;
    DatabaseReference reference;
    FirebaseUser user;
    String uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_addin_firebase_otp);


        otp_edt1 = findViewById(R.id.otp_edt1);
        otp_edt2 = findViewById(R.id.otp_edt2);
        otp_edt3 = findViewById(R.id.otp_edt3);
        otp_edt4 = findViewById(R.id.otp_edt4);
        otp_edt5 = findViewById(R.id.otp_edt5);
        otp_edt6 = findViewById(R.id.otp_edt6);
        img_btn_back = findViewById(R.id.img_btn_back);
        tv_show_mobile_no = findViewById(R.id.tv_show_mobile_no);
        tv_Resent_otp = findViewById(R.id.tv_Resent_otp);
        btn_OTP_Verify = findViewById(R.id.btn_Verify);

        ProgressBar progressBar = findViewById(R.id.progressbar_verify_otp);


        String mobilenumber=getIntent().getStringExtra("mobile");
        img_btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //noinspection deprecation
                onBackPressed();
            }
        });

        //show mobile number in textview
        tv_show_mobile_no.setText(String.format("+91-%s",mobilenumber));
        getOTPbackend = getIntent().getStringExtra("backend_OTP");


        btn_OTP_Verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!otp_edt1.getText().toString().trim().isEmpty() &&
                        !otp_edt2.getText().toString().trim().isEmpty() &&
                        !otp_edt3.getText().toString().trim().isEmpty() &&
                        !otp_edt4.getText().toString().trim().isEmpty() &&
                        !otp_edt5.getText().toString().trim().isEmpty() &&
                        !otp_edt6.getText().toString().trim().isEmpty()){

                    String enterOTPcode = otp_edt1.getText().toString()+
                            otp_edt2.getText().toString()+
                            otp_edt3.getText().toString()+
                            otp_edt4.getText().toString()+
                            otp_edt5.getText().toString()+
                            otp_edt6.getText().toString();

                    if (getOTPbackend!=null){
                        progressBar.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.INVISIBLE);

                        PhoneAuthCredential phoneAuthCredential = PhoneAuthCredential.zza(
                                getOTPbackend,enterOTPcode
                        );
                        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        progressBar.setVisibility(View.GONE);
                                        progressBar.setVisibility(View.VISIBLE);

                                        if (task.isSuccessful()){
                                            Intent intent = new Intent(getApplicationContext(), UserPage.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);

                                            reference= FirebaseDatabase.getInstance().getReference();
                                            user=FirebaseAuth.getInstance().getCurrentUser();
                                            uid=user.getUid();

//                                            reference.child("Profileinfo").child(uid).addValueEventListener(new ValueEventListener() {
//                                                @Override
//                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                                                    if(dataSnapshot.exists())
//                                                    {
//
////                                                        Toast.makeText(UserAddinFirebaseOtp.this, "already register", Toast.LENGTH_SHORT).show();
//                                                    }else
//                                                    {
//
////                                                        Toast.makeText(UserAddinFirebaseOtp.this, "new register", Toast.LENGTH_SHORT).show();
//                                                    }
//                                                }
//
//
//
//                                                @Override
//                                                public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                                                }
//                                            });

                                            HashMap<String, Object> hashMap = new HashMap<>();
//                                            hashMap.put("proname", "");
//                                            hashMap.put("proemail", "");
                                            hashMap.put("pronumber", mobilenumber);
//                                            hashMap.put("prostate", "");
//                                            hashMap.put("proimage","");
                                            hashMap.put("userkey",uid);
                                            reference.child("Profileinfo").child(uid)
                                                    .updateChildren(hashMap)
                                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                        @Override
                                                        public void onComplete(@NonNull Task<Void> task) {
                                                            Toast.makeText(getApplicationContext(), "User Added", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

                                        }else{
                                            Toast.makeText(UserAddinFirebaseOtp.this, "Enter the correct OTP", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                    }else {
                        Toast.makeText(UserAddinFirebaseOtp.this, "Please check internet connection", Toast.LENGTH_SHORT).show();
                    }

                    //Toast.makeText(OtpVerificationActivity.this, "OTP Verify", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(UserAddinFirebaseOtp.this, "Please enter all numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });

        numberOtpCursorMove();

        //Resend OTP
        findViewById(R.id.tv_Resent_otp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                btn_OTP_Verify.setVisibility(View.GONE);
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91" + getIntent().getStringExtra("mobile"),
                        60, TimeUnit.SECONDS,
                        UserAddinFirebaseOtp.this,
                        new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(UserAddinFirebaseOtp.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onCodeSent(@NonNull String newbackendOTP, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {

                                getOTPbackend = newbackendOTP;
                                progressBar.setVisibility(View.GONE);
                                btn_OTP_Verify.setVisibility(View.VISIBLE);
                                Toast.makeText(UserAddinFirebaseOtp.this, "OTP sended successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });
    }
    private void numberOtpCursorMove() {
        otp_edt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    otp_edt2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otp_edt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    otp_edt3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otp_edt3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    otp_edt4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otp_edt4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    otp_edt5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        otp_edt5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    otp_edt6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}