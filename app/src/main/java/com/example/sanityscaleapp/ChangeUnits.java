package com.example.sanityscaleapp;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.ActionBarDrawerToggle;
        import androidx.appcompat.app.AppCompatActivity;
        import androidx.drawerlayout.widget.DrawerLayout;

        import android.os.Bundle;
        import android.view.MenuItem;
        import android.widget.Button;
        import android.view.View.OnClickListener;
        import android.view.View;
        import android.content.Intent;
        import android.widget.ImageView;

        import com.google.android.material.navigation.NavigationView;


public class ChangeUnits extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Button lbsBtn, kgsBtn;
    ImageView bluebuttonlb, bluebuttonkg;
    private int USERID;
    private String UNITS;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_units);
        bluebuttonlb=findViewById(R.id.bluebuttonlb);
        bluebuttonkg=findViewById(R.id.bluebuttonkg);
        //bluebuttonkg.setVisibility(View.INVISIBLE);
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null) {
            USERID = bundle.getInt("USERID");
            UNITS = bundle.getString("selected");
        }

        if (UNITS.equals("pounds"))
        {
            bluebuttonkg.setVisibility(View.INVISIBLE);
        }
        else if (UNITS.equals("kgs"))
        {
            bluebuttonlb.setVisibility(View.INVISIBLE);
        }


        drawerLayout= findViewById(R.id.changeUnitsScreen);
        toggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        NavigationView navigationView=(NavigationView) findViewById(R.id.nav_view6);
        navigationView.setNavigationItemSelectedListener(this);


        kgsBtn=findViewById(R.id.kgsBtn);
        kgsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               // Intent intent =new Intent(ChangeUnits.this, ChangeUnitsKgs.class);
               // ChangeUnits.this.startActivity(intent);
                bluebuttonlb.setVisibility(View.INVISIBLE);
                bluebuttonkg.setVisibility(View.VISIBLE);

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
                System.out.println("here");
                Intent intent=new Intent(ChangeUnits.this,HomeScreen.class);
                startActivity(intent);
                break;
            case R.id.nav_settings:
                Intent intent2=new Intent(ChangeUnits.this,SettingsScreen.class);
                startActivity(intent2);
                break;
            case R.id.nav_logout:
                Intent intent3=new Intent(ChangeUnits.this,LogoutHome.class);
                startActivity(intent3);
                break;
        }
        return true;
    }

}
