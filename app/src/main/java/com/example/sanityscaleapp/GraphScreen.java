package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

public class GraphScreen extends AppCompatActivity {
    Button backBtn, menuBtn;
    private float weeklyAverage;
    private int USERID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_screen);
        weeklyAverage= getIntent().getExtras().getFloat("weeklyavg");
        USERID=getIntent().getExtras().getInt("USERID");
        TextView avgWeightTextView = findViewById(R.id.AvgWeightTextView);
        avgWeightTextView.append(Float.toString(weeklyAverage));

        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GraphScreen.this, HomeScreen.class);
                intent.putExtra("USERID", USERID);
                GraphScreen.this.startActivity(intent);

            }
        });

        menuBtn=findViewById(R.id.menuBtn);
        menuBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GraphScreen.this, SettingsScreen.class);
                intent.putExtra("USERID", USERID);
                intent.putExtra("backScreen", "graphScreen");
                GraphScreen.this.startActivity(intent);

            }
        });
    }


}