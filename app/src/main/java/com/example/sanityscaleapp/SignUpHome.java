package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpHome extends AppCompatActivity {
    Button backBtn,nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_home);

        //have to add api call to add data to database

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpHome.this, MainActivity.class);
                SignUpHome.this.startActivity(intent);

            }
        });

        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpHome.this, SignUpIntro.class);
                SignUpHome.this.startActivity(intent);

            }
        });


    }
}
