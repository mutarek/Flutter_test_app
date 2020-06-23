package com.techtrickbd.nshop.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.techtrickbd.nshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment {

    public ProfileFragment() {
        // Required empty public constructor
    }

    private FirebaseAuth firebaseAuth;
    private String cUser;
    private TextView userID,userCoin;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        casting(view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadProfile();
    }

    private void loadProfile() {

    }

    private void casting(View view) {
        userID =view.findViewById(R.id.profile_uid);
        userCoin = view.findViewById(R.id.profile_coin);
        firebaseAuth = FirebaseAuth.getInstance();
        cUser = firebaseAuth.getCurrentUser().getUid();

    }
}
