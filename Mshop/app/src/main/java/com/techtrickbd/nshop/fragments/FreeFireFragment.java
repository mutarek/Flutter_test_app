package com.techtrickbd.nshop.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.techtrickbd.nshop.R;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 */
public class FreeFireFragment extends Fragment {

    public FreeFireFragment() {
        // Required empty public constructor
    }

    private Button buyNow;
    private RadioGroup radioGroup;
    private RadioButton rb1, rb2, rb3, rb4, rb5, rb6, rb7, rb8;
    private EditText playerID;
    private EditText gateway;
    String splayerId, sGateway;

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
        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                splayerId = playerID.getText().toString();
                sGateway = gateway.getText().toString();
                final int selectedID = radioGroup.getCheckedRadioButtonId();
                if (splayerId.isEmpty()) {
                    Toasty.error(getContext(), "Type your player id", Toast.LENGTH_LONG).show();
                } else if (sGateway.isEmpty()) {
                    Toasty.error(getContext(), "Type payment method", Toast.LENGTH_SHORT).show();
                } else if (selectedID == -1) {
                    Toasty.error(getContext(), "Select an option", Toast.LENGTH_SHORT).show();
                } else {
                    RadioButton radioButton = radioGroup.findViewById(selectedID);
                    FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.fragment_container, new PurchaseFragment(), "DetailFragment").commit();
                    fragmentTransaction.addToBackStack(null);
                    Toast.makeText(getContext(), "" + radioButton.getText().toString(), Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
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
        gateway = view.findViewById(R.id.free_fire_payment_way_ET);

    }
}
