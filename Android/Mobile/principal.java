package com.example.ceres;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
//import androidx.viewpager.R;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

public class principal extends Fragment {
    ImageView graph;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_principal, container, false);

        graph = rootView.findViewById(R.id.infographs1);

        graph.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), midScreenCharts.class);
                startActivity(intent);
            }
        });

        return rootView;
    }
}