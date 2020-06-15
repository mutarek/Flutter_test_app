package com.techtrickbd.nshop.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techtrickbd.nshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaintSeiyaFragment extends Fragment {

    public SaintSeiyaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saint_seiya, container, false);
    }
}
