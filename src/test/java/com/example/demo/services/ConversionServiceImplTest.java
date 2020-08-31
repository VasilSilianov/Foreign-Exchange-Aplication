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

import java.util.Date;

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

    @Test
    public void currencyConversionShould_ReturnTransaction(){
        //arrange
        Transaction transaction = new Transaction();
        Date date = new Date();
        transaction.setTransactionId(1);
         transaction.setDate(date);
        Mockito.when(clientService.exchangeRate("USD","EUR")).thenReturn(1.0d);
        transaction.setAmount(200 * clientService.exchangeRate("USD","EUR"));
//        Mockito.when(clientService).thenReturn(new ClientServiceImpl());
//        Mockito.when(transactionRepository.save(transaction)).thenReturn(transaction);
        Mockito.when(mockConversionService.getClientService()).thenReturn(clientService);
//        Mockito.when(mockConversionService.getClientService()).thenReturn(clientService);
//        Mockito.when(mockConversionService.currencyConversion(200,"USD","EUR")).thenReturn(transaction);
        //assert,act
//        Mockito.verify(transactionRepository,Mockito.times(1)).
//                save(transaction);
        Assert.assertEquals(transaction,mockConversionService.currencyConversion(200,"USD","EUR"));
    }


    @Test
    public void currencyConversionShould_ReturnTransaction2(){
        Transaction transaction = new Transaction();
//        transaction=mockConversionService.currencyConversion(100,"USD","EUR");
        transaction.setAmount(100);
        Mockito.when(transactionRepository.getOne(Mockito.anyInt()))
                .thenReturn(transaction);

        Transaction transactionToReturn = new Transaction();//= mockConversionService.currencyConversion(100,"USD","EUR");
        Mockito.when(mockConversionService.currencyConversion(100,"USD","EUR"))
                .thenReturn(transactionToReturn);

       // transactionToReturn.setAmount(100);
        System.out.println("transactionToReturn.getAmount()11111111111111111111111111111111111111111111111111111111111111");
        System.out.println(transaction.getAmount());
        System.out.println(transactionToReturn.getAmount());

        Assert.assertEquals(transaction.getAmount(), transactionToReturn.getAmount());

    }
}

