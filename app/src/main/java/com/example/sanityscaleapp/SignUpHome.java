package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpHome extends AppCompatActivity {
    Button backBtn,nextBtn;
    EditText name, email, email2, password, password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_home);

        name = findViewById(R.id.nameBox);
        email=findViewById(R.id.emailbox_signup);
        email2=findViewById(R.id.confirmemailbox);
        password = findViewById(R.id.passwordbox_signup);
        password2=findViewById(R.id.confirmpassword);

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpHome.this, MainActivity.class);
                SignUpHome.this.startActivity(intent);

            }
        });

        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                Intent intent = new Intent(SignUpHome.this, SignUpIntro.class);
                if(email!=null && name!=null && password!=null) {
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("email", email.getText().toString());
                    intent.putExtra("password", password.getText().toString());

                }
                SignUpHome.this.startActivity(intent);

            }
        });


    }
}
