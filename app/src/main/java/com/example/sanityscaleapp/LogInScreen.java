package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;

public class LogInScreen extends AppCompatActivity {
    Button nextBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);

        nextBtn=findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LogInScreen.this, HomeScreen.class);
                LogInScreen.this.startActivity(intent);

            }
        });

        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LogInScreen.this, MainActivity.class);
                LogInScreen.this.startActivity(intent);

            }
        });
    }


}
