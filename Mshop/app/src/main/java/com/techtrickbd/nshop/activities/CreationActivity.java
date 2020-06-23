package com.techtrickbd.nshop.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.techtrickbd.nshop.R;
import com.techtrickbd.nshop.models.Profile;

import java.util.Map;

import es.dmoral.toasty.Toasty;

public class CreationActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private String cUser;
    private EditText userNameET, emailET;
    private FirebaseFirestore db;
    DocumentReference userCollection;
    String TAG = "Main";
    private Button done;
    String sName, sEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);
        casting();
    }

    private void casting() {
        userNameET = findViewById(R.id.name_ET);
        emailET = findViewById(R.id.email_et);
        firebaseAuth = FirebaseAuth.getInstance();
        cUser = firebaseAuth.getCurrentUser().getUid();
        db = FirebaseFirestore.getInstance();
        userCollection = db.collection("Users").document(cUser);
        done = findViewById(R.id.btn_submit);
    }

    @Override
    protected void onStart() {
        super.onStart();
        checkProfile();
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNullValue();
            }
        });
    }

    private void checkNullValue() {
        sName = userNameET.getText().toString();
        sEmail = emailET.getText().toString();
        if (sName.isEmpty() || sEmail.isEmpty()) {
            Toasty.error(this, "Please Complete All the things", Toast.LENGTH_SHORT).show();
        } else {
            completeProfile();
        }
    }

    private void completeProfile() {
        Profile profile = new Profile();
        profile.setName(sName);
        profile.setEmail(sEmail);
        profile.setCoin(0);
        profile.setUid(cUser);
        profile.setTk(0);

        db.collection("Users").document(cUser).set(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toasty.success(CreationActivity.this, "Successfully Account Created", Toast.LENGTH_SHORT).show();
                    updateUI();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toasty.error(CreationActivity.this, "Unsuccesfull:" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void checkProfile() {
        userCollection.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot snapshot,
                                @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Log.w(TAG, "Listen failed.", e);
                    return;
                }
                if (snapshot != null && snapshot.exists()) {
                    updateUI();
                } else {
                    Log.d(TAG, "Current data: null");
                }
            }
        });
    }

    private void updateUI() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
