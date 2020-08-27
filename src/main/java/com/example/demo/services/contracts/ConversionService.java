package com.example.demo.services.contracts;

import com.example.demo.models.Transaction;

import java.util.List;

public interface ConversionService  {
    Transaction getById(int id);
    List<Transaction> getByDate(String date);
    Transaction currencyConversion(double sourceAmount, String sourceCurrency , String targetCurrency);
}
