package com.example.demo.services;

import com.example.demo.models.Transaction;
import com.example.demo.repositiries.TransactionRepository;
import com.example.demo.services.contracts.ClientService;
import com.example.demo.services.contracts.ConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
    public Transaction getById(int id) {
      return   transactionRepository.getOne(id);
    }

    @Override
    public List<Transaction> getByDate(String date) {
        return transactionRepository.getByDate(date);
    }

    @Override
    public Transaction currencyConversion(double sourceAmount, String sourceCurrency , String targetCurrency){
        Transaction transaction = new Transaction();
        Date date = new Date();
        transaction.setAmount(sourceAmount*clientService.exchangeRate(sourceCurrency,targetCurrency));
        transaction.setDate(date);//TODO date
        return     transactionRepository.save(transaction);

    }
}
