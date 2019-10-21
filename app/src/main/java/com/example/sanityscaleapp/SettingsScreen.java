package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;

public class SettingsScreen extends AppCompatActivity {
    Button backBtn, changeUnitsBtn,goalsBtn,profileBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SettingsScreen.this, HomeScreen.class);
                SettingsScreen.this.startActivity(intent);

            }
        });
        changeUnitsBtn=findViewById(R.id.changeUnitsBtn);
        changeUnitsBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SettingsScreen.this, ChangeUnits.class);
                SettingsScreen.this.startActivity(intent);

            }
        });

        goalsBtn=findViewById(R.id.goalsBtn);
        goalsBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SettingsScreen.this, Goals.class);
                SettingsScreen.this.startActivity(intent);

            }
        });



    }


}