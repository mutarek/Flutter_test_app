package com.techtrickbd.nahidshop.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.techtrickbd.nahidshop.R;

public class PurchaseActivity extends AppCompatActivity {

    private String method, diamond, gameid;
    private TextView plan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase);
        casting();
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        method = intent.getStringExtra("method");
        diamond = intent.getStringExtra("dimond");
        gameid = intent.getStringExtra("gameid");
        plan.setText("You are selected "+diamond+" Package");
    }

    private void casting() {
        plan = findViewById(R.id.plan_TV);
    }

    @Override
    protected void onStart() {
        super.onStart();
        getDataFromIntent();
    }
}
