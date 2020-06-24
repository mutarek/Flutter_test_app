package com.techtrickbd.nahidshop.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.techtrickbd.nahidshop.R;

import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;

import static java.security.AccessController.getContext;

public class SignUpActivity extends AppCompatActivity {
    private EditText numberEt;
    private Button btnNext;
    private View view;
    private PinView pinView;
    private EditText nameET;
    private EditText gameET;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String mVerificationId;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        casting();

    }

    private void casting() {
        numberEt = findViewById(R.id.number_ET);
        btnNext = findViewById(R.id.btn_next);
        pinView = findViewById(R.id.firstPinView);
        progressBar = findViewById(R.id.progress_bar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkUserSignInOrNot();
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNullValue();
            }
        });

    }

    private void checkNullValue() {
        String number = numberEt.getText().toString();
        if (number.isEmpty()) {
            Toasty.error(this, "Please enter your number", Toast.LENGTH_SHORT, true).show();
        } else if (!number.startsWith("01")) {
            Toasty.warning(this, "Please enter a valid number", Toast.LENGTH_SHORT, true).show();
        } else if (!(number.length() == 11)) {
            Toasty.warning(this, "Number should be 11 character", Toast.LENGTH_SHORT, true).show();
        } else {
            progressBar.setVisibility(View.VISIBLE);
            sendOTP(number);
        }


    }

    private void sendOTP(String number) {
        numberEt.setVisibility(View.GONE);
        pinView.setVisibility(View.VISIBLE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+88" + number,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
        btnNext.setText("Verify");
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goForVerify();
            }
        });
    }

    private void goForVerify() {
        pinView.setVisibility(View.GONE);
        numberEt.setText(null);
        btnNext.setText("Done");
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            String code = phoneAuthCredential.getSmsCode();
            if (code != null) {
                progressBar.setVisibility(View.INVISIBLE);
                pinView.setText(code);
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toasty.error(SignUpActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            mVerificationId = s;
        }
    };

    private void verifyVerificationCode(String code) {
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //verification successful we will start the profile activity
                            btnNext.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    upDateUi();
                                }
                            });

                        } else {
                            String message = "Somthing is wrong, we will fix it soon...";
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                                Toasty.error(SignUpActivity.this, "Invalid code entered...", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void upDateUi() {
        Intent intent = new Intent(this, CreationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void checkUserSignInOrNot() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            upDateUi();
        } else {


        }

    }
}
