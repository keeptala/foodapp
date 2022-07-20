package com.example.fooddeliveryapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MapsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("google maps");
    }
}