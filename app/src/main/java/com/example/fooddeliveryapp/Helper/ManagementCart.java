package com.example.fooddeliveryapp.Helper;

import android.content.Context;
import android.widget.Toast;

import java.util.ArrayList;

import Model.FoodDomain;

public class ManagementCart {
    private Context context;
    private TinyDB tinyDB;

    public ManagementCart(Context context) {
        this.context = context;
        this.tinyDB = new TinyDB(context);
    }

    public void insertFood(FoodDomain item){
        ArrayList<FoodDomain> listFood = getListCart();
        boolean alreadyExist = false;
        int n = 0;
        for (int i = 0; i < listFood.size(); i++){
            if (listFood.get(i).getTitle().equals(item.getTitle())){
                n = i;
                break;
            }
        }

        if (alreadyExist){
            listFood.get(n).setNumberIntCart(item.getNumberIntCart());
        } else {
            listFood.add(item);
        }

        tinyDB.putListObject("CardList", listFood);
        Toast.makeText(context, "Added to cart ", Toast.LENGTH_SHORT).show();

    }

    public  ArrayList<FoodDomain> getListCart(){
        return tinyDB.getListObject("CartList");
    }
}
