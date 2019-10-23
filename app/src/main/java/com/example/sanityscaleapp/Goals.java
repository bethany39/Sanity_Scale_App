package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;


public class Goals extends AppCompatActivity {
    Button backBtn, gainBtn,loseBtn, maintainBtn;
    ImageView bluebuttonm, bluebuttong,bluebuttonl;
    private int USERID;
    private String goal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        USERID = getIntent().getExtras().getInt("USERID");
        goal = getIntent().getExtras().getString("selected");
        setContentView(R.layout.activity_goals);

        //properly setting them to whatever the person clicked...
        bluebuttonm=findViewById(R.id.bluebuttonm);
        bluebuttonl=findViewById(R.id.bluebuttonl);
        bluebuttong=findViewById(R.id.bluebuttong);

        if(goal.equals("maintain")){
            selectMaintain(findViewById(R.id.activityGoals));
        } else if(goal.equals("lose")){
            selectLose(findViewById(R.id.activityGoals));
        } else if(goal.equals("gain")){
            selectGain(findViewById(R.id.activityGoals));
        } else{
            Log.e("Goals", "we got a problem");
        }

        gainBtn=findViewById(R.id.gainBtn);
        gainBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selectGain(v);
            }
        });
        loseBtn=findViewById(R.id.loseBtn);
        loseBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selectLose(v);

            }
        });

        maintainBtn=findViewById(R.id.maintainBtn);
        maintainBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                selectMaintain(v);
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
    public void selectLose(View v){
        bluebuttonl.setVisibility(v.VISIBLE);
        bluebuttonm.setVisibility(v.INVISIBLE);
        bluebuttong.setVisibility(v.INVISIBLE);
    }
    public void selectGain(View v){
        bluebuttong.setVisibility(v.VISIBLE);
        bluebuttonm.setVisibility(v.INVISIBLE);
        bluebuttonl.setVisibility(v.INVISIBLE);
    }

    public void selectMaintain(View v) {
        bluebuttonm.setVisibility(v.VISIBLE);
        bluebuttonl.setVisibility(v.INVISIBLE);
        bluebuttong.setVisibility(v.INVISIBLE);
    }

}
