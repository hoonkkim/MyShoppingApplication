package com.example.myshoppingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class ShoppingResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_result);

        Intent intent = getIntent();
        Bundle listBundle= intent.getBundleExtra("LISTBUNDLE");

        ArrayList<ShoppingItem> shoppingArrayList = (ArrayList<ShoppingItem>) listBundle.getSerializable("ARRAYLIST");
        User ShoppingUser = new User();
        ShoppingUser.setName(listBundle.getString("USER_NAME"));
        ShoppingUser.setBankAccount(listBundle.getDouble("USER_BUDGET"));

        TextView userView = findViewById(R.id.UserView);
        userView.setText(ShoppingUser.getName()+", your remaining budget is: $"+ShoppingUser.getBankAccount()+".");

        TextView boughtNameView = findViewById(R.id.BoughtNameView);
        TextView BoughtPriceView = findViewById(R.id.BoughtPriceView);
        TextView BoughtQuantityView = findViewById(R.id.BoughtQuantityView);
        TextView BoughtPriorityView = findViewById(R.id.BoughtPriorityView);



        int ListIterator = 0;
        while (ListIterator < shoppingArrayList.size()) {
            if (shoppingArrayList.get(ListIterator).getBought()) {
                boughtNameView .append(shoppingArrayList.get(ListIterator).getName()+"\n");
                BoughtPriceView .append(shoppingArrayList.get(ListIterator).getPrice()+"\n");
                BoughtQuantityView .append(shoppingArrayList.get(ListIterator).getQuantity()+"\n");
                BoughtPriorityView .append(shoppingArrayList.get(ListIterator).getPriority()+"\n");

            }
            ListIterator++;
        }


        TextView NotBoughtNameView = findViewById(R.id.NotBoughtNameView);
        TextView NotBoughtPriceView = findViewById(R.id.NotBoughtPriceView);
        TextView NotBoughtQuantityView = findViewById(R.id.NotBoughtQuantityView);
        TextView NotBoughtPriorityView = findViewById(R.id.NotBoughtPriorityView);


        ListIterator = 0;
        while (ListIterator < shoppingArrayList.size()) {
            if (!shoppingArrayList.get(ListIterator).getBought()) {
                NotBoughtNameView .append(shoppingArrayList.get(ListIterator).getName()+"\n");
                NotBoughtPriceView .append(shoppingArrayList.get(ListIterator).getPrice()+"\n");
                NotBoughtQuantityView .append(shoppingArrayList.get(ListIterator).getQuantity()+"\n");
                NotBoughtPriorityView .append(shoppingArrayList.get(ListIterator).getPriority()+"\n");

            }
            ListIterator++;
        }



    }


}
