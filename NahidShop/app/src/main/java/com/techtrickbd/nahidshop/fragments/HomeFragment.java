package com.techtrickbd.nahidshop.fragments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.techtrickbd.nahidshop.R;
import com.techtrickbd.nahidshop.adapters.SliderAdapterExample;
import com.techtrickbd.nahidshop.models.Slider_Model;
import com.techtrickbd.nahidshop.utils.Static_Image;

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
        getData();
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

    private void getData() {
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference sliderRef = db.collection("Images").document("Slider_Images");
        sliderRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Slider_Model slider_model = document.toObject(Slider_Model.class);
                        Static_Image.imageone = slider_model.getImage1();
                        Static_Image.imagetwo = slider_model.getImage2();
                        Static_Image.imagethree = slider_model.getImage3();
                        Static_Image.imagefour = slider_model.getImage4();
                        Static_Image.imagefive = slider_model.getImage5();
                    }
                } else {

                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), ""+e.getMessage(), Toast.LENGTH_SHORT).show();
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
        sliderView.setSliderAdapter(adapter);
        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.CUBEINROTATIONTRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setIndicatorSelectedColor(Color.RED);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setAutoCycle(true);
        sliderView.animate();

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                sliderView.setCurrentPagePosition(position);
            }
        });
    }
}
