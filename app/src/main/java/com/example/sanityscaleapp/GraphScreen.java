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

import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.Calendar;

import java.math.BigDecimal;

public class GraphScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private float weeklyAverageInLbs;
    private float weeklyAverageInKgs;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigation;
    private String SESSIONID;
    private String UNITS;
    private LineChart mpLineChart;
    private Button onemonthBtn, threemonthsBtn, alltimeBtn;
    private IWeightsController weightsController;
    private List<Weight> weightsDisplayed;
    private String graphTimeRange;
    private TextView nav_user;
    private IUserController userService;
    private String firstName;
    private TextView avgWeightTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_screen);
        weeklyAverageInLbs= getIntent().getExtras().getFloat("weeklyavg");
        SESSIONID=getIntent().getExtras().getString("SESSIONID");
        UNITS = getIntent().getExtras().getString("UNITS");
        firstName=getIntent().getExtras().getString("firstname");


        mpLineChart = findViewById(R.id.weightGraph);
        avgWeightTextView = findViewById(R.id.AvgWeightTextView);

        drawerLayout= findViewById(R.id.graphScreen);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);


        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView=(NavigationView) findViewById(R.id.nav_view3);
        View hView=navigationView.getHeaderView(0);
        nav_user=(TextView) hView.findViewById(R.id.nameTxt);
        nav_user.setText(firstName);


        navigationView.setNavigationItemSelectedListener(this);

        onemonthBtn=findViewById(R.id.onemonthBtn);
        onemonthBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                graphTimeRange="1month";
                getWeights("1month");
            }

            });

        threemonthsBtn=findViewById(R.id.threemonthsBtn);
        threemonthsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                graphTimeRange="3month";
                getWeights("3months");
            }

        });

        alltimeBtn=findViewById(R.id.alltimeBtn);
        alltimeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                graphTimeRange="alltime";
                getWeights("alltime");
            }

        });

    }

    @Override
    public void onStart() {
        super.onStart();

        mpLineChart.getXAxis().setDrawLabels(true);

        graphTimeRange = "1month";
        getWeights(graphTimeRange);

        XAxis xAxis = mpLineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelRotationAngle(-45);
        YAxis yAxis = mpLineChart.getAxisLeft();
        mpLineChart.setDrawGridBackground(false);
        xAxis.disableGridDashedLine();
        xAxis.setDrawGridLines(false);
        yAxis.disableGridDashedLine();
        yAxis.setDrawGridLines(false);
        mpLineChart.getAxisRight().setEnabled(false);
        mpLineChart.getAxisRight().setDrawGridLines(false);
        mpLineChart.getDescription().setEnabled(false);
        mpLineChart.getLegend().setTextSize(14f);

        getUnits();



    }

    private void setData(List<Weight> weightsToDisplay) {
        ArrayList<Entry> values = new ArrayList<>();
        ArrayList<Entry> weeklyAvgPoint = new ArrayList<>();

        long difference = 0;
        float daysBetween = 0;
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date laterDate;
        int[] xVals = new int[weightsToDisplay.size()];

        for(int i = 0; i < weightsToDisplay.size(); i++){
            try {
                Weight currentWeight = weightsToDisplay.get(i);
                Date date0 = format.parse(weightsToDisplay.get(0).getDateString());
                laterDate = format.parse(currentWeight.getDateString());
                difference = laterDate.getTime() - date0.getTime();
                daysBetween = (difference / (1000*60*60*24));
                xVals[i]=(int) daysBetween;
                float weight = currentWeight.getWeight();
                if(UNITS.equals("kgs")){
                    weight = (float) (weight*0.454);
                }
                values.add(new Entry(daysBetween, weight));

            } catch (ParseException e) {
                e.printStackTrace();
            }

        }

        XAxisLabelFormatter xAxisFormatter = new XAxisLabelFormatter(weightsToDisplay.get(0).getDateString());
        //String[] dateStrings = new String[(int)daysBetween+1];
        String[] labels = new String[(int) daysBetween+1];
        //String[] labels = new String[values.size()];
        int xValsIndex = 0;
        for(int i = 0; i <= daysBetween; i++, xValsIndex++) {
            if(xVals[xValsIndex]==i){
                String label = xAxisFormatter.getFormattedValue(weightsToDisplay.get(xValsIndex).getDateString());
                labels[i] = label;
            } else {
                //get previous dateString, add one to it, getformattedvalue on that
                String dayBefore = labels[i-1];
                Calendar c = Calendar.getInstance();
                try{
                    //Setting the date to the given date
                    c.setTime(format.parse(dayBefore));
                }catch(ParseException e){
                    e.printStackTrace();
                }
                c.add(Calendar.DAY_OF_MONTH, 1);
                String dayAfter = format.format(c.getTime());
                labels[i]=dayAfter;
                xValsIndex--;
            }

        }
        //mpLineChart.getXAxis().setAvoidFirstLastClipping(true);

        mpLineChart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));

        //mpLineChart.getXAxis().setLabelCount(6, true);
        //mpLineChart.getXAxis().setGranularity(3);
