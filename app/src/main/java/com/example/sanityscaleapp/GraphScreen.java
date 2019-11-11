package com.example.sanityscaleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

public class GraphScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private float weeklyAverage;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigation;
  //  private int USERID;
    private String SESSIONID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_screen);
        weeklyAverage= getIntent().getExtras().getFloat("weeklyavg");
    //    USERID=getIntent().getExtras().getInt("USERID");
        SESSIONID=getIntent().getExtras().getString("SESSIONID");
        TextView avgWeightTextView = findViewById(R.id.AvgWeightTextView);
        avgWeightTextView.append(Float.toString(weeklyAverage));



        drawerLayout= findViewById(R.id.graphScreen);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);


        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=(NavigationView) findViewById(R.id.nav_view3);
        navigationView.setNavigationItemSelectedListener(this);


    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.nav_home:
                Intent intent=new Intent(GraphScreen.this,HomeScreen.class);
            //    intent.putExtra("USERID", USERID);
                intent.putExtra("SESSIONID",SESSIONID);
                startActivity(intent);
                break;
            case R.id.nav_settings:
                Intent intent2=new Intent(GraphScreen.this,SettingsScreen.class);
            //    intent2.putExtra("USERID", USERID);
                intent2.putExtra("SESSIONID",SESSIONID);
                startActivity(intent2);
                break;
            case R.id.nav_logout:
                Intent intent3=new Intent(GraphScreen.this,LogoutHome.class);
            //    intent3.putExtra("USERID", USERID);
                intent3.putExtra("SESSOINID",SESSIONID);
                startActivity(intent3);
                break;
        }
        return true;
    }

}