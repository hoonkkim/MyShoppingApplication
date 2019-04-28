package com.example.myshoppingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;


public class ShoppingItemEntry extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// Get Intent from previous Activity
        setContentView(R.layout.activity_shopping_item_entry);
        Bundle b = getIntent().getExtras();
// Use Data from Intent to Show Name & Budget to User
        String userName = b.getString(MainActivity.USER_NAME);
        double userBudget = b.getDouble(MainActivity.USER_BUDGET);
        String userBugdetString = Double.toString(userBudget);
// Welcome User
        TextView nameView = findViewById(R.id.nameView);
        nameView.setText("Welcome " + userName + "!");
// Display Budget
        TextView budgetView = findViewById(R.id.budgetView);
        budgetView.setText("Your Budget today is: $" + userBugdetString);
    }

    BubbleSort BubbleSorter = new BubbleSort();
    ArrayList<ShoppingItem> UserShoppingArrayList = new ArrayList<ShoppingItem>();

    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.AddItemButton:
                EditText nameEntryView = findViewById(R.id.NameEntry);
                EditText priceEntryView = findViewById(R.id.PriceEntry);
                EditText quantityEntryView = findViewById(R.id.QuantityEntry);
                EditText priorityEntryView = findViewById(R.id.PriorityEntry);

                if (!nameEntryView.getText().toString().isEmpty() && (nameEntryView.getText().toString() != "") &&
                        !priceEntryView.getText().toString().isEmpty() &&
                        !quantityEntryView.getText().toString().isEmpty() &&
                        !priorityEntryView.getText().toString().isEmpty()) {
                    String name = nameEntryView.getText().toString();
                    double price = new Double(priceEntryView.getText().toString()).doubleValue();
                    int quantity = new Integer(quantityEntryView.getText().toString());
                    int priority = new Integer(priorityEntryView.getText().toString());

                    if (price > 0 && quantity > 0 && priority > 0) {

                        UserShoppingArrayList.add(new ShoppingItem(name, price, priority, quantity));
                        int itemNumber = UserShoppingArrayList.size();
                        UserShoppingArrayList.get(itemNumber - 1).setTotalValue();

                        String output = String.format("%-30s", UserShoppingArrayList.get(itemNumber - 1).getName()) +
                                String.format("%-30s", String.valueOf(UserShoppingArrayList.get(itemNumber - 1).getPrice())) +
                                String.format("%-30s", String.valueOf(UserShoppingArrayList.get(itemNumber - 1).getQuantity())) +
                                String.format("%-30s", String.valueOf(UserShoppingArrayList.get(itemNumber - 1).getPriority()));

                        TextView ItemListView = findViewById(R.id.ItemListView);
                        ItemListView.append(output + "\n");

                        nameEntryView.getText().clear();
                        priceEntryView.getText().clear();
                        quantityEntryView.getText().clear();
                        priorityEntryView.getText().clear();
                    }
                }
                break;

            case R.id.ClearList:
                UserShoppingArrayList = null;
                TextView ItemListView = findViewById(R.id.ItemListView);
                ItemListView.clearComposingText();

                break;

            case R.id.goShoppingButton:
                int listLength = UserShoppingArrayList.size();
                BubbleSorter.RunSort(listLength, UserShoppingArrayList);

                Intent intent = new Intent(this, ShoppingResultActivity.class);
                Bundle listBundle = new Bundle();
                listBundle.putSerializable("ARRAYLIST", (Serializable)UserShoppingArrayList);
                intent.putExtra("BUNDLE",listBundle);
                startActivity(intent);

                break;

            default:
                break;
        }
    }
}

