package com.example.demo.services.contracts;

import com.example.demo.models.Transaction;

public interface ConversionService  {
    Transaction currencyConversion(double sourceAmount, String sourceCurrency , String targetCurrency);
}
