package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;

public class GraphScreen extends AppCompatActivity {
    Button backBtn, menuBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_screen);

        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GraphScreen.this, HomeScreen.class);
                GraphScreen.this.startActivity(intent);

            }
        });

        menuBtn=findViewById(R.id.menuBtn);
        menuBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(GraphScreen.this, HomeWMenuScreen.class);
                GraphScreen.this.startActivity(intent);

            }
        });
    }


}