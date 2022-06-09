package com.example.fooddeliveryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeLogin();
        initializeRegister();

        btnClickedLogin();
        btnClickedRegister();

        // initialize database which is firebase
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference table_user = database.getReference("user");


    }

    private void btnClickedRegister() {
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this, TermsActivity.class);
                startActivity(intent2);
            }
        });
    }

    private void btnClickedLogin() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(login);
            }
        });
    }

    private void initializeLogin() {
        btnLogin = findViewById(R.id.btnLogin);
    }

    private void initializeRegister() {
        btnRegister = findViewById(R.id.btnRegister);
    }

}