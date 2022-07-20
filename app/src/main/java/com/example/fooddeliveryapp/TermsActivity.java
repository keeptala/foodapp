package com.example.fooddeliveryapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class TermsActivity extends AppCompatActivity {
    private Button btnGetToTerms;
    private CheckBox checkTerms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        getSupportActionBar().setTitle("    Terms");

        intialize();
        AgreeAndContinue();
    }



    private void AgreeAndContinue() {
        btnGetToTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateTerms();
            }
        });

    }

    private void validateTerms() {
        if (checkTerms.isChecked()){
            Intent intent = new Intent(TermsActivity.this,RegisterActivity.class);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(TermsActivity.this, "You have to agree to our terms and conditons", Toast.LENGTH_SHORT).show();
        }
    }


    private void intialize() {
        btnGetToTerms = findViewById(R.id.btnGetToTerms);
        checkTerms = findViewById(R.id.checkTerms);
    }
}