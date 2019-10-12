package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;

public class LogoutHome extends AppCompatActivity {
    Button cancelBtn, logOutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout_home);

        cancelBtn=findViewById(R.id.cancelBtn);
        cancelBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LogoutHome.this, HomeWMenuScreen.class);
                LogoutHome.this.startActivity(intent);

            }
        });
        logOutBtn=findViewById(R.id.logOutBtn);
        logOutBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LogoutHome.this, MainActivity.class);
                LogoutHome.this.startActivity(intent);

            }
        });
    }

}
