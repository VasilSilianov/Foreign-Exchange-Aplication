package com.example.demo.models;

import javax.persistence.*;

@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transaction_id")
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

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }


    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
