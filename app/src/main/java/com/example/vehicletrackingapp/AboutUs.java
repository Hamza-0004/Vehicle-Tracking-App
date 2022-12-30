package com.example.vehicletrackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.model.Dash;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }

    public void onClickToDashboard(View view){
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
    }  // End of OnClick
}