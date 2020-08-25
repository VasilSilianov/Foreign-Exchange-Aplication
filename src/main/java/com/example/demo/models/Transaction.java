package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @Column(name="transactionID")
    private int transactionId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "date")
    private String date;

    public Transaction() {
    }

    public int getTransactionId() {
        return transactionId;
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
