package com.example.sanityscaleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GraphScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private float weeklyAverage;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigation;
    private String SESSIONID;
    private String UNITS;
    private LineChart mpLineChart;
    private Button onemonthBtn, threemonthsBtn, alltimeBtn, fourmonthsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_screen);
        weeklyAverage= getIntent().getExtras().getFloat("weeklyavg");
        SESSIONID=getIntent().getExtras().getString("SESSIONID");
        UNITS = getIntent().getExtras().getString("UNITS");

        mpLineChart = findViewById(R.id.weightGraph);
        TextView avgWeightTextView = findViewById(R.id.AvgWeightTextView);
        avgWeightTextView.append(Float.toString(weeklyAverage));
        avgWeightTextView.append(" "+UNITS);




        drawerLayout= findViewById(R.id.graphScreen);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);


        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=(NavigationView) findViewById(R.id.nav_view3);
        navigationView.setNavigationItemSelectedListener(this);

        /*
            will have this call to set it with "fallback" range, and then for the various time buttons,
            the on click listeners will have later calls to setdata with new parameters based on their time range.

            Both this call and the button listeners will need to have api calls attached to get the correct data from the db.

            Before calling setData with the data, the Entries must be in sorted order by the X column (date) or the graph could have unexpected behavior.

            In order to plot the weekly average on the graph, it will likely have to be in a different LineDataSet so that it can be differentiated
            and have different styles applied than the regular plotted points.
         */

        onemonthBtn=findViewById(R.id.onemonthBtn);
        onemonthBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getWeightTimes("onemonth");
            }

            });

        threemonthsBtn=findViewById(R.id.threemonthsBtn);
        threemonthsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getWeightTimes("threemonths");
            }

        });

        alltimeBtn=findViewById(R.id.alltimeBtn);
        alltimeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getWeightTimes("alltime");
            }

        });

        //should we include 4 months btn right away?
        fourmonthsBtn=findViewById(R.id.fourmonthsBtn);
        fourmonthsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getWeightTimes("fourmonths");
            }

        });





        setData();

        XAxis xAxis = mpLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        mpLineChart.setDrawGridBackground(false);
        mpLineChart.getXAxis().disableGridDashedLine();
        mpLineChart.getXAxis().setDrawGridLines(false);
        mpLineChart.getAxisLeft().disableGridDashedLine();
        mpLineChart.getAxisLeft().setDrawGridLines(false);
        mpLineChart.getAxisRight().setEnabled(false);
        mpLineChart.getAxisRight().setDrawGridLines(false);


    }

    private void setData(){
        ArrayList<Entry> values = new ArrayList<>();
        values.add(new Entry(0, 123));
        values.add(new Entry(1, 124));
        values.add(new Entry(2, 125));
        values.add(new Entry(3, 126));
        values.add(new Entry(4, 124));
        values.add(new Entry(5, 121));
        values.add(new Entry(6, 122));


        LineDataSet lds = new LineDataSet(values, "weights");

        lds.setColor(Color.BLACK);
        lds.setCircleColor(Color.BLACK);
        lds.setDrawValues(false);
        lds.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        List<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(lds);
        LineData data = new LineData(dataSets);
        mpLineChart.setData(data);
        mpLineChart.invalidate();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(toggle.onOptionsItemSelected(item)){
            return true;
        }
        else {
            return super.onOptionsItemSelected(item);
        }

    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.nav_home:
                Intent intent=new Intent(GraphScreen.this,HomeScreen.class);
            //    intent.putExtra("USERID", USERID);
                intent.putExtra("SESSIONID",SESSIONID);
                startActivity(intent);
                break;
            case R.id.nav_settings:
                Intent intent2=new Intent(GraphScreen.this,SettingsScreen.class);
            //    intent2.putExtra("USERID", USERID);
                intent2.putExtra("SESSIONID",SESSIONID);
                startActivity(intent2);
                break;
            case R.id.nav_logout:
                Intent intent3=new Intent(GraphScreen.this,LogoutHome.class);
            //    intent3.putExtra("USERID", USERID);
                intent3.putExtra("SESSOINID",SESSIONID);
                startActivity(intent3);
                break;
        }
        return true;
    }

    public void getWeightTimes(String time){
        IWeightsController weightsService = RetrofitApi.getInstance().getWeightsService();
        Call<Weight> weightsCall = weightsService.getAverageWeight(SESSIONID,time);
        //default time is 4 months

        weightsCall.enqueue(new Callback<Weight>() {
            @Override
            public void onResponse(Call<Weight> call, Response<Weight> response) {
                if (!response.isSuccessful()) {
                    //should do something for the error handling
                    Log.d("WeightsController", "inside if in onResponse");
                    return;

                }
                Log.d("WeightsController", "outside if in onResponse");
                Weight avg = response.body();
                weeklyAverage = avg.getWeeklyAverage();


                EspressoIdlingResource.decrement();

            }

            @Override
            public void onFailure(Call<Weight> call, Throwable t) {
                Log.d("WeightsController", "inside onFailure");
                EspressoIdlingResource.decrement();

            }
        });
    }

}