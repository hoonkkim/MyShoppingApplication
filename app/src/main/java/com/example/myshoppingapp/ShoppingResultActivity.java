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
        Bundle listBundle= intent.getBundleExtra("BUNDLE");

        ArrayList<ShoppingItem> shoppingArrayList = (ArrayList<ShoppingItem>) listBundle.getSerializable("ARRAYLIST");

        TextView textView = findViewById(R.id.TestText);
        textView.setText(shoppingArrayList.get(0).getName());
    }

//    Go Shop
//    Show Start & Remaining Budget
//    Show Purchased
//    Show Remaining
//    Save to Google


}
