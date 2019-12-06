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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInScreen extends AppCompatActivity {
    Button nextBtn, backBtn;
    TextView errorMessage;
    TextView error2;
    private String SESSIONID;
    CountingIdlingResource IdlingResource = new CountingIdlingResource("LOGIN");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_screen);
        errorMessage = findViewById(R.id.logInError);
        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new OnClickListener() {



            @Override
            public void onClick(View v) {
                EspressoIdlingResource.increment();

                EditText email = findViewById(R.id.emailBox);
                EditText password = findViewById(R.id.passwordBox);

                IUserController userService = RetrofitApi.getInstance().getUserService();

                    User userToSend = new User(email.getText().toString().toLowerCase(), password.getText().toString());

                    Call<User> call = userService.getUser(userToSend);


                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (!response.isSuccessful()) {
                                Log.d("UserController", "inside if in onResponse");
                                errorMessage.setVisibility(findViewById(R.id.logInScreen).VISIBLE);
                                EspressoIdlingResource.decrement();
                                return;

                            }
                            Log.d("UserController", "outside if in onResponse");
                            User user = response.body();
                            SESSIONID = user.getSessionId();

                            goToHomeScreen();
                            errorMessage.setVisibility(findViewById(R.id.logInScreen).INVISIBLE);
                            EspressoIdlingResource.decrement();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
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
        Intent intent = new Intent(getBaseContext(), HomeScreen.class);
        intent.putExtra("SESSIONID",SESSIONID);
        LogInScreen.this.startActivity(intent);

    }
    public CountingIdlingResource getEspressoIdlingResourceForMainActivity() {
        return IdlingResource;
    }
}
