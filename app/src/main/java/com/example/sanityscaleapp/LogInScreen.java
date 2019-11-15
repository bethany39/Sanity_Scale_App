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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.UUID;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LogInScreen extends AppCompatActivity {
    Button nextBtn, backBtn;
    TextView errorMessage;
 //   private int USERID;
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
               
                    User userToSend = new User(email.getText().toString(), password.getText().toString());

                    //Gson gson = new Gson();

                    //User userObject = gson.fromJson(userToSend, User.class);

                    Call<User> call = userService.getUser(userToSend);


                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            if (!response.isSuccessful()) {
                                //should do something for the error handlign
                                Log.d("UserController", "inside if in onResponse");
                                System.out.println("the response is" + response);
                                errorMessage.setVisibility(findViewById(R.id.logInScreen).VISIBLE);
                                EspressoIdlingResource.decrement();
                                return;

                            }
                            Log.d("UserController", "outside if in onResponse");
                            User user = response.body();
                            System.out.println("the response body is " + user);
                            //   USERID = user.getUserId();
                            //   SESSIONID= UUID.randomUUID().toString();
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
     //   intent.putExtra("USERID", USERID);
        intent.putExtra("SESSIONID",SESSIONID);
        LogInScreen.this.startActivity(intent);

    }
    public CountingIdlingResource getEspressoIdlingResourceForMainActivity() {
        return IdlingResource;
    }


}
