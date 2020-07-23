package com.techtrickbd.nadmin.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.techtrickbd.nadmin.R;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private EditText email, pass, agentemil, agenPass;
    private String semail, spass;
    private Button adminlog, agentlog;
    private LinearLayout agentLiner, adminLinerar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        casting();
    }

    private void casting() {
        email = findViewById(R.id.admin_email_ET);
        pass = findViewById(R.id.admin_pass_ET);
        agentemil = findViewById(R.id.agent_email_ET);
        agenPass = findViewById(R.id.agent_pass_ET);
        adminlog = findViewById(R.id.loginBTN);
        agentlog = findViewById(R.id.agent_loginBTN);
        agentLiner = findViewById(R.id.agent_Linerar);
        adminLinerar = findViewById(R.id.admin_Linerar);
    }

    private void showCustomDialog() {
        ViewGroup viewGroup = findViewById(android.R.id.content);
        View dialogView = LayoutInflater.from(this).inflate(R.layout.my_dialog, viewGroup, false);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        Button adButton = dialogView.findViewById(R.id.btnAdmin);
        Button agButton = dialogView.findViewById(R.id.btnAgent);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();
        agButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                goForAgentSignup();
            }
        });
        adButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                goForAdminLogin();
            }
        });
    }

    private void goForAdminLogin() {

        adminlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNullValue();
            }
        });
    }

    private void goForAgentSignup() {
        adminLinerar.setVisibility(View.GONE);
        agentLiner.setVisibility(View.VISIBLE);
        agentlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAgemtNullValue();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        if (currentUser == null) {
            showCustomDialog();
        } else {
            updateUI();
        }
    }

    private void checkAgemtNullValue() {
        semail = agentemil.getText().toString();
        spass = agenPass.getText().toString();
        if (semail.isEmpty()) {
            Toast.makeText(this, "emial", Toast.LENGTH_SHORT).show();
        } else if (spass.isEmpty()) {
            Toast.makeText(this, "Pass", Toast.LENGTH_SHORT).show();
        } else {
            agenLogin(semail, spass);
        }
    }

    private void agenLogin(String semail, String spass) {
        firebaseAuth.createUserWithEmailAndPassword(semail, spass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "success", Toast.LENGTH_SHORT).show();
                    updateAgentUI();
                } else {
                    Toast.makeText(SignUpActivity.this, "" + task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateAgentUI() {
        startActivity(new Intent(this, AgentActivity.class));
    }

    private void checkNullValue() {
        semail = email.getText().toString();
        spass = pass.getText().toString();
        if (semail.isEmpty()) {
            Toast.makeText(this, "Email is required", Toast.LENGTH_SHORT).show();
        } else if (spass.isEmpty()) {
            Toast.makeText(this, "Password is required", Toast.LENGTH_SHORT).show();
        } else {
            login(semail, spass);
        }
    }

    private void login(String semail, String spass) {
        firebaseAuth.createUserWithEmailAndPassword(semail, spass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    updateUI();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(SignUpActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateUI() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}