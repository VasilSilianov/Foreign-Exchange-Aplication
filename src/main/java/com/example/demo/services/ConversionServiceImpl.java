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
    public static final String ID_DOSE_NOT_EXIST = "There is not transaction with ID: %d";
    public static final String NO_TRANSACTIONS_FOUND = "There is no transaction with date: %s in the data base.";

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
      try{

        return   transactionRepository.getOne(id);
      }catch (Exception e){
          throw  new IllegalArgumentException(String.format(ID_DOSE_NOT_EXIST,id));
      }
    }

    /**
     *
     * @param date This is the date of the transaction creation
     * @return This method returns list of transactions filtered by date
     */
    @Override
    public List<Transaction> getByDate(Date date) {
        List<Transaction> filteredTransactions = transactionRepository.getTransactionsByDate(date);
             if (filteredTransactions.size()!=0){
                return filteredTransactions;
             }
            throw  new IllegalArgumentException(String.format(NO_TRANSACTIONS_FOUND,date.toString()));
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
