package com.techtrickbd.nahidshop.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.techtrickbd.nahidshop.R;
import com.techtrickbd.nahidshop.models.Free_Fire_Model;
import com.techtrickbd.nahidshop.utils.Users_Static;

import java.util.Date;

import es.dmoral.toasty.Toasty;

public class PurchaseActivity extends AppCompatActivity {

    private String method, diamond, gameid, spayment, strnas, semail, parent;
    private TextView plan;
    private EditText paymentn, trnac, email;
    private Button submit;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference documentReference;
    private CollectionReference rootRef;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        casting();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        method = intent.getStringExtra("method");
        diamond = intent.getStringExtra("dimond");
        gameid = intent.getStringExtra("gameid");
        parent = intent.getStringExtra("from");
        plan.setText("You are selected " + diamond + " Package");
    }

    private void casting() {
        plan = findViewById(R.id.plan_TV);
        paymentn = findViewById(R.id.payment_number);
        trnac = findViewById(R.id.payment_trancid);
        email = findViewById(R.id.user_email);
        submit = findViewById(R.id.submit_btn);
        documentReference = db.collection("Users").document(Users_Static.uid);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getDataFromIntent();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNullValue();
            }
        });
    }

    private void checkNullValue() {
        spayment = paymentn.getText().toString();
        strnas = trnac.getText().toString();
        semail = email.getText().toString();
        if (spayment.isEmpty()) {
            Toasty.error(PurchaseActivity.this, "Enter your payment number", Toast.LENGTH_SHORT).show();
        } else if (strnas.isEmpty()) {
            Toasty.error(PurchaseActivity.this, "Enter your payment Transition ID", Toast.LENGTH_SHORT).show();
        } else if (semail.isEmpty()) {
            Toasty.error(PurchaseActivity.this, "Enter your Email for confirmation", Toast.LENGTH_SHORT).show();
        } else {
            submitData();
        }
    }

    private void submitData() {
        rootRef = db.collection(parent);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        final Free_Fire_Model free_fire_model = new Free_Fire_Model();
        free_fire_model.setPaymentNumber(spayment);
        free_fire_model.setGameid(gameid);
        free_fire_model.setPack(diamond);
        free_fire_model.setUserEmail(semail);
        free_fire_model.setPaymentTrancID(strnas);
        free_fire_model.setStatus(false);
        free_fire_model.setTimestamp(new Timestamp(new Date()));
        documentReference.collection(parent).document().set(free_fire_model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    rootRef.document().set(free_fire_model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progressDialog.hide();
                                Toasty.success(PurchaseActivity.this, "We will notify you within 24 hours", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.hide();
                            Toasty.error(PurchaseActivity.this, "error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.hide();
                Toasty.error(PurchaseActivity.this, "error" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}
