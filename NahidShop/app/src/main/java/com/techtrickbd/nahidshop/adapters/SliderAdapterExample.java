package com.techtrickbd.nahidshop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.techtrickbd.nahidshop.R;

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


        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "" + position, Toast.LENGTH_SHORT).show();
            }
        });


        switch (position) {
            case 0:
                /*viewHolder.textViewDescription.setText("Item 0");
                viewHolder.textViewDescription.setTextSize(20);
                viewHolder.textViewDescription.setTextColor(Color.BLACK);*/
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.sliderone)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 1:
                /*viewHolder.textViewDescription.setText("Item 1");
                viewHolder.textViewDescription.setTextSize(20);
                viewHolder.textViewDescription.setTextColor(Color.BLACK);*/
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.test)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            case 2:
                /*viewHolder.textViewDescription.setText("Item 2");
                viewHolder.textViewDescription.setTextSize(20);
                viewHolder.textViewDescription.setTextColor(Color.BLACK);*/
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.test)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;

            case 3:
                /*viewHolder.textViewDescription.setText("Item 3");
                viewHolder.textViewDescription.setTextSize(20);
                viewHolder.textViewDescription.setTextColor(Color.BLACK);*/
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.test)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;

            case 4:
                /*viewHolder.textViewDescription.setText("Item Four");
                viewHolder.textViewDescription.setTextSize(20);
                viewHolder.textViewDescription.setTextColor(Color.BLACK);*/
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.slider5)
                        .fitCenter()
                        .into(viewHolder.imageViewBackground);
                break;
            default:
                /*viewHolder.textViewDescription.setTextSize(20);
                viewHolder.textViewDescription.setTextColor(Color.BLACK);
                viewHolder.textViewDescription.setText("Item Five");*/
                Glide.with(viewHolder.itemView)
                        .load(R.drawable.slider5)
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

