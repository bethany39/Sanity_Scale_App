package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpUnits extends AppCompatActivity {
    Button kgsBtn,lbsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_units);

        kgsBtn = findViewById(R.id.kgsBtn);
        kgsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpUnits.this, SignUpGoals.class);
                SignUpUnits.this.startActivity(intent);
            }
        });

        lbsBtn = findViewById(R.id.lbsBtn);
        lbsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpUnits.this, SignUpGoals.class);
                SignUpUnits.this.startActivity(intent);
            }
        });


    }
}
