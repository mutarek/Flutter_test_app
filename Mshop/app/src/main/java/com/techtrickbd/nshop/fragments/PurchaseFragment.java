package com.techtrickbd.nshop.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techtrickbd.nshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PurchaseFragment extends Fragment {

    public PurchaseFragment() {
        // Required empty public constructor
    }

    private TextView plantv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_purchase, container, false);
        casting(view);

        return view;
    }

    private void casting(View view) {
        plantv = view.findViewById(R.id.plan_TV);
    }

    @Override
    public void onStart() {
        super.onStart();
        getBundleData();
    }

    private void getBundleData() {
        Bundle mBundle = getArguments();
        String game = mBundle.getString("gameID");
        Log.d("Data",game);
    }
}
