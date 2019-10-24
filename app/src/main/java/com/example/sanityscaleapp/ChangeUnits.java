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
        import com.google.gson.JsonDeserializer;

        import okhttp3.ResponseBody;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;


public class ChangeUnits extends AppCompatActivity {
    Button lbsBtn, kgsBtn,backBtn;
    ImageView bluebuttonlb, bluebuttonkg;
    private int USERID;
    private String UNITS;
    IUserController userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_units);
        bluebuttonlb=findViewById(R.id.bluebuttonlb);
        bluebuttonkg=findViewById(R.id.bluebuttonkg);
        //bluebuttonkg.setVisibility(View.INVISIBLE);
        USERID = getIntent().getExtras().getInt("USERID");
        UNITS=getIntent().getExtras().getString("selected");
        userService = RetrofitApi.getInstance().getUserService();

        if (UNITS.equals("pounds"))
        {
            bluebuttonlb.setVisibility(View.VISIBLE);
            bluebuttonkg.setVisibility(View.INVISIBLE);
        }
        else if (UNITS.equals("kgs"))
        {
            bluebuttonlb.setVisibility(View.INVISIBLE);
            bluebuttonkg.setVisibility(View.VISIBLE);

        }


        kgsBtn=findViewById(R.id.kgsBtn);
        kgsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               // Intent intent =new Intent(ChangeUnits.this, ChangeUnitsKgs.class);
               // ChangeUnits.this.startActivity(intent);

                String userJson = "{'unit': 'kgs'}";
                Gson gson = new Gson();
                User userObject = gson.fromJson(userJson, User.class);
                Call<ResponseBody> changeToKgsCall = userService.patchUserUnits(USERID, userObject);
                changeToKgsCall.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(!response.isSuccessful()){
                            //should do something for the error handlign
                            Log.d("UserController", "inside if in onResponse");
                            return;

                        }
                        //System.out.println(response);
                        //System.out.println(response.body());
                        //ResponseBody jsonResponse = response.body();
                        //User user = response.body();
                        //String unit = user.getUnit();
                        //System.out.println(response.body());
                        bluebuttonlb.setVisibility(View.INVISIBLE);
                        bluebuttonkg.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("UserController", "inside onFailure");

                    }
                });


            }
        });

        lbsBtn=findViewById(R.id.lbsBtn);
        lbsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Intent intent =new Intent(ChangeUnits.this, ChangeUnitsKgs.class);
                // ChangeUnits.this.startActivity(intent);
                bluebuttonkg.setVisibility(View.INVISIBLE);
                bluebuttonlb.setVisibility(View.VISIBLE);

            }
        });

        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ChangeUnits.this, HomeScreen.class);
                intent.putExtra("USERID", USERID);
                ChangeUnits.this.startActivity(intent);

            }
        });
    }

}
