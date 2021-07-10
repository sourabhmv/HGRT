package com.itsmesou.hgrtapp;

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

import java.util.concurrent.TimeUnit;

public class Mobilenumber extends AppCompatActivity {

    EditText enternumber;
    Button getotpbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobilenumber);

        enternumber = findViewById(R.id.inputmobilenum);
        getotpbutton = findViewById(R.id.otp);

        ProgressBar progressBar = findViewById(R.id.progresssending);


        getotpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!enternumber.getText().toString().trim().isEmpty()) {
                    if (enternumber.getText().toString().trim().length() == 10) {

                        progressBar.setVisibility(View.VISIBLE);
                        getotpbutton.setVisibility(View.INVISIBLE);

                        PhoneAuthProvider.getInstance().verifyPhoneNumber("+91" + enternumber.getText().toString(), 60, TimeUnit.SECONDS, Mobilenumber.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                progressBar.setVisibility(View.GONE);
                                getotpbutton.setVisibility(View.VISIBLE);

                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                progressBar.setVisibility(View.GONE);
                                getotpbutton.setVisibility(View.VISIBLE);
                                Toast.makeText(Mobilenumber.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                            }

                            @Override
                            public void onCodeSent(@NonNull String backendotp, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                progressBar.setVisibility(View.GONE);
                                getotpbutton.setVisibility(View.VISIBLE);

                                Intent intent = new Intent(getApplicationContext(), otpverify.class);
                                intent.putExtra("mobile", enternumber.getText().toString());
                                intent.putExtra("backendotp", backendotp);
                                startActivity(intent);

                            }
                        });

                         /*Intent intent = new Intent(getApplicationContext(), otpverify.class);
                         intent.putExtra("mobile", enternumber.getText().toString());
                         startActivity(intent);*/
                    } else {
                        Toast.makeText(Mobilenumber.this, "Please Enter correct number ", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    Toast.makeText(Mobilenumber.this, "Enter mobile number", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}