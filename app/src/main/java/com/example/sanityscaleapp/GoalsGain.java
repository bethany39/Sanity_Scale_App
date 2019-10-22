package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;





public class GoalsGain extends AppCompatActivity {
    Button backBtn, maintainBtn,loseBtn;
    private int USERID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goals_gain);
        USERID = getIntent().getExtras().getInt("USERID");


        maintainBtn=findViewById(R.id.maintainBtn);
        maintainBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GoalsGain.this, Goals.class);
                GoalsGain.this.startActivity(intent);

            }
        });
        loseBtn=findViewById(R.id.loseBtn);
        loseBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GoalsGain.this, GoalsLose.class);
                GoalsGain.this.startActivity(intent);

            }
        });
        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GoalsGain.this, SettingsScreen.class);
                intent.putExtra("USERID", USERID);
                GoalsGain.this.startActivity(intent);

            }
        });
    }

}
