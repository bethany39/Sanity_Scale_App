package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;





public class GoalsLose2 extends AppCompatActivity {
    Button backBtn, maintainBtn,gainBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_lose2);


        maintainBtn=findViewById(R.id.maintainBtn);
        maintainBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GoalsLose2.this, Goals2.class);
                GoalsLose2.this.startActivity(intent);

            }
        });
        maintainBtn=findViewById(R.id.maintainBtn);
        maintainBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GoalsLose2.this, GoalsGain2.class);
                GoalsLose2.this.startActivity(intent);

            }
        });
        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GoalsLose2.this, SettingsScreen2.class);
                GoalsLose2.this.startActivity(intent);

            }
        });
    }

}
