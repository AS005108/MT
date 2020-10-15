package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    Button resetButton;
    Integer[] map = {
            1,0,1,1,
            0,0,0,1,
            1,0,0,1,
            0,1,1,0
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridView.setAdapter(new GridAdapter(v.getContext(), map));
            }
        });

        gridView = findViewById(R.id.gridView);
        gridView.setAdapter(new GridAdapter(this, map));
    }
}