package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpGoals extends AppCompatActivity {
    Button gainBtn,loseBtn,maintainBtn;
    String name,email,password, units;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_goals);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
            name=bundle.getString("name");
            email=bundle.getString("email");
            password=bundle.getString("password");
            units=bundle.getString("units");
        }

        maintainBtn = findViewById(R.id.maintainBtn);
        maintainBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(SignUpGoals.this, SignUpMessage.class);
                createUser(email,password,name,"maintain weight", units);
               // SignUpGoals.this.startActivity(intent);
            }
        });

        loseBtn = findViewById(R.id.loseBtn);
        loseBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(SignUpGoals.this, SignUpMessage.class);
                createUser(email,password,name,"lose weight", units);
                //SignUpGoals.this.startActivity(intent);
            }
        });

        gainBtn = findViewById(R.id.gainBtn);
        gainBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(SignUpGoals.this, SignUpMessage.class);
                createUser(email,password,name,"gain weight", units);

               // SignUpGoals.this.startActivity(intent);
            }
        });

    }


    public void createUser(String email, String password, String firstname, String goal, String units){
        IUserController userService=RetrofitApi.getInstance().getUserService();
        User newUser=new User(email,password,firstname,goal,units);
        Call<okhttp3.ResponseBody> call =userService.createUser(newUser);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response){
                if(!response.isSuccessful()){
                    Log.d("UserController","inside if in onResponse");
                    EspressoIdlingResource.decrement();
                    return;
                }
                Log.d("UserController","outside if in onResponse");
                ResponseBody user=response.body();
                Intent intent = new Intent(SignUpGoals.this, SignUpMessage.class);
                SignUpGoals.this.startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t){
                Log.d("UserController","inside on Failure");
            }
        });



    }


}
