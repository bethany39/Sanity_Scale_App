package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;


public class Goals extends AppCompatActivity {
    Button backBtn, gainBtn,loseBtn, maintainBtn;
    ImageView bluebuttonm, bluebuttong,bluebuttonl;
    private int USERID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        USERID = getIntent().getExtras().getInt("USERID");

        setContentView(R.layout.activity_goals);
        //properly setting them to whatever the person clicked...
        bluebuttonm=findViewById(R.id.bluebuttonm);
        //bluebuttonl=findViewById(R.id.bluebuttonl);
        // bluebuttong=findViewById(R.id.bluebuttonkg);
        bluebuttong.setVisibility(View.INVISIBLE);
        bluebuttonl.setVisibility(View.INVISIBLE);


        gainBtn=findViewById(R.id.gainBtn);
        gainBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Intent intent =new Intent(Goals.this, GoalsGain.class);
               // Goals.this.startActivity(intent);
                bluebuttong.setVisibility(View.VISIBLE);
                bluebuttonm.setVisibility(View.INVISIBLE);
                bluebuttonl.setVisibility(View.INVISIBLE);

            }
        });
        loseBtn=findViewById(R.id.loseBtn);
        loseBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
              //  Intent intent =new Intent(Goals.this, GoalsLose.class);
              //  Goals.this.startActivity(intent);
                bluebuttonl.setVisibility(View.VISIBLE);
                bluebuttonm.setVisibility(View.INVISIBLE);
                bluebuttong.setVisibility(View.INVISIBLE);

            }
        });

        maintainBtn=findViewById(R.id.maintainBtn);
        maintainBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //  Intent intent =new Intent(Goals.this, GoalsLose.class);
                //  Goals.this.startActivity(intent);
                bluebuttonm.setVisibility(View.VISIBLE);
                bluebuttonl.setVisibility(View.INVISIBLE);
                bluebuttong.setVisibility(View.INVISIBLE);

            }
        });

        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Goals.this, SettingsScreen.class);
                intent.putExtra("USERID", USERID);
                Goals.this.startActivity(intent);
            }
        });
    }

}
