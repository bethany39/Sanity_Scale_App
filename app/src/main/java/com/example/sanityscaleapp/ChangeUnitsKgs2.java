package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;




public class ChangeUnitsKgs2 extends AppCompatActivity {
    Button lbsBtn, kgsBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_units_kgs2);


        lbsBtn=findViewById(R.id.lbsBtn);
        lbsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ChangeUnitsKgs2.this, ChangeUnits.class);
                ChangeUnitsKgs2.this.startActivity(intent);

            }
        });

        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ChangeUnitsKgs2.this, GraphScreen.class);
                ChangeUnitsKgs2.this.startActivity(intent);

            }
        });
    }
}
