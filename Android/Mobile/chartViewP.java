package com.example.ceres;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class chartViewP extends AppCompatActivity {

    private BarChart chart;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference referenceSensors = reference.child("P");
    private ArrayList<String> dataSensors;
    public ArrayList<Float> valueArray = new ArrayList<>();
    Query sensorsListQuery = referenceSensors.orderByChild("P");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_view);
        chart = findViewById(R.id.chart);

        dataSensors = new ArrayList<>();


        sensorsListQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Map<String, Object> map = (Map<String, Object>) dataSnapshot.getValue();
                    dataSensors.add(map.toString());
                }

                for(String value : dataSensors){
                    value.replace("{", "");
                    value.replace("}", "");
                    value.replace("}", "");
                    String[] values = value.split(",");
                    for(String v : values){
                        String[] key_value = v.split("=");
                        valueArray.add(Float.parseFloat(key_value[1].replace("}","")));

                        setupChart();
                        setData();
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Error to generate chart data " + error, Toast.LENGTH_LONG).show();
            }
        });


    }

    private void setupChart() {
        // Chart general config
        chart.getDescription().setEnabled(false);
        chart.setTouchEnabled(true);
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setPinchZoom(true);
        chart.getAxisLeft().setEnabled(true);
        chart.getAxisRight().setEnabled(false);
        chart.getXAxis().setEnabled(true);
    }

    private void setData() {
        int i = 1;
        ArrayList<BarEntry> valueEntries = new ArrayList<>();

        for (Float value : valueArray) {
            float tempValue = (float) value;
            valueEntries.add(new BarEntry(i, tempValue));
            i++;
        }

        BarDataSet dataSet = new BarDataSet(valueEntries, "Temperatura");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet.setDrawValues(true);

        // Crie um segundo conjunto de dados duplicando o conjunto de dados existente
        BarDataSet dataSet2 = new BarDataSet(valueEntries, "");
        dataSet2.setColors(ColorTemplate.MATERIAL_COLORS);
        dataSet2.setDrawValues(false);

        BarData barData = new BarData(dataSet, dataSet2);

        // Definir a largura das barras
        float barWidth = 0.35f; // largura das barras
        float barSpace = 0.04f; // espaçamento barras
        float groupSpace = 0.5f; // espaçamento entre grupos de barras

        barData.setBarWidth(barWidth);
        barData.groupBars(0f, groupSpace, barSpace); // Adiciona o espaçamento entre as barras

        chart.setData(barData);
        chart.invalidate(); // Refresh do gráfico


    }
}