package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;

import com.google.gson.Gson;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Goals extends AppCompatActivity {
    Button backBtn, gainBtn,loseBtn, maintainBtn;
    ImageView bluebuttonm, bluebuttong,bluebuttonl;
    private int USERID;
    private String goal;
    IUserController userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        USERID = getIntent().getExtras().getInt("USERID");
        goal = getIntent().getExtras().getString("selected");
        setContentView(R.layout.activity_goals);
        userService = RetrofitApi.getInstance().getUserService();

        //properly setting them to whatever the person clicked...
        bluebuttonm=findViewById(R.id.bluebuttonm);
        bluebuttonl=findViewById(R.id.bluebuttonl);
        bluebuttong=findViewById(R.id.bluebuttong);

        if(goal.equals("maintain")){
            selectMaintain(findViewById(R.id.activityGoals));
        } else if(goal.equals("lose")){
            selectLose(findViewById(R.id.activityGoals));
        } else if(goal.equals("gain")){
            selectGain(findViewById(R.id.activityGoals));
        } else{
            Log.e("Goals", "we got a problem");
        }

        gainBtn=findViewById(R.id.gainBtn);
        gainBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userJson = "{'goal': 'gain weight'}";
                Gson gson = new Gson();
                User userObject = gson.fromJson(userJson, User.class);
                Call<ResponseBody> gainCall = userService.patchUserGoal(USERID, userObject);
                gainCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(!response.isSuccessful()) {
                            //should do something for the error handlign
                            Log.d("UserController", "inside if in onResponse");
                            return;

                        }
                        selectGain(findViewById(R.id.activityGoals));
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("UserController", "inside onFailure");

                    }
                });
               // selectGain(v);
            }
        });
        loseBtn=findViewById(R.id.loseBtn);
        loseBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userJson = "{'goal': 'lose weight'}";
                Gson gson = new Gson();
                User userObject = gson.fromJson(userJson, User.class);
                Call<ResponseBody> loseCall = userService.patchUserGoal(USERID, userObject);
                loseCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(!response.isSuccessful()) {
                            //should do something for the error handlign
                            Log.d("UserController", "inside if in onResponse");
                            return;

                        }
                        selectLose(findViewById(R.id.activityGoals));
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("UserController", "inside onFailure");

                    }
                });
                //selectLose(v);

            }
        });

        maintainBtn=findViewById(R.id.maintainBtn);
        maintainBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userJson = "{'goal': 'maintain weight'}";
                Gson gson = new Gson();
                User userObject = gson.fromJson(userJson, User.class);
                Call<ResponseBody> maintainCall = userService.patchUserGoal(USERID, userObject);
                maintainCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(!response.isSuccessful()) {
                            //should do something for the error handlign
                            Log.d("UserController", "inside if in onResponse");
                            return;

                        }
                        selectMaintain(findViewById(R.id.activityGoals));
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("UserController", "inside onFailure");

                    }
                });
                //selectMaintain(v);
            }
        });

        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Goals.this, SettingsScreen.class);
                intent.putExtra("USERID", USERID);
                Goals.this.startActivity(intent);
            }
        });


    }
    public void selectLose(View v){
        bluebuttonl.setVisibility(v.VISIBLE);
        bluebuttonm.setVisibility(v.INVISIBLE);
        bluebuttong.setVisibility(v.INVISIBLE);
    }
    public void selectGain(View v){
        bluebuttong.setVisibility(v.VISIBLE);
        bluebuttonm.setVisibility(v.INVISIBLE);
        bluebuttonl.setVisibility(v.INVISIBLE);
    }

    public void selectMaintain(View v) {
        bluebuttonm.setVisibility(v.VISIBLE);
        bluebuttonl.setVisibility(v.INVISIBLE);
        bluebuttong.setVisibility(v.INVISIBLE);
    }

}
