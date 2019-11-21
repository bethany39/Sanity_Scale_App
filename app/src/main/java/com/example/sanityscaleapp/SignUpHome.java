package com.example.sanityscaleapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpHome extends AppCompatActivity {
    Button backBtn,nextBtn;
    EditText name, email, email2, password, password2;
    TextView errorMessage,errorMessage2,errorMessage3,errorMessage4,errorMessage5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_home);

        name = findViewById(R.id.nameBox);
        email=findViewById(R.id.emailbox_signup);
        email2=findViewById(R.id.confirmemailbox);
        password = findViewById(R.id.passwordbox_signup);
        password2=findViewById(R.id.confirmpassword);
        errorMessage = findViewById(R.id.emailError);
        errorMessage2=findViewById(R.id.passwordError);
        errorMessage3=findViewById(R.id.emailandpassError);
        errorMessage4=findViewById(R.id.blankError);
        errorMessage5=findViewById(R.id.emailexistsError);


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

                if (email.getText().toString().equals("") || name.getText().toString().equals("") || password.getText().toString().equals("") || email2.getText().toString().equals("") || password2.getText().toString().equals("")) {
                    System.out.println("Correctly checks for empty boxes");
                    errorMessage4.setVisibility(findViewById(R.id.logInScreen).VISIBLE);
                }
                //if(email!=null && name!=null && password!=null) {
                else{


                    IUserController userService = RetrofitApi.getInstance().getUserService();

                    Call<APIResponse> call = userService.getEmail(email.getText().toString());


                    call.enqueue(new Callback<APIResponse>() {
                        @Override
                        public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                            if (!response.isSuccessful()) {
                                //should do something for the error handlign
                                Log.d("UserController", "inside if in onResponse");
                                System.out.println("the response is" + response);
                                EspressoIdlingResource.decrement();
                                return;
                            }
                            Log.d("UserController", "outside if in onResponse");
                            Boolean emailTest = response.body().getMessage();

                            if (emailTest == true) {
                                errorMessage5.setVisibility(findViewById(R.id.logInScreen).VISIBLE);
                            } else {
                                Intent intent = new Intent(SignUpHome.this, SignUpIntro1.class);
                                intent.putExtra("name", name.getText().toString());
                                intent.putExtra("email", email.getText().toString());
                                intent.putExtra("password", password.getText().toString());


                                if (!email.getText().toString().equals(email2.getText().toString()) && !password.getText().toString().equals(password2.getText().toString())) {
                                    System.out.println("email and pass don't match");
                                    errorMessage3.setVisibility(findViewById(R.id.logInScreen).VISIBLE);
                                } else if (!email.getText().toString().equals(email2.getText().toString())) {
                                    System.out.println("emails don't match");
                                    errorMessage.setVisibility(findViewById(R.id.logInScreen).VISIBLE);
                                } else if (!password.getText().toString().equals(password2.getText().toString())) {
                                    System.out.println("passwords don't match");
                                    errorMessage2.setVisibility(findViewById(R.id.logInScreen).VISIBLE);
                                }


                                if (email.getText().toString().equals(email2.getText().toString()) && password.getText().toString().equals(password2.getText().toString())) {


                                    SignUpHome.this.startActivity(intent);

                                }

                            }
                        }

                        @Override
                        public void onFailure(Call<APIResponse> call, Throwable t) {
                            Log.d("UserController", "inside onFailure");
                        }
                    });


                 /*   else {
                        if (!email.getText().toString().equals(email2.getText().toString()) && !password.getText().toString().equals(password2.getText().toString())) {
                            System.out.println("email and pass don't match");
                            errorMessage3.setVisibility(findViewById(R.id.logInScreen).VISIBLE);
                        } else if (!email.getText().toString().equals(email2.getText().toString())) {
                            System.out.println("emails don't match");
                            errorMessage.setVisibility(findViewById(R.id.logInScreen).VISIBLE);
                        } else if (!password.getText().toString().equals(password2.getText().toString())) {
                            System.out.println("passwords don't match");
                            errorMessage2.setVisibility(findViewById(R.id.logInScreen).VISIBLE);
                        }


                        if (email.getText().toString().equals(email2.getText().toString()) && password.getText().toString().equals(password2.getText().toString())) {
                            System.out.println("everything works, yay");
                            SignUpHome.this.startActivity(intent);
                        }
                    }*/
                }


            }
        });


    }
}
