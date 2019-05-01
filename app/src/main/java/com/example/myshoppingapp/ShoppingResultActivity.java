package com.example.myshoppingapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class ShoppingResultActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_result);

        Intent intent = getIntent();
        Bundle listBundle = intent.getBundleExtra("LISTBUNDLE");

        ArrayList<ShoppingItem> shoppingArrayList = (ArrayList<ShoppingItem>) listBundle.getSerializable("ARRAYLIST");
        User ShoppingUser = new User();
        ShoppingUser.setName(listBundle.getString("USER_NAME"));
        ShoppingUser.setBankAccount(listBundle.getDouble("USER_BUDGET"));

        TextView userView = findViewById(R.id.UserView);
        userView.setText(ShoppingUser.getName() + ", your remaining budget is: $" + ShoppingUser.getBankAccount() + ".");

        TextView boughtNameView = findViewById(R.id.BoughtNameView);
        TextView BoughtPriceView = findViewById(R.id.BoughtPriceView);
        TextView BoughtQuantityView = findViewById(R.id.BoughtQuantityView);
        TextView BoughtPriorityView = findViewById(R.id.BoughtPriorityView);


        int ListIterator = 0;
        while (ListIterator < shoppingArrayList.size()) {
            if (shoppingArrayList.get(ListIterator).getBought()) {
                boughtNameView.append(shoppingArrayList.get(ListIterator).getName() + "\n");
                BoughtPriceView.append(shoppingArrayList.get(ListIterator).getPrice() + "\n");
                BoughtQuantityView.append(shoppingArrayList.get(ListIterator).getQuantity() + "\n");
                BoughtPriorityView.append(shoppingArrayList.get(ListIterator).getPriority() + "\n");

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
                NotBoughtNameView.append(shoppingArrayList.get(ListIterator).getName() + "\n");
                NotBoughtPriceView.append(shoppingArrayList.get(ListIterator).getPrice() + "\n");
                NotBoughtQuantityView.append(shoppingArrayList.get(ListIterator).getQuantity() + "\n");
                NotBoughtPriorityView.append(shoppingArrayList.get(ListIterator).getPriority() + "\n");

            }
            ListIterator++;
        }

    }


    public void onClick(View v) throws IOException {

        Context context = getApplicationContext();

        Intent intent = getIntent();
        Bundle listBundle = intent.getBundleExtra("LISTBUNDLE");

        ArrayList<ShoppingItem> shoppingArrayList = (ArrayList<ShoppingItem>) listBundle.getSerializable("ARRAYLIST");

        File path = context.getFilesDir();
        String filename = "NotBought.txt";

        StringBuilder sb = new StringBuilder();


        sb.append(String.format("%20s %20s %20s %20s %20s %n"
                , "Item Name", "Price", "Quantity", "Total Value", "Priority", "Did you buy it?"));

        for (int count = 0; count < shoppingArrayList.size(); count++) {
            if (!shoppingArrayList.get(count).getBought()) {
                sb.append(
                        String.format("%20s %20s %20s %20s %20s %n"
                                , "|" + shoppingArrayList.get(count).getName() + "|,"
                                , "|" + shoppingArrayList.get(count).getPrice() + "|,"
                                , "|" + shoppingArrayList.get(count).getQuantity() + "|,"
                                , "|" + shoppingArrayList.get(count).getTotalValue() + "|,"
                                , "|" + shoppingArrayList.get(count).getPriority() + "|,"
                                , "|" + shoppingArrayList.get(count).getBought() + "|"

                        ));
            }
        }


                File localfile = new File(path, filename);

                FileOutputStream localstream = new FileOutputStream(localfile);
                try {
                    localstream.write(sb.toString().getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    localstream.close();
                }


                // Create a storage reference from our app
                FirebaseStorage storage = FirebaseStorage.getInstance();
                // Create a storage reference from our app
                Uri file = Uri.fromFile(localfile);
                UploadTask uploadTask;

                final StorageReference storageRef = storage.getReference(filename);
                uploadTask = storageRef.putFile(file);

// Register observers to listen for when the download is done or if it fails
                uploadTask.addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        TextView PathView = findViewById(R.id.textView19);
                        PathView.setText(exception.getCause().toString());

                    }
                }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        String successpath = storageRef.getDownloadUrl().toString();
                        TextView PathView = findViewById(R.id.textView19);
                        PathView.setText(successpath);

                    }
                });


                Toast toast = Toast.makeText(context, "Remaining Items saved in " + storageRef.toString() + filename, Toast.LENGTH_LONG);
                toast.setGravity(Gravity.TOP | Gravity.CENTER_HORIZONTAL, 60, 0);
                toast.show();


            }

        }


