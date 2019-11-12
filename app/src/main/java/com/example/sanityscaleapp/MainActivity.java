package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {
    Button logInBtn,signUpBtn;
    //public UserController userController;
    //public WeightsController weightsController;
    public int userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logInBtn=findViewById(R.id.logInBtn);
        logInBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, LogInScreen.class);
                MainActivity.this.startActivity(intent);

//                MainActivity.this.weightsController = new WeightsController(userController.USER_ID);

            }
        });

        signUpBtn=findViewById(R.id.signUpBtn);
        signUpBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MainActivity.this, SignUpHome.class);
                MainActivity.this.startActivity(intent);
            }
        });

    }
    public void setUserID(int newID){
        userID=newID;
    }

    public int getUserID() {
        return this.userID;
    }


}
