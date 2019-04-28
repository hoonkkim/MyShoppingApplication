package com.example.myshoppingapp;

public class User {

    private String userName = "No Name Yet";
    private double bankAccount = -1000.01;

    public void setName(String newName) {userName = newName;}
    public void setBankAccount(double newBalance) {bankAccount = newBalance;}

    public String getName() {
        return userName;}

    public double getBankAccount() {
        return bankAccount;}

}