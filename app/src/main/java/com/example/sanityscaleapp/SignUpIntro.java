package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpIntro extends AppCompatActivity {
    Button nextBtn;
    String name,email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_intro);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
            name=bundle.getString("name");
            email=bundle.getString("email");
            password=bundle.getString("password");
        }


        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpIntro.this, SignUpUnits.class);
                intent.putExtra("name",name);
                intent.putExtra("email",email);
                intent.putExtra("password",password);
                SignUpIntro.this.startActivity(intent);

            }
        });

    }
}
