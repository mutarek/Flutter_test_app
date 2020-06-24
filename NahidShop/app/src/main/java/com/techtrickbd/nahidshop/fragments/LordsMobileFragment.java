package com.techtrickbd.nahidshop.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techtrickbd.nahidshop.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LordsMobileFragment extends Fragment {

    public LordsMobileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lords_mobile, container, false);
    }
}
