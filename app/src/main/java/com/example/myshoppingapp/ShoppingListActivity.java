package com.example.myshoppingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class ShoppingListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Bundle b = getIntent().getExtras();
//
        String userName = b.getString(MainActivity.USER_NAME);
        double userBudget= b.getDouble(MainActivity.USER_BUDGET);
        String userBugdetString = Double.toString(userBudget);
        //
        TextView nameView = findViewById(R.id.NameView);
        nameView.setText("Welcome "+userName+"!");
        
        TextView budgetView = findViewById(R.id.BudgetView);
        budgetView.setText("Your Budget today is: $"+userBugdetString);
    }

}
