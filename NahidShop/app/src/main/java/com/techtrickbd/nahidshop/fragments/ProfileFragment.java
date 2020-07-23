package com.techtrickbd.nahidshop.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.techtrickbd.nahidshop.R;
import com.techtrickbd.nahidshop.activities.CreationActivity;
import com.techtrickbd.nahidshop.adapters.Free_Fire_Adapter;
import com.techtrickbd.nahidshop.adapters.History_Adapters;
import com.techtrickbd.nahidshop.models.Free_Fire_Model;
import com.techtrickbd.nahidshop.models.History_Model;
import com.techtrickbd.nahidshop.models.Profile;
import com.techtrickbd.nahidshop.utils.Users_Static;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    private TextView uid, coin, tk;
    private TextView send;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView profileRecyller;
    private CollectionReference collectionReference;
    private List<History_Model> history_models;
    private Context context;
    private History_Adapters history_adapters;
    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        casting(view);
        return view;
    }

    private void casting(View view) {
        uid = view.findViewById(R.id.profile_uid);
        coin = view.findViewById(R.id.profile_coin);
        tk = view.findViewById(R.id.profile_tk);
        send = view.findViewById(R.id.send_tk);
        profileRecyller = view.findViewById(R.id.profile_recyllerView);
        collectionReference = db.collection("Users").document(Users_Static.uid).collection("History");
        history_models = new ArrayList<>();
        progressBar = view.findViewById(R.id._history_spin_kit);
        Sprite doubleBounce = new CubeGrid();
        progressBar.setIndeterminateDrawable(doubleBounce);
    }

    @Override
    public void onStart() {
        super.onStart();
        initProfile();
        initRecyllerView();
    }

    private void initRecyllerView() {
        db.collection("RAW")
                .whereEqualTo("uid", Users_Static.uid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("TAG", document.getId() + " => " + document.getData());
                                History_Model fireModel = document.toObject(History_Model.class);
                                history_models.add(fireModel);
                                history_adapters = new History_Adapters(history_models, context);
                                profileRecyller.setAdapter(history_adapters);
                                LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                                profileRecyller.setLayoutManager(horizontalLayoutManagaer);
                                profileRecyller.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                            }
                        } else {
                            Log.d("TAG", "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    private void initProfile() {
        DocumentReference userDoc = db.collection("Users").document(Users_Static.uid);
        userDoc.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Profile profile = document.toObject(Profile.class);
                        uid.setText("@" + profile.getUid());
                        coin.setText("Coin: " + profile.getCoin());
                        tk.setText("N-Wallet: " + profile.getTk().toString());
                    }
                } else {

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), "" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        history_models.clear();
    }
}
