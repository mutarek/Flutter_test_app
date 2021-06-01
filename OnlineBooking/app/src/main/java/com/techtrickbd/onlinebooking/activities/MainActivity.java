package com.techtrickbd.onlinebooking.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.techtrickbd.onlinebooking.R;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Onselectedseat(View view) {
        if (selectedbtn ==0)
        {
            Toasty.success(this, "Seat selected! 0", Toast.LENGTH_SHORT, true).show();

        }
        else
        {
            Toasty.error(this,"error",Toast.LENGTH_LONG,true).show();
        }
    }
}