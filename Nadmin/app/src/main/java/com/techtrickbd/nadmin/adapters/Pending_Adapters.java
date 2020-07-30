package com.techtrickbd.nadmin.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
import com.techtrickbd.nadmin.interfaces.Pending_Click;
import com.techtrickbd.nadmin.models.Pending_models;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class Pending_Adapters extends RecyclerView.Adapter<Pending_Adapters.PendingViewHolder> {
    private List<Pending_models> pending_models;
    private Context context;
    private Pending_Click pending_click;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference d;

    public Pending_Adapters(List<Pending_models> pending_models, Context context, Pending_Click pending_click) {
        this.pending_models = pending_models;
        this.context = context;
        this.pending_click = pending_click;
    }

    @NonNull
    @Override
    public PendingViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final View view = layoutInflater.inflate(R.layout.pending_layout, parent, false);
        final PendingViewHolder pendingViewHolder = new PendingViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pending_click.OnPendingClick(parent, pendingViewHolder.getAdapterPosition());
            }
        });
        return pendingViewHolder;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull PendingViewHolder holder, final int position) {
        holder.parent.setText(pending_models.get(position).getParent());
        holder.trancID.setText(pending_models.get(position).getPaymentTrancID());
        holder.packagename.setText(pending_models.get(position).getPack());
        holder.trancID.setText(pending_models.get(position).getPaymentTrancID());
        Timestamp timestamp = pending_models.get(position).getTimestamp();
        long mili = timestamp.getSeconds() * 1000;
        String tz = String.valueOf(ZoneId.systemDefault());
        LocalDateTime localDateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(mili), ZoneId.of(tz));
        holder.date.setText(localDateTime.toLocalDate().toString());
        holder.parent.setText(pending_models.get(position).getParent());
        holder.paid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                Map<String, Object> data = new HashMap<>();
                data.put("ps", true);
                db.collection("Orders").document(pending_models.get(position).getId())
                        .set(data, SetOptions.merge()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            removeItem(position);
                            Toasty.success(v.getContext(), "Item Is Updated", Toasty.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }

    private void removeItem(int position) {
        pending_models.remove(pending_models.get(position));
        notifyDataSetChanged();

    }

    @Override
    public int getItemCount() {
        return pending_models.size();
    }

    public class PendingViewHolder extends RecyclerView.ViewHolder {

        private TextView date, packagename, trancID, parent;
        private Button paid;

        public PendingViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.pending_date);
            packagename = itemView.findViewById(R.id.pending_package);
            trancID = itemView.findViewById(R.id.pending_tranc);
            parent = itemView.findViewById(R.id.pending_parent);
            paid = itemView.findViewById(R.id.paid_button);
        }
    }
}
