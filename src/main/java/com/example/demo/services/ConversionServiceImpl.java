package com.example.demo.services;

import com.example.demo.models.Transaction;
import com.example.demo.repositiries.TransactionRepository;
import com.example.demo.services.contracts.ClientService;
import com.example.demo.services.contracts.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConversionServiceImpl implements ConversionService {

    private ClientService clientService;
    private TransactionRepository transactionRepository;

    @Autowired
    public ConversionServiceImpl(ClientService clientService,TransactionRepository transactionRepository) {
        this.clientService = clientService;
        this.transactionRepository = transactionRepository;
    }


    @Override
    public Transaction currencyConversion(double sourceAmount, String sourceCurrency , String targetCurrency){
        Transaction transaction = new Transaction();
        transaction.setAmount(sourceAmount*clientService.exchangeRate(sourceCurrency,targetCurrency));
        transaction.setDate("28.08.2020");//TODO date
        return     transactionRepository.save(transaction);

    }
}
