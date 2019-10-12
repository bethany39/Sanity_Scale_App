package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;


public class HomeWMenuScreen extends AppCompatActivity {
    Button settingsBtn, logOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_wmenu_screen);

        settingsBtn=findViewById(R.id.settingsBtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeWMenuScreen.this, SettingsScreen.class);
                HomeWMenuScreen.this.startActivity(intent);

            }
        });
        logOutBtn=findViewById(R.id.logOutBtn);
        logOutBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeWMenuScreen.this, LogoutHome.class);
                HomeWMenuScreen.this.startActivity(intent);

            }
        });
    }
}
