package com.example.sanityscaleapp;

        import androidx.appcompat.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.Button;
        import android.view.View.OnClickListener;
        import android.view.View;
        import android.content.Intent;
        import android.widget.ImageView;


public class ChangeUnits extends AppCompatActivity {
    Button lbsBtn, kgsBtn,backBtn;
    ImageView bluebuttonlb, bluebuttonkg;
    private int USERID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_units);
        bluebuttonlb=findViewById(R.id.bluebuttonlb);
        bluebuttonkg=findViewById(R.id.bluebuttonkg);
        bluebuttonkg.setVisibility(View.INVISIBLE);
        USERID = getIntent().getExtras().getInt("USERID");


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
