package com.example.demo.controllers.rest;

import com.example.demo.models.Transaction;
import com.example.demo.services.contracts.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/conversion")
public class ConversionRestController {
    public  static final String  CONVERSION =  "Transaction ID: %d \nTarget currency amount: %.5f";
    public  static final String  TRANSACTION =  "Transaction ID: %d\n" + "Amount: %.5f\n" + "Date: %s";
    public  static final String  DATE =  "yyyy-MM-dd";
    private ConversionService conversionService;

    @Autowired
    public ConversionRestController(ConversionService conversionService) {
        this.conversionService = conversionService;
    }


    /**
     * @param id int variable that represents the ID of a transaction
     * @return String that is formatted  to represent  a transaction filtered  by ID
     */
    @GetMapping("/transaction/{id}")
    public String getTransactionById(@PathVariable int id){
        Transaction transaction= conversionService.getById(id);
        return  String.format(TRANSACTION,transaction.getTransactionId(),transaction.getAmount(),transaction.getDate()) ;
    }

    /**
     * @param date This is a String that is formatted "yyyy-MM-dd" and represents the date you want to filter by
     * @return List of transaction filtered by specific date
     */
    @GetMapping("transactions/{date}")
    public List<Transaction> getAllTransactionsAtSpecificDate(@PathVariable String date) throws ParseException {

        Date real_date = new SimpleDateFormat(DATE).parse(date);
       return  conversionService.getByDate(real_date);//List<Transaction> transactions =
    }

    /**
     * @param sourceAmount double value that represents the amount of the source currency
     * @param sourceCurrency String that represents the source currency
     * @param targetCurrency String that represents the target currency
     * @return String that represents the transaction that has been create  and is formatted to shaw only the ID of the transaction and the target amount
     */
    @PostMapping("/{sourceAmount}/{sourceCurrency}/{targetCurrency}")//todo DTO za da gi napavam kato Json
    public String currencyConversion(@PathVariable double sourceAmount, @PathVariable String sourceCurrency, @PathVariable String targetCurrency)  {
        Transaction transaction = conversionService.currencyConversion(sourceAmount, sourceCurrency, targetCurrency);
        return String.format(CONVERSION, transaction.getTransactionId(), transaction.getAmount());
    }

}

