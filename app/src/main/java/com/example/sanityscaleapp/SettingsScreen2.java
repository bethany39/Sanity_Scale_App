package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;

public class SettingsScreen2 extends AppCompatActivity {
    Button backBtn, changeUnitsBtn,profileBtn,goalsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen2);

        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SettingsScreen2.this, GraphWithSideMenu.class);
                SettingsScreen2.this.startActivity(intent);

            }
        });

        changeUnitsBtn=findViewById(R.id.changeUnitsBtn);
        changeUnitsBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SettingsScreen2.this, ChangeUnits2.class);
                SettingsScreen2.this.startActivity(intent);

            }
        });

        goalsBtn=findViewById(R.id.goalsBtn);
        goalsBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SettingsScreen2.this, Goals2.class);
                SettingsScreen2.this.startActivity(intent);

            }
        });

    }


}