package com.example.ceres;

import android.content.Intent;
import android.os.Bundle;

//import androidx.fragment.R;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class dispositive extends Fragment {
    ImageView dispositivo1;
    ImageView dispositivo2;
    ImageView dispositivo3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_dispositivos, container, false);

        dispositivo1 = rootView.findViewById(R.id.dispositivo1);
        dispositivo2 = rootView.findViewById(R.id.dispositivo2);
        dispositivo3 = rootView.findViewById(R.id.dispositivo3);

        dispositivo1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), viewDataDispositive1.class);
                startActivity(intent);
            }
        });

        dispositivo2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), viewDataDispositive2.class);
                startActivity(intent);
            }
        });

        dispositivo3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), viewDataDispositive3.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}