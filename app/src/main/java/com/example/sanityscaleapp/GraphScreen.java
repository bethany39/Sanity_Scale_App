package com.example.sanityscaleapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;


import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.content.Intent;
import android.widget.TextView;


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

public class GraphScreen extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private float weeklyAverage;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigation;
    private String SESSIONID;
    private LineChart mpLineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph_screen);
        weeklyAverage= getIntent().getExtras().getFloat("weeklyavg");
        SESSIONID=getIntent().getExtras().getString("SESSIONID");

        mpLineChart = findViewById(R.id.weightGraph);
        TextView avgWeightTextView = findViewById(R.id.AvgWeightTextView);
        avgWeightTextView.append(Float.toString(weeklyAverage));



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

}