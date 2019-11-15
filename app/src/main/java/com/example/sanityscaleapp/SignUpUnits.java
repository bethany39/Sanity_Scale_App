package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignUpUnits extends AppCompatActivity {
    Button kgsBtn,lbsBtn;
    String name,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_units);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
            name=bundle.getString("name");
            email=bundle.getString("email");
            password=bundle.getString("password");
        }

        kgsBtn = findViewById(R.id.kgsBtn);
        kgsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpUnits.this, SignUpGoals.class);
                intent.putExtra("name",name);
                intent.putExtra("email",email);
                intent.putExtra("password",password);
                intent.putExtra("units","kgs");
                SignUpUnits.this.startActivity(intent);
            }
        });

        lbsBtn = findViewById(R.id.lbsBtn);
        lbsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpUnits.this, SignUpGoals.class);
                intent.putExtra("name",name);
                intent.putExtra("email",email);
                intent.putExtra("password",password);
                intent.putExtra("units","lbs");
                SignUpUnits.this.startActivity(intent);
            }
        });


    }
}
