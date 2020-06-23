package com.techtrickbd.nshop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.techtrickbd.nshop.R;
import com.techtrickbd.nshop.models.Free_fire_model;

import java.util.List;

public class Free_fire_adapter extends RecyclerView.Adapter<Free_fire_adapter.freeFireViewHolder> {
    private List<Free_fire_model> free_fire_models;

    public Free_fire_adapter(List<Free_fire_model> free_fire_models) {
        this.free_fire_models = free_fire_models;
    }

    @NonNull
    @Override
    public freeFireViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.sample_history_layout_, parent, false);
        freeFireViewHolder freeFireViewHolder = new freeFireViewHolder(view);

        return freeFireViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull freeFireViewHolder holder, int position) {
        Free_fire_model movie = free_fire_models.get(position);
        holder.plan.setText(movie.getPlan());

    }

    @Override
    public int getItemCount() {
        return free_fire_models.size();
    }

    public class freeFireViewHolder extends RecyclerView.ViewHolder {
        private TextView plan;

        public freeFireViewHolder(@NonNull View itemView) {
            super(itemView);
            plan = itemView.findViewById(R.id.free_fire_plan);
        }
    }
}
