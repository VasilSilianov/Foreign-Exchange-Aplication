package com.example.demo.models;

import javax.persistence.*;
import java.util.Date;

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
    private Date date;

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

    public Date getDate() {
        return date;
    }


    public void setTransactionId(int transactionId) {
        this.transactionId = transactionId;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
