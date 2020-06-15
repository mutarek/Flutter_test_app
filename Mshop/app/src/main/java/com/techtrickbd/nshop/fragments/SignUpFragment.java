package com.techtrickbd.nshop.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.techtrickbd.nshop.R;
import com.techtrickbd.nshop.activities.CreationActivity;
import com.techtrickbd.nshop.activities.HomeActivity;

import java.util.concurrent.TimeUnit;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignUpFragment extends Fragment {

    public SignUpFragment() {
        // Required empty public constructor
    }

    private EditText numberEt;
    private Button btnNext;
    private View view;
    private PinView pinView;
    private EditText nameET;
    private EditText gameET;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private String mVerificationId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);
        casting(view);
        checkUserSignInOrNot();


        return view;
    }

    private void onClickLister(View view) {
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
            Toasty.error(getContext(), "Please enter your number", Toast.LENGTH_SHORT, true).show();
        } else if (!number.startsWith("01")) {
            Toasty.warning(getContext(), "Please enter a valid number", Toast.LENGTH_SHORT, true).show();
        } else if (!(number.length() == 11)) {
            Toasty.warning(getContext(), "Number should be 11 character", Toast.LENGTH_SHORT, true).show();
        } else {
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
                pinView.setText(code);
                verifyVerificationCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
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
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
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
                            /*Intent intent = new Intent(VerifyPhoneActivity.this, ProfileActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);*/

                        } else {

                            //verification unsuccessful.. display an error message

                            String message = "Somthing is wrong, we will fix it soon...";

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                message = "Invalid code entered...";
                            }

                           /* Snackbar snackbar = Snackbar.make(findViewById(R.id.parent), message, Snackbar.LENGTH_LONG);
                            snackbar.setAction("Dismiss", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                }
                            });
                            snackbar.show();*/
                        }
                    }
                });
    }

    private void casting(View view) {
        numberEt = view.findViewById(R.id.number_ET);
        btnNext = view.findViewById(R.id.btn_next);
        pinView = view.findViewById(R.id.firstPinView);
    }

    @Override
    public void onStart() {
        super.onStart();
        onClickLister(view);
    }

    private void checkUserSignInOrNot() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            upDateUi();
        } else {


        }

    }

    private void upDateUi() {
        Intent intent = new Intent(getActivity(), CreationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        getActivity().finish();
    }

}
