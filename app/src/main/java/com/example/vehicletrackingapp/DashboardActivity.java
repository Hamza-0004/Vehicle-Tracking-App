package com.example.vehicletrackingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import io.paperdb.Paper;

public class DashboardActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    // Variables
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        Paper.init(this);

        /*----------------Hooks------------------*/
        drawerLayout=findViewById(R.id.drawer_layout);
        navigationView=findViewById(R.id.nav_view);
        toolbar=findViewById(R.id.toolbar);

        /*----------------Tool Bar------------------*/
        setSupportActionBar(toolbar);
        toolbar.setTitle("Dashboard");

        /*----------------Navigation Drawer Menu------------------*/

        // Hide or Show items
        Menu menu = navigationView.getMenu();
        menu.findItem(R.id.iclogout).setVisible(true);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_open,R.string.navigation_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.ic_dashboard);

    }   // End of onCreate

    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        } // End of If
        else{
            super.onBackPressed();
        }  // End of Else
    }  // End of onBackPressed

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }  // End of onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    } // End of onOptionsItemSelected

    @SuppressWarnings("StatemnentWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if(id ==  R.id.ic_dashboard)
        {

        }   // End of If Statement

        else if(id ==  R.id.allVehicles)
        {
            Intent intent = new Intent(DashboardActivity.this,TransportDetails.class);
            startActivity(intent);
        }   // End of ElseIf Statement

        else if(id ==  R.id.aboutUsdetails)
        {
            Intent intent = new Intent(DashboardActivity.this,AboutUs.class);
            startActivity(intent);
        }   // End of ElseIf Statement

        else if(id ==  R.id.iclogout)
        {
            Paper.book().destroy();
            Intent intent = new Intent(DashboardActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }   // End of ElseIf Statement

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }  // End of onNavigationItemSelected


    // Go to PointSchedule Activity
    public void onPointSchedule(View view){
        Intent intentps = new Intent(this, PointSchedule.class);
        startActivity(intentps);
    } // onPointSchedule


    // Go to About Activity
    public void onABout(View view){
        Intent intentas = new Intent(this, AboutUs.class);
        startActivity(intentas);
    }

    // Go to About Activity
    public void onTrackVehicle(View view){
        Intent intenttv = new Intent(this, TrackVehicle.class);
        startActivity(intenttv);
    }

    // Go to About Activity
    public void onTransportDetails(View view){
        Intent intenttd = new Intent(this, TransportDetails.class);
        startActivity(intenttd);
    }

}  // End of DashboardActivity