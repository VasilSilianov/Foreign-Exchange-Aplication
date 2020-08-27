package com.example.demo.controllers.rest;

import com.example.demo.models.Transaction;
import com.example.demo.services.contracts.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/conversion")
public class ConversionRestController {
    private ConversionService conversionService;

    @Autowired
    public ConversionRestController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }


    @PostMapping("/{sourceAmount}/{sourceCurrency}/{targetCurrency}")//todo DTO za da gi napavam kato Json
    public String currencyConversion(@PathVariable double sourceAmount, @PathVariable String sourceCurrency, @PathVariable String targetCurrency) {
        Transaction transaction = conversionService.currencyConversion(sourceAmount, sourceCurrency, targetCurrency);
        System.out.println(transaction.getTransactionId());
        return String.format("Transaction ID: %d \nTarget currency amount: %.5f", transaction.getTransactionId(), transaction.getAmount());
    }

}

