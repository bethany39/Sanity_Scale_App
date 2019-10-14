package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;





public class GoalsGain2 extends AppCompatActivity {
    Button backBtn, maintainBtn,loseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_gain2);


        maintainBtn=findViewById(R.id.maintainBtn);
        maintainBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GoalsGain2.this, Goals2.class);
                GoalsGain2.this.startActivity(intent);

            }
        });
        loseBtn=findViewById(R.id.loseBtn);
        loseBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GoalsGain2.this, GoalsLose2.class);
                GoalsGain2.this.startActivity(intent);

            }
        });
        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GoalsGain2.this, SettingsScreen2.class);
                GoalsGain2.this.startActivity(intent);

            }
        });
    }

}
