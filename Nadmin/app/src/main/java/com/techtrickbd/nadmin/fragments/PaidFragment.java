package com.techtrickbd.nadmin.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.techtrickbd.nadmin.R;
import com.techtrickbd.nadmin.adapters.Paid_adapters;
import com.techtrickbd.nadmin.adapters.Pending_Adapters;
import com.techtrickbd.nadmin.interfaces.Pending_Click;
import com.techtrickbd.nadmin.models.Paid_Models;
import com.techtrickbd.nadmin.models.Pending_models;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class PaidFragment extends Fragment {

    private RecyclerView recyclerView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Orders");
    private List<Paid_Models> pending_models;
    private Paid_adapters pending_adapters;
    private Context context;

    public PaidFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_paid, container, false);
        casting(view);
        return view;
    }

    private void casting(View view) {
        recyclerView = view.findViewById(R.id.paid_recyller);
        pending_models = new ArrayList<>();
    }

    @Override
    public void onStart() {
        super.onStart();
        initRecyllerView();
    }

    private void initRecyllerView() {
        db.collection("Orders")
                .whereEqualTo("ps", true)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Paid_Models paid_models = document.toObject(Paid_Models.class);
                                paid_models.setId(document.getId());
                                pending_models.add(paid_models);
                                pending_adapters = new Paid_adapters(pending_models, context);
                                recyclerView.setAdapter(pending_adapters);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                                recyclerView.setLayoutManager(linearLayoutManager);
                            }
                        } else {
                            Toasty.error(getContext(),"error"+task.getException(),Toasty.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}