package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;


public class ChangeUnits2 extends AppCompatActivity {
    Button lbsBtn, kgsBtn,backBtn;
    ImageView bluebuttonlb, bluebuttonkg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_units2);
        bluebuttonlb=findViewById(R.id.bluebuttonlb);
        // bluebuttonkg=findViewById(R.id.bluebuttonkg);
        bluebuttonkg.setVisibility(View.INVISIBLE);


        kgsBtn=findViewById(R.id.kgsBtn);
        kgsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               // Intent intent =new Intent(ChangeUnits2.this, ChangeUnitsKgs2.class);
               // ChangeUnits2.this.startActivity(intent);
                bluebuttonlb.setVisibility(View.INVISIBLE);
                bluebuttonkg.setVisibility(View.VISIBLE);



            }
        });
        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ChangeUnits2.this, GraphScreen.class);
                ChangeUnits2.this.startActivity(intent);

            }
        });
    }

}
