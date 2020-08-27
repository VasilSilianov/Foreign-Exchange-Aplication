package com.example.demo.controllers.rest;

import com.example.demo.models.Transaction;
import com.example.demo.services.contracts.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversion")
public class ConversionRestController {
    private ConversionService conversionService;

    @Autowired
    public ConversionRestController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }


    @GetMapping("/transaction/{id}")
    public String getTransactionById(@PathVariable int id){
        Transaction transaction= conversionService.getById(id);
        return  String.format("Transaction ID: %d\n" +
                              "Amount: %.5f\n" +
                              "Date: %s",transaction.getTransactionId(),transaction.getAmount(),transaction.getDate()) ;
    }

    @GetMapping("transactions/{date}")
    public List<Transaction> getAllTransactionsAtSpecificDate(@PathVariable String date){
       return  conversionService.getByDate(date);//List<Transaction> transactions =
    }

    @PostMapping("/{sourceAmount}/{sourceCurrency}/{targetCurrency}")//todo DTO za da gi napavam kato Json
    public String currencyConversion(@PathVariable double sourceAmount, @PathVariable String sourceCurrency, @PathVariable String targetCurrency) {
        Transaction transaction = conversionService.currencyConversion(sourceAmount, sourceCurrency, targetCurrency);
        System.out.println(transaction.getTransactionId());
        return String.format("Transaction ID: %d \nTarget currency amount: %.5f", transaction.getTransactionId(), transaction.getAmount());
    }

}

