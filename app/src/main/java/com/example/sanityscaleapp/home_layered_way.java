package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home_layered_way extends AppCompatActivity {
    Button settingsBtn, logOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layered_way);

        settingsBtn=findViewById(R.id.settingsBtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(home_layered_way.this, SettingsScreen.class);
                home_layered_way.this.startActivity(intent);

            }
        });
        logOutBtn=findViewById(R.id.logOutBtn);
        logOutBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(home_layered_way.this, LogoutHome.class);
                home_layered_way.this.startActivity(intent);

            }
        });
    }
}
