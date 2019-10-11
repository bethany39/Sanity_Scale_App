package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;

public class HomeScreen extends AppCompatActivity {
    Button weeklyAvgBtn, settingsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        weeklyAvgBtn=findViewById(R.id.weeklyAvgBtn);
        weeklyAvgBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeScreen.this, GraphScreen.class);
                HomeScreen.this.startActivity(intent);

            }
        });

        settingsBtn=findViewById(R.id.settingsBtn);
        settingsBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeScreen.this, SettingsScreen.class);
                HomeScreen.this.startActivity(intent);

            }
        });
    }


}