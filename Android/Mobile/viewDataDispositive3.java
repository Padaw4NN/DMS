package com.example.ceres;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class viewDataDispositive3 extends AppCompatActivity {

    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference referenceSensors = reference.child("K");
    private ListView listDispositive1;
    private ArrayList<String> dataSensors;
    public ArrayList<Float> valueArray = new ArrayList<>();


    public viewDataDispositive3() {
    }

    Query sensorsListQuery = referenceSensors.orderByChild("K");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data_dispositive1);
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
                    }
                }

                listDispositive1 = findViewById(R.id.listDispositive1);
                ArrayAdapter<Float> adapter = new ArrayAdapter<Float>(getApplicationContext(), android.R.layout.simple_list_item_1, android.R.id.text1,valueArray);
                listDispositive1.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Error to generate list: " + error, Toast.LENGTH_LONG).show();
            }
        });
    }

    public ArrayList<Float> getValues() {
        return valueArray;
    }
}