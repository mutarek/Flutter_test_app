package com.techtrickbd.nahidshop.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.techtrickbd.nahidshop.R;
import com.techtrickbd.nahidshop.adapters.SliderAdapterExample;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    public HomeFragment() {
        // Required empty public constructor
    }
    SliderView sliderView;
    private CardView freeFire, pubg, lords, mobilelegends, saintSeiya, legacyofdiscords, eraofcelestials;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        slide(view);
        casting(view);


        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        freeFire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, new FreeFireFragment(), "DetailFragment").commit();
                ft.addToBackStack(null);
            }
        });
        pubg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, new PubgFragment(), "DetailFragment").commit();
                ft.addToBackStack(null);
            }
        });
        lords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, new LordsMobileFragment(), "DetailFragment").commit();
                ft.addToBackStack(null);
            }
        });
        mobilelegends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, new MobileLegendsFragment(), "DetailFragment").commit();
                ft.addToBackStack(null);
            }
        });
        saintSeiya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, new SaintSeiyaFragment(), "DetailFragment").commit();
                ft.addToBackStack(null);
            }
        });
        legacyofdiscords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, new LegecyofDiscordsFragment(), "DetailFragment").commit();
                ft.addToBackStack(null);
            }
        });
        eraofcelestials.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, new EraOfCelestialsFragment(), "DetailFragment").commit();
                ft.addToBackStack(null);
            }
        });


    }

    private void casting(View view) {
        freeFire = view.findViewById(R.id.freeFireCard);
        pubg = view.findViewById(R.id.pubgCardId);
        lords = view.findViewById(R.id.lordsMobileID);
        mobilelegends = view.findViewById(R.id.mobileLegendsCard);
        saintSeiya = view.findViewById(R.id.saintSeiyaCard);
        legacyofdiscords = view.findViewById(R.id.legacyofDiscordCard);
        eraofcelestials = view.findViewById(R.id.eraOfCelestialsCard);
    }

    private void slide(View view) {
        sliderView = view.findViewById(R.id.imageSlider);
        final SliderAdapterExample adapter = new SliderAdapterExample(getActivity());
        adapter.setCount(5);
        sliderView.startAutoCycle();
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setAutoCycle(false);
        sliderView.animate();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        });
    }
}
