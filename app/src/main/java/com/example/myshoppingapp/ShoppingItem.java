package com.example.myshoppingapp;

import java.io.Serializable;

public class ShoppingItem implements Serializable {
        private String itemName;
        private double itemPrice;
        private int itemQuantity;
        private int itemPriority;
        private boolean itemBought;
        private double itemTotalValue;

        public ShoppingItem() {
            itemName = "No Name Yet";
            itemPrice = 9999.99;
            itemPriority = -1;
            itemBought = false;
            itemQuantity = -1;
            double itemTotalValue = -1;
        }

        public ShoppingItem(String newName, double newPrice, int newPriority, int newQuantity) {
            itemName = newName;
            itemPrice = newPrice;
            itemPriority = newPriority;
            itemBought = false;
            itemQuantity = newQuantity;
            itemTotalValue = newPrice * newQuantity;

        }
        // Getters
        public String getName() {
            return itemName;
        }

        public double getPrice() {
            return itemPrice;
        }

        public int getQuantity() {
            return itemQuantity;
        }

        public int getPriority() {
            return itemPriority;
        }

        public boolean getBought() {
            return itemBought;
        }

        public double getTotalValue() {
            return itemTotalValue;
        }

        // Setters
        public void setName(String newName) {
            itemName = newName;
        }

        public void setPrice(double newPrice) {
            itemPrice = newPrice;
        }

        public void setQuantity(int newQuantity) {
            itemQuantity = newQuantity;
        }

        public void setPriority(int newPriority) {
            itemPriority = newPriority;
        }

        public void bought() {
            itemBought = true;
        }

        public void setTotalValue() {itemTotalValue = itemPrice*itemQuantity;}

        // Equals
        public boolean nameEquals(ShoppingItem previousItem){//, double comparePrice, int comparePriority) {
            return (itemName.equals(previousItem.getName()));// && comparePrice == itemPrice && comparePriority == itemPriority);
        }

        public void nameCopy(ShoppingItem previousItem) {
            itemName = previousItem.getName();
        }

        public void quantityCopy(ShoppingItem previousItem) {
            itemQuantity = previousItem.getQuantity();
        }

        public void priceCopy(ShoppingItem previousItem) {
            itemPrice = previousItem.getPrice();
        }

        public void priorityCopy(ShoppingItem previousItem) {
            itemPriority = previousItem.getPriority();
        }

        public void totalValueCopy(ShoppingItem previousItem) {
            itemTotalValue = previousItem.getTotalValue();
        }
    }

