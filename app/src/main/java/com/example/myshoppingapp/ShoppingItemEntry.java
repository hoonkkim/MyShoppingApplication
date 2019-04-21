package com.example.myshoppingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.view.View;

public class ShoppingItemEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_item_entry);

        Bundle b = getIntent().getExtras();

        String userName = b.getString(MainActivity.USER_NAME);
        double userBudget = b.getDouble(MainActivity.USER_BUDGET);
        String userBugdetString = Double.toString(userBudget);
        //
        TextView nameView = findViewById(R.id.nameView);
        nameView.setText("Welcome " + userName + "!");

        TextView budgetView = findViewById(R.id.budgetView);
        budgetView.setText("Your Budget today is: $" + userBugdetString);

    }

//    public void addRow() {
//
//    }



}
