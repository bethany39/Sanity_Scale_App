package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class SignUpGoals extends AppCompatActivity {
    Button gainBtn,loseBtn,maintainBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_goals);

        maintainBtn = findViewById(R.id.maintainBtn);
        maintainBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpGoals.this, SignUpMessage.class);
                SignUpGoals.this.startActivity(intent);
            }
        });

        loseBtn = findViewById(R.id.loseBtn);
        loseBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpGoals.this, SignUpMessage.class);
                SignUpGoals.this.startActivity(intent);
            }
        });

        gainBtn = findViewById(R.id.gainBtn);
        gainBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpGoals.this, SignUpMessage.class);
                SignUpGoals.this.startActivity(intent);
            }
        });

    }
}
