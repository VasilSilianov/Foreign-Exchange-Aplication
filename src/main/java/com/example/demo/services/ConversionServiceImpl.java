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


    /**
     *
     * @param id the uniq ID of the specific transaction
     * @return Returns the transaction by the filed ID
     */

    @Override
    public Transaction getById(int id) {
      return   transactionRepository.getOne(id);
    }

    /**
     *
     * @param date This is the date of the transaction creation
     * @return This method returns list of transactions filtered by date
     */
    @Override
    public List<Transaction> getByDate(Date date) {
        return transactionRepository.getTransactionsByDate(date);//date.getYear(),date.getMonth(),date.getDay());
    }

    /**
     *
     * @param sourceAmount double value that represents the amount of the source currency
     * @param sourceCurrency String that represents the source currency
     * @param targetCurrency String that represents the target currency
     * @return Returns object of type Transactions  with uniq ID transactions amount equal to(sourceAmount * getClientService().exchangeRate(sourceCurrency,targetCurrency))
     * and Date value that represents the date of creation of the transaction
     */

    @Override
    public Transaction currencyConversion(double sourceAmount, String sourceCurrency , String targetCurrency)  {
        Transaction transaction = new Transaction();
        Date date = new Date();
        transaction.setAmount(sourceAmount*getClientService().exchangeRate(sourceCurrency,targetCurrency));
        transaction.setDate(date);
        return     transactionRepository.save(transaction);
    }

    public ClientService getClientService() {
        return clientService;
    }
}
