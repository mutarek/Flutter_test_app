package com.techtrickbd.nshop.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.techtrickbd.nshop.R;
import com.techtrickbd.nshop.adapters.Free_fire_adapter;
import com.techtrickbd.nshop.models.Free_fire_model;
import com.techtrickbd.nshop.models.Profile;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class FreeFireFragment extends Fragment {

    public FreeFireFragment() {
        // Required empty public constructor
    }

    private Button buyNow;
    private RadioGroup radioGroup, paymentRadioGroup;
    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8, p1, p2, p3, p4;
    private EditText playerID;
    String splayerId;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private String cUser = firebaseAuth.getCurrentUser().getUid();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference userRef = db
            .collection("Users").document(cUser);
    private DocumentReference free_fire = db
            .collection("free_fire").document();
    private List<Free_fire_model> free_fire_models;
    private Free_fire_adapter free_fire_adapter;
    private Context context;
    private RecyclerView recyclerView;
    private String TAG = "free_fire";
    String methodRB, diamondrb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_free_fire, container, false);
        casting(view);
        //initRecyller(view);
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
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, new PurchaseFragment(), "DetailFragment").commit();
                        fragmentTransaction.addToBackStack(null);
                    }
                }

            }
        });

        return view;
    }

    private void initRecyller(View view) {
        free_fire.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Free_fire_model bd_travel_model = document.toObject(Free_fire_model.class);
                    free_fire_models.add(bd_travel_model);
                    recyclerView.hasFixedSize();
                    free_fire_adapter = new Free_fire_adapter(free_fire_models);
                    recyclerView.setAdapter(free_fire_adapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                    recyclerView.setLayoutManager(linearLayoutManager);
                } else {
                    Log.d(TAG, "Cached get failed: ", task.getException());
                }
            }
        });
    }

    private void checkWallet() {
        userRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    Profile profile = document.toObject(Profile.class);
                    Long tk = (long) document.get("tk");
                    if (tk <= 30) {
                        Toasty.error(getActivity(), "you do not have sufficient Balance", Toast.LENGTH_SHORT).show();
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
        Bundle result = new Bundle();
        result.putString("gameID", splayerId);
        result.putString("pament_method", methodRB);
        result.putString("diamond", diamondrb);
        PurchaseFragment purchaseFragment = new PurchaseFragment();
        purchaseFragment.setArguments(result);
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, purchaseFragment, "DetailFragment").commit();
        fragmentTransaction.addToBackStack(null);
    }

    @Override
    public void onStart() {
        super.onStart();
        //uploadDB();
    }


    private void casting(View view) {
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
}
