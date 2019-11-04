package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.test.espresso.idling.CountingIdlingResource;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogInScreen extends AppCompatActivity {
    Button nextBtn, backBtn;
    private int USERID;
    CountingIdlingResource IdlingResource = new CountingIdlingResource("LOGIN");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);

        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new OnClickListener() {



            @Override
            public void onClick(View v) {
                //IdlingResource.increment();
                EspressoIdlingResource.increment();

                EditText email = (EditText) findViewById(R.id.emailBox);
                EditText password = (EditText) findViewById(R.id.passwordBox);

                IUserController userService = RetrofitApi.getInstance().getUserService();
                //UserController userController = new UserController();

                Call<User> call = userService.getUser(email.getText().toString(), password.getText().toString());
                //Call<User> call = userService.getUser("david@gmail.com", "davidmayes");

                call.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(!response.isSuccessful()){
                            //should do something for the error handlign
                            Log.d("UserController", "inside if in onResponse");
                            return;

                        }
                        Log.d("UserController", "outside if in onResponse");
                        User user = response.body();
                        USERID = user.getUserId();
                        goToHomeScreen();
                        //IdlingResource.decrement();
                        EspressoIdlingResource.decrement();
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        Log.d("UserController", "inside onFailure");
                        //IdlingResource.decrement();
                        EspressoIdlingResource.decrement();

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
        Intent intent = new Intent(getBaseContext(), HomeScreen.class);
        intent.putExtra("USERID", USERID);
        LogInScreen.this.startActivity(intent);

    }
    public CountingIdlingResource getEspressoIdlingResourceForMainActivity() {
        return IdlingResource;
    }


}
