package com.example.vehicletrackingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;
import android.widget.Toolbar;

public class PointSchedule extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_point_schedule);

    }  // End of onCreate

    // onClick for Pick Morning Shift
    public void onMorningShiftDetail1(View view){
        Intent intent = new Intent(this,MorningDetails.class);
        startActivity(intent);
    }  // End of onMorningShift

    // onClick for Pick Evening Shift
    public void onEveningShift(View view){
        Intent intent = new Intent(this,EveningShiftDetails.class);
        startActivity(intent);
    }  // End of onEveningShift

    // onClick for Drop Shift 1
    public void onDropShift1(View view){
        Intent intent = new Intent(this,DropTimeShift1.class);
        startActivity(intent);
    }  // End of onDropShift1

}  // End of Main Activity