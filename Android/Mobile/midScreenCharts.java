package com.example.ceres;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class midScreenCharts extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mid_screen_charts);


        Button buttonN = findViewById(R.id.buttonN);
        buttonN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(midScreenCharts.this, chartViewN.class);
                startActivity(intent);
            }
        });

        Button buttonP = findViewById(R.id.buttonP);
        buttonP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(midScreenCharts.this, chartViewP.class);
                startActivity(intent);
            }
        });

        Button buttonK = findViewById(R.id.buttonK);
        buttonK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(midScreenCharts.this, chartViewK.class);
                startActivity(intent);
            }
        });

        Button buttonUm = findViewById(R.id.buttonUm);
        buttonUm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(midScreenCharts.this, chartView.class);
                startActivity(intent);
            }
        });
    }


}