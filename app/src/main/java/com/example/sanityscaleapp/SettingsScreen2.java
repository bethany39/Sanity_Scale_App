package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;

public class SettingsScreen2 extends AppCompatActivity {
    Button backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_screen);

        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SettingsScreen2.this, GraphScreen.class);
                SettingsScreen2.this.startActivity(intent);

            }
        });


    }


}