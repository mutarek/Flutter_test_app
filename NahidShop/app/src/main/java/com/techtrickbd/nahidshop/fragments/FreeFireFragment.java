package com.techtrickbd.nahidshop.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.techtrickbd.nahidshop.R;
import com.techtrickbd.nahidshop.activities.PurchaseActivity;
import com.techtrickbd.nahidshop.models.Free_Fire_Model;
import com.techtrickbd.nahidshop.models.Profile;
import com.techtrickbd.nahidshop.utils.Users_Static;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class FreeFireFragment extends Fragment {

    public FreeFireFragment() {

    }

    private Button buyNow;
    private RadioGroup radioGroup, paymentRadioGroup;
    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, p1, p2, p3, p4;
    private EditText playerID;
    String splayerId;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference userRef;
    private DocumentReference free_fire = db
            .collection("free_fire").document();
    /*private List<Free_fire_model> free_fire_models;
    private Free_fire_adapter free_fire_adapter;*/
    private Context context;
    private RecyclerView recyclerView;
    private String TAG = "free_fire";
    String methodRB, diamondrb;
    private List<Free_Fire_Model> free_fire_models;
    //private Free_Fire_History free_fire_history;
    private Context mContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_free_fire, container, false);
        casting(view);
        radioGroup.clearCheck();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
            }
        });
        paymentRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
            }
        });
        return view;
    }

    private void casting(View view) {
        Users_Static.uid = firebaseAuth.getCurrentUser().getUid();
        userRef = db.collection("Users").document(Users_Static.uid);
        buyNow = view.findViewById(R.id.buyNow);
        radioGroup = view.findViewById(R.id.free_fire_radio_group);
        rb1 = view.findViewById(R.id.free_fire_rb1);
        rb2 = view.findViewById(R.id.free_fire_rb2);
        rb3 = view.findViewById(R.id.free_fire_rb3);
        rb4 = view.findViewById(R.id.free_fire_rb4);
        rb5 = view.findViewById(R.id.free_fire_rb5);
        rb6 = view.findViewById(R.id.free_fire_rb6);
        rb7 = view.findViewById(R.id.free_fire_rb7);
        rb8 = view.findViewById(R.id.free_fire_rb8);
        playerID = view.findViewById(R.id.free_fire_player_ID);
        paymentRadioGroup = view.findViewById(R.id.payment_rg);
        p1 = view.findViewById(R.id.bkash_rb);
        p2 = view.findViewById(R.id.rocket_rb);
        p3 = view.findViewById(R.id.nagad_rb);
        p4 = view.findViewById(R.id.nwallet_rb);
        recyclerView = view.findViewById(R.id.free_fire_recyller);
        free_fire_models = new ArrayList<>();
    }

    @Override
    public void onStart() {
        super.onStart();
        //initRecyller();
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splayerId = playerID.getText().toString();
                final int selectedID = radioGroup.getCheckedRadioButtonId();
                int paymentSelectedID = paymentRadioGroup.getCheckedRadioButtonId();
                if (splayerId.isEmpty()) {
                    Toasty.error(getContext(), "Type your player id", Toast.LENGTH_LONG).show();
                } else if (selectedID == -1) {
                    Toasty.error(getContext(), "Select an option", Toast.LENGTH_SHORT).show();
                } else if (paymentSelectedID == -1) {
                    Toasty.error(getContext(), "Please Select a payment method", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton diamondRB = radioGroup.findViewById(selectedID);
                    RadioButton paymentMethodRB = paymentRadioGroup.findViewById(paymentSelectedID);
                    methodRB = paymentMethodRB.getText().toString();
                    diamondrb = diamondRB.getText().toString();
                    if (methodRB.contains("N-Wallet")) {
                        checkWallet();
                    } else {
                        googingtoPurchase();
                    }
                }

            }
        });

    }

    /*private void initRecyller() {
        db.collection("Free_Fire").document().get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot data: " + document.getData());
                        Free_Fire_Model fireModel = document.toObject(Free_Fire_Model.class);
                        free_fire_models.add(fireModel);
                        free_fire_history = new Free_Fire_History(free_fire_models, mContext);
                        recyclerView.setAdapter(free_fire_history);
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                        recyclerView.setLayoutManager(linearLayoutManager);
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }*/

    private void checkWallet() {
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Profile profile = document.toObject(Profile.class);
                    Long tk = (long) document.get("tk");
                    if (tk <= 30) {
                        Toasty.error(getContext(), "you do not have sufficient Balance", Toast.LENGTH_SHORT).show();
                    } else {
                        googingtoPurchase();
                    }
                } else {
                    Log.d(TAG, "Cached get failed: ", task.getException());
                }
            }
        });
    }

    private void googingtoPurchase() {
        Intent intent = new Intent(getActivity(), PurchaseActivity.class);
        intent.putExtra("gameid", splayerId);
        intent.putExtra("dimond", diamondrb);
        intent.putExtra("method", methodRB);
        intent.putExtra("from", "Free_Fire");
        startActivity(intent);
    }
}
