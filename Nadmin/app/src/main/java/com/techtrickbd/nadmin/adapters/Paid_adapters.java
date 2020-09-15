package com.techtrickbd.nadmin.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;
import com.techtrickbd.nadmin.R;
import com.techtrickbd.nadmin.models.Paid_Models;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class Paid_adapters extends RecyclerView.Adapter<Paid_adapters.PaidViewHoler> {
    private List<Paid_Models> paid_models;
    private Context context;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference d;

    public Paid_adapters(List<Paid_Models> paid_models, Context context) {
        this.paid_models = paid_models;
        this.context = context;
    }

    @NonNull
    @Override
    public PaidViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.pending_layout, parent, false);
        final PaidViewHoler pendingViewHolder = new PaidViewHoler(view);

        return pendingViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull PaidViewHoler holder, final int position) {
        holder.parent.setText(paid_models.get(position).getParent());
        holder.trancID.setText(paid_models.get(position).getPaymentTrancID());
        holder.packagename.setText(paid_models.get(position).getPack());
        holder.trancID.setText(paid_models.get(position).getPaymentTrancID());
        Timestamp timestamp = paid_models.get(position).getTimestamp();
        long mili = timestamp.getSeconds() * 1000;
        String tz = String.valueOf(ZoneId.systemDefault());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(mili), ZoneId.of(tz));
        holder.date.setText(localDateTime.toLocalDate().toString());
        holder.parent.setText(paid_models.get(position).getParent());
        holder.paid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Map<String, Object> data = new HashMap<>();
                data.put("status", true);
                db.collection("Orders").document(paid_models.get(position).getId())
                        .set(data, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            removeItem(position);
                            Toasty.success(v.getContext(), "Item Is Delivered", Toasty.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void removeItem(int position) {
        paid_models.remove(paid_models.get(position));
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return paid_models.size();
    }

    public class PaidViewHoler extends RecyclerView.ViewHolder {
        private TextView date, packagename, trancID, parent;
        private Button paid;

        public PaidViewHoler(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.paid_date);
            packagename = itemView.findViewById(R.id.paid_package);
            trancID = itemView.findViewById(R.id.paid_tranc);
            parent = itemView.findViewById(R.id.paid_parent);
            paid = itemView.findViewById(R.id.deleverd_btn);
        }
    }
}
