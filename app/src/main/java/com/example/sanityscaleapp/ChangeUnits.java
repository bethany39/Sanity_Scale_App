package com.example.sanityscaleapp;

        import androidx.appcompat.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.Button;
        import android.view.View.OnClickListener;
        import android.view.View;
        import android.content.Intent;





public class ChangeUnits extends AppCompatActivity {
    Button lbsBtn, kgsBtn,backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_units);


        kgsBtn=findViewById(R.id.kgsBtn);
        kgsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ChangeUnits.this, ChangeUnitsKgs.class);
                ChangeUnits.this.startActivity(intent);

            }
        });
        backBtn=findViewById(R.id.backBtn);
        backBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent =new Intent(ChangeUnits.this, HomeScreen.class);
                ChangeUnits.this.startActivity(intent);

            }
        });
    }

}
