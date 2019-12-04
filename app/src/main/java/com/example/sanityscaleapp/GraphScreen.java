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
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;

import java.time.temporal.ChronoUnit;
import java.time.LocalDate;


public class GraphScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private float weeklyAverageInLbs;
    private float weeklyAverageInKgs;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigation;
    private String SESSIONID;
    private String UNITS;
    private LineChart mpLineChart;
    private Button onemonthBtn, threemonthsBtn, alltimeBtn, fourmonthsBtn;
    private IWeightsController weightsController;
    private List<Weight> weightsDisplayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_screen);
        weeklyAverageInLbs= getIntent().getExtras().getFloat("weeklyavg");
        SESSIONID=getIntent().getExtras().getString("SESSIONID");
        UNITS = getIntent().getExtras().getString("UNITS");

        mpLineChart = findViewById(R.id.weightGraph);
        TextView avgWeightTextView = findViewById(R.id.AvgWeightTextView);
        if(UNITS.equals("kgs")){
            weeklyAverageInKgs=(float)(weeklyAverageInLbs*0.454);
            avgWeightTextView.append(Float.toString(weeklyAverageInKgs));

        } else {
            avgWeightTextView.append(Float.toString(weeklyAverageInLbs));
        }
        avgWeightTextView.append(" "+UNITS);




        drawerLayout= findViewById(R.id.graphScreen);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);


        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=(NavigationView) findViewById(R.id.nav_view3);
        navigationView.setNavigationItemSelectedListener(this);

        /*
            will have this call to set it with "default" range, and then for the various time buttons,
            the on click listeners will have later calls to setdata with new parameters based on their time range.

            Both this call and the button listeners will need to have api calls attached to get the correct data from the db.

            Before calling setData with the data, the Entries must be in sorted order by the X column (date) or the graph could have unexpected behavior.

            In order to plot the weekly average on the graph, it will likely have to be in a different LineDataSet so that it can be differentiated
            and have different styles applied than the regular plotted points.
         */

        String defaultTimeRange = "alltime";
        getWeights(defaultTimeRange);

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
        YAxis yAxis = mpLineChart.getAxisLeft();
        mpLineChart.setDrawGridBackground(false);
        xAxis.disableGridDashedLine();
        xAxis.setDrawGridLines(false);
        yAxis.disableGridDashedLine();
        yAxis.setDrawGridLines(false);
        mpLineChart.getAxisRight().setEnabled(false);
        mpLineChart.getAxisRight().setDrawGridLines(false);


    }


    /* TODOS:

        xxMAKE SURE GRAPH TRANSLATES FROM LBS TO KGS IF NEEDED

        xxActually get the weights from the API into the graph

        xxCheck if sorting of entries can be done on sqlalchemy side?

        Figure out how to make the X axis what we want it to be

        xxFigure out how to plot on x axis according to how far away the dates are

        Plot weekly average

     */
    private void setData(List<Weight> weightsToDisplay) {
        ArrayList<Entry> values = new ArrayList<>();
        ArrayList<Entry> weeklyAvgPoint = new ArrayList<>();

        long difference;
        float daysBetween;
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date laterDate;

        for(int i = 0; i < weightsToDisplay.size(); i++){
            try {
                Weight currentWeight = weightsToDisplay.get(i);
                Date date0 = format.parse(weightsToDisplay.get(0).getDateString());
                laterDate = format.parse(currentWeight.getDateString());
                difference = laterDate.getTime() - date0.getTime();
                daysBetween = (difference / (1000*60*60*24));
                float weight = currentWeight.getWeight();

                if(UNITS.equals("kgs")){
                    weight = (float) (weight*0.454);
                }

                values.add(new Entry(daysBetween, weight));
                //this will need to change/be dynamic
                if(i == weightsToDisplay.size()-1){
                    if(UNITS.equals("kgs")){
                        weeklyAvgPoint.add(new Entry(i-3, weeklyAverageInKgs));
                    } else {
                        weeklyAvgPoint.add(new Entry(i-3, weeklyAverageInLbs));
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }


        LineDataSet weightsDataSet = new LineDataSet(values, "weights");
        LineDataSet weeklyAvgSet = new LineDataSet(weeklyAvgPoint, "weeklyAverage");

        weightsDataSet.setColor(Color.BLACK);
        weightsDataSet.setCircleColor(Color.BLACK);
        weightsDataSet.setDrawValues(false);
        weightsDataSet.setMode(LineDataSet.Mode.HORIZONTAL_BEZIER);

        List<ILineDataSet> weightDataSets = new ArrayList<ILineDataSet>();
        weightDataSets.add(weightsDataSet);
        weightDataSets.add(weeklyAvgSet);
        LineData data = new LineData(weightDataSets);
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

    private void getWeights(String timerange){
        weightsController=RetrofitApi.getInstance().getWeightsService();
        Call<List<Weight>> unitsCall=weightsController.getWeights(SESSIONID, timerange);

        EspressoIdlingResource.increment();

        unitsCall.enqueue(new Callback<List<Weight>>() {
            @Override
            public void onResponse(Call<List<Weight>> call, Response<List<Weight>> response) {
                if(!response.isSuccessful()){
                    return;
                }

                weightsDisplayed = response.body();
                setData(weightsDisplayed);
                EspressoIdlingResource.decrement();
            }

            @Override
            public void onFailure(Call<List<Weight>> call, Throwable t) {
                EspressoIdlingResource.decrement();
            }
        });
    }

// don't need this method anymore bc sorting done on API side (but keeping til we're more confident it works)

//    private ArrayList<Weight> formatWeights(List<Weight> weights){
//        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
//        ArrayList<Weight> realWeights = new ArrayList<Weight>();
//        int xCo = 0;
//        for(int i = 1; i < realWeights.size(); i++){
//            Date date;
//
//            try {
//                date = format.parse(realWeights.get(i).getDateString());
//                float weight = realWeights.get(i).getWeight();
//                if(UNITS.equals("kgs")){
//                    weight = (float) (weight*0.454);
//                }
//                System.out.println(date);
//                realWeights.add(new Weight(weight, date));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
//        //Collections.sort(realWeights, new Weight.WeightComparator());
//        return realWeights;
//    }

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