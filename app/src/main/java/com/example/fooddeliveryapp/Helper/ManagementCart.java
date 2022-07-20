package com.example.fooddeliveryapp.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.fooddeliveryapp.Interface.*;

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

        tinyDB.putListObject("CartList", listFood);
        Toast.makeText(context, "Added to cart ", Toast.LENGTH_SHORT).show();

    }

    public  ArrayList<FoodDomain> getListCart(){
        return tinyDB.getListObject("CartList");
    }

    public void plusNumberFood(ArrayList<FoodDomain>listFood, int position, ChangeNumberItemsListener changeNumberItemsListener){
        listFood.get(position).setNumberIntCart(listFood.get(position).getNumberIntCart()+1);
        tinyDB.putListObject("CartList", listFood);
        changeNumberItemsListener.changed();
    }

    public void minusNumberFood(ArrayList<FoodDomain>listfood, int position, ChangeNumberItemsListener changeNumberItemListener){
        if (listfood.get(position).getNumberIntCart()==1){
            listfood.remove(position);
        } else {
            listfood.get(position).setNumberIntCart(listfood.get(position).getNumberIntCart()-1);
        }
        tinyDB.putListObject("CartList",listfood);
        changeNumberItemListener.changed();
    }

    public double getTotalFee(){
        ArrayList<FoodDomain> listFood = getListCart();
        double fee = 0;
        for (int i = 0; i < listFood.size(); i++){
            fee = fee + (listFood.get(i).getFee() * listFood.get(i).getNumberIntCart());
        }
        return fee;
    }
}
