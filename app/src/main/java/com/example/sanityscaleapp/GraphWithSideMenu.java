package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GraphWithSideMenu extends AppCompatActivity {
    Button settingsBtn, logOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_with_side_menu);

        settingsBtn=findViewById(R.id.settingsBtn);
        settingsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GraphWithSideMenu.this, SettingsScreen2.class);
               GraphWithSideMenu.this.startActivity(intent);

            }
        });
        logOutBtn=findViewById(R.id.logOutBtn);
        logOutBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GraphWithSideMenu.this, LogoutHome.class);
                GraphWithSideMenu.this.startActivity(intent);

            }
        });
    }
}
