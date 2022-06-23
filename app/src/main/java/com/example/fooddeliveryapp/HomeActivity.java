package com.example.fooddeliveryapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class HomeActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    HomeFragment  homeFragment = new HomeFragment();
//    SettingsFragment settingsFragment = new SettingsFragment();
    SupportFragment supportFragment = new SupportFragment();
//    ProfileFragment  profileFragment = new ProfileFragment();
//    CartFragment cartFragment = new CartFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavView);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,homeFragment).commit();
                        return true;
                    case R.id.profile:
                        Toast.makeText(HomeActivity.this, "profiles", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.cart:
                        Toast.makeText(HomeActivity.this, "cart", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.support:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,supportFragment).commit();
                        return true;
                    case R.id.settings:
                        Toast.makeText(HomeActivity.this, "settings", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });

    }
}