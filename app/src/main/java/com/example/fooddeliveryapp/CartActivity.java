package com.example.fooddeliveryapp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fooddeliveryapp.Adapter.CartListAdapter;
import com.example.fooddeliveryapp.Helper.ManagementCart;
import com.example.fooddeliveryapp.Interface.ChangeNumberItemsListener;

public class CartActivity extends AppCompatActivity {
    private Button btnCheckOut;
    private RecyclerView.Adapter adapter;
    private ManagementCart managementCart;
    private RecyclerView recyclerViewCart;
    private TextView totalFeeTxt, deliveryTxt, totalTxt, emptyTxt;
    private ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        getSupportActionBar().setTitle("Cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        btnCheckOut = findViewById(R.id.btnCheckOut);

        btnCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent maps = new Intent(CartActivity.this, MapsActivity.class);
                startActivity(maps);
            }
        });

        ManagementCart managementCart = new ManagementCart(this);

        initView();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewCart.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(),this,new ChangeNumberItemsListener() {
            @Override
            public void changed() {
                double delivery  = 50.0;
                double total = Math.round((managementCart.getTotalFee()+delivery)*100)/100;
                double itemTotal = delivery;

                totalFeeTxt.setText("kes" + itemTotal);
                totalTxt.setText("kes" + total);
            }
        });

        recyclerViewCart.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
        } else {
            emptyTxt.setVisibility(View.GONE);
        }



    }

    private void initView() {
        btnCheckOut = findViewById(R.id.btnCheckOut);
        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        recyclerViewCart =findViewById(R.id.recyclerViewCart);
    }


}