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
import com.techtrickbd.nadmin.adapters.Pending_Adapters;
import com.techtrickbd.nadmin.interfaces.Pending_Click;
import com.techtrickbd.nadmin.models.Pending_models;

import java.util.ArrayList;
import java.util.List;

public class PendingFragment extends Fragment {


    public PendingFragment() {
        // Required empty public constructor
    }

    private RecyclerView recyclerView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference collectionReference = db.collection("Orders");
    private List<Pending_models> pending_models;
    private Pending_Adapters pending_adapters;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pending, container, false);
        casting(view);


        return view;
    }

    private void casting(View view) {
        recyclerView = view.findViewById(R.id.pending_recyllerView);
        pending_models = new ArrayList<>();
    }

    @Override
    public void onStart() {
        super.onStart();
        initRecyllerView();
    }

    private void initRecyllerView() {
        db.collection("Orders")
                .whereEqualTo("ps", false)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Pending_models pending_models1 = document.toObject(Pending_models.class);
                                pending_models1.setId(document.getId());
                                pending_models.add(pending_models1);
                                pending_adapters = new Pending_Adapters(pending_models, context, new Pending_Click() {
                                    @Override
                                    public void OnPendingClick(View view, int pendingPostion) {
                                        Toast.makeText(getContext(), "" + pendingPostion, Toast.LENGTH_SHORT).show();
                                    }
                                });
                                recyclerView.setAdapter(pending_adapters);
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                                recyclerView.setLayoutManager(linearLayoutManager);
                            }
                        } else {

                        }
                    }
                });
    }
}