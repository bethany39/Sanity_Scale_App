package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

import com.google.gson.GsonBuilder;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogInScreen extends AppCompatActivity {
    Button nextBtn, backBtn;

    //public final UserController userController = new UserController();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);

        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {


                EditText email = (EditText) findViewById(R.id.emailBox);
                EditText password = (EditText) findViewById(R.id.passwordBox);
                Retrofit retrofit = new Retrofit.Builder().baseUrl("https://sanity-scale-api.herokuapp.com/")
                        .addConverterFactory(GsonConverterFactory.create(
                                new GsonBuilder().setPrettyPrinting().create()))
                        .build();
                IUserController iUserController = retrofit.create(IUserController.class);
                //UserController userController = new UserController();
                //boolean success = userController.getUser(email.getText().toString(), password.getText().toString(), LogInScreen.this);
                Call<Response> call = iUserController.getUser(email.getText().toString(), password.getText().toString());

                call.enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, Response<Response> response) {
                        if(!response.isSuccessful()){
                            //should do something for the error handlign
                            Log.d("UserController", "inside if in onResponse");
                            return;

                        }
                        Log.d("UserController", "outside if in onResponse");
                        Response rb = response;
                        Intent intent = new Intent(LogInScreen.this, HomeScreen.class);
                        LogInScreen.this.startActivity(intent);

                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {
                        Log.d("UserController", "inside onFailure");

                    }
                });
            }
        });

        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInScreen.this, MainActivity.class);
                LogInScreen.this.startActivity(intent);

            }
        });
    }

    public void goToHomeScreen() {
        Intent intent = new Intent(LogInScreen.this, HomeScreen.class);
        LogInScreen.this.startActivity(intent);
    }


}
