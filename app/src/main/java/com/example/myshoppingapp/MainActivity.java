package com.example.myshoppingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    public static final String USER_NAME = "com.example.myfirstapp.NAME";
    public static final String USER_BUDGET = "example.BUDGET";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enterDeets(View view) {
        Intent intent = new Intent(this, ShoppingItemEntry.class);
        Bundle b = new Bundle();

        EditText editName = (EditText) findViewById(R.id.editText);
        EditText editBudget = (EditText) findViewById(R.id.editText2);

        if(!editName.getText().toString().isEmpty() && !editBudget.getText().toString().isEmpty()) {
            String name = editName.getText().toString();
            double budget = new Double(editBudget.getText().toString()).doubleValue();

            b.putString(USER_NAME, name);
            b.putDouble(USER_BUDGET, budget);

            intent.putExtras(b);
            startActivity(intent);
        }
    }


}
