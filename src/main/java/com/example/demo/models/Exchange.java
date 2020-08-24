package com.example.demo.models;

public class Exchange {

    private int id;
    private double amount;
    private String date;

    public Exchange() {
    }

    public int getId() {
        return id;
    }
    
    public double getAmount() {
        return amount;
    }

    private void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

}
