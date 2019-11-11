package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpIntro extends AppCompatActivity {
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_intro);

        //do I have to pass the database stuff along? or I'm just adding it

        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpIntro.this, SignUpUnits.class);
                SignUpIntro.this.startActivity(intent);

            }
        });

    }
}