//        mpLineChart.getXAxis().setAxisMinimum(0);
//        mpLineChart.getXAxis().setAxisMaximum(daysBetween);
        if(UNITS.equals("kgs")){
                weeklyAvgPoint.add(new Entry(daysBetween-3, weeklyAverageInKgs));
        } else {
                weeklyAvgPoint.add(new Entry(daysBetween-3, weeklyAverageInLbs));
        }
        //}

        LineDataSet weightsDataSet = new LineDataSet(values, "weights");
        LineDataSet weeklyAvgSet = new LineDataSet(weeklyAvgPoint, "weeklyAverage");
        weeklyAvgSet.setColor(R.color.weeklyAverage);
        weeklyAvgSet.setCircleColor(R.color.weeklyAverage);
        weeklyAvgSet.setCircleHoleRadius(0);
        weeklyAvgSet.setCircleHoleColor(Color.TRANSPARENT);

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
                intent.putExtra("SESSIONID",SESSIONID);
                intent.putExtra("firstname",firstName);
                startActivity(intent);
                break;
            case R.id.nav_settings:
                Intent intent2=new Intent(GraphScreen.this,SettingsScreen.class);
                intent2.putExtra("SESSIONID",SESSIONID);
                intent2.putExtra("firstname",firstName);
                startActivity(intent2);
                break;
            case R.id.nav_logout:
                Intent intent3=new Intent(GraphScreen.this,LogoutHome.class);
                intent3.putExtra("SESSIONID",SESSIONID);
                intent3.putExtra("firstname",firstName);
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

    private void getUnits(){
        userService=RetrofitApi.getInstance().getUserService();
        Call<User> unitsCall=userService.getUserUnits(SESSIONID);

        EspressoIdlingResource.increment();

        unitsCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(!response.isSuccessful()){
                    //should do something for the error handling
                    Log.d("IUserController", "inside if in onResponse");
                    return;

                }
                Log.d("IUserController", "outside if in onResponse");
                User user = response.body();
                UNITS = user.getUnit();
                firstName = user.getFirstName();
                nav_user.setText(firstName);
                avgWeightTextView.setText("Weekly Average Weight: ");

                if(UNITS.equals("kgs")){
                    weeklyAverageInKgs=(float)(weeklyAverageInLbs*0.454);
                    avgWeightTextView.append(String.format("%.1f", weeklyAverageInKgs));
                } else {
                    //avgWeightTextView.append(Float.toString(weeklyAverageInLbs));
                    String mystr = String.format("%.1f", weeklyAverageInLbs);
                    avgWeightTextView.append(String.format("%.1f", weeklyAverageInLbs));

                }
                avgWeightTextView.append(" "+UNITS);
                EspressoIdlingResource.decrement();

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("IUserController", "inside onFailure");
                EspressoIdlingResource.decrement();

            }
        });

    }
}