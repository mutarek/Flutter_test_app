package com.techtrickbd.nahidshop.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.techtrickbd.nahidshop.R;
import com.techtrickbd.nahidshop.models.Eara_Model;
import com.techtrickbd.nahidshop.models.Profile;
import com.techtrickbd.nahidshop.models.Slider_Model;
import com.techtrickbd.nahidshop.utils.Static_Image;

public class SliderAdapterExample extends
        SliderViewAdapter<SliderAdapterExample.SliderAdapterVH> {
    private Context context;
    private int mCount;

    public SliderAdapterExample(Context context) {
        this.context = context;
    }

    public void setCount(int count) {
        this.mCount = count;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View inflate = layoutInflater.inflate(R.layout.sample_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {
        switch (position) {
            case 0:
                /*viewHolder.textViewDescription.setText("Item 0");
                viewHolder.textViewDescription.setTextSize(20);
                viewHolder.textViewDescription.setTextColor(Color.BLACK);*/
                Glide.with(viewHolder.itemView)
                        .load(Static_Image.imageone)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 1:
                /*viewHolder.textViewDescription.setText("Item 1");
                viewHolder.textViewDescription.setTextSize(20);
                viewHolder.textViewDescription.setTextColor(Color.BLACK);*/
                Glide.with(viewHolder.itemView)
                        .load(Static_Image.imagetwo)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 2:
                /*viewHolder.textViewDescription.setText("Item 2");
                viewHolder.textViewDescription.setTextSize(20);
                viewHolder.textViewDescription.setTextColor(Color.BLACK);*/
                Glide.with(viewHolder.itemView)
                        .load(Static_Image.imagethree)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;

            case 3:
                /*viewHolder.textViewDescription.setText("Item 3");
                viewHolder.textViewDescription.setTextSize(20);
                viewHolder.textViewDescription.setTextColor(Color.BLACK);*/
                Glide.with(viewHolder.itemView)
                        .load(Static_Image.imagefour)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;

            case 4:
                /*viewHolder.textViewDescription.setText("Item Four");
                viewHolder.textViewDescription.setTextSize(20);
                viewHolder.textViewDescription.setTextColor(Color.BLACK);*/
                Glide.with(viewHolder.itemView)
                        .load(Static_Image.imagefive)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            default:
                /*viewHolder.textViewDescription.setTextSize(20);
                viewHolder.textViewDescription.setTextColor(Color.BLACK);
                viewHolder.textViewDescription.setText("Item Five");*/
                Glide.with(viewHolder.itemView)
                        .load(Static_Image.imagefive)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;

        }

    }


    @Override
    public int getCount() {
        //slider view count could be dynamic size
        return mCount;
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        View itemView;
        ImageView imageViewBackground;
        //TextView textViewDescription;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewBackground = itemView.findViewById(R.id.iv_auto_image_slider);
            //textViewDescription = itemView.findViewById(R.id.tv_auto_image_slider);
            this.itemView = itemView;
        }
    }


}

