package com.example.fooddeliveryapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private TextView phoneEdt, usernameEdt;
    private EditText currentEdt, newEdt;
    private Button logoutBtn, updateBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setTitle("Profile");


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }

    private void initialize() {

        phoneEdt = findViewById(R.id.phoneEdt);
        usernameEdt = findViewById(R.id.usernameEdt);
        currentEdt = findViewById(R.id.currentEdt);
        newEdt = findViewById(R.id.newEdt);
        logoutBtn = findViewById(R.id.logoutBtn);
        updateBtn = findViewById(R.id.updateBtn);
    }
}