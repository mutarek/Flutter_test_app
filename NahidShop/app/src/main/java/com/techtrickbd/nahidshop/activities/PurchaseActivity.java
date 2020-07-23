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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.techtrickbd.nahidshop.R;
import com.techtrickbd.nahidshop.models.Eara_Model;
import com.techtrickbd.nahidshop.models.Free_Fire_Model;
import com.techtrickbd.nahidshop.models.Legecy_Model;
import com.techtrickbd.nahidshop.models.Legends_Model;
import com.techtrickbd.nahidshop.models.Lords_Model;
import com.techtrickbd.nahidshop.models.Profile;
import com.techtrickbd.nahidshop.models.Pubg_Model;
import com.techtrickbd.nahidshop.models.Saint_Model;
import com.techtrickbd.nahidshop.utils.Users_Static;

import java.util.Date;

import es.dmoral.toasty.Toasty;

public class PurchaseActivity extends AppCompatActivity {
    private String method, diamond, gameid, spayment, strnas, semail, parent, server;
    private TextView plan;
    private EditText paymentn, trnac, email;
    private Button submit;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference documentReference;
    private CollectionReference rootRef, profileref;
    private ProgressDialog progressDialog;
    private String newParent = "History";
    private TextView paymentNumberID, supprt;
    private CollectionReference collectionReference = db.collection("RAW");

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
        server = intent.getStringExtra("server");
        plan.setText("You are selected " + diamond + " Package");
    }

    private void casting() {
        plan = findViewById(R.id.plan_TV);
        paymentn = findViewById(R.id.payment_number);
        trnac = findViewById(R.id.payment_trancid);
        email = findViewById(R.id.user_email);
        submit = findViewById(R.id.submit_btn);
        supprt = findViewById(R.id.support_TV);
        paymentNumberID = findViewById(R.id.payment_Number_ID);
        documentReference = db.collection("Users").document(Users_Static.uid);
        progressDialog = new ProgressDialog(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getDataFromIntent();
        getNumberUTils();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkNullValue();
            }
        });
    }

    private void getNumberUTils() {
        documentReference = db.collection("Numbers").document("Payments");
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        supprt.setText(" If your gyus facing any problem during payment or anykind of payment issue please call for live support " + document.get("support").toString());
                        if (method.equals("Bkash")) {
                            paymentNumberID.setText("1. Send Your validated ammount to this Bkash Number " + document.get("Bkash").toString());
                        } else if (method.equals("Rocket")) {
                            paymentNumberID.setText("1. Send Your validated ammount to this Rocket Number " + document.get("Rocket").toString());
                        } else {
                            paymentNumberID.setText("1. Send Your validated ammount to this Nagad Number " + document.get("Nagad").toString());
                        }
                    }
                } else {

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PurchaseActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
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
        checkCasedValue();

    }

    private void checkCasedValue() {
        switch (parent) {
            case "Free_Fire": {
                setFreeFireData();
                break;
            }
            case "Pubg": {
                setPubgData();
                break;
            }
            case "Era_Celestials": {
                setEraData();
                break;
            }
            case "Legecy_of_Discords": {
                setLegecyData();
                break;
            }
            case "Lords": {
                setLordsData();
                break;
            }
            case "Mobile_Legends": {
                setLegendsData();
            }
            case "Saint_Seiya": {
                setSaintData();
            }
            default:
                Toast.makeText(PurchaseActivity.this, "Default called", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void setSaintData() {
        rootRef = db.collection("Orders");
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        final Saint_Model free_fire_model = new Saint_Model();
        free_fire_model.setPaymentNumber(spayment);
        free_fire_model.setGameid(gameid);
        free_fire_model.setPack(diamond);
        free_fire_model.setEmail(semail);
        free_fire_model.setPaymentTrancID(strnas);
        free_fire_model.setStatus(false);
        free_fire_model.setTimestamp(new Timestamp(new Date()));
        free_fire_model.setParent(parent);
        free_fire_model.setServer(server);
        free_fire_model.setUid(Users_Static.uid);
        free_fire_model.setPs(false);
        rootRef.document().set(free_fire_model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    collectionReference.document().set(free_fire_model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progressDialog.hide();
                                Toasty.success(PurchaseActivity.this, "We will notify you within 24 hours", Toast.LENGTH_SHORT).show();
                                upDateUi();
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

    private void upDateUi() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    private void setLegendsData() {
        rootRef = db.collection(parent);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        final Lords_Model free_fire_model = new Lords_Model();
        free_fire_model.setPaymentNumber(spayment);
        free_fire_model.setGameid(gameid);
        free_fire_model.setPack(diamond);
        free_fire_model.setEmail(semail);
        free_fire_model.setPaymentTrancID(strnas);
        free_fire_model.setStatus(false);
        free_fire_model.setTimestamp(new Timestamp(new Date()));
        free_fire_model.setParent(parent);
        rootRef.document().set(free_fire_model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    collectionReference.document(Users_Static.uid).collection("History").document().set(free_fire_model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progressDialog.hide();
                                Toasty.success(PurchaseActivity.this, "We will notify you within 24 hours", Toast.LENGTH_SHORT).show();
                                upDateUi();
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

    private void setLordsData() {
        rootRef = db.collection(parent);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        final Legends_Model free_fire_model = new Legends_Model();
        free_fire_model.setPaymentNumber(spayment);
        free_fire_model.setGameid(gameid);
        free_fire_model.setPack(diamond);
        free_fire_model.setEmail(semail);
        free_fire_model.setPaymentTrancID(strnas);
        free_fire_model.setStatus(false);
        free_fire_model.setTimestamp(new Timestamp(new Date()));
        free_fire_model.setParent(parent);
        free_fire_model.setServer(server);
        rootRef.document().set(free_fire_model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    collectionReference.document(Users_Static.uid).collection("History").document().set(free_fire_model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progressDialog.hide();
                                Toasty.success(PurchaseActivity.this, "We will notify you within 24 hours", Toast.LENGTH_SHORT).show();
                                upDateUi();
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

    private void setLegecyData() {
        rootRef = db.collection(parent);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        final Legecy_Model free_fire_model = new Legecy_Model();
        free_fire_model.setPaymentNumber(spayment);
        free_fire_model.setGameid(gameid);
        free_fire_model.setPack(diamond);
        free_fire_model.setEmail(semail);
        free_fire_model.setPaymentTrancID(strnas);
        free_fire_model.setStatus(false);
        free_fire_model.setTimestamp(new Timestamp(new Date()));
        free_fire_model.setParent(parent);
        free_fire_model.setServer(server);
        rootRef.document().set(free_fire_model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    collectionReference.document(Users_Static.uid).collection("History").document().set(free_fire_model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progressDialog.hide();
                                Toasty.success(PurchaseActivity.this, "We will notify you within 24 hours", Toast.LENGTH_SHORT).show();
                                upDateUi();
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

    private void setEraData() {
        rootRef = db.collection(parent);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        final Eara_Model free_fire_model = new Eara_Model();
        free_fire_model.setPaymentNumber(spayment);
        free_fire_model.setGameid(gameid);
        free_fire_model.setPack(diamond);
        free_fire_model.setEmail(semail);
        free_fire_model.setPaymentTrancID(strnas);
        free_fire_model.setStatus(false);
        free_fire_model.setTimestamp(new Timestamp(new Date()));
        free_fire_model.setParent(parent);
        free_fire_model.setServer(server);
        rootRef.document().set(free_fire_model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    collectionReference.document(Users_Static.uid).collection("History").document().set(free_fire_model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progressDialog.hide();
                                Toasty.success(PurchaseActivity.this, "We will notify you within 24 hours", Toast.LENGTH_SHORT).show();
                                upDateUi();
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

    private void setPubgData() {
        rootRef = db.collection(parent);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        final Pubg_Model free_fire_model = new Pubg_Model();
        free_fire_model.setPaymentNumber(spayment);
        free_fire_model.setGameid(gameid);
        free_fire_model.setPack(diamond);
        free_fire_model.setEmail(semail);
        free_fire_model.setPaymentTrancID(strnas);
        free_fire_model.setStatus(false);
        free_fire_model.setTimestamp(new Timestamp(new Date()));
        free_fire_model.setParent(parent);
        rootRef.document().set(free_fire_model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    collectionReference.document(Users_Static.uid).collection("History").document().set(free_fire_model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progressDialog.hide();
                                Toasty.success(PurchaseActivity.this, "We will notify you within 24 hours", Toast.LENGTH_SHORT).show();
                                upDateUi();
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

    private void setFreeFireData() {
        rootRef = db.collection(parent);
        progressDialog.setMessage("Please Wait...");
        progressDialog.show();
        final Free_Fire_Model free_fire_model = new Free_Fire_Model();
        free_fire_model.setPaymentNumber(spayment);
        free_fire_model.setGameid(gameid);
        free_fire_model.setPack(diamond);
        free_fire_model.setEmail(semail);
        free_fire_model.setPaymentTrancID(strnas);
        free_fire_model.setStatus(false);
        free_fire_model.setTimestamp(new Timestamp(new Date()));
        free_fire_model.setParent(parent);
        rootRef.document().set(free_fire_model).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    collectionReference.document(Users_Static.uid).collection("History").document().set(free_fire_model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                progressDialog.hide();
                                Toasty.success(PurchaseActivity.this, "We will notify you within 24 hours", Toast.LENGTH_SHORT).show();
                                upDateUi();
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