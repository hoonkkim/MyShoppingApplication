package com.example.myshoppingapp;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class TextWriter {
    public void WriteTxt(String filename)
    {
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(filename);
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file " + filename);
            System.exit(0);
        }

        outputStream.printf("%20s %20s %20s %20s %20s %n"
                , "Item Name", "Price", "Quantity","Total Value", "Priority", "Did you buy it?");
        outputStream.close();
    }


    public void WriteTxt(String filename, ArrayList<ShoppingItem> UserShoppingArrayList, boolean bought)
    {
        PrintWriter outputStream = null;

        try {
            outputStream = new PrintWriter(filename);
        } catch (FileNotFoundException e) {
            System.out.println("Error opening the file " + filename);
            System.exit(0);
        }
        System.out.println("Saving Product List to txt file "+filename+".txt");

        outputStream.printf("%20s %20s %20s %20s %20s %n"
                , "Item Name", "Price", "Quantity","Total Value", "Priority", "Did you buy it?");

        for (int count = 0; count < UserShoppingArrayList.size(); count++) {
            if (!UserShoppingArrayList.get(count).getBought() && !bought) {
                outputStream.printf("%20s %20s %20s %20s %20s %n"
                        , "|"+UserShoppingArrayList.get(count).getName()+"|,"
                        , "|"+UserShoppingArrayList.get(count).getPrice()+"|,"
                        , "|"+UserShoppingArrayList.get(count).getQuantity()+"|,"
                        , "|"+UserShoppingArrayList.get(count).getTotalValue()+"|,"
                        , "|"+UserShoppingArrayList.get(count).getPriority()+"|,"
                        , "|"+UserShoppingArrayList.get(count).getBought()+"|,"

                );
            } else if (UserShoppingArrayList.get(count).getBought() && bought) {
                outputStream.printf("%20s %20s %20s %20s %20s %n"
                        , "|"+UserShoppingArrayList.get(count).getName()+"|,"
                        , "|"+UserShoppingArrayList.get(count).getPrice()+"|,"
                        , "|"+UserShoppingArrayList.get(count).getQuantity()+"|,"
                        , "|"+UserShoppingArrayList.get(count).getTotalValue()+"|,"
                        , "|"+UserShoppingArrayList.get(count).getPriority()+"|,"
                        , "|"+UserShoppingArrayList.get(count).getBought()+"|,"
                );
            }
        }
        outputStream.close();

    }
}