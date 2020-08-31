package com.example.demo.services;


import com.example.demo.models.Transaction;
import com.example.demo.repositiries.TransactionRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class ConversionServiceImplTest {

    @InjectMocks
    ConversionServiceImpl mockConversionService;

    @Mock
    ClientServiceImpl clientService;

    @Mock
    TransactionRepository transactionRepository;


    @Test
    public void getByIdShould_CallRepository(){
        //arrange
        Transaction transaction = new Transaction();
        transaction.setTransactionId(1);
        Mockito.when(transactionRepository.getOne(1)).thenReturn(transaction);
        //act
        Assert.assertEquals(transaction, mockConversionService.getById(1));
        //assert
        Mockito.verify(transactionRepository,Mockito.times(1)).getOne(1);

    }


    @Test public void getByDate_ShouldReturnListOfTransactionsFilteredByDate() throws ParseException {
        Date date= new Date();
        Transaction transaction1 = new Transaction();
        Transaction transaction2 = new Transaction();
        Transaction transaction3 = new Transaction();
        transaction1.setAmount(100);
        transaction1.setDate(date);
        transaction2.setAmount(100);
        transaction2.setDate(date);
        transaction3.setAmount(100);
        Date otherDate = new SimpleDateFormat("yyyy-MM-dd").parse("2020-08-27");
        transaction3.setDate(otherDate);
        List<Transaction> transactions = new ArrayList<>();
        transactions.add(transaction1);
        transactions.add(transaction2);
        transactions.add(transaction3);
        List<Transaction> transactionsFiltered = new ArrayList<>();
        transactionsFiltered.add(transaction1);
        transactionsFiltered.add(transaction2);
        Mockito.when(transactionRepository.getTransactionsByDate(date)).thenReturn(transactionsFiltered);

        Assert.assertNotEquals(transactions.size(),transactionRepository.getTransactionsByDate(date).size());

    }
    
}

