package com.example.myshoppingapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;


public class ShoppingItemEntry<userName> extends AppCompatActivity implements View.OnClickListener{

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

        ShoppingUser.setName(userName);
        ShoppingUser.setBankAccount(userBudget);
// Welcome User
        TextView nameView = findViewById(R.id.nameView);
        nameView.setText("Welcome " + userName + "!");
// Display Budget
        TextView budgetView = findViewById(R.id.budgetView);
        budgetView.setText("Your Budget today is: $" + userBugdetString);
    }

    BubbleSort BubbleSorter = new BubbleSort();
    ArrayList<ShoppingItem> UserShoppingArrayList = new ArrayList<ShoppingItem>();
    User ShoppingUser = new User();

    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.AddItemButton:
                EditText nameEntryView = findViewById(R.id.NameEntry);
                EditText priceEntryView = findViewById(R.id.PriceEntry);
                EditText quantityEntryView = findViewById(R.id.QuantityEntry);
                EditText priorityEntryView = findViewById(R.id.PriorityEntry);
// Run DupeChecker Here
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

                        TextView NameView = findViewById(R.id.NameView);
                        TextView PriceView = findViewById(R.id.PriceView);
                        TextView QuantityView = findViewById(R.id.QuantityView);
                        TextView PriorityView = findViewById(R.id.PriorityView);


                        NameView.append(UserShoppingArrayList.get(itemNumber - 1).getName() + "\n");
                        PriceView.append(UserShoppingArrayList.get(itemNumber - 1).getPrice() + "\n");
                        QuantityView.append(UserShoppingArrayList.get(itemNumber - 1).getQuantity() + "\n");
                        PriorityView.append(UserShoppingArrayList.get(itemNumber - 1).getPriority() + "\n");

                        nameEntryView.getText().clear();
                        priceEntryView.getText().clear();
                        quantityEntryView.getText().clear();
                        priorityEntryView.getText().clear();
                    }
                }
                break;

            case R.id.ClearList:
                UserShoppingArrayList.clear();
                UserShoppingArrayList.trimToSize();

                TextView NameView = findViewById(R.id.NameView);
                TextView PriceView = findViewById(R.id.PriceView);
                TextView QuantityView = findViewById(R.id.QuantityView);
                TextView PriorityView = findViewById(R.id.PriorityView);
                NameView.setText("");
                PriceView.setText("");
                QuantityView.setText("");
                PriorityView.setText("");

                break;

            case R.id.goShoppingButton:
                int listLength = UserShoppingArrayList.size();
                if (listLength > 0) {
                    BubbleSorter.RunSort(listLength, UserShoppingArrayList);

                    for (int parseOrder = 0; parseOrder < listLength; parseOrder++) {
                        if (UserShoppingArrayList.get(parseOrder).getTotalValue() <= ShoppingUser.getBankAccount()) {
                            UserShoppingArrayList.get(parseOrder).bought();
                            ShoppingUser.setBankAccount(ShoppingUser.getBankAccount() - UserShoppingArrayList.get(parseOrder).getTotalValue());
                            }
                    }

                    Intent intent = new Intent(this, ShoppingResultActivity.class);
                    Bundle listBundle = new Bundle();
                    listBundle.putSerializable("ARRAYLIST", (Serializable) UserShoppingArrayList);
                    intent.putExtra("LISTBUNDLE", listBundle);

                    String USER_NAME = ShoppingUser.getName();
                    double USER_BUDGET = ShoppingUser.getBankAccount();

                    listBundle.putString("USER_NAME", USER_NAME);
                    listBundle.putDouble("USER_BUDGET", USER_BUDGET);
                    startActivity(intent);
                }
                break;

            default:
                break;
        }
    }
}

